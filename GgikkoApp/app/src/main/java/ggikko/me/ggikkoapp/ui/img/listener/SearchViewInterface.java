package ggikko.me.ggikkoapp.ui.img.listener;

import ggikko.me.ggikkoapp.network.models.img.ImageSearchResponse;
import rx.Observable;

/**
 * Created by admin on 16. 5. 30..
 */
public interface SearchViewInterface {

    void onCompleted();
    void onError(String errorString);
    Observable<ImageSearchResponse> searchImage(String searchWord);
}
