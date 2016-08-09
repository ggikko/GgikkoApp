package ggikko.me.ggikkoapp.network.models.img;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ggikko on 16. 8. 9..
 */
public class Item {

    @SerializedName("pubDate")
    @Expose
    public String pubDate;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("thumbnail")
    @Expose
    public String thumbnail;
    @SerializedName("cp")
    @Expose
    public String cp;
    @SerializedName("height")
    @Expose
    public String height;
    @SerializedName("link")
    @Expose
    public String link;
    @SerializedName("width")
    @Expose
    public String width;
    @SerializedName("image")
    @Expose
    public String image;
}
