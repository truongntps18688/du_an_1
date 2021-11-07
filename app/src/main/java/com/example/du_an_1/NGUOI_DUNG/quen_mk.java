package com.example.du_an_1.NGUOI_DUNG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.du_an_1.DAO.user_DAO;
import com.example.du_an_1.R;
import com.example.du_an_1.model.USER;

import java.util.List;

public class quen_mk extends AppCompatActivity {
    EditText tk,tl,mk_moi;
    TextView cauhoi;
    Button kt,doimk;
    LinearLayout ln1,ln2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quen_mk);
        tk = findViewById(R.id.TK_QUENMK);
        tl = findViewById(R.id.TL_QUENMK);
        mk_moi = findViewById(R.id.MK_QUENMK);
        cauhoi = findViewById(R.id.CAUHOI_QUENMK);
        kt = findViewById(R.id.btn_KT);
        doimk = findViewById(R.id.btn_QUENMK);
        ln1 = findViewById(R.id.quenmk1);
        ln2 = findViewById(R.id.quenmk2);
        // thực hiện ẩn để kiểm tra có đúng tài khoản không
        ln1.setVisibility(View.GONE);
        ln2.setVisibility(View.GONE);
        doimk.setVisibility(View.GONE);
        List<USER> data = (List<USER>) (new user_DAO(quen_mk.this)).select();
        //thực hiện kiểm tra
        kt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String tk_1 = tk.getText().toString();
                //kiểm tra xem giống tài khoản chưa
                if(data.get(0).getTk().equals(tk_1)){
                    // vô hiệu hóa nhập tài khoản
                    tk.setFocusable(false);
                    // hiện lại các thanh
                    ln1.setVisibility(View.VISIBLE);
                    ln2.setVisibility(View.VISIBLE);
                    doimk.setVisibility(View.VISIBLE);
                    cauhoi.setText(data.get(0).getCauhoi());
                    Toast.makeText(quen_mk.this, "chính xác", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(quen_mk.this, "không đúng tài khoản", Toast.LENGTH_SHORT).show();
                }
            }
        });
        doimk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mk_1 = mk_moi.getText().toString();
                String traloi = tl.getText().toString();
                if(data.get(0).getTl().equals(traloi)){
                    if(mk_1.isEmpty()){
                        mk_moi.requestFocus();
                        Toast.makeText(quen_mk.this, "không được bỏ trống", Toast.LENGTH_SHORT).show();
                    }else if(mk_1.length() < 6){
                        mk_moi.requestFocus();
                        Toast.makeText(quen_mk.this, "mật khẩu phải ít nhất 6 ký tự", Toast.LENGTH_SHORT).show();
                    }else{
                        user_DAO user_dao = new user_DAO(quen_mk.this);
                        USER u = new USER();
                        u.setTk(data.get(0).getTk());
                        u.setMk(mk_1);
                        user_dao.updata_quen_mk(u);
                        Toast.makeText(quen_mk.this, "thành công", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(quen_mk.this, login.class);
                        startActivity(i);
                    }
                }else{
                    tl.requestFocus();
                    Toast.makeText(quen_mk.this, "câu trả lời không chính xác", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}