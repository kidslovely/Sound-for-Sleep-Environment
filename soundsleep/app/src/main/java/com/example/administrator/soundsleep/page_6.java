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

public class page_6 extends android.support.v4.app.Fragment {


    ArrayList<soundList> soundList=new ArrayList<soundList>();
    ArrayList<String> str=new ArrayList<String>();
    GridView simpleList;
    ViewPager viewPager;
    soundDB sdb;
    public page_6(ViewPager viewPager,soundDB sdb)
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
        background.setBackground(getResources().getDrawable(R.drawable.night_bg));
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

        soundList.add(new soundList(1, "Crickets", R.drawable.icon_nature_night_cricket, R.raw.nature_night_crickets, null, 5, 6));
        soundList.add(new soundList(2, "Grasshoppers", R.drawable.icon_nature_night_grasshoppers, R.raw.nature_night_grasshoppers, null, 5, 6));
        soundList.add(new soundList(3, "Owls", R.drawable.icon_nature_night_owl, R.raw.nature_night_owls, null, 5, 6));
        soundList.add(new soundList(4, "Wolves", R.drawable.icon_nature_night_wolf, R.raw.nature_night_wolves, null, 5, 6));
        soundList.add(new soundList(5, "Loon", R.drawable.icon_nature_night_loons, R.raw.nature_night_loons, null, 5, 6));
        soundList.add(new soundList(6, "Coyote", R.drawable.icon_nature_night_coyote, R.raw.nature_night_coyote, null, 5, 6));
        soundList.add(new soundList(7, "Coqui", R.drawable.icon_nature_night_coqui, R.raw.nature_night_coqui, null, 5, 6));
        soundList.add(new soundList(8, "Frogs", R.drawable.icon_nature_night_frog, R.raw.nature_night_frogs, null, 5, 6));

//        List<soundList> slist=sdb.getTypeSounds("5");
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