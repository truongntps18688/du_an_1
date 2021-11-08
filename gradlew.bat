package com.example.truongntps18688.dialog;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentResultListener;

import com.example.truongntps18688.DAO.LoaiSach_DAO;
import com.example.truongntps18688.DAO.Sach_DAO;
import com.example.truongntps18688.R;
import com.example.truongntps18688.adapter.name_loai_sach_adapter;
import com.example.truongntps18688.model.LOAISACH;
import com.example.truongntps18688.model.SACH;

import java.util.List;

public class add_Sach extends DialogFragment implements FragmentResultListener {

    private EditText name,gia,sl;
    private Button them,huy;
    private TextView tv;
    private Spinner sp;
    private Sach_DAO dao = new Sach_DAO(getContext());

    private List<LOAISACH> data;
    private name_loai_sach_adapter sach_adapter;
    private int id_ls = 0;


    public  add_Sach(){}
    public static add_Sach newInstance(int id){
        add_Sach fragment = new add_Sach();
        Bundle arg = new Bundle();
        arg.putInt("id_s",id);
        fragment.setArguments(arg);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_add_sach,container);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tv = (TextView) view.findViewById(R.id.tieude_s);
        name = (EditText) view.findViewById(R.id.name_sach);
        gia = (EditText) view.findViewById(R.id.gia_sach);
        sl = (EditText) view.findViewById(R.id.soluong_sach);
        them = (Button) view.findViewById(R.id.themSach);
        huy = (Button) view.findViewById(R.id.huythemSach);
        sp = (Spinner) view.findViewById(R.id.spinner_sach);

        int _id = getArguments().getInt("id_s",-1);
        dao = new Sach_DAO(getContext());

        data = (new LoaiSach_DAO(getContext())).select();
        sach_adapter = new name_loai_sach_adapter(getContext(),data);
        sp.setAdapter(sach_adapter);
        sp.setSelection(0);

        if(_id == -1){
            Log.d("lỗi", "onViewCreated: no");
        }else{
           