package com.example.huixuegong.androidadv.architecture.mvvm.viewmodel;

import android.os.Looper;
import android.os.Handler;
import android.view.View;

import com.example.huixuegong.androidadv.architecture.mvvm.OnSearchListener;
import com.example.huixuegong.androidadv.architecture.mvvm.model.MvvmModel;
import com.example.huixuegong.androidadv.databinding.ActivityMvvmBinding;

public class MvvmVM implements OnSearchListener {
    private final static String TAG = MvvmVM.class.getSimpleName();

    private ActivityMvvmBinding binding;
    private MvvmModel mModel = new MvvmModel();
    private android.os.Handler mHandler;

    public MvvmVM(ActivityMvvmBinding binding) {
        this.binding = binding;
        mHandler = new android.os.Handler(Looper.getMainLooper());
    }
    public void search(View view) {
        binding.pbLoad.setVisibility(View.VISIBLE);
        mModel.getIp(this);
    }

    public void onSuccess(final String ip) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                binding.pbLoad.setVisibility(View.GONE);
                binding.tvMsg.setText(ip);
            }
        });
    }

    public void onError(){
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                binding.pbLoad.setVisibility(View.GONE);
                binding.tvMsg.setText("query error");
            }
        });
    }
}
