package com.example.firidgeapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class MainActivity15 extends AppCompatActivity {

    PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main16);

        pieChart = (PieChart)findViewById(R.id.piechart);

        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(10,10,10,10);

        pieChart.setDragDecelerationFrictionCoef(0.95f);

        pieChart.setDrawHoleEnabled(false);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setTransparentCircleRadius(61f);

        ArrayList yValues = new ArrayList();

        yValues.add(new PieEntry(34f,"배달"));
        yValues.add(new PieEntry(23f,"요리"));
        yValues.add(new PieEntry(14f,"기타"));
        yValues.add(new PieEntry(35f,"다이어트식품"));
        yValues.add(new PieEntry(45f,"밀키트"));
        yValues.add(new PieEntry(40f,"외식"));

        Description description = new Description();
        description.setText("식비 통계"); //라벨
        description.setTextSize(50);
        pieChart.setDescription(description);


        PieDataSet dataSet = new PieDataSet(yValues,"Countries");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);
        dataSet.setColors(ColorTemplate.JOYFUL_COLORS);

        PieData data = new PieData((dataSet));
        data.setValueTextSize(35f);
        data.setValueTextColor(Color.YELLOW);

        pieChart.setData(data);

    }
    // 뒤로가기 버튼 설정
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intentBack = new Intent(MainActivity15.this, MainActivity2.class);
        startActivity(intentBack);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout); // 화면 전환 애니메이션
        finish();
    }
}