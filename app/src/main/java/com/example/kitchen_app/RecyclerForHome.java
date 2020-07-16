package com.example.kitchen_app;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Date;

import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class RecyclerForHome extends RecyclerView.Adapter<RecyclerForHome.ViewHolder> {
    private LayoutInflater inflater;
    private ArrayList<MyProduct> products;
    private Date data12;
    private long datefor;
    private long dateto;
    private MyProduct product;

    RecyclerForHome(Context context, ArrayList<MyProduct> products) {
        this.products = products;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerForHome.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.adapter_for_home_fragment, parent, false);
        return new ViewHolder(view);
    }

    {
        try {
            String date1 = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
            Date date12;
            date12 = new SimpleDateFormat("dd-MM-yyyy").parse(date1);
            datefor = date12.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onBindViewHolder(RecyclerForHome.ViewHolder holder, int position) {
        MyProduct product = products.get(position);
        holder.nameView.setText(product.getProductName());
        holder.dateView.setText((product.getProductDate() - datefor) / (24 * 60 * 60 * 1000) + " дн.");
    }


    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final TextView nameView, dateView;

        ViewHolder(View view) {
            super(view);
            nameView = (TextView) view.findViewById(R.id.tv_product_name);
            dateView = (TextView) view.findViewById(R.id.tv_product_date);
        }
    }

}
