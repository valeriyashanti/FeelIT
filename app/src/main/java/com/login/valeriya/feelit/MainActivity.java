package com.login.valeriya.feelit;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.login.valeriya.feelit.Fragments.ProfileFragment;
import com.login.valeriya.feelit.Fragments.SettingsFragment;
import com.login.valeriya.feelit.Fragments.SubscriptionFragment;
import com.login.valeriya.feelit.Utils.User;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{
    public static final String TAG = "MainActivity";
    private FirebaseAuth mAuth;

//    private Button mLogoutButton;

    public static User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: start");

        mAuth = FirebaseAuth.getInstance();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null)
        {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }

        user = new User();

//        mLogoutButton-findViewById(R.id.btn_logout)

        BottomNavigationView navigationView = (BottomNavigationView) findViewById(R.id.navigation);
        navigationView.setOnNavigationItemSelectedListener(this);


        toFragment(new ProfileFragment());

//        mLogoutButton = findViewById(R.id.btn_logout);
//        mLogoutButton.setOnClickListener(v -> {
//            mAuth.signOut();
//            Intent intent = new Intent(v.getContext(), LoginActivity.class);
//            startActivity(intent);
//            finish();
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)  {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings){
            Intent intent = new Intent (MainActivity.this, AboutActivity.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.action_exit){
            mAuth.signOut();
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean toFragment(Fragment fragment){

        if (fragment != null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_linear, fragment).commit();

            return true;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        Fragment fragment = null;

        switch (item.getItemId()){
            case R.id.item_profile:
                fragment = new ProfileFragment();
                break;
            case R.id.item_subscription:
                fragment = new SubscriptionFragment();
                break;
            case R.id.item_settings:
                fragment = new SettingsFragment();
                break;
        }
        return toFragment(fragment);
    }

}
