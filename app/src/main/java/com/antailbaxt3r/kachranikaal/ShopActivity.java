package com.antailbaxt3r.kachranikaal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ShopActivity extends AppCompatActivity {

    public DatabaseReference shopReference;
    public RecyclerView recyclerView;
    public RecyclerView.Adapter adapter;
    private Toolbar toolbar;
    public List<ShopItem> shopItemList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

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

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        System.out.println(shopItemList);
        shopReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot shot : dataSnapshot.getChildren()){
                    ShopItem item = shot.getValue(ShopItem.class);
                    shopItemList.add(item);
                    System.out.println(item.getName());
                }

                System.out.println(shopItemList);
                adapter = new ShopRVAdapter(getApplicationContext(), shopItemList);
                adapter.notifyDataSetChanged();
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        System.out.println(shopItemList);
    }

    private void attachID() {

        recyclerView = findViewById(R.id.shop_rv);
        shopReference = FirebaseDatabase.getInstance().getReference().child("shop");
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }
}
