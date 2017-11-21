package com.chi.nikita.sqlite_realm_greendao_compare.ui.activity;

import android.support.annotation.NonNull;

import com.chi.nikita.sqlite_realm_greendao_compare.data.db.SQLiteManager;
import com.chi.nikita.sqlite_realm_greendao_compare.data.model.UserModelRealm;
import com.chi.nikita.sqlite_realm_greendao_compare.data.model.UserModelSQLite;

import java.util.ArrayList;
import java.util.List;

public class MainPresenterImpl implements MainPresenter<MainView> {

    private UserModelSQLite userModelSQLite;
    private MainView view;

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
        userModelSQLite = new UserModelSQLite();
        final List<UserModelSQLite> userModelSQLiteList = new ArrayList<>();
        for (int i = 0; i < 300_000; i++) {
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

    }

    @Override
    public void insertUserRealm(@NonNull UserModelRealm userModelRealm) {

    }

    @Override
    public void updateUserRealm(int id, @NonNull UserModelRealm userModelRealm) {

    }

    @Override
    public void deleteUserRealm(int id) {

    }

    @Override
    public void getAllUsersRealm() {

    }
}
