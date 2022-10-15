package com.example.dynews;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class IntroActivity extends AppCompatActivity {
    ImageView imageView;
    Animation splash_imageview, splash_textview,splash_out_down,splash_out_top,fadein2,fadein;
    TextView textView,textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);


        imageView = (ImageView) findViewById(R.id.imageView);
        textView = (TextView) findViewById(R.id.textView);
        textView2 = (TextView) findViewById(R.id.textView2);

        splash_imageview = AnimationUtils.loadAnimation(IntroActivity.this, R.anim.splash_imageview);
        splash_out_down = AnimationUtils.loadAnimation(IntroActivity.this, R.anim.splash_out_down);
        splash_out_top = AnimationUtils.loadAnimation(IntroActivity.this, R.anim.splash_out_top);


        fadein = AnimationUtils.loadAnimation(IntroActivity.this, R.anim.descend);
        fadein2 = AnimationUtils.loadAnimation(IntroActivity.this, R.anim.descend);
   /*     imageView.startAnimation(fadein);*/

        splash_textview = AnimationUtils.loadAnimation(IntroActivity.this, R.anim.descend);
        fadein.setStartOffset(2000);
        textView.startAnimation(fadein);
        textView2.startAnimation(fadein2);
        fadein2.setStartOffset(3000);
        // 이벤트 핸들러를 통해서 몇초 후의 동작을 시킨다.
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(IntroActivity.this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout); // 화면 전환 애니메이션
                finish();

            }
        }, 5500);
    }
}