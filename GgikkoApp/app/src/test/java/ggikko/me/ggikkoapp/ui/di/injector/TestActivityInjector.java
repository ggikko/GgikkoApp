package ggikko.me.ggikkoapp.ui.di.injector;

import ggikko.me.ggikkoapp.di.base.activity.InjectionActivity;

import ggikko.me.ggikkoapp.ui.di.component.TestActivityComponent;
import lombok.Getter;

/**
 * activity injector
 */
public class TestActivityInjector {

    @Getter
    private static TestActivityComponent activityComponent;

    public TestActivityInjector(TestActivityComponent activityComponent) {
        this.activityComponent = activityComponent;
    }

    public void inject(InjectionActivity activity) {
    }
}
