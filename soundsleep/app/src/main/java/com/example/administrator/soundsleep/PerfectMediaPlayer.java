package com.example.administrator.soundsleep;

/**
 * Created by Administrator on 5/11/2018.
 */
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URI;
import java.util.ArrayList;

public class PerfectMediaPlayer {


    private static final String TAG = PerfectMediaPlayer.class.getName();
    private final int MAX_VOLUME=1000;
    private Context mContext = null;
    private int mResId = 0;
    private String mPath = null;

    public MediaPlayer mCurrentPlayer = null;
    private MediaPlayer mNextPlayer = null;

    /**
     * Creating instance of the player with given context and raw resource
     *
     * @param context - context
     * @param resId   - raw resource
     * @return new instance
     */
    public static PerfectMediaPlayer create(Context context, int resId) {
        return new PerfectMediaPlayer(context, resId);
    }


    private PerfectMediaPlayer(Context context, int resId) {
        mContext = context;
        mResId = resId;
        try {
            AssetFileDescriptor afd = context.getResources().openRawResourceFd(mResId);
            mCurrentPlayer = new MediaPlayer();
            mCurrentPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            mCurrentPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    mCurrentPlayer.start();
                }
            });
            mCurrentPlayer.prepareAsync();
            createNextMediaPlayerRaw();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createNextMediaPlayerRaw() {
        AssetFileDescriptor afd = mContext.getResources().openRawResourceFd(mResId);
        mNextPlayer = new MediaPlayer();
        try {
            mNextPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            mNextPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mNextPlayer.seekTo(0);
                    mCurrentPlayer.setNextMediaPlayer(mNextPlayer);
                    mCurrentPlayer.setOnCompletionListener(onCompletionListener);
                }
            });
            mNextPlayer.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Creating instance of the player with given context
     * and internal memory/SD path resource
     *
     * @param context - context
     * @param path    - internal memory/SD path to sound resource
     * @return new instance
     */
    public static PerfectMediaPlayer create(Context context, String path) {
           return new PerfectMediaPlayer(context, path);
    }

    private PerfectMediaPlayer(Context context, String path) {
        mContext = context;
        mPath = path;
        try {
            mCurrentPlayer = new MediaPlayer();
            setVolume(5,5);
            FileInputStream fis = new FileInputStream(mPath);
            mCurrentPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mCurrentPlayer.setDataSource(fis.getFD());
            mCurrentPlayer.setLooping(false);
//            mCurrentPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//                @Override
//                public void onPrepared(MediaPlayer mediaPlayer) {
//                    mCurrentPlayer.start();
//                }
//            });

//            mCurrentPlayer.prepareAsync();
  //          createNextMediaPlayerPath();

            mCurrentPlayer.prepare();
            mCurrentPlayer.start();
        } catch (IOException e) {
            Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    private void createNextMediaPlayerPath() {
        mNextPlayer = new MediaPlayer();
        try {
            mNextPlayer.setDataSource(mPath);
            mNextPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mNextPlayer.seekTo(0);
                    mCurrentPlayer.setNextMediaPlayer(mNextPlayer);
                    mCurrentPlayer.setOnCompletionListener(onCompletionListener);
                }
            });
            mNextPlayer.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private final MediaPlayer.OnCompletionListener onCompletionListener =
            new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    mCurrentPlayer = mNextPlayer;
                    createNextMediaPlayerRaw();
                    mediaPlayer.release();
                }
            };


    public boolean isPlaying() throws IllegalStateException {
        if (mCurrentPlayer != null) {
            return mCurrentPlayer.isPlaying();
        } else {
            return false;
        }
    }

    public void setVolume(float leftVolume, float rightVolume) {

        final float volume = (float) (1 - (Math.log(MAX_VOLUME - leftVolume*100) / Math.log(MAX_VOLUME)));
        mCurrentPlayer.setVolume(volume, volume);


    }

    public void start() throws IllegalStateException {
        if (mCurrentPlayer != null) {
            Log.d(TAG, "start()");
            mCurrentPlayer.start();

        } else {
            Log.d(TAG, "start() | mCurrentPlayer is NULL");
        }

    }

    public void stop() throws IllegalStateException {

        if (mCurrentPlayer != null && mCurrentPlayer.isPlaying()) {
            Log.d(TAG, "stop()");

            mCurrentPlayer.stop();
        } else {
            Log.d(TAG, "stop() | mCurrentPlayer " +
                    "is NULL or not playing");
        }

    }

    public void pause() throws IllegalStateException {
        if (mCurrentPlayer != null && mCurrentPlayer.isPlaying()) {
            Log.d(TAG, "pause()");
            mCurrentPlayer.pause();
        } else {
            Log.d(TAG, "pause() | mCurrentPlayer " +
                    "is NULL or not playing");
        }

    }

    public void setWakeMode(Context c, int mode) {
        if (mCurrentPlayer != null) {
            mCurrentPlayer.setWakeMode(c, mode);
            Log.d(TAG, "setWakeMode() | ");
        } else {
            Log.d(TAG, "setWakeMode() | " +
                    "mCurrentPlayer is NULL");
        }
    }

    public void setAudioStreamType(int audioStreamType) {
        if (mCurrentPlayer != null) {
            mCurrentPlayer.setAudioStreamType(audioStreamType);
        } else {
            Log.d(TAG, "setAudioStreamType() | " +
                    "mCurrentPlayer is NULL");
        }
    }

    public void release() {
        Log.d(TAG, "release()");
        if (mCurrentPlayer != null) {
            mCurrentPlayer.release();
        }
        if (mNextPlayer != null)
            mNextPlayer.release();
    }
    public void reset() {
        if (mCurrentPlayer != null) {
            Log.d(TAG, "reset()");
            mCurrentPlayer.reset();
        } else {
            Log.d(TAG, "reset() | " +
                    "mCurrentPlayer is NULL");
        }

    }

}
