package ggikko.me.ggikkoapp.ui.img.adapter;

import ggikko.me.ggikkoapp.network.models.img.ImageSearchResponse;

/**
 * Created by admin on 16. 8. 11..
 */
public interface SearchAdapterDataModel {

    void add(ImageSearchResponse imageSearchResponse);
    void clear();

}
