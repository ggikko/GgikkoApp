package ggikko.me.ggikkoapp.di.component;


import dagger.Subcomponent;
import ggikko.me.ggikkoapp.di.module.ActivityModule;
import ggikko.me.ggikkoapp.di.module.FragmentModule;
import ggikko.me.ggikkoapp.di.module.network.ApiModule;
import ggikko.me.ggikkoapp.di.qualifier.PerActivity;
import ggikko.me.ggikkoapp.ui.img.ImageActivity;

/**
 * activity component
 */
@PerActivity
@Subcomponent(modules = {ActivityModule.class, ApiModule.class})
public interface ActivityComponent {

    FragmentComponent plusFragmentComponent(FragmentModule module);

    void inject(ImageActivity imageActivity);

}
