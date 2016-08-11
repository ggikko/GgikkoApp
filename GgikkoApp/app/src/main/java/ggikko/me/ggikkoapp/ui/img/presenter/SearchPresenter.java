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

    private boolean searchWordIsNotEmpty(String searchWord) {
        return !TextUtils.isEmpty(searchWord);
    }

    @Override
    public void onCompleted() {
        mSearchAdapterDataView.refresh();
        mSearchViewInterface.onCompleted();
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        mSearchViewInterface.onError(e.toString());
    }

    @Override
    public void onNext(ImageSearchResponse imageSearchResponse) {
        mSearchAdapterDataModel.add(imageSearchResponse);
    }

}
