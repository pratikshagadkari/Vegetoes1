package com.example.hp.vegetoes;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class product_details extends AppCompatActivity {
    Button addToCartBtn;
    ImageView productImage;
    ElegantNumberButton numberButton;
    TextView productName,productPrice;
    String productID=" ";
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        Intent i=getIntent();
        productID = i.getStringExtra("pid");
        //productID=getIntent().getStringExtra("pid");
        database=FirebaseDatabase.getInstance();

        addToCartBtn=(Button) findViewById(R.id.pd_add_to_cart);
        numberButton=(ElegantNumberButton)findViewById(R.id.number_btn);
        productName=(TextView)findViewById(R.id.product_name_details);
        productPrice=(TextView)findViewById(R.id.product_price_details);

        getProductDetails(productID);
        }
    private void getProductDetails(String productID)
    {


            DatabaseReference productsRef=database.getReference().child("Offers");
            productsRef.child(productID).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot.exists())
                    {
                        OfferClass offerClass=dataSnapshot.getValue(OfferClass.class);
                        productName.setText(offerClass.getProduct());
                        productPrice.setText(offerClass.getPrice());
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }



   /* private void addingToCartList() {
        String saveCurrentTime,saveCurrentDate;
        Calendar callForDate=Calendar.getInstance();
        SimpleDateFormat currentDate=new SimpleDateFormat("MMM dd,yyyy");
        saveCurrentDate=currentDate.format(callForDate.getTime());
        SimpleDateFormat currentTime=new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime=currentTime.format(callForDate.getTime());

        DatabaseReference cartListRef=FirebaseDatabase.getInstance().getReference().child("Cart List");

        final HashMap<String,Object > cartMap=new HashMap<>();
        cartMap.put("pid",productID);
        cartMap.put("Name",productName.getText().toString());
        cartMap.put("Price",productPrice.getText().toString());
        cartMap.put("Date",saveCurrentDate);
        cartMap.put("Time",saveCurrentTime);
        cartMap.put("Quantity",numberButton.getNumber());

        cartListRef.child("User view");//start frm here
    }*/


}
