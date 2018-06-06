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

public class page_8 extends android.support.v4.app.Fragment {


    ArrayList<soundList> soundList=new ArrayList<soundList>();
    ArrayList<String> str=new ArrayList<String>();
    GridView simpleList;
    ViewPager viewPager;
    soundDB sdb;
    public page_8(ViewPager viewPager,soundDB sdb)
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
        background.setBackground(getResources().getDrawable(R.drawable.home_bg));
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

        soundList.add(new soundList(1, "Fan", R.drawable.icon_home_fan, R.raw.home_fan, null, 5, 8));
        soundList.add(new soundList(2, "Air Conditioner", R.drawable.icon_home_air_conditioner, R.raw.home_air_conditioner, null, 5, 8));
        soundList.add(new soundList(3, "Hairdryer", R.drawable.icon_home_hairdryer, R.raw.home_hairdryer, null, 5, 8));
        soundList.add(new soundList(4, "Vacum cleane", R.drawable.icon_home_washing_machine, R.raw.home_vacuum_cleaner, null, 5, 8));
        soundList.add(new soundList(5, "Cat purring", R.drawable.icon_home_cat_purring, R.raw.home_cat_purring, null, 5, 8));
        soundList.add(new soundList(6, "Shower", R.drawable.icon_home_shower, R.raw.home_shower, null, 5, 8));
        soundList.add(new soundList(7, "Washing machine", R.drawable.icon_home_washing_machine, R.raw.home_washing_machine, null, 5, 8));
        soundList.add(new soundList(8, "Jacuzi", R.drawable.icon_home_jacuzzi, R.raw.home_jacuzzi, null, 5, 8));
        soundList.add(new soundList(9, "Fridge", R.drawable.icon_home_fridge, R.raw.home_fridge, null, 5, 8));

        simpleList = (GridView)linearLayout.findViewById(R.id.gridview);

        gridApapter myAdapter = new gridApapter(getContext(), R.layout.grid_lay, str, soundList, viewPager, sdb);
        simpleList.setAdapter(myAdapter);


        return linearLayout;

    }
}