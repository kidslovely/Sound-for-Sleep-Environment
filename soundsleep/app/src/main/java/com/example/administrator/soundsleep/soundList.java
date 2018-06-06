package com.example.administrator.soundsleep;

/**
 * Created by Administrator on 5/11/2018.
 */

public class soundList {

    int id;
    String title;
    int icon_id;
    int music_id;
    String file_path;
    int add_music;
    int music_type;

    public soundList()
    {
    }
    public soundList(int id,String title,int iconid, int music_id, String file_path, int add_music, int music_type)
    {
        this.id=id;
        this.title=title;
        this.icon_id=iconid;
        this.music_id=music_id;
        this.file_path=file_path;
        this.add_music=add_music;
        this.music_type=music_type;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setIcon(int icon_id) {
        this.icon_id = icon_id;
    }
    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }
    public void setMusicid(int music_id) {
        this.music_id = music_id;
    }
    public void setAddMusic(int add_music) {
        this.add_music = add_music;
    }
    public void serMusictype(int music_type){this.music_type = music_type;}

    public int getId() {
        return id;
    }
    public String getTitle() {
         return title;
    }
    public int getMusicid() {
        return music_id;
    }
    public int getIcon() {return icon_id;}
    public String getFile_path() {
        return file_path;
    }
    public int getAddMusic() { return add_music; }
    public int gerMusictype(){return music_type;}

}
