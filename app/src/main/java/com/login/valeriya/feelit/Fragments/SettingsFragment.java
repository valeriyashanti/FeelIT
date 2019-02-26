package com.login.valeriya.feelit.Fragments;

import android.app.Application;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.login.valeriya.feelit.R;


public class SettingsFragment extends Fragment implements View.OnClickListener {

    private Button mButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_settings, container, false);

        mButton = v.findViewById(R.id.btn_exit);
        mButton.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        System.exit(1);
    }
}
