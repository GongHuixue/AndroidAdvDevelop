package com.example.huixuegong.androidadv.architecture.mvvm.model;

import android.text.TextUtils;
import android.util.Log;

import com.example.huixuegong.androidadv.architecture.mvvm.viewmodel.MvvmVM;
import com.example.huixuegong.androidadv.architecture.util.PhoneInfo;

import static android.os.SystemClock.sleep;

public class MvvmModel {
    private final static String TAG = MvvmModel.class.getSimpleName();

    public void getIp(final MvvmVM vm) {
        Log.d(TAG, "getIp enter");

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    sleep(2000);
                    String ip = PhoneInfo.getIPAddress();
                    if(TextUtils.isEmpty(ip)) {
                        vm.onError();
                    }else {
                        vm.onSuccess(ip);
                    }
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
