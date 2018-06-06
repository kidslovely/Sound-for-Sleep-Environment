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

public class page_3 extends android.support.v4.app.Fragment {


    ArrayList<soundList> soundList=new ArrayList<soundList>();
    ArrayList<String> str=new ArrayList<String>();
    GridView simpleList;
    ViewPager viewPager;
    soundDB sdb;
    public page_3(ViewPager viewPager,soundDB sdb)
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
        background.setBackground(getResources().getDrawable(R.drawable.wind_bg));
        soundList.clear();
        str.clear();
        str.add("1");
        str.add("2");
        str.add("3");
        str.add("4");
        str.add("5");
        str.add("6");

        soundList.add(new soundList(1, "Light Wind", R.drawable.icon_air_light_wind, R.raw.air_light_wind, null, 5, 3));
        soundList.add(new soundList(2, "Strong Wind", R.drawable.icon_air_strong_wind, R.raw.air_strong_wind, null, 5, 3));
        soundList.add(new soundList(3, "Mountain wind", R.drawable.icon_air_wind_mountain, R.raw.air_wind_mountain, null, 5, 3));
        soundList.add(new soundList(4, "Under the door", R.drawable.icon_air_wind_door, R.raw.air_wind_door, null, 5, 3));
        soundList.add(new soundList(5, "Campfire", R.drawable.icon_fire_campfire ,R.raw.fire_campfire, null, 5, 3));
        soundList.add(new soundList(6, "Fireplace", R.drawable.icon_fire_fireplace, R.raw.fire_fireplace, null, 5, 3));

//        List<soundList> slist=sdb.getTypeSounds("2");
//
//        for(int i=0;i<slist.size();i++)
//        {
//            str.add(slist.get(i).getTitle());
//            soundList.add(slist.get(i));
//        }
//        str.add("Add Item");
//        soundList.add(new soundList(slist.size()+1, "Add Item", R.drawable.icon_add, -1, null, 0, 3));
        //Adding Sounds to the Grid Views
        simpleList = (GridView)linearLayout.findViewById(R.id.gridview);

        gridApapter myAdapter = new gridApapter(getContext(), R.layout.grid_lay, str, soundList, viewPager, sdb);
        simpleList.setAdapter(myAdapter);
     return linearLayout;

    }
}