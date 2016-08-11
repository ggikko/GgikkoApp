package ggikko.me.ggikkoapp.network.service.img;

import java.util.Map;

import ggikko.me.ggikkoapp.network.models.img.ImageSearchResponse;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by ggikko on 16. 8. 9..
 */
public interface ImageSearchService {

    @GET("search/image")
    Observable<ImageSearchResponse> searchImage(@QueryMap Map<String, String> options);

}
