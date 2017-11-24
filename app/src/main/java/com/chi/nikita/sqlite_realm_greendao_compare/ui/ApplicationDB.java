package com.chi.nikita.sqlite_realm_greendao_compare.ui;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import com.chi.nikita.sqlite_realm_greendao_compare.BuildConfig;
import com.chi.nikita.sqlite_realm_greendao_compare.data.db.GreenDAOManager;
import com.chi.nikita.sqlite_realm_greendao_compare.data.db.RealmManager;
import com.chi.nikita.sqlite_realm_greendao_compare.data.db.SQLiteManager;
import com.facebook.stetho.Stetho;

import io.realm.Realm;

public class ApplicationDB extends Application {

    final Handler handler = new Handler(Looper.getMainLooper());

    @Override
    public void onCreate() {
        super.onCreate();

        //Stetho
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this);
        }

        //SQLite
        if (!SQLiteManager.isInit()) {
            SQLiteManager.init(this, handler);
        }

        //Realm
        if (!RealmManager.isInit()) {
            Realm.init(this);
        }

        //GreenDAO
        if (!GreenDAOManager.isInit()) {
            GreenDAOManager.init(this, handler);
        }
    }
}
