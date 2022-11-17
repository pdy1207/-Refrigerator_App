package com.example.firidgeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import org.w3c.dom.Text;

public class IntroActivity extends AppCompatActivity {
    TextView textView1,textView2;
    Animation slide;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        textView1 = (TextView) findViewById(R.id.textView1);
        textView2 = (TextView) findViewById(R.id.textView2);

        slide = AnimationUtils.loadAnimation(IntroActivity.this, R.anim.slide_in_right);


        slide.setStartOffset(1000);
        textView1.startAnimation(slide);
        textView2.startAnimation(slide);
        // 이벤트 핸들러를 통해서 몇초 후의 동작을 시킨다.
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(IntroActivity.this, agree.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout); // 화면 전환 애니메이션
                finish();

            }
        }, 3000);
    }
}