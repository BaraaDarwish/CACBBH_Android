package com.example.baraa.cabbh;



import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {
    private TextView mTextMessage;
    private FrameLayout frameLayout;
    DiabetesResultFragment diabetesResultFragment;
    FSResultFragment fsResultFragment;
    SettingsFragment settingsFragment;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    startFragment(diabetesResultFragment);
                    return true;
                case R.id.navigation_dashboard:
                    startFragment(fsResultFragment);
                    return true;
                case R.id.navigation_notifications:
                    startFragment(settingsFragment);
                    return true;
            }
            return false;
        }
    };

    @Override
    public void onBackPressed() {

            Intent a = new Intent(Intent.ACTION_MAIN);
            a.addCategory(Intent.CATEGORY_HOME);
            a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(a);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Fragment fraggy = new DiabetesResultFragment();
        fragmentTransaction.add(R.id.home_frame, fraggy);
        fragmentTransaction.commit();
        frameLayout = findViewById(R.id.home_frame);
        fsResultFragment = new FSResultFragment();
        diabetesResultFragment = new DiabetesResultFragment();
        settingsFragment = new SettingsFragment();
    }
    public void startFragment(Fragment fragment)
    {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.home_frame,fragment);
        transaction.commit();
    }

}
