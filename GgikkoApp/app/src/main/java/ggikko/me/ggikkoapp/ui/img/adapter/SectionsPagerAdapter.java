package ggikko.me.ggikkoapp.ui.img.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import ggikko.me.ggikkoapp.ui.img.fragment.ArchiveFragment;
import ggikko.me.ggikkoapp.ui.img.fragment.SearchFragment;

import lombok.Getter;

/**
 * Section pager
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @Getter private SearchFragment searchFragment;

    @Getter private ArchiveFragment archiveFragment;

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            searchFragment = SearchFragment.getInstance();
            return searchFragment;
        } else {
            archiveFragment = ArchiveFragment.getInstance();
            return archiveFragment ;
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