package ggikko.me.ggikkoapp.di.injector;

import ggikko.me.ggikkoapp.GgikkoApplication;
import ggikko.me.ggikkoapp.di.component.TestApplicationComponent;
import lombok.Getter;

public class TestApplicationInjector {

    @Getter
    private final TestApplicationComponent applicationComponent;

    public TestApplicationInjector(TestApplicationComponent applicationComponent) {
        this.applicationComponent = applicationComponent;
    }

    public void inject(GgikkoApplication application) {
        applicationComponent.inject(application);
    }
}
