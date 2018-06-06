package com.example.administrator.soundsleep;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class FavoriteActivity extends AppCompatActivity {

   ArrayList<Flist> ss=new ArrayList<Flist>();
   ArrayList<String>   str=new ArrayList<String>();
    favariteDB fdb;
    static ViewPager vp;
    static  MainActivity av;
    //static gridApapter ga;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        fdb=new favariteDB(this);

        ss=fdb.getAllSounds();


        for(int i=0;i<ss.size();i++)
        {
              str.add("isfavarite");
        }

        if(MainActivity.mediaindex.size()>0) {
            str.add("isplaying");
            int id;
            String title="";
            String icon_id="";
            String music_id="";
            String file_path="";
            String add_music="";
            String music_type="";
            for(int i=0;i<MainActivity.mediaindex.size();i++) {
               if(i==0) {
                  // id=id+Integer.toString(MainActivity.mediaindex.get(i).getId());
                   title = title + MainActivity.mediaindex.get(i).getTitle();
                   icon_id=icon_id+Integer.toString(MainActivity.mediaindex.get(i).getIcon());
                   music_id=music_id+Integer.toString(MainActivity.mediaindex.get(i).getMusicid());
                   file_path=file_path+MainActivity.mediaindex.get(i).getFile_path();
                   add_music=add_music+Integer.toString(MainActivity.mediaindex.get(i).getAddMusic());
                   music_type=music_type+Integer.toString(MainActivity.mediaindex.get(i).gerMusictype());
               }
               else
               {
                  // id=id+","+Integer.toString(MainActivity.mediaindex.get(i).getId());
                   title = title +","+ MainActivity.mediaindex.get(i).getTitle();
                   icon_id=icon_id+","+Integer.toString(MainActivity.mediaindex.get(i).getIcon());
                   music_id=music_id+","+Integer.toString(MainActivity.mediaindex.get(i).getMusicid());
                   file_path=file_path+","+MainActivity.mediaindex.get(i).getFile_path();
                   add_music=add_music+","+Integer.toString(MainActivity.mediaindex.get(i).getAddMusic());
                   music_type=music_type+","+Integer.toString(MainActivity.mediaindex.get(i).gerMusictype());
               }
            }
            id=fdb.getsoundListCount()+1;
            ss.add(new Flist( id, title, icon_id, music_id, file_path, add_music, music_type));
        }


        ListView li=(ListView)findViewById(R.id.flist);
        flistAdapter adapter = new flistAdapter(this, str, ss, fdb);
        li.setAdapter(adapter);

        ImageButton backpress=(ImageButton) findViewById(R.id.backp);
        backpress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.vpn=FavoriteActivity.vp.getCurrentItem();
                  av.set_viewp();
                onBackPressed();
            }

        });
    }
}
