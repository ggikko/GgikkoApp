package ggikko.me.ggikkoapp.ui.img.presenter;


import ggikko.me.ggikkoapp.di.base.presenter.BasePresenter;
import ggikko.me.ggikkoapp.network.models.img.ImageSearchResponse;
import rx.Observer;

/**
 * Created by admin on 16. 8. 10..
 */
public class SearchFragmentPresenter extends BasePresenter implements Observer<ImageSearchResponse> {

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onNext(ImageSearchResponse imageSearchResponse) {

    }
}
