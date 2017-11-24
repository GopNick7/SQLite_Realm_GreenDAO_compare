package com.chi.nikita.sqlite_realm_greendao_compare.data.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.util.Log;

import com.chi.nikita.sqlite_realm_greendao_compare.data.model.UserModelSQLite;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class SQLiteManager extends SQLiteOpenHelper {

    private SQLiteDatabase sqLiteDatabase;
    private static SQLiteManager ourInstance;
    private static final String DB_NAME = "SQLITE_DB";
    private static final int VERSION = 1;
    private static final String TABLE_USER = "USER_TABLE";
    private Handler handler;
    private Executor executor;

    /**
     * constants for TABLE_USER
     */
    public static final String ID = "_id";
    public static final String NAME = "name";
    public static final String AGE = "age";

    /**
     * total constants
     */
    private static final String CREATE_TABLE = "CREATE TABLE ";
    private static final String TYPE_TEXT = " TEXT";
    private static final String TYPE_INTEGER = " INTEGER";
    private static final String NOT_NULL = " NOT NULL";
    private static final String COMMA_SEP = ", ";
    private static final String PRIMARY_KEY = " PRIMARY KEY";
    private static final String AUTOINCREMENT = " AUTOINCREMENT";
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS ";
//    private static final String UNIQUE = " UNIQUE ";
//    private static final String DEFAULT = " DEFAULT ' '";

    /**
     * constant for create TABLE_USER
     */
    private static final String CREATE_TABLE_USER = CREATE_TABLE + TABLE_USER +
            " (" +
            ID + TYPE_INTEGER + PRIMARY_KEY + AUTOINCREMENT + COMMA_SEP +
            NAME + TYPE_TEXT + NOT_NULL + COMMA_SEP +
            AGE + TYPE_INTEGER + NOT_NULL +
            ")";

    public SQLiteManager(@NonNull final Context context, @NonNull final Handler handler) {
        super(context, DB_NAME, null, VERSION);
        executor = Executors.newSingleThreadExecutor();
        this.handler = handler;
    }

    public static SQLiteManager getInstance() {
        return ourInstance;
    }

    public static void init(@NonNull final Context context, @NonNull final Handler handler) {
        ourInstance = new SQLiteManager(context, handler);
        SQLiteManager.getInstance().openDB();
    }

    public static boolean isInit() {
        return ourInstance != null;
    }

    @Override
    public void onCreate(@NonNull final SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_USER);
    }

    @Override
    public void onUpgrade(@NonNull final SQLiteDatabase sqLiteDatabase, int oldVersion,
                          int newVersion) {
        sqLiteDatabase.execSQL(DROP_TABLE + TABLE_USER);
        onCreate(sqLiteDatabase);
    }

    /**
     * Method for open Database
     */
    public void openDB() {
        sqLiteDatabase = getWritableDatabase();
    }

    /**
     * Method for insert users to database
     *
     * @param userModelSQLiteList {@link UserModelSQLite}
     */
    public void insertUsersToDB(@NonNull final List<UserModelSQLite> userModelSQLiteList) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                final long l = System.currentTimeMillis();
                sqLiteDatabase.beginTransaction();
                try {
                    final ContentValues cv = new ContentValues();
                    for (int i = 0; i < userModelSQLiteList.size(); i++) {
                        cv.put(NAME, userModelSQLiteList.get(i).getName());
                        cv.put(AGE, userModelSQLiteList.get(i).getAge());
                        sqLiteDatabase.insert(TABLE_USER, null, cv);
                    }
                    sqLiteDatabase.setTransactionSuccessful();
                } finally {
                    sqLiteDatabase.endTransaction();
                }
                Log.d("TAG", "SUCCESS insertUsersToSQLite: " + userModelSQLiteList.size() + " rows in " + (System.currentTimeMillis() - l) + "ms");
            }
        });
    }

    /**
     * Method for insert user to database
     *
     * @param userModelSQLite {@link UserModelSQLite}
     */
    public void insertUsersToDB(@NonNull final UserModelSQLite userModelSQLite) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                final long l = System.currentTimeMillis();
                sqLiteDatabase.beginTransaction();
                try {
                    final ContentValues cv = new ContentValues();
                    cv.put(NAME, userModelSQLite.getName());
                    cv.put(AGE, userModelSQLite.getAge());
                    sqLiteDatabase.insert(TABLE_USER, null, cv);

                    sqLiteDatabase.setTransactionSuccessful();
                } finally {
                    sqLiteDatabase.endTransaction();
                }
                Log.d("TAG", "SUCCESS insertUsersToSQLite: " + 1 + " row in " + (System.currentTimeMillis() - l) + "ms");
            }
        });
    }

    /**
     * Method for update users in database
     *
     * @param id              on this id we update our users
     * @param userModelSQLite {@link UserModelSQLite}
     */
    public void updateUserInDB(final long id, @NonNull final UserModelSQLite userModelSQLite) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                final long l = System.currentTimeMillis();
                sqLiteDatabase.beginTransaction();
                final ContentValues cv = new ContentValues();
                try {
                    cv.put(NAME, userModelSQLite.getName());
                    cv.put(AGE, userModelSQLite.getAge());

                    final String where = ID + " = " + id;
                    sqLiteDatabase.update(TABLE_USER, cv, where, null);
                    sqLiteDatabase.setTransactionSuccessful();
                } finally {
                    sqLiteDatabase.endTransaction();
                }
                Log.d("TAG", "SUCCESS updateUserInSQLite: " + 1 + " row in " + (System.currentTimeMillis() - l) + "ms");
            }
        });
    }

    /**
     * Method for delete users
     *
     * @param id on this id we delete our users
     */
    public void deleteUserInDB(final long id) {
        final String where = ID + " = " + id;
        final long l = System.currentTimeMillis();
        sqLiteDatabase.delete(TABLE_USER, where, null);
        Log.d("TAG", "SUCCESS deleteUserInSQLite: " + 1 + " row in " + (System.currentTimeMillis() - l) + "ms");
    }

    /**
     * Method for show all users from our database
     *
     * @param resultListener {@link ResultListener}
     */
    public void getAllUsersFromDB(final @NonNull ResultListener resultListener) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                long l = System.currentTimeMillis();
                final String select = "SELECT * FROM " + TABLE_USER;
                final Cursor cursor = sqLiteDatabase.rawQuery(select, null);
                final List<UserModelSQLite> userModelSQLiteList = getUserModelList(cursor);
                Log.d("TAG", "SUCCESS showAllUserInSQLite: " + userModelSQLiteList.size() + " rows in " + (System.currentTimeMillis() - l) + "ms");
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        resultListener.onSuccess(userModelSQLiteList);
                    }
                });
            }
        });
    }

    private List<UserModelSQLite> getUserModelList(@NonNull final Cursor cursor) {
        final List<UserModelSQLite> userModelSQLiteList = new ArrayList<>();
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            final UserModelSQLite userModelSQLite = new UserModelSQLite();
            userModelSQLite.setId(cursor.getInt(cursor.getColumnIndex(ID)));
            userModelSQLite.setName(cursor.getString(cursor.getColumnIndexOrThrow(NAME)));
            userModelSQLite.setAge(cursor.getInt(cursor.getColumnIndexOrThrow(AGE)));

            userModelSQLiteList.add(userModelSQLite);
        }
        cursor.close();
        return userModelSQLiteList;

    }

    public interface ResultListener {
        void onSuccess(final @NonNull List<UserModelSQLite> userModelSQLiteList);
    }
}
