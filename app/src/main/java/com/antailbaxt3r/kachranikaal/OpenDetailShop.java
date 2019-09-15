package com.antailbaxt3r.kachranikaal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class OpenDetailShop extends AppCompatActivity {


    private Toolbar toolbar;
    private ImageView image;
    private TextView name, price;
    private Button addToOrder;
    private DatabaseReference itemReference, userReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_detail_shop);

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

        itemReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                name.setText(dataSnapshot.child("name").getValue().toString());
                price.setText("₹ " + dataSnapshot.child("price").getValue().toString());

                if (!dataSnapshot.child("img").getValue().toString().isEmpty()){
                    Picasso.get().load(dataSnapshot.child("img").getValue().toString()).into(image);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        addToOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                userReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if (dataSnapshot.child("order").child(name.getText().toString()).exists()){
                            Toast.makeText(OpenDetailShop.this, "Already In Order", Toast.LENGTH_SHORT).show();
                        }else{
                            userReference.child("order").child(name.getText().toString()).child("name").setValue(name.getText().toString());

                            itemReference.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot2) {
                                    userReference.child("order").child(name.getText().toString()).child("img").setValue(dataSnapshot2.child("img").getValue().toString());
                                    userReference.child("order").child(name.getText().toString()).child("price").setValue(Integer.parseInt(dataSnapshot2.child("price").getValue().toString()));
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });


                            Toast.makeText(OpenDetailShop.this, "Added To Order", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });

    }

    private void attachID() {

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        name = findViewById(R.id.name_open_detail);
        price = findViewById(R.id.price_open_detail);
        image = findViewById(R.id.image_open_detail);
        addToOrder = findViewById(R.id.button_order_add);

        itemReference = FirebaseDatabase.getInstance().getReference().child("shop").child(getIntent().getStringExtra("name"));
        userReference = FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
    }
}
