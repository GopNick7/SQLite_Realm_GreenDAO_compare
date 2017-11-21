package com.chi.nikita.sqlite_realm_greendao_compare.ui.activity;


import android.support.annotation.NonNull;

import com.chi.nikita.sqlite_realm_greendao_compare.data.model.UserModelRealm;
import com.chi.nikita.sqlite_realm_greendao_compare.data.model.UserModelSQLite;
import com.chi.nikita.sqlite_realm_greendao_compare.ui.Presenter;
import com.chi.nikita.sqlite_realm_greendao_compare.ui.View;

public interface MainPresenter<T extends View> extends Presenter<T> {

    void insert300kUsersSQLite();

    void insertUserSQLite(@NonNull UserModelSQLite userModelSQLite);

    void updateUserSQLite(int id, @NonNull UserModelSQLite userModelSQLite);

    void deleteUserSQLite(int id);

    void getAllUsersSQLite();

    void insert300kUsersRealm();

    void insertUserRealm(@NonNull UserModelRealm userModelRealm);

    void updateUserRealm(int id, @NonNull UserModelRealm userModelRealm);

    void deleteUserRealm(int id);

    void getAllUsersRealm();
}
