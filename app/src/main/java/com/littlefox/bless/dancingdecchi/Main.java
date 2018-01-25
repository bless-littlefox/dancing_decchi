package com.littlefox.bless.dancingdecchi;

import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

public class Main extends GameActivity {

    ImageView imageView;

    SoundPool soundPoolTap;
    SoundPool soundPoolEnd;
//    int seTap;
    int seEnd;

    Button leftButton;
    Button jumpButton;
    Button rightButton;

    private TickHandler tHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView)findViewById(R.id.imageDecchi);

//        if (Build.VERSION.SDK_INT >= 19) {
//            Window window = getWindow();
//            View view = window.getDecorView();
//            view.setSystemUiVisibility(
//                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY |
//                            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
//                            View.SYSTEM_UI_FLAG_FULLSCREEN);
//        }
//
//        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
//            soundPoolTap = new SoundPool(50, AudioManager.STREAM_MUSIC, 0);
//        } else {
//            AudioAttributes attr = new AudioAttributes.Builder()
//                    .setUsage(AudioAttributes.USAGE_MEDIA)
//                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
//                    .build();
//            soundPoolTap = new SoundPool.Builder()
//                    .setAudioAttributes(attr)
//                    .setMaxStreams(50)
//                    .build();
//        }
//        seTap = soundPoolTap.load(this, R.raw.se_handclap, 1);

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

        tHandler = new TickHandler();
        tHandler.sleep(0);

        leftButton = (Button)findViewById(R.id.leftButton);
        jumpButton = (Button)findViewById(R.id.jumpButton);
        rightButton = (Button)findViewById(R.id.rightButton);

        leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                soundPoolTap.play(seTap,2f , 2f, 0, 0, 1f);
                Log.d("DD",String.valueOf(getTiming()));
                imageView.setImageResource(R.drawable.decchi_left);
                Log.d("DancingDecchi", "leftTap");
                tHandler.sleep(500);
            }
        });

        jumpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                soundPoolTap.play(seTap,2f , 2f, 0, 0, 1f);
                Log.d("DD",String.valueOf(getTiming()));
                imageView.setImageResource(R.drawable.decchi_jump);
                Log.d("DancingDecchi", "jumpTap");
                tHandler.sleep(500);
            }
        });

        rightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                soundPoolTap.play(seTap,2f , 2f, 0, 0, 1f);
                Log.d("DD",String.valueOf(getTiming()));
                imageView.setImageResource(R.drawable.decchi_right);
                Log.d("DancingDecchi", "rightTap");
                tHandler.sleep(500);
            }
        });

        bgmStart();
    }

    //定期処理 Handlerクラス
    public class TickHandler extends Handler{

        boolean counter;
        //メッセージを受信することで起動
        @Override
        public void handleMessage(Message msg) {
            //描画
            if (counter) {
                imageView.setImageResource(R.drawable.decchi_wait1);
            } else {
                imageView.setImageResource(R.drawable.decchi_wait2);
            }

            if(tHandler !=null){
                //sleepメソッドの呼び出し(定期的にメッセージを送るため)
                tHandler.sleep(750);
            }

            super.handleMessage(msg);
            counter = !counter;
        }
        public void sleep(long delayMillis){
            //使用済メッセージを削除
            removeMessages(0);
            //新しいメッセージを取得して指定時間後にメッセージを送る
            sendMessageDelayed(obtainMessage(0), delayMillis);

        }
    }

    public void leftButton(View v) {
        //getTiming();
        Log.d("DD",String.valueOf(getTiming()));
    }

    public void jumpButton(View v) {
        //getTiming();
        Log.d("DD",String.valueOf(getTiming()));
    }

    public void rightButton(View v) {
        //getTiming();
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

    @Override
    protected void onPause() {
        super.onPause();
        //soundPoolTap.release();
        soundPoolEnd.release();
        tHandler=null;
    }
}
