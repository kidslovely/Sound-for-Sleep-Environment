package com.example.administrator.soundsleep;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.R.drawable.ic_input_add;
import static android.R.drawable.ic_media_pause;
import static android.R.drawable.ic_media_play;
import static android.R.drawable.ic_menu_edit;


public class flistAdapter extends ArrayAdapter<String> {


    ArrayList<Flist> sli=new ArrayList<Flist>();
    ArrayList<String> str=new ArrayList<String>();
    boolean[] ispressed;
    favariteDB fdb;
    ArrayList<PerfectMediaPlayer> mediaPlayer=new ArrayList<PerfectMediaPlayer>();
    String tmp;
    Context context;
    public flistAdapter(Context context, ArrayList<String> str,  ArrayList<Flist> sli, favariteDB fdb) {
        super(context, R.layout.flist_adapter, str);
        this.context = context;
        this.str=str;
        this.sli=sli;
        this.fdb=fdb;
        ispressed=new boolean[str.size()];
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.flist_adapter, parent, false);


        final EditText sname1 = (EditText) rowView.findViewById(R.id.ftlist);
         sname1.setText(sli.get(position).getTitle());

        final ImageButton ib1=(ImageButton) rowView.findViewById(R.id.fplay);

        final ImageButton ib2=(ImageButton) rowView.findViewById(R.id.fexit);

        if(!ispressed[position])
            ib1.setImageResource(ic_media_play);
        else
            ib1.setImageResource(ic_media_pause);

        if(str.get(position)=="isplaying")
        {
            ib1.setImageResource(ic_input_add);
        }
        sname1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tmp=str.get(position);
                ib1.setImageResource(ic_menu_edit);
                str.set(position, "isedit");
            }
        });
        ib1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (str.get(position) == "isedit") {
                    String ti=sname1.getText().toString();
                    sli.get(position).setTitle(ti);
                    fdb.updatesoundList(sli.get(position));
                    str.set(position, "isfavarite");
                    notifyDataSetChanged();

                } else {
                    if (str.get(position) == "isplaying") {
                        fdb.addSound(sli.get(position));
                        for(int i=0;i<MainActivity.allmedia.size();i++) {
                            MainActivity.allmedia.get(i).stop();
                            MainActivity.allmedia.get(i).release();
                        }
                        MainActivity.allmedia.clear();
                        MainActivity.mediaindex.clear();
                        str.set(position, tmp);
                        notifyDataSetChanged();
                    } else {
                        if (!ispressed[position]) {
                            ib1.setImageResource(ic_media_pause);
                            for (int i = 0; i < sli.size(); i++) {
                                if (i != position) {
                                    ispressed[i] = false;
                                }

                            }
                            mediaPlayer.clear();
                            String[] idd;
                            String[] title;
                            String[] icon;
                            String[] music_idd;
                            String[] fpath_idd;
                            String[] vol;
                            String[] music_type;


                          //  String id_i = sli.get(position).getId();
                            String title_i=sli.get(position).getTitle();
                            String icon_i = sli.get(position).getIcon();
                            String mus_i = sli.get(position).getMusicid();
                            String fpa_i = sli.get(position).getFile_path();
                            String vol_i=sli.get(position).getAddMusic();
                            String type_i=sli.get(position).gerMusictype();


                           // idd = id_i.split(",");
   //                         icon = icon_i.split(",");
                            music_idd = mus_i.split(",");
                            fpath_idd = fpa_i.split(",");
                            music_type=type_i.split(",");

                            for (int i = 0; i < music_idd.length; i++) {
                                PerfectMediaPlayer mp;
                                int mi = Integer.parseInt(music_idd[i]);
                                if (mi==-1) {
                                    mp = PerfectMediaPlayer.create(getContext(), fpath_idd[i]);
                                } else {
                                    mp = PerfectMediaPlayer.create(getContext(), mi);
                                }
                                MainActivity.allmedia.add(mp);//1, "White noise", R.drawable.white_noise, R.raw.white_noise, null, 5, 11
                                MainActivity.mediaindex.add(new soundList(sli.get(position).getId(), title_i, R.drawable.music, mi, fpath_idd[i], 5,
                                                 Integer.parseInt(music_type[i])));
                                mediaPlayer.add(mp);
                            }

                            ispressed[position] = true;
                            notifyDataSetChanged();
                        } else {
                            ib1.setImageResource(ic_media_play);
//                            for (int i = 0; i < mediaPlayer.size(); i++) {
//                                mediaPlayer.get(i).stop();
//                                mediaPlayer.get(i).release();
//                            }
                            for(int i=0;i<MainActivity.allmedia.size();i++)
                            {
                                MainActivity.allmedia.get(i).stop();
                                MainActivity.allmedia.get(i).release();
                            }
                            MainActivity.allmedia.clear();
                            MainActivity.mediaindex.clear();
                            mediaPlayer.clear();
                            ispressed[position] = false;

                        }
                        //   Toast.makeText(getContext(), "v:" + Integer.toString(position), Toast.LENGTH_SHORT).show();
                    }
                }
                }

        });
        ib2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(str.get(position)=="isfavarite") {

                    for(int i=0;i<mediaPlayer.size();i++)
                    {
                        mediaPlayer.get(i).stop();
                        mediaPlayer.get(i).release();
                    }
                    mediaPlayer.clear();
                    fdb.deletesoundList(sli.get(position).getTitle(), sli.get(position).gerMusictype());
                }
                str.remove(position);
                sli.remove(position);
                notifyDataSetChanged();
            }

        });
        return rowView;
    }

}