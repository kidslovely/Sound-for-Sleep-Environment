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

public class page11 extends android.support.v4.app.Fragment {


    ArrayList<soundList> soundList=new ArrayList<soundList>();
    ArrayList<String> str=new ArrayList<String>();

    GridView simpleList;
    ViewPager viewPager;
    soundDB sdb;
    public page11(ViewPager viewPager, soundDB sdb)
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
        background.setBackground(getResources().getDrawable(R.drawable.noise_bg));
        soundList.clear();
        str.clear();
        str.add("1");
        str.add("2");
        str.add("3");
        str.add("4");
        str.add("5");
        str.add("6");


        soundList.add(new soundList(1, "White noise", R.drawable.white_noise, R.raw.white_noise, null, 5, 11));
        soundList.add(new soundList(2, "Pink noise", R.drawable.white_noise, R.raw.pink_noise, null, 5, 11));
        soundList.add(new soundList(3, "Blue noise", R.drawable.white_noise, R.raw.blue_noise, null, 5, 11));
        soundList.add(new soundList(4, "Brown noise", R.drawable.white_noise, R.raw.brown_noise, null, 5, 11));
        soundList.add(new soundList(5, "Gray noise", R.drawable.white_noise, R.raw.gray_noise, null, 5, 11));
        soundList.add(new soundList(6, "purple noise", R.drawable.white_noise, R.raw.purple_noise, null, 5, 11));

//        List<soundList> slist=sdb.getTypeSounds("10");
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