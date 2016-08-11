package ggikko.me.ggikkoapp.di.component;


import dagger.Subcomponent;
import ggikko.me.ggikkoapp.di.module.ActivityModule;
import ggikko.me.ggikkoapp.di.module.FragmentModule;
import ggikko.me.ggikkoapp.di.module.network.ApiModule;
import ggikko.me.ggikkoapp.di.qualifier.PerActivity;
import ggikko.me.ggikkoapp.ui.img.ImageSearchActivity;
import ggikko.me.ggikkoapp.ui.img.di.SearchComponent;
import ggikko.me.ggikkoapp.ui.img.di.SearchModule;

/**
 * activity component
 */
@PerActivity
@Subcomponent(modules = {ActivityModule.class, ApiModule.class})
public interface ActivityComponent {

    FragmentComponent plusFragmentComponent(FragmentModule fragmentModule);
    SearchComponent plusSearchComponent(SearchModule searchModule);

    void inject(ImageSearchActivity imageActivity);

}
