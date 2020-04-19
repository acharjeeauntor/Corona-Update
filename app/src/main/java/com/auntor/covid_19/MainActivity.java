package com.auntor.covid_19;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Adapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import net.yslibrary.android.keyboardvisibilityevent.util.UIUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    private Toolbar toolbar;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // getSupportActionBar().setTitle("Corona Update Data");



        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new DeashboardFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_dashboard);
        }
    }

//TODO: OUT of OnCreate
    //TODO:Methods Implementation


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_dashboard:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new DeashboardFragment()).commit();
                break;
            case R.id.nav_feedback:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new FeedbackFragment()).commit();
                break;
            case R.id.nav_bangladesh:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new BangladeshFragment()).commit();
                break;
            case R.id.nav_information:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new InformationFragment()).commit();
                break;
            case R.id.nav_about:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new AboutFragmentActivity()).commit();
                break;
            case R.id.nav_share:
                shareLink();
                break;

        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }




    private void shareLink() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");

        String subject = "App Name: MyBudget";
        String body = "this is package name of the app:\n com.auntor.mybudget";

        intent.putExtra(Intent.EXTRA_SUBJECT,subject);
        intent.putExtra(Intent.EXTRA_TEXT,body);

        startActivity(Intent.createChooser(intent,"share with"));
    }
}



