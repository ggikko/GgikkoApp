package ggikko.me.ggikkoapp.ui.img.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

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
public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder>
        implements SearchAdapterDataModel, SearchAdapterDataView {

    private Context mContext;
    private List<Item> items;
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
        holder.search_swipe_wrapper.setSwipeEnabled(false);

        Glide.with(mContext).load(item.thumbnail).into(holder.ivThumbnail);

        holder.search_swipe_wrapper.setShowMode(SwipeLayout.ShowMode.LayDown);
        holder.search_swipe_wrapper.addDrag(SwipeLayout.DragEdge.Right, holder.search_behind_wrapper);

        holder.search_title.setText("Title :\n" + fromHtml(item.title));
        holder.search_height.setText("Height : " + item.height);
        holder.search_width.setText("Width : " + item.width);

        holder.search_surface_wrapper.setOnClickListener(view->{
            if (holder.search_swipe_wrapper.getOpenStatus() == SwipeLayout.Status.Open) {
                holder.search_swipe_wrapper.close(true);
            } else {
                holder.search_swipe_wrapper.open(true);
            }
        });

        holder.search_behind_wrapper.setOnClickListener(view ->{
            mDatabaseRealm.add(item);
            items.remove(item);
            notifySpecificItemRemoved(holder.itemView,position);
        });
    }

    /**
     * Item count 반환
     * @return
     */
    @Override
    public int getItemCount() {
        return items.size();
    }

    /**
     * Item refresh & view rendering again
     */
    @Override
    public void refresh() {
        notifyDataSetChanged();
    }

    /**
     * remove specific item & refresh view
     * @param itemView
     * @param position
     */
    @Override
    public void notifySpecificItemRemoved(View itemView, int position) {
        notifyItemRemoved(position);
        itemView.postDelayed(()->notifyItemRangeChanged(position,getItemCount()),200);
    }

    /**
     * item add
     * @param imageSearchResponse
     */
    @Override
    public void add(ImageSearchResponse imageSearchResponse) {
        items.addAll(imageSearchResponse.channel.item);
    }

    /**
     * item clear
     */
    @Override
    public void clear() {
        items.clear();
    }

    static class SearchViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_item_search_result_thumb) ImageView ivThumbnail;
        @BindView(R.id.search_swipe_wrapper) SwipeLayout search_swipe_wrapper;
        @BindView(R.id.search_behind_wrapper) RelativeLayout search_behind_wrapper;
        @BindView(R.id.search_surface_wrapper) LinearLayout search_surface_wrapper;

        @BindView(R.id.search_title) TextView search_title;
        @BindView(R.id.search_height) TextView search_height;
        @BindView(R.id.search_width) TextView search_width;

        public SearchViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    /**
     * fromHTML drprecated -> OS Version마다 분기태움
     * @param source
     * @return
     */
    public static Spanned fromHtml(String source) {
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.N) {
            // noinspection deprecation
            return Html.fromHtml(source);
        }
        return Html.fromHtml(source, Html.FROM_HTML_MODE_LEGACY);
    }
}
