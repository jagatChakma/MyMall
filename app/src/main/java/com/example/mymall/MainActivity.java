package com.example.mymall;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

//import static com.example.mymall.RegisterActivity.setSignUpFragment;

public class MainActivity extends AppCompatActivity {
    private ImageView actionBarLogo;

    private AppBarConfiguration mAppBarConfiguration;

//    private static final int HOME_FRAGMENT = 0;
//    private static final int CART_FRAGMENT = 1;
    private static int currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        actionBarLogo = findViewById(R.id.actionBar_logo);

//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_my_home,
                R.id.nav_my_order, R.id.nav_my_rewards, R.id.nav_my_cart,
                R.id.nav_my_wishlist, R.id.nav_my_account
                //.....Border
                )
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;

        //      This is the menu item action method
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.main_search_icom){
            Toast.makeText(this, "Tuch- om search", Toast.LENGTH_SHORT).show();
        }else if (id == R.id.main_notification_icon){
            Toast.makeText(this, "Tuch- om notification", Toast.LENGTH_SHORT).show();
        }else if (id == R.id.main_cart_icon){

            final Dialog signInDialog = new Dialog(MainActivity.this);
            signInDialog.setContentView(R.layout.sign_in_dialog);
            signInDialog.setCancelable(true);
            signInDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            Button dialogSignIn = signInDialog.findViewById(R.id.cancel_button);
            Button dialogSignUp = signInDialog.findViewById(R.id.ok_button);

            final Intent regisIntent = new Intent(MainActivity.this, RegisterActivity.class);

            dialogSignIn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    signInDialog.dismiss();
//                    setSignUpFragment = false;
                    startActivity(regisIntent);
                }
            });
            dialogSignUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    signInDialog.dismiss();
//                    setSignUpFragment = true;
                    startActivity(regisIntent);
                }
            });
            signInDialog.show();
        }
        return super.onOptionsItemSelected(item);
    }
}
