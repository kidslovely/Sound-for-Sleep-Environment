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

public class psge_9 extends android.support.v4.app.Fragment {


    ArrayList<soundList> soundList=new ArrayList<soundList>();
    ArrayList<String> str=new ArrayList<String>();
    GridView simpleList;
    ViewPager viewPager;
    soundDB sdb;
    public psge_9(ViewPager viewPager,soundDB sdb)
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
        background.setBackground(getResources().getDrawable(R.drawable.relax_bg));
        soundList.clear();
        str.clear();
        str.add("1");
        str.add("2");
        str.add("3");
        str.add("4");
        str.add("5");
        str.add("6");
        str.add("7");

        soundList.add(new soundList(1, "Piano", R.drawable.icon_music_piano, R.raw.music_piano, null, 5, 9));
        soundList.add(new soundList(2, "Guitar", R.drawable.icon_music_guitar, R.raw.music_guitar, null, 5, 9));
        soundList.add(new soundList(3, "Violin", R.drawable.icon_music_violin, R.raw.music_violin, null, 5, 9));
        soundList.add(new soundList(4, "Harp", R.drawable.icon_music_harp, R.raw.music_harp, null, 5, 9));
        soundList.add(new soundList(5, "Flute", R.drawable.icon_music_flute, R.raw.music_flute, null, 5, 9));
        soundList.add(new soundList(6, "Sax", R.drawable.icon_city_musician, R.raw.music_sax, null, 5, 9));
        soundList.add(new soundList(7, "Native am.", R.drawable.icon_music_harp, R.raw.music_native, null, 5, 9));

        simpleList = (GridView)linearLayout.findViewById(R.id.gridview);

        gridApapter myAdapter = new gridApapter(getContext(), R.layout.grid_lay, str, soundList, viewPager, sdb);
        simpleList.setAdapter(myAdapter);

        return linearLayout;

    }
}