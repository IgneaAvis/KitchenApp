package com.example.kitchen_app;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class ProductsData extends AppCompatActivity  {
    private AutoCompleteTextView autoCompleteTextView;
    private Button button;
    private DataBaseForProducts databaseHelper;
    public Long dif;
    private Date date12;
    private Date date11;
    public Long datefor;
    private CalendarView calendarView;
    private DataBaseForProducts databaseHelper1;
    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_with_expiration_date);
        getSupportActionBar().setTitle("Добавить продукт");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.autotv);
        autoCompleteTextView.setThreshold(1);
        String[] products = getResources().getStringArray(R.array.products);
        List<String> productList = Arrays.asList(products);
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, productList);
        autoCompleteTextView.setAdapter(adapter);
        calendarView = (CalendarView) findViewById(R.id.calendarView_product);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                int mYear = year;
                int mMonth = month;
                int mDay = dayOfMonth;
                String selectedDate = new StringBuilder().append(mDay)
                        .append("-").append(mMonth + 1).append("-").append(mYear)
                        .append(" ").toString();
                try {
                     date11 = new SimpleDateFormat("dd-MM-yyyy").parse(selectedDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                 String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
                try {
                    date12 = new SimpleDateFormat("dd-MM-yyyy").parse(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                  datefor = date11.getTime();
                  if(date11.equals(date12)){
                      try {
                          Toast.makeText(ProductsData.this,"Выбрана текущая дата!",Toast.LENGTH_SHORT).show();
                      }catch (Exception e){
                          throw e;
                      }

                  }
                  dif = (date11.getTime() - date12.getTime()) / (24 * 60 * 60 * 1000);
            }
        });
        button = (Button) findViewById(R.id.button_set_product);
        databaseHelper = new DataBaseForProducts(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = autoCompleteTextView.getText().toString();
                if(!name.equals("") && date11.after(date12) && databaseHelper.insertDataForHome(name,datefor)){
                    autoCompleteTextView.setText("");
                }else{
                    Toast.makeText(ProductsData.this,"Продукт не был добавлен, попробуйте ещё раз",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

