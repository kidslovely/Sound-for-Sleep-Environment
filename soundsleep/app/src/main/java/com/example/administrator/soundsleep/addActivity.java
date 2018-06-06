package com.example.administrator.soundsleep;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v4.content.CursorLoader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.net.URI;

public class addActivity extends AppCompatActivity {
    int REQUEST_PATH=1;
    EditText title;// = (EditText) findViewById(R.id.music_title);
    EditText fpath;
    static gridApapter as;
    static int pos;
   static  Uri addmusic;
    // = (EditText) findViewById(R.id.file_path);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        Button fbrowser=(Button)findViewById(R.id.bbrowser);
        Button fadd=(Button)findViewById(R.id.add);
        Button fcancel=(Button)findViewById(R.id.cancel);
        title = (EditText) findViewById(R.id.music_title);
        fpath = (EditText) findViewById(R.id.file_path);
        fbrowser.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent fileintent = new Intent(Intent.ACTION_GET_CONTENT);
                fileintent.addCategory(Intent.CATEGORY_OPENABLE);
                fileintent.setType("*/*");
                try {
                    startActivityForResult(Intent.createChooser(fileintent,"Select file or dir"), REQUEST_PATH);
                    // curFileName="/mnt/1.mp3";
                } catch (ActivityNotFoundException e) {
                    Log.e("tag", "No activity can handle picking a file. Showing alternatives.");
                }
            }

        });
        fadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(title.length()==0)
                {
                    Toast.makeText(addActivity.this, "Title no Input", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(fpath.length()==0) {
                        Toast.makeText(addActivity.this, "file path no Input", Toast.LENGTH_SHORT).show();
                    }
                    else {
                       String ft= title.getText().toString();
                       String fp= fpath.getText().toString();
                        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
                        SharedPreferences.Editor editor = pref.edit();
                        editor.putString("mtitle", ft);
                        editor.putString("mfilepath", fp);
                        editor.commit();
                        addActivity.as.add_sound(addActivity.pos);
                        onBackPressed();
                    }
                }

            }

        });
        fcancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
              onBackPressed();
            }

        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            super.onActivityResult(requestCode, resultCode, data);

            if (requestCode == REQUEST_PATH  && resultCode  == RESULT_OK) {

                addActivity.addmusic= data.getData();
              //  String filepath = getRealPathFromURI(uri);//uri.getPath();

                 String filepath = data.getData().getPath();
                 String[] lpath;
                 lpath=filepath.split(":");
                //External strage path setting. u must modify  Environment.getExternalStorageDirectory().getPath()
                fpath.setText(Environment.getExternalStorageDirectory().getPath()+"/"+lpath[lpath.length-1]);//Environment.getExternalStorageDirectory().getPath()+
                Toast.makeText(getApplicationContext(), Uri.decode(data.getDataString()), Toast.LENGTH_SHORT).show();

            }
        } catch (Exception ex) {
            Toast.makeText(addActivity.this, ex.toString(),
                    Toast.LENGTH_SHORT).show();
        }

    }
}
