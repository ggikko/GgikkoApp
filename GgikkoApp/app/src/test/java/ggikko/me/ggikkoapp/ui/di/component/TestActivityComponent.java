package ggikko.me.ggikkoapp.ui.di.component;


import dagger.Subcomponent;
import ggikko.me.ggikkoapp.ui.di.module.TestActivityModule;
import ggikko.me.ggikkoapp.ui.di.module.TestApiModule;
import ggikko.me.ggikkoapp.ui.di.module.TestFragmentModule;
import ggikko.me.ggikkoapp.ui.di.qualifier.TestPerActivity;

/**
 * activity component
 */
@TestPerActivity
@Subcomponent(modules = {TestActivityModule.class, TestApiModule.class})
public interface TestActivityComponent {

    TestFragmentComponent plusFragmentComponent(TestFragmentModule module);


}
