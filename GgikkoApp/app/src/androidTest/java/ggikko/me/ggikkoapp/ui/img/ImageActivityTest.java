package ggikko.me.ggikkoapp.ui.img;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ggikko.me.ggikkoapp.R;
import ggikko.me.ggikkoapp.ui.img.ImageSearchActivity;
import ggikko.me.ggikkoapp.ui.img.presenter.SearchPresenter;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by ggikko on 16. 8. 14..
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class ImageActivityTest {

    private SearchPresenter searchPresenter;

    @Rule
    public ActivityTestRule<ImageSearchActivity> searchActivityRule = new ActivityTestRule<>(ImageSearchActivity.class);

    @Test
    public void testSearchView() {
        onView(withId(R.id.action_search)).perform(click());
    }
    @Test
    public void testCenterHelpTextMatches() {
        String searchview_helper = searchActivityRule.getActivity().getString(R.string.searchview_helper);
        onView(withId(R.id.search_empty_text)).check(matches(withText(searchview_helper)));
    }
}
