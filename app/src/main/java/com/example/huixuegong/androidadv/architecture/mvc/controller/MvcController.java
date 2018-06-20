package com.example.huixuegong.androidadv.architecture.mvc.controller;

import android.util.Log;

import com.example.huixuegong.androidadv.architecture.mvc.model.MvcModel;
import com.example.huixuegong.androidadv.architecture.mvc.view.MvcActivity;

public class MvcController {
    private final static String TAG = MvcController.class.getSimpleName();

    private MvcActivity mvcActivity;
    private MvcModel mvcModel;

    public MvcController(MvcActivity mActivity) {
        this.mvcActivity = mActivity;
    }

    public void loadData() {
        Log.d(TAG, "load data");
        mvcModel = new MvcModel(mvcActivity);
        mvcModel.loadModel();
    }
}
