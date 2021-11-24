package com.example.du_an_1.Fragment;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.du_an_1.Adapter.adapter_giaodich;
import com.example.du_an_1.Adapter.adapter_itemimgphanloai;
import com.example.du_an_1.Adapter.adapter_itemphanloai;
import com.example.du_an_1.Adapter.adapter_phanloai;
import com.example.du_an_1.DAO.GiaoDich_DAO;
import com.example.du_an_1.DAO.PhanLoai_DAO;
import com.example.du_an_1.Entity.GIAODICH;
import com.example.du_an_1.Entity.PHANLOAI;
import com.example.du_an_1.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class FragmentChildChi_TabLayout_GD extends Fragment {
    RecyclerView rv;
    FloatingActionButton fab;
    PhanLoai_DAO dao;
    GIAODICH giaodich;
    GiaoDich_DAO giaoDich_dao;
    PHANLOAI _phanloai;
    String time,_date;
    int hour,_minute;
    String year,month,day;
    SimpleDateFormat ctime = new SimpleDateFormat("hh:mm aa");
    SimpleDateFormat cDate = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layoutchild_phanloai,container,false);
    }

    @SuppressLint("WrongThread")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rv = view.findViewById(R.id.rv_phanloai);
        fab = view.findViewById(R.id.fab_phanloai);

        updateData();
        //nhấn nút show dialog
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getContext(),0);
            }
        });
    }
    @SuppressLint("ResourceType")
    //dialog
    protected void openDialog(final Context context, final int type){
        Button btnSave,btnCancel;
        EditText edTien,edGhiChu;
        TextView tvNgay,tvGio,tvLoai;
        ImageView imgCaterDialog;
        RecyclerView item;
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_giaodich);
        item = dialog.findViewById(R.id.ry_src_giaodich);
        tvLoai = dialog.findViewById(R.id.tvLoai);
        imgCaterDialog = dialog.findViewById(R.id.imgdialog_giaodich);
        edTien = dialog.findViewById(R.id.edTien_giaodich);
        edGhiChu = dialog.findViewById(R.id.edGhiChu_giaodich);
        tvNgay = dialog.findViewById(R.id.tvNgay_giaodich);
        tvGio = dialog.findViewById(R.id.tvGio_giaodich);
        btnCancel = dialog.findViewById(R.id.btnHuyDialogGD);
        btnSave = dialog.findViewById(R.id.btnLuuDialogGD);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        PhanLoai_DAO dao = new PhanLoai_DAO(getContext());
        List<PHANLOAI> listPL = dao.select(0);
        adapter_itemphanloai adapter = new adapter_itemphanloai(listPL, new adapter_itemphanloai.IItemimgphanloai() {
            @Override
            public void onClickListener(PHANLOAI _item) {
                //click vào recycleview
                _phanloai = _item;
                imgCaterDialog.setImageResource(_phanloai.getSrc());
                tvLoai.setText(_phanloai.getName());
                item.setVisibility(View.GONE);
            }
        });
        item.setAdapter(adapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),3);
        item.setLayoutManager(gridLayoutManager);
        item.setVisibility(View.GONE);
        //bắt sự kiện click image hiện rv
        imgCaterDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listPL.size() == 0){
                    //nếu chưa có danh sách phân loại thì không hiện thị recycleview
                    item.setVisibility(View.GONE);
                }else{
                    //nếu có danh sách phân loại thì hiện thị recycleview
                    item.setVisibility(View.VISIBLE);
                }
            }
        });
        //bắt sự kiện click giờ
        tvGio.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(type == 0){
                    if(tvGio.length() == 0){
                        Calendar c = Calendar.getInstance();
                        hour = c.get(Calendar.HOUR_OF_DAY);
                        _minute = c.get(Calendar.MINUTE);

                    }
                }else{
                    try {
                        Date d = ctime.parse(tvGio.getText().toString());
                        String time = new SimpleDateFormat("HH:mm").format(d);
                        hour = Integer.parseInt(time.substring(0,2));
                        _minute = Integer.parseInt(time.substring(3,5));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                }
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        getContext(),
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                Calendar calendar = Calendar.getInstance();
                                calendar.set(0,0,0,hourOfDay,minute);
                                hour = hourOfDay;
                                _minute = minute;
                                time = hour + ":" + _minute;
                                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                                try {
                                    Date date = sdf.parse(time);
                                    time = ctime.format(date);
                                    tvGio.setText(time);
                                } catch (ParseException e) {
                                    Toast.makeText(getContext(), "Error time", Toast.LENGTH_SHORT).show();
                                }
                            }
                        },12,0,false
                );
                timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                //hien thi thoi gian truoc
                timePickerDialog.updateTime(hour,_minute);
                //show dialog
                timePickerDialog.show();
            }
        });
        //bắt sự kiện click ngày
        tvNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(type == 0){
                    if(tvNgay.length() == 0){
                        Calendar c = Calendar.getInstance();
                        year = String.valueOf(c.get(Calendar.YEAR));
                        month = String.valueOf(c.get(Calendar.MONTH) + 1);
                        day = String.valueOf(c.get(Calendar.DAY_OF_MONTH));
                    }
                }else{
                    String time = tvNgay.getText().toString();
                    year = time.substring(6,10);
                    month = time.substring(3,5);
                    day = time.substring(0,2);
                }
                Calendar calendar = Calendar.getInstance();
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int _year, int _month, int dayOfMonth) {
                        calendar.set(_year, _month, dayOfMonth);
                        year = String.valueOf(_year);
                        month = String.valueOf(_month + 1);
                        day = String.valueOf(dayOfMonth);
                        if(month.length()<2){
                            month = "0" + month;
                        }
                        if(day.length()<2){
                            day = "0" + day;
                            Log.i("yeah",day);
                        }
                        _date = day + "/" + month + "/" + year;
                        tvNgay.setText(_date);
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.updateDate(Integer.parseInt(year),Integer.parseInt(month) - 1,Integer.parseInt(day));
                datePickerDialog.show();
            }
        });
