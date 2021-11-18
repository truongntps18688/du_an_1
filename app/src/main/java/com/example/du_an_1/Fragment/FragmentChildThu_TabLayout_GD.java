package com.example.du_an_1.Fragment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
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

import com.example.du_an_1.Adapter.adapter_itemimgphanloai;
import com.example.du_an_1.Adapter.adapter_phanloai;
import com.example.du_an_1.DAO.PhanLoai_DAO;
import com.example.du_an_1.Entity.PHANLOAI;
import com.example.du_an_1.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class FragmentChildThu_TabLayout_GD extends Fragment {
    RecyclerView rv;
    FloatingActionButton fab;
    PhanLoai_DAO dao;
    PHANLOAI _phanloai;
    private int idAnh = 0;
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
        idAnh = R.drawable.anh1;
        Button btnSave,btnCancel;
        EditText edTien,edGhiChu;
        TextView tvNgay,tvGio;
        ImageView imgCaterDialog;
        RecyclerView item;
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_giaodich);
        item = dialog.findViewById(R.id.ry_src_giaodich);
        edTien = dialog.findViewById(R.id.edTien_giaodich);
        edGhiChu = dialog.findViewById(R.id.edGhiChu_giaodich);
        tvNgay = dialog.findViewById(R.id.tvNgay_giaodich);
        tvGio = dialog.findViewById(R.id.tvGio_giaodich);
        btnCancel = dialog.findViewById(R.id.btnHuyDialogGD);
        btnSave = dialog.findViewById(R.id.btnLuuDialogGD);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);

        PhanLoai_DAO dao = new PhanLoai_DAO(getContext());
        List<PHANLOAI> listPL = dao.select();

        List<Integer> list = new ArrayList<>();
        for (int i = 0;i < listPL.size();i++){
            list.add(listPL.get(i).getSrc());
        }

        item.setVisibility(View.GONE);

        adapter_itemimgphanloai adapter = new adapter_itemimgphanloai(list, new adapter_itemimgphanloai.IItemimgphanloai() {
            //click ảnh trong rv show ra imageview
            @Override
            public void onClickListener(int anhID) {
                idAnh = anhID;

                item.setVisibility(View.GONE);
            }
        });
        item.setAdapter(adapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),4);
        item.setLayoutManager(gridLayoutManager);

        tvGio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        getContext(),
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                Calendar calendar = Calendar.getInstance();
                                calendar.set(0,0,0,hourOfDay,minute);
                            }
                        },12,0,false
                );
                timePickerDialog.show();
            }
        });
//        tvNgay.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
//                    @Override
//                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                        Calendar calendar = Calendar.getInstance();
//                        calendar.set(year,month,dayOfMonth);
//                    }
//                });
//            }
//        });
        //nếu dialog là sửa
//        if(type == 1){
//            idAnh = _phanloai.getSrc();
//            edName.setText(_phanloai.getName());
//            imgCaterDialog.setImageResource(idAnh);
//        }
//        btnCancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dialog.dismiss();
//            }
//        });
//        btnSave.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String name = edName.getText().toString();
//                int img = idAnh;
//                dao = new PhanLoai_DAO(getContext());
//                PHANLOAI phanloai = new PHANLOAI(img,name,0);
//                if(validate(name)){
//                    if(type == 1){
//                        phanloai.setId(_phanloai.getId());
//                        dao.updata(phanloai);
//                        if(dao.updata(phanloai)){
//                            Toast.makeText(getContext(), "Sửa thành công", Toast.LENGTH_SHORT).show();
//                        }else{
//                            Toast.makeText(getContext(), "Sửa thất bại", Toast.LENGTH_SHORT).show();
//                        }
//                    }else{
//                        if(dao.check(name) > 0){
//                            Toast.makeText(getContext(), "đã tồn tại ", Toast.LENGTH_SHORT).show();
//                        }else if(dao.insert(phanloai)){
//                            Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
//                        }else{
//                            Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                    updateData();
//                }
//                dialog.dismiss();
//            }
//        });
        dialog.show();
    }
    private void updateData(){
        dao = new PhanLoai_DAO(getContext());
        List<PHANLOAI> list = dao.select(0);
        adapter_phanloai adapter = new adapter_phanloai(list, new adapter_phanloai.Iphanloai() {
            //bắt sự kiện click trên rv
            @Override
            public void onClickListener(PHANLOAI phanloai,int type) {
                _phanloai = phanloai;
                if(type == 0) {
                    //show dialog sửa
                    openDialog(getContext(), 1);
                }else {
                    //xoá
                    del(_phanloai);
                }
            }
        });

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),1);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(gridLayoutManager);
        rv.setAdapter(adapter);
    }
    private boolean validate(String ed){
        if(ed.length() == 0){
            Toast.makeText(getContext(), "Chưa nhập tên loại", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    private void del(PHANLOAI __phanloai){
        dao = new PhanLoai_DAO(getContext());
        if(dao.delete(__phanloai.getId())){
            Toast.makeText(getContext(), "Xoá thành công", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getContext(), "Xoá thất bại", Toast.LENGTH_SHORT).show();
        }
        updateData();
    }

}
