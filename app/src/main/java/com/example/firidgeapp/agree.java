package com.example.firidgeapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.firidgeapp.R;
import com.squareup.picasso.Picasso;

import java.io.File;

public class agree extends AppCompatActivity {
    Animation aniTouch;
    //접속정보와 태그명 지정
    private String TAG = "memberJoinTest";

    private TextView singagree;

    //뷰 객체 생성
    EditText idEt, pwEt, repwEt;//사용자 ID ,비밀번호, 비밀번호 확인
    Button signBtn; //회원가입 버튼
    CheckBox agreeCb; //약관 동의 여부 체크 박스
    ImageView profileIv; //프로필 이미지

    //변수 객체 생성
    String name, password, repassword; //아이디, 암호, 암호재입력 String

    private static final int PICK_FORM_ALBUM = 1;
    private File tempFile;

    SharedPreferences memberPref, profilePref;
    SharedPreferences.Editor editor;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agree);
// 터치 애니메이션
        aniTouch = AnimationUtils.loadAnimation(agree.this, R.anim.scale);
        singagree = (TextView) findViewById(R.id.signagree); //동의약관
        singagree.setMovementMethod(new ScrollingMovementMethod()); //동의약관 스크롤되도록


        signBtn = (Button) findViewById(R.id.signBtn); //회원가입 버튼
        signBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.signBtn:
                        signBtn.startAnimation(aniTouch);
                        Intent intentProfile = new Intent(agree.this, MainActivity.class);
                        startActivity(intentProfile);
                        overridePendingTransition(R.anim.fadein, R.anim.fadeout); // 화면 전환 애니메이션
                        finish();
                        break;
                }

        }


        });
    }

}