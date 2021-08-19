package com.example.myinstagramclone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.graphics.Camera;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.myinstagramclone.adaptor.FragmentPagerAdaptor;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Initializing
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        ViewPager2 viewPagerMain = findViewById(R.id.viewpagerMain);
        //Initializing Fragments
        FragmentPagerAdaptor fragmentPagerAdaptor = new FragmentPagerAdaptor(getSupportFragmentManager(),this.getLifecycle());
        viewPagerMain.setAdapter(fragmentPagerAdaptor);
        viewPagerMain.setUserInputEnabled(false);

        //Listener for ButtonNavigation
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
                switch (item.getItemId()){
                    //Make Fragments switching with ButtonNavigation
                    case R.id.actionHome:
                        viewPagerMain.setCurrentItem(0);
                        break;
                    case R.id.actionSearch:
                        viewPagerMain.setCurrentItem(1);
                        break;
                    case R.id.actionLikes:
                        viewPagerMain.setCurrentItem(2);
                        break;
                    case R.id.actionProfile:
                        viewPagerMain.setCurrentItem(3);
                }
                return true;
            }
        });

    }
}