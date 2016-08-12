package ggikko.me.ggikkoapp.network.models.img;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by ggikko on 16. 8. 9..
 */

@Getter
@Setter
public class Item extends RealmObject {

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
