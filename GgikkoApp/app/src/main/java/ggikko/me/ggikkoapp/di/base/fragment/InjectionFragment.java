package ggikko.me.ggikkoapp.di.base.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import ggikko.me.ggikkoapp.GgikkoApplication;
import ggikko.me.ggikkoapp.di.injector.FragmentInjector;
import lombok.AccessLevel;
import lombok.Getter;

/**
 * fragment for injection, v4.fragment
 */
public class InjectionFragment extends Fragment {

    @Getter(value = AccessLevel.PACKAGE)
    private FragmentInjector fragmentInjector;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        inject();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /** inject */
    private void inject() {
        final GgikkoApplication application = ((GgikkoApplication) getActivity().getApplication());
        fragmentInjector = application.getInjectorCreator().makeFragmentInjector(this, getContext());
        fragmentInjector.inject(this);
    }

}
