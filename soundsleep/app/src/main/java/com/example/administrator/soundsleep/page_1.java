package com.example.administrator.soundsleep;

/**
 * Created by Administrator on 5/10/2018.
 */

import android.content.ClipData;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 5/10/2018.
 */

public class page_1 extends android.support.v4.app.Fragment {


    ArrayList<soundList> soundList=new ArrayList<soundList>();
    ArrayList<String> str=new ArrayList<String>();
    GridView simpleList;
    ViewPager viewPager;
    soundDB sdb;
    public page_1(ViewPager viewPager,soundDB sdb)
    {
        this.viewPager=viewPager;
        this.sdb=sdb;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LinearLayout linearLayout=(LinearLayout)inflater.inflate(R.layout.pager,container,false);

        LinearLayout background=(LinearLayout)linearLayout.findViewById(R.id.background);
        background.setBackground(getResources().getDrawable(R.drawable.rain_bg));
       if(soundList.size()>0) {
           soundList.clear();
       }
       if(str.size()>0)
            str.clear();
       // int id,String title,String iconid, int music_id, String file_path, int add_music, int music_type
        str.add("Morninig rain");
        str.add("Umbrella");
        str.add("Thunder");
        str.add("Umbrella");
        str.add("Light rain");
        str.add("Distabt thunder");
        str.add("Tent");
        str.add("Window");
        str.add("Roof");

        soundList.add(new soundList(1, "Morninig rain", R.drawable.icon_rain_morning, R.raw.rain_morning_rain, null, 5, 1));
        soundList.add(new soundList(2, "Umbrella", R.drawable.icon_rain_umbrella, R.raw.rain_umbrella, null, 5, 1));
        soundList.add(new soundList(3, "Thunder", R.drawable.icon_rain_thunders, R.raw.rain_thunders, null, 5, 1));
        soundList.add(new soundList(4, "Light rain", R.drawable.icon_rain_light, R.raw.rain_light, null, 5, 1));
        soundList.add(new soundList(5, "Heavy rain", R.drawable.icon_rain_heavy, R.raw.rain_heavy, null, 5, 1));
        soundList.add(new soundList(6, "Distabt thunder", R.drawable.icon_rain_distant_thunder, R.raw.rain_distant_thunder, null, 5, 1));
        soundList.add(new soundList(7, "Tent", R.drawable.icon_rain_tent, R.raw.rain_tent, null, 5, 1));
        soundList.add(new soundList(8, "Window", R.drawable.icon_rain_window, R.raw.rain_window, null, 5, 1));
        soundList.add(new soundList(9, "Roof", R.drawable.icon_rain_roof, R.raw.rain_roof, null, 5, 1));

        simpleList = (GridView)linearLayout.findViewById(R.id.gridview);

        gridApapter myAdapter = new gridApapter(getContext(), R.layout.grid_lay, str, soundList, viewPager, sdb);
        simpleList.setAdapter(myAdapter);

        return linearLayout;

    }
}