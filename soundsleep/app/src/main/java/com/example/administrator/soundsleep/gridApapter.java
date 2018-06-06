package com.example.administrator.soundsleep;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

import static java.security.AccessController.getContext;
import android.content.*;
/**
 * Created by Administrator on 5/10/2018.
 */

public class gridApapter extends ArrayAdapter<String> {

    ArrayList<soundList> soundlist = new ArrayList<soundList>();
    ArrayList<String> str = new ArrayList<String>();
    LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    int pos;
    PerfectMediaPlayer[] mediaPlayer;
    SeekBar seekBar;
    ViewPager viewPager;
    gridApapter adap;
    soundDB sdb;

    public gridApapter(Context context, int textViewResourceID,ArrayList<String>str, ArrayList<soundList> soundlist, ViewPager viewPager,  soundDB sdb){
        super(context,textViewResourceID, str);
        this.soundlist = soundlist;
        this.viewPager=viewPager;
        this.str=str;
        adap=this;
        mediaPlayer=new PerfectMediaPlayer[soundlist.size()];
        this.sdb=sdb;
    }
    public void update()
    {
        notifyDataSetChanged();
    }
    public void add_sound(int pos)
    {
         SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getContext());
         String title = pref.getString("mtitle", null);
          String fpath= pref.getString("mfilepath", null);
        if(title!=null && fpath!=null) {
            soundlist.remove(pos);
            str.remove(pos);
            int scount=sdb.getsoundListCount();
            sdb.addSound(new soundList(scount+1, title, R.drawable.music, -1, fpath, 5, viewPager.getCurrentItem()+1));
            soundlist.add(new soundList(pos, title, R.drawable.music, -1, fpath, 5, 0));
            str.add("new");
            soundlist.add(new soundList(pos + 1, "Add Item", R.drawable.icon_add, -1, null, 5, 1));
            str.add("add");
            Toast.makeText(getContext(), "Music is added", Toast.LENGTH_SHORT).show();
            notifyDataSetChanged();
        }
    }
    @Override
    public int getCount() {
        return super.getCount();
    }

    //Get View
    @Override
    public View getView(final int position, final View convertView, ViewGroup parent){

        final ViewHolder viewHolder;
        View v = convertView;

        if(v ==null){
            v = inflater.inflate(R.layout.grid_lay,parent,false);
            viewHolder = new ViewHolder();
            assert v != null;

            viewHolder.title = (TextView) v.findViewById(R.id.itext);
            viewHolder.image = (ImageView) v.findViewById(R.id.iimg);
            viewHolder.seekBar1 = (SeekBar) v.findViewById(R.id.seekbar);
            v.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) v.getTag();
        }

        viewHolder.title.setText(soundlist.get(position).getTitle());
        viewHolder.image.setImageResource(soundlist.get(position).getIcon());
        FrameLayout fr=(FrameLayout)v.findViewById(R.id.fr);

        int isactive=0;
        int seekvalue=0;
        viewHolder.seekBar1.setProgress(soundlist.get(position).getAddMusic());

        ////////////
        viewHolder.seekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChanged = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChanged = progress;
                if (mediaPlayer[position] != null) {

                    for(int i=0;i<MainActivity.mediaindex.size();i++)
                    {
                        if(MainActivity.mediaindex.get(i)==soundlist.get(position))
                            MainActivity.mediaindex.get(i).setAddMusic(progressChanged);
                    }

                    soundlist.get(position).setAddMusic(progressChanged);
                    mediaPlayer[position].setVolume(progressChanged, progressChanged);
                }

            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        ////////
        for(int i=0;i<MainActivity.allmedia.size();i++)
        {
            int ptype=MainActivity.mediaindex.get(i).gerMusictype();
            int pid=MainActivity.mediaindex.get(i).getMusicid();
            int id=MainActivity.mediaindex.get(i).getId();
            String ppath=MainActivity.mediaindex.get(i).getFile_path();
            if(viewPager.getCurrentItem()!=11) {
                if ((ptype == soundlist.get(position).gerMusictype()) && (pid == soundlist.get(position).getMusicid())) {
                    isactive = 1;
                    seekvalue = MainActivity.mediaindex.get(i).getAddMusic();
                    mediaPlayer[position] = MainActivity.allmedia.get(i);
                    str.set(position, "pus");
                }
            }
            else
            {     if(id == soundlist.get(position).getId()) {
                        isactive = 1;
                        seekvalue = MainActivity.mediaindex.get(i).getAddMusic();
                        mediaPlayer[position] = MainActivity.allmedia.get(i);
                        str.set(position, "pus");
                    }

            }
        }
        if(isactive==1)
        {
            viewHolder.seekBar1.setVisibility(View.VISIBLE);
            fr.setAlpha(0.7f);
            viewHolder.seekBar1.setProgress(seekvalue);
        }

        fr.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    seekBar = (SeekBar) v.findViewById(R.id.seekbar);
                    FrameLayout fr1 = (FrameLayout) v.findViewById(R.id.fr);
                       if (viewPager.getCurrentItem() == 11) {


                           if(position == (soundlist.size() - 1))
                           {
                               addActivity.as = adap;
                               addActivity.pos = position;
                               Intent intent = new Intent(getContext(), addActivity.class);
                               getContext().startActivity(intent);
                           }
                           else
                           {
                               if (seekBar.getVisibility() == View.INVISIBLE) {

                                   if(MainActivity.allmedia.size()<10) {
                                       // MainActivity.bottomNavigationView.setSelectedItemId(R.id.action_one);
                                       if (mediaPlayer[position] == null) {
                                           Toast.makeText(getContext(), "volume:"+Integer.toString(seekBar.getProgress()), Toast.LENGTH_SHORT).show();
                                           soundlist.get(position).setAddMusic(seekBar.getProgress());
                                           mediaPlayer[position] = PerfectMediaPlayer.create(getContext(), soundlist.get(position).getFile_path());
                                           mediaPlayer[position].setVolume(soundlist.get(position).getAddMusic(), soundlist.get(position).getAddMusic());
                                           MainActivity.allmedia.add(mediaPlayer[position]);
                                           MainActivity.mediaindex.add(soundlist.get(position));
                                           seekBar.setProgress(soundlist.get(position).getAddMusic());
                                       }
                                       seekBar.setVisibility(View.VISIBLE);
                                       fr1.setAlpha(0.7f);
                                       MainActivity.notification_on_off=1;
                                       setting_activity.nplay_count=0;
                                       MainActivity.thisA.startNotification(0);
                                       if (mediaPlayer[position] != null)
                                           mediaPlayer[position].start();
                                   }
                                   else
                                   {

                                       Toast.makeText(getContext(), "Playing Music number is not more than 10", Toast.LENGTH_SHORT).show();
                                   }

                               } else {

                                   seekBar.setVisibility(View.INVISIBLE);
                                   fr1.setAlpha(0.3f);

                                   if (mediaPlayer[position] != null) {
                                       for(int i=0;i<MainActivity.allmedia.size();i++) {

                                           if(mediaPlayer[position]==MainActivity.allmedia.get(i))
                                           {
                                               mediaPlayer[position].stop();
                                               mediaPlayer[position].release();
                                               mediaPlayer[position] = null;
                                               MainActivity.mediaindex.remove(i);
                                               MainActivity.allmedia.remove(i);
                                           }

                                       }

                                   }
                                   if(MainActivity.allmedia.size()==0)
                                       MainActivity.thisA.stopNotification();

                               }

                               notifyDataSetChanged();
                           }
                       } else {

                           if (seekBar.getVisibility() == View.INVISIBLE) {

                               if(MainActivity.allmedia.size()<10) {
                                   // MainActivity.bottomNavigationView.setSelectedItemId(R.id.action_one);
                                   if (mediaPlayer[position] == null) {
                                       Toast.makeText(getContext(), "volume:"+Integer.toString(seekBar.getProgress()), Toast.LENGTH_SHORT).show();
                                       soundlist.get(position).setAddMusic(seekBar.getProgress());
                                       mediaPlayer[position] = PerfectMediaPlayer.create(getContext(), soundlist.get(position).getMusicid());
                                       mediaPlayer[position].setVolume(soundlist.get(position).getAddMusic(), soundlist.get(position).getAddMusic());
                                       MainActivity.allmedia.add(mediaPlayer[position]);
                                       MainActivity.mediaindex.add(soundlist.get(position));
                                       seekBar.setProgress(soundlist.get(position).getAddMusic());
                                   }
                                   seekBar.setVisibility(View.VISIBLE);
                                   fr1.setAlpha(0.7f);
                                   MainActivity.notification_on_off=1;
                                   setting_activity.nplay_count=0;
                                   MainActivity.thisA.startNotification(0);
                                   if (mediaPlayer[position] != null)
                                       mediaPlayer[position].start();
                               }
                               else
                               {

                                   Toast.makeText(getContext(), "Playing Music number is not more than 10", Toast.LENGTH_SHORT).show();
                               }

                           } else {
                            //   if (PerfectMediaPlayer.allmedia.size() == 0)
                                //   MainActivity.bottomNavigationView.setSelectedItemId(R.id.action_one);
                                   seekBar.setVisibility(View.INVISIBLE);
                                   fr1.setAlpha(0.3f);
                                   if (mediaPlayer[position] != null) {
                                       mediaPlayer[position].stop();
                                       mediaPlayer[position].release();
                                       mediaPlayer[position] = null;
                                       for(int i=0;i<MainActivity.mediaindex.size();i++)
                                       {
                                           if(viewPager.getCurrentItem()!=11) {
                                               if (MainActivity.mediaindex.get(i).getMusicid() == soundlist.get(position).getMusicid()) {
                                                   if (MainActivity.mediaindex.get(i).gerMusictype() == soundlist.get(position).gerMusictype())
                                                       MainActivity.mediaindex.remove(i);
                                                   MainActivity.allmedia.remove(i);
                                               }
                                           } else {
                                               if (MainActivity.mediaindex.get(i).getFile_path() == soundlist.get(position).getFile_path()) {
                                                   if (MainActivity.mediaindex.get(i).gerMusictype() == soundlist.get(position).gerMusictype())
                                                       MainActivity.mediaindex.remove(i);
                                                   MainActivity.allmedia.remove(i);
                                               }
                                           }
                                       }
                                   }
                               if(MainActivity.allmedia.size()==0)
                                   MainActivity.thisA.stopNotification();

                           }

                           notifyDataSetChanged();
                       }
                    //pus
