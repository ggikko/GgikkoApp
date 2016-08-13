package ggikko.me.ggikkoapp.ui.img.presenter;


import android.support.test.runner.AndroidJUnit4;
import org.junit.runner.RunWith;


import org.junit.Before;
import org.junit.Test;

import ggikko.me.ggikkoapp.ui.img.adapter.SearchAdapterDataModel;
import ggikko.me.ggikkoapp.ui.img.adapter.SearchAdapterDataView;
import ggikko.me.ggikkoapp.ui.img.listener.SearchViewInterface;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by ggikko on 16. 8. 13..
 */
@RunWith(AndroidJUnit4.class)
public class TestSearchPresenter {

    private SearchPresenter searchPresenter;
    private SearchViewInterface searchViewInterface;
    private SearchAdapterDataModel searchAdapterDataModel;
    private SearchAdapterDataView searchAdapterDataView;

    @Before
    public void setUp() throws Exception {
        //given
        searchAdapterDataModel = mock(SearchAdapterDataModel.class);
        searchViewInterface = mock(SearchViewInterface.class);
        searchAdapterDataView = mock(SearchAdapterDataView.class);
        searchPresenter = new SearchPresenter(searchViewInterface,searchAdapterDataModel,searchAdapterDataView);
    }

    @Test
    public void testRequestEmptyText() throws Exception {
        //when
        searchPresenter.requestSearchImage("");
        //then
        verify(searchAdapterDataModel).clear();
        verify(searchAdapterDataView).refresh();
    }

    @Test
    public void testSearchWordIsNotEmpty() throws Exception {
        //when
        boolean valid1 = searchPresenter.searchWordIsNotEmpty("");
        boolean valid2 = searchPresenter.searchWordIsNotEmpty("hello");
        //then
        assertThat(valid1, is(false));
        assertThat(valid2, is(true));
    }

    @Test
    public void testSearchOnComplete() throws Exception {
        //when
        searchPresenter.onCompleted();
        //then
        verify(searchAdapterDataView).refresh();
        verify(searchViewInterface).onCompleted();
    }

}
