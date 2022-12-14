package com.example.firidgeapp;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class schedulecheck extends AppCompatActivity {
    private static final String TAG = "schedulecheck";
    Animation fadein;
    private SimpleDateFormat dateFormatForDisplaying = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
    private SimpleDateFormat dateFormatForMonth = new SimpleDateFormat("yyyy년 MM월", Locale.KOREA);
    private SimpleDateFormat dateFormatForMonth2 = new SimpleDateFormat("yyyy-MM", Locale.KOREA);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedulecheck);

        fadein = AnimationUtils.loadAnimation(schedulecheck.this, R.anim.fadein);

        final CompactCalendarView compactCalendarView = (CompactCalendarView) findViewById(R.id.compactcalendar_view);
        compactCalendarView.startAnimation(fadein);

        TextView textView_month = (TextView) findViewById(R.id.textView_month);
        TextView textView_result = (TextView) findViewById(R.id.textView_result);
        textView_month.startAnimation(fadein);
        textView_result.startAnimation(fadein);

        textView_month.setText(dateFormatForMonth.format(compactCalendarView.getFirstDayOfCurrentMonth()));
        compactCalendarView.setFirstDayOfWeek(Calendar.MONDAY);
        textView_result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getDate = textView_result.getText().toString();

                String[] getDate2 = getDate.split(":");
                String getDateST = getDate2[0];
                Log.d(TAG, "태그 getDateST : " +  getDateST);
                SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");

                AlertDialog.Builder builder = new AlertDialog.Builder(schedulecheck.this);
                AlertDialog dialog = builder.setTitle("일정을 삭제하겠습니까?")
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // 확인 클릭 시 실행할 거 작성
                                Date writeDate = null;
                                try {
                                    writeDate = simpleDate.parse(getDateST);
                                    Log.d(TAG, "태그 writeDate: " +writeDate);

                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }

                                Long currentLong = writeDate.getTime();
                                Log.d(TAG, "태그 currentLong : " + currentLong);

                                compactCalendarView.removeEvents(currentLong);
                                textView_result.setText("");
                                Toast.makeText(schedulecheck.this, "일정이 삭제되었습니다.", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // 취소 클릭 시 실행할 거 작성
                                Toast.makeText(schedulecheck.this, "일정삭제가 취소되었습니다..", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .create();
                dialog.show();

            }
        });


        // 날짜 클릭 이벤트 관련 코드
        compactCalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                Log.d(TAG, "태그 dateClicked : " + dateClicked);
                List<Event> events = compactCalendarView.getEvents(dateClicked);

                // 해당날짜에 이벤트가 있으면
                if (events.size() > 0) {
                    textView_result.setText(events.get(0).getData().toString());
                }
                // 해당날짜에 이벤트가 없으면
                else {
                    SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
                    String clickDate = simpleDate.format(dateClicked);


                    AlertDialog.Builder builder = new AlertDialog.Builder(schedulecheck.this);
                    AlertDialog dialog = builder.setTitle("일정추가하기")
                            .setMessage(clickDate + "에 일정을 추가하시겠습니까?")
                            .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // 확인 클릭 시 실행할 거 작성
                                    Toast.makeText(schedulecheck.this, "확인되었습니다.", Toast.LENGTH_SHORT).show();
                                    EditText editText = new EditText(schedulecheck.this);

                                    // 일정입력할 팝업 띄우기
                                    AlertDialog.Builder builder = new AlertDialog.Builder(schedulecheck.this);
                                    AlertDialog dialogText = builder.setTitle("추가할 일정을 입력해 주세요.")
                                            // .setMessage("메시지 입력")
                                            .setView(editText)
                                            .setPositiveButton("저장하기", new DialogInterface.OnClickListener() {
                                                @SuppressLint("SetTextI18n")
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    String dateClicked_st = simpleDate.format(dateClicked);
                                                    Log.d(TAG, "태그 dateClicked_st : " + dateClicked_st);

                                                    Date currentDay = null;
                                                    try {
                                                        // .parse 함수 : Parses text from a string to produce a Date (문자열에서 텍스트를 분석하여 날짜 생성)
                                                        currentDay = simpleDate.parse(dateClicked_st);
                                                        Log.d(TAG, "태그 currentDay : " + currentDay);

                                                    } catch (ParseException e) {
                                                        e.printStackTrace();
                                                    }
                                                    Long currentLong = currentDay.getTime();
                                                    Log.d(TAG, "태그 currentLong : " + currentLong);

                                                    Event ev1 = new Event(Color.GREEN, currentLong, clickDate + " : "+ editText.getText().toString());
                                                    compactCalendarView.addEvent(ev1);
                                                    textView_result.setText(clickDate + " : "+ editText.getText().toString());
                                                    Toast.makeText(schedulecheck.this, "일정이 저장되었습니다.", Toast.LENGTH_SHORT).show();
                                                }
                                            })
                                            .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    // 취소 클릭 시 실행할 거 작성
                                                    Toast.makeText(schedulecheck.this, "일정입력 취소되었습니다.", Toast.LENGTH_SHORT).show();
                                                }
                                            })
                                            .create();
                                    dialogText.show();



                                }
                            })
                            .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // 취소 클릭 시 실행할 거 작성
                                    Toast.makeText(schedulecheck.this, "취소되었습니다.", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .create();
                    dialog.show();
                }
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                textView_month.setText(dateFormatForMonth.format(firstDayOfNewMonth));
                Log.d(TAG, "Month was scrolled to: " + firstDayOfNewMonth);

            }
        });
    }
    // 뒤로가기 버튼 설정
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intentBack = new Intent(schedulecheck.this, MainActivity3.class);
        startActivity(intentBack);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout); // 화면 전환 애니메이션
        finish();
    }
}