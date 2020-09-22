package com.example.cheers.Adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.cheers.Fragment.ChildFragment.PersonalActionFragment;
import com.example.cheers.Fragment.ChildFragment.PersonalStatusFragment;


/**
 * Created by Carson_Ho on 16/7/22.
 */
public class PersonalFragmentPagerAdapter extends FragmentPagerAdapter {

    private String[] mTitles = new String[]{"信息", "动态"};

    public PersonalFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        if (position == 1) {
            return new PersonalActionFragment();
        }
        return new PersonalStatusFragment();

    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }


}



