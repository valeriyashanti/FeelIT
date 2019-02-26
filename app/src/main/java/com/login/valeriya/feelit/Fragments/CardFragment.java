package com.login.valeriya.feelit.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.login.valeriya.feelit.Adapter.PagerAdapter;
import com.login.valeriya.feelit.MainActivity;
import com.login.valeriya.feelit.R;
import com.login.valeriya.feelit.Utils.Questions;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class CardFragment extends Fragment implements View.OnClickListener{

    private static final String ARG_ID = "id";
    private static final String ARG_TITLE = "title";


    private int id;
    private OnActionListener actionListener;
    private RadioGroup mRadioGroup;
    private RadioButton[] mRadioButton;
    private int checked;
    private EditText mEditText;

    private Button mButton;

    public CardFragment() { }

    public static CardFragment newInstance(int id, String question_id) {
        CardFragment fragment = new CardFragment();
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
        View v = null;
        // Inflate the layout for this fragment
        if (id >= 0 && id <= 2)
        {
            v = inflater.inflate(R.layout.fragment_card_radio_buttons, container, false);
            TextView textView = v.findViewById(R.id.textQuestionRB);
            textView.setText(Questions.text.get(id));

            mRadioGroup = v.findViewById(R.id.radioGroup);
            mRadioButton = new RadioButton[3];

            for (int i = 0; i < 3; i++)
            {
                mRadioButton[i] = new RadioButton(getContext());
                mRadioButton[i].setText(Questions.answers.get(id)[i]);
                mRadioGroup.addView(mRadioButton[i]);
            }

            mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    checked = checkedId % 3;
                }
            });
            mButton = v.findViewById(R.id.button2);
            mButton.setOnClickListener(this);

        }
        else if (id > 2 && id <= 5)
        {
            v = inflater.inflate(R.layout.fragment_card, container, false);
            TextView textView = v.findViewById(R.id.textQuestonNUM);
            textView.setText(Questions.text.get(id));

            mButton = v.findViewById(R.id.button3);
            mButton.setOnClickListener(this);
            mEditText = v.findViewById(R.id.editText);



        }
//        textView.setText(Integer.toString(id));
        return v;
//        return inflater.inflate(R.layout.fragment_card, container, false);
    }

    @Override
    public void onClick(View v) {
        String string;
        switch (id)
        {
            case 0:
                MainActivity.user.setLifestyle(checked - 1);
                onAction();
                break;
            case 1:
                MainActivity.user.setFoodstyle(checked - 1);
                onAction();
                break;
            case 2:
                MainActivity.user.setSex(checked - 1);
                onAction();
                break;
            case 3:
                string = mEditText.getText().toString();
                MainActivity.user.setBirth_year(Integer.parseInt(string));
                onAction();
                break;
            case 4:
                string = mEditText.getText().toString();
                MainActivity.user.setWeight(Integer.parseInt(string));
                onAction();
                break;
            case 5:
                string = mEditText.getText().toString();
                MainActivity.user.setHeight(Integer.parseInt(string));
                onAction();
                break;
        }

        mButton.setEnabled(false);

        if (id >= 0 && id <= 2)
            for (int i = 0; i < 3; i++)
                mRadioButton[i].setEnabled(false);

    }

    public void onAction()
    {
        if (actionListener != null)
            actionListener.onAction(this.id);
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
