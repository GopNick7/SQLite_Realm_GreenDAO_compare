package com.chi.nikita.sqlite_realm_greendao_compare.data.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class UserModelRealm extends RealmObject implements iCRUD {

    @PrimaryKey
    private long id;
    private String name;
    private int age;

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getAge() {
        return age;
    }

    @Override
    public void setAge(int age) {
        this.age = age;
    }
}
