package ggikko.me.ggikkoapp.ui.img.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
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
public class ArchiveAdapter extends RecyclerView.Adapter<ArchiveAdapter.ArchiveViewHolder>
        implements ArchiveAdapterDataView {

    private Context mContext;
    private List<Item> items;
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

        //swipe layout 설정
        holder.search_swipe_wrapper.setShowMode(SwipeLayout.ShowMode.LayDown);
        holder.search_swipe_wrapper.addDrag(SwipeLayout.DragEdge.Right, holder.search_behind_wrapper);

        holder.search_title.setText("Title\n" + fromHtml(item.title));
        holder.search_height.setText("Height : " + item.height);
        holder.search_width.setText("Width : " + item.width);

        holder.search_surface_wrapper.setOnClickListener(view->{
            if (isSwipeLayoutOpen(holder)) {
                holder.search_swipe_wrapper.close(true);
            } else {
                holder.search_swipe_wrapper.open(true);
            }
        });

        //row -> realm save
        holder.search_behind_wrapper.setOnClickListener(view ->mDatabaseRealm.deleteFromArchive(holder.itemView, position, this));
    }

    /**
     * Swipe Layout이 Open되었는지 checking
     * @param holder
     * @return
     */
    private boolean isSwipeLayoutOpen(ArchiveViewHolder holder) {
        return holder.search_swipe_wrapper.getOpenStatus() == SwipeLayout.Status.Open;
    }

    /**
     * Item 개수 반환
     * @return
     */
    @Override
    public int getItemCount() {
        return items.size();
    }

    /**
     * Item이 추가 되었을 때 refresh 하는 Method
     */
    @Override
    public void refresh() {
        List<Item> savedItems = mDatabaseRealm.findAll(Item.class);
        items = savedItems;
        notifyDataSetChanged();
    }

    /**
     * 특정 아이템 에니메이션 효과 -> 레이아웃 중복방지를 위한 200 post delay
     * @param itemView
     * @param position
     */
    @Override
    public void notifySpecificItemRemoved(View itemView, int position) {
        notifyItemRemoved(position);
        itemView.postDelayed(()->notifyItemRangeChanged(position,getItemCount()),200);
    }

    static class ArchiveViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_item_search_result_thumb) ImageView ivThumbnail;
        @BindView(R.id.search_swipe_wrapper) SwipeLayout search_swipe_wrapper;
        @BindView(R.id.search_behind_wrapper) RelativeLayout search_behind_wrapper;
        @BindView(R.id.search_surface_wrapper) LinearLayout search_surface_wrapper;

        @BindView(R.id.search_title) TextView search_title;
        @BindView(R.id.search_height) TextView search_height;
        @BindView(R.id.search_width) TextView search_width;

        public ArchiveViewHolder(View itemView) {
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
