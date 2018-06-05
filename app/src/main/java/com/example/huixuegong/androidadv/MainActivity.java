package com.example.huixuegong.androidadv;

import android.app.ListActivity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends ListActivity {

    private final static String TAG = MainActivity.class.getSimpleName();
    private final static String intentAction = "com.example.huixuegong.androidadv";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        SimpleAdapter simpleAdapter;
        Intent intent = getIntent();
        String path = intent.getStringExtra("com.example.huixuegong.androidadv.Path");

        if(path == null) {
            path = "";
        }

        simpleAdapter = new SimpleAdapter(this, getData(path),
                android.R.layout.simple_list_item_1, new String[]{"title"}, new int[]{android.R.id.text1});

        //Activity must extends ListActivity as using setListAdapter & getListView
        setListAdapter(simpleAdapter);
        getListView().setTextFilterEnabled(true);
    }


    private List<Map<String, Object>> getData(String path) {
        List<Map<String, Object>> listData = new ArrayList<Map<String, Object>>();
        String[] prefixPath;
        String prefixWithSlash;
        Intent mainIntent = new Intent(intentAction, null);
        mainIntent.addCategory(Intent.CATEGORY_SAMPLE_CODE);

        PackageManager pm = getPackageManager();
        List<ResolveInfo> activityList = pm.queryIntentActivities(mainIntent, 0);
        Map<String, Boolean> entries = new HashMap<String, Boolean>();
        int activitySize = activityList.size();

        prefixWithSlash = path;
        if(path.equals("")) {
            prefixPath = null;
        }else {
            prefixPath = path.split("/");
            prefixWithSlash = path + "/";
        }

        if(activityList.isEmpty()) {
            Log.e(TAG, "There is no activity");
            return listData;
        }else {
            Log.d(TAG, "There are " + activitySize + " Activity");
            for(int i = 0; i < activitySize; i++) {
                ResolveInfo info = activityList.get(i);
                CharSequence labelSeq = info.loadLabel(pm);
                String lable = (labelSeq != null) ? labelSeq.toString():info.activityInfo.name;
                Log.d(TAG, "Index = " + i + ", prefixPath = " + ", prefixWithSlash = " + prefixWithSlash + ", Lable = " + lable);
                if((prefixWithSlash.length() == 0) || lable.startsWith(prefixWithSlash)) {
                    String[] lablePath = lable.split("/");
                    String nextLable = (prefixPath == null) ? lablePath[0] : lablePath[prefixPath.length];

                    if((prefixPath != null ? prefixPath.length: 0) == lablePath.length - 1) {
                        addItem(listData, nextLable, activityIntent(
                                info.activityInfo.applicationInfo.packageName,
                                info.activityInfo.name));
                    }else {
                        if(entries.get(nextLable) == null) {
                            addItem(listData, nextLable, browseIntent(path.equals("")? nextLable : path + "/" + nextLable));
                            entries.put(nextLable, true);
                        }
                    }
                }
            }
        }

        Collections.sort(listData, sDisplayNameComparator);

        return listData;
    }

    private final static Comparator<Map<String, Object>> sDisplayNameComparator =
            new Comparator<Map<String, Object>>() {
                private final Collator collator = Collator.getInstance();

                public int compare(Map<String, Object> map1, Map<String, Object> map2) {
                    return collator.compare(map1.get("title"), map2.get("title"));
                }
            };

    private Intent browseIntent(String path) {
        Intent intent = new Intent();
        intent.setClass(this, MainActivity.class);
        intent.putExtra("com.example.huixuegong.androidadv.Path", path);
        return intent;
    }

    private Intent activityIntent(String pkg, String name) {
        Intent intent = new Intent();
        intent.setClassName(pkg, name);
        return intent;
    }

    private void addItem(List<Map<String, Object>> data, String name, Intent intent) {
        Map<String, Object> temp = new HashMap<String, Object>();
        temp.put("title", name);
        temp.put("intent", intent);
        data.add(temp);
    }

    @Override
    protected void onListItemClick(ListView lv, View view, int position, long id) {
        Map<String, Object> map = (Map<String, Object>) lv.getItemAtPosition(position);
        Intent intent = new Intent((Intent)map.get("intent"));
        intent.addCategory(Intent.CATEGORY_SAMPLE_CODE);
        Log.d(TAG, "position = " + position + ", intent = " + intent);
        startActivity(intent);
    }
}
