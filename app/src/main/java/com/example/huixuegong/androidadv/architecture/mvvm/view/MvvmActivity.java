package com.example.huixuegong.androidadv.architecture.mvvm.view;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;

import com.example.huixuegong.androidadv.R;
import com.example.huixuegong.androidadv.architecture.mvvm.viewmodel.MvvmVM;
import com.example.huixuegong.androidadv.databinding.ActivityMvvmBinding;

public class MvvmActivity extends Activity {
    private final static String TAG = MvvmActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "MvvmActivity onCreate");
        ActivityMvvmBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_mvvm);

        final MvvmVM mVM = new MvvmVM(binding);

        binding.setMvvmVM(mVM);
    }
}
