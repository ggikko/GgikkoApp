package ggikko.me.ggikkoapp.ui.img.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import ggikko.me.ggikkoapp.util.log.DebugLog;

/**
 * A simple {@link InjectionFragment } subclass.
 */
public class ArchiveFragment extends InjectionFragment {

    private static String TAG = "ArchiveFragment";

    private static ArchiveFragment mArchiveFragment;

    @BindView(R.id.rv_archive) RecyclerView rv_archive;

    @Inject ArchiveAdapter mArchiveAdapter;
    @Inject LinearLayoutManager mLinearLayoutManager;
    @Inject DebugLog mDebugLog;

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
        mDebugLog.d("onCreateView");
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mDebugLog.d("onAttach");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDebugLog.d("onCreate");
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mDebugLog.d("onViewCreated");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mDebugLog.d("onActivityCreated");
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        mDebugLog.d("onViewStateRestored");
    }

    @Override
    public void onStart() {
        super.onStart();
        mDebugLog.d("onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        mDebugLog.d("onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        mDebugLog.d("onPause");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mDebugLog.d("onDestroyView");
        if (getChildFragmentManager().getFragments() != null) {
            for (Fragment fragment : getChildFragmentManager().getFragments()) {
                getChildFragmentManager().beginTransaction().remove(fragment).commitAllowingStateLoss();
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mDebugLog.d("onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mDebugLog.d("onDetach");
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
        if(mArchiveAdapter!=null)mArchiveAdapter.refresh();
    }

}
