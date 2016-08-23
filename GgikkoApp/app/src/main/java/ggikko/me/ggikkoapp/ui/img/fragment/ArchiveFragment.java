package ggikko.me.ggikkoapp.ui.img.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

  @BindView(R.id.rv_archive)
  RecyclerView rvArchive;

  @Inject
  ArchiveAdapter archiveAdapter;
  @Inject
  LinearLayoutManager linearLayoutManager;
  @Inject
  DebugLog debugLog;

  /**
   * modified by ggikko on 16. 8. 23..
   * singleton static 생성자
   */
  public static ArchiveFragment getInstance() {
    if (mArchiveFragment == null) {
      return new ArchiveFragment();
    }
    return mArchiveFragment;
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {

    View rootView = inflater.inflate(R.layout.fragment_archive, container, false);
    ButterKnife.bind(this, rootView);

    recyclerViewSetting();
    return rootView;
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    if (getChildFragmentManager().getFragments() != null) {
      for (Fragment fragment : getChildFragmentManager().getFragments()) {
        getChildFragmentManager().beginTransaction().remove(fragment).commitAllowingStateLoss();
      }
    }
  }

  /**
   * modified by ggikko on 16. 8. 23..
   * recyclerview setting
   * @Inject adapter, layoutManager
   */
  private void recyclerViewSetting() {
    rvArchive.setLayoutManager(linearLayoutManager);
    rvArchive.setAdapter(archiveAdapter);
    rvArchive.setItemAnimator(new ScaleInAnimator());
    rvArchive.getItemAnimator().setRemoveDuration(300);
    archiveAdapter.refresh();
  }

  /**
   * modified by ggikko on 16. 8. 23..
   * Pager로 부터 불리는 call method
   */
  public void refresh() {
    if (archiveAdapter != null) archiveAdapter.refresh();
  }

}
