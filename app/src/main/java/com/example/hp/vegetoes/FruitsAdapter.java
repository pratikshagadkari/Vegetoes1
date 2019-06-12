package com.example.hp.vegetoes;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class FruitsAdapter extends RecyclerView.Adapter<FruitsAdapter.ViewHolder> {
    Context context;
    ArrayList<FruitsClass> list;
    public FruitsAdapter(Context context, ArrayList<FruitsClass> list){
        this.context=context;
        this.list=list;
    }
    @NonNull
    @Override

    public FruitsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.product_items_layout, viewGroup, false);
        return new FruitsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FruitsAdapter.ViewHolder viewHolder, int i) {
        viewHolder.price.setText(list.get(i).price);
        viewHolder.product.setText(list.get(i).product);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView price,product;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            product=(TextView) itemView.findViewById(R.id.product_name);
            price=itemView.findViewById(R.id.product_description);
        }
    }
}
