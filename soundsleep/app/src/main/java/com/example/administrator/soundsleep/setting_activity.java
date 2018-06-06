package com.example.administrator.soundsleep;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.icu.util.Calendar;
import android.net.Uri;
import android.os.CountDownTimer;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;


import static android.R.drawable.btn_star_big_off;
import static android.R.drawable.btn_star_big_on;
import static android.R.drawable.ic_media_pause;
import static android.R.drawable.ic_media_play;
import static android.R.drawable.ic_menu_close_clear_cancel;
import static java.security.AccessController.getContext;


public class setting_activity extends AppCompatActivity {


    int star_score;
    static CountDownTimer counter;
    static int nplay_count;
    static long nhour;
    static long nmin;
    static setting_activity thisA;
    static int tirmer_check;

    public void rateMe() {
        try {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("market://details?id=" + this.getPackageName())));
        } catch (android.content.ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details?id=" + this.getPackageName())));
        }
    }
    public void share_app()
    {
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Text");
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject");
        startActivity(Intent.createChooser(sharingIntent, "Share using"));
    }
    public void email_contact(String subject, String bodyText)
    {
        String mailto = "mailto:bob@example.org" +
                "?cc=" + "alice@example.com" +
                "&subject=" + Uri.encode(subject) +
                "&body=" + Uri.encode(bodyText);

        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse(mailto));

        try {
            startActivity(emailIntent);
        } catch (ActivityNotFoundException e) {
            //TODO: Handle case where no email app is available
        }
    }
    public void setting_timer(long hh, long mm)
    {
        long timeV=hh*60*60*hh*1000+mm*60*1000;
        final TextView stext=(TextView)findViewById(R.id.timet);
        String shh="";
        String sss="";
        if(hh<10)
            shh="0"+Long.toString(hh);
        else
            shh=Long.toString(hh);
        if(mm<10)
            sss="0"+Long.toString(mm);
        else
            sss=Long.toString(mm);
        stext.setText(shh+":"+sss);
        if(counter != null) {
            counter.cancel();
            counter = null;
        }
        if(timeV!=0) {
            setting_activity.nhour=hh;
            setting_activity.nmin=mm;
            startTimer(timeV);
        }
    }
    private void startTimer(long time){

        counter = new CountDownTimer(time, 1000){
            public void onTick(long millisUntilDone){
                Calendar currentTime    = Calendar.getInstance();
                long chour                = currentTime.get(Calendar.HOUR_OF_DAY);
                long cminute              = currentTime.get(Calendar.MINUTE);

                if( (chour==setting_activity.nhour) && (setting_activity.nmin==cminute))
                {
                    MainActivity.thisA.startNotification(2);
                    this.cancel();
                }
            }

            public void onFinish() {

            }
        }.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        thisA=this;

        final Button share_button=(Button)findViewById(R.id.share);
        final Button email_button=(Button)findViewById(R.id.email);
        final ImageView star1=(ImageView)findViewById(R.id.star1);
        final ImageView star2=(ImageView)findViewById(R.id.star2);
        final ImageView star3=(ImageView)findViewById(R.id.star3);
        final ImageView star4=(ImageView)findViewById(R.id.star4);
        final ImageView star5=(ImageView)findViewById(R.id.star5);
        final Button  set=(Button)findViewById(R.id.set);
        final Switch sw=(Switch)findViewById(R.id.switch1);
        final TextView stext=(TextView)findViewById(R.id.timet);

        String shh="";
        String sss="";
        if(setting_activity.nhour<10)
            shh="0"+Long.toString(setting_activity.nhour);
        else
            shh=Long.toString(setting_activity.nhour);
        if(setting_activity.nmin<10)
            sss="0"+Long.toString(setting_activity.nmin);
        else
            sss=Long.toString(setting_activity.nmin);
        stext.setText(shh+":"+sss);

        sw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sw.isChecked())
                {

                  setting_activity.tirmer_check=1;
                }
                else
                {   if(counter != null) {
                     counter.cancel();
                     counter = null;
                     }
                     setting_activity.tirmer_check=0;
                }
            }
        });

        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tsetting_dialog tsetting=new tsetting_dialog(null, thisA);
                tsetting.show(getFragmentManager(), "customer_id");
            }
        });
        share_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            share_app();
            }
        });
        email_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String subject="sleep sound";
                String bodyText="This is Sound App";
                email_contact("", bodyText);
            }
        });
        star1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                star1.setImageResource(btn_star_big_on);
                star2.setImageResource(btn_star_big_off);
                star3.setImageResource(btn_star_big_off);
                star4.setImageResource(btn_star_big_off);
                star5.setImageResource(btn_star_big_off);
                star_score=1;
                rateMe();
            }
        });
        star2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                star_score=2;
                star1.setImageResource(btn_star_big_on);
                star2.setImageResource(btn_star_big_on);
                star3.setImageResource(btn_star_big_off);
                star4.setImageResource(btn_star_big_off);
                star5.setImageResource(btn_star_big_off);
                rateMe();
            }
        });
        star3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                star_score=3;
                star1.setImageResource(btn_star_big_on);
                star2.setImageResource(btn_star_big_on);
                star3.setImageResource(btn_star_big_on);
                star4.setImageResource(btn_star_big_off);
                star5.setImageResource(btn_star_big_off);
                rateMe();
            }
        });
        star4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                star_score=4;
                star1.setImageResource(btn_star_big_on);
                star2.setImageResource(btn_star_big_on);
                star3.setImageResource(btn_star_big_on);
                star4.setImageResource(btn_star_big_on);
                star5.setImageResource(btn_star_big_off);
                rateMe();
            }
        });
        star5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                star_score=5;
                star1.setImageResource(btn_star_big_on);
                star2.setImageResource(btn_star_big_on);
                star3.setImageResource(btn_star_big_on);
                star4.setImageResource(btn_star_big_on);
                star5.setImageResource(btn_star_big_on);
                rateMe();
            }
        });
        ImageButton backpress=(ImageButton) findViewById(R.id.sbackp);
        backpress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }

        });
    }
}
