package com.example.dynews.layoutall;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dynews.R;

public class layout6 extends AppCompatActivity {
    ScaleAnimation scaleAnimation,scaleAnimation2;
    BounceInterpolator bounceInterpolator;//애니메이션이 일어나는 동안의 회수, 속도를 조절하거나 시작과 종료시의 효과를 추가 할 수 있다
    CompoundButton button_favorite;
    ImageButton btnSetting;
    Button btnHomeTitle;
    ScrollView ScrollView;
    Animation aniTouch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout6);
        TextView textTitle2 = (TextView) findViewById(R.id.textTitle2);
        textTitle2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentTest = new Intent(Intent.ACTION_VIEW);
                Uri uri = Uri.parse("https://www.fnnews.com/");
                intentTest.setData(uri);
                startActivity(intentTest);
            }
        });
        scaleAnimation = new ScaleAnimation(0.7f, 1.0f, 0.7f, 1.0f, Animation.RELATIVE_TO_SELF, 0.7f, Animation.RELATIVE_TO_SELF, 0.7f);

        scaleAnimation2 = new ScaleAnimation(1.0f, 0.7f, 0.7f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation2.setDuration(100);


        scaleAnimation.setDuration(500);
        bounceInterpolator = new BounceInterpolator();
        scaleAnimation.setInterpolator(bounceInterpolator);
        ToggleButton alarmToggle = findViewById(R.id.alarmToggle);
        // toggle에 setOnCheckedChangeListener 달아주기
        alarmToggle.setOnCheckedChangeListener(
                //CompundButton.OnCheckedChangedListener을 새로 선언
                new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    // 첫번째 인자는 ToggleButton, 두번째 인자는 on/off에 대한 boolean값
                    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                        String toastMessage;
                        //toggle 버튼이 on된 경우
                        if(isChecked){
                            toastMessage = "구독 하였습니다.";
                            alarmToggle.startAnimation(scaleAnimation2);
                        }else{
                            toastMessage = "구독 취소";
                            alarmToggle.startAnimation(scaleAnimation2);
                        }

                        Toast.makeText(layout6.this,toastMessage, Toast.LENGTH_SHORT).show();
                    }


                }
        );
        button_favorite = findViewById(R.id.button_favorite);

        button_favorite.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                compoundButton.startAnimation(scaleAnimation);
            }
        });
        aniTouch = AnimationUtils.loadAnimation(layout6.this, R.anim.scale);
        findViewById(R.id.ScrollView).setOnClickListener(mClick);
        ScrollView = (ScrollView) findViewById(R.id.ScrollView);
        findViewById(R.id.btnSetting).setOnClickListener(mClick);
        btnSetting = (ImageButton) findViewById(R.id.btnSetting);
        findViewById(R.id.btnHomeTitle).setOnClickListener(mClick);
        btnHomeTitle = (Button) findViewById(R.id.btnHomeTitle);
    }
        View.OnClickListener mClick = new View.OnClickListener() {
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btnHomeTitle:
                        btnHomeTitle.startAnimation(aniTouch);
                        ScrollView.post(new Runnable() {
                            public void run() {
                                ScrollView.fullScroll(ScrollView.FOCUS_UP);
                            }
                        });
                        break;
                    case R.id.btnSetting:
                        btnSetting.startAnimation(aniTouch);
                        finish();
                        break;
                }
            }
        };
    }

