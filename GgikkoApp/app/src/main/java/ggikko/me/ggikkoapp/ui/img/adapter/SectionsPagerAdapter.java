package ggikko.me.ggikkoapp.ui.img.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;

import ggikko.me.ggikkoapp.ui.img.fragment.ArchiveFragment;
import ggikko.me.ggikkoapp.ui.img.fragment.SearchFragment;

import lombok.Getter;

/**
 * Section pager
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @Getter private SearchFragment searchFragment;

    @Getter private ArchiveFragment archiveFragment;

    SparseArray<Fragment> fragmentCollection = new SparseArray<Fragment>();

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

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        fragmentCollection.put(position,fragment);
        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        fragmentCollection.remove(position);
        super.destroyItem(container, position, object);
    }

    public Fragment getFragmentFromCollection(int position){
        return fragmentCollection.get(position);
    }

}