package ggikko.me.ggikkoapp.di.module;

import dagger.Module;
import dagger.Provides;
import ggikko.me.ggikkoapp.config.TestNetworkConfig;
import ggikko.me.ggikkoapp.di.qualifier.TestPerActivity;
import ggikko.me.ggikkoapp.network.service.img.ImageSearchService;
import retrofit2.Retrofit;

/**
 * Created by ggikko on 16. 5. 30..
 */
@Module
public class TestApiModule {

    @Provides
    @TestPerActivity
    ImageSearchService provideImageSearchService(Retrofit retrofit){
        return retrofit.create(ImageSearchService.class);
    }

    /**
     * Test network config for testing
     * @return testNetworkConfig
     */
    @Provides
    @TestPerActivity
    public TestNetworkConfig provideTestNetworkConfig(){
        return new TestNetworkConfig();
    }

}
