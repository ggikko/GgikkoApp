package ggikko.me.ggikkoapp.ui.img.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import ggikko.me.ggikkoapp.ui.img.fragment.ArchiveFragment;
import ggikko.me.ggikkoapp.ui.img.fragment.SearchFragment;

import lombok.Getter;

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @Getter
    private SearchFragment mSearchFragment;

    @Getter
    private ArchiveFragment mArchiveFragment;

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            mSearchFragment = SearchFragment.getInstance();
            return mSearchFragment;
        } else {
            mArchiveFragment = ArchiveFragment.getInstance();
            return mArchiveFragment ;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "search";
            case 1:
                return "archive";
        }
        return null;
    }

}