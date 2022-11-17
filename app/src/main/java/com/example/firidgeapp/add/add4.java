package com.example.firidgeapp.add;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firidgeapp.AddProduct;
import com.example.firidgeapp.CarmeraAdd;
import com.example.firidgeapp.MainActivity3;
import com.example.firidgeapp.MultiImageActivity;
import com.example.firidgeapp.cardview;
import com.example.firidgeapp.MainActivity2;
import com.example.firidgeapp.MainAdapter;
import com.example.firidgeapp.MainData;
import com.example.firidgeapp.R;
import com.example.firidgeapp.RoomDB;

import java.util.ArrayList;
import java.util.List;

public class add4 extends AppCompatActivity {
    Button button,button2;
    RecyclerView recyclerView;
    List<MainData> dataList = new ArrayList<>();
    RoomDB database;
    MainAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main6);
        button = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button.setOnClickListener(mClickListener);

        recyclerView = findViewById(R.id.recycler_view);

        database = RoomDB.getInstance(getApplication());

        dataList.addAll(database.mainDao().getAll());

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new MainAdapter(add4.this, dataList);

        recyclerView.setAdapter(adapter);
        button2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dialogProfile();
            }
        });
    }
    View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.button1:
                    AddProduct();
                    break;
            }
        }

    };

    public void AddProduct() {
        Intent intentIntj = new Intent(add4.this, AddProduct.class);
        startActivity(intentIntj);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        finish();
    }
    // 뒤로가기 버튼 설정
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intentBack = new Intent(add4.this, MainActivity2.class);
        startActivity(intentBack);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout); // 화면 전환 애니메이션
        finish();
    }
    // 프로필 다이얼로그 (닉네임, MBTI 설정)
    public void dialogProfile() {
        Dialog dialog = new Dialog(this, R.style.DialogStyle);
        dialog.setContentView(R.layout.main_dialog3);
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
                Intent intentProfile = new Intent(add4.this, CarmeraAdd.class);
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
                Intent intentProfile = new Intent(add4.this, MultiImageActivity.class);
                startActivity(intentProfile);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout); // 화면 전환 애니메이션
                finish();
            }
        });

        dialog.show();
    }

}
