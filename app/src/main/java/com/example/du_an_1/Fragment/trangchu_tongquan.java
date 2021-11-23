package com.example.du_an_1.Fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.du_an_1.DAO.GiaoDich_DAO;
import com.example.du_an_1.DAO.user_DAO;
import com.example.du_an_1.Entity.GIAODICH;
import com.example.du_an_1.Entity.USER;
import com.example.du_an_1.R;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import az.plainpie.PieView;

public class trangchu_tongquan extends Fragment {
    private PieView pieView_ngay,pieView_tuan,pieView_thang;
    private TextView Thu_nhap_ngay,Su_dung_ngay;
    private TextView Thu_nhap_tuan,Su_dung_tuan;
    private TextView Thu_nhap_thang,Su_dung_thang;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_trangchu_tongquan,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        pieView_ngay = view.findViewById(R.id.pieView_ngay);
        pieView_tuan = view.findViewById(R.id.pieView_tuan);
        pieView_thang = view.findViewById(R.id.pieView_thang);
        Thu_nhap_ngay = view.findViewById(R.id.thu_nhap_ngay);
        Thu_nhap_tuan = view.findViewById(R.id.thu_nhap_tuan);
        Thu_nhap_thang = view.findViewById(R.id.thu_nhap_thang);
        Su_dung_ngay = view.findViewById(R.id.su_dung_ngay);
        Su_dung_tuan = view.findViewById(R.id.su_dung_tuan);
        Su_dung_thang = view.findViewById(R.id.su_dung_thang);

        GiaoDich_DAO dao = new GiaoDich_DAO(getContext());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");

        // tính tổng thu chi của ngày hôm nay
        Calendar c = Calendar.getInstance();
        int thisDay = c.get(Calendar.DAY_OF_MONTH);
        int thisWeed = c.get(Calendar.MONTH);
        int thisYead = c.get(Calendar.YEAR);
        String new_Day = thisDay + "/" + (thisWeed+1) + "/" + thisYead;
        List<GIAODICH> data = (List<GIAODICH>) (new GiaoDich_DAO(getContext())).select();
        int size = data.size();
        double tong_chi_ngay = 0;
        double tong_thu_ngay = 0;
        for (int i = 0; i < size; i++){
            DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            String ngay_new_Day = format.format(data.get(i).getNgay().getTime());
            if(new_Day.equals(ngay_new_Day) && data.get(i).getTrang_thai() == 0){
                tong_chi_ngay += data.get(i).getTien();
            }
            if(new_Day.equals(ngay_new_Day) && data.get(i).getTrang_thai() == 1){
                tong_thu_ngay += data.get(i).getTien();
            }
        }
        Su_dung_ngay.setText(decimalFormat.format(tong_chi_ngay)+" đ");
        Thu_nhap_ngay.setText(decimalFormat.format(tong_thu_ngay)+" đ");
        double phan_Tram_ngay = (tong_chi_ngay/tong_thu_ngay)*100;

        pieView_ngay.setMainBackgroundColor (getResources (). getColor (R.color.tongquan));// thay đổi màu nền
        pieView_ngay.setPercentageBackgroundColor(getResources().getColor(android.R.color.holo_blue_bright));// thay đổi màu thanh phân trăm
        pieView_ngay.setPieInnerPadding(30); // số càng lớn thì thanh tròn càng lớn // Thay đổi độ dày của thanh phần trăm
        pieView_ngay.setPercentage((float) phan_Tram_ngay);// set %
        pieView_ngay.setPercentageTextSize(20); // set size %
        pieView_ngay.setInnerBackgroundColor(getResources().getColor(R.color.white));//set màu nền %
        pieView_ngay.setTextColor(getResources().getColor(R.color.black));//set mau cho %


        // tính tổng thu chi của ngày tuần này
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.clear(Calendar.MINUTE);
        cal.clear(Calendar.SECOND);
        cal.clear(Calendar.MILLISECOND);
        cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());// ngày thứ 2
        Long truoc_tuan = cal.getTimeInMillis() -1;
        cal.add(Calendar.WEEK_OF_YEAR, 1);// ngày chủ nhật
        Long sau_tuan = cal.getTimeInMillis() - 1;
        double tong_chi_tuan = 0;
        double tong_thu_tuan = 0;
        tong_chi_tuan = dao.tong(truoc_tuan,sau_tuan,0);
        tong_thu_tuan = dao.tong(truoc_tuan,sau_tuan,1);
        Thu_nhap_tuan.setText(decimalFormat.format(tong_thu_tuan)+" đ");
        Su_dung_tuan.setText(decimalFormat.format(tong_chi_tuan)+" đ");
        double phan_Tram_tuan = (tong_chi_tuan/tong_thu_tuan)*100;
        pieView_tuan.setMainBackgroundColor (getResources (). getColor (R.color.tongquan));// thay đổi màu nền
        pieView_tuan.setPercentageBackgroundColor(getResources().getColor(android.R.color.holo_blue_bright));// thay đổi màu thanh phân trăm
        pieView_tuan.setPieInnerPadding(30); // số càng lớn thì thanh tròn càng lớn // Thay đổi độ dày của thanh phần trăm
        pieView_tuan.setPercentage((float) phan_Tram_tuan);// set %
        pieView_tuan.setPercentageTextSize(20); // set size %
        pieView_tuan.setInnerBackgroundColor(getResources().getColor(R.color.white));//set màu nền %
        pieView_tuan.setTextColor(getResources().getColor(R.color.black));//set mau cho %

        // tính tổng thu chi của tháng này
        Calendar cal_1 = Calendar.getInstance();
        cal_1.set(Calendar.HOUR_OF_DAY, 0);
        cal_1.clear(Calendar.MINUTE);
        cal_1.clear(Calendar.SECOND);
        cal_1.clear(Calendar.MILLISECOND);
        cal_1.set(Calendar.DAY_OF_MONTH, 1);
        Long truoc_thang = cal_1.getTimeInMillis() -1;// ngày đầu tháng
        cal_1.add(Calendar.MONTH, 1);
        Long sau_thang = cal_1.getTimeInMillis() -1;// ngày cuối tháng
        double tong_chi_thang = 0;
        double tong_thu_thang = 0;
        tong_chi_thang = dao.tong(truoc_thang,sau_thang,0);
        tong_thu_thang = dao.tong(truoc_thang,sau_thang,1);
        Thu_nhap_thang.setText(decimalFormat.format(tong_thu_tuan)+" đ");
        Su_dung_thang.setText(decimalFormat.format(tong_chi_tuan)+" đ");
        double phan_Tram_thang = (tong_chi_thang/tong_thu_thang)*100;
        pieView_thang.setMainBackgroundColor (getResources (). getColor (R.color.tongquan));// thay đổi màu nền
        pieView_thang.setPercentageBackgroundColor(getResources().getColor(android.R.color.holo_blue_bright));// thay đổi màu thanh phân trăm
        pieView_thang.setPieInnerPadding(30); // số càng lớn thì thanh tròn càng lớn // Thay đổi độ dày của thanh phần trăm
        pieView_thang.setPercentage((float) phan_Tram_thang);// set %
        pieView_thang.setPercentageTextSize(20); // set size %
        pieView_thang.setInnerBackgroundColor(getResources().getColor(R.color.white));//set màu nền %
        pieView_thang.setTextColor(getResources().getColor(R.color.black));//set mau cho %
    }
}