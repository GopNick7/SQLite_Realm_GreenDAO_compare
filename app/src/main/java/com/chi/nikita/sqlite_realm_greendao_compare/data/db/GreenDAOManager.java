package com.chi.nikita.sqlite_realm_greendao_compare.data.db;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.util.Log;

import com.chi.nikita.sqlite_realm_greendao_compare.data.model.DaoMaster;
import com.chi.nikita.sqlite_realm_greendao_compare.data.model.DaoSession;
import com.chi.nikita.sqlite_realm_greendao_compare.data.model.UserModelGreenDAO;
import com.chi.nikita.sqlite_realm_greendao_compare.data.model.UserModelGreenDAODao;

import org.greenrobot.greendao.database.Database;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class GreenDAOManager {

    private static GreenDAOManager instance;
    private DaoSession daoSession;
    private Executor executor;
    private Handler handler;

    private GreenDAOManager(final @NonNull Context context, @NonNull final Handler handler) {
        executor = Executors.newSingleThreadExecutor();
        this.handler = handler;
        final DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, "GREENDAO_DB");
        final Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }

    public static GreenDAOManager getInstance() {
        if (instance == null) {
            throw new IllegalStateException("please call method init in your Application.class");
        }
        return instance;
    }

    public static void init(final @NonNull Context context, @NonNull final Handler handler) {
        instance = new GreenDAOManager(context, handler);
    }

    public static boolean isInit() {
        return instance != null;
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

    /**
     * Method for insert users to database
     *
     * @param userModelGreenDAOList {@link UserModelGreenDAO}
     */
    public void insertUsersToDB(@NonNull final List<UserModelGreenDAO> userModelGreenDAOList) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                final long l = System.currentTimeMillis();
                getDaoSession().getDatabase().beginTransaction();
                try {
                    getDaoSession().getUserModelGreenDAODao().insertInTx(userModelGreenDAOList);
                    getDaoSession().getDatabase().setTransactionSuccessful();
                } finally {
                    getDaoSession().getDatabase().endTransaction();
                }
                Log.d("TAG", "SUCCESS insertUsersToGreenDAO: " + userModelGreenDAOList.size() + " rows in " + (System.currentTimeMillis() - l) + "ms");
            }
        });
    }

    /**
     * Method for insert user to database
     *
     * @param userModelGreenDAO {@link UserModelGreenDAO}
     */
    public void insertUsersToDB(@NonNull final UserModelGreenDAO userModelGreenDAO) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                final long l = System.currentTimeMillis();
                getDaoSession().getDatabase().beginTransaction();
                try {
                    getDaoSession().getUserModelGreenDAODao().insert(userModelGreenDAO);
                    getDaoSession().getDatabase().setTransactionSuccessful();
                } finally {
                    getDaoSession().getDatabase().endTransaction();
                }
                Log.d("TAG", "SUCCESS insertUserToGreenDAO: " + 1 + " row in " + (System.currentTimeMillis() - l) + "ms");
            }
        });
    }

    /**
     * Method for update users in database
     *
     * @param id                on this id we update our users
     * @param userModelGreenDAO {@link UserModelGreenDAO}
     */
    public void updateUserInDB(final long id, @NonNull final UserModelGreenDAO userModelGreenDAO) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                final long l = System.currentTimeMillis();
                getDaoSession().getDatabase().beginTransaction();
                try {
                    final UserModelGreenDAO userModel = daoSession.queryBuilder(UserModelGreenDAO.class)
                            .where(UserModelGreenDAODao.Properties.Id.eq(id))
                            .unique();
                    userModel.setName(userModelGreenDAO.getName());
                    userModel.setAge(userModelGreenDAO.getAge());
                    getDaoSession().update(userModel);
                    getDaoSession().getDatabase().setTransactionSuccessful();
                } finally {
                    getDaoSession().getDatabase().endTransaction();
                }
                Log.d("TAG", "SUCCESS updateUserInGreenDAO: " + 1 + " row in " + (System.currentTimeMillis() - l) + "ms");
            }
        });
    }

    /**
     * Method for delete users
     *
     * @param id on this id we delete our users
     */
    public void deleteUserInDB(final long id) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                long l = System.currentTimeMillis();
                getDaoSession().getDatabase().beginTransaction();
                try {
                    getDaoSession().getUserModelGreenDAODao().deleteByKey(id);
                    getDaoSession().getDatabase().setTransactionSuccessful();
                } finally {
                    getDaoSession().getDatabase().endTransaction();
                }
                Log.d("TAG", "SUCCESS deleteUserInGreenDAO: " + 1 + " row in " + (System.currentTimeMillis() - l) + "ms");
            }
        });
    }

    /**
     * Method show all users from database
     */
    public void getAllUsersFromDB(@NonNull final ResultListener resultListener) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                final List<UserModelGreenDAO> userModelGreenDAOList;
                long l = System.currentTimeMillis();
                getDaoSession().getDatabase().beginTransaction();
                try {
                    userModelGreenDAOList = getDaoSession().getUserModelGreenDAODao().loadAll();
                    getDaoSession().getDatabase().setTransactionSuccessful();
                } finally {
                    getDaoSession().getDatabase().endTransaction();
                }
                Log.d("TAG", "SUCCESS showAllUserInGreenDAO: " + userModelGreenDAOList.size() + " rows in " + (System.currentTimeMillis() - l) + "ms");
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        resultListener.onSuccess(userModelGreenDAOList);
                    }
                });
            }
        });
    }

    public interface ResultListener {
        void onSuccess(final @NonNull List<UserModelGreenDAO> userModelGreenDAOList);
    }
}
