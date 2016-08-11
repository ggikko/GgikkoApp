package ggikko.me.ggikkoapp.di.component;

import dagger.Subcomponent;
import ggikko.me.ggikkoapp.di.module.FragmentModule;
import ggikko.me.ggikkoapp.di.qualifier.PerFragment;
import ggikko.me.ggikkoapp.ui.img.di.SearchModule;
import ggikko.me.ggikkoapp.ui.img.fragment.ArchiveFragment;
import ggikko.me.ggikkoapp.ui.img.fragment.SearchFragment;

/**
 * fragment component
 */
@PerFragment
@Subcomponent(modules = FragmentModule.class)
public interface FragmentComponent {

    void inject(ArchiveFragment archiveFragment);

}
