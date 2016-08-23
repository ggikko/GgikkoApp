package ggikko.me.ggikkoapp.ui.img.adapter;

import ggikko.me.ggikkoapp.network.models.img.ImageSearchResponse;

/**
 * Created by ggikko on 16. 8. 11..
 */
public interface SearchAdapterDataModel {

  /**
   * modified by ggikko on 16. 8. 23..
   * Data를 더해줄 때 필요
   */
  void add(ImageSearchResponse imageSearchResponse);

  /**
   * modified by ggikko on 16. 8. 23..
   * Data adapter clear
   */
  void clear();

}
