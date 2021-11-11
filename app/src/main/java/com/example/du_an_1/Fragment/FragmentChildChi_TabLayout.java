package com.example.du_an_1.Fragment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.du_an_1.Adapter.adapter_itemimgphanloai;
import com.example.du_an_1.Adapter.adapter_phanloai;
import com.example.du_an_1.DAO.PhanLoai_DAO;
import com.example.du_an_1.Entity.PHANLOAI;
import com.example.du_an_1.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class FragmentChildChi_TabLayout extends Fragment {
    RecyclerView rv;
    FloatingActionButton fab;
    PhanLoai_DAO dao;
    PHANLOAI _phanloai;
    int idAnh;
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
        EditText edName;
        ImageView imgCaterDialog;
        RecyclerView rv_dialog;
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_phanloai);
        edName = dialog.findViewById(R.id.ed_phanloai);
        btnCancel = dialog.findViewById(R.id.btnHuyDialogPH);
        btnSave = dialog.findViewById(R.id.btnLuuDialogPH);
        imgCaterDialog = dialog.findViewById(R.id.imgdialog_phanloai);
        rv_dialog = dialog.findViewById(R.id.dialoglv_phanloai);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        //tạo danh sách chứa các ảnh trong dialog
        List<Integer> list = new ArrayList<>();
        list.add(R.drawable.anh1);  list.add(R.drawable.anh2);  list.add(R.drawable.anh3);
        list.add(R.drawable.anh4);  list.add(R.drawable.anh6);  list.add(R.drawable.anh7);
        list.add(R.drawable.anh8);  list.add(R.drawable.anh9);  list.add(R.drawable.anh10);
        list.add(R.drawable.anh11); list.add(R.drawable.anh5); list.add(R.drawable.anh13);
        list.add(R.drawable.anh14); list.add(R.drawable.anh15); list.add(R.drawable.anh16);
        list.add(R.drawable.anh17); list.add(R.drawable.anh18); list.add(R.drawable.anh19);
        list.add(R.drawable.anh20); list.add(R.drawable.anh22); list.add(R.drawable.anh24);
        list.add(R.drawable.anh21); list.add(R.drawable.anh26); list.add(R.drawable.anh27);
        list.add(R.drawable.anh25); list.add(R.drawable.anh28); list.add(R.drawable.anh29);
        list.add(R.drawable.anh30);

        //adapter ảnh
        adapter_itemimgphanloai adapter = new adapter_itemimgphanloai(list, new adapter_itemimgphanloai.IItemimgphanloai() {
            //click ảnh trong rv show ra imageview
            @Override
            public void onClickListener(int anhID) {
                idAnh = anhID;
                imgCaterDialog.setImageResource(idAnh);
            }
        });
        rv_dialog.setAdapter(adapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),4);
        rv_dialog.setLayoutManager(gridLayoutManager);

        //nếu dialog là sửa
        if(type == 1){
            idAnh = _phanloai.getSrc();
            edName.setText(_phanloai.getName());
            imgCaterDialog.setImageResource(idAnh);
        }
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edName.getText().toString();
                int img = idAnh;
                dao = new PhanLoai_DAO(getContext());
                PHANLOAI phanloai = new PHANLOAI(img,name,0);
                if(validate(name)){
                    if(type == 1){
                        phanloai.setId(_phanloai.getId());
                        dao.updata(phanloai);
                        if(dao.updata(phanloai)){
                            Toast.makeText(getContext(), "Sửa thành công", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getContext(), "Sửa thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }else{

                        if(dao.insert(phanloai)){
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

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),3);
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
