package com.example.du_an_1;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.du_an_1.Fragment.Gioi_Thieu;
import com.example.du_an_1.NGUOI_DUNG.Nguoi_Dung;
import com.example.du_an_1.Fragment.Fragment_PhanLoai;
import com.example.du_an_1.Fragment.trang_chu;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private ActionBarDrawerToggle drawerToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        drawerLayout = (DrawerLayout) findViewById(R.id.my_drawer);
        drawerToggle = setupDrawertoggle();
        drawerToggle.setDrawerIndicatorEnabled(true);
        drawerToggle.syncState();
        drawerLayout.addDrawerListener(drawerToggle);

        navigationView = (NavigationView) findViewById(R.id.my_navigation);
        setupDrawer(navigationView);

        // hiển thị trang fragment lên đầu tiên
        if(savedInstanceState == null){
            Class fragmentclass = trang_chu.class;
            try {
                Fragment fragment = (Fragment) fragmentclass.newInstance();
                getSupportFragmentManager().beginTransaction().replace(R.id.may_layout_container,fragment).commit();
                navigationView.getMenu().getItem(0).setChecked(true);
                setTitle(navigationView.getMenu().getItem(0).getTitle());
            }catch (Exception e){ }

        }
    }
    private ActionBarDrawerToggle setupDrawertoggle(){
        return new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
    }
    private void setupDrawer(NavigationView _navigationView){
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                select(item);
                return false;
            }
        });
    }
    private boolean select(MenuItem item){
        // gọi trang trong menu ra để xuất lên màn hình
        Fragment fragment = null;
        Class fragmentclass;
        switch (item.getItemId()){
            case R.id.trang_chu:
                fragmentclass = trang_chu.class;
                break;
            case R.id.PHAN_LOAI:
                fragmentclass = Fragment_PhanLoai.class;
                break;
            case R.id.USER:
                fragmentclass = Nguoi_Dung.class;
                break;
            case R.id.DX:
                finish();
                return true;
            case R.id.GT:
                fragmentclass = Gioi_Thieu.class;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + item.getItemId());
        }
        try {
            fragment = (Fragment) fragmentclass.newInstance();
        }catch (Exception e){ }
        getSupportFragmentManager().beginTransaction().replace(R.id.may_layout_container,fragment).commit();
        setTitle(item.getTitle());
        drawerLayout.closeDrawers();
        updatachecked(item.getItemId());
        return true;
    }
    public void updatachecked(int check){
        // hiển thị item được chọn mà in màu.
        Menu menu = navigationView.getMenu();
        for (int i = 0, size = menu.size(); i < size; i++) {
            MenuItem item = menu.getItem(i);
            item.setChecked(item.getItemId() == check);
        }
    }
    @Override
    public boolean onOptionsItemSelected( MenuItem item) {
        if(drawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
}