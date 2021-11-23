package com.example.du_an_1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.example.du_an_1.Entity.GIAODICH;
import com.example.du_an_1.R;

import java.util.ArrayList;
import java.util.List;

public class Thong_Ke_adapter extends RecyclerView.Adapter<Thong_Ke_adapter.ViewHolder> {
    private Context context;
    private ArrayList<Integer> thu;
    private ArrayList<Integer> chi;
    private int nam;
    public Thong_Ke_adapter(int _nam, ArrayList<Integer> _thu, ArrayList<Integer> _chi, Context _context){
        nam = _nam;
        thu = _thu;
        chi = _chi;
        context = _context;
    }
    public Thong_Ke_adapter(ArrayList<Integer> _thu,Context _context){
        thu = _thu;
        context = _context;
    }
    @NonNull
    @Override
    public Thong_Ke_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_thong_ke,parent,false);
        return new Thong_Ke_adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TextView tv = holder.tv_thu;
        tv.setText(thu.get(position) +"");

    }

    @Override
    public int getItemCount() {
        return thu.size();
    }
    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_thang,tv_thu,tv_chi,tv_ruiro;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_thang = itemView.findViewById(R.id.thang_tk);
            tv_chi = itemView.findViewById(R.id.chi_tieu_tk);
            tv_thu = itemView.findViewById(R.id.thu_nhap_tk);
            tv_ruiro = itemView.findViewById(R.id.rui_ro_tk);
        }
    }
}
