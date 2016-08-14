package ggikko.me.ggikkoapp.util.db;

import android.content.Context;
import android.util.Log;
import android.view.View;

import java.util.List;

import ggikko.me.ggikkoapp.network.models.img.Item;
import ggikko.me.ggikkoapp.ui.img.adapter.ArchiveAdapterDataView;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.RealmResults;

/**
 * Created by ggikko on 16. 8. 12..
 */
public class DatabaseRealm {

    private RealmConfiguration realmConfiguration;

    private Context mContext;

    public DatabaseRealm(Context context) {
        this.mContext = context;
        setup();
    }

    /**
     * Realm setup
     */
    public void setup() {
        if (realmConfiguration == null) {
            realmConfiguration = new RealmConfiguration.Builder(mContext).build();
            Realm.setDefaultConfiguration(realmConfiguration);
        } else {
            throw new IllegalStateException("database already configured");
        }
    }

    /**
     * Realm instance 제공
     * @return
     */
    public Realm getRealmInstance() {
        return Realm.getDefaultInstance();
    }

    /**
     * Model add
     * @param model
     * @param <T>
     * @return
     */
    public <T extends RealmObject> T add(T model) {
        Realm realmInstance = getRealmInstance();
        realmInstance.executeTransactionAsync(realm -> realm.copyToRealm(model));
        return model;
    }

    /**
     * Specific Item removed & request View reload
     * @param itemView
     * @param position
     * @param archiveAdapterDataView
     */
    public void deleteFromArchive(View itemView, final int position, ArchiveAdapterDataView archiveAdapterDataView){
        Realm realmInstance = getRealmInstance();
        realmInstance.executeTransactionAsync(realm ->{
            RealmResults<Item> items = realm.where(Item.class).findAll();
            if(position<items.size())items.get(position).deleteFromRealm();
        },()-> archiveAdapterDataView.notifySpecificItemRemoved(itemView,position));
    }

    /**
     * Find all items
     * @param clazz
     * @param <T>
     * @return
     */
    public <T extends RealmObject> List<T> findAll(Class<T> clazz) {
        return getRealmInstance().where(clazz).findAll();
    }

    public void close() {
        getRealmInstance().close();
    }
}
