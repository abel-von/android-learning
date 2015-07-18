package com.pheng.abel.badthingslldo;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by mokan on 2015/7/12.
 */
public class BadThingCache {
    private static BadThingCache sBadThingCache;
    private Context mAppContext;
    private final List<BadThing> mBadThings;

    private BadThingCache(Context appContext) {
        this.mAppContext = appContext;
        mBadThings = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            BadThing c = new BadThing();
            c.setTitle("BadThing #" + i);
            c.setCorrected(i % 2 == 0);
            mBadThings.add(c);
        }// Every other one
    }

    public static synchronized BadThingCache get(Context context) {
        if (null == sBadThingCache) {
            sBadThingCache = new BadThingCache(context.getApplicationContext());
        }
        return sBadThingCache;
    }

    public List<BadThing> getBadThings() {
        return mBadThings;
    }

    public BadThing getBadthing(UUID id) {

        for (BadThing c : mBadThings) {
            if (c.getId().equals(id))
                return c;
        }
        return null;
    }
}
