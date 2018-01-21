package com.littlefox.bless.dancingdecchi;

import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Build;
import android.view.View;
import android.view.Window;

public class Title extends BgmActivity {

    SoundPool soundPool;
    int seSelect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title);

        if (Build.VERSION.SDK_INT >= 19) {
            Window window = getWindow();
            View view = window.getDecorView();
            view.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY |
                            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                            View.SYSTEM_UI_FLAG_FULLSCREEN);
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

        bgmStart();
    }

    public void NextActivity(View v) {
        soundPool.play(seSelect,1f , 1f, 0, 0, 1f);
        Intent intentMain = new Intent(this, ModeSelect.class);
        startActivity(intentMain);
        finish();
    }

//    @Override
//    protected void onPause() {
//        super.onPause();
//        soundPool.release();
//    }
}
