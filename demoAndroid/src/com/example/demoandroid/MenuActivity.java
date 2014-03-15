package com.example.demoandroid;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.demoandroid.onetag.OneTagActivity;
import com.example.demoandroid.podcast.PodcastExampleActivity;
import com.example.demoandroid.simple.SimpleExampleActivity;
import com.example.demoandroid.team.TeamExampleActivity;

public class MenuActivity extends BaseActivity {
	public static final String TITLE = "Menu";
	private ListView menu;
	private ArrayList<Item> items;
	private Context mContext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		actionBar.setTitle(TITLE);
		actionBar.setDisplayHomeAsUpEnabled(false);
		mContext = this;
		menu = (ListView) findViewById(R.id.lv_menu);
		items = new ArrayList<MenuActivity.Item>();
		items.add(new Item(OneTagActivity.class, OneTagActivity.TITLE));
		items.add(new Item(SimpleExampleActivity.class,
				SimpleExampleActivity.TITLE));
		items.add(new Item(PodcastExampleActivity.class,
				PodcastExampleActivity.TITLE));
		items.add(new Item(TeamExampleActivity.class, TeamExampleActivity.TITLE));

		ArrayAdapter<Item> adapter;
		adapter = new ArrayAdapter<Item>(this,
				android.R.layout.simple_list_item_1, android.R.id.text1, items);

		menu.setAdapter(adapter);
		menu.setDividerHeight(2);
		menu.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Item current = (Item) parent.getItemAtPosition(position);
				Intent intent = new Intent(mContext, current.getType());
				startActivity(intent);
			}
		});
	}

	public class Item {
		Class<?> type;
		String title;

		public Class<?> getType() {
			return type;
		}

		public String getTitle() {
			return title;
		}

		public Item(Class<?> type, String title) {
			this.type = type;
			this.title = title;
		}

		@Override
		public String toString() {
			return title;
		}
	}
}
