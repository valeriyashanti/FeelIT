package com.login.valeriya.feelit.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.login.valeriya.feelit.MainActivity;
import com.login.valeriya.feelit.ProfileFillActivity;
import com.login.valeriya.feelit.R;
import com.login.valeriya.feelit.Utils.Questions;
import com.login.valeriya.feelit.Utils.User;


public class ProfileFragment extends Fragment implements CardFragment.OnActionListener , View.OnClickListener {



    private Button mGetProfileFilled;

    private Button mGetRefresh;

    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private DocumentReference userRef;

    private String name;
    public static boolean isDataKnown;

    private User user;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        userRef = FirebaseFirestore.getInstance().collection("users").document(firebaseUser.getUid());

        userRef.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot == null) {
                            return;
                        } else {
                            name = documentSnapshot.getString("name");
                            isDataKnown = documentSnapshot.getBoolean("dataIsLoaded");
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                    }
                });

        user = MainActivity.user;

        View v = null;

        if (!isDataKnown) {
            v = inflater.inflate(R.layout.fragment_profile, container, false);

            mGetProfileFilled = v.findViewById(R.id.getProfileFilled);
            mGetProfileFilled.setOnClickListener(this);
            mGetRefresh = v.findViewById(R.id.getRefresh);
            mGetRefresh.setOnClickListener(this);

        }
        else
        {
            v = inflater.inflate(R.layout.fragment_profile_filled, container, false);


            TextView textName = v.findViewById(R.id.textProfileName);
            TextView textSex = v.findViewById(R.id.textProfileSex);
            TextView textBirthYear = v.findViewById(R.id.textProfileYear);
            TextView textLifestyle = v.findViewById(R.id.textProfileLifestyle);
            TextView textFoodstyle = v.findViewById(R.id.textProfileFoodstyle);
            TextView textWeight = v.findViewById(R.id.textProfileWeight);
            TextView textHeight = v.findViewById(R.id.textProfileHeight);


            userRef.get()
                    .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            if (documentSnapshot == null) {
                                return;
                            } else {



                                textName.setText(documentSnapshot.getString("name"));
                                textSex.setText(Questions.answers.get(2)[Integer.parseInt(documentSnapshot.get("sex").toString())]);
                                textBirthYear.setText(documentSnapshot.get("year").toString());
                                textLifestyle.setText(Questions.answers.get(0)[Integer.parseInt(documentSnapshot.get("lifestyle").toString())]);
                                textFoodstyle.setText(Questions.answers.get(1)[Integer.parseInt(documentSnapshot.get("sex").toString())]);
                                textWeight.setText(documentSnapshot.get("weight").toString() + " кг");
                                textHeight.setText(documentSnapshot.get("height").toString() + " см");

                            }
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                        }
                    });
        }
        return v;
    }


    @Override
    public void onAction(int id) {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.getProfileFilled:
                Intent intent = new Intent(getActivity().getApplicationContext(), ProfileFillActivity.class);
                startActivity(intent);
                break;
            case R.id.getRefresh:
                Intent mintent = new Intent(getActivity().getApplicationContext(), MainActivity.class);
                startActivity(mintent);
                break;

        }
    }
}
