package com.example.cheers.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cheers.Adapter.SchoolFragmentPagerAdapter;
import com.example.cheers.R;
import com.google.android.material.tabs.TabLayout;


public class SchoolFragment extends Fragment {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private SchoolFragmentPagerAdapter myFragmentPagerAdapter;
    private TabLayout.Tab one;
    private TabLayout.Tab two;

    @Override

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_school, container, false);

        //bind viewpager with fragment
        mViewPager= (ViewPager) view.findViewById(R.id.viewPager);
        myFragmentPagerAdapter = new SchoolFragmentPagerAdapter(getChildFragmentManager());
        mViewPager.setAdapter(myFragmentPagerAdapter);

        //bind viewpager with tablayout
        mTabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        mTabLayout.setupWithViewPager(mViewPager);

        //tab location
        one = mTabLayout.getTabAt(0);
        two = mTabLayout.getTabAt(1);

        return view;
    }



    @Override

    public void onPause() {

        super.onPause();

    }


}
