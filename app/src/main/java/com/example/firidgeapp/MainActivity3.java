package com.example.firidgeapp;

import android.app.Dialog;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.firidgeapp.shake.ShakeDetector;

public class MainActivity3 extends AppCompatActivity {
    // 버튼
    Button btn1, btn2,btn3,btn4;
    // 애니메이션
    Animation aniTouch, aniLlTitle, aniLlTitle2, aniLlTitle3,aniLlTitle4,brain;
    LinearLayout llTitle1, llTitle2,llTitle3,llTitle4;
    TextView tvTitle2;
    // 뒤로가기 버튼 관리 (2번 누르면 앱 종료)
    private BackHandler backHandler = new BackHandler(this);
    // 사이드 메뉴
    //쉐이크 기능
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private ShakeDetector mShakeDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        // 터치 애니메이션
        aniTouch = AnimationUtils.loadAnimation(MainActivity3.this, R.anim.scale);

        // 레이아웃 애니메이션
        aniLlTitle = AnimationUtils.loadAnimation(MainActivity3.this, R.anim.descend);
        aniLlTitle2 = AnimationUtils.loadAnimation(MainActivity3.this, R.anim.descend);
        aniLlTitle3 = AnimationUtils.loadAnimation(MainActivity3.this, R.anim.descend);
        aniLlTitle4 = AnimationUtils.loadAnimation(MainActivity3.this, R.anim.descend);
//반짝반짝 애니메이션
        brain = AnimationUtils.loadAnimation(MainActivity3.this, R.anim.brain);
        tvTitle2 = (TextView) findViewById(R.id.tvTitle2);
        tvTitle2.startAnimation(brain);
        // 레이아웃
        llTitle1 = (LinearLayout) findViewById(R.id.llTitle1);
        llTitle2 = (LinearLayout) findViewById(R.id.llTitle2);
        llTitle3 = (LinearLayout) findViewById(R.id.llTitle3);
        llTitle4 = (LinearLayout) findViewById(R.id.llTitle4);
        aniLlTitle.setStartOffset(400);
        aniLlTitle3.setStartOffset(700);
        aniLlTitle2.setStartOffset(1000);
        aniLlTitle4.setStartOffset(1300);
        llTitle1.startAnimation(aniLlTitle);
        llTitle2.startAnimation(aniLlTitle2);
        llTitle3.startAnimation(aniLlTitle3);
        llTitle4.startAnimation(aniLlTitle4);

        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);

        btn1.setOnClickListener(mClickListener);
        btn2.setOnClickListener(mClickListener);
        btn3.setOnClickListener(mClickListener);
        btn4.setOnClickListener(mClickListener);

    }


    // Main Layout
    View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn1:
                    Intent intent = new Intent(MainActivity3.this, shopping.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout); // 화면 전환 애니메이션
                    finish();
                    break;
                case R.id.btn2:
                    Intent intent2 = new Intent(MainActivity3.this, MartActivity.class);
                    String mart = "이마트";
                    intent2.putExtra("이마트", mart);
                    startActivity(intent2);
                    overridePendingTransition(R.anim.scale, R.anim.scale); // 화면 전환 애니메이션
                    finish();
                    break;
                case R.id.btn3:
                    Intent intent3 = new Intent(MainActivity3.this, schedulecheck.class);
                    startActivity(intent3);
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout); // 화면 전환 애니메이션
                    finish();
                    break;
                case R.id.btn4:
                    Intent intent4 = new Intent(MainActivity3.this, cardview.class);
                    startActivity(intent4);
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout); // 화면 전환 애니메이션
                    finish();
                    break;
            }
        }
    };

    // 프로필 다이얼로그 (닉네임, MBTI 설정)
    public void dialogProfile() {
        Dialog dialog = new Dialog(this, R.style.DialogStyle);
        dialog.setContentView(R.layout.main_dialog);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.dl_background);
        dialog.setCancelable(false);

        // 닫기 버튼
        ImageView btnClose = dialog.findViewById(R.id.btn_close);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        // "네" 버튼
        Button btnYes = dialog.findViewById(R.id.btn_yes);
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent intentProfile = new Intent(MainActivity3.this, MainActivity2.class);
                startActivity(intentProfile);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout); // 화면 전환 애니메이션
                finish();
            }
        });

        // "아니오" 버튼
        Button btnNo = dialog.findViewById(R.id.btn_no);
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    // 뒤로가기 버튼 설정
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intentBack = new Intent(MainActivity3.this, MainActivity.class);
        startActivity(intentBack);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout); // 화면 전환 애니메이션
        finish();
    }

}
