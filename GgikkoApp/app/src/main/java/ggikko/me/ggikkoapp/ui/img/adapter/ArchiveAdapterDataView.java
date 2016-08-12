package ggikko.me.ggikkoapp.ui.img.adapter;

import android.view.View;

import ggikko.me.ggikkoapp.ui.img.listener.OnRvItemClickListener;

/**
 * Created by ggikko on 16. 8. 11..
 */
public interface ArchiveAdapterDataView {

    void refresh();
    void notifySpecificItemRemoved(View itemView, int position);
}