//                    seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//                        int progressChanged = 0;
//
//                        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                            progressChanged = progress;
////                             if (mediaPlayer[position] != null)
////                                 mediaPlayer[position].setVolume(progressChanged, progressChanged);
//
//                        }
//
//                        public void onStartTrackingTouch(SeekBar seekBar) {
//                            // TODO Auto-generated method stub
//                        }
//
//                        public void onStopTrackingTouch(SeekBar seekBar) {
//                            if (mediaPlayer[position] != null) {
//
//                                for(int i=0;i<MainActivity.mediaindex.size();i++)
//                                {
//                                    if(MainActivity.mediaindex.get(i)==soundlist.get(position))
//                                        MainActivity.mediaindex.get(i).setAddMusic(progressChanged);
//                                }
//                                Toast.makeText(getContext(), "volnume:"+Integer.toString(progressChanged), Toast.LENGTH_SHORT).show();
//                                soundlist.get(position).setAddMusic(progressChanged);
//                                mediaPlayer[position].setVolume(progressChanged, progressChanged);
//                            }
//
//                        }
//                    });
                }

        });
        fr.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View arg0) {
                AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
                alert.setTitle("Delete this music?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dig, int which) {
                        if(soundlist.get(position).getIcon()==R.drawable.music) {
                            sdb.deletesoundList(soundlist.get(position).getTitle(), soundlist.get(position).getFile_path(), soundlist.get(position).gerMusictype());
                            soundlist.remove(position);
                            str.remove(position);
                            Toast.makeText(getContext(), "Music deleted", Toast.LENGTH_SHORT).show();
                            notifyDataSetChanged();
                            dig.dismiss();
                        }
                        else
                        {
                            Toast.makeText(getContext(), "This music can not delete", Toast.LENGTH_SHORT).show();
                            dig.dismiss();
                        }

                    }
                });
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                alert.show();

                return false;
            }
        });



        return v;

    }

    private static class ViewHolder{
        ImageView image;
        TextView title;
        SeekBar seekBar1;
    }
}