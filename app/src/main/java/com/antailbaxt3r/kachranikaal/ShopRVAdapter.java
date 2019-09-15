package com.antailbaxt3r.kachranikaal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ShopRVAdapter extends RecyclerView.Adapter<ShopViewHolder> {

    Context context;
    private List<ShopItem> itemList;

    ShopRVAdapter(Context context, List<ShopItem> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ShopViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.shop_item_layout, parent, false);
        ShopViewHolder viewHolder = new ShopViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ShopViewHolder holder, int position) {

        ShopItem item = itemList.get(position);
        holder.name.setText(item.getName());
        holder.price.setText("₹ " + String.valueOf(item.getPrice()));
        if (!item.getImg().equals("")) {
            Picasso.get().load(item.getImg()).into(holder.image);
        }

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}
