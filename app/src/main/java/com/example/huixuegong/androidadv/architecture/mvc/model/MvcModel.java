package com.example.huixuegong.androidadv.architecture.mvc.model;

import android.util.Log;

import com.example.huixuegong.androidadv.architecture.mvc.view.MvcActivity;
import com.example.huixuegong.androidadv.architecture.util.PhoneInfo;

public class MvcModel {
    private final static String TAG = MvcModel.class.getSimpleName();
    private MvcActivity mvcActivity;
    private String mIp;


    public MvcModel(MvcActivity mActivity) {
        this.mvcActivity = mActivity;
    }

    public void loadModel(){
        Log.d(TAG, "load Model");
        mIp = PhoneInfo.getIPAddress();
        mvcActivity.updateUI(mIp);
    }
}
