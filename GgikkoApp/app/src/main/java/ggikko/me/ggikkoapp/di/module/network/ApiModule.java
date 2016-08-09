package ggikko.me.ggikkoapp.di.module.network;


import dagger.Module;
import dagger.Provides;
import ggikko.me.ggikkoapp.di.qualifier.PerActivity;
import ggikko.me.ggikkoapp.network.service.img.ImageSearchService;
import retrofit2.Retrofit;

/**
 * Created by ggikko on 16. 5. 25..
 */
@Module
public class ApiModule {

    @Provides
    @PerActivity
    ImageSearchService provideImageSearchService(Retrofit retrofit){
        return retrofit.create(ImageSearchService.class);
    }
}
