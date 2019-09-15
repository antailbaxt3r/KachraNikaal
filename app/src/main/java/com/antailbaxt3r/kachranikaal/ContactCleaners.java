package com.antailbaxt3r.kachranikaal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ContactCleaners extends AppCompatActivity {

    private Toolbar toolbar;
    private CardView truck, cleaner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_cleaners);

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

        truck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callNo(1);
            }
        });

        cleaner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callNo(2);
            }
        });


    }

    private void callNo(final int i) {

        FirebaseDatabase.getInstance().getReference().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String noString;
                if (i == 1){
                    noString = dataSnapshot.child("truck").getValue().toString();
                }else{
                    noString = dataSnapshot.child("cleaner").getValue().toString();
                }


                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:1234567890" ));
                if (checkSelfPermission(Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {

                    return;
                }
                startActivity(callIntent);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void attachID() {

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        truck = findViewById(R.id.truck_btn);
        cleaner = findViewById(R.id.cleaner_btn);
    }
}
