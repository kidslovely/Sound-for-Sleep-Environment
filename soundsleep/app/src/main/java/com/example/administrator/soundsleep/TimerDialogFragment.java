package com.example.administrator.soundsleep;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class TimerDialogFragment extends DialogFragment {

    static CountDownTimer counter;
    private int timevalue;
    long timecount;
    MainActivity mainA;
    TimerDialogFragment thisD;
    static int timer_off;
   static String timerS;

    TimerDialogFragment(MainActivity mainA)
    {
      this.mainA=mainA;
      this.thisD=this;
    }
    private void startTimer(long time, final String tims){
        timecount=time/1000;
        counter = new CountDownTimer(time, 1000){
            public void onTick(long millisUntilDone){
                String timss="";
                timecount--;
                    long hh=timecount / 3600;
                    long mm=(timecount-hh*3600)/60;
                    long ss=timecount-mm*60-hh*3600;
                    String sh=Long.toString(hh);
                    String sm=Long.toString(mm);
                    String sss=Long.toString(ss);
                    if(hh<10)
                        sh="0"+sh;
                    if(mm<10)
                        sm="0"+sm;
                    if(ss<10)
                        sss="0"+ss;
                    if(hh!=0)
                        timss =  sh+ ":" +sm+":"+sss;
                    else
                        timss =sm+":"+sss;
                timer_off=1;
                TimerDialogFragment.timerS=timss;
                if(MainActivity.notification_on_off==1)
                   MainActivity.thisA.startNotification(setting_activity.nplay_count % 2);
                BottomNavigationViewHelper.removeShiftMode(MainActivity.bottomNavigationView, 1,timss);
            }

            public void onFinish() {
                for(int i=0;i<MainActivity.allmedia.size();i++) {
                    if(MainActivity.allmedia.get(i)!=null) {
                        MainActivity.allmedia.get(i).stop();
                        MainActivity.allmedia.get(i).release();
                    }
                }
                MainActivity.allmedia.clear();
                MainActivity.mediaindex.clear();
                timer_off=0;
                MainActivity.thisA.stopNotification();
                mainA.finish();
            }
        }.start();
    }
    public void setting_timer(long timeV)
    {
        if(counter != null) {
            counter.cancel();
            counter = null;
        }
        if(timeV!=0)
          startTimer(timeV, "h1");
        else
          BottomNavigationViewHelper.removeShiftMode(MainActivity.bottomNavigationView, 1,"Timer");
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_timer_dialog_fragment, null);
        final RadioButton toff=(RadioButton) view.findViewById(R.id.toff);
        final RadioButton t5=(RadioButton) view.findViewById(R.id.t5);
        final RadioButton t10=(RadioButton) view.findViewById(R.id.t10);
        final RadioButton t15=(RadioButton) view.findViewById(R.id.t15);
        final RadioButton t30=(RadioButton) view.findViewById(R.id.t30);
        final RadioButton h1=(RadioButton) view.findViewById(R.id.h1);
        final RadioButton h2=(RadioButton) view.findViewById(R.id.h2);
        final RadioButton h3=(RadioButton) view.findViewById(R.id.h3);
        final Button tcustom=(Button)  view.findViewById(R.id.tcustom);
        final Button tcancel=(Button)  view.findViewById(R.id.tcancel);
        builder.setView(view)
                .setTitle("Timer Setting");

        final AlertDialog d = builder.create();
        d.setCanceledOnTouchOutside(false);
        d.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {

                toff.setOnClickListener(new View.OnClickListener() {
                @Override
                 public void onClick(View view) {
                  if(toff.isChecked())
                  {
                      if(counter != null) {
                          counter.cancel();
                          counter = null;
                      }
                      TimerDialogFragment.timerS="Timer is off";
                      if(MainActivity.notification_on_off==1)
                          MainActivity.thisA.startNotification(setting_activity.nplay_count % 2);
                      BottomNavigationViewHelper.removeShiftMode(MainActivity.bottomNavigationView, 1,"Timer");
                      d.dismiss();
                  }
                 }
                });
                t5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        long tval=0;
                        String ts="";

                        if(t5.isChecked())
                        {
                            if(counter != null) {
                                counter.cancel();
                                counter = null;
                            }
                            tval = 5 * 1000 * 60;
                             ts="t5";

                            startTimer(tval, ts);
                            d.dismiss();
                        }
                    }
                });
                t10.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        long tval=0;
                        String ts="";

                        if(t10.isChecked())
                        {
                            if(counter != null) {
                                counter.cancel();
                                counter = null;
                            }
                            tval = 10 * 1000 * 60;
                            ts="t5";
                            startTimer(tval, ts);
                            d.dismiss();
                        }
                    }
                });
                t15.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        long tval=0;
                        String ts="";

                        if(t15.isChecked())
                        {
                            if(counter != null) {
                                counter.cancel();
                                counter = null;
                            }
                            tval = 15 * 1000 * 60;
                            ts="t5";
                            startTimer(tval, ts);
                            d.dismiss();
                        }
                    }
                });
                t30.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        long tval=0;
                        String ts="";

                        if(t30.isChecked())
                        {
                            if(counter != null) {
                                counter.cancel();
                                counter = null;
                            }
                            tval = 30 * 1000 * 60;
                            ts="t5";
                            startTimer(tval, ts);
                            d.dismiss();
                        }
                    }
                });
                h1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        long tval=0;
                        String ts="";

                        if(h1.isChecked())
                        {
                            if(counter != null) {
                                counter.cancel();
                                counter = null;
                            }
                            tval = 60 * 1000 * 60;
                            ts="h1";
                            startTimer(tval, ts);
                            d.dismiss();
                        }
                    }
                });
                h2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        long tval=0;
                        String ts="";

                        if(h2.isChecked())
                        {
                            if(counter != null) {
                                counter.cancel();
                                counter = null;
                            }
                            tval = 2* 60 * 1000 * 60;
                            ts="h1";
                            startTimer(tval, ts);
                            d.dismiss();
                        }
                    }
                });
                h3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        long tval=0;
                        String ts="";

                        if(h3.isChecked())
                        {
                            if(counter != null) {
                                counter.cancel();
                                counter = null;
                            }
                            tval = 3*60 * 1000 * 60;
                            ts="h1";
                            startTimer(tval, ts);
                            d.dismiss();
                        }
                    }
                });

                tcustom.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        timecount=0;
                        if(counter != null) {
                            counter.cancel();
                            counter = null;
                        }
                        tsetting_dialog tsetting=new tsetting_dialog(thisD, null);
                        tsetting.show(getFragmentManager(), "customer_id");

                        d.dismiss();
                    }
                });
                tcancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        d.dismiss();
                    }
                });
            }
        });

        return d;
    }
}
