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
import ggikko.me.ggikkoapp.util.db.DatabaseRealm;

/**
 * Created by ggikko on 16. 8. 11..
 */
public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder>
    implements SearchAdapterDataModel, SearchAdapterDataView {

  private Context context;
  private List<Item> items;
  private DatabaseRealm databaseRealm;

  /**
   * modified by ggikko on 16. 8. 23..
   *
   * @param context       : activity's context
   * @param databaseRealm : realm wrapper object
   */
  public SearchAdapter(Context context, DatabaseRealm databaseRealm) {
    this.context = context;
    this.databaseRealm = databaseRealm;
    this.items = new ArrayList<>();
  }

  @Override
  public SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(context).inflate(R.layout.item_search_row, parent, false);
    return new SearchViewHolder(view);
  }

  @Override
  public void onBindViewHolder(SearchViewHolder holder, int position) {
    final Item item = items.get(position);
    holder.swipeWrapperLayout.setSwipeEnabled(false);

    Glide.with(context).load(item.thumbnail).into(holder.ivThumbnail);

    holder.swipeWrapperLayout.setShowMode(SwipeLayout.ShowMode.LayDown);
    holder.swipeWrapperLayout.addDrag(SwipeLayout.DragEdge.Right, holder.behindWrapperLayout);

    holder.title.setText("Title :\n" + fromHtml(item.title));
    holder.height.setText("Height : " + item.height);
    holder.width.setText("Width : " + item.width);

    holder.surfaceWrapperLayout.setOnClickListener(view -> {
      if (holder.swipeWrapperLayout.getOpenStatus() == SwipeLayout.Status.Open) {
        holder.swipeWrapperLayout.close(true);
      } else {
        holder.swipeWrapperLayout.open(true);
      }
    });

    holder.behindWrapperLayout.setOnClickListener(view -> {
      databaseRealm.add(item);
      items.remove(item);
      notifySpecificItemRemoved(holder.itemView, position);
    });
  }

  /**
   * modified by ggikko on 16. 8. 23.. Item count 반환
   */
  @Override
  public int getItemCount() {
    return items.size();
  }

  /**
   * modified by ggikko on 16. 8. 23.. Item refresh & view rendering again
   */
  @Override
  public void refresh() {
    notifyDataSetChanged();
  }

  /**
   * modified by ggikko on 16. 8. 23.. remove specific item & refresh view
   */
  @Override
  public void notifySpecificItemRemoved(View itemView, int position) {
    notifyItemRemoved(position);
    itemView.postDelayed(() -> notifyItemRangeChanged(position, getItemCount()), 200);
  }

  /**
   * modified by ggikko on 16. 8. 23.. item add
   */
  @Override
  public void add(ImageSearchResponse imageSearchResponse) {
    items.addAll(imageSearchResponse.channel.item);
  }

  /**
   * modified by ggikko on 16. 8. 23.. item clear
   */
  @Override
  public void clear() {
    items.clear();
  }

  static class SearchViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.iv_item_search_result_thumb)
    ImageView ivThumbnail;
    @BindView(R.id.search_swipe_wrapper)
    SwipeLayout swipeWrapperLayout;
    @BindView(R.id.search_behind_wrapper)
    RelativeLayout behindWrapperLayout;
    @BindView(R.id.search_surface_wrapper)
    LinearLayout surfaceWrapperLayout;

    @BindView(R.id.search_title)
    TextView title;
    @BindView(R.id.search_height)
    TextView height;
    @BindView(R.id.search_width)
    TextView width;

    public SearchViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }

  /**
   * modified by ggikko on 16. 8. 23..
   * fromHTML drprecated -> OS Version마다 분기태움
   */
  public static Spanned fromHtml(String source) {
    if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.N) {
      // noinspection deprecation
      return Html.fromHtml(source);
    }
    return Html.fromHtml(source, Html.FROM_HTML_MODE_LEGACY);
  }
}
