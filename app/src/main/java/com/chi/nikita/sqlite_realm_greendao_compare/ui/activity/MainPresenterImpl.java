package com.chi.nikita.sqlite_realm_greendao_compare.ui.activity;

import android.support.annotation.NonNull;

import com.chi.nikita.sqlite_realm_greendao_compare.data.db.RealmManager;
import com.chi.nikita.sqlite_realm_greendao_compare.data.db.SQLiteManager;
import com.chi.nikita.sqlite_realm_greendao_compare.data.model.UserModelRealm;
import com.chi.nikita.sqlite_realm_greendao_compare.data.model.UserModelSQLite;

import java.util.ArrayList;
import java.util.List;

public class MainPresenterImpl implements MainPresenter<MainView> {

    private MainView view;
    private int userCount = 100;

    @Override
    public void bindView(MainView view) {
        this.view = view;
    }

    @Override
    public void unbindView() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void insert300kUsersSQLite() {
        final List<UserModelSQLite> userModelSQLiteList = new ArrayList<>();
        for (int i = 0; i < userCount; i++) {
            final UserModelSQLite userModelSQLite = new UserModelSQLite();
            userModelSQLite.setName("Name:" + i);
            userModelSQLite.setAge(i);
            userModelSQLiteList.add(userModelSQLite);
        }
        SQLiteManager.getInstance().insertUsersToDB(userModelSQLiteList);
    }

    @Override
    public void insertUserSQLite(@NonNull UserModelSQLite userModelSQLite) {
        SQLiteManager.getInstance().insertUsersToDB(userModelSQLite);
    }

    @Override
    public void updateUserSQLite(int id, @NonNull UserModelSQLite userModelSQLite) {
        SQLiteManager.getInstance().updateUserInDB(id, userModelSQLite);
    }

    @Override
    public void deleteUserSQLite(int id) {
        SQLiteManager.getInstance().deleteUserInDB(id);
    }

    @Override
    public void getAllUsersSQLite() {

    }

    @Override
    public void insert300kUsersRealm() {
        final List<UserModelRealm> userModelRealmList = new ArrayList<>();
        for (int i = 0; i < userCount; i++) {
            final UserModelRealm userModelRealm = new UserModelRealm();
            userModelRealm.setId(i);
            userModelRealm.setName("Name:" + i);
            userModelRealm.setAge(i);
            userModelRealmList.add(userModelRealm);
        }
        RealmManager.getInstance().insertUsersToDB(userModelRealmList);
    }

    @Override
    public void insertUserRealm(@NonNull UserModelRealm userModelRealm) {
        RealmManager.getInstance().insertUsersToDB(userModelRealm);
    }

    @Override
    public void updateUserRealm(int id, @NonNull UserModelRealm userModelRealm) {
        RealmManager.getInstance().updateUserInDB(id, userModelRealm);
    }

    @Override
    public void deleteUserRealm(int id) {
        RealmManager.getInstance().deleteUserInDB(id);
    }

    @Override
    public void getAllUsersRealm() {

    }
}
