package activities;

//import info.androidhive.tabsswipe.adapter.TabsPagerAdapter;
import java.util.ArrayList;
import java.util.List;

import info.androidhive.tabsswipe.adapter.TabsPagerAdapter;

import com.firstapp.myfirstapp.R;



import database.DataSource;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;

@SuppressWarnings("unused")
public  class TabsActivity extends FragmentActivity implements
		ActionBar.TabListener {



	private ViewPager viewPager;
	private TabsPagerAdapter mAdapter;
	private ActionBar actionBar;
	// Tab titles
	private String[] tabs = { "Set", "Search", "My List" };
	public static List<String> typeslist = new ArrayList<String>();
	public static DataSource datasource;
 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tabs);
		datasource = new DataSource(this);
		datasource.open();

		// Initilization
		viewPager = (ViewPager) findViewById(R.id.pager);
		actionBar = getActionBar();
		mAdapter = new TabsPagerAdapter(getSupportFragmentManager());

		viewPager.setAdapter(mAdapter);
		actionBar.setHomeButtonEnabled(false);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);		

		// Adding Tabs
		for (String tab_name : tabs) {
			actionBar.addTab(actionBar.newTab().setText(tab_name)
					.setTabListener(this));
		}

		/**
		 * on swiping the viewpager make respective tab selected
		 * */
		viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				// on changing the page
				// make respected tab selected
				actionBar.setSelectedNavigationItem(position);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});
		
	}
	
	
	
	

	@Override
	public void onBackPressed() {
		
        Main.editor.putString("username", "0");
        Main.editor.putString("password", "0");
        Main.editor.commit();
        datasource.close();
        Intent intent4 = new Intent(TabsActivity.this,Main.class);
        startActivity(intent4);
	}
	
	@Override
	public void onResume() {
		datasource.open();
	    super.onResume();
	  }
	
	  @Override
	public void onPause() {
		  datasource.close();
	    super.onPause();
	  }

	
	@Override
	public void onTabSelected(Tab tab, android.app.FragmentTransaction ft) {
		// on tab selected
				// show respected fragment view
				viewPager.setCurrentItem(tab.getPosition());
		
	}

	 
	@Override
	public void onTabUnselected(Tab tab, android.app.FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onTabReselected(Tab tab, android.app.FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	} 
	
	
	


}