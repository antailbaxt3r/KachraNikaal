package com.antailbaxt3r.kachranikaal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class EventDetails extends AppCompatActivity {

    private TextView name, date, time, venue;
    private ImageView image;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);

        attachID();

        if (toolbar != null) {

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        }

        FirebaseDatabase.getInstance().getReference().child("events").child(getIntent().getStringExtra("id")).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Event event = dataSnapshot.getValue(Event.class);
                name.setText(event.getName());
                date.setText(event.getDate());
                time.setText(event.getTime());
                venue.setText(event.getVenue());

                Picasso.get().load(event.getImg()).into(image);
                System.out.println(event.getName());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void attachID() {
        name = findViewById(R.id.name_event);
        date = findViewById(R.id.date);
        time = findViewById(R.id.time);
        venue = findViewById(R.id.venue);
        image = findViewById(R.id.image_event_detail);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
}
