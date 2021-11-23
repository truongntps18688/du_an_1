package com.example.du_an_1.Adapter;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.du_an_1.Fragment.trang_chu;
import com.example.du_an_1.Fragment.trangchu_cachangmuc;
import com.example.du_an_1.Fragment.trangchu_tongquan;

// PaperFragment của phân loại
public class trangchu_adapter extends FragmentStateAdapter {
    final int TAB_COUNT = 2;
    public trangchu_adapter(trang_chu fa){
        super(fa);
    }

    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = null;
        Class fragmentClass = null;
            switch (position) {
                case 0:
                    fragmentClass = trangchu_tongquan.class;
                    break;
                case 1:
                    fragmentClass = trangchu_cachangmuc.class;
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
