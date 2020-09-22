package com.example.cheers.Adapter;



import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.cheers.Fragment.ChildFragment.CampusFragment;
import com.example.cheers.Fragment.ChildFragment.StudentListFragment;

/**
 * Created by Carson_Ho on 16/7/22.
 */
public class SchoolFragmentPagerAdapter extends FragmentPagerAdapter {

    private String[] mTitles = new String[]{"同学", "学校"};

    public SchoolFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        if (position == 1) {
            return new CampusFragment();
        }
        return new StudentListFragment();

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



