package com.example.du_an_1.Adapter;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.du_an_1.Entity.PHANLOAI;
import com.example.du_an_1.R;

import java.util.List;

public class adapter_itemphanloai extends RecyclerView.Adapter<adapter_itemphanloai.UserViewHolder> {
    List<PHANLOAI> list;
    IItemimgphanloai listener;

    public adapter_itemphanloai(List<PHANLOAI> list, IItemimgphanloai listener) {
        this.list = list;
        this.listener = listener;
    }


    public void updateData(List<PHANLOAI> list){
        this.list = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public adapter_itemphanloai.UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_phanloai,
                parent,false);
        return new adapter_itemphanloai.UserViewHolder(view);
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder,  int position) {
        holder.img.setImageResource(list.get(position).getSrc());
        holder.tv.setText(list.get(position).getName());

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listener.onClickListener(list.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        if(list.size() > 0){
            return list.size();
        }
        return 0;
    }
    public class UserViewHolder extends RecyclerView.ViewHolder{
        private ImageView img;
        private TextView tv;
        private LinearLayout linearLayout;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img_itemphanloai);
            tv = itemView.findViewById(R.id.tv_phanloai);
            linearLayout = itemView.findViewById(R.id.linear_item_rv_phanloai);
        }
    }
    public interface IItemimgphanloai{
         void onClickListener(PHANLOAI item);
    }
}
