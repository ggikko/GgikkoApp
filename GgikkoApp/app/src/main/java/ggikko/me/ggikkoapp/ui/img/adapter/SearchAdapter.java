package ggikko.me.ggikkoapp.ui.img.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ggikko.me.ggikkoapp.R;
import ggikko.me.ggikkoapp.network.models.img.ImageSearchResponse;
import ggikko.me.ggikkoapp.network.models.img.Item;
import ggikko.me.ggikkoapp.ui.img.listener.OnRvItemClickListener;

/**
 * Created by ggikko on 16. 8. 11..
 */
public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder>
        implements SearchAdapterDataModel, SearchAdapterDataView {

    private Context mContext;
    private List<Item> items;
    private OnRvItemClickListener mOnRvItemClickListener;

    public SearchAdapter(Context context) {
        this.mContext = context;
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

        holder.itemView.setOnClickListener(view->{
            mOnRvItemClickListener.onItemClick(this, position);
        });
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

        public SearchViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
