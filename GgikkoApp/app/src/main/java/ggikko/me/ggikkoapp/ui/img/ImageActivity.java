package ggikko.me.ggikkoapp.ui.img;

import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.AppCompatDrawableManager;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import ggikko.me.ggikkoapp.R;
import ggikko.me.ggikkoapp.di.base.activity.InjectionActivity;
import ggikko.me.ggikkoapp.ui.img.adapter.SectionsPagerAdapter;

public class ImageActivity extends InjectionActivity {

    private SearchView searchView;
    private MenuItem menuItem;
    private SectionsPagerAdapter sectionsPagerAdapter;

    @BindView(R.id.activity_image_toolbar) Toolbar activity_image_toolbar;
    @BindView(R.id.activity_image_tabs) TabLayout activity_image_tabs;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_image;
    }

    @Override
    protected void onCreate() {
        unbinder = ButterKnife.bind(this);

        //toolbar
        toolbarSetting();

        //tab pager
        setupTabs();
    }

    /**
     * toolbar setting
     */
    private void toolbarSetting() {
        activity_image_toolbar.setTitle("GGIKKO");
        setSupportActionBar(activity_image_toolbar);
    }

    /**
     * setup tab layout
     */
    private void setupTabs() {
        final ViewPager viewPager = (ViewPager) findViewById(R.id.container);
        sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(sectionsPagerAdapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position ==0) {
                    sectionsPagerAdapter.getMArchiveFragment();
                }else{
                    sectionsPagerAdapter.getMSearchFragment();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        activity_image_tabs.setupWithViewPager(viewPager);
        activity_image_tabs.getTabAt(0).setCustomView(getCustomIcon(R.drawable.ic_search));
        activity_image_tabs.getTabAt(0).setText("");
        activity_image_tabs.getTabAt(1).setCustomView(getCustomIcon(R.drawable.ic_search, 100));
        activity_image_tabs.getTabAt(1).setText("");

        activity_image_tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                ImageView imageView = (ImageView) tab.getCustomView();
                imageView.getDrawable().mutate().setAlpha(255);
                viewPager.setCurrentItem(tab.getPosition(), true);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                ImageView imageView = (ImageView) tab.getCustomView();
                imageView.getDrawable().mutate().setAlpha(100);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private View getCustomIcon(int resId, int alpha) {
        return getCustomIcon(AppCompatDrawableManager.get().getDrawable(activity_image_tabs.getContext(), resId), alpha);
    }

    private View getCustomIcon(int resId) {
        return getCustomIcon(AppCompatDrawableManager.get().getDrawable(activity_image_tabs.getContext(), resId), 255);
    }

    private View getCustomIcon(Drawable icon, int alpha) {
        ImageView imageView = (ImageView) getLayoutInflater().inflate(R.layout.custom_tab, null);
        imageView.setImageDrawable(icon);
        imageView.getDrawable().mutate().setAlpha(alpha);
        return imageView;
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search, menu);
        menuItem = menu.findItem(R.id.action_search);
        searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setQueryHint("Please type search word..");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                toastText("search!", Toast.LENGTH_SHORT);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

}
