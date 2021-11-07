package com.example.du_an_1.NGUOI_DUNG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.du_an_1.DAO.user_DAO;
import com.example.du_an_1.R;
import com.example.du_an_1.model.USER;

import java.util.List;

public class Nguoi_Dung extends Fragment {
    private TextView tv_1,tv_2,tv_3,tv1,tv2,tv3;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getParentFragmentManager().setFragmentResultListener("key",
                Nguoi_Dung.this,new chinh_sua(){
                    @Override
                    public void onFragmentResult(String requestKey, Bundle result) {
                        super.onFragmentResult(requestKey, result);

                    }
                });
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_nguoi_dung,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tv_1 = view.findViewById(R.id.MK_user);
        tv_2 = view.findViewById(R.id.cauhoi_user);
        tv_3 = view.findViewById(R.id.cautl_user);
        tv1 = view.findViewById(R.id.sua_MK_user);
        tv2 = view.findViewById(R.id.sua_cauhoi_user);
        tv3 = view.findViewById(R.id.sua_cautl_user);
        List<USER> data = (List<USER>) (new user_DAO(getContext())).select();
        tv_1.setText(data.get(0).getMk());
        tv_2.setText(data.get(0).getCauhoi());
        tv_3.setText(data.get(0).getTl());
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                chinh_sua dialogFragment = chinh_sua.newInstance(1);
                dialogFragment.show(fragmentManager, "");
                onCreate(savedInstanceState);
            }
        });
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                chinh_sua dialogFragment = chinh_sua.newInstance(2);
                dialogFragment.show(fragmentManager, "");
                onCreate(savedInstanceState);
            }
        });
        tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                chinh_sua dialogFragment = chinh_sua.newInstance(3);
                dialogFragment.show(fragmentManager, "");
                onCreate(savedInstanceState);
            }
        });
    }
}