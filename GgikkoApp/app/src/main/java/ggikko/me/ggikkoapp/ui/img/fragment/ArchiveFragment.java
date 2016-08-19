package ggikko.me.ggikkoapp.ui.img.fragment;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ggikko.me.ggikkoapp.R;
import ggikko.me.ggikkoapp.di.base.fragment.InjectionFragment;
import ggikko.me.ggikkoapp.ui.img.adapter.ArchiveAdapter;
import ggikko.me.ggikkoapp.util.animator.ScaleInAnimator;

/**
 * A simple {@link InjectionFragment } subclass.
 */
public class ArchiveFragment extends InjectionFragment {

    private static ArchiveFragment mArchiveFragment;

    @BindView(R.id.rv_archive) RecyclerView rv_archive;

    @Inject ArchiveAdapter mArchiveAdapter;
    @Inject LinearLayoutManager mLinearLayoutManager;

    /**
     * singleton static 생성자
     * @return
     */
    public static ArchiveFragment getInstance(){
        if(mArchiveFragment == null){
            return new ArchiveFragment();
        }
        return mArchiveFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_archive, container, false);
        ButterKnife.bind(this, rootView);

        recyclerViewSetting();

        return rootView;
    }

    /**
     * recyclerview setting
     * @Inject adapter, layoutManager
     */
    private void recyclerViewSetting() {
        rv_archive.setLayoutManager(mLinearLayoutManager);
        rv_archive.setAdapter(mArchiveAdapter);
        rv_archive.setItemAnimator(new ScaleInAnimator());
        rv_archive.getItemAnimator().setRemoveDuration(300);
        mArchiveAdapter.refresh();
    }

    /**
     * Pager로 부터 불리는 call method
     *
     */
    public void refresh() {
        Log.e("ggikko", "refresh");
        if(mArchiveAdapter!=null)mArchiveAdapter.refresh();
    }
}
