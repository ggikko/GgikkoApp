package ggikko.me.ggikkoapp.di.injector;


import ggikko.me.ggikkoapp.di.base.activity.InjectionActivity;
import ggikko.me.ggikkoapp.di.component.ActivityComponent;
import ggikko.me.ggikkoapp.ui.img.ImageActivity;
import lombok.Getter;

/**
 * activity injector
 */
public class ActivityInjector {

    @Getter
    private final ActivityComponent activityComponent;

    public ActivityInjector(ActivityComponent activityComponent) {
        this.activityComponent = activityComponent;
    }

    public void inject(InjectionActivity activity) {
        if (activity instanceof ImageActivity) activityComponent.inject((ImageActivity) activity);

    }
}
