package com.example.kitchen_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerForBasket extends RecyclerView.Adapter<RecyclerForBasket.ViewHolder> {
    private LayoutInflater inflater;
    private ArrayList<MyProductBasket> products;

    RecyclerForBasket(Context context, ArrayList<MyProductBasket> products) {
        this.products = products;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public RecyclerForBasket.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.adapter_for_basket_fragment, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(RecyclerForBasket.ViewHolder holder, int position) {
        MyProductBasket product = products.get(position);
        holder.nameView.setText(product.getProductName());
        holder.quantityView.setText(product.getQuantity() + " шт.");
    }


    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
         TextView nameView;
         TextView quantityView;

        ViewHolder(View view){
            super(view);
            nameView = (TextView) view.findViewById(R.id.tv_basket_name);
            quantityView = (TextView) view.findViewById(R.id.tv_quantity);
        }
    }
}
