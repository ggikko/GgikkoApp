package ggikko.me.ggikkoapp.di.component;

import dagger.Subcomponent;
import ggikko.me.ggikkoapp.di.module.FragmentModule;
import ggikko.me.ggikkoapp.di.qualifier.PerFragment;

/**
 * fragment component
 */
@PerFragment
@Subcomponent(modules = FragmentModule.class)
public interface FragmentComponent {


}
