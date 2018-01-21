package com.littlefox.bless.dancingdecchi;

import android.app.IntentService;
import android.content.Intent;
import android.os.Build;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

public class Lording extends GameActivity {

//    Thread trd;
//    Handler handler;
//
//    TextView textView = (TextView) findViewById(R.id.textViewLording);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lording);

        if (Build.VERSION.SDK_INT >= 19) {
            Window window = getWindow();
            View view = window.getDecorView();
            view.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY |
                            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                            View.SYSTEM_UI_FLAG_FULLSCREEN);
        }

        Handler hdl = new Handler();
        // 第２引数で切り替わる秒数(ミリ秒)を指定、今回は2秒
        hdl.postDelayed(new splashHandler(), 3000);
    }

    class splashHandler implements Runnable {
        public void run() {
            Intent intent = new Intent(Lording.this, Main.class);
            startActivity(intent);
            Lording.this.finish();
        }
    }
}
