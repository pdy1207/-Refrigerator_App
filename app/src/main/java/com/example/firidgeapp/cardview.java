package com.example.firidgeapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class cardview extends AppCompatActivity {
    Animation aniLlMate,aniLlMate2,aniLlMate3,aniLlMate4,aniLlMate5
            ,aniLlMate6,aniLlMate7,aniLlMate8; //레이아웃 애니메이션 정의
    Animation anilCard,anilCard2,anilCard3,anilCard4,anilCard5,
            anilCard6,anilCard7,anilCard8; //카드뷰 애니메이션 정의

    CardView card_view,card_view2,card_view3,card_view4,card_view5,
            card_view6,card_view7,card_view8;
    LinearLayout llMate,llMate2,llMate3,llMate4,llMate5,
            llMate6,llMate7,llMate8;
    ImageButton ImageView_title,ImageView_title2,ImageView_title3,ImageView_title4,ImageView_title5,ImageView_title6,ImageView_title7,ImageView_title8;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cardview);

        //레이아웃 정의
        llMate = (LinearLayout) findViewById(R.id.llMate);
        llMate2 = (LinearLayout) findViewById(R.id.llMate2);
        llMate3 = (LinearLayout) findViewById(R.id.llMate3);
        llMate4 = (LinearLayout) findViewById(R.id.llMate4);
        llMate5 = (LinearLayout) findViewById(R.id.llMate5);
        llMate6 = (LinearLayout) findViewById(R.id.llMate6);
        llMate7 = (LinearLayout) findViewById(R.id.llMate7);
        llMate8 = (LinearLayout) findViewById(R.id.llMate8);
        //카드뷰 정의
        findViewById(R.id.card_view).setOnClickListener(mClick);
        findViewById(R.id.card_view2).setOnClickListener(mClick);
        findViewById(R.id.card_view3).setOnClickListener(mClick);
        findViewById(R.id.card_view4).setOnClickListener(mClick);
        findViewById(R.id.card_view5).setOnClickListener(mClick);
        findViewById(R.id.card_view6).setOnClickListener(mClick);
        findViewById(R.id.card_view7).setOnClickListener(mClick);
        findViewById(R.id.card_view8).setOnClickListener(mClick);

        //카드뷰 애니메이션
        card_view = (CardView) findViewById(R.id.card_view);
        card_view2 = (CardView) findViewById(R.id.card_view2);
        card_view3 = (CardView) findViewById(R.id.card_view3);
        card_view4 = (CardView) findViewById(R.id.card_view4);
        card_view5 = (CardView) findViewById(R.id.card_view5);
        card_view6 = (CardView) findViewById(R.id.card_view6);
        card_view7 = (CardView) findViewById(R.id.card_view7);
        card_view8 = (CardView) findViewById(R.id.card_view8);

        //레이아웃 애니메이션
        aniLlMate = AnimationUtils.loadAnimation(cardview.this, R.anim.fadein);
        aniLlMate.setStartOffset(300);
        llMate.startAnimation(aniLlMate);

        aniLlMate2 = AnimationUtils.loadAnimation(cardview.this, R.anim.fadein);
        aniLlMate2.setStartOffset(600);
        llMate2.startAnimation(aniLlMate2);

        aniLlMate3 = AnimationUtils.loadAnimation(cardview.this, R.anim.fadein);
        aniLlMate3.setStartOffset(900);
        llMate3.startAnimation(aniLlMate3);

        aniLlMate4 = AnimationUtils.loadAnimation(cardview.this, R.anim.fadein);
        aniLlMate4.setStartOffset(1200);
        llMate4.startAnimation(aniLlMate4);

        aniLlMate5 = AnimationUtils.loadAnimation(cardview.this, R.anim.fadein);
        aniLlMate5.setStartOffset(1500);
        llMate5.startAnimation(aniLlMate5);

        aniLlMate6 = AnimationUtils.loadAnimation(cardview.this, R.anim.fadein);
        aniLlMate6.setStartOffset(1800);
        llMate6.startAnimation(aniLlMate6);

        aniLlMate7 = AnimationUtils.loadAnimation(cardview.this, R.anim.fadein);
        aniLlMate7.setStartOffset(2100);
        llMate7.startAnimation(aniLlMate7);

        aniLlMate8 = AnimationUtils.loadAnimation(cardview.this, R.anim.fadein);
        aniLlMate8.setStartOffset(2400);
        llMate8.startAnimation(aniLlMate8);

        //카드뷰 애니메이션
        anilCard = AnimationUtils.loadAnimation(cardview.this, R.anim.fadein);
        anilCard.setStartOffset(300);
        card_view.startAnimation(anilCard);

        anilCard2 = AnimationUtils.loadAnimation(cardview.this, R.anim.fadein);
        anilCard2.setStartOffset(600);
        card_view2.startAnimation(anilCard2);

        anilCard3 = AnimationUtils.loadAnimation(cardview.this, R.anim.fadein);
        anilCard3.setStartOffset(900);
        card_view3.startAnimation(anilCard3);

        anilCard4 = AnimationUtils.loadAnimation(cardview.this, R.anim.fadein);
        anilCard4.setStartOffset(1200);
        card_view4.startAnimation(anilCard4);

        anilCard5 = AnimationUtils.loadAnimation(cardview.this, R.anim.fadein);
        anilCard5.setStartOffset(1500);
        card_view5.startAnimation(anilCard5);

        anilCard6 = AnimationUtils.loadAnimation(cardview.this, R.anim.fadein);
        anilCard6.setStartOffset(1800);
        card_view6.startAnimation(anilCard6);

        anilCard7 = AnimationUtils.loadAnimation(cardview.this, R.anim.fadein);
        anilCard7.setStartOffset(1800);
        card_view7.startAnimation(anilCard7);

        anilCard8 = AnimationUtils.loadAnimation(cardview.this, R.anim.fadein);
        anilCard8.setStartOffset(1800);
        card_view8.startAnimation(anilCard8);

        //유튜브 넘어가기 정의
        findViewById(R.id.ImageView_title).setOnClickListener(mClick);
        ImageView_title = (ImageButton) findViewById(R.id.ImageView_title);
        ImageView_title2 = (ImageButton) findViewById(R.id.ImageView_title2);
        ImageView_title3 = (ImageButton) findViewById(R.id.ImageView_title3);
        ImageView_title4 = (ImageButton) findViewById(R.id.ImageView_title4);
        ImageView_title5 = (ImageButton) findViewById(R.id.ImageView_title5);
        ImageView_title6 = (ImageButton) findViewById(R.id.ImageView_title6);
        ImageView_title7 = (ImageButton) findViewById(R.id.ImageView_title7);
        ImageView_title8 = (ImageButton) findViewById(R.id.ImageView_title8);
        findViewById(R.id.ImageView_title2).setOnClickListener(mClick);
        findViewById(R.id.ImageView_title3).setOnClickListener(mClick);
        findViewById(R.id.ImageView_title4).setOnClickListener(mClick);
        findViewById(R.id.ImageView_title5).setOnClickListener(mClick);
        findViewById(R.id.ImageView_title6).setOnClickListener(mClick);
        findViewById(R.id.ImageView_title7).setOnClickListener(mClick);
        findViewById(R.id.ImageView_title8).setOnClickListener(mClick);

    }
    View.OnClickListener mClick = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.ImageView_title:
                    Intent intentTest = new Intent(Intent.ACTION_VIEW);
                    Uri uri = Uri.parse("https://youtu.be/nHjj-HwIq2s");
                    intentTest.setData(uri);
                    startActivity(intentTest);
                    break;
                case R.id.ImageView_title2:
                    Intent intentTest2 = new Intent(Intent.ACTION_VIEW);
                    Uri uri2 = Uri.parse("https://www.youtube.com/watch?v=AlK2Gl6kHZI");
                    intentTest2.setData(uri2);
                    startActivity(intentTest2);
                    break;
                case R.id.ImageView_title3:
                    Intent intentTest3 = new Intent(Intent.ACTION_VIEW);
                    Uri uri3 = Uri.parse("https://www.youtube.com/watch?v=ZfRXRw2sUTI");
                    intentTest3.setData(uri3);
                    startActivity(intentTest3);
                    break;
                case R.id.ImageView_title4:
                    Intent intentTest4 = new Intent(Intent.ACTION_VIEW);
                    Uri uri4 = Uri.parse("https://www.youtube.com/watch?v=zztV0L8bGRo");
                    intentTest4.setData(uri4);
                    startActivity(intentTest4);
                    break;
                case R.id.ImageView_title5:
                    Intent intentTest5 = new Intent(Intent.ACTION_VIEW);
                    Uri uri5 = Uri.parse("https://www.youtube.com/watch?v=rfEts-BrKkg");
                    intentTest5.setData(uri5);
                    startActivity(intentTest5);
                    break;
                case R.id.ImageView_title6:
                    Intent intentTest6 = new Intent(Intent.ACTION_VIEW);
                    Uri uri6 = Uri.parse("https://www.youtube.com/watch?v=RvDu833RSek");
                    intentTest6.setData(uri6);
                    startActivity(intentTest6);
                    break;
                case R.id.ImageView_title7:
                    Intent intentTest7 = new Intent(Intent.ACTION_VIEW);
                    Uri uri7 = Uri.parse("https://www.youtube.com/watch?v=pY5LsxNdQlA");
                    intentTest7.setData(uri7);
                    startActivity(intentTest7);
                    break;
                case R.id.ImageView_title8:
                    Intent intentTest8 = new Intent(Intent.ACTION_VIEW);
                    Uri uri8 = Uri.parse("https://www.youtube.com/watch?v=h_CIhXs9QA8");
                    intentTest8.setData(uri8);
                    startActivity(intentTest8);
                    break;
            }
        }
    };
    // 뒤로가기 버튼 설정
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intentBack = new Intent(cardview.this, MainActivity3.class);
        startActivity(intentBack);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout); // 화면 전환 애니메이션
        finish();
    }

    public static class CarmeraAdd extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_carmera_add);
        }
    }
}
