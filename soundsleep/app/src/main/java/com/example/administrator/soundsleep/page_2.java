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

public class page_2 extends android.support.v4.app.Fragment {


    ArrayList<soundList> soundList=new ArrayList<soundList>();
    ArrayList<String> str=new ArrayList<String>();

    GridView simpleList;
    ViewPager viewPager;
    soundDB sdb;
    public page_2(ViewPager viewPager,soundDB sdb)
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
        background.setBackground(getResources().getDrawable(R.drawable.ocean_bg));
        soundList.clear();
        str.clear();
        // int id,String title,String iconid, int music_id, String file_path, int add_music, int music_type
        str.add("1");
        str.add("2");
        str.add("3");
        str.add("4");
        str.add("5");
        str.add("6");
        str.add("7");

        soundList.add(new soundList(1, "clam waves", R.drawable.icon_ocean_gentle_waves, R.raw.ocean_calm_waves, null, 5, 2));
        soundList.add(new soundList(2, "Waves", R.drawable.icon_ocean_waves, R.raw.ocean_waves, null, 5, 2));
        soundList.add(new soundList(3, "Segullsr", R.drawable.icon_ocean_seagulls, R.raw.ocean_seagulls, null, 5, 2));
        soundList.add(new soundList(4, "Scuba diver", R.drawable.icon_ocean_scuba_diver, R.raw.ocean_diver, null, 5, 2));
        soundList.add(new soundList(5, "Dolphins", R.drawable.icon_ocean_dolphin, R.raw.ocean_dolphins, null, 5, 2));
        soundList.add(new soundList(6, "Sailboart", R.drawable.icon_ocean_sailboat, R.raw.ocean_sailboat, null, 5, 2));
        soundList.add(new soundList(7, "Whale", R.drawable.icon_ocean_whale, R.raw.ocean_whale, null, 5, 2));

        //Adding Sounds to the Grid Views
        simpleList = (GridView)linearLayout.findViewById(R.id.gridview);

        gridApapter myAdapter = new gridApapter(getContext(), R.layout.grid_lay, str, soundList, viewPager, sdb);
        simpleList.setAdapter(myAdapter);

        return linearLayout;

    }
}