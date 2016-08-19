package ggikko.me.ggikkoapp.ui.img;

import android.graphics.drawable.Drawable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatDrawableManager;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import ggikko.me.ggikkoapp.R;
import ggikko.me.ggikkoapp.di.base.activity.InjectionActivity;
import ggikko.me.ggikkoapp.ui.img.adapter.SectionsPagerAdapter;
import ggikko.me.ggikkoapp.ui.img.fragment.ArchiveFragment;

//TODO : code convention 필요
public class ImageSearchActivity extends InjectionActivity {

    private SearchView mSearchView;
    private MenuItem mMenuItem;
    private SectionsPagerAdapter sectionsPagerAdapter;

    @BindView(R.id.activity_image_toolbar) Toolbar activity_image_toolbar;
    @BindView(R.id.activity_image_tabs) TabLayout activity_image_tabs;
    @BindString(R.string.app_name) String app_name;
    @BindString(R.string.searchview_hint) String searchview_hint;

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
     * toolbar 타이틀 적용
     * support actionbar에 toolbar등록
     */
    private void toolbarSetting() {
        activity_image_toolbar.setTitle(app_name);
        setSupportActionBar(activity_image_toolbar);
    }

    /**
     * 1. view pager를 등록
     * 2. section pager adapter를 생성하고 각 page activity정함
     * 3. 페이지 변경이 일어날 때마다 archive fragment의 content refresh 시켜줌
     * 4. 탭을 이동할 때 아이콘에 Alpha값을 주어 client가 현재 어떤 페이지에 있는지 인지시켜줌
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
                Fragment currentFragment = sectionsPagerAdapter.getFragmentFromCollection(position);
                if(currentFragment !=null && currentFragment instanceof ArchiveFragment) ((ArchiveFragment) currentFragment).refresh();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        activity_image_tabs.setupWithViewPager(viewPager);
        activity_image_tabs.getTabAt(0).setCustomView(getCustomIcon(R.drawable.ic_download));
        activity_image_tabs.getTabAt(0).setText("");
        activity_image_tabs.getTabAt(1).setCustomView(getCustomIcon(R.drawable.ic_save, 100));
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
    /**
     * ref - Aos developer
     * mutate
     * Added in API level 3
     * Drawable mutate ()
     * Make this drawable mutable.
     * This operation cannot be reversed.
     * A mutable drawable is guaranteed to not share its state with any other drawable.
     * This is especially useful when you need to modify properties of drawables loaded from resources.
     * By default, all drawables instances loaded from the same resource share a common state;
     * if you modify the state of one instance, all the other instances will receive the same modification.
     * Calling this method on a mutable Drawable will have no effect.
     */

    /**
     * 커스텀 탭을 이용하여 alpha값을 줌 - 100
     * setupTabs method에서 사용
     * @param resId
     * @param alpha
     * @return
     */
    private View getCustomIcon(int resId, int alpha) {
        return getCustomIcon(AppCompatDrawableManager.get().getDrawable(activity_image_tabs.getContext(), resId), alpha);
    }

    /**
     * 탭의 커스텀 아이콘의 alpha값을 줍니다 - 200
     * setupTabs method에서 사용
     * @param resId
     * @return
     */
    private View getCustomIcon(int resId) {
        return getCustomIcon(AppCompatDrawableManager.get().getDrawable(activity_image_tabs.getContext(), resId), 255);
    }

    /**
     * custom view 를 inflate해주고 icon, drawable, alpha설정 후 만들어진 view 반환
     * @param icon
     * @param alpha
     * @return
     */
    private View getCustomIcon(Drawable icon, int alpha) {
        ImageView imageView = (ImageView) getLayoutInflater().inflate(R.layout.custom_tab, null);
        imageView.setImageDrawable(icon);
        imageView.getDrawable().mutate().setAlpha(alpha);
        return imageView;
    }

    /**
     * 검색 View 생성 및 힌트 설정
     * 완료 후에 softkeyboard 내리고 clear focus
     * //TODO 추후에 publish subscriber를 이용하여 일정 시간 단위 후 입력이 안되면 자동으로 service를 call하는 방식 적용 예정
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search, menu);
        mMenuItem = menu.findItem(R.id.action_search);
        mSearchView = (SearchView) MenuItemCompat.getActionView(mMenuItem);
        mSearchView.setQueryHint(searchview_hint);
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                sectionsPagerAdapter.getSearchFragment().sendSearchWord(query);
                mSearchView.post(()-> mSearchView.clearFocus());
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

}
