package com.login.valeriya.feelit.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.login.valeriya.feelit.R;
import com.login.valeriya.feelit.SubscriptionActivity;
import com.login.valeriya.feelit.Utils.PackVit;


public class SubFragment extends Fragment implements View.OnClickListener{
    private static final String ARG_ID = "id";
    private static final String ARG_TITLE = "title";


    private int id;
    private OnActionListener actionListener;

    public SubFragment() { }

    public static SubFragment newInstance(int id, String question_id) {
        SubFragment fragment = new SubFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_ID, id);
        args.putString(ARG_TITLE, question_id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.id = getArguments().getInt(ARG_ID);
            String title = getArguments().getString(ARG_TITLE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_sub, container, false);

        Gson gson = new Gson();
        PackVit mPackage = new PackVit();
        mPackage = gson.fromJson(SubscriptionActivity.pack, PackVit.class);

        TextView textView = v.findViewById(R.id.textPack);

        StringBuilder stringBuilder = new StringBuilder();

        int size = 3;

        for (int i = 0; i < size; i++)
        {
            stringBuilder.append("Название : ");
            stringBuilder.append(mPackage.pack.get(i).name);
            stringBuilder.append(" с дозировкой ");
            stringBuilder.append(mPackage.pack.get(i).dose);
            stringBuilder.append(" мг в день\n");
        }

        textView.setText(stringBuilder.toString());



        return v;
//        return inflater.inflate(R.layout.fragment_card, container, false);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnActionListener) {
            actionListener = (OnActionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnActionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        actionListener = null;
    }

    public interface OnActionListener {
        void onAction(int id);

        void onClick(View v);
    }
}
