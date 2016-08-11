package ggikko.me.ggikkoapp.data.service.img;

import com.annimon.stream.Stream;

import org.junit.Before;
import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import ggikko.me.ggikkoapp.di.component.DaggerTestApiComponent;
import ggikko.me.ggikkoapp.di.component.DaggerTestNetworkComponent;
import ggikko.me.ggikkoapp.di.component.TestNetworkComponent;
import ggikko.me.ggikkoapp.di.module.TestNetworkModule;
import ggikko.me.ggikkoapp.network.models.img.ImageSearchResponse;
import ggikko.me.ggikkoapp.network.service.img.ImageSearchService;
import ggikko.me.ggikkoapp.util.api.NetworkConfig;
import ggikko.me.ggikkoapp.util.api.TestNetworkConfig;
import rx.observers.TestSubscriber;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by ggikko on 16. 8. 10..
 */
public class TestImageSearchService {

    @Inject TestNetworkConfig mTestNetworkConfig;
    @Inject ImageSearchService mImageSearchService;

    @Before
    public void setup() {
        //dagger inject
        DaggerTestApiComponent
                .builder()
                .testNetworkComponent(getTestNetworkComponent())
                .build()
                .inject(this);
    }

    private TestNetworkComponent getTestNetworkComponent() {
        return DaggerTestNetworkComponent.builder().testNetworkModule(new TestNetworkModule(mTestNetworkConfig.DEV_URL)).build();
    }

    @Test
    public void testImageSearchService() {

        //given
        TestSubscriber<ImageSearchResponse> imageSearchSubscriber = TestSubscriber.create();

        Map<String, String> data = new LinkedHashMap<>();
        data.put("apiKey", mTestNetworkConfig.api_key);
        data.put("q", "helloworld");
        data.put("result", "20");
        data.put("pageno", "1");
        data.put("output", "json");

        //when
        mImageSearchService.searchImage(data).subscribe(imageSearchSubscriber);
        List<ImageSearchResponse> imageSearchingInfoItems = imageSearchSubscriber.getOnNextEvents();

        //then
        imageSearchSubscriber.awaitTerminalEvent();

        //test
        assertThat(imageSearchingInfoItems).hasSize(1);
        Stream.of(imageSearchingInfoItems).forEach(value -> {
        });

    }

}
