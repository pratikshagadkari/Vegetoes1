package com.example.hp.vegetoes;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ExoticVegetables extends AppCompatActivity {
    FirebaseDatabase database;
    ExoticVegetablesAdapter adapter;
    RecyclerView recyclerView;
    ArrayList<ExoticVegetablesClass> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exotic_vegetables);
        database=FirebaseDatabase.getInstance();

        recyclerView=findViewById(R.id.recycler3);
        list=new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        adapter=new ExoticVegetablesAdapter(getApplicationContext(),list);
        database.getReference().child("ExoticVegetables").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                    list.add(new ExoticVegetablesClass(dataSnapshot1.child("Name").getValue().toString(),dataSnapshot1.child("Price").getValue().toString()));
                    adapter.notifyDataSetChanged();
                }
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
