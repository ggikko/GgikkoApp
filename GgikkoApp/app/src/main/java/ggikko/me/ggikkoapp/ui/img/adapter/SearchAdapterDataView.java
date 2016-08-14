package ggikko.me.ggikkoapp.ui.img.adapter;

import android.view.View;

import ggikko.me.ggikkoapp.network.models.img.ImageSearchResponse;
import ggikko.me.ggikkoapp.ui.img.listener.OnRvItemClickListener;

/**
 * Created by ggikko on 16. 8. 11..
 */
public interface SearchAdapterDataView {

    /**
     * Data adapter refresh
     */
    void refresh();

    /**
     * specific item removed
     * 일부만 변경시켜 애니메이이션 적용
     * @param itemView
     * @param position
     */
    void notifySpecificItemRemoved(View itemView, int position);
}

