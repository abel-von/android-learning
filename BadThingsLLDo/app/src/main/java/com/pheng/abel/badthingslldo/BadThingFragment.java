package com.pheng.abel.badthingslldo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import java.text.DateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by mokan on 2015/7/7.
 */
public class BadThingFragment extends Fragment {

    public static final String EXTRA_CRIME_ID = "com.pheng.abel.badthinglldo.crime_id";
    private static final String DIALOG_DATE = "date";
    private static final int REQUEST_CODE_DATE = 0;
    private BadThing badThing;
    private EditText titleField;
    private Button button;
    private CheckBox checkBox;

    public static BadThingFragment newInstance(UUID id) {
        BadThingFragment fragment = new BadThingFragment();
        Bundle arguments = new Bundle();
        arguments.putSerializable(EXTRA_CRIME_ID, id);
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID badThingId = (UUID) getArguments().getSerializable(EXTRA_CRIME_ID);
        badThing = BadThingCache.get(getActivity()).getBadthing(badThingId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_badthing, container, false);

        titleField = (EditText) v.findViewById(R.id.crime_title);
        titleField.setText(badThing.getTitle());
        titleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                badThing.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        button = (Button) v.findViewById(R.id.crime_date);
        button.setText(DateFormat.getDateTimeInstance().format(badThing.getDate()));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                DatePickerFragment dialog = DatePickerFragment.newInstance(badThing
                        .getDate());
                dialog.setTargetFragment(BadThingFragment.this, REQUEST_CODE_DATE);
                dialog.show(fm, DIALOG_DATE);
            }
        });

        checkBox = (CheckBox) v.findViewById(R.id.crime_corrected);
        checkBox.setChecked(badThing.isCorrected());
        checkBox.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        badThing.setCorrected(isChecked);
                    }
                }
        );

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if(requestCode == REQUEST_CODE_DATE){
            Date date = (Date)data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            badThing.setDate(date);
            button.setText(DateFormat.getDateTimeInstance().format(badThing.getDate()));
        }
    }
}
