package com.antailbaxt3r.kachranikaal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyOrderRVAdapter extends RecyclerView.Adapter<MyOrderViewHolder> {

    Context context;
    private List<ShopItem> itemList;

    public MyOrderRVAdapter(Context context, List<ShopItem> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public MyOrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.order_item_layout, parent, false);
        MyOrderViewHolder viewHolder = new MyOrderViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyOrderViewHolder holder, int position) {

        ShopItem item = itemList.get(position);
        holder.name.setText(item.getName());
        holder.price.setText("₹ " + String.valueOf(item.getPrice()));

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}
