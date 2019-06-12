package com.example.hp.vegetoes;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class OfferAdapter extends RecyclerView.Adapter<OfferAdapter.ViewHolder> {
    Context context;
    Dialog dialog;
   


    ArrayList<OfferClass> list;
    public OfferAdapter(Context context, ArrayList<OfferClass> list){
        this.context=context;
        this.list=list;
    }
    @NonNull
    @Override

    public OfferAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.product_items_layout, viewGroup, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i ) {
        viewHolder.price.setText(list.get(i).price);
        viewHolder.product.setText(list.get(i).product);
        @NonNull final OfferClass model=new OfferClass();


       // FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
        viewHolder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos=viewHolder.getAdapterPosition();
                Intent intent= new Intent(context, product_details.class);
                intent.putExtra("pid",list.get(pos).getPid());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView price,product;
        Button add;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            product=(TextView) itemView.findViewById(R.id.product_name);
            price=itemView.findViewById(R.id.product_description);
            add=itemView.findViewById(R.id.add);
        }
    }
   /* public void open(View view){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Offer.class);
        alertDialogBuilder.setMessage("Are you sure, You wanted to make decision");
        alertDialogBuilder.setPositiveButton("yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

        alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }*/
}
