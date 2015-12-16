package com.novoda.demoandroid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.novoda.demoandroid.onetag.OneTagActivity;
import com.novoda.demoandroid.podcast.PodcastExampleActivity;
import com.novoda.demoandroid.simple.SimpleExampleActivity;
import com.novoda.demoandroid.team.TeamExampleActivity;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends Activity {
    private static final int DIVIDER_HEIGHT = 2;
    private ListView menu;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        context = this;
        menu = (ListView) findViewById(R.id.lv_menu);
        List<NavigationItem> items = populateNavigationMenu();
        ArrayAdapter<NavigationItem> adapter;
        adapter = new ArrayAdapter<NavigationItem>(this, android.R.layout.simple_list_item_1, android.R.id.text1, items);

        menu.setAdapter(adapter);
        menu.setDividerHeight(DIVIDER_HEIGHT);
        menu.setOnItemClickListener(
                new OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        NavigationItem current = (NavigationItem) parent.getItemAtPosition(position);
                        Intent intent = new Intent(context, current.getType());
                        startActivity(intent);
                    }
                }
        );
    }

    private List<NavigationItem> populateNavigationMenu() {
        List<NavigationItem> items = new ArrayList<NavigationItem>();
        items.add(new NavigationItem(OneTagActivity.class, getResources().getString(R.string.title_activity_one_tag)));
        items.add(new NavigationItem(SimpleExampleActivity.class, getResources().getString(R.string.title_activity_simple_example)));
        items.add(new NavigationItem(PodcastExampleActivity.class, getResources().getString(R.string.title_activity_podcast)));
        items.add(new NavigationItem(TeamExampleActivity.class, getResources().getString(R.string.title_activity_team_example)));
        return items;
    }

    public static class NavigationItem {
        Class<? extends Activity> type;
        String title;

        public Class<? extends Activity> getType() {
            return type;
        }

        public String getTitle() {
            return title;
        }

        public NavigationItem(Class<? extends Activity> type, String title) {
            this.type = type;
            this.title = title;
        }

        @Override
        public String toString() {
            return title;
        }
    }
}
