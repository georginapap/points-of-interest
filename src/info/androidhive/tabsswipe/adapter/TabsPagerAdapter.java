package info.androidhive.tabsswipe.adapter;

//import info.androidhive.tabsswipe.GamesFragment;
//import info.androidhive.tabsswipe.MoviesFragment;
//import info.androidhive.tabsswipe.TopRatedFragment;
import activities.SearchFragment;
import activities.MyListFragment;
import activities.SetFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabsPagerAdapter extends FragmentPagerAdapter {

	public TabsPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int index) {

		switch (index) {
		case 0:
			// Top Rated fragment activity
			return new SetFragment();
		case 1:
			// Games fragment activity
			return new SearchFragment();
		case 2:
			// Movies fragment activity
			return new MyListFragment();
		}

		return null;
	}

	@Override
	public int getCount() {
		// get item count - equal to number of tabs
		return 3;
	}

}
