package com.example.administrator.soundsleep;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import android.content.Context;


import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Andy Tran on 8/27/2015.
 */
public class volumFragment extends DialogFragment {


    MainActivity activity;
    AudioManager audioManager = null;
    static ViewPager vp;
    static  MainActivity av;
    static int volu;
    static int first;
    volumFragment(MainActivity activity)
    {
        this.activity=activity;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.activity_volum_fragment, null);
        final ArrayList<String> str =new ArrayList<String>();
        final ArrayList<soundList> slist=new ArrayList<soundList>();
        builder.setView(view)
                .setTitle("Volume Setting")
                ;

        final AlertDialog d = builder.create();

        d.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                getDialog().setCanceledOnTouchOutside(true);
                audioManager = (AudioManager) getContext().getSystemService(Context.AUDIO_SERVICE);
                Button bott=(Button)view.findViewById(R.id.allclear);
                final SeekBar volu=(SeekBar) view.findViewById(R.id.svolum);
                volu.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));

                 if(first==0) {
                     volu.setProgress(volu.getMax()*2/3);
                     audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,volu.getMax()/2, 0);
                 }
                 else {
                     volu.setProgress(volumFragment.volu);
                     audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,
                             volumFragment.volu, 0);
                 }

                volu.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

                    int progressChanged = 0;
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        progressChanged = progress;
                        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,
                                progressChanged, 0);
                    }
                    public void onStartTrackingTouch(SeekBar seekBar) {
                        // TODO Auto-generated method stub
                    }
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        first=1;
                        volumFragment.volu=progressChanged;
                        Toast.makeText(getContext(), "system volum:" + progressChanged, Toast.LENGTH_SHORT).show();
                    }
                });
                bott.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                      for(int i=0;i<MainActivity.mediaindex.size();i++)
                      {
                          for(int ii=0;ii<MainActivity.mediaindex.size();ii++)
                          {
                              if(MainActivity.allmedia.get(ii)!=null)
                              {
                                  MainActivity.allmedia.get(ii).stop();
                                  MainActivity.allmedia.get(ii).release();
                              }
                          }
                          MainActivity.allmedia.clear();
                          MainActivity.mediaindex.clear();
                          str.clear();
                          slist.clear();
                          ListView listView1=(ListView)view.findViewById(R.id.vlist);
                          vlist adapter1 = new vlist(getContext(),str, slist);
                          listView1.setAdapter(adapter1);
                      }
                        MainActivity.vpn=volumFragment.vp.getCurrentItem();
                        volumFragment.av.set_viewp();
                    }
                });
                for(int i=0;i<MainActivity.mediaindex.size();i++)
                {
                  str.add(Integer.toString(i));
                  slist.add(MainActivity.mediaindex.get(i));
                }
                 ListView listView=(ListView)view.findViewById(R.id.vlist);
                 vlist adapter = new vlist(getContext(),str, slist);
                 listView.setAdapter(adapter);
            }

        });
        return d;
    }

}