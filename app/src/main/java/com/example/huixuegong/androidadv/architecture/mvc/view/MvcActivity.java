package com.example.huixuegong.androidadv.architecture.mvc.view;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.huixuegong.androidadv.R;
import com.example.huixuegong.androidadv.architecture.mvc.controller.MvcController;
import com.example.huixuegong.androidadv.architecture.mvc.model.MvcModel;

public class MvcActivity extends Activity {
    private final static String TAG = MvcActivity.class.getSimpleName();
    private Button mButton;
    private TextView mTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvc);

        mButton = (Button) findViewById(R.id.btn_get);
        mTextView = (TextView) findViewById(R.id.tv_ip);
        final MvcController controller = new MvcController(this);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "On Click");
                controller.loadData();
            }
        });
    }

    public void updateUI(String ip) {
        Log.d(TAG, "update UI");
        mTextView.setText(ip);
    }
}
