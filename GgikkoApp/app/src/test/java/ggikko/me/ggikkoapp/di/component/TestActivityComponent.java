package ggikko.me.ggikkoapp.di.component;


import dagger.Subcomponent;
import ggikko.me.ggikkoapp.di.module.TestActivityModule;
import ggikko.me.ggikkoapp.di.module.TestApiModule;
import ggikko.me.ggikkoapp.di.module.TestFragmentModule;
import ggikko.me.ggikkoapp.di.qualifier.TestPerActivity;

/**
 * activity component
 */
@TestPerActivity
@Subcomponent(modules = {TestActivityModule.class, TestApiModule.class})
public interface TestActivityComponent {

    TestFragmentComponent plusFragmentComponent(TestFragmentModule module);

//    void inject(ImageSearchActivity imageActivity);

}
