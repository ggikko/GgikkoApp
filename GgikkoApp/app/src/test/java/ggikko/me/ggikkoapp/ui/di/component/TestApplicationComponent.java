package ggikko.me.ggikkoapp.ui.di.component;

import javax.inject.Singleton;

import dagger.Component;
import ggikko.me.ggikkoapp.GgikkoApplication;
import ggikko.me.ggikkoapp.ui.di.module.TestActivityModule;
import ggikko.me.ggikkoapp.ui.di.module.TestApplicationModule;
import ggikko.me.ggikkoapp.ui.di.module.TestNetworkModule;

/**
 * Application component -> Activity component -> Fragment component
 * 순서대로 주입
 * Qulifier에 따른 분할
 */
@Singleton
@Component(modules = {TestApplicationModule.class, TestNetworkModule.class})
public interface TestApplicationComponent {

    TestActivityComponent plusActivityComponent(TestActivityModule activityModule);

    void inject(GgikkoApplication application);
}
