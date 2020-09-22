package com.example.cheers.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.example.cheers.Fragment.ActionFragment;
import com.example.cheers.Fragment.MessageFragment;
import com.example.cheers.Fragment.PersonFragment;
import com.example.cheers.Fragment.SchoolFragment;
import com.example.cheers.R;


public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener{

    private Fragment mFragment;
    private SchoolFragment schoolFragment;
    private ActionFragment actionFragment;
    private MessageFragment messageFragment;
    private PersonFragment personFragment;
    private FragmentTransaction transaction;
    BottomNavigationBar bottomNavigationBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        initFragment();
        bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        BottomNavigationBar();
        bottomNavigationBar.setTabSelectedListener(this);
    }


    private void initFragment() {
        schoolFragment = new SchoolFragment();
        actionFragment = new ActionFragment();
        messageFragment = new MessageFragment();
        personFragment = new PersonFragment();
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fragment_container,schoolFragment).commit();
        mFragment = schoolFragment;
    }


    private void BottomNavigationBar(){
        bottomNavigationBar.setActiveColor(R.color.colorAccent)
                .setInActiveColor(R.color.gray)
                .setBarBackgroundColor("#FFFFFF");
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);


        bottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.ic_school, "学校").setActiveColorResource(R.color.dodgerblue))
                .addItem(new BottomNavigationItem(R.drawable.ic_action, "动态").setActiveColorResource(R.color.dodgerblue))
                .addItem(new BottomNavigationItem(R.drawable.ic_message, "信息").setActiveColorResource(R.color.dodgerblue))
                .addItem(new BottomNavigationItem(R.drawable.ic_person, "个人").setActiveColorResource(R.color.dodgerblue))
                .setFirstSelectedPosition(0)
                .initialise();
    }


    @Override
    public void onTabSelected(int position) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        switch(position){
            case 0:
                switchFragment(schoolFragment);
                break;
            case 1:
                switchFragment(actionFragment);
                break;
            case 2:
                switchFragment(messageFragment);
                break;
            case 3:
                switchFragment(personFragment);
                break;
            default:
                switchFragment(schoolFragment);
                break;
        }
        transaction.commit();
    }


    @Override
    public void onTabUnselected(int position) { }

    @Override
    public void onTabReselected(int position) { }


    private void switchFragment(Fragment fragment) {

        if(mFragment != fragment) {
            if (!fragment.isAdded()) { //if the fragment is not added
                getSupportFragmentManager().beginTransaction().hide(mFragment) //hide current fragment
                        .add(R.id.fragment_container,fragment).commit(); //add next fragment
            } else { //if added
                getSupportFragmentManager().beginTransaction().hide(mFragment).show(fragment).commit();
            }
            mFragment = fragment;
        }
    }
}

