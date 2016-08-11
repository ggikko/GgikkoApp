package ggikko.me.ggikkoapp.di.component;

/**
 * Created by ggikko on 16. 5. 25..
 */

import dagger.Component;
import ggikko.me.ggikkoapp.di.module.network.ApiModule;
import ggikko.me.ggikkoapp.di.qualifier.PerActivity;
import ggikko.me.ggikkoapp.ui.img.ImageSearchActivity;

/**
 * API Component
 */
@PerActivity
@Component(modules = ApiModule.class, dependencies = NetworkComponent.class)
public interface ApiComponent {

    void inject(ImageSearchActivity imageActivity);
}
