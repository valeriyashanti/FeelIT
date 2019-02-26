package com.login.valeriya.feelit.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.login.valeriya.feelit.MainActivity;
import com.login.valeriya.feelit.ProfileFillActivity;
import com.login.valeriya.feelit.R;
import com.login.valeriya.feelit.SubscriptionActivity;
import com.login.valeriya.feelit.Utils.Parsing;

import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SubscriptionFragment extends Fragment implements CardFragment.OnActionListener , View.OnClickListener {
    public static final String TAG = "SubscriptionFragment";



    private Button mToSubscriptions;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_subscription, container, false);

        mToSubscriptions = v.findViewById(R.id.ToSubscriptions);
        mToSubscriptions.setOnClickListener(this);

        return v;
    }

    @Override
    public void onAction(int id) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ToSubscriptions:
                Log.d(TAG, "onClick: " + Parsing.getUrlPackage(MainActivity.user));
                MainActivity.user.setUid(FirebaseAuth.getInstance().getCurrentUser().getUid());
                new GetPackages().execute(Parsing.getUrlPackage(MainActivity.user));
                break;
        }
    }


    private class GetPackages extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {

            Log.d(TAG, "doInBackground: ");

            String data = "";

            HttpURLConnection httpURLConnection = null;
            try {

                httpURLConnection = (HttpURLConnection) new URL(params[0]).openConnection();
                httpURLConnection.setRequestMethod("POST");

                httpURLConnection.setDoOutput(true);

                InputStream in = httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(in);

                int inputStreamData = inputStreamReader.read();
                while (inputStreamData != -1) {
                    char current = (char) inputStreamData;
                    inputStreamData = inputStreamReader.read();
                    data += current;
                }
                return data;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
            }

            return data;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Intent intent = new Intent(getActivity().getApplicationContext(), SubscriptionActivity.class);
            intent.putExtra("pack", result);
            startActivity(intent);

        }
    }
}
