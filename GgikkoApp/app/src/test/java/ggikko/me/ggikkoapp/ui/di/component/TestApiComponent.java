package ggikko.me.ggikkoapp.ui.di.component;

/**
 * Created by ggikko on 16. 5. 25..
 */

import dagger.Component;
import ggikko.me.ggikkoapp.ui.data.service.TestImageSearchService;
import ggikko.me.ggikkoapp.ui.di.module.TestApiModule;
import ggikko.me.ggikkoapp.ui.di.qualifier.TestPerActivity;

/**
 * API Component
 */
@TestPerActivity
@Component(modules = TestApiModule.class, dependencies = TestNetworkComponent.class)
public interface TestApiComponent {

    void inject(TestImageSearchService testImageSearchService);
}
