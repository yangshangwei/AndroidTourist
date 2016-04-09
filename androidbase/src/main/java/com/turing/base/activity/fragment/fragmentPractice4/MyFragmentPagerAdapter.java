package com.turing.base.activity.fragment.fragmentPractice4;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * MyApp
 *
 * @author Mr.Yang on 2016-03-16  22:50.
 * @version 1.0
 * @desc
 */
public class MyFragmentPagerAdapter extends FragmentPagerAdapter {


    private final int PAGER_COUNT = 4;

    private Fragment1 myFragment1 = null;
    private Fragment2 myFragment2 = null;
    private Fragment3 myFragment3 = null;
    private Fragment4 myFragment4 = null;



    public MyFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
        myFragment1 = new Fragment1();
        myFragment2 = new Fragment2();
        myFragment3 = new Fragment3();
        myFragment4 = new Fragment4();
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case BottomNvgViewPageAct.PAGE_ONE:
                fragment = myFragment1;
                break;
            case BottomNvgViewPageAct.PAGE_TWO:
                fragment = myFragment2;
                break;
            case BottomNvgViewPageAct.PAGE_THREE:
                fragment = myFragment3;
                break;
            case BottomNvgViewPageAct.PAGE_FOUR:
                fragment = myFragment4;
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return PAGER_COUNT;
    }
}
