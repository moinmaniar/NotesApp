package com.example.customdialog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class HomePage extends AppCompatActivity {
   // ActivityHomePageBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
   //     binding = ActivityHomePageBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_home_page);

       getSupportFragmentManager().beginTransaction().replace(R.id.bottom_navigation,new HomeFragment()).commit();


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment temp = null;

                switch(item.getItemId())
                {
                    case R.id.nav_home: temp = new HomeFragment();
                    break;

                    case R.id.nav_book: temp = new BookFragment();
                    break;

                    case R.id.nav_cart: temp = new FavorateFragment();
                    break;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,temp).commit();
                return true;
            }
        });


    }
}