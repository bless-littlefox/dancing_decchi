package com.littlefox.bless.dancingdecchi;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by littl on 2018/01/20.
 */

public class GameActivity extends Activity implements MediaPlayer.OnCompletionListener {
    static protected MediaPlayer mp;
    static protected AudioManager am;

    @Override
    protected void onCreate(Bundle state) {
        super.onCreate(state);

        //MediaPlayerインスタンスの生成
        if (mp == null) {
            Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.gogowats);

            mp = MediaPlayer.create(this, uri);
            mp.setLooping(false);
        }

        if (am == null) {
            am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        }
    }

    protected void bgmStart() {
        if (!mp.isPlaying()) {
            mp.start();
            mp.setOnCompletionListener(this);
        }
    }

    protected void bgmStop() {
        if (mp.isPlaying()) {
            mp.stop();
            mp.release();
            mp = null;
        }
    }

    protected int getMaxVolume() {
        int max = am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        return max;
    }

    protected int getNowVolume() {
        int Vol = am.getStreamVolume(AudioManager.STREAM_MUSIC);
        return Vol;
    }

    protected void setVolume(int vol) {
        am.setStreamVolume(AudioManager.STREAM_MUSIC, vol, 0);
    }

    public void onCompletion(MediaPlayer arg0) {
    }

}
