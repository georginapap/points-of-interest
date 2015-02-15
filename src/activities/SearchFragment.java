package activities;

import gps.AppLocationService;

import java.util.ArrayList;
import java.util.List;

import listeners.SearchFragmentListeners;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firstapp.myfirstapp.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import database.Pending;
import database.Verified;

public class SearchFragment extends Fragment {
	AppLocationService appLocationService;
	Context ctx;
	int alert_response=-1;
	public static Double latitude;
	public static  Double longitude;
	public static CheckBox checkBox_other,checkBox_entertainment,checkBox_drink,checkBox_cinema,checkBox_seeing,checkBox_education,checkBox_library,checkBox_university,checkBox_fastfood,checkBox_takeaway,checkBox_typical,checkBox_food;
	public static Button button_search_my,button_search_other,button_search_all;
	public static List<String> checklist = new ArrayList<String>();
	public static String[] types={ "Other","Entertainment","Drink","Cinema","Site seeing","Education","Library","University","Food","Fast food Restaurant","Take away Restaurant","Typical Restaurant"};
	private static String userpsw;
	public static ListView lista;
	public static ArrayAdapter<String> listAdapter ;
	public static ArrayList<String> poiList;
	public static FragmentActivity search_activity;
	public static LinearLayout layout ;
	public static GoogleMap googleMap;
	public static TextView[] tx;
	public static TextView[] tx2;
	public static TextView[] tx1;
	public static TextView title;
	public static TextView title2;
	public static Marker TP[];
	public static LatLng poisaround[];
	public static Marker TP1[];
	public static LatLng poisaround1[];
	public static Marker TP2[];
	public static LatLng poisaround2[];
	public static Double aktina=0.00000000001;
	
	public static List<Verified> result_values =null;
	public static List <Pending>  result_pending=null;

	
	
	@SuppressWarnings("unused")
	@Override	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View rootView = inflater.inflate(R.layout.fragment_search, container, false);	
		search_activity=getActivity();
		appLocationService = new AppLocationService(search_activity);
		checkBox_other = (CheckBox) rootView.findViewById(R.id.checkBox_other);
		checkBox_entertainment=(CheckBox) rootView.findViewById(R.id.checkBox_entertainment);
		checkBox_drink=(CheckBox) rootView.findViewById(R.id.checkBox_drink);
		checkBox_cinema=(CheckBox) rootView.findViewById(R.id.checkBox_cinema);
		checkBox_seeing=(CheckBox) rootView.findViewById(R.id.checkBox_seeing);
		checkBox_education=(CheckBox) rootView.findViewById(R.id.checkBox_education);
		checkBox_library=(CheckBox) rootView.findViewById(R.id.checkBox_library);
		checkBox_university=(CheckBox) rootView.findViewById(R.id.checkBox_university);
		checkBox_fastfood=(CheckBox) rootView.findViewById(R.id.checkBox_fastfood);
		checkBox_takeaway=(CheckBox) rootView.findViewById(R.id.checkBox_takeaway);
		checkBox_typical=(CheckBox) rootView.findViewById(R.id.checkBox_typical);
		checkBox_food=(CheckBox) rootView.findViewById(R.id.checkBox_food);
		button_search_my= (Button) rootView.findViewById(R.id.button_search_my);
		button_search_other= (Button) rootView.findViewById(R.id.button_search_other);
		button_search_all= (Button) rootView.findViewById(R.id.button_search_all);	
		
		layout=(LinearLayout) rootView.findViewById(R.id.linearSearch);
		
		result_values = new ArrayList<Verified>();
		result_pending= new ArrayList<Pending>();
	
		SearchFragmentListeners.addAllSearchListeners();
		
		
		Location gpsLocation = appLocationService.getLocation(LocationManager.GPS_PROVIDER);
	    if(gpsLocation != null) {
	        Log.i("PRINT","Provider GPS  has been selected.");
	        appLocationService.onLocationChanged(gpsLocation);
	        Toast.makeText(getActivity().getApplicationContext(),"Mobile Location (GPS)"+": \nLatitude:"+ latitude+ "\nLongitude:" + longitude,Toast.LENGTH_LONG).show();
	     }else{ 
	    	Location nwLocation = appLocationService.getLocation(LocationManager.NETWORK_PROVIDER);
	    	if(nwLocation != null) {
	  	       Log.i("PRINT","Provider NETWORK  has been selected.");
	  	       appLocationService.onLocationChanged(nwLocation);
	  	       //MyTask_set.FromPending();

	  	       Toast.makeText(getActivity().getApplicationContext(),"Mobile Location (network)"+": \nLatitude:"+ latitude+ "\nLongitude:" +longitude,Toast.LENGTH_LONG).show();
	    	}else if(nwLocation == null){
	    		Log.i("PRINT","lOCATION NOT AVAILABLE");
	    		Toast.makeText(getActivity().getApplicationContext(),"Your location is innaccessibe\nEnable GPS.",Toast.LENGTH_LONG).show();
	    	}
	      }
	    Log.i("long!!!!!!!!!!!!!!!!!!!!!", "long:    "+longitude );
	    Log.i("lat","latit:    "+latitude);
	    if(latitude != null || longitude!=null)
	    {	
	    	
		final LatLng mylocation = new LatLng(latitude , longitude);
		try{ 
            if (googleMap == null) {
            	googleMap = ((SupportMapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
            }
            googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            googleMap.getUiSettings().setZoomGesturesEnabled(true);
            //googleMap.setMyLocationEnabled(true);
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mylocation, 13));
            Marker TP = googleMap.addMarker(new MarkerOptions().position(mylocation).title("HEY").snippet("I am here!!!")); 
		}catch (Exception e) {
         Log.e("PRINT","Error while opening google map\n"+e.toString());
		}	
	    }
		return rootView;
	}
	@Override
	public void onStop() {
		appLocationService.stopUsingGPS();    
	    super.onStop();
	}

	
