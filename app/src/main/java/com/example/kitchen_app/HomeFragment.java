package com.example.kitchen_app;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;


import com.github.clans.fab.FloatingActionMenu;

import java.util.ArrayList;
import java.util.Comparator;

public class HomeFragment extends Fragment {
    private FloatingActionMenu floatingActionMenu;
    private DataBaseForProducts databaseHelper;
    private ArrayList<MyProduct> arrayList;
    private RecyclerView recyclerView;
    private RecyclerForHome adapter1;
    private View root;
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_home, container, false);
        databaseHelper = new DataBaseForProducts(getContext());
        arrayList =  new ArrayList<>();
        recyclerView = (RecyclerView) root.findViewById(R.id.rv2);
        floatingActionMenu = root.findViewById(R.id.fabfirst1);
        viewData1();
        floatingActionMenu.setOnMenuButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1;
                intent1 = new Intent(getActivity(), ProductsData.class);
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
                db.delete("Products_Home","ID1=?",new String[]{String.valueOf(arrayList.get(position).getId())});
                db.close();
                arrayList.remove(position);
                adapter1.notifyDataSetChanged();
            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerView);
        return root;
    }
    public void viewData1(){
        Cursor cursor = databaseHelper.viewDataForHome();
            while (cursor.moveToNext()) {
                arrayList.add(new MyProduct(cursor.getString(1), cursor.getLong(2),cursor.getInt(0)));
                arrayList.sort(new Comparator<MyProduct>() {
                    @Override
                    public int compare(MyProduct o1, MyProduct o2) {
                        if(o1.getProductDate() > o2.getProductDate()){
                            return 1;
                        }else if(o1.getProductDate() < o2.getProductDate()){
                            return -1;
                        }else{
                            return 0;
                        }
                    }
                });
            }
            adapter1 = new RecyclerForHome(root.getContext(), arrayList);
            recyclerView.setAdapter(adapter1);
            adapter1.notifyDataSetChanged();
        }

    @Override
    public void onResume() {
        super.onResume();
        arrayList.clear();
        viewData1();
    }
}
