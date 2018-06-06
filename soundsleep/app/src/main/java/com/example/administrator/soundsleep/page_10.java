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

public class page_10 extends android.support.v4.app.Fragment {


    ArrayList<soundList> soundList=new ArrayList<soundList>();
    ArrayList<String> str=new ArrayList<String>();
    GridView simpleList;
    ViewPager viewPager;
    soundDB sdb;
    public page_10(ViewPager viewPager, soundDB sdb)
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
        background.setBackground(getResources().getDrawable(R.drawable.river_bg));
        soundList.clear();
        str.clear();
        str.add("1");
        str.add("2");
        str.add("3");
        str.add("4");

        soundList.add(new soundList(1, "River", R.drawable.icon_water_river, R.raw.water_river, null, 5, 10));
        soundList.add(new soundList(2, "Brook", R.drawable.icon_water_brook, R.raw.water_brook, null, 5, 10));
        soundList.add(new soundList(3, "Greek", R.drawable.icon_water_creek, R.raw.water_creek, null, 5, 10));
        soundList.add(new soundList(4, "Waterfall", R.drawable.icon_water_waterfall, R.raw.water_waterfall, null, 5, 10));

        simpleList = (GridView)linearLayout.findViewById(R.id.gridview);

        gridApapter myAdapter = new gridApapter(getContext(), R.layout.grid_lay, str, soundList, viewPager, sdb);
        simpleList.setAdapter(myAdapter);


        return linearLayout;

    }
}