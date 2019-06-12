package com.example.hp.vegetoes.ViewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hp.vegetoes.Interface.ItemClickListner;
import com.example.hp.vegetoes.R;

public class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{

    public TextView txtProductName,txtProductDescription;
    public ImageView imageView;
    public ItemClickListner listner;


    public ProductViewHolder(@NonNull View itemView) {
        super(itemView);


        txtProductName=itemView.findViewById(R.id.product_name);
        txtProductDescription=itemView.findViewById(R.id.product_description);
    }

    public void setItemClickListner(ItemClickListner listner)
    {
        this.listner=listner;
    }

    @Override
    public void onClick(View v)
    {
        listner.onClick(v,getAdapterPosition(),false);

    }
}
