package com.login.valeriya.feelit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.login.valeriya.feelit.Adapter.PagerAdapter;
import com.login.valeriya.feelit.Fragments.CardFragment;
import com.login.valeriya.feelit.Fragments.ProfileFragment;
import com.login.valeriya.feelit.Utils.Connection;

public class ProfileFillActivity extends AppCompatActivity implements CardFragment.OnActionListener{


    ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        pager = (ViewPager) findViewById(R.id.pager);

        pager.setAdapter(new PagerAdapter(this, getSupportFragmentManager()));
        pager.setPageMargin((int) getResources().getDimension(R.dimen.card_padding) / 4);
        pager.setOffscreenPageLimit(4);

    }

    public static void open(Context context) {
        context.startActivity(new Intent(context, ProfileFillActivity.class));
    }

    @Override
    public void onAction(int id) {
        switch (id) {
            case 0:
                pager.setCurrentItem(id+1);
                break;
            case 1:
                pager.setCurrentItem(id+1);
                break;
            case 2:
                pager.setCurrentItem(id+1);
                break;
            case 3:
                pager.setCurrentItem(id+1);
                break;
            case 4:
                pager.setCurrentItem(id+1);
                break;
            case 5:
                Connection.registerUser(MainActivity.user);
                Connection.updateDataLoaded(true);
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                this.finish();
                break;
        }
    }

    @Override
    public void onClick(View v) {
    }


}
