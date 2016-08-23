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
import ggikko.me.ggikkoapp.util.log.DebugLog;
import rx.Observable;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends InjectionFragment implements SearchViewInterface {

  private static SearchFragment mSearchFragment;

  @BindView(R.id.rv_search)
  RecyclerView rvSearch;
  @BindView(R.id.search_empty_text)
  TextView searchEmptyText;
  @BindString(R.string.api_key)
  String apikey;

  //util
  @Inject
  LinearLayoutManager linearLayoutManager;
  @Inject
  Context context;

  //service
  @Inject
  ImageSearchService imageSearchService;

  //adapter
  @Inject
  SearchAdapter searchAdapter;
  @Inject
  SearchPresenter searchPresenter;

  //debug
  @Inject
  DebugLog debugLog;

  /**
   * modified by ggikko on 16. 8. 23..
   * singleton static 생성자
   */
  public static SearchFragment getInstance() {
    if (mSearchFragment == null) return new SearchFragment();
    return mSearchFragment;
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {

    View rootView = inflater.inflate(R.layout.fragment_search, container, false);
    ButterKnife.bind(this, rootView);

    recyclerViewSetting();

    return rootView;
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    if (getChildFragmentManager().getFragments() != null) {
      for (Fragment fragment : getChildFragmentManager().getFragments()) {
        getChildFragmentManager().beginTransaction().remove(fragment).commitAllowingStateLoss();
      }
    }
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
  }

  @Override
  public void onDetach() {
    super.onDetach();
  }

  /**
   * modified by ggikko on 16. 8. 23..
   * recyclerview setting
   * @Inject adapter, layoutManager
   */
  private void recyclerViewSetting() {
    rvSearch.setAdapter(searchAdapter);
    rvSearch.setItemAnimator(new SlideInRightAnimator());
    rvSearch.getItemAnimator().setRemoveDuration(200);
    rvSearch.setLayoutManager(linearLayoutManager);
  }

  /**
   * modified by ggikko on 16. 8. 23..
   * 완료되었을 때 처리
   */
  @Override
  public void onCompleted() {
    ((ImageSearchActivity) context).hideLoading();
  }

  /**
   * modified by ggikko on 16. 8. 23..
   * 에러 핸들링 TODO : error handling(내부 객체 랩핑 or Retry code)
   */
  @Override
  public void onError(String errorString) {
    ((ImageSearchActivity) context).hideLoading();
  }

  /**
   * modified by ggikko on 16. 8. 23..
   * Image 요청 parameter + API call need @Inject ImageSearch Service TODO : paging
   * @return Observable<ImageSearchResponse>
   */
  @Override
  public Observable<ImageSearchResponse> searchImage(String searchWord) {
    Map<String, String> data = new LinkedHashMap<>();
    data.put("apiKey", apikey);
    data.put("q", searchWord);
    data.put("result", "10");
    data.put("pageno", "1");
    data.put("output", "json");
    return imageSearchService.searchImage(data);
  }

  /**
   * modified by ggikko on 16. 8. 23..
   * 해당 word로 이미지를 요청한다.
   * @param searchWord - 검색어
   */
  public void sendSearchWord(String searchWord) {
    if (isViewVisible(searchEmptyText)) searchEmptyText.setVisibility(View.GONE);
    if (!isViewVisible(rvSearch)) rvSearch.setVisibility(View.VISIBLE);

    ((ImageSearchActivity) context).showLoading();
    searchPresenter.requestSearchImage(searchWord);
  }

  /**
   * modified by ggikko on 16. 8. 23..
   * View의 visivility 판단 TODO : Util class로 빼서 @Inject 필요
   */
  private boolean isViewVisible(View view) {
    if (view.getVisibility() == View.VISIBLE) return true;
    return false;
  }

}
