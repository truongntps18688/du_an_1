package com.example.du_an_1.loading_login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.du_an_1.DAO.user_DAO;
import com.example.du_an_1.MainActivity;
import com.example.du_an_1.R;
import com.example.du_an_1.model.USER;

import java.util.ArrayList;
import java.util.List;

public class login extends AppCompatActivity {
    TextView tv1,tv2,tv_quen_mk;
    EditText ed1,ed2;
    Button bt;
    CheckBox checkBox;
    SharedPreferences LuuThongTinDangNhap;

    private List<USER> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        tv_quen_mk = findViewById(R.id.quenmk);
        ed1 = findViewById(R.id.TK_DN);
        ed2 = findViewById(R.id.MK_DN);
        bt = findViewById(R.id.login);
        checkBox = findViewById(R.id.checkbox);

        // kiểm tra tính tồn tại của user, nếu đã có thì ẩn thanh đăng ký tài khoản
        data = (List<USER>) (new user_DAO(login.this)).select();
        Log.i("size", "onCreate: " + data.size());
        if(data.size() == 1){
            tv1.setVisibility(View.GONE);
            tv2.setVisibility(View.GONE);
        }

        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // chuyển qua activity đăng ký
                Intent i = new Intent(login.this,dang_ky.class);
                startActivity(i);
            }
        });


        LuuThongTinDangNhap = getSharedPreferences("ThongTinDangNhap",MODE_PRIVATE);
        ed1.setText(LuuThongTinDangNhap.getString("TK",""));
        ed2.setText(LuuThongTinDangNhap.getString("MK",""));
        checkBox.setChecked(LuuThongTinDangNhap.getBoolean("CHECK",false));
        ed2.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);


        // đăng nhập và kiểm lỗi
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // lấy thông tin đã nhập từ bàn phím và gán vào biến
                String tk = ed1.getText().toString();
                String mk = ed2.getText().toString();

                if(tk.isEmpty() || mk.isEmpty()){
                    // kiểm tra rỗng
                    Toast.makeText(login.this, "không được để trống", Toast.LENGTH_SHORT).show();
                    Log.i("login", "không được để trống");
                }else if(data.size() == 0){
                    // kiểm tra xem người dùng đã có tài khoản hay chưa
                    Toast.makeText(login.this, "Bạn chưa có tài khoản, hãy đâng ký", Toast.LENGTH_SHORT).show();
                    Log.i("login", "chưa có tài khoản");
                }else if(data.size() > 0){
                    // lấy tài khoản và mật khẩu của người dùng ra và check
                    String tk_user = data.get(0).getTk();
                    String mk_user = data.get(0).getMk();
                    if(tk_user.equals(tk) && mk_user.equals(mk)){
                        Toast.makeText(login.this, "thành công", Toast.LENGTH_SHORT).show();
                        Log.i("login", "thành công");
                        setLuuThongTinDangNhap(tk,mk,checkBox.isChecked());
                        Intent i = new Intent(login.this, MainActivity.class);
                        startActivity(i);
                    }else {
                        Toast.makeText(login.this, "sai tài khoản hoặc mật khẩu", Toast.LENGTH_SHORT).show();
                        Log.i("login", "sai tài khoản hoặc mật khẩu");
                    }
                }
                Log.i("tk", "onCreate: " + data.get(0).getTk());
                Log.i("mk", "onCreate: " + data.get(0).getMk());
            }
        });
    }
    public void setLuuThongTinDangNhap(String tk,String mk, boolean check){
        SharedPreferences sharedPreferences = getSharedPreferences("ThongTinDangNhap",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (check == false){
            editor.clear();
        }else {
            editor.putString("TK",tk);
            editor.putString("MK",mk);
            editor.putBoolean("CHECK",check);
        }
        editor.commit();
    }
}