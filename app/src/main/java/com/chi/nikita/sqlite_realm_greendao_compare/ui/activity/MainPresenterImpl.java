package com.chi.nikita.sqlite_realm_greendao_compare.ui.activity;

import android.support.annotation.NonNull;

import com.chi.nikita.sqlite_realm_greendao_compare.data.db.GreenDAOManager;
import com.chi.nikita.sqlite_realm_greendao_compare.data.db.RealmManager;
import com.chi.nikita.sqlite_realm_greendao_compare.data.db.SQLiteManager;
import com.chi.nikita.sqlite_realm_greendao_compare.data.model.UserModel;
import com.chi.nikita.sqlite_realm_greendao_compare.data.model.UserModelGreenDAO;
import com.chi.nikita.sqlite_realm_greendao_compare.data.model.UserModelRealm;
import com.chi.nikita.sqlite_realm_greendao_compare.data.model.UserModelSQLite;

import java.util.ArrayList;
import java.util.List;

public class MainPresenterImpl implements MainPresenter<MainView> {

    private MainView view;
    private int userCount = 300_000;
    private SQLiteManager.ResultListener resultListenerFromSQLite;
    private RealmManager.ResultListener resultListenerFromRealm;
    private GreenDAOManager.ResultListener resultListenerFromGreenDAO;

    @Override
    public void bindView(@NonNull final MainView view) {
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
            userModelSQLite.setName("SQLite:" + i);
            userModelSQLite.setAge(i);
            userModelSQLiteList.add(userModelSQLite);
        }
        SQLiteManager.getInstance().insertUsersToDB(userModelSQLiteList);
    }

    @Override
    public void insertUserSQLite(@NonNull final UserModelSQLite userModelSQLite) {
        SQLiteManager.getInstance().insertUsersToDB(userModelSQLite);
    }

    @Override
    public void updateUserSQLite(final long id, @NonNull final UserModelSQLite userModelSQLite) {
        SQLiteManager.getInstance().updateUserInDB(id, userModelSQLite);
    }

    @Override
    public void deleteUserSQLite(final long id) {
        SQLiteManager.getInstance().deleteUserInDB(id);
    }

    @Override
    public void getAllUsersSQLite() {
        resultListenerFromSQLite = new SQLiteManager.ResultListener() {
            @Override
            public void onSuccess(@NonNull final List<UserModelSQLite> userModelSQLiteList) {
                view.onShowUsers(userModelSQLiteList);
            }
        };
        SQLiteManager.getInstance().getAllUsersFromDB(resultListenerFromSQLite);
    }

    @Override
    public void insert300kUsersRealm() {
        final List<UserModelRealm> userModelRealmList = new ArrayList<>();
        for (int i = 0; i < userCount; i++) {
            final UserModelRealm userModelRealm = new UserModelRealm();
            userModelRealm.setId(i);
            userModelRealm.setName("Realm:" + i);
            userModelRealm.setAge(i);
            userModelRealmList.add(userModelRealm);
        }
        RealmManager.getInstance().insertUsersToDB(userModelRealmList);
    }

    @Override
    public void insertUserRealm(@NonNull final UserModelRealm userModelRealm) {
        RealmManager.getInstance().insertUsersToDB(userModelRealm);
    }

    @Override
    public void updateUserRealm(final long id, @NonNull final UserModelRealm userModelRealm) {
        RealmManager.getInstance().updateUserInDB(id, userModelRealm);
    }

    @Override
    public void deleteUserRealm(final long id) {
        RealmManager.getInstance().deleteUserInDB(id);
    }

    @Override
    public void getAllUsersRealm() {
        resultListenerFromRealm = new RealmManager.ResultListener() {
            @Override
            public void onSuccess(@NonNull final List<UserModel> userModelRealmList) {
                view.onShowUsers(userModelRealmList);
            }
        };
        RealmManager.getInstance().getAllUsersFromDB(resultListenerFromRealm);
    }

    @Override
    public void insert300kUsersGreenDAO() {
        final List<UserModelGreenDAO> userModelGreenDAOList = new ArrayList<>();
        for (int i = 0; i < userCount; i++) {
            final UserModelGreenDAO userModelGreenDAO = new UserModelGreenDAO();
            userModelGreenDAO.setId(i);
            userModelGreenDAO.setName("GreenDAO:" + i);
            userModelGreenDAO.setAge(i);
            userModelGreenDAOList.add(userModelGreenDAO);
        }
        GreenDAOManager.getInstance().insertUsersToDB(userModelGreenDAOList);
    }

    @Override
    public void insertUserGreenDAO(@NonNull final UserModelGreenDAO userModelGreenDAO) {
        GreenDAOManager.getInstance().insertUsersToDB(userModelGreenDAO);
    }

    @Override
    public void updateUserGreenDAO(final long id, @NonNull UserModelGreenDAO userModelGreenDAO) {
        GreenDAOManager.getInstance().updateUserInDB(id, userModelGreenDAO);
    }

    @Override
    public void deleteUserGreenDAO(final long id) {
        GreenDAOManager.getInstance().deleteUserInDB(id);
    }

    @Override
    public void getAllUsersGreenDAO() {
        resultListenerFromGreenDAO = new GreenDAOManager.ResultListener() {
            @Override
            public void onSuccess(@NonNull final List<UserModelGreenDAO> userModelGreenDAOList) {
                view.onShowUsers(userModelGreenDAOList);
            }
        };
        GreenDAOManager.getInstance().getAllUsersFromDB(resultListenerFromGreenDAO);
    }
}
