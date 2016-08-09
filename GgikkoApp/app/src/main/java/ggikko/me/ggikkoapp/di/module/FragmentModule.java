package ggikko.me.ggikkoapp.di.module;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import dagger.Module;
import dagger.Provides;
import ggikko.me.ggikkoapp.di.qualifier.PerFragment;

/**
 * Fragment module
 */
@Module
public class FragmentModule {
    private final Fragment fragment;

    public FragmentModule(Fragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @PerFragment
    Fragment provideFragment() {
        return fragment;
    }

    @Provides
    @PerFragment
    FragmentManager provideFragmentManager() {
        return fragment.getActivity().getSupportFragmentManager();
    }
}
