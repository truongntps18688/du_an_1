package com.example.du_an_1.fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.du_an_1.R;

public class Gioi_Thieu extends Fragment {
    private TextView tv,tv2;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_gioi_thieu,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tv = view.findViewById(R.id.tieude_GT);
        tv2 = view.findViewById(R.id.tieude_GT_1);

        // settext tiêu đề
        String tieude = "Tự hào là một trong những ứng\n dụng được người dùng ưa\n chuộng nhật hiện nay";
        tv.setText(tieude);

        // settext đơn vi thực hiện
        String tieude_1 = "<h4>MY MONYE</h4>\n <p><font color=\"black\">Phiên bản 1.0</font><p> \n" +
                " <p><font color=\"black\">được nhóm SV FPOLY thực hiện</font><p>";
        tv2.setText(android.text.Html.fromHtml(tieude_1,-1));
    }
}