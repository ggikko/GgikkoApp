package ggikko.me.ggikkoapp.ui.img.listener;

import android.support.v7.widget.RecyclerView;

/**
 * recycler view item click을 위한 listener
 * Adapter와 position을 반환하여 제어할 수 있게 해준다.
 */
public interface OnRvItemClickListener {
    void onItemClick(RecyclerView.Adapter adapter, int position);
}
