package com.xandy.colorviewpagerdemo;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.xandy.colorviewpager.ColorViewPager;

public class MainActivity extends FragmentActivity implements ViewFragment.OnFragmentInteractionListener{

    private ColorViewPager colorViewPager;

    FragmentPagerAdapter adapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        colorViewPager = (ColorViewPager) findViewById(R.id.colorviewpager);
        adapter = new MyAdapter(getSupportFragmentManager());
        colorViewPager.setAdapter(adapter);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    private class MyAdapter extends FragmentPagerAdapter {
        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return new ViewFragment().newInstance("this is " + position);
        }

        @Override
        public int getCount() {
            return 90;
        }
    }

}
