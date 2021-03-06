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
            Log.d("lỗi", "onViewCreated: vào");
            tv.setText("Sửa Thông Tin Sách");
            SACH s = dao.getByID(_id);
            name.setText(s.getTen_sach());
            gia.setText(s.getGiaThue()+"");
            sl.setText(s.getSo_luong()+"");
            id_ls = getIndex(data,s.getMa_loai());
            sp.setSelection(id_ls);
            them.setText("Sửa");
        }
        name.requestFocus();
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s_name = name.getText().toString();
                int s_gia = Integer.parseInt(gia.getText().toString());
                int s_sl = Integer.parseInt(sl.getText().toString());
                SACH s = new SACH(s_name,s_gia,s_sl,id_ls);
                Sach_DAO sach_dao = new Sach_DAO(getContext());
                if(_id == -1){
                    List<SACH> ds = dao.select();
                    int x = -1;
                    if(ds.size()<1){}else{
                        x = ds.size();
                    }
                    int kt = 0;
                    for (int i = 0; i < x; i++){
                        if(name.equals(ds.get(i).getTen_sach())){
                            kt = 1;
                            Toast.makeText(getContext(), "đã tồn tại sách", Toast.LENGTH_SHORT).show();
                            break;
                        }
                    }
                    if (kt == 0){
                        sach_dao.insert(s);
                    }
                }else{
                    s.setId(dao.getByID(_id).getId());
                    sach_dao.updata(s);
                }
                getParentFragmentManager().setFragmentResult("key",new Bundle());
                dismiss();
            }
        });
        huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                LOAISACH loaisach = (LOAISACH) adapterView.getItemAtPosition(i);
                id_ls = loaisach.getId();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void onFragmentResult(String requestKey,Bundle result) {

    }
    private int getIndex (List<LOAISACH> categories, int categoryId) {
        for (int i = 0; i < categories.size(); i++) {
            if (categories.get(i).getId() == categoryId) return i;
        }
        return 0;
    }
}
                           