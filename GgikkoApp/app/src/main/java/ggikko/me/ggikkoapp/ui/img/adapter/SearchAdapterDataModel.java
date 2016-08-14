package ggikko.me.ggikkoapp.ui.img.adapter;

import ggikko.me.ggikkoapp.network.models.img.ImageSearchResponse;

/**
 * Created by ggikko on 16. 8. 11..
 */
public interface SearchAdapterDataModel {

    /**
     * Data를 더해줄 때 필요
     * @param imageSearchResponse
     */
    void add(ImageSearchResponse imageSearchResponse);

    /**
     * Data adapter clear
     */
    void clear();

}
