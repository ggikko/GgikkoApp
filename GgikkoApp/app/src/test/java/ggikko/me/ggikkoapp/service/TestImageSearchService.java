package ggikko.me.ggikkoapp.service;


import com.annimon.stream.Stream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import ggikko.me.ggikkoapp.config.TestNetworkConfig;
import ggikko.me.ggikkoapp.di.component.DaggerTestApiComponent;
import ggikko.me.ggikkoapp.di.component.DaggerTestNetworkComponent;
import ggikko.me.ggikkoapp.di.component.TestNetworkComponent;
import ggikko.me.ggikkoapp.di.module.TestNetworkModule;
import ggikko.me.ggikkoapp.network.models.img.ImageSearchResponse;
import ggikko.me.ggikkoapp.network.service.img.ImageSearchService;
import rx.observers.TestSubscriber;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by ggikko on 16. 8. 10..
 */

@RunWith(JUnit4.class)
public class TestImageSearchService {

    @Inject
    TestNetworkConfig mTestNetworkConfig;
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
            Stream.of(value.channel.item).forEach(item->{
                assertThat(item.thumbnail).isNotEmpty();
                assertThat(item.title).isNotEmpty();
                assertThat(item.height).isNotEmpty();
                assertThat(item.width).isNotEmpty();
            });
        });

    }

}
