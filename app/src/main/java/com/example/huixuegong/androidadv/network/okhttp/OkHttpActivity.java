package com.example.huixuegong.androidadv.network.okhttp;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.huixuegong.androidadv.R;

import java.io.File;
import java.io.IOException;
import java.text.Normalizer;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpActivity extends Activity implements View.OnClickListener{
    private final static String TAG = OkHttpActivity.class.getSimpleName();

    private Button mGetSync, mGetAsync, mPost, mPostRequest, mPostFrom;
    private TextView mTvHttps;
    private OkHttpClient httpClient;
    private Request request;
    private Response response;
    private static String getMsg;

    private String BAIDU_URI = "http://www.baidu.com";

    private Handler mHandler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ok_http);
        initView();

        httpClient = new OkHttpClient();
    }

    private void initView() {
        mTvHttps = (TextView) findViewById(R.id.tv_https);

        mGetSync = (Button) findViewById(R.id.button_sync);
        mGetAsync = (Button) findViewById(R.id.button_async);
        mPost = (Button) findViewById(R.id.button_post);
        mPostFrom = (Button) findViewById(R.id.button_from_body);
        mPostRequest = (Button) findViewById(R.id.button_request_body);

        mGetSync.setOnClickListener(this);
        mGetAsync.setOnClickListener(this);
        mPost.setOnClickListener(this);
        mPostFrom.setOnClickListener(this);
        mPostRequest.setOnClickListener(this);
    }

    private void updateUI() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "GET MSG = " + getMsg);
                mTvHttps.setText(getMsg);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_sync:
                getDataSync();
                break;
            case R.id.button_async:
                getDataAsync();
                break;
            case R.id.button_post:
                postMsg();
                break;
            case R.id.button_request_body:
                postFile();
                break;
            default:
                break;
        }
    }

    public void getDataSync() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    request = new Request.Builder()
                            .url(BAIDU_URI)
                            .build();
                    response = httpClient.newCall(request).execute();
                    if(response.isSuccessful()) {
                        getMsg = response.body().string();
                    }
                    updateUI();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void getDataAsync() {
        request = new Request.Builder()
                .url(BAIDU_URI)
                .build();
        httpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful()) {
                    getMsg = response.body().string();
                }
                updateUI();
            }
        });
    }

    public void postMsg() {
        FormBody.Builder formBody = new FormBody.Builder();
        formBody.add("name", "Jack");
        request = new Request.Builder()
                .url(BAIDU_URI)
                .post(formBody.build())
                .build();

        httpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful()) {
                    getMsg = response.body().string();
                }
                updateUI();
            }
        });
    }

    public void postFile() {
        MediaType fileType = MediaType.parse("File/*");
        File file = new File("path");

        RequestBody fileBody = RequestBody.create(fileType, file);

        request = new Request.Builder()
                .url(BAIDU_URI)
                .post(fileBody)
                .build();

        httpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful()) {
                    getMsg = response.body().string();
                }
                updateUI();
            }
        });
    }
}
