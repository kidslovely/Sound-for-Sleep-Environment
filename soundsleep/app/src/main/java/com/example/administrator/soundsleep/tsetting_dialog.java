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
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.TimePicker;
import android.widget.Toast;

public class tsetting_dialog extends DialogFragment {

    static CountDownTimer counter;
    private int timevalue;
    long timecount;
    TimerDialogFragment pareD;
    setting_activity pareS;
    int hour;
    int min;

    tsetting_dialog(TimerDialogFragment thisD, setting_activity pareS)

    {
        this.pareD=thisD;
        this.pareS=pareS;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.tsetting_dialog, null);
        final Button tcustom=(Button)  view.findViewById(R.id.tcustom);
        final Button tcancel=(Button)  view.findViewById(R.id.tcancel);
        final NumberPicker numberpicker1 = (NumberPicker)view.findViewById(R.id.numberPicker1);
        final NumberPicker numberpicker2 = (NumberPicker)view.findViewById(R.id.numberPicker2);
        builder.setView(view)
                .setTitle("Time Setting");

        final AlertDialog d = builder.create();
        d.setCanceledOnTouchOutside(false);
        d.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {


                numberpicker1.setMinValue(0);
                if(pareS==null)
                  numberpicker1.setMaxValue(12);
                else
                  numberpicker1.setMaxValue(23);

                numberpicker2.setMinValue(0);

                numberpicker2.setMaxValue(59);

                numberpicker1.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                    @Override
                    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                     //use newval
                        hour=newVal;
                    }
                });
                numberpicker2.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                    @Override
                    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                        min=newVal;
                    }
                });
                tcustom.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(pareS==null)
                         pareD.setting_timer(hour*60*60*1000+min*60*1000);
                        else
                          pareS.setting_timer(hour, min);
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
