package ggikko.me.ggikkoapp.ui.img.listener;

import ggikko.me.ggikkoapp.network.models.img.ImageSearchResponse;
import rx.Observable;

/**
 * Created by ggikko on 16. 5. 30..
 */

/**
 * Presenter -> view 로 업데이트 해주는 interface
 */
public interface SearchViewInterface {

    void onCompleted();
    void onError(String errorString);
    Observable<ImageSearchResponse> searchImage(String searchWord);
}
