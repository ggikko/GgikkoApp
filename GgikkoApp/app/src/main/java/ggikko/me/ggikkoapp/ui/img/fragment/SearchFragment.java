package ggikko.me.ggikkoapp.ui.img.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import ggikko.me.ggikkoapp.R;
import ggikko.me.ggikkoapp.di.base.fragment.InjectionFragment;
import ggikko.me.ggikkoapp.network.models.img.ImageSearchResponse;
import ggikko.me.ggikkoapp.network.service.img.ImageSearchService;
import ggikko.me.ggikkoapp.ui.img.ImageSearchActivity;
import ggikko.me.ggikkoapp.ui.img.adapter.SearchAdapter;
import ggikko.me.ggikkoapp.ui.img.listener.SearchViewInterface;
import ggikko.me.ggikkoapp.ui.img.presenter.SearchPresenter;
import ggikko.me.ggikkoapp.util.animator.SlideInRightAnimator;
import rx.Observable;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends InjectionFragment implements SearchViewInterface {

    private static SearchFragment mSearchFragment;

    @BindView(R.id.rv_search) RecyclerView rv_search;
    @BindView(R.id.search_empty_text) TextView search_empty_text;
    @BindString(R.string.api_key) String APIKEY;

    //util
    @Inject LinearLayoutManager mLinearLayoutManager;
    @Inject Context mContext;

    //service
    @Inject ImageSearchService mImageSearchService;

    //adapter
    @Inject SearchAdapter mSearchAdapter;
    @Inject SearchPresenter mSearchPresenter;

    public static SearchFragment getInstance(){
        if(mSearchFragment == null) return new SearchFragment();
        return mSearchFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_search, container, false);
        ButterKnife.bind(this, rootView);

        rv_search.setAdapter(mSearchAdapter);
        rv_search.setItemAnimator(new SlideInRightAnimator());
        rv_search.getItemAnimator().setRemoveDuration(200);
        rv_search.setLayoutManager(mLinearLayoutManager);

        return rootView;
    }

    @Override
    public void onCompleted() {
        ((ImageSearchActivity)mContext).hideLoading();
    }

    @Override
    public void onError(String errorString) {
        //TODO : error handling
        ((ImageSearchActivity)mContext).hideLoading();
    }

    @Override
    public Observable<ImageSearchResponse> searchImage(String searchWord) {
        //TODO : paging
        Map<String, String> data = new LinkedHashMap<>();
        data.put("apiKey", APIKEY);
        data.put("q", searchWord);
        data.put("result", "10");
        data.put("pageno", "1");
        data.put("output", "json");
        return mImageSearchService.searchImage(data);
    }

    public void sendSearchWord(String searchWord) {
        if(isViewVisible(search_empty_text))search_empty_text.setVisibility(View.GONE);
        if(!isViewVisible(rv_search))rv_search.setVisibility(View.VISIBLE);

        ((ImageSearchActivity)mContext).showLoading();
        mSearchPresenter.requestSearchImage(searchWord);
    }

    private boolean isViewVisible(View view){
        if(view.getVisibility() == View.VISIBLE) return true;
        return false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
