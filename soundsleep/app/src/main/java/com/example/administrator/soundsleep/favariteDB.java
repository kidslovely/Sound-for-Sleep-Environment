package com.example.administrator.soundsleep; /**
 * Created by Administrator on 5/12/2018.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;


public class favariteDB extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 2;
    // Database Name
    private static final String DATABASE_NAME = "sounddb1.db";
    // Contacts table name
    private static final String TABLE_NAME = "ftable";
    // Shops Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_FILE = "filepath";
    private static final String KEY_MusicId = "musicid";
    private static final String KEY_TYPE = "type";

    public favariteDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
                + KEY_ID + " INTEGER PRIMARY KEY, " + KEY_TITLE + " TEXT, "
                + KEY_FILE + " TEXT, " + KEY_MusicId + " TEXT, "+KEY_TYPE + " TEXT " + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
// Creating tables again
        onCreate(db);
    }

    // Adding new user
    public void addSound(Flist sound) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ID, sound.getId());
        values.put(KEY_TITLE, sound.getTitle()); // soundList Name
        values.put(KEY_FILE, sound.getFile_path());
        values.put(KEY_MusicId, sound.getMusicid());
        // soundList ip
        values.put(KEY_TYPE, sound.gerMusictype());
        // Inserting Row
        db.insert(TABLE_NAME, null, values);
        db.close(); // Closing database connection
    }



    // Getting All users
    public ArrayList<Flist> getAllSounds() {
        ArrayList<Flist> soundLists = new ArrayList<Flist>();
// Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

// looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Flist soundList = new Flist();
                soundList.setId(Integer.parseInt(cursor.getString(0)));
                soundList.setTitle(cursor.getString(1));
                soundList.setFile_path(cursor.getString(2));
                soundList.setMusicid(cursor.getString(3));
                soundList.serMusictype(cursor.getString(4));
// Adding contact to list
                soundLists.add(soundList);
            } while (cursor.moveToNext());
        }
        db.close();
        cursor.close();
// return contact list
        return soundLists;
    }

    // Getting users Count
    public int getsoundListCount() {
        String countQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count=cursor.getCount();
        cursor.close();
        db.close();
// return count
        return count;
    }

    // Updating a shop
    public int updatesoundList(Flist soundlist) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, soundlist.getTitle());
      //  values.put(KEY_FILE, soundlist.getFile_path());
      //  values.put(KEY_TYPE, soundlist.gerMusictype());
// updating row
        return db.update(TABLE_NAME, values, KEY_ID + " = ?",
                new String[]{String.valueOf(soundlist.getId())});
    }

    // Deleting a shop
    public void deletesoundList(String title, String music_type) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_NAME, KEY_TITLE+" = ?"+" AND "+KEY_TYPE + " = ?",
                new String[]{title, music_type});
        db.close();
    }

}
