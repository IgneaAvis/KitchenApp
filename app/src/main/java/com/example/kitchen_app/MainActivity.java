package com.example.kitchen_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class MainActivity extends AppCompatActivity {

    private ActionBar actionBar;
    private BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.nav_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(navlis);
        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new HomeFragment()).commit();
        actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle("Продукты");
    }


    @Override  //Блок для трех точек
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.appbarmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            case R.id.settings: //Настройки
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navlis = new BottomNavigationView.OnNavigationItemSelectedListener() {
        private boolean shown;

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment selectfrag = null;
            actionBar = getSupportActionBar();
            switch (menuItem.getItemId()) {
                case R.id.navigation_products:
                    actionBar.setTitle("Продукты");
                    selectfrag = new HomeFragment();
                    break;
                case R.id.navigation_basket:
                    actionBar.setTitle("Корзина");
                    selectfrag = new BasketFragment();
                    break;
                case R.id.navigation_recipes:
                    actionBar.setTitle("Рецепты");
                    selectfrag = new SearchFragment();
                    break;
            }
            assert selectfrag != null;
            getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, selectfrag).commit();
            return true;
        }
    };
}
