package com.example.huixuegong.androidadv.architecture.mvp.model;

import android.util.Log;

import com.example.huixuegong.androidadv.architecture.util.PhoneInfo;

public class MvpModel {
    private final static String TAG = MvpModel.class.getSimpleName();
    private OnLoadListener mListener;
    private String mIp;

    public MvpModel(OnLoadListener listener) {
        Log.d(TAG, "MvpModel OnLoadListener");
        this.mListener = listener;
    }
    public void loadModel() {
        Log.d(TAG, "load model");
        mIp = PhoneInfo.getIPAddress();
        if(mListener != null) {
            Log.d(TAG, "Notify Listener");
            mListener.onLoadFinish(mIp);
        }
    }

    public interface OnLoadListener {
        void onLoadFinish(String ip);
    }
}
