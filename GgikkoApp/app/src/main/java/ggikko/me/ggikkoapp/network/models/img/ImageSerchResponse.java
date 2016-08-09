package ggikko.me.ggikkoapp.network.models.img;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ggikko on 16. 8. 9..
 */
public class ImageSerchResponse {
    @SerializedName("channel")
    @Expose
    public Channel channel;
}
