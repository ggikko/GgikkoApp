package ggikko.me.ggikkoapp.di.component;

import javax.inject.Singleton;

import dagger.Component;
import ggikko.me.ggikkoapp.GgikkoApplication;
import ggikko.me.ggikkoapp.di.module.ActivityModule;
import ggikko.me.ggikkoapp.di.module.ApplicationModule;
import ggikko.me.ggikkoapp.di.module.RepositoryModule;
import ggikko.me.ggikkoapp.di.module.network.NetworkModule;

/**
 * Application component -> Activity component -> Fragment component
 * 순서대로 주입
 * Qulifier에 따른 분할
 */
@Singleton
@Component(modules = {ApplicationModule.class, NetworkModule.class, RepositoryModule.class})
public interface ApplicationComponent {

    ActivityComponent plusActivityComponent(ActivityModule activityModule);

    void inject(GgikkoApplication ggikkoApplication);
}
