package com.example.administrator.soundsleep;

import android.Manifest;
import android.app.ActionBar;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.media.AudioManager;
import android.os.Build;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.RemoteViews;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static android.R.drawable.ic_media_pause;
import static android.R.drawable.ic_media_play;
import static com.example.administrator.soundsleep.R.id.gridview;
import static com.example.administrator.soundsleep.R.id.parent;
import static java.security.AccessController.getContext;

import android.widget.AdapterView.OnItemClickListener;
public class MainActivity extends AppCompatActivity {

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final ArrayList<Fragment> mFragmentList = new ArrayList<>();
        private final ArrayList<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }
        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    GridView simpleList;
    int play_and_stop;
    soundDB sdb;
    static BottomNavigationView bottomNavigationView;
    static ArrayList<PerfectMediaPlayer> allmedia=new ArrayList<PerfectMediaPlayer>();
    static ArrayList<soundList>   mediaindex=new ArrayList<soundList>();
    static int vpn;
    static MainActivity thisA;
    ViewPager viewPager;
    NotificationManager notificationManager;
    RemoteViews notificationView;

    static  int notification_on_off;
    AudioManager audioManager = null;

    public void set_viewp()
    {
        viewPager = (ViewPager) findViewById(R.id.pager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setOffscreenPageLimit(1);
        adapter.addFragment(new page_1(viewPager,sdb), "Rain");
        adapter.addFragment(new page_2(viewPager,sdb), "Ocean");
        adapter.addFragment(new page_3(viewPager,sdb), "Wind & Fire");
        adapter.addFragment(new page_4(viewPager,sdb), "countrySide");
        adapter.addFragment(new page_5(viewPager,sdb), "Oriental");
        adapter.addFragment(new page_6(viewPager,sdb), "Night");
        adapter.addFragment(new page_7(viewPager,sdb), "City");
        adapter.addFragment(new page_8(viewPager,sdb), "Home");
        adapter.addFragment(new psge_9(viewPager,sdb), "Relaxing");
        adapter.addFragment(new page_10(viewPager,sdb), "River");
        adapter.addFragment(new page11(viewPager,sdb), "Color of noise");
        adapter.addFragment(new page_12(viewPager,sdb), "Custom Music");
        viewPager.setAdapter(adapter);


        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        tabLayout.setupWithViewPager(viewPager);
        viewPager.setCurrentItem(vpn);
    }
    public void startNotification(int kk){

        //Android 8.0 is 26 version
//        notificationView = new RemoteViews(getPackageName(), R.layout.notification);
//        if(Build.VERSION.SDK_INT <26)
//        {
//            Notification notification;
//            notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//            notification= new Notification(R.drawable.ic_launcher, null, System.currentTimeMillis());
//            notification.contentView = notificationView;
//
//            if(kk==0) {
//                notificationView.setImageViewResource(R.id.n2, ic_media_pause);
//                if(TimerDialogFragment.timer_off==0)
//                    notificationView.setTextViewText(R.id.ntext, "soundsleep|timer is off\n   playing");
//                else
//                    notificationView.setTextViewText(R.id.ntext, "soundsleep|"+TimerDialogFragment.timerS+"\n   playing");
//            }
//            if(kk==1) {
//                notificationView.setImageViewResource(R.id.n2, ic_media_play);
//                if(TimerDialogFragment.timer_off==0)
//                    notificationView.setTextViewText(R.id.ntext, "soundsleep|timer is off\n   paused");
//                else
//                    notificationView.setTextViewText(R.id.ntext, "soundsleep|"+TimerDialogFragment.timerS+"\n   paused");
//            }
//            if(kk==2) {
//                notificationView.setImageViewResource(R.id.n2, 0);
//                notificationView.setTextViewText(R.id.ntext, "soundsleep\n   Bedtime");
//            }
//            //the intent that is started when the notification is clicked (works)
//            Intent notificationIntent = new Intent(this, MainActivity.class);
//            PendingIntent pendingNotificationIntent = PendingIntent.getActivity(this, 0,
//                    notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
//
//
//            notification.contentIntent = pendingNotificationIntent;
//            notification.flags |= Notification.FLAG_NO_CLEAR;
//
//            //this is the intent that is supposed to be called when the
//            //button is clicked
//            Intent b1 = new Intent(this, bplay.class);
//            PendingIntent bp1 = PendingIntent.getBroadcast(this, 0,
//                    b1, 0);
//
//            notificationView.setOnClickPendingIntent(R.id.n2,bp1);
//
//            Intent b2 = new Intent(this, bclose.class);
//            PendingIntent bp2 = PendingIntent.getBroadcast(this, 0,b2, 0);
//
//            notificationView.setOnClickPendingIntent(R.id.n3, bp2);
//
//            notificationManager.notify(1, notification);
//
//        }
//        else
//            {
//            notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//            NotificationChannel channel = new NotificationChannel("default", "Channel name", NotificationManager.IMPORTANCE_DEFAULT);
//                channel.enableLights(true);
//                channel.enableVibration(true);
//                channel.setLightColor(Color.GRAY);
//                channel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
//
//            if(kk==0) {
//                notificationView.setImageViewResource(R.id.n2, ic_media_pause);
//                if(TimerDialogFragment.timer_off==0)
//                    notificationView.setTextViewText(R.id.ntext, "soundsleep|timer is off\n   playing");
//                else
//                    notificationView.setTextViewText(R.id.ntext, "soundsleep|"+TimerDialogFragment.timerS+"\n   playing");
//            }
//            if(kk==1) {
//                notificationView.setImageViewResource(R.id.n2, ic_media_play);
//                if(TimerDialogFragment.timer_off==0)
//                    notificationView.setTextViewText(R.id.ntext, "soundsleep|timer is off\n   paused");
//                else
//                    notificationView.setTextViewText(R.id.ntext, "soundsleep|"+TimerDialogFragment.timerS+"\n   paused");
//            }
//            if(kk==2) {
//                notificationView.setImageViewResource(R.id.n2, 0);
//                notificationView.setTextViewText(R.id.ntext, "soundsleep\n   Bedtime");
//            }
//
//            //button is clicked
//            Intent b1 = new Intent(this, bplay.class);
//            PendingIntent bp1 = PendingIntent.getBroadcast(this, 0,
//                    b1, 0);
//
//            notificationView.setOnClickPendingIntent(R.id.n2,bp1);
//
//            Intent b2 = new Intent(this, bclose.class);
//            PendingIntent bp2 = PendingIntent.getBroadcast(this, 0,b2, 0);
//
//            notificationView.setOnClickPendingIntent(R.id.n3, bp2);
//            Intent notification_intent = new Intent(this,MainActivity.class);
//            PendingIntent pendingIntent = PendingIntent.getActivity(this,0,notification_intent,0);
//
//            NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
//                    builder.setSmallIcon(R.drawable.ic_launcher)
//                    .setChannel("default")
//                    .setCustomContentView(notificationView)
//                    .setContentIntent(pendingIntent)
//                    ;
//
//                notificationManager.createNotificationChannel(channel);
//                notificationManager.notify(1, builder.build());
//        }
        final Intent showClock = new Intent(this, MainActivity.class);
        final PendingIntent pending = PendingIntent.getActivity(this, 0, showClock, PendingIntent.FLAG_UPDATE_CURRENT);
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= 11) {
            notificationView = new RemoteViews(getPackageName(), R.layout.notification);

            if(kk==0) {
                notificationView.setImageViewResource(R.id.n2, ic_media_pause);
                if(TimerDialogFragment.timer_off==0)
                    notificationView.setTextViewText(R.id.ntext, "soundsleep|timer is off\n   playing");
                else
                    notificationView.setTextViewText(R.id.ntext, "soundsleep|"+TimerDialogFragment.timerS+"\n   playing");
            }
            if(kk==1) {
                notificationView.setImageViewResource(R.id.n2, ic_media_play);
                if(TimerDialogFragment.timer_off==0)
                    notificationView.setTextViewText(R.id.ntext, "soundsleep|timer is off\n   paused");
                else
                    notificationView.setTextViewText(R.id.ntext, "soundsleep|"+TimerDialogFragment.timerS+"\n   paused");
            }
            if(kk==2) {
                notificationView.setImageViewResource(R.id.n2, 0);
                notificationView.setTextViewText(R.id.ntext, "soundsleep\n   Bedtime");
            }

            //button is clicked
            Intent b1 = new Intent(this, bplay.class);
            PendingIntent bp1 = PendingIntent.getBroadcast(this, 0,
                    b1, 0);

            notificationView.setOnClickPendingIntent(R.id.n2,bp1);

            Intent b2 = new Intent(this, bclose.class);
            PendingIntent bp2 = PendingIntent.getBroadcast(this, 0,b2, 0);

            notificationView.setOnClickPendingIntent(R.id.n3, bp2);

//             NotificationChannel channel = new NotificationChannel("123", "Channel name", NotificationManager.IMPORTANCE_DEFAULT);
//                channel.enableLights(true);
//                channel.enableVibration(true);
//                channel.setLightColor(Color.GRAY);
//                channel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);

            final Notification.Builder builder = new Notification.Builder(this)
                    .setContentTitle("sleep")
                    .setCustomContentView(notificationView)
                    .setSmallIcon(R.drawable.ic_launcher)
                    .setOngoing(true);
           // builder.setChannelId("123");
            builder.setContentIntent(pending);
           // notificationManager.createNotificationChannel(channel);
            notificationManager.notify(0, builder.build());
        } else {
            final android.support.v4.app.NotificationCompat.Builder builder = new android.support.v4.app.NotificationCompat.Builder(this)
                    .setContentTitle("leep")
                    .setSmallIcon(R.drawable.ic_launcher)
                    .setOngoing(true);
            builder.setContentIntent(pending);
            notificationManager.notify(0, builder.build());
        }
    }

    public void stopNotification() {

         for (int i = 0; i < MainActivity.allmedia.size(); i++) {
                MainActivity.allmedia.get(i).stop();
                MainActivity.allmedia.get(i).release();
            }
            MainActivity.allmedia.clear();
            MainActivity.mediaindex.clear();
           notification_on_off=0;
        notificationManager.cancel(0);
    }

    public static class bplay extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {

            setting_activity.nplay_count=setting_activity.nplay_count+1;
            int ncount=setting_activity.nplay_count;
            if((ncount % 2) ==0)
            {
                for(int i=0; i<MainActivity.allmedia.size();i++)
                    MainActivity.allmedia.get(i).start();
            }
            else
            {
                for(int i=0; i<MainActivity.allmedia.size();i++)
                    MainActivity.allmedia.get(i).pause();

            }
            MainActivity.thisA.startNotification(ncount % 2);

        }
    }

    public static class bclose extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            MainActivity.thisA.stopNotification();
            MainActivity.thisA.set_viewp();
            //  Toast.makeText(context, "cloase", Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) !=PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},20);
        }

         sdb=new soundDB(this);
        thisA=this;
        FavoriteActivity.av=this;
        volumFragment.av=this;
        audioManager = (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);
        audioManager.setStreamVolume(AudioManager.STREAM_SYSTEM, audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)*2/3, 0);

        bottomNavigationView=(BottomNavigationView)findViewById(R.id.bottom_navigation);

        set_viewp();
        final Context context = getApplicationContext();


        BottomNavigationViewHelper.removeShiftMode(bottomNavigationView, 0,"".toString());

        Button cb=(Button)findViewById(R.id.cus_b);
        cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(11);
            }
        });
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()) {
                    case R.id.action_one:
                        play_and_stop++;
                        if((play_and_stop %2)==1) {
                            item.setIcon(R.drawable.nav_pause);
                            for(int i=0;i<MainActivity.allmedia.size();i++) {
                                if(MainActivity.allmedia.get(i)!=null) {
                                    MainActivity.allmedia.get(i).pause();
                                }
                            }
                        }
                        if((play_and_stop %2)==0) {
                           item.setIcon(R.drawable.nav_play);
                            for(int i=0;i<MainActivity.allmedia.size();i++) {
                                if(MainActivity.allmedia.get(i)!=null) {
                                    MainActivity.allmedia.get(i).start();
                                }
                            }

                        }
                        break;
                    case R.id.action_two:
                        TimerDialogFragment timerd= new TimerDialogFragment(thisA);
                        timerd.show(getFragmentManager(), "customer_id");
                        break;
                    case R.id.action_three:
                        FavoriteActivity.vp=viewPager;
                        Intent intent = new Intent(MainActivity.this, FavoriteActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.action_four:
                        volumFragment.vp=viewPager;
                        volumFragment volumd= new volumFragment(MainActivity.this);
                        volumd.show(getFragmentManager(), "customer_id");
                        break;
                    case R.id.action_five:
                        Intent intent1 = new Intent(MainActivity.this, setting_activity.class);
                        startActivity(intent1);
                        break;

                }
                return true;
            }
        });
    }


}
