package com.login.valeriya.feelit.Adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.login.valeriya.feelit.Fragments.CardFragment;
import com.login.valeriya.feelit.Fragments.SubFragment;

public class SubAdapter extends FragmentStatePagerAdapter {

    private Context context;

    public SubAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        String id = null;

        return SubFragment.newInstance(position, id);
    }

    @Override
    public int getCount() {
        return 1;
    }
}
