package com.antailbaxt3r.kachranikaal;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyOrderViewHolder extends RecyclerView.ViewHolder {

    TextView name, price;

    public MyOrderViewHolder(@NonNull View itemView) {
        super(itemView);

        name = itemView.findViewById(R.id.name_my_order_rv_layout);
        price = itemView.findViewById(R.id.price_my_order_rv_layout);
    }
}
