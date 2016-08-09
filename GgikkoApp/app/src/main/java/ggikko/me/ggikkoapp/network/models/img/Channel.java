package ggikko.me.ggikkoapp.network.models.img;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 16. 8. 9..
 */
public class Channel {
    @SerializedName("result")
    @Expose
    public String result;
    @SerializedName("pageCount")
    @Expose
    public String pageCount;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("totalCount")
    @Expose
    public String totalCount;
    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("item")
    @Expose
    public List<Item> item = new ArrayList<Item>();
    @SerializedName("lastBuildDate")
    @Expose
    public String lastBuildDate;
    @SerializedName("link")
    @Expose
    public String link;
    @SerializedName("generator")
    @Expose
    public String generator;

}
