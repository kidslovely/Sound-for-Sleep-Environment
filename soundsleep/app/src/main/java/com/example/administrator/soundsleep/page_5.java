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

public class page_5 extends android.support.v4.app.Fragment {

    ArrayList<soundList> soundList=new ArrayList<soundList>();
    ArrayList<String> str=new ArrayList<String>();
    GridView simpleList;
    ViewPager viewPager;
    soundDB sdb;
    public page_5(ViewPager viewPager,soundDB sdb)
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
        background.setBackground(getResources().getDrawable(R.drawable.oriental_bg));
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

        soundList.add(new soundList(1, "Bowls", R.drawable.icon_oriental_bowl, R.raw.oriental_bowls, null, 5, 5));
        soundList.add(new soundList(2, "Gong", R.drawable.icon_oriental_gong, R.raw.oriental_gong, null, 5, 5));
        soundList.add(new soundList(3, "Bells", R.drawable.icon_oriental_bell, R.raw.oriental_bells, null, 5, 5));
        soundList.add(new soundList(4, "Om", R.drawable.icon_oriental_om, R.raw.oriental_om, null, 5, 5));
        soundList.add(new soundList(5, "Flute", R.drawable.icon_oriental_flute, R.raw.oriental_flute, null, 5, 5));
        soundList.add(new soundList(6, "Dideridoo", R.drawable.icon_oriental_didgeridoo, R.raw.oriental_didgeridoo, null, 5, 5));
        soundList.add(new soundList(7, "Chimes", R.drawable.icon_oriental_chimes, R.raw.oriental_chimes, null, 5, 5));
        soundList.add(new soundList(8, "Cuzheng", R.drawable.icon_oriental_string, R.raw.oriental_string, null, 5, 5));

//        List<soundList> slist=sdb.getTypeSounds("4");
//
//        for(int i=0;i<slist.size();i++)
//        {
//            str.add(slist.get(i).getTitle());
//            soundList.add(slist.get(i));
//        }
//        str.add("Add Item");
//        soundList.add(new soundList(slist.size()+1, "Add Item", R.drawable.icon_add, -1, null, 0, 5));
        simpleList = (GridView)linearLayout.findViewById(R.id.gridview);

        gridApapter myAdapter = new gridApapter(getContext(), R.layout.grid_lay, str, soundList, viewPager, sdb);
        simpleList.setAdapter(myAdapter);
        return linearLayout;

    }
}