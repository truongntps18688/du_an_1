package com.example.du_an_1.Fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.du_an_1.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class trangchu_cachangmuc extends Fragment {
    private BarChart barchart;
    @Override

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_trangchu_cachangmuc,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        barchart = view.findViewById(R.id.barchart);
        List<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0f, 30f));
        entries.add(new BarEntry(1f, 80f));
        entries.add(new BarEntry(2f, 60f));
        entries.add(new BarEntry(3f, 50f));
        entries.add(new BarEntry(4f, 50f));
        entries.add(new BarEntry(5f, 70f));
        entries.add(new BarEntry(6f, 60f));
        entries.add(new BarEntry(7f, 80f));
        entries.add(new BarEntry(8f, 100f));
        entries.add(new BarEntry(9f, 90f));
        entries.add(new BarEntry(10f, 10f));
        entries.add(new BarEntry(11f, 70f));
        entries.add(new BarEntry(12f, 10f));

        BarDataSet set = new BarDataSet(entries,"thống kê");
        BarData data = new BarData(set);
        data.setBarWidth(0.9f);  // khoảng cách <1/ càng nhỏ thì khoảng cách càng lớn
        barchart.setData(data); // set dữ liệu
        barchart.setFitBars(true); // làm cho trục x khớp chính xác với tất cả các
        barchart.invalidate(); // tạo ms

    }
}