package com.example.administrator.soundsleep;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.R.attr.data;
import static android.app.Activity.RESULT_OK;


public class adddialog extends DialogFragment {
   // UserItemActivity MainA;
  //  UserDbHandler db;
    int REQUEST_PATH=1234;
    public String curTitle;
    public String curFileName;
    gridApapter adap;
    int pos;
    adddialog(gridApapter adap,int pos)
    {
      this.adap=adap;
      this.pos=pos;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Activity ss=getActivity();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_adddialog, null);
        final EditText title = (EditText) view.findViewById(R.id.music_title);
        final EditText fpath = (EditText) view.findViewById(R.id.file_path);
        final  Button fbrowser=(Button)view.findViewById(R.id.bbrowser);


      //  int did=db.getLastId()+1;
      //  id.setText(Integer.toString(did));

        builder.setView(view)
                .setTitle("Add New Music")
                .setPositiveButton("Add", null)
                .setNegativeButton("Cancel",null);

        final AlertDialog d = builder.create();
        d.setCanceledOnTouchOutside(false);

        d.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                Button b = d.getButton(AlertDialog.BUTTON_POSITIVE);
                Button n=d.getButton(AlertDialog.BUTTON_NEGATIVE);
                fbrowser.setOnClickListener(new View.OnClickListener() {

                  @Override
                  public void onClick(View v) {
                      Intent fileintent = new Intent(Intent.ACTION_GET_CONTENT);
                      fileintent.addCategory(Intent.CATEGORY_OPENABLE);
                      fileintent.setType("*/*");
                     try {
                          startActivityForResult(fileintent, REQUEST_PATH);
                         // curFileName="/mnt/1.mp3";
                      } catch (ActivityNotFoundException e) {
                          Log.e("tag", "No activity can handle picking a file. Showing alternatives.");
                      }
                      //
                  }

                });

                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String ctitle = title.getText().toString();
                        String cfile=fpath.getText().toString();

                        if ((ctitle.length() > 0) && (cfile.length()>0)) {
                            //SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getActivity());
                            //SharedPreferences.Editor editor = pref.edit();
                            SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getActivity());
                            SharedPreferences.Editor editor = pref.edit();
                            editor.putString("mtitle", ctitle);
                            editor.putString("mfilepath", cfile);
                            editor.commit();
                            adap.add_sound(pos);
                             d.dismiss();
                            } else {
                                Toast.makeText(getActivity(), "no input sound information", Toast.LENGTH_SHORT).show();
                            }
                        }
                });
                n.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getActivity());
                        SharedPreferences.Editor editor = pref.edit();
                        editor.putString("mtitle", null);
                        editor.putString("mfilepath", null);
                        editor.commit();
                        d.dismiss();
                    }
                });
            }
        });

        return d;
    }
}
