package com.antailbaxt3r.kachranikaal;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class EventsViewHolder extends RecyclerView.ViewHolder {

    TextView name, id;
    ImageView image;

    public EventsViewHolder(@NonNull final View itemView) {
        super(itemView);

        name = itemView.findViewById(R.id.event_name_in_rv);
        image = itemView.findViewById(R.id.event_image_in_rv);
        id = itemView.findViewById(R.id.id_in_event_rv);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(itemView.getContext(), EventDetails.class);
                intent.putExtra("id", id.getText().toString());
                itemView.getContext().startActivity(intent);
            }
        });


    }
}
