package com.example.firidgeapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.firidgeapp.add.add2;
import com.example.firidgeapp.add.add3;
import com.example.firidgeapp.add.add4;
import com.example.firidgeapp.add.add5;
import com.example.firidgeapp.add.add6;
import com.example.firidgeapp.add.add7;
import com.example.firidgeapp.add.add8;
import com.example.firidgeapp.add.add9;
import com.example.firidgeapp.fragment.MyAdapter;

import me.relex.circleindicator.CircleIndicator3;

public class MainActivity2 extends AppCompatActivity {
    private ViewPager2 mPager;
    private FragmentStateAdapter pagerAdapter;
    private int num_page = 4;
    private CircleIndicator3 mIndicator;
    // 레이아웃 요소
    LinearLayout llTitle;
    LinearLayout llfood1, llfood2, llfood3, llfood4, llfood5, llfood6, llfood7, llfood8;
    TextView foodId1, foodId2,foodId3,foodId4,foodId5,foodId6,foodId7,foodId8;
    TextView  foodId11,foodId12,foodId13,foodId14,foodId15,foodId16 ,tvTitle2  ;

    // 애니메이션
    Animation aniLlTitle,brain;
    Animation anillfood1, anillfood2, anillfood3, anillfood4, anillfood5, anillfood6, anillfood7, anillfood8;
    Animation anillfood9;
    Animation aniTexfood1;

