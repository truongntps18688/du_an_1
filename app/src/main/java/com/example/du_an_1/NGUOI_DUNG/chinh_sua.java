package com.example.du_an_1.NGUOI_DUNG;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentResultListener;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.du_an_1.DAO.user_DAO;
import com.example.du_an_1.R;
import com.example.du_an_1.Entity.USER;

import java.util.List;

public class chinh_sua extends DialogFragment implements FragmentResultListener {
    private EditText kt_mk,mk_new,cauhoi_new,tl_new;
    private Button kiemtra,sua,huy;
    public  chinh_sua(){}
    public static chinh_sua newInstance(Integer id){
        chinh_sua fragment = new chinh_sua();
        Bundle arg = new Bundle();
        arg.putInt("id",id);
        fragment.setArguments(arg);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_chinh_sua,container);
    }
    @Override
    public void onViewCreated(View view,Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        kt_mk = (EditText) view.findViewById(R.id.MK_KT_chinhsua);
        mk_new = (EditText) view.findViewById(R.id.ed);
        cauhoi_new = (EditText) view.findViewById(R.id.ed1);
        tl_new = (EditText) view.findViewById(R.id.ed2);
        kiemtra = (Button) view.findViewById(R.id.btn_KT_chinhsua);
        sua = (Button) view.findViewById(R.id.sua_chinhsua);
        huy = (Button) view.findViewById(R.id.huy_chinhsua);
        Integer id = getArguments().getInt("id",-1);

        getDialog().getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);

        //ẩn mật khẩu kiểm tra
        kt_mk.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        mk_new.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        //ẩn thanh
        mk_new.setVisibility(View.GONE);
        cauhoi_new.setVisibility(View.GONE);
        tl_new.setVisibility(View.GONE);
        sua.setVisibility(View.GONE);
        huy.setVisibility(View.GONE);
        List<USER> data = (List<USER>) (new user_DAO(getContext())).select();
        kiemtra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String kt_1 = kt_mk.getText().toString();
                if(data.get(0).getMk().equals(kt_1)){
                    Toast.makeText(getContext(), "chính xác", Toast.LENGTH_SHORT).show();
                    if(id == 1){
                        sua.setVisibility(View.VISIBLE);
                        huy.setVisibility(View.VISIBLE);
                        mk_new.setVisibility(View.VISIBLE);
                    }else if(id == 2){
                        sua.setVisibility(View.VISIBLE);
                        huy.setVisibility(View.VISIBLE);
                        cauhoi_new.setVisibility(View.VISIBLE);
                    }else if(id == 3){
                        sua.setVisibility(View.VISIBLE);
                        huy.setVisibility(View.VISIBLE);
                        tl_new.setVisibility(View.VISIBLE);
                    }
                }else {
                    Toast.makeText(getContext(), "không đúng mật khẩu", Toast.LENGTH_SHORT).show();
                }
            }
        });
        huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(id == 1){
                    String mk_user = mk_new.getText().toString();
                    if(mk_user.isEmpty()){
                        mk_new.requestFocus();
                        Toast.makeText(getContext(), "không được bỏ trống", Toast.LENGTH_SHORT).show();
                    }else if(mk_user.length() < 6){
                        Toast.makeText(getContext(), "mật khẩu phải ít nhất 6 ký tự", Toast.LENGTH_SHORT).show();
                    }else{
                        user_DAO user_dao = new user_DAO(getContext());
                        USER u = new USER();
                        u.setTk(data.get(0).getTk());
                        u.setMk(mk_user);
                        user_dao.updata_quen_mk(u);
                        Toast.makeText(getContext(), "thành công", Toast.LENGTH_SHORT).show();
                    }
                }else if(id == 2){
                    String cauhoi_user = cauhoi_new.getText().toString();
                    if(cauhoi_user.isEmpty()){
                        cauhoi_new.requestFocus();
                        Toast.makeText(getContext(), "không được bỏ trống", Toast.LENGTH_SHORT).show();
                    }else{
                        user_DAO user_dao = new user_DAO(getContext());
                        USER u = new USER();
                        u.setTk(data.get(0).getTk());
                        u.setCauhoi(cauhoi_user);
                        user_dao.updata_CauHoi(u);
                        Toast.makeText(getContext(), "thành công", Toast.LENGTH_SHORT).show();
                    }
                }else if(id == 3){
                    String tl_user = tl_new.getText().toString();
                    if(tl_user.isEmpty()){
                        cauhoi_new.requestFocus();
                        Toast.makeText(getContext(), "không được bỏ trống", Toast.LENGTH_SHORT).show();
                    }else{
                        user_DAO user_dao = new user_DAO(getContext());
                        USER u = new USER();
                        u.setTk(data.get(0).getTk());
                        u.setTl(tl_user);
                        user_dao.updata_CauTL(u);
                        Toast.makeText(getContext(), "thành công", Toast.LENGTH_SHORT).show();
                    }
                }
                getParentFragmentManager().setFragmentResult("key",new Bundle());
                dismiss();
            }
        });


    }

    @Override
    public void onFragmentResult(String requestKey,Bundle result) {

    }
}