//	public static void showChoices(){
//		String result = "";
//		if(checklist.isEmpty()){
//			 AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
//			 alertDialog.setTitle("NO CHOICE");
//			 alertDialog.setMessage(" You must select at least one category");
//			 alertDialog.setPositiveButton("OK",new DialogInterface.OnClickListener() {
//		          public void onClick(DialogInterface dialog, int which) {
//		        	  dialog.cancel();
//		          }
//		        });
//		}else{
//			for(int i=0;i<checklist.size();i++){
//				result= checklist.get(i);
//				result=result+"\n";
//			}
//			Toast.makeText(getActivity().getApplicationContext().getApplicationContext(),"Your choices: "+ result ,Toast.LENGTH_LONG).show();
//		}
//		
//	}
	

//	public int showSettingsAlert(String provider) {
//	    AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
//	    alertDialog.setTitle(provider + "SETTINGS");
//	    alertDialog.setMessage(provider +" is not enabled! Want to go to settings menu?");
//	    alertDialog.setPositiveButton("Settings",new DialogInterface.OnClickListener() {
//	          public void onClick(DialogInterface dialog, int which) {
//	        	  alert_response=1;
//	        	  Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
//	        	  getActivity().startActivity(intent);	           
//	          }
//	        });
//	    alertDialog.setNegativeButton("Cancel",		new DialogInterface.OnClickListener() {
//	    	public void onClick(DialogInterface dialog, int which) {
//	    		alert_response=0;
//	    		dialog.cancel();    	}
//	        });
//	    alertDialog.show();
//	    return alert_response;
//	  }
	public static String getUserpsw() {
		return userpsw;
	}



	public static void setUserpsw(String usrpsw) {
		userpsw = usrpsw;
	}
	
	public static void removeViews()
	{
		
		
		if(title2!=null)
			layout.removeView( title2);
		if(title!=null)
			layout.removeView( title);
		if(tx!=null)
		{
			
		
			if( tx.length!=0)
				for(int i=0 ;i<SearchFragment.tx.length ;i++)
				{
					layout.removeView(SearchFragment.tx[i]);
					if( TP[i] !=null)
					  TP[i].remove();
				}
		}
		if( tx2!=null)
		{
			
		
			if( tx2.length!=0)
				for(int i=0 ;i< tx2.length ;i++)
				{
					 layout.removeView( tx2[i]);
					if( TP2[i] !=null)
						 TP2[i].remove();
				}
		}
		if( tx1!=null)
		{
			
		
			if( tx1.length!=0)
				for(int i=0 ;i< tx1.length ;i++)
				{
					 layout.removeView(SearchFragment.tx1[i]);
					if( TP1[i] !=null)
						 TP1[i].remove();
				}
		}
	}

	
	
public static List<String> selectFromCheckList()
{
	List<String> list = new ArrayList<String>();
	if( checklist.size()==0)
 	{
 		list.add("Entertainment");
    	list.add("Drink");
    	list.add("Cinema");
    	list.add("Site seeing");
    	list.add("Education");
		list.add("Library");
		list.add("University");
    	list.add("Food");
    	list.add("Fast food Restaurant");
    	list.add("Take away Restaurant");
    	list.add("Typical Restaurant");
 	}
 	else
 	{
 		for(String s :  checklist)
 		{ 
 			if(s.equals("Entertainment")){
 				list.add("Entertainment");
	 			list.add("Drink");
		    	list.add("Cinema");
		    	list.add("Site seeing");
	     }	
	    else if(s.equals("Education")) {
	    		list.add("Education");
	    		list.add("Library");
	    		list.add("University");
	      }
	    else if(s.equals("Food")){
	    		list.add("Food");
	    		list.add("Fast food Restaurant");
	    		list.add("Take away Restaurant");
	    		list.add("Typical Restaurant");
	      }
	   }
	   
	   for( String t: checklist){
		   boolean found = false;
		   for (String s : list){
			   if (s.equals(t))  {
				   	found = true;
				   	break;
			   }
		   }
		   if(found == false)
			   list.add(t);
	   	}
 	}
	 for( String t: checklist)
		 Log.i("checklist",t);
	
	return list;
	
}
	
	
}
