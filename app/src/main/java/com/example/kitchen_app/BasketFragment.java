package com.example.kitchen_app;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.github.clans.fab.FloatingActionMenu;

import java.util.ArrayList;
import java.util.Comparator;

public class BasketFragment extends Fragment {
    private DataBaseForProducts databaseHelper;
    private ArrayList<MyProductBasket> arrayList;
    private RecyclerView recyclerView;
    private RecyclerForBasket recyclerForBasket;
    private View root;
    private FloatingActionMenu floatingActionMenu;
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_basket, container, false);
        databaseHelper = new DataBaseForProducts(getContext());
        recyclerView = (RecyclerView) root.findViewById(R.id.rv1);
        arrayList =  new ArrayList<>();
        recyclerForBasket = new RecyclerForBasket(getContext(),arrayList);
        viewData();
        recyclerForBasket.notifyDataSetChanged();
        floatingActionMenu = root.findViewById(R.id.fabfirst1);
        floatingActionMenu.setOnMenuButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1;
                intent1 = new Intent(getActivity(), ProductsToAdd1.class);
                startActivity(intent1);
            }
        });
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                SQLiteDatabase db = databaseHelper.getWritableDatabase();
                db.delete("Products_Basket","ID=?",new String[]{String.valueOf(arrayList.get(position).getId())});
                db.close();
                arrayList.remove(position);
                recyclerForBasket.notifyDataSetChanged();
            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerView);
        return root;
    }
    public void viewData(){
        Cursor cursor = databaseHelper.viewDataForBasket();
            while (cursor.moveToNext()){
                arrayList.add(new MyProductBasket(cursor.getInt(0),cursor.getString(1),cursor.getInt(2)));
                arrayList.sort(new Comparator<MyProductBasket>() {
                    @Override
                    public int compare(MyProductBasket o1, MyProductBasket o2) {
                        return o1.getProductName().compareTo(o2.getProductName());
                    }
                });
            }
            recyclerForBasket = new RecyclerForBasket(root.getContext(), arrayList);
            recyclerView.setAdapter(recyclerForBasket);
        }
    @Override
    public void onResume() {
        super.onResume();
        arrayList.clear();
        viewData();
    }
}
