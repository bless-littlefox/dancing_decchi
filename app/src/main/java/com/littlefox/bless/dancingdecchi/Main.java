package com.littlefox.bless.dancingdecchi;

import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

public class Main extends GameActivity {

    ImageView imageView;

    SoundPool soundPoolTap;
    SoundPool soundPoolEnd;
    int seTap;
    int seEnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView)findViewById(R.id.imageDecchi);

        if (Build.VERSION.SDK_INT >= 19) {
            Window window = getWindow();
            View view = window.getDecorView();
            view.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY |
                            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                            View.SYSTEM_UI_FLAG_FULLSCREEN);
        }

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            soundPoolTap = new SoundPool(50, AudioManager.STREAM_MUSIC, 0);
        } else {
            AudioAttributes attr = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .build();
            soundPoolTap = new SoundPool.Builder()
                    .setAudioAttributes(attr)
                    .setMaxStreams(50)
                    .build();
        }
        seTap = soundPoolTap.load(this, R.raw.se_handclap, 1);

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            soundPoolEnd = new SoundPool(50, AudioManager.STREAM_MUSIC, 0);
        } else {
            AudioAttributes attr = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .build();
            soundPoolEnd = new SoundPool.Builder()
                    .setAudioAttributes(attr)
                    .setMaxStreams(50)
                    .build();
        }
        seEnd = soundPoolEnd.load(this, R.raw.se_cheer, 1);

        bgmStart();
    }

    public void leftButton(View v) {
        soundPoolTap.play(seTap,2f , 2f, 0, 0, 1f);
        imageView.setImageResource(R.drawable.quicky_decchi);
        Log.d("DancingDecchi", "leftTap");
    }

    public void jumpButton(View v) {
        soundPoolTap.play(seTap,2f , 2f, 0, 0, 1f);
        imageView.setImageResource(R.drawable.quicky_decchi);
        Log.d("DancingDecchi", "jumpTap");
    }

    public void rightButton(View v) {
        soundPoolTap.play(seTap,2f , 2f, 0, 0, 1f);
        imageView.setImageResource(R.drawable.quicky_decchi);
        Log.d("DancingDecchi", "rightTap");
    }

    @Override
    public void onCompletion(MediaPlayer arg0) {
        NextActivity();
    }

    public void NextActivity() {
        soundPoolEnd.play(seEnd,1f , 1f, 0, 0, 1f);

        Intent intentMain = new Intent(this, Result.class);
        startActivity(intentMain);
        finish();
    }

//    @Override
//    protected void onPause() {
//        super.onPause();
//        soundPoolTap.release();
//        soundPoolEnd.release();
//    }
}
