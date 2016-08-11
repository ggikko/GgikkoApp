package ggikko.me.ggikkoapp.ui.img.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.inject.Inject;

import ggikko.me.ggikkoapp.R;
import ggikko.me.ggikkoapp.di.base.fragment.InjectionFragment;
import ggikko.me.ggikkoapp.network.models.img.ImageSearchResponse;
import ggikko.me.ggikkoapp.network.service.img.ImageSearchService;
import ggikko.me.ggikkoapp.ui.img.listener.SearchInterface;
import ggikko.me.ggikkoapp.ui.img.presenter.SearchFragmentPresenter;
import rx.Observable;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends InjectionFragment implements SearchInterface {

    private static SearchFragment mSearchFragment;
    private SearchFragmentPresenter mSearchFragmentPresenter;

    @Inject ImageSearchService mImageSearchService;

    public static SearchFragment getInstance(){
        if(mSearchFragment == null){
            return new SearchFragment();
        }
        return mSearchFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_search, container, false);
        return rootView;
    }

    private void init(){
        mSearchFragmentPresenter = new SearchFragmentPresenter();
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(String errorString) {
        //TODO : error handling
    }

    @Override
    public void onNext(ImageSearchResponse imageSearchResponse) {

    }

    @Override
    public Observable<ImageSearchResponse> searchImage() {
        Map<String, String> data = new LinkedHashMap<>();
        data.put("", "");
        return mImageSearchService.searchImage(data);
    }
}
