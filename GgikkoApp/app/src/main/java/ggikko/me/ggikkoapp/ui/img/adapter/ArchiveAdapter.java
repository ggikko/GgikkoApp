package ggikko.me.ggikkoapp.ui.img.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.daimajia.swipe.SwipeLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ggikko.me.ggikkoapp.R;
import ggikko.me.ggikkoapp.network.models.img.ImageSearchResponse;
import ggikko.me.ggikkoapp.network.models.img.Item;
import ggikko.me.ggikkoapp.ui.img.listener.OnRvItemClickListener;
import ggikko.me.ggikkoapp.util.db.DatabaseRealm;

/**
 * Created by ggikko on 16. 8. 11..
 */
public class ArchiveAdapter extends RecyclerView.Adapter<ArchiveAdapter.ArchiveViewHolder>
        implements ArchiveAdapterDataView {

    private Context mContext;
    private List<Item> items;
    private OnRvItemClickListener mOnRvItemClickListener;
    private DatabaseRealm mDatabaseRealm;

    public ArchiveAdapter(Context context, DatabaseRealm databaseRealm) {
        this.mContext = context;
        this.mDatabaseRealm = databaseRealm;
        this.items = new ArrayList<>();
    }

    @Override
    public ArchiveViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_archive_row, parent, false);
        return new ArchiveViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ArchiveViewHolder holder, int position) {
        final Item item = items.get(position);
        holder.search_swipe_wrapper.setSwipeEnabled(false);

        Glide.with(mContext).load(item.thumbnail).into(holder.ivThumbnail);

        holder.search_swipe_wrapper.setShowMode(SwipeLayout.ShowMode.LayDown);
        holder.search_swipe_wrapper.addDrag(SwipeLayout.DragEdge.Right, holder.search_behind_wrapper);

        holder.search_surface_wrapper.setOnClickListener(view->{
            if (isSwipeLayoutOpen(holder)) {
                holder.search_swipe_wrapper.close(true);
            } else {
                holder.search_swipe_wrapper.open(true);
            }
        });

        holder.search_behind_wrapper.setOnClickListener(view ->mDatabaseRealm.deleteFromArchive(holder.itemView, position, this));
    }

    private boolean isSwipeLayoutOpen(ArchiveViewHolder holder) {
        return holder.search_swipe_wrapper.getOpenStatus() == SwipeLayout.Status.Open;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void refresh() {
        List<Item> savedItems = mDatabaseRealm.findAll(Item.class);
        items = savedItems;
        notifyDataSetChanged();
    }

    @Override
    public void notifySpecificItemRemoved(View itemView, int position) {
        notifyItemRemoved(position);
        itemView.postDelayed(()->notifyItemRangeChanged(position,getItemCount()),200);
    }

    @Override
    public void setOnRvItemClickListener(OnRvItemClickListener onRvItemClickListener) {
        this.mOnRvItemClickListener = onRvItemClickListener;
    }

    static class ArchiveViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_item_search_result_thumb) ImageView ivThumbnail;
        @BindView(R.id.search_swipe_wrapper) SwipeLayout search_swipe_wrapper;
        @BindView(R.id.search_behind_wrapper) RelativeLayout search_behind_wrapper;
        @BindView(R.id.search_surface_wrapper) LinearLayout search_surface_wrapper;

        public ArchiveViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