    Button btnfood1, btnfood2, btnfood3, btnfood4, btnfood5, btnfood6, btnfood7, btnfood8,btnAbout2,btn,btnMatchSide;
    // 사이드 메뉴
    private DrawerLayout drawerLayout;
    private View drawerView;
    Button btnAbout,btnYoutubeSide,btnBookSide,btnemail;
    ImageView btnClose;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
//사이드바 설정 하기
        findViewById(R.id.btnAbout).setOnClickListener(mClick2);
        findViewById(R.id.btnAbout2).setOnClickListener(mClick2);
        btnAbout = (Button) findViewById(R.id.btnAbout);
        drawerLayout = (DrawerLayout) findViewById(R.id.sidemenu_layout);
        drawerView = (View) findViewById(R.id.drawer);
        drawerLayout.setDrawerListener(drawerListener);
        drawerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        btnClose = (ImageView) findViewById(R.id.btnClose);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawers();
            }
        });
        btnYoutubeSide = (Button) findViewById(R.id.btnYoutubeSide);
        btnYoutubeSide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent act2 = new Intent(MainActivity2.this, cardview.class);
                startActivity(act2);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout); // 화면 전환 애니메이션
                finish();
            }
        });
        btnBookSide = (Button) findViewById(R.id.btnBookSide);
        btnBookSide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent act2 = new Intent(MainActivity2.this, MartActivity.class);
                String mart = "이마트";
                act2.putExtra("이마트", mart);
                startActivity(act2);
                overridePendingTransition(R.anim.scale, R.anim.scale); // 화면 전환 애니메이션
                finish();
            }
        });
        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent act2 = new Intent(MainActivity2.this, schedulecheck.class);
                startActivity(act2);
                overridePendingTransition(R.anim.scale, R.anim.scale); // 화면 전환 애니메이션
                finish();
            }
        });

        btnMatchSide = (Button) findViewById(R.id.btnMatchSide);
        btnMatchSide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent act2 = new Intent(MainActivity2.this, shopping.class);
                startActivity(act2);
                overridePendingTransition(R.anim.scale, R.anim.scale); // 화면 전환 애니메이션
                finish();
            }
        });
        /* 문의 사항 메일 넘어가기*/
        btnemail = (Button) findViewById(R.id.btnemail);
        btnemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent email = new Intent(Intent.ACTION_SEND);
                email.setType("plain/text");
                String[] address = {"cms@cms.cms"};
                email.putExtra(Intent.EXTRA_EMAIL, address);
                email.putExtra(Intent.EXTRA_SUBJECT, "냉장고를 부탁해 문의사항 입니다.");
                email.putExtra(Intent.EXTRA_TEXT, "문의사항을 자세하게 적어주세요. 항상 불편함 없이 최선을 다하겠습니다. 감사합니다.");
                startActivity(email);
            }
        });

        //ViewPager2
        mPager = findViewById(R.id.viewpager);

        //Adapter
        pagerAdapter = new MyAdapter(this, num_page);
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

        // 레이아웃
        llTitle = (LinearLayout) findViewById(R.id.llTitle);
        llfood1 = (LinearLayout) findViewById(R.id.llfood1);
        llfood2 = (LinearLayout) findViewById(R.id.llfood2);
        llfood3 = (LinearLayout) findViewById(R.id.llfood3);
        llfood4 = (LinearLayout) findViewById(R.id.llfood4);
        llfood5 = (LinearLayout) findViewById(R.id.llfood5);
        llfood6 = (LinearLayout) findViewById(R.id.llfood6);
        llfood7 = (LinearLayout) findViewById(R.id.llfood7);
        llfood8 = (LinearLayout) findViewById(R.id.llfood8);
        //버튼
        btnfood1 = (Button) findViewById(R.id.btnfood1);
        btnfood2 = (Button) findViewById(R.id.btnfood2);
        btnfood3 = (Button) findViewById(R.id.btnfood3);
        btnfood4 = (Button) findViewById(R.id.btnfood4);
        btnfood5 = (Button) findViewById(R.id.btnfood5);
        btnfood6 = (Button) findViewById(R.id.btnfood6);
        btnfood7 = (Button) findViewById(R.id.btnfood7);
        btnfood8 = (Button) findViewById(R.id.btnfood8);
        btnAbout2 = (Button) findViewById(R.id.btnAbout2);
        btnAbout = (Button) findViewById(R.id.btnAbout);

        btnfood1.setOnClickListener(mClickListener);
        btnfood2.setOnClickListener(mClickListener);
        btnfood3.setOnClickListener(mClickListener);
        btnfood4.setOnClickListener(mClickListener);
        btnfood5.setOnClickListener(mClickListener);
        btnfood6.setOnClickListener(mClickListener);
        btnfood7.setOnClickListener(mClickListener);
        btnfood8.setOnClickListener(mClickListener);
        
        // 레이아웃 애니메이션
        aniLlTitle = AnimationUtils.loadAnimation(MainActivity2.this, R.anim.descend);
        anillfood1 = AnimationUtils.loadAnimation(MainActivity2.this, R.anim.ascend_fast);
        anillfood2 = AnimationUtils.loadAnimation(MainActivity2.this, R.anim.ascend_fast);
        anillfood3 = AnimationUtils.loadAnimation(MainActivity2.this, R.anim.ascend_fast);
        anillfood4 = AnimationUtils.loadAnimation(MainActivity2.this, R.anim.ascend_fast);
        anillfood5 = AnimationUtils.loadAnimation(MainActivity2.this, R.anim.ascend_fast);
        anillfood6 = AnimationUtils.loadAnimation(MainActivity2.this, R.anim.ascend_fast);
        anillfood7 = AnimationUtils.loadAnimation(MainActivity2.this, R.anim.ascend_fast);
        anillfood8 = AnimationUtils.loadAnimation(MainActivity2.this, R.anim.ascend_fast);
        anillfood9 = AnimationUtils.loadAnimation(MainActivity2.this, R.anim.ascend_fast);

        aniLlTitle.setStartOffset(1000);
        anillfood1.setStartOffset(1300);
        anillfood2.setStartOffset(1400);
        anillfood3.setStartOffset(1500);
        anillfood4.setStartOffset(1600);
        anillfood5.setStartOffset(1700);
        anillfood6.setStartOffset(1800);
        anillfood7.setStartOffset(1900);
        anillfood8.setStartOffset(2000);
        anillfood9.setStartOffset(1000);
        llTitle.startAnimation(aniLlTitle);
        llfood1.startAnimation(anillfood1);
        llfood2.startAnimation(anillfood2);
        llfood3.startAnimation(anillfood3);
        llfood4.startAnimation(anillfood4);
        llfood5.startAnimation(anillfood5);
        llfood6.startAnimation(anillfood6);
        llfood7.startAnimation(anillfood7);
        llfood8.startAnimation(anillfood8);
        btnAbout.startAnimation(anillfood9);
        btnAbout2.startAnimation(anillfood9);
        foodId1 = (TextView) findViewById(R.id.foodId1);
        foodId2 = (TextView) findViewById(R.id.foodId2);
        foodId3 = (TextView) findViewById(R.id.foodId3);
        foodId4 = (TextView) findViewById(R.id.foodId4);
        foodId5 = (TextView) findViewById(R.id.foodId5);
        foodId6 = (TextView) findViewById(R.id.foodId6);
        foodId7 = (TextView) findViewById(R.id.foodId7);
        foodId8 = (TextView) findViewById(R.id.foodId8);
        brain = AnimationUtils.loadAnimation(MainActivity2.this, R.anim.brain);
        tvTitle2 = (TextView) findViewById(R.id.tvTitle2);
        tvTitle2.startAnimation(brain);
        aniTexfood1 = AnimationUtils.loadAnimation(MainActivity2.this, R.anim.fadein);
        aniTexfood1.setStartOffset(1400);
        foodId1.startAnimation(aniTexfood1);
        foodId2.startAnimation(aniTexfood1);
        foodId3.startAnimation(aniTexfood1);
        foodId4.startAnimation(aniTexfood1);
        foodId5.startAnimation(aniTexfood1);
        foodId6.startAnimation(aniTexfood1);
        foodId7.startAnimation(aniTexfood1);
        foodId8.startAnimation(aniTexfood1);
    }
    View.OnClickListener mClick2 = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnAbout:
                    drawerLayout.openDrawer(drawerView);
                    break;
                case R.id.btnAbout2:
                    Intent act2 = new Intent(MainActivity2.this, MainActivity15.class);
                    startActivity(act2);
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout); // 화면 전환 애니메이션
                    finish();
                    break;
            }
        }
    };
    View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnfood1:
                    Mai();
                    break;
                case R.id.btnfood2:
                    Mai2();
                    break;
                case R.id.btnfood3:
                    Mai3();
                    break;
                case R.id.btnfood4:
                    Mai4();
                    break;
                case R.id.btnfood5:
                    Mai5();
                    break;
                case R.id.btnfood6:
                    Mai6();
                    break;
                case R.id.btnfood7:
                    Mai7();
                    break;
                case R.id.btnfood8:
                    Mai8();
                    break;
            }
        }
    };
    public void Mai() {
        Intent food1 = new Intent(MainActivity2.this, add2.class);
        startActivity(food1);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        finish();
    }
    public void Mai2() {
        Intent food2 = new Intent(MainActivity2.this, add3.class);
        startActivity(food2);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        finish();
    }
    public void Mai3() {
        Intent food3 = new Intent(MainActivity2.this, add4.class);
        startActivity(food3);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        finish();
    }
    public void Mai4() {
        Intent food4 = new Intent(MainActivity2.this, add5.class);
        startActivity(food4);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        finish();
    }
    public void Mai5() {
        Intent food5 = new Intent(MainActivity2.this, add6.class);
        startActivity(food5);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        finish();
    }
    public void Mai6() {
        Intent food6 = new Intent(MainActivity2.this, add7.class);
        startActivity(food6);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        finish();
    }public void Mai7() {
        Intent food7 = new Intent(MainActivity2.this, add8.class);
        startActivity(food7);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        finish();
    }public void Mai8() {
        Intent food8 = new Intent(MainActivity2.this, add9.class);
        startActivity(food8);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        finish();
    }




    // 뒤로가기 버튼 설정
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intentBack = new Intent(MainActivity2.this, MainActivity.class);
        startActivity(intentBack);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout); // 화면 전환 애니메이션
        finish();
    }
    // 사이드 메뉴
    DrawerLayout.DrawerListener drawerListener = new DrawerLayout.DrawerListener() {
        @Override
        public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

        }

        @Override
        public void onDrawerOpened(@NonNull View drawerView) {

        }

        @Override
        public void onDrawerClosed(@NonNull View drawerView) {

        }

        @Override
        public void onDrawerStateChanged(int newState) {

        }
    };
}