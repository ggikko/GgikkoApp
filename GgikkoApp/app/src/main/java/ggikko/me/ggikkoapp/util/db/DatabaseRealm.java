package ggikko.me.ggikkoapp.util.db;

import android.content.Context;
import android.util.Log;

import java.util.List;

import ggikko.me.ggikkoapp.network.models.img.Item;
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

    public void setup() {
        if (realmConfiguration == null) {
            realmConfiguration = new RealmConfiguration.Builder(mContext).build();
            Realm.setDefaultConfiguration(realmConfiguration);
        } else {
            throw new IllegalStateException("database already configured");
        }
    }

    public Realm getRealmInstance() {
        return Realm.getDefaultInstance();
    }

    public <T extends RealmObject> T add(T model) {
        Realm realm = getRealmInstance();
        realm.beginTransaction();
        realm.copyToRealm(model);
        realm.commitTransaction();
        return model;
    }

    public void deleteFromArchive(String thumbnail){
        Realm realm = getRealmInstance();
        realm.beginTransaction();
        RealmResults<Item> items = realm.where(Item.class).equalTo("thumbnail", thumbnail).findAll();
        items.deleteAllFromRealm();
    }

    public <T extends RealmObject> List<T> findAll(Class<T> clazz) {
        return getRealmInstance().where(clazz).findAll();
    }

    public void close() {
        getRealmInstance().close();
    }
}
