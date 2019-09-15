package com.antailbaxt3r.kachranikaal;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.antailbaxt3r.kachranikaal.R;

public class ShopViewHolder extends RecyclerView.ViewHolder {

    TextView name, price;
    ImageView image;

    public ShopViewHolder(@NonNull View itemView) {
        super(itemView);

        name = itemView.findViewById(R.id.shopItem_name);
        image = itemView.findViewById(R.id.shopItem_img);
        price = itemView.findViewById(R.id.shopItem_price);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent detailIntent = new Intent(view.getContext(), OpenDetailShop.class);
                detailIntent.putExtra("name", name.getText().toString());
                view.getContext().startActivity(detailIntent);
            }
        });

    }
}
