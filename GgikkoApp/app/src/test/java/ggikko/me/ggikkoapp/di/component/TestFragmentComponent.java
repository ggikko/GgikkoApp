package ggikko.me.ggikkoapp.di.component;

import dagger.Subcomponent;
import ggikko.me.ggikkoapp.di.module.FragmentModule;
import ggikko.me.ggikkoapp.di.module.TestFragmentModule;
import ggikko.me.ggikkoapp.di.qualifier.PerFragment;
import ggikko.me.ggikkoapp.di.qualifier.TestPerFragment;

/**
 * fragment component
 */
@TestPerFragment
@Subcomponent(modules = TestFragmentModule.class)
public interface TestFragmentComponent {


}
