package ggikko.me.ggikkoapp.ui.img.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import ggikko.me.ggikkoapp.util.log.DebugLog;
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

    //debug
    @Inject DebugLog mDebugLog;

    /**
     * singleton static 생성자
     * @return
     */
    public static SearchFragment getInstance(){
        if(mSearchFragment == null) return new SearchFragment();
        return mSearchFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_search, container, false);
        ButterKnife.bind(this, rootView);

        recyclerViewSetting();

        return rootView;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mDebugLog.d("onAttach");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDebugLog.d("onCreate");
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mDebugLog.d("onViewCreated");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mDebugLog.d("onActivityCreated");
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        mDebugLog.d("onViewStateRestored");
    }

    @Override
    public void onStart() {
        super.onStart();
        mDebugLog.d("onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        mDebugLog.d("onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        mDebugLog.d("onPause");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mDebugLog.d("onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mDebugLog.d("onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mDebugLog.d("onDetach");
    }


    /**
     * recyclerview setting
     * @Inject adapter, layoutManager
     */
    private void recyclerViewSetting() {
        rv_search.setAdapter(mSearchAdapter);
        rv_search.setItemAnimator(new SlideInRightAnimator());
        rv_search.getItemAnimator().setRemoveDuration(200);
        rv_search.setLayoutManager(mLinearLayoutManager);
    }

    /**
     * 완료되었을 때 처리
     * @param
     */
    @Override
    public void onCompleted() {
        ((ImageSearchActivity)mContext).hideLoading();
    }

    /**
     * 에러 핸들링
     * TODO : error handling(내부 객체 랩핑 or Retry code)
     * @param errorString
     */
    @Override
    public void onError(String errorString) {
        ((ImageSearchActivity)mContext).hideLoading();
    }

    /**
     * Image 요청 parameter + API call
     * need @Inject ImageSearch Service
     * TODO : paging
     * @param searchWord
     * @return Observable<ImageSearchResponse>
     */
    @Override
    public Observable<ImageSearchResponse> searchImage(String searchWord) {
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

    /**
     * View의 visivility 판단
     * TODO : Util class로 빼서 @Inject 필요
     * @param view
     * @return
     */
    private boolean isViewVisible(View view){
        if(view.getVisibility() == View.VISIBLE) return true;
        return false;
    }

}
