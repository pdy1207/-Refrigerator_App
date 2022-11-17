package com.example.firidgeapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firidgeapp.add.add2;
import com.example.firidgeapp.add.add4;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class AddProduct extends AppCompatActivity
{
    // Millisecond 형태의 하루(24 시간)
    private final int ONE_DAY = 24 * 60 * 60 * 1000;
    EditText editText,editText2,edit_text3;

    Button btAdd, btReset;
    RecyclerView recyclerView;

    List<MainData> dataList = new ArrayList<>();
    RoomDB database;
    MainAdapter adapter;
    private Calendar mCalender;
    TextView dateText;
    TextView tv_result,date_text_view;
    private TextView mTvResult;
    DatePickerDialog datePickerDialog;
    // DatePicker 에서 날짜 선택 시 호출

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_recipe);
        //텍스트뷰 변수에 담기
        dateText = findViewById(R.id.date_text_view);
        mCalender = new GregorianCalendar();
        //D-day 보여주기
        mTvResult = findViewById(R.id.tv_result);
        tv_result = findViewById(R.id.tv_result);
        date_text_view = findViewById(R.id.date_text_view);
        TextView tvDate = findViewById(R.id.tv_date);
        tvDate.setText(getToday());

        //버튼 변수에 담기
        Button datePickerBtn = findViewById(R.id.date_picker_btn);


        //버튼 클릭 이벤트 설정
        datePickerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //오늘 날짜(년,월,일) 변수에 담기
                Calendar calendar = Calendar.getInstance();
                int pYear = calendar.get(Calendar.YEAR); //년
                int pMonth = calendar.get(Calendar.MONTH);//월
                int pDay = calendar.get(Calendar.DAY_OF_MONTH);//일

                datePickerDialog = new DatePickerDialog(AddProduct.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                                //1월은 0부터 시작하기 때문에 +1을 해준다.

                                month = month + 1;
                                String date = year + " 년 " + month + " 월 " + day + " 일 ";

                                dateText.setText(date);
                                mTvResult.setText(getDday(year,month-1,day));
                            }
                        }, pYear, pMonth, pDay);
                datePickerDialog.show();
            } //onClick
        });
    //onCreate
        editText = findViewById(R.id.edit_text);
        editText2 = findViewById(R.id.edit_text2);
        btAdd = findViewById(R.id.bt_add);
        btReset = findViewById(R.id.bt_reset);
        recyclerView = findViewById(R.id.recycler_view);
        edit_text3 = findViewById(R.id.edit_text3);
        database = RoomDB.getInstance(this);

        dataList = database.mainDao().getAll();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new MainAdapter(AddProduct.this, dataList);

        recyclerView.setAdapter(adapter);

        btAdd.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                String sText = editText.getText().toString().trim();
                String sText2 = tv_result.getText().toString().trim();
                String sText3 = dateText.getText().toString().trim();
                String sText4 = editText2.getText().toString().trim();
                /*    insertFood(sText,sText2,sText3,sText4);*/
                if (!sText.equals("")) {
                    MainData data = new MainData();
                    data.setText(sText);
                    data.setText2(sText2);
                    data.setText3(sText3);
                    data.setText4(sText4);
                    Log.d("sText", "sText" + sText);
                    Log.d("sText", "sText2" + sText2);
                    Log.d("sText", "sText3" + sText3);
                    Log.d("sText", "sText4" + sText4);

                    database.mainDao().insert(data);
                    /*저장되면 값 지워지게*/
                    editText.setText("");
                    tv_result.setText("");
                    dateText.setText("");
                    editText2.setText("");
                    edit_text3.setText("");

                    dataList.clear();
                    dataList.addAll(database.mainDao().getAll());
                    adapter.notifyDataSetChanged();
                    dialogProfile();
                }

            }

        });

        btReset.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dialogProfile2();
            }
        });
    }
    public void dialogProfile() {
        Dialog dialog = new Dialog(this, R.style.DialogStyle);
        dialog.setContentView(R.layout.add_dialog);
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

        // "확인" 버튼
        Button btnYes = dialog.findViewById(R.id.btn_yes);
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Toast.makeText(AddProduct.this, "등록 되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        dialog.show();
    }
    public void dialogProfile2() {
        Dialog dialog = new Dialog(this, R.style.DialogStyle);
        dialog.setContentView(R.layout.warning_dialog2);
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
                database.mainDao().reset(dataList);
                dataList.clear();
                dataList.addAll(database.mainDao().getAll());
                Toast.makeText(AddProduct.this, "전체 삭제 되었습니다.", Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
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
    /**
     * Today 반환
     */
    private String getToday() {
    // 지정된 format 으로 string 표시
        final String strFormat = getString(R.string.format_today);
        SimpleDateFormat CurDateFormat = new SimpleDateFormat(strFormat);
        return CurDateFormat.format(mCalender.getTime());
    }
    /**
     * D-day 반환
     */
    private String getDday(int year, int month, int day) {
        // D-day 설정
        final Calendar ddayCalendar = Calendar.getInstance();
        ddayCalendar.set(year, month, day);

        // D-day 를 구하기 위해 millisecond 으로 환산하여 d-day 에서 today 의 차를 구한다.
        final long dday = ddayCalendar.getTimeInMillis() / ONE_DAY;
        final long today = Calendar.getInstance().getTimeInMillis() / ONE_DAY;
        long result = dday - today;

        // 출력 시 d-day 에 맞게 표시
        final String strFormat;
        if (result > 0) {
            strFormat = " %d 일 남았습니다 💨";
        } else if (result == 0) {
            strFormat = "D-day 빠른 섭취 해주세요 ❗";
        } else {
            result *= -1;
            strFormat = "🚫 %d 일 지났습니다 🚫";
        }

        final String strCount = (String.format(strFormat, result));
        return strCount;
    }

    // 뒤로가기 버튼 설정
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intentBack = new Intent(AddProduct.this, add4.class);
        startActivity(intentBack);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout); // 화면 전환 애니메이션
        finish();
    }
}