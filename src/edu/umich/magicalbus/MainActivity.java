package edu.umich.magicalbus;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.ActionBarDrawerToggle;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.widget.ListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.view.View;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.Window;
import android.content.res.Configuration;

public class MainActivity extends FragmentActivity {

	private DrawerLayout mDrawerLayout;
	private ActionBarDrawerToggle mDrawerToggle;
	private ListView mDrawerListView;
	private String mTitle;

	private static final int ITEM_MAP_VIEW = 0;
	private static final int ITEM_ROUTE_LIST = 1;
	private static final int ITEM_SETTINGS = 2;

	private static final String[] ITEM_NAMES = {
		"Map",
		"Route List",
		"Settings"
	};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_ACTION_BAR);

        setContentView(R.layout.main_layout);

		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

		mDrawerToggle = new ActionBarDrawerToggle(
				this,
				mDrawerLayout,
				R.drawable.ic_drawer,
				R.string.drawer_open,
				R.string.drawer_close) {

			public void onDrawerClosed(View drawerView) {
				super.onDrawerClosed(drawerView);
				getActionBar().setTitle(mTitle);
			}

			public void onDrawerOpened(View drawerView) {
				super.onDrawerOpened(drawerView);
				getActionBar().setTitle("Select");
			}
		};

		mDrawerLayout.setDrawerListener(mDrawerToggle);

		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);

		mDrawerListView = (ListView) findViewById(R.id.drawer_list);
		mDrawerListView.setAdapter(new DrawerListAdapter(this));
		mDrawerListView.setOnItemClickListener(new DrawerItemClickListener());

		if (savedInstanceState == null) {
			selectFragment(ITEM_MAP_VIEW);
		}
    }

	private void selectFragment(int position) {

		Fragment fragment = null;

		switch (position) {
			case ITEM_MAP_VIEW:
				fragment = new MapFragment();
				break;
			case ITEM_ROUTE_LIST:
				// TODO
				fragment = new MapFragment();
				break;
			case ITEM_SETTINGS:
				// TODO
				fragment = new MapFragment();
				break;
		}

		if (fragment != null) {
			FragmentManager fragmentManager = getSupportFragmentManager();
			fragmentManager.beginTransaction()
				.replace(R.id.content_frame, fragment)
				.commit();
			mDrawerListView.setItemChecked(position, true);
			setTitle(ITEM_NAMES[position]);
		}

		mDrawerLayout.closeDrawer(mDrawerListView);
	}

	private class DrawerItemClickListener implements ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView parent, View view, int position, long id) {
			selectFragment(position);
		}
	}

	private class DrawerListAdapter extends BaseAdapter {

		private Context mContext;

		public DrawerListAdapter(Context context) {
			mContext = context;
		}

		@Override
		public String getItem(int position) {
			return ITEM_NAMES[position];
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public int getCount() {
			return ITEM_NAMES.length;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			TextView view;
			if (convertView == null) {
				view = new TextView(mContext);
			} else {
				view = (TextView) convertView;
			}
			view.setText(ITEM_NAMES[position]);
			return view;
		}
	}

	@Override
	public void setTitle(CharSequence title) {
		mTitle = (String) title;
		getActionBar().setTitle(title);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Pass the event to ActionBarDrawerToggle, if it returns
		// true, then it has handled the app icon touch event
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}
}
