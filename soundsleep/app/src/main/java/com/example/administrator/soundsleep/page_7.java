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

public class page_7 extends android.support.v4.app.Fragment {


    ArrayList<soundList> soundList=new ArrayList<soundList>();
    ArrayList<String> str=new ArrayList<String>();
    GridView simpleList;
    ViewPager viewPager;
    soundDB sdb;
    public page_7(ViewPager viewPager,soundDB sdb)
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
        background.setBackground(getResources().getDrawable(R.drawable.city_bg));
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

        soundList.add(new soundList(1, "Misician", R.drawable.icon_city_musician, R.raw.city_musician, null, 5, 7));
        soundList.add(new soundList(2, "Coffe shop", R.drawable.icon_city_coffee_shop, R.raw.city_coffee_shop, null, 5, 7));
        soundList.add(new soundList(3, "Fountain", R.drawable.icon_water_creek, R.raw.city_fountain, null, 5, 7));
        soundList.add(new soundList(4, "Children", R.drawable.icon_city_children, R.raw.city_children, null, 5, 7));
        soundList.add(new soundList(5, "Traffict", R.drawable.icon_city_traffic, R.raw.city_traffic, null, 5, 7));
        soundList.add(new soundList(6, "Subway", R.drawable.icon_city_subway, R.raw.city_subway, null, 5, 7));
        soundList.add(new soundList(7, "Road work", R.drawable.icon_city_road_work, R.raw.city_works, null, 5, 7));
        soundList.add(new soundList(8, "Train station", R.drawable.icon_city_subway, R.raw.city_train_station, null, 5, 7));
//        List<soundList> slist=sdb.getTypeSounds("6");
//
//        for(int i=0;i<slist.size();i++)
//        {
//            str.add(slist.get(i).getTitle());
//            soundList.add(slist.get(i));
//        }
//        str.add("Add Item");
//        soundList.add(new soundList(slist.size()+1, "Add Item", R.drawable.icon_add, -1, null, 0, 1));

        simpleList = (GridView)linearLayout.findViewById(R.id.gridview);

        gridApapter myAdapter = new gridApapter(getContext(), R.layout.grid_lay, str, soundList, viewPager, sdb);
        simpleList.setAdapter(myAdapter);

        return linearLayout;

    }
}