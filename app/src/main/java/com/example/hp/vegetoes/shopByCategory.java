package com.example.hp.vegetoes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class shopByCategory extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_by_category);
    }
    public void fruits(View view) {
        Intent i=new Intent(this,Fruits.class);
        startActivity(i);
    }

    public void organic_fruits(View view) {
        Intent i=new Intent(this,OrganicFruits.class);
        startActivity(i);
    }

    public void Vegetables(View view) {
        Intent i=new Intent(this,Vegetables.class);
        startActivity(i);
    }

    public void Exotic_Vegetables(View view) {
        Intent i=new Intent(this,ExoticVegetables.class);
        startActivity(i);
    }
}
