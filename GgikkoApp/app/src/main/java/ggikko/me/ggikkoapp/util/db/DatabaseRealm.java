package ggikko.me.ggikkoapp.util.db;

import android.content.Context;
import android.util.Log;
import android.view.View;

import java.util.List;

import ggikko.me.ggikkoapp.network.models.img.Item;
import ggikko.me.ggikkoapp.ui.img.adapter.ArchiveAdapterDataView;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmModel;
import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.Sort;

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
   */
  public Realm getRealmInstance() {
    return Realm.getDefaultInstance();
  }

  /**
   * Model add
   */
  public <T extends RealmObject> T add(T model) {
    Realm realmInstance = getRealmInstance();
    realmInstance.executeTransaction(realm -> realm.copyToRealm(model));
    return model;
  }

  /**
   * 특정 객체의 PK의 최대 INDEX 반환
   */
  public long getMaxIndex(Class clazz) {
    Realm realmInstance = getRealmInstance();
    Number number = realmInstance.where(clazz).max("id");
    return number == null ? 0 : number.longValue();
  }

  /**
   * Specific Item removed & request View reload
   * @param clazz
   * @param id
   */
  public void deleteFromArchive(Class clazz, Long id) {
    Realm realmInstance = getRealmInstance();
    RealmResults result = realmInstance.where(clazz).equalTo("id", id).findAll();
    if (result != null) realmInstance.executeTransaction(realm -> result.deleteAllFromRealm());
  }

  /**
   * Find all items - Sort.ASC, PK
   */
  public <T extends RealmObject> List<T> findAll(Class<T> clazz, String key) {
    return getRealmInstance().where(clazz).findAll().sort(key, Sort.ASCENDING);
  }

  public void close() {
    getRealmInstance().close();
  }
}
