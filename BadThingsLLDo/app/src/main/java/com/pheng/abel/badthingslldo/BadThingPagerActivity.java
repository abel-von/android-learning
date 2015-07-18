package com.pheng.abel.badthingslldo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.List;
import java.util.UUID;

/**
 * Created by mokan on 2015/7/12.
 */
public class BadThingPagerActivity extends FragmentActivity {
    private ViewPager mViewPager;
    private List<BadThing> mBadThings;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewPager = new ViewPager(this);
        mViewPager.setId(R.id.viewPager);
        setContentView(mViewPager);
        mBadThings = BadThingCache.get(this).getBadThings();
        FragmentManager fm = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentPagerAdapter(fm) {
            @Override
            public Fragment getItem(int position) {
                BadThing badThing = mBadThings.get(position);
                return BadThingFragment.newInstance(badThing.getId());
            }

            @Override
            public int getCount() {
                return mBadThings.size();
            }
        });

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener(){

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                BadThing badThing = mBadThings.get(state);
                if(null != getTitle()){
                    setTitle(badThing.getTitle());
                }
            }
        });

        UUID mBadThingId = (UUID)getIntent().getSerializableExtra(BadThingFragment
                .EXTRA_CRIME_ID);
        for(int i = 0;i<mBadThings.size();i++) {
            if(mBadThings.get(i).getId().equals(mBadThingId)){
                mViewPager.setCurrentItem(i);
                break;
            }
        }


    }
}
