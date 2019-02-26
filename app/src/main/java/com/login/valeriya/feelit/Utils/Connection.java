package com.login.valeriya.feelit.Utils;

import android.app.Application;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.login.valeriya.feelit.MainActivity;
import com.login.valeriya.feelit.SubscriptionActivity;

import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static com.login.valeriya.feelit.MainActivity.user;

public class Connection {

    private static String TAG = "Connection";

    private static FirebaseAuth mAuth;
    private static CollectionReference mUserRef;

    public static void registerUser(User user)
    {
        Log.d(TAG, "registerUser: " + Parsing.getUrl(user));
        new RegisterUser().execute(Parsing.getUrl(user));
    }


    private static  class RegisterUser extends AsyncTask<String, Void, String> {

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
            Log.d(TAG, "onPostExecute: ");
            super.onPostExecute(result);
            Log.d(TAG, result); // this is expecting a response code to be sent from your server upon receiving the POST data
//            output.setText(result);
        }
    }

    public static void updateDataLoaded(boolean state)
    {

        Log.d(TAG, "updateDataLoaded: ");


        Map<String, Object> newUser= new HashMap<>();
        newUser.put("dataIsLoaded", state);
        newUser.put("sex", user.getSex());
        newUser.put("year", user.getBirth_year());
        newUser.put("lifestyle", user.getLifestyle());
        newUser.put("foodstyle", user.getFoodstyle());
        newUser.put("weight", user.getWeight());
        newUser.put("height", user.getHeight());

        mAuth = FirebaseAuth.getInstance();
        mUserRef = FirebaseFirestore.getInstance().collection("users");


        mUserRef.document(mAuth.getCurrentUser().getUid())
                .update(newUser)
                .addOnSuccessListener(aVoid -> Log.d(TAG, "onSuccess: successfully loaded data to Firestore"));

    }

}
