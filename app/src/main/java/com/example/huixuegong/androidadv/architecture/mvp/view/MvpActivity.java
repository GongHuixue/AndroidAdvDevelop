package com.example.huixuegong.androidadv.architecture.mvp.view;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.huixuegong.androidadv.R;
import com.example.huixuegong.androidadv.architecture.mvp.presenter.MvpPresenter;

public class MvpActivity extends Activity {
    private final static String TAG = MvpActivity.class.getSimpleName();
    private Button button;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp);

        button = (Button) findViewById(R.id.btn_get_mvp);
        textView = (TextView) findViewById(R.id.tv_ip_mvp);

        final MvpPresenter mvpPresenter = new MvpPresenter(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mvpPresenter.loadData();
            }
        });
    }

    public void updateUI(String ip) {
        Log.d(TAG, "update UI " + ip);
        textView.setText(ip);
    }
}
