package com.pheng.abel.badthingslldo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.List;

/**
 * Created by mokan on 2015/7/12.
 */
public class BadThingListFragment extends ListFragment {

    private List<BadThing> mBadThings;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.crimes_title);
        mBadThings = BadThingCache.get(getActivity()).getBadThings();
        ArrayAdapter<BadThing> adapter = new BadThingAdapter(mBadThings);
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Intent i = new Intent(getActivity(),BadThingPagerActivity.class);
        BadThing c = (BadThing)getListAdapter().getItem(position);
        i.putExtra(BadThingFragment.EXTRA_CRIME_ID,c.getId());
        startActivity(i);
    }

    private final class BadThingAdapter extends ArrayAdapter<BadThing> {
        public BadThingAdapter(List<BadThing> badThings) {
            super(getActivity(), 0, badThings);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // If we weren't given a view, inflate one
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater()
                        .inflate(R.layout.list_item_badthing, null);
            }
            // Configure the view for this BadThing
            BadThing c = getItem(position);
            TextView titleTextView =
                    (TextView) convertView.findViewById(R.id.crime_list_item_titleTextView);
            titleTextView.setText(c.getTitle());
            TextView dateTextView =
                    (TextView) convertView.findViewById(R.id.crime_list_item_dateTextView);
            dateTextView.setText(DateFormat.getDateTimeInstance().format(c.getDate()));
            CheckBox solvedCheckBox =
                    (CheckBox) convertView.findViewById(R.id.crime_list_item_solvedCheckBox);
            solvedCheckBox.setChecked(c.isCorrected());
            return convertView;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        ((BadThingAdapter)getListAdapter()).notifyDataSetChanged();
    }
}
