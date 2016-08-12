package ggikko.me.ggikkoapp.util.db;

import android.content.Context;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;

/**
 * Created by ggikko on 16. 8. 12..
 */
public class DatabaseRealm {

    private RealmConfiguration realmConfiguration;

    private Context mContext;

    public DatabaseRealm(Context context) {
        this.mContext = context;
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

    public <T extends RealmObject> List<T> findAll(Class<T> clazz) {
        return getRealmInstance().where(clazz).findAll();
    }
}
