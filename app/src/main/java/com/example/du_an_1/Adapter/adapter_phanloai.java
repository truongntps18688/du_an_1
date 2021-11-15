package com.example.du_an_1.Adapter;

import android.annotation.SuppressLint;
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
import com.chauthai.swipereveallayout.ViewBinderHelper;
import com.example.du_an_1.Entity.PHANLOAI;
import com.example.du_an_1.R;

import java.util.List;

public class adapter_phanloai extends RecyclerView.Adapter<adapter_phanloai.UserViewHolder>{

    private List<PHANLOAI> phanloaiList;
    Iphanloai listener;
    ViewBinderHelper viewBinderHelper = new ViewBinderHelper();
    public void setData(List<PHANLOAI> list){
        this.phanloaiList = list;
        notifyDataSetChanged();
    }
    public adapter_phanloai(List<PHANLOAI> list, Iphanloai listener){
        this.phanloaiList = list;
        this.listener = listener;
    }
    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_phanloai,parent,false);
        return new UserViewHolder(view);
    }
    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder,  int position) {
        PHANLOAI phanloai = phanloaiList.get(position);
        if(phanloai == null)
            return;
        holder.tvPhanLoai.setText(phanloai.getName());
        holder.img.setImageResource(phanloai.getSrc());
        //nhấn giữ trên item trong recycleview
        holder.ln.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                listener.onClickListener(phanloaiList.get(position),0);
                return false;
            }
        });
        viewBinderHelper.bind(holder.swipeRevealLayout, String.valueOf(phanloaiList.get(position).getId()));

        //xoá
        holder.del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClickListener(phanloaiList.get(position),1);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(phanloaiList != null){
            return phanloaiList.size();
        }
        return 0;
    }

    public class UserViewHolder extends RecyclerView.ViewHolder{
        private LinearLayout ln;
        private TextView tvPhanLoai;
        private ImageView img;
        private SwipeRevealLayout swipeRevealLayout;
        private LinearLayout del;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            ln = itemView.findViewById(R.id.lnPH);
            tvPhanLoai = itemView.findViewById(R.id.tv_phanloai);
            img = itemView.findViewById(R.id.img_phanloai);
            swipeRevealLayout = itemView.findViewById(R.id.SwipeRevealLayout);
            del = itemView.findViewById(R.id.lnDelPhanLoai);
        }
    }
    public interface Iphanloai{
        public void onClickListener(PHANLOAI phanloai,int type);
    }
}
