package activities;



import listeners.SetFragmentListeners;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.firstapp.myfirstapp.R;

public class SetFragment extends Fragment  {

	private static String spinner_text;
	private static String userpsw;
	private static Spinner spinner;
	private static Button insert;
	private static EditText poiName ;
	public static FragmentActivity activity;
	//Thread tt;

//	Handler handler = new Handler(Looper.getMainLooper());
//	Runnable runnable = new Runnable() {
//		public void run() {
//			MyTask_set.FromPendingToVerified(handler, runnable);
//		}
//	};

	@Override
	public void onResume() {
		getPoiName().setText("");
		super.onResume();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_set, container, false);
		setInsert((Button)    rootView.findViewById(R.id.insert));
		setSpinner((Spinner) rootView.findViewById(R.id.poi_spinner));	// Create an ArrayAdapter using the string array and a default spinner layout
		setPoiName((EditText) rootView.findViewById(R.id.PoiName));
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity().getBaseContext(),
		R.array.types, android.R.layout.simple_spinner_item);// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);// Apply the adapter to the spinner
		getSpinner().setAdapter(adapter);
		activity=getActivity();

//		//Auto//
//		runnable.run();

		SetFragmentListeners.addItemSelectedListenerToSpinner();
		SetFragmentListeners.setButtonOnClickListeners();
		return rootView;
	}



	public static Spinner getSpinner() {
		return spinner;
	}



	public void setSpinner(Spinner spinner) {
		SetFragment.spinner = spinner;
	}



	public static String getSpinner_text() {
		return spinner_text;
	}



	public static  void setSpinner_text(String text) {
		spinner_text =text;
	}



	public static Button getInsert() {
		return insert;
	}



	public void setInsert(Button insert) {
		SetFragment.insert = insert;
	}



	public static EditText getPoiName() {
		return poiName;
	}



	public void setPoiName(EditText poiName) {
		SetFragment.poiName = poiName;
	}



	public static String getUserpsw() {
		return userpsw;
	}



	public static void setUserpsw(String usrpsw) {
		userpsw = usrpsw;
	}


	public static int haveNetworkConnection() {
		int haveConnectedWifi = 0;


		@SuppressWarnings("static-access")
		ConnectivityManager cm = (ConnectivityManager) activity.getApplicationContext().getSystemService(activity.getApplicationContext().CONNECTIVITY_SERVICE);
		NetworkInfo[] netInfo = cm.getAllNetworkInfo();
		for (NetworkInfo ni : netInfo) {
			if (ni.getTypeName().equalsIgnoreCase("WIFI"))
				if (ni.isConnected())
					haveConnectedWifi = 1;
			if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
				if (ni.isConnected())
					haveConnectedWifi = 2;
		}
		return haveConnectedWifi;
	}

}