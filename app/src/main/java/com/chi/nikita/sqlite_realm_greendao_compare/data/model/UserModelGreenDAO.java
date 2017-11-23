package com.chi.nikita.sqlite_realm_greendao_compare.data.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;

@Entity
public class UserModelGreenDAO {

    @Id(autoincrement = true)
    private long id;
    private String name;
    private int age;


    @Generated(hash = 692309904)
    public UserModelGreenDAO(long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    @Generated(hash = 893323659)
    public UserModelGreenDAO() {
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
}
