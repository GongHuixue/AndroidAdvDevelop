package com.example.huixuegong.androidadv.architecture.mvp.presenter;

import android.util.Log;

import com.example.huixuegong.androidadv.architecture.mvp.model.MvpModel;
import com.example.huixuegong.androidadv.architecture.mvp.view.MvpActivity;

public class MvpPresenter {
    private final static String TAG = MvpPresenter.class.getSimpleName();
    private MvpActivity mActivity;
    private MvpModel mModel;

    public MvpPresenter(MvpActivity mActivity) {
        Log.d(TAG, "MvpPresenter");
        this.mActivity = mActivity;
    }

    public void loadData() {
        Log.d(TAG, "load Data");

        mModel = new MvpModel(new MvpModel.OnLoadListener() {
            @Override
            public void onLoadFinish(String ip) {
                Log.d(TAG, "onLoadFinish");
                mActivity.updateUI(ip);
            }
        });

        mModel.loadModel();
    }
}
