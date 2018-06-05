package com.example.huixuegong.androidadv.memoryleak;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.huixuegong.androidadv.R;

public class MATActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mat);
        ListView lv = (ListView) findViewById(R.id.mat_lv);
        lv.setAdapter(new LeakAdapter());
    }

    private class LeakAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return 1000;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView matView = new TextView(MATActivity.this);
            matView.setText("TextView " + String.valueOf(position));
            return matView;
        }
    }
}
