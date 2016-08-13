package ggikko.me.ggikkoapp.di.injector;

import ggikko.me.ggikkoapp.di.base.activity.InjectionActivity;
import ggikko.me.ggikkoapp.di.component.TestActivityComponent;
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
