package ggikko.me.ggikkoapp.ui.di.component;

import dagger.Subcomponent;
import ggikko.me.ggikkoapp.ui.di.module.TestFragmentModule;
import ggikko.me.ggikkoapp.ui.di.qualifier.TestPerFragment;

/**
 * fragment component
 */
@TestPerFragment
@Subcomponent(modules = TestFragmentModule.class)
public interface TestFragmentComponent {


}