//        nếu dialog là sửa
        if(type == 1){
            edGhiChu.setText(giaodich.getMota());
            edTien.setText(giaodich.getTien()+"");
            tvGio.setText(giaodich.getGio());
            tvNgay.setText(cDate.format(giaodich.getNgay()));
            tvLoai.setText(_phanloai.getName());
            imgCaterDialog.setImageResource(_phanloai.getSrc());
        }
        //button huỷ
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        //button lưu
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ghichu = edGhiChu.getText().toString();
                String tien = edTien.getText().toString();
                String gio = tvGio.getText().toString();
                String ngay = tvNgay.getText().toString();
                String loai = tvLoai.getText().toString();
                //check giá trị
                if(validate(ghichu,tien,gio,ngay,loai)){
                    try {
                        if(type == 0){
                            giaodich = new GIAODICH();
                        }
                        giaodich.setTien(Integer.parseInt(tien));
                        if(tien.startsWith("0")){
                            Toast.makeText(getContext(), "Số tiền không bắt đầu bằng số 0", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        giaodich.setGio(gio);
                        giaodich.setNgay(cDate.parse(ngay));
                        giaodich.setMota(ghichu);
                        giaodich.setPhan_loai_id(_phanloai.getId());
                        giaodich.setTrang_thai(0);
                    } catch (Exception e) {
                        Toast.makeText(getContext(), "Số tiền không bao gồm chữ", Toast.LENGTH_LONG).show();
                        return;
                    }
                    if(type == 1){
                        if(giaoDich_dao.updata(giaodich)){
                            Toast.makeText(getContext(), "Sửa thành công", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getContext(), "Sửa thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        if(giaoDich_dao.insert(giaodich)){
                            Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                    updateData();
                }
                dialog.dismiss();
            }
        });


        dialog.show();
    }
    private void updateData(){
        giaoDich_dao = new GiaoDich_DAO(getContext());
        dao = new PhanLoai_DAO(getContext());
        List<GIAODICH> list = giaoDich_dao.select(0);
        adapter_giaodich adapter = new adapter_giaodich(getContext(),list, new adapter_giaodich.Igiaodich() {
            //bắt sự kiện click trên rv
            @Override
            public void onClickListener(GIAODICH _giaodich,int type) {
                giaodich = _giaodich;
                if(type == 0) {
                    _phanloai = dao.getItemPL(_giaodich.getPhan_loai_id());
                    //show dialog sửa
                    openDialog(getContext(), 1);
                }else {
                    //xoá
                    del(giaodich);
                }
            }
        });

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),1);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(gridLayoutManager);
        rv.setAdapter(adapter);
    }
    private boolean validate(String ghichu,String tien,String gio,String ngay,String loai){
        if(tien.length() == 0 || gio.length() == 0 || ngay.length() == 0
                || loai.length() == 0){
            Toast.makeText(getContext(), "Chưa nhập đầy đủ thông tin ", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    private void del(GIAODICH giaodich){
        giaoDich_dao = new GiaoDich_DAO(getContext());
        if(giaoDich_dao.delete(giaodich.getId())){
            Toast.makeText(getContext(), "Xoá thành công", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getContext(), "Xoá thất bại", Toast.LENGTH_SHORT).show();
        }
        updateData();
    }


}
