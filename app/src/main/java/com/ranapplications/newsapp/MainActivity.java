package com.ranapplications.newsapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.ranapplications.newsapp.discoverPage.DiscoverPageFragment;
import com.ranapplications.newsapp.global.Global;
import com.ranapplications.newsapp.mainPage.MainPageFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements GetArrayNews.GetArrayNewsCallBack {

    private ArrayList<NewsClass> newsClasses;
    BottomNavigationView navigation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading_page);


        new GetArrayNews(Global.apiUrl, this);
    }

    @Override
    public void onDone(ArrayList<NewsClass> newsClasses) {
        this.newsClasses= newsClasses;
        setContentView(R.layout.activity_main);

        setUpBottomNavigationView();
    }

    private void setUpBottomNavigationView(){
        navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        Fragment fragment = new MainPageFragment(newsClasses);
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            if (navigation.getSelectedItemId() == item.getItemId()){
                return true;
            }

            switch (item.getItemId()) {
                case R.id.navigationMain:
                    fragment = new MainPageFragment(newsClasses);
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment).commit();
                    return true;
                case R.id.navigationDiscover:
                    fragment = new DiscoverPageFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment).commit();
                    return true;
            }
            return false;
        }
    };
}
