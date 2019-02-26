package com.login.valeriya.feelit;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.login.valeriya.feelit.Adapter.SubAdapter;
import com.login.valeriya.feelit.Fragments.SubFragment;

public class SubscriptionActivity extends AppCompatActivity implements SubFragment.OnActionListener {

    ViewPager pager;
    public static String pack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscription);

        pager = (ViewPager) findViewById(R.id.pager);

        pager.setAdapter(new SubAdapter(this, getSupportFragmentManager()));
        pager.setPageMargin((int) getResources().getDimension(R.dimen.card_padding) / 4);
        pager.setOffscreenPageLimit(1);

        pack = getIntent().getStringExtra("pack");
    }

    public static void open(Context context) {
        context.startActivity(new Intent(context, ProfileFillActivity.class));
    }

    @Override
    public void onAction(int id) {
        switch (id) {
            case 0:
                pager.setCurrentItem(id+1);
                this.finish();
                break;

        }
    }

    @Override
    public void onClick(View v) {
    }

}
