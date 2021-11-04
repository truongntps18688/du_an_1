package com.example.du_an_1.loading_login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.du_an_1.DAO.user_DAO;
import com.example.du_an_1.R;
import com.example.du_an_1.model.USER;

public class dang_ky extends AppCompatActivity {
    EditText tk,mk,cauhoi,tl;
    Button bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
        tk = findViewById(R.id.TK_DK);
        mk = findViewById(R.id.MK_DK);
        cauhoi = findViewById(R.id.CAUHOI_DK);
        tl = findViewById(R.id.TL_DK);
        bt = findViewById(R.id.btn_DK);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // lấy thông tin đã nhập vào edittex
                String x = tk.getText().toString();// tên user
                String y = mk.getText().toString();// mật khẩu user
                String z = cauhoi.getText().toString();// cây hỏi để lấy lại mật khẩu
                String t = tl.getText().toString();// câu trả lời
                Log.i("tk", "onClick: " + x);
                Log.i("mk", "onClick: " + y);
                Log.i("cauhoi", "onClick: " + z);
                Log.i("tl", "onClick: " + t);
                if(x.isEmpty() || y.isEmpty() || z.isEmpty() || t.isEmpty()){
                    // kiểm tra rỗng
                    Toast.makeText(dang_ky.this, "không được để trống", Toast.LENGTH_SHORT).show();
                }else{
                    // thêm user
                    user_DAO user_dao = new user_DAO(dang_ky.this);
                    USER user = new USER(x,y,z,t);
                    user_dao.insert(user);
                    Toast.makeText(dang_ky.this, "thành công", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(dang_ky.this,login.class);
                    startActivity(i);
                }

            }
        });

    }
}