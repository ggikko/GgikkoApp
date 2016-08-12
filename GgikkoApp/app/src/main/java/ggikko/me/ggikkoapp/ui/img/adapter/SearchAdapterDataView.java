package ggikko.me.ggikkoapp.ui.img.adapter;

import android.view.View;

import ggikko.me.ggikkoapp.network.models.img.ImageSearchResponse;
import ggikko.me.ggikkoapp.ui.img.listener.OnRvItemClickListener;

/**
 * Created by ggikko on 16. 8. 11..
 */
public interface SearchAdapterDataView {

    void refresh();
    void notifySpecificItemRemoved(View itemView, int position);
}

