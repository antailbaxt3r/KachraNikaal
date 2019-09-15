package com.antailbaxt3r.kachranikaal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class EventsRVAdapter extends RecyclerView.Adapter<EventsViewHolder> {

    Context context;
    private List<Event> eventList;

    public EventsRVAdapter(Context context, List<Event> eventList) {
        this.context = context;
        this.eventList = eventList;
    }

    @NonNull
    @Override
    public EventsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.event_item_layout, parent, false);
        EventsViewHolder viewHolder = new EventsViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull EventsViewHolder holder, int position) {

        Event event = eventList.get(position);
        holder.name.setText(event.getName());
        holder.id.setText(event.getId());
        if (!event.getImg().isEmpty()){
            Picasso.get().load(event.getImg()).into(holder.image);
        }
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }
}
