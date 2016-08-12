package ggikko.me.ggikkoapp.ui.img.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.daimajia.swipe.SwipeLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ggikko.me.ggikkoapp.R;
import ggikko.me.ggikkoapp.network.models.img.ImageSearchResponse;
import ggikko.me.ggikkoapp.network.models.img.Item;
import ggikko.me.ggikkoapp.ui.img.listener.OnRvItemClickListener;
import ggikko.me.ggikkoapp.util.db.DatabaseRealm;

/**
 * Created by ggikko on 16. 8. 11..
 */
public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder>
        implements SearchAdapterDataModel, SearchAdapterDataView {

    private Context mContext;
    private List<Item> items;
    private OnRvItemClickListener mOnRvItemClickListener;
    private DatabaseRealm mDatabaseRealm;

    public SearchAdapter(Context context, DatabaseRealm databaseRealm) {
        this.mContext = context;
        this.mDatabaseRealm = databaseRealm;
        this.items = new ArrayList<>();
    }

    @Override
    public SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_search_row, parent, false);
        return new SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchViewHolder holder, int position) {
        final Item item = items.get(position);

        Glide.with(mContext).load(item.thumbnail).into(holder.ivThumbnail);

        holder.search_wipe_wrapper.setShowMode(SwipeLayout.ShowMode.LayDown);
        holder.search_wipe_wrapper.addDrag(SwipeLayout.DragEdge.Right, holder.search_behind_wrapper);

        holder.search_surface_wrapper.setOnClickListener(view->{
            if (holder.search_wipe_wrapper.getOpenStatus() == SwipeLayout.Status.Open) {
                holder.search_wipe_wrapper.close(true);
            } else {
                holder.search_wipe_wrapper.open(true);
            }
        });

        holder.search_behind_wrapper.setOnClickListener(view ->mDatabaseRealm.add(item));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void refresh() {
        notifyDataSetChanged();
    }

    @Override
    public void setOnRvItemClickListener(OnRvItemClickListener onRvItemClickListener) {
        this.mOnRvItemClickListener = onRvItemClickListener;
    }

    @Override
    public void add(ImageSearchResponse imageSearchResponse) {
        items.addAll(imageSearchResponse.channel.item);
    }

    @Override
    public void clear() {
        items.clear();
    }

    static class SearchViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_item_search_result_thumb) ImageView ivThumbnail;
        @BindView(R.id.search_wipe_wrapper) SwipeLayout search_wipe_wrapper;
        @BindView(R.id.search_behind_wrapper) RelativeLayout search_behind_wrapper;
        @BindView(R.id.search_surface_wrapper) LinearLayout search_surface_wrapper;

        public SearchViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
