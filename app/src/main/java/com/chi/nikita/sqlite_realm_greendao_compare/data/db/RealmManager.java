package com.chi.nikita.sqlite_realm_greendao_compare.data.db;

import android.support.annotation.NonNull;
import android.util.Log;

import com.chi.nikita.sqlite_realm_greendao_compare.data.model.UserModelRealm;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class RealmManager {

    private static RealmManager instance;
    private RealmConfiguration config;
    private Realm realm;
    private Executor executor;

    private RealmManager() {
//        executor = Executors.newSingleThreadExecutor();
//        executor.execute(new Runnable() {
//            @Override
//            public void run() {
        config = new RealmConfiguration.Builder().name("REALM_DB").build();
        realm = Realm.getDefaultInstance();
        Realm.setDefaultConfiguration(config);
//            }
//        });
    }


    public static RealmManager getInstance() {
        if (instance == null) {
            instance = new RealmManager();
        }
        return instance;
    }

    public static boolean isInit() {
        return instance != null;
    }

    public void insertUsersToDB(final @NonNull List<UserModelRealm> userModelRealmList) {
        final long l = System.currentTimeMillis();
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(final @NonNull Realm realm) {
                realm.insert(userModelRealmList);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                Log.d("TAG", "SUCCESS insertUsersToRealm: " + userModelRealmList.size() + " rows in " + (System.currentTimeMillis() - l) + "ms");
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                Log.d("TAG", "FAILED insertUsersToRealm: " + userModelRealmList.size() + " rows in " + (System.currentTimeMillis() - l) + "ms");
            }
        });
//        executor.execute(new Runnable() {
//            @Override
//            public void run() {
//                long l = System.currentTimeMillis();
//                realm.beginTransaction();
//                realm.insert(userModelRealmList);
//                realm.commitTransaction();
//                Log.d("TAG", "SUCCESS insertUsersToDBRealm: " + userModelRealmList.size() + " rows in " + (System.currentTimeMillis() - l) + "ms");
//            }
//        });
    }

    public void insertUsersToDB(final @NonNull UserModelRealm userModelRealm) {
        final long l = System.currentTimeMillis();
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(@NonNull final Realm realm) {
                realm.insert(userModelRealm);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                Log.d("TAG", "SUCCESS insertUserToRealm: " + 1 + " row in " + (System.currentTimeMillis() - l) + "ms");
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                Log.d("TAG", "FAILED insertUserToRealm: " + 1 + " row in " + (System.currentTimeMillis() - l) + "ms");
            }
        });
    }

    public void updateUserInDB(final long id, @NonNull final UserModelRealm userModelRealm) {
        final long l = System.currentTimeMillis();
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(@NonNull final Realm realm) {
                UserModelRealm userModel = realm.where(UserModelRealm.class).equalTo("id", id).findFirst();
                userModel.setAge(userModelRealm.getAge());
                userModel.setName(userModelRealm.getName());
                realm.insertOrUpdate(userModel);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                Log.d("TAG", "SUCCESS updateUserInRealm: " + 1 + " row in " + (System.currentTimeMillis() - l) + "ms");
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                Log.d("TAG", "FAILED updateUserInRealm: " + 1 + " row in " + (System.currentTimeMillis() - l) + "ms");
            }
        });
    }

    public void deleteUserInDB(final long id) {
        final long l = System.currentTimeMillis();
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                UserModelRealm userModel = realm.where(UserModelRealm.class).equalTo("id", id).findFirst();
                userModel.deleteFromRealm();
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                Log.d("TAG", "SUCCESS deleteUserInRealm: " + 1 + " row in " + (System.currentTimeMillis() - l) + "ms");
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                Log.d("TAG", "FAILED deleteUserInRealm: " + 1 + " row in " + (System.currentTimeMillis() - l) + "ms");
            }
        });
    }

//
//    @Override
//    public void getAllStudents(OnGetAllStudentsCallback callback) {
//        Realm realm = Realm.getInstance(SimpleRealmApp.getInstance());
//        RealmResults results = realm.where(Student.class).findAll();
//        if (callback != null)
//            callback.onSuccess(results);
//    }
//
//    @Override
//    public void getAllStudentsByUniversityId(String id, OnGetStudentsCallback callback) {
//        Realm realm = Realm.getInstance(SimpleRealmApp.getInstance());
//        University university = realm.where(University.class).equalTo(RealmTable.ID, id).findFirst();
//        RealmList students = university.getStudents();
//        if (callback != null)
//            callback.onSuccess(students);
//    }
//
//    @Override
//    public void getStudentById(String id, OnGetStudentByIdCallback callback) {
//        Realm realm = Realm.getInstance(SimpleRealmApp.getInstance());
//        Student student = realm.where(Student.class).equalTo(RealmTable.ID, id).findFirst();
//        if (callback != null)
//            callback.onSuccess(student);
//    }
}
