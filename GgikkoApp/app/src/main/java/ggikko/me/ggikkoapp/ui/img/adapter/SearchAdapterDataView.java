package ggikko.me.ggikkoapp.ui.img.adapter;

import ggikko.me.ggikkoapp.network.models.img.ImageSearchResponse;
import ggikko.me.ggikkoapp.ui.img.listener.OnRvItemClickListener;

/**
 * Created by admin on 16. 8. 11..
 */
public interface SearchAdapterDataView {

    void refresh();
    void setOnRvItemClickListener(OnRvItemClickListener onRvItemClickListener);
}

