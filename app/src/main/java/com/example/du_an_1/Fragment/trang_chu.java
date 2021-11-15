package com.example.du_an_1.Fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.du_an_1.Adapter.trangchu_adapter;
import com.example.du_an_1.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class trang_chu extends Fragment {
    private TabLayout tabLayout;
    private ViewPager2 viewPager2;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_tab,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewPager2 = view.findViewById(R.id.my_viewpaper);
        tabLayout = view.findViewById(R.id.my_tab);
        viewPager2.setAdapter( new trangchu_adapter(this));
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab( TabLayout.Tab tab, int position) {
                switch (position){
                    case 0:
                        tab.setText("Tổng quan");
                        break;
                    case 1:
                        tab.setText("Các hạng mục");
                        break;
                }
            }
        });
        tabLayoutMediator.attach();
    }
}