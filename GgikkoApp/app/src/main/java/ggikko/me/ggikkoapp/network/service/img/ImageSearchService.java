package ggikko.me.ggikkoapp.network.service.img;

import java.util.Map;

import ggikko.me.ggikkoapp.network.models.img.ImageSerchResponse;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by ggikko on 16. 8. 9..
 */
public interface ImageSearchService {

    @GET("investment/schedules/")
    Observable<ImageSerchResponse> searchImage(@QueryMap Map<String, String> options);

}
