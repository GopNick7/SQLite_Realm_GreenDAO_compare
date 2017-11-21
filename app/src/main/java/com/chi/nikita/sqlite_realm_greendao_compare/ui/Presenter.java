package com.chi.nikita.sqlite_realm_greendao_compare.ui;

public interface Presenter<T extends View>{

    void bindView(T view);

    void unbindView();

    void onDestroy();
}