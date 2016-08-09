package ggikko.me.ggikkoapp.di.injector;


import ggikko.me.ggikkoapp.GgikkoApplication;
import ggikko.me.ggikkoapp.di.component.ApplicationComponent;
import lombok.Getter;

/**
 * Application Injector
 */
public class ApplicationInjector {

    @Getter
    private final ApplicationComponent applicationComponent;

    public ApplicationInjector(ApplicationComponent applicationComponent) {
        this.applicationComponent = applicationComponent;
    }

    public void inject(GgikkoApplication application) {
        applicationComponent.inject(application);
    }
}
