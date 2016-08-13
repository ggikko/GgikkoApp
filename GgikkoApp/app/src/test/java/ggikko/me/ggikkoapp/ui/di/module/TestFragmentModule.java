package ggikko.me.ggikkoapp.ui.di.module;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import dagger.Module;
import dagger.Provides;
import ggikko.me.ggikkoapp.ui.di.qualifier.TestPerFragment;

/**
 * Fragment module
 */
@Module
public class TestFragmentModule {
    private final Fragment fragment;

    public TestFragmentModule(Fragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @TestPerFragment
    Fragment provideFragment() {
        return fragment;
    }

    @Provides
    @TestPerFragment
    FragmentManager provideFragmentManager() {
        return fragment.getActivity().getSupportFragmentManager();
    }
}
