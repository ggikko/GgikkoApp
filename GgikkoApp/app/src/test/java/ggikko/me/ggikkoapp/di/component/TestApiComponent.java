package ggikko.me.ggikkoapp.di.component;

/**
 * Created by ggikko on 16. 5. 25..
 */

import dagger.Component;
import ggikko.me.ggikkoapp.di.module.TestApiModule;
import ggikko.me.ggikkoapp.di.qualifier.TestPerActivity;
import ggikko.me.ggikkoapp.service.TestImageSearchService;

/**
 * API Component
 */
@TestPerActivity
@Component(modules = TestApiModule.class, dependencies = TestNetworkComponent.class)
public interface TestApiComponent {

    void inject(TestImageSearchService testImageSearchService);
}
