package com.example.firidgeapp;

import android.app.Dialog;
import android.content.Context;
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

import com.example.firidgeapp.shake.CreateQR;
import com.example.firidgeapp.shake.ShakeDetector;

public class MainActivity extends AppCompatActivity {
    // 버튼
    Button btn1, btn2;
    // 애니메이션
    Animation aniTouch, aniLlTitle, aniLlTitle2, brain;
    LinearLayout llTitle1, llTitle2;
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
        setContentView(R.layout.activity_main);
        // 쉐이크 기능
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager
                .getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mShakeDetector = new ShakeDetector();
        mShakeDetector.setOnShakeListener(new ShakeDetector.OnShakeListener() {
            @Override
            public void onShake(int count) {
                mShakeDetector.setOnShakeListener(new ShakeDetector.OnShakeListener() {
                    @Override
                    public void onShake(int count) {
                        Intent intent = new Intent(MainActivity.this, CreateQR.class);
                        startActivity(intent);
                    }
                });

            }
        });
        // 터치 애니메이션
        aniTouch = AnimationUtils.loadAnimation(MainActivity.this, R.anim.scale);

        // 레이아웃 애니메이션
        aniLlTitle = AnimationUtils.loadAnimation(MainActivity.this, R.anim.descend);
        aniLlTitle2 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.ascend);
        //반짝반짝 애니메이션
        brain = AnimationUtils.loadAnimation(MainActivity.this, R.anim.brain);

        // 레이아웃
        llTitle1 = (LinearLayout) findViewById(R.id.llTitle1);
        llTitle2 = (LinearLayout) findViewById(R.id.llTitle2);
        tvTitle2 = (TextView) findViewById(R.id.tvTitle2);

        llTitle1.startAnimation(aniLlTitle);
        llTitle2.startAnimation(aniLlTitle2);
        tvTitle2.startAnimation(brain);
        btn1 = (Button) findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogProfile2();
            }
        });
        btn2 = (Button) findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogProfile();
            }
        });

    }

    //쉐이크 기능
    @Override
    public void onResume() {
        super.onResume();
        // Add the following line to register the Session Manager Listener onResume
        mSensorManager.registerListener(mShakeDetector, mAccelerometer, SensorManager.SENSOR_DELAY_UI);
    }
    // background 상황에서도 흔들림을 감지하고 적용할 필요는 없다
    @Override
    public void onPause() {
        // Add the following line to unregister the Sensor Manager onPause
        mSensorManager.unregisterListener(mShakeDetector);
        super.onPause();
    }
    // Main Layout
    View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn1:
                    btn1.startAnimation(aniTouch);
                    dialogProfile2();
                    break;
                case R.id.btn2:
                    btn2.startAnimation(aniTouch);
                    dialogProfile();
                    break;
            }
        }
    };

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
                Intent intentProfile = new Intent(MainActivity.this, MainActivity2.class);
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
    public void dialogProfile2() {
        Dialog dialog = new Dialog(this, R.style.DialogStyle);
        dialog.setContentView(R.layout.main_dialog2);
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
                Intent intentProfile = new Intent(MainActivity.this, MainActivity3.class);
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
        backHandler.onBackPressed();
    }

}
