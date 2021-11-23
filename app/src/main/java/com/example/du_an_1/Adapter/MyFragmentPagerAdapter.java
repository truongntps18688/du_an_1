package com.example.du_an_1.Adapter;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.du_an_1.Fragment.FragmentChildChi_TabLayout_GD;
import com.example.du_an_1.Fragment.FragmentChildChi_TabLayout_PL;
import com.example.du_an_1.Fragment.FragmentChildThu_TabLayout_GD;
import com.example.du_an_1.Fragment.FragmentChildThu_TabLayout_PL;
import com.example.du_an_1.Fragment.Fragment_PhanLoai;

// PaperFragment của phân loại
public class MyFragmentPagerAdapter extends FragmentStateAdapter {
    final int TAB_COUNT = 2;
    int type;
    public MyFragmentPagerAdapter(Fragment fa, int type){
        super(fa);
        this.type = type;
    }

    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = null;
        Class fragmentClass = null;
        if(type == 1){
            switch (position) {
                case 0:
                    fragmentClass = FragmentChildChi_TabLayout_PL.class;
                    break;
                case 1:
                    fragmentClass = FragmentChildThu_TabLayout_PL.class;
                    break;
            }
            try {
                fragment = (Fragment) fragmentClass.newInstance();
            } catch (Exception e) {
            }
        }else{
            switch (position) {
                case 0:
                    fragmentClass = FragmentChildChi_TabLayout_GD.class;
                    break;
                case 1:
                    fragmentClass = FragmentChildThu_TabLayout_GD.class;
                    break;
            }
            try {
                fragment = (Fragment) fragmentClass.newInstance();
            } catch (Exception e) {
            }
        }
            return fragment;
    }
    @Override
    public int getItemCount() {
        return TAB_COUNT;
    }
}
