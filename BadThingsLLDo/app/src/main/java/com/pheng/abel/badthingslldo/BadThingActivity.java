package com.pheng.abel.badthingslldo;

import android.support.v4.app.Fragment;

import java.util.UUID;

/**
 * Created by mokan on 2015/7/12.
 */
public class BadThingActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        UUID id = (UUID)getIntent().getSerializableExtra(BadThingFragment.EXTRA_CRIME_ID);
        return BadThingFragment.newInstance(id);
    }
}
