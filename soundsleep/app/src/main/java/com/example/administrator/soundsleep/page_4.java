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

public class page_4 extends android.support.v4.app.Fragment {


    ArrayList<soundList> soundList=new ArrayList<soundList>();
    ArrayList<String> str=new ArrayList<String>();
    GridView simpleList;
    ViewPager viewPager;
    soundDB sdb;
    public page_4(ViewPager viewPager,soundDB sdb)

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
        background.setBackground(getResources().getDrawable(R.drawable.countryside_bg));
        soundList.clear();
        str.clear();
        str.add("1");
        str.add("2");
        str.add("3");
        str.add("4");
        str.add("5");
        str.add("6");
        str.add("7");
        str.add("8");
        str.add("9");


        soundList.add(new soundList(1, "Blackbirds", R.drawable.icon_nature_day_birds, R.raw.nature_day_blackbirds, null, 5, 4));
        soundList.add(new soundList(2, "Crows", R.drawable.icon_nature_day_crow, R.raw.nature_day_crows, null, 5, 4));
        soundList.add(new soundList(3, "Farm", R.drawable.icon_nature_day_farm, R.raw.nature_day_farm, null, 5, 4));
        soundList.add(new soundList(4, "Cowbells", R.drawable.icon_nature_day_cowbells, R.raw.nature_day_cowbells, null, 5, 4));
        soundList.add(new soundList(5, "Sheep", R.drawable.icon_nature_day_sheep, R.raw.nature_day_sheep, null, 5, 4));
        soundList.add(new soundList(6, "Horse", R.drawable.icon_nature_day_horse, R.raw.nature_day_horse, null, 5, 4));
        soundList.add(new soundList(7, "Eagle", R.drawable.icon_nature_day_eagle, R.raw.nature_day_eagle, null, 5, 4));
        soundList.add(new soundList(8, "Turtledove", R.drawable.icon_nature_day_turtledove, R.raw.nature_day_turtledove, null, 5, 4));
        soundList.add(new soundList(9, "cicadas", R.drawable.icon_nature_day_cicadas, R.raw.nature_day_cicadas, null, 5, 4));

//        List<soundList> slist=sdb.getTypeSounds("3");
//
//        for(int i=0;i<slist.size();i++)
//        {
//            str.add(slist.get(i).getTitle());
//            soundList.add(slist.get(i));
//        }
//        str.add("Add Item");
//        soundList.add(new soundList(slist.size()+1, "Add Item", R.drawable.icon_add, -1, null, 0, 4));

        simpleList = (GridView)linearLayout.findViewById(R.id.gridview);

        gridApapter myAdapter = new gridApapter(getContext(), R.layout.grid_lay, str, soundList, viewPager, sdb);
        simpleList.setAdapter(myAdapter);
        return linearLayout;

    }
}