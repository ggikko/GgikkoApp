package ggikko.me.ggikkoapp.ui.img.adapter;

import android.view.View;

import ggikko.me.ggikkoapp.ui.img.listener.OnRvItemClickListener;

/**
 * Created by ggikko on 16. 8. 11..
 */
public interface ArchiveAdapterDataView {

  /**
   * modified by ggikko on 16. 8. 23..
   * Data adapter refresh
   */
  void refresh();

  /**
   * modified by ggikko on 16. 8. 23..
   * specific item removed 일부만 변경시켜 애니메이이션 적용
   */
  void notifySpecificItemRemoved(View itemView, int position);
}

