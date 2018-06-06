package com.example.administrator.soundsleep;

/**
 * Created by Administrator on 5/14/2018.
 */
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class vlist extends ArrayAdapter<String> {

    ArrayList<String> str=new ArrayList<String>();
    ArrayList<soundList> slist=new ArrayList<soundList>();

    Context context;
    public vlist(Context context, ArrayList<String> str, ArrayList<soundList> slist) {
        super(context, R.layout.vlist, str);
        this.context = context;
        this.str=str;
        this.slist=slist;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.vlist, parent, false);

        TextView it=(TextView) rowView.findViewById(R.id.vtlist);
        ImageButton ib2=(ImageButton) rowView.findViewById(R.id.imgexit);
        SeekBar  seekBar=(SeekBar) rowView.findViewById(R.id.vseekbar);
        seekBar.setProgress(slist.get(position).getAddMusic());
        it.setText(slist.get(position).getTitle());
        ib2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.allmedia.get(position)!=null)
                {
                    MainActivity.allmedia.get(position).stop();
                    MainActivity.allmedia.get(position).release();
                    MainActivity.allmedia.set(position, null);
                    MainActivity.allmedia.remove(position);
                    MainActivity.mediaindex.remove(position);
                    str.remove(position);
                    slist.remove(position);
                    MainActivity.vpn=volumFragment.vp.getCurrentItem();
                    volumFragment.av.set_viewp();
                    notifyDataSetChanged();
                }

            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            int progressChanged = 0;
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChanged = progress;
                //MainActivity.allmedia.get(position).setVolume(progressChanged,progressChanged);
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }
            public void onStopTrackingTouch(SeekBar seekBar) {
                MainActivity.allmedia.get(position).setVolume(progressChanged, progressChanged);
                MainActivity.mediaindex.get(position).setAddMusic(progressChanged);
                Toast.makeText(getContext(), "volum:" + progressChanged, Toast.LENGTH_SHORT).show();
                MainActivity.vpn=volumFragment.vp.getCurrentItem();
                volumFragment.av.set_viewp();
                notifyDataSetChanged();
            }
        });

      return rowView;
    }

}
