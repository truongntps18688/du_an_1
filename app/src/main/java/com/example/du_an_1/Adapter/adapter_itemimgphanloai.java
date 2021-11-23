package com.example.du_an_1.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.chauthai.swipereveallayout.ViewBinderHelper;
import com.example.du_an_1.Entity.PHANLOAI;
import com.example.du_an_1.R;

import java.util.List;

public class adapter_itemimgphanloai extends RecyclerView.Adapter<adapter_itemimgphanloai.UserViewHolder> {
    List<Integer> list;
    IItemimgphanloai listener;

    public adapter_itemimgphanloai(List<Integer> list,IItemimgphanloai listener) {
        this.list = list;
        this.listener = listener;
    }


    public void updateData(List<Integer> list){
        this.list = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public adapter_itemimgphanloai.UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_imgphanloai,
                parent,false);
        return new adapter_itemimgphanloai.UserViewHolder(view);
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder,  int position) {
        holder.img.setImageResource(list.get(position));
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
        private LinearLayout linearLayout;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img_itemphanloai);
            linearLayout = itemView.findViewById(R.id.linear_imgphanloai);

        }
    }
    public interface IItemimgphanloai{
        public void onClickListener(int anhID);
    }
}
