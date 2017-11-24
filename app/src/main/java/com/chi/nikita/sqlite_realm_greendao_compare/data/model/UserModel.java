package com.chi.nikita.sqlite_realm_greendao_compare.data.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 24.11.2017.
 */

public class UserModel {

    private long id;
    private String name;
    private int age;

    public UserModel(UserModelRealm userModelRealm) {
        this.id = userModelRealm.getId();
        this.age = userModelRealm.getAge();
        this.name = userModelRealm.getName();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public static List<UserModel> createFrom(List<UserModelRealm> userModelRealmList){
        final List<UserModel> userModels = new ArrayList<>(userModelRealmList.size());
        for (UserModelRealm userModelRealm : userModelRealmList) {
            userModels.add(new UserModel(userModelRealm));
        }
        return userModels;
    }
}
