package com.chi.nikita.sqlite_realm_greendao_compare.ui.activity;

import com.chi.nikita.sqlite_realm_greendao_compare.data.model.iCRUD;
import com.chi.nikita.sqlite_realm_greendao_compare.ui.View;

import java.util.List;


public interface MainView extends View {
    void onShowUsers(List<? extends iCRUD> userModelList);
}
