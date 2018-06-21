package com.example.huixuegong.androidadv.database.greendao;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.huixuegong.androidadv.AdvApplication;
import com.example.huixuegong.androidadv.R;
import com.example.huixuegong.androidadv.database.greendao.entity.User;
import com.example.huixuegong.androidadv.database.greendao.gen.UserDao;

import java.util.List;

public class GreenDaoActivity extends Activity implements View.OnClickListener{

    private Button mAdd, mDel, mModify, mQuery;
    private TextView mTv;
    private User mUser;
    private UserDao mUserDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_green_dao);
        initView();
        mUserDao = AdvApplication.getAppApplication().getDaoSession().getUserDao();
    }

    private void initView() {
        mTv = (TextView) findViewById(R.id.tv_user);
        mAdd = (Button) findViewById(R.id.button_add);
        mDel = (Button) findViewById(R.id.button_del);
        mModify = (Button) findViewById(R.id.button_modify);
        mQuery = (Button) findViewById(R.id.button_query);

        mAdd.setOnClickListener(this);
        mDel.setOnClickListener(this);
        mModify.setOnClickListener(this);
        mQuery.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_add:
                addData();
                break;
            case R.id.button_del:
                delData();
                break;
            case R.id.button_modify:
                updateData();
                break;
            case R.id.button_query:
                queryData();
                break;
            default:
                break;
        }
        updateTv(mUser.getName());
    }

    private void addData() {
        mUser = new User((long)1, "a");
        mUserDao.insert(mUser);
    }

    private void delData() {
        mUserDao.deleteByKey((long)1);
    }

    private void updateData() {
        mUser = new User((long)2, "b");
        mUserDao.update(mUser);
    }

    private void queryData() {
        List<User> users = mUserDao.loadAll();
        String userName = "";
        for(int i = 0; i < users.size(); i++) {
            userName += users.get(i).getName() + ":";
        }

    }

    private void updateTv(String tv) {
        mTv.setText(tv);
    }
}
