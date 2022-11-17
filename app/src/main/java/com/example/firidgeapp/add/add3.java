package com.example.firidgeapp.add;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firidgeapp.AddProduct;
import com.example.firidgeapp.MainActivity2;
import com.example.firidgeapp.MainAdapter;
import com.example.firidgeapp.MainData;
import com.example.firidgeapp.R;
import com.example.firidgeapp.RoomDB;

import java.util.ArrayList;
import java.util.List;

public class add3 extends AppCompatActivity {
    Button button,button2;
    RecyclerView recyclerView;
    List<MainData> dataList = new ArrayList<>();
    RoomDB database;
    MainAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main5);
        button = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button.setOnClickListener(mClickListener);

        recyclerView = findViewById(R.id.recycler_view);

        database = RoomDB.getInstance(getApplication());

        dataList.addAll(database.mainDao().getAll());

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new MainAdapter(add3.this, dataList);

        recyclerView.setAdapter(adapter);
        button2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intentBack = new Intent(add3.this, add3.class);
                startActivity(intentBack);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout); // 화면 전환 애니메이션
                finish();
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
        Intent intentIntj = new Intent(add3.this, AddProduct.class);
        startActivity(intentIntj);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        finish();
    }
    // 뒤로가기 버튼 설정
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intentBack = new Intent(add3.this, MainActivity2.class);
        startActivity(intentBack);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout); // 화면 전환 애니메이션
        finish();
    }
}
