package com.example.du_an_1.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.chauthai.swipereveallayout.ViewBinderHelper;
import com.example.du_an_1.DAO.GiaoDich_DAO;
import com.example.du_an_1.DAO.PhanLoai_DAO;
import com.example.du_an_1.Entity.GIAODICH;
import com.example.du_an_1.Entity.PHANLOAI;
import com.example.du_an_1.R;

import java.text.SimpleDateFormat;
import java.util.List;

public class adapter_giaodich extends RecyclerView.Adapter<adapter_giaodich.UserViewHolder>{

    private List<GIAODICH> list;
    Context context;
    Igiaodich listener;
    ViewBinderHelper viewBinderHelper = new ViewBinderHelper();

    public void setData(List<GIAODICH> list){
        this.context = context;
        this.list = list;
        notifyDataSetChanged();
    }
    public adapter_giaodich(Context context,List<GIAODICH> list, Igiaodich listener){
        this.list = list;
        this.context = context;
        this.listener = listener;
    }
    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_giaodich,parent,false);
        return new UserViewHolder(view);
    }
    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder,  int position) {
        PhanLoai_DAO phanLoai_dao = new PhanLoai_DAO(context);
        GIAODICH giaodich = list.get(position);
        if(giaodich == null)
            return;


        holder.tvTien.setText(giaodich.getTien()+"");
        holder.tvGio.setText(giaodich.getGio());
        holder.tvNgay.setText(new SimpleDateFormat("dd-mm-YYYY").format(giaodich.getNgay()));
        holder.img.setImageResource(phanLoai_dao.getAnh(giaodich.getPhan_loai_id()));
        //nhấn giữ trên item trong recycleview
        holder.ln.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                listener.onClickListener(list.get(position),0);
                return false;
            }
        });
        viewBinderHelper.bind(holder.swipeRevealLayout, String.valueOf(list.get(position).getId()));

        //xoá
        holder.del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClickListener(list.get(position),1);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(list != null){
            return list.size();
        }
        return 0;
    }

    public class UserViewHolder extends RecyclerView.ViewHolder{
        private LinearLayout ln;
        private TextView tvGio,tvNgay,tvTien;
        private ImageView img;
        private SwipeRevealLayout swipeRevealLayout;
        private LinearLayout del;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            ln = itemView.findViewById(R.id.lnPH);
            tvGio = itemView.findViewById(R.id.tvGio);
            tvNgay = itemView.findViewById(R.id.tvNgay);
            tvTien = itemView.findViewById(R.id.tvTien);
            img = itemView.findViewById(R.id.imggd);
            ln = itemView.findViewById(R.id.ln_rv_giaodich);
            swipeRevealLayout = itemView.findViewById(R.id.SwipeRevealLayout);
            del = itemView.findViewById(R.id.lnDelPhanLoai);
        }
    }
    public interface Igiaodich{
         void onClickListener(GIAODICH giaodich,int type);
    }
}
