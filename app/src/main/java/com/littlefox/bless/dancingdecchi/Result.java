package com.littlefox.bless.dancingdecchi;

import android.content.Context;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;

public class Result extends BgmActivity {
    static protected MediaPlayer mp;
    static protected AudioManager am;

    SoundPool soundPool;
    int seSelect;
    FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        frameLayout = (FrameLayout) findViewById(R.id.flameLayout);

        if (Build.VERSION.SDK_INT >= 19) {
            Window window = getWindow();
            View view = window.getDecorView();
            view.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY |
                            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                            View.SYSTEM_UI_FLAG_FULLSCREEN);
        }

        //MediaPlayerインスタンスの生成
        if (mp == null) {
            Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.bgm_after);

            mp = MediaPlayer.create(this, uri);
            mp.setLooping(true);
        }

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            soundPool = new SoundPool(50, AudioManager.STREAM_MUSIC, 0);
        } else {
            AudioAttributes attr = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .build();
            soundPool = new SoundPool.Builder()
                    .setAudioAttributes(attr)
                    .setMaxStreams(50)
                    .build();
        }
        seSelect = soundPool.load(this, R.raw.se_select1, 1);

        if (am == null) {
            am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        }
        bgmStart();
    }

    protected void bgmStart() {
        if (!mp.isPlaying()) {
            mp.start();
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

    public void TapOk(View v) {
        if (frameLayout != null) {
            frameLayout.setVisibility(View.VISIBLE);
        }
    }

    public void OnceMore(View v) {
        soundPool.play(seSelect,1f , 1f, 0, 0, 1f);
        soundPool.release();
        bgmStop();
        Intent intentMain = new Intent(this, Lording.class);
        startActivity(intentMain);
        finish();
    }

    public void ModeSelect(View v) {
        soundPool.play(seSelect,1f , 1f, 0, 0, 1f);
        soundPool.release();
        bgmStop();
        Intent intentMain = new Intent(this, ModeSelect.class);
        startActivity(intentMain);
        finish();
    }

    public void End(View v) {
        soundPool.play(seSelect,1f , 1f, 0, 0, 1f);
        soundPool.release();
        bgmStop();
        this.finish();
        this.moveTaskToBack(true);
    }

//    @Override
//    protected void onPause() {
//        super.onPause();
//        soundPool.release();
//    }
}
