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

public class OrganicFruits extends AppCompatActivity {
    FirebaseDatabase database;
    OrganicFruitsAdapter adapter;
    RecyclerView recyclerView;
    ArrayList<OrganicFruitsClass> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organic_fruits);
        database=FirebaseDatabase.getInstance();

        recyclerView=findViewById(R.id.recycler1);
        list=new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        adapter=new OrganicFruitsAdapter(getApplicationContext(),list);
        database.getReference().child("OrganicFruits").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                    list.add(new OrganicFruitsClass(dataSnapshot1.child("Name").getValue().toString(),dataSnapshot1.child("Price").getValue().toString()));
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
