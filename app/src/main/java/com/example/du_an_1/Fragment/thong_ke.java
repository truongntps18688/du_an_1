package com.example.du_an_1.Fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.du_an_1.DAO.GiaoDich_DAO;
import com.example.du_an_1.Entity.GIAODICH;
import com.example.du_an_1.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class thong_ke extends Fragment {
    private BarChart Barchart;
    private Spinner sp,sp_chithu;
//    private RecyclerView recyclerView;
    private ArrayList<GIAODICH> ds;
    private int tong;
    private ArrayList<Integer> thang_1_12 = new ArrayList<>();
    private int thu_hay_chi;
    private int nam;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_thong_ke,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Barchart = view.findViewById(R.id.barchart);
//        recyclerView = view.findViewById(R.id.rv_thong_ke);
        sp = view.findViewById(R.id.spinner);
        sp_chithu = view.findViewById(R.id.sp_thuchi);
        // sp chọn biểu đồ thu hay chi
        ArrayAdapter<CharSequence> adapter_thu_chi = ArrayAdapter.createFromResource(getContext(),R.array.planets_array_1, android.R.layout.simple_spinner_item);
        adapter_thu_chi.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_chithu.setAdapter(adapter_thu_chi);
        sp_chithu.setSelection(0);
        sp_chithu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int p, long l) {
                String thuchi = adapterView.getItemAtPosition(p).toString();
                if (thuchi.equals("CHI")){
                    thu_hay_chi = 0;
                }else{
                    thu_hay_chi = 1;
                }
                select_nam(nam);
                thang_1_12.clear();
                if(ds.size() != 0){
                    SimpleDateFormat month = new SimpleDateFormat("MM");
                    for (int i = 1; i < 13; i++){
                        tong = 0;
                        for (int j = 0; j < ds.size(); j++){
                            Double month_ds = Double.parseDouble(month.format(ds.get(j).getNgay()));
                            if(month_ds == i){
                                tong += ds.get(j).getTien();
                            }
                        }
                        thang_1_12.add(tong);
                    }
                }else{
                    for (int i = 1; i < 13; i++){
                        thang_1_12.add(0);
                    }
                }
                try {
                    List<BarEntry> entries = new ArrayList<>();
                    entries.add(new BarEntry(1f, thang_1_12.get(0)));
                    entries.add(new BarEntry(2f, thang_1_12.get(1)));
                    entries.add(new BarEntry(3f, thang_1_12.get(2)));
                    entries.add(new BarEntry(4f, thang_1_12.get(3)));
                    entries.add(new BarEntry(5f, thang_1_12.get(4)));
                    entries.add(new BarEntry(6f, thang_1_12.get(5)));
                    entries.add(new BarEntry(7f, thang_1_12.get(6)));
                    entries.add(new BarEntry(8f, thang_1_12.get(7)));
                    entries.add(new BarEntry(9f, thang_1_12.get(8)));
                    entries.add(new BarEntry(10f, thang_1_12.get(9)));
                    entries.add(new BarEntry(11f, thang_1_12.get(10)));
                    entries.add(new BarEntry(12f, thang_1_12.get(11)));
                    BarDataSet set;
                    if (thu_hay_chi == 0){
                        set = new BarDataSet(entries,"chi");
                    }else{
                        set = new BarDataSet(entries,"thu");
                    }
                    BarData data = new BarData(set);
                    data.setBarWidth(0.9f);  // khoảng cách <1/ càng nhỏ thì khoảng cách càng lớn
                    Barchart.setData(data); // set dữ liệu
                    Barchart.setFitBars(true); // làm cho trục x khớp chính xác với tất cả các
                    Barchart.invalidate(); // tạo ms
                }catch (Exception e){
                    Log.i("lỗi", "onItemSelected: " + e.getMessage());
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });
        //sp chọn năm
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),R.array.planets_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adapter);
        sp.setSelection(6);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int p, long l) {
                nam = Integer.parseInt(adapterView.getItemAtPosition(p).toString());
                select_nam(nam);
                thang_1_12.clear();
                if(ds.size() != 0){
                    SimpleDateFormat month = new SimpleDateFormat("MM");
                    for (int i = 1; i < 13; i++){
                        tong = 0;
                        for (int j = 0; j < ds.size(); j++){
                            Double month_ds = Double.parseDouble(month.format(ds.get(j).getNgay()));
                            if(month_ds == i){
                                tong += ds.get(j).getTien();
                            }
                        }
                        thang_1_12.add(tong);
                    }
                }else{
                    Toast.makeText(getContext(), "bạn chưa có giao dịch chi tiêu nào", Toast.LENGTH_SHORT).show();
                    for (int i = 1; i < 13; i++){
                        thang_1_12.add(0);
                    }
                }
                try {
                    List<BarEntry> entries = new ArrayList<>();
                    entries.add(new BarEntry(1f, thang_1_12.get(0)));
                    entries.add(new BarEntry(2f, thang_1_12.get(1)));
                    entries.add(new BarEntry(3f, thang_1_12.get(2)));
                    entries.add(new BarEntry(4f, thang_1_12.get(3)));
                    entries.add(new BarEntry(5f, thang_1_12.get(4)));
                    entries.add(new BarEntry(6f, thang_1_12.get(5)));
                    entries.add(new BarEntry(7f, thang_1_12.get(6)));
                    entries.add(new BarEntry(8f, thang_1_12.get(7)));
                    entries.add(new BarEntry(9f, thang_1_12.get(8)));
                    entries.add(new BarEntry(10f, thang_1_12.get(9)));
                    entries.add(new BarEntry(11f, thang_1_12.get(10)));
                    entries.add(new BarEntry(12f, thang_1_12.get(11)));
                    BarDataSet set;
                    if (thu_hay_chi == 0){
                        set = new BarDataSet(entries,"chi");
                    }else{
                        set = new BarDataSet(entries,"thu");
                    }
                    BarData data = new BarData(set);
                    data.setBarWidth(0.9f);  // khoảng cách <1/ càng nhỏ thì khoảng cách càng lớn
                    Barchart.setData(data); // set dữ liệu
                    Barchart.setFitBars(true); // làm cho trục x khớp chính xác với tất cả các
                    Barchart.invalidate(); // tạo ms
                }catch (Exception e){
                    Log.i("lỗi", "onItemSelected: " + e.getMessage());
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });
    }
    public ArrayList<GIAODICH> select_nam(int nam){
        SimpleDateFormat year = new SimpleDateFormat("yyyy");
        ds = new ArrayList<>();
        List<GIAODICH> data = (List<GIAODICH>) (new GiaoDich_DAO(getContext())).select(thu_hay_chi);
        if(data.size() != 0){
            for (int i = 0; i < data.size(); i++){
                Double year_ds = Double.parseDouble(year.format(data.get(i).getNgay()));
                if(year_ds == nam){
                    ds.add(data.get(i));
                }
            }
        }
        return ds;
    }
}