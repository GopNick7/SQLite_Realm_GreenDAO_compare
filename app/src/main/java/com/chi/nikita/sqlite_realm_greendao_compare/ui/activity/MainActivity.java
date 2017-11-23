package com.chi.nikita.sqlite_realm_greendao_compare.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.chi.nikita.sqlite_realm_greendao_compare.R;
import com.chi.nikita.sqlite_realm_greendao_compare.data.adapter.DataRVAdapter;
import com.chi.nikita.sqlite_realm_greendao_compare.data.model.UserModelGreenDAO;
import com.chi.nikita.sqlite_realm_greendao_compare.data.model.UserModelRealm;
import com.chi.nikita.sqlite_realm_greendao_compare.data.model.UserModelSQLite;

public class MainActivity extends AppCompatActivity implements MainView, View.OnClickListener {

    private EditText edtId, edtName, edtAge;
    private Button btnInsert300kSQLite, btnInsertSQLite, btnLoadAllSQLite, btnUpdateSQLite, btnDeleteSQLite;
    private Button btnInsert300kRealm, btnInsertRealm, btnLoadAllRealm, btnUpdateRealm, btnDeleteRealm;
    private Button btnInsert300kGreenDAO, btnInsertGreenDAO, btnLoadAllGreenDAO, btnUpdateGreenDAO, btnDeleteGreenDAO;
    private MainPresenter<MainView> presenter;
    private DataRVAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        adapter = new DataRVAdapter();
        presenter = new MainPresenterImpl();
        presenter.bindView(this);
    }

    private void init() {
        edtId = findViewById(R.id.edtID);
        edtName = findViewById(R.id.edtName);
        edtAge = findViewById(R.id.edtAge);

        btnInsert300kSQLite = findViewById(R.id.btnInsert300kSQLite);
        btnInsertSQLite = findViewById(R.id.btnInsertSQLite);
        btnLoadAllSQLite = findViewById(R.id.btnLoadAllSQLite);
        btnUpdateSQLite = findViewById(R.id.btnUpdateSQLite);
        btnDeleteSQLite = findViewById(R.id.btnDeleteSQLite);

        btnInsert300kSQLite.setOnClickListener(this);
        btnInsertSQLite.setOnClickListener(this);
        btnLoadAllSQLite.setOnClickListener(this);
        btnUpdateSQLite.setOnClickListener(this);
        btnDeleteSQLite.setOnClickListener(this);

        btnInsert300kRealm = findViewById(R.id.btnInsert300kRealm);
        btnInsertRealm = findViewById(R.id.btnInsertRealm);
        btnLoadAllRealm = findViewById(R.id.btnLoadAllRealm);
        btnUpdateRealm = findViewById(R.id.btnUpdateRealm);
        btnDeleteRealm = findViewById(R.id.btnDeleteRealm);

        btnInsert300kRealm.setOnClickListener(this);
        btnInsertRealm.setOnClickListener(this);
        btnLoadAllRealm.setOnClickListener(this);
        btnUpdateRealm.setOnClickListener(this);
        btnDeleteRealm.setOnClickListener(this);

        btnInsert300kGreenDAO = findViewById(R.id.btnInsert300kGreenDAO);
        btnInsertGreenDAO = findViewById(R.id.btnInsertGreenDAO);
        btnLoadAllGreenDAO = findViewById(R.id.btnLoadAllGreenDAO);
        btnUpdateGreenDAO = findViewById(R.id.btnUpdateGreenDAO);
        btnDeleteGreenDAO = findViewById(R.id.btnDeleteGreenDAO);

        btnInsert300kGreenDAO.setOnClickListener(this);
        btnInsertGreenDAO.setOnClickListener(this);
        btnLoadAllGreenDAO.setOnClickListener(this);
        btnUpdateGreenDAO.setOnClickListener(this);
        btnDeleteGreenDAO.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        UserModelSQLite userModelSQLite = new UserModelSQLite();
        UserModelRealm userModelRealm = new UserModelRealm();
        UserModelGreenDAO userModelGreenDAO = new UserModelGreenDAO();

        switch (view.getId()) {
            case R.id.btnInsert300kSQLite:
                presenter.insert300kUsersSQLite();
                btnInsert300kSQLite.setEnabled(false);
                break;
            case R.id.btnInsertSQLite:
                userModelSQLite.setName(getValue(edtName));
                userModelSQLite.setAge(Integer.parseInt(getValue(edtAge)));
                presenter.insertUserSQLite(userModelSQLite);
                break;
            case R.id.btnUpdateSQLite:
                userModelSQLite.setName(getValue(edtName));
                userModelSQLite.setAge(Integer.parseInt(getValue(edtAge)));
                presenter.updateUserSQLite(Integer.parseInt(getValue(edtId)), userModelSQLite);
                break;
            case R.id.btnDeleteSQLite:
                presenter.deleteUserSQLite(Integer.parseInt(getValue(edtId)));
                break;
            case R.id.btnLoadAllSQLite:
                presenter.getAllUsersSQLite();
                break;
            case R.id.btnInsert300kRealm:
                presenter.insert300kUsersRealm();
                btnInsert300kRealm.setEnabled(false);
                break;
            case R.id.btnInsertRealm:
                userModelRealm.setName(getValue(edtName));
                userModelRealm.setAge(Integer.parseInt(getValue(edtAge)));
                presenter.insertUserRealm(userModelRealm);
                break;
            case R.id.btnUpdateRealm:
                userModelRealm.setName(getValue(edtName));
                userModelRealm.setAge(Integer.parseInt(getValue(edtAge)));
                presenter.updateUserRealm(Integer.parseInt(getValue(edtId)), userModelRealm);
                break;
            case R.id.btnDeleteRealm:
                presenter.deleteUserRealm(Integer.parseInt(getValue(edtId)));
                break;
            case R.id.btnLoadAllRealm:
                presenter.getAllUsersRealm();
                break;
            case R.id.btnInsert300kGreenDAO:
                presenter.insert300kUsersGreenDAO();
                btnInsert300kGreenDAO.setEnabled(false);
                break;
            case R.id.btnInsertGreenDAO:
                userModelGreenDAO.setName(getValue(edtName));
                userModelGreenDAO.setAge(Integer.parseInt(getValue(edtAge)));
                presenter.insertUserGreenDAO(userModelGreenDAO);
                break;
            case R.id.btnUpdateGreenDAO:
                userModelGreenDAO.setName(getValue(edtName));
                userModelGreenDAO.setAge(Integer.parseInt(getValue(edtAge)));
                presenter.updateUserGreenDAO(Integer.parseInt(getValue(edtId)), userModelGreenDAO);
                break;
            case R.id.btnDeleteGreenDAO:
                presenter.deleteUserGreenDAO(Integer.parseInt(getValue(edtId)));
                break;
            case R.id.btnLoadAllGreenDAO:
                presenter.getAllUsersGreenDAO();
                break;
        }
    }

    private String getValue(@NonNull final EditText edtText) {
        return edtText.getText().toString().trim();
    }

//    private void setButtonEnabled(@NonNull final Boolean bool) {
//        btnInsert300kSQLite.setEnabled(bool);
//    }
}
