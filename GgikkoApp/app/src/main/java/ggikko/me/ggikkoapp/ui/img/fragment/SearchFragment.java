package ggikko.me.ggikkoapp.ui.img.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ggikko.me.ggikkoapp.R;
import ggikko.me.ggikkoapp.di.base.fragment.InjectionFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends InjectionFragment {

    private static SearchFragment mSearchFragment;

    public static SearchFragment getInstance(){
        if(mSearchFragment == null){
            return new SearchFragment();
        }
        return mSearchFragment;
    }

    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_search, container, false);
        return rootView;
    }

}
