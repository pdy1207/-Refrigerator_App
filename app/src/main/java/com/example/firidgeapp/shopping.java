package com.example.firidgeapp;

import static android.graphics.Color.*;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.firidgeapp.fragment.MyAdapter;
import com.example.firidgeapp.fragment2.MyAdapter2;

import java.util.ArrayList;

import me.relex.circleindicator.CircleIndicator3;

public class shopping extends AppCompatActivity {
    private ViewPager2 mPager;
    private FragmentStateAdapter pagerAdapter;
    private int num_page = 4;
    private CircleIndicator3 mIndicator;
    Animation aniTouch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);
        // 터치 애니메이션
        aniTouch = AnimationUtils.loadAnimation(shopping.this, R.anim.scale);
        //장 볼 리스트
        final ArrayList<String> cklist = new ArrayList<String>();
        ListView cklistview = (ListView) findViewById(R.id.checklistview);
        final ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, cklist);
        cklistview.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        cklistview.setAdapter(adapter2);
        cklistview.setTextDirection(BLACK);
        Button addShop = (Button) findViewById(R.id.addShop);
        final EditText inputShop = (EditText) findViewById(R.id.inputShop);
        addShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cklist.add(inputShop.getText().toString());
                cklistview.setTextDirection(BLACK);
                adapter2.notifyDataSetChanged();

            }

        });
        //ViewPager2
        mPager = findViewById(R.id.viewpager);

        //Adapter
        pagerAdapter = new MyAdapter2(this, num_page);
        mPager.setAdapter(pagerAdapter);
        //Indicator
        mIndicator = findViewById(R.id.indicator);
        mIndicator.setViewPager(mPager);
        mIndicator.createIndicators(num_page, 0);
        //ViewPager Setting
        mPager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        /**
         * 이 부분 조정하여 처음 시작하는 이미지 설정.
         * 2000장 생성하였으니 현재위치 1002로 설정하여
         * 좌 우로 슬라이딩 할 수 있게 함. 거의 무한대로
         */

        mPager.setCurrentItem(1000); //시작 지점
        mPager.setOffscreenPageLimit(4); //최대 이미지 수

        mPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                if (positionOffsetPixels == 0) {
                    mPager.setCurrentItem(position);

                }
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                mIndicator.animatePageSelected(position % num_page);

            }

        });
        cklistview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                cklistview.setTextDirection(BLACK);
                cklist.remove(i);
                adapter2.notifyDataSetChanged();
                return false;
            }
        });

        cklistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent mIntent=new Intent(Intent.ACTION_VIEW, Uri.parse("http://m.emart.ssg.com/search.ssg?query="+cklist.get(i)));
                startActivity(mIntent);
            }
        });
    }
    // 뒤로가기 버튼 설정
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intentBack = new Intent(shopping.this, MainActivity3.class);
        startActivity(intentBack);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout); // 화면 전환 애니메이션
        finish();
    }

}