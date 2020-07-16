package com.example.kitchen_app;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainer;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductsToAdd1 extends AppCompatActivity {
    private Button button;
    private EditText editText;
    private DataBaseForProducts databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_to_add1);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setTitle("Добавить продукт в корзину");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final AutoCompleteTextView autoCompleteTextView = findViewById(R.id.autoCompleteTextView);
        autoCompleteTextView.setThreshold(1);
        String[] products = getResources().getStringArray(R.array.products);
        List<String> productList = Arrays.asList(products);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_dropdown_item_1line, productList);
        autoCompleteTextView.setAdapter(adapter);
        editText = (EditText)findViewById(R.id.et1);
        button = (Button)findViewById(R.id.button_set_product1);
        databaseHelper = new DataBaseForProducts(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = autoCompleteTextView.getText().toString();
                String quantity = editText.getText().toString();
                if(!name.equals("") && !quantity.equals("") && databaseHelper.insertDataForBasket(name,Integer.parseInt(quantity))){
                    autoCompleteTextView.setText("");
                    editText.setText("");
                }else{
                    Toast.makeText(ProductsToAdd1.this,"Продукт не был добавлен, попробуйте ещё раз",Toast.LENGTH_SHORT).show();
                    autoCompleteTextView.setText("");
                    editText.setText("");
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
