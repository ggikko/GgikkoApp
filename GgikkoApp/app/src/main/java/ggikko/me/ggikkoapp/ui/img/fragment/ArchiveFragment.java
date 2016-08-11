package ggikko.me.ggikkoapp.ui.img.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ggikko.me.ggikkoapp.R;
import ggikko.me.ggikkoapp.di.base.fragment.InjectionFragment;

/**
 * A simple {@link InjectionFragment } subclass.
 */
public class ArchiveFragment extends InjectionFragment {

    private static ArchiveFragment mArchiveFragment;

    public static ArchiveFragment getInstance(){
        if(mArchiveFragment == null){
            return new ArchiveFragment();
        }
        return mArchiveFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_archive, container, false);
        return rootView;
    }

}
