package com.example.rplrus11.midsemester12rpl;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

public class pageAdapter extends FragmentStatePagerAdapter {

    int Tabs;

    public pageAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.Tabs = NumOfTabs;
    }

    @Override
    public int getCount() {
        return Tabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                oneFragment nowPlaying = new oneFragment();
                Log.d("makan10", "getItem: ");
                return nowPlaying;
            case 1:
                twoFragment upcoming = new twoFragment();
                return upcoming;
            default:
                return null;
        }
    }
}
