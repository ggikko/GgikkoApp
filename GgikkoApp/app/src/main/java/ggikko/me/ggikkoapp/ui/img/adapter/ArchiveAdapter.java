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
import ggikko.me.ggikkoapp.network.models.img.Item;
import ggikko.me.ggikkoapp.util.db.DatabaseRealm;

/**
 * Created by ggikko on 16. 8. 11..
 */
public class ArchiveAdapter extends RecyclerView.Adapter<ArchiveAdapter.ArchiveViewHolder>
    implements ArchiveAdapterDataView {

  private Context context;
  private List<Item> items;
  private DatabaseRealm databaseRealm;

  /**
   * Created by ggikko on 16. 8. 23.. 생성자 context, realm, item 필요
   *
   * @param context       - activity의 문맥
   * @param databaseRealm - database realm 객체
   */
  public ArchiveAdapter(Context context, DatabaseRealm databaseRealm) {
    this.context = context;
    this.databaseRealm = databaseRealm;
    this.items = new ArrayList<>();
  }

  @Override
  public ArchiveViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(context).inflate(R.layout.item_archive_row, parent, false);
    return new ArchiveViewHolder(view);
  }

  @Override
  public void onBindViewHolder(ArchiveViewHolder holder, int position) {
    final Item item = items.get(position);
    holder.swipeWrapperLayout.setSwipeEnabled(false);

    Glide.with(context).load(item.thumbnail).into(holder.ivThumbnail);

    //swipe layout 설정
    holder.swipeWrapperLayout.setShowMode(SwipeLayout.ShowMode.LayDown);
    holder.swipeWrapperLayout.addDrag(SwipeLayout.DragEdge.Right, holder.searchBehindWrapperLayout);

    holder.title.setText("Title\n" + fromHtml(item.title));
    holder.height.setText("Height : " + item.height);
    holder.width.setText("Width : " + item.width);

    holder.searchSurfaceWrapperLayout.setOnClickListener(view -> {
      if (isSwipeLayoutOpen(holder)) {
        holder.swipeWrapperLayout.close(true);
      } else {
        holder.swipeWrapperLayout.open(true);
      }
    });

    //row -> realm save
    holder.searchBehindWrapperLayout.setOnClickListener(
        view -> {
          Long id = items.get(position).getId();
          databaseRealm.deleteFromArchive(Item.class, id);
          notifySpecificItemRemoved(holder.itemView, position);
        });
  }

  /**
   * Created by ggikko on 16. 8. 23.. Swipe Layout이 Open되었는지 checking
   */
  private boolean isSwipeLayoutOpen(ArchiveViewHolder holder) {
    return holder.swipeWrapperLayout.getOpenStatus() == SwipeLayout.Status.Open;
  }

  /**
   * Created by ggikko on 16. 8. 23.. Item 개수 반환
   */
  @Override
  public int getItemCount() {
    return items.size();
  }

  /**
   * Created by ggikko on 16. 8. 23.. Item이 추가 되었을 때 refresh 하는 Method
   */
  @Override
  public void refresh() {
    List<Item> savedItems = databaseRealm.findAll(Item.class, "id");
    items = savedItems;
    notifyDataSetChanged();
  }

  /**
   * Created by ggikko on 16. 8. 23.. 특정 아이템 에니메이션 효과 -> 레이아웃 중복방지를 위한 200 post delay
   */
  @Override
  public void notifySpecificItemRemoved(View itemView, int position) {
    refresh();
//    itemView.postDelayed(() -> notifyItemRangeChanged(position, getItemCount()), 200);
  }

  static class ArchiveViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.iv_item_search_result_thumb)
    ImageView ivThumbnail;
    @BindView(R.id.search_swipe_wrapper)
    SwipeLayout swipeWrapperLayout;
    @BindView(R.id.search_behind_wrapper)
    RelativeLayout searchBehindWrapperLayout;
    @BindView(R.id.search_surface_wrapper)
    LinearLayout searchSurfaceWrapperLayout;

    @BindView(R.id.search_title)
    TextView title;
    @BindView(R.id.search_height)
    TextView height;
    @BindView(R.id.search_width)
    TextView width;

    public ArchiveViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }

  /**
   * Created by ggikko on 16. 8. 23..
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
