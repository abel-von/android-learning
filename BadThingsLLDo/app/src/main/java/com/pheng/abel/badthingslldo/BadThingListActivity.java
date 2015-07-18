package com.pheng.abel.badthingslldo;

import android.support.v4.app.Fragment;


public class BadThingListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new BadThingListFragment();
    }
}
