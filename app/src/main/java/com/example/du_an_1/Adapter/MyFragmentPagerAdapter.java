package com.example.du_an_1.Adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.du_an_1.Fragment.FragmentChildChi_TabLayout;
import com.example.du_an_1.Fragment.FragmentChildThu_TabLayout;
import com.example.du_an_1.Fragment.Fragment_PhanLoai;

// PaperFragment của phân loại
public class MyFragmentPagerAdapter extends FragmentStateAdapter {
    final int TAB_COUNT = 2;
    public MyFragmentPagerAdapter(Fragment_PhanLoai fa){
        super(fa);
    }

    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = null;
        Class fragmentClass = null;
            switch (position) {
                case 0:
                    fragmentClass = FragmentChildChi_TabLayout.class;
                    break;
                case 1:
                    fragmentClass = FragmentChildThu_TabLayout.class;
                    break;
            }
            try {
                fragment = (Fragment) fragmentClass.newInstance();
            } catch (Exception e) {
            }
            return fragment;
    }
    @Override
    public int getItemCount() {
        return TAB_COUNT;
    }
}
