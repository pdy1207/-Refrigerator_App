package com.example.firidgeapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.firidgeapp.add.add4;

public class CarmeraAdd extends AppCompatActivity
{
    final private static String TAG = "MainActivity";
    Button btn_photo;
    ImageView iv_photo;
    Animation aniTouch;
    final static int TAKE_PICTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carmera_add);
        // 터치 애니메이션
        aniTouch = AnimationUtils.loadAnimation(CarmeraAdd.this, R.anim.scale);
        iv_photo = findViewById(R.id.iv_photo);
        btn_photo = findViewById(R.id.btn_photo);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED &&
                    checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
            {
                Log.e(TAG, "권한 설정 완료");
            }
            else
            {
                Log.e(TAG, "권한 설정 요청");
                ActivityCompat.requestPermissions(CarmeraAdd.this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            }
        }

        // 사진 찍기 버튼을 눌러 카메라 화면으로 이동 및 촬영
        btn_photo.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                switch (v.getId())
                {
                    case R.id.btn_photo:
                        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(cameraIntent, TAKE_PICTURE);
                        break;
                }
            }
        });
    }
    // 뒤로가기 버튼 설정
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intentBack = new Intent(CarmeraAdd.this,add4.class);
        startActivity(intentBack);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout); // 화면 전환 애니메이션
        finish();
    }
    // 카메라 권한 요청
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.d(TAG, "onRequestPermissionsResult");
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED)
        {
            Log.e(TAG, "Permission: " + permissions[0] + "was " + grantResults[0]);
        }
    }

    // 카메라로 촬영한 사진의 썸네일을 가져와 이미지뷰에 띄우기
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent)
    {
        super.onActivityResult(requestCode, resultCode, intent);

        switch (requestCode)
        {
            case TAKE_PICTURE:
                if (resultCode == RESULT_OK && intent.hasExtra("data"))
                {
                    Bitmap bitmap = (Bitmap) intent.getExtras().get("data");
                    if (bitmap != null)
                    {
                        iv_photo.setImageBitmap(bitmap);
                    }

                }
                break;
        }
    }


}