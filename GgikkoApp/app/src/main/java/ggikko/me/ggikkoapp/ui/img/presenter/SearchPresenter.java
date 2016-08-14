package ggikko.me.ggikkoapp.ui.img.presenter;


import android.text.TextUtils;

import java.util.concurrent.TimeUnit;

import ggikko.me.ggikkoapp.di.base.presenter.BasePresenter;
import ggikko.me.ggikkoapp.network.models.img.ImageSearchResponse;
import ggikko.me.ggikkoapp.ui.img.adapter.SearchAdapterDataModel;
import ggikko.me.ggikkoapp.ui.img.adapter.SearchAdapterDataView;
import ggikko.me.ggikkoapp.ui.img.listener.SearchViewInterface;
import rx.Observer;
import rx.Subscription;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;

/**
 * Created by ggikko on 16. 8. 10..
 */
public class SearchPresenter extends BasePresenter implements Observer<ImageSearchResponse> {

    private SearchViewInterface mSearchViewInterface;
    private SearchAdapterDataModel mSearchAdapterDataModel;
    private SearchAdapterDataView mSearchAdapterDataView;

    public SearchPresenter(SearchViewInterface searchViewInterface, SearchAdapterDataModel searchAdapterDataModel, SearchAdapterDataView searchAdapterDataView){
        this.mSearchViewInterface = searchViewInterface;
        this.mSearchAdapterDataModel = searchAdapterDataModel;
        this.mSearchAdapterDataView = searchAdapterDataView;
    }

    public void requestSearchImage(String searchWord){
        mSearchAdapterDataModel.clear();
        if (searchWordIsNotEmpty(searchWord)) {
            unSubscribeAll();
            subscribe(mSearchViewInterface.searchImage(searchWord), SearchPresenter.this);
        } else {
            mSearchAdapterDataView.refresh();
        }
    }

    /**
     * Text 비어있거나 null Check
     * @param searchWord
     * @return
     */
    public boolean searchWordIsNotEmpty(String searchWord) {
        return !TextUtils.isEmpty(searchWord);
    }

    /**
     * 생명주기
     * view refresh, call main view oncompleted
     */
    @Override
    public void onCompleted() {
        mSearchAdapterDataView.refresh();
        mSearchViewInterface.onCompleted();
    }

    /**
     * TODO : error 내부 랩핑 필요
     * @param e
     */
    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        mSearchViewInterface.onError(e.toString());
    }

    /**
     * add item to Search Data Model
     * @param imageSearchResponse
     */
    @Override
    public void onNext(ImageSearchResponse imageSearchResponse) {
        mSearchAdapterDataModel.add(imageSearchResponse);
    }

}
