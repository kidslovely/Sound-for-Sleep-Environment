package com.example.administrator.soundsleep;

/**
 * Created by Administrator on 5/16/2018.
 */
public class Flist {

    int id;
    String title;
    String icon_id;
    String music_id;
    String file_path;
    String add_music;
    String music_type;

    public Flist()
    {
    }

    public Flist(int id,String title,String iconid, String music_id, String file_path, String add_music, String music_type)
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
    public void setIcon(String icon_id) {
        this.icon_id = icon_id;
    }
    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }
    public void setMusicid(String  music_id) {
        this.music_id = music_id;
    }
    public void setAddMusic(String add_music) {
        this.add_music = add_music;
    }
    public void serMusictype(String music_type){this.music_type = music_type;}

    public int getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getMusicid() {
        return music_id;
    }
    public String getIcon() {return icon_id;}
    public String getFile_path() {
        return file_path;
    }
    public String getAddMusic() { return add_music; }
    public String gerMusictype(){return music_type;}

}
