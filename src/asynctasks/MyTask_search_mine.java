package asynctasks;



import java.util.ArrayList;
import java.util.List;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import database.Pending;
import database.Verified;
import activities.SearchFragment;
import activities.TabsActivity;
import android.app.Activity;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.TextView;


	public class MyTask_search_mine extends AsyncTask<String, Integer, String>{

		

		private static Activity myactivity = null ;
		String result_string=null; 
		
		public MyTask_search_mine(FragmentActivity activity) {
			myactivity=activity;
		}



		@Override
	    protected String doInBackground(String... params) {
			
			
			
			List<Verified> values = TabsActivity.datasource.getAllVerified();
			List <Pending> pending =TabsActivity.datasource.getAllPending();
			List<String> list = new ArrayList<String>();
			list= SearchFragment.selectFromCheckList();
			result_string="";
					   for(String s2:list)
						   Log.i("list",s2);
				 	if(list.isEmpty())
				 	{	for(Verified s: values)
				 			if((Math.abs(s.getLongitude()-SearchFragment.longitude) <= SearchFragment.aktina)
				 				&&(Math.abs(s.getLatitude()-SearchFragment.latitude) <=SearchFragment.aktina))
				 					SearchFragment.result_values.add(s);
				 		for(Pending p:pending)
				 			if((Math.abs(p.getLongitude()-SearchFragment.longitude) <= SearchFragment.aktina)
					 				&&(Math.abs(p.getLatitude()-SearchFragment.latitude) <= SearchFragment.aktina))
				 						SearchFragment.result_pending.add(p);
				 	}
				 	else
				 	{
				 		for(Verified s: values)
				 			for(String s3:list)
				 				if(s.getType().equals(s3))
				 					if((Math.abs(s.getLongitude()-SearchFragment.longitude) <= SearchFragment.aktina)
							 				&&(Math.abs(s.getLatitude()-SearchFragment.latitude) <=SearchFragment.aktina))
							 					SearchFragment.result_values.add(s);
				 		for(Pending p:pending)
				 			for(String s3:list)
				 				if(p.getType().equals(s3))
				 					if((Math.abs(p.getLongitude()-SearchFragment.longitude) <= SearchFragment.aktina)
							 				&&(Math.abs(p.getLatitude()-SearchFragment.latitude) <=SearchFragment.aktina))
				 								SearchFragment.result_pending.add(p);
				 		
				 	}
						
				 		
					
		
		
	    return result_string;
	  
	}
		
		
		
		protected void onPostExecute(String response) {
			SearchFragment.removeViews();
			  
		 	for(Verified s: SearchFragment.result_values)
		 		Log.i(" verified new ",s.toString());
	 		for(Pending p:SearchFragment.result_pending)
	 			Log.i("  pendind new ",p.toString());
			if((SearchFragment.result_pending.size()!=0) || (SearchFragment.result_values.size()!=0))
			{
				SearchFragment.title = new TextView(myactivity);
				SearchFragment.title.setText(" My Near by pois ");
				SearchFragment.title.setTextColor(Color.rgb(49,244,227));
				SearchFragment.title.setTextSize(25);
				SearchFragment.googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
				SearchFragment. googleMap.getUiSettings().setZoomGesturesEnabled(true);
				SearchFragment. googleMap.setMyLocationEnabled(true);

				SearchFragment.layout.addView(SearchFragment.title);
			}
			if(SearchFragment.result_values.size()>0)
			{

				SearchFragment.tx = new TextView[SearchFragment.result_values.size()];
				SearchFragment.TP= new 	Marker[SearchFragment.result_values.size()];
				SearchFragment.poisaround=new  LatLng[SearchFragment.result_values.size()];
				
				int i=0;
				for (Verified v: SearchFragment.result_values) {
					SearchFragment.tx[i] = new TextView(myactivity);
					SearchFragment.tx[i].setText(v.toString());
					SearchFragment.tx[i].setTextColor(Color.rgb(213,203,198));
					SearchFragment.layout.addView(SearchFragment.tx[i]);
					
				
				   SearchFragment.poisaround[i] = new LatLng(v.getLatitude(), v.getLongitude());
					try{ 
			         
						
						SearchFragment.googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(SearchFragment.poisaround[i], 13));
						SearchFragment.TP[i] = SearchFragment.googleMap.addMarker(new MarkerOptions().position(SearchFragment.poisaround[i])
								.title(v.getName()).snippet(v.getType()));
			            Log.i("print","marker");
					}catch (Exception e) {
			         Log.e("PRINT","Error while opening google map");
					}
					i++;
				}
			}
			if(SearchFragment.result_pending.size()>0)
			{
				SearchFragment.tx2 = new TextView[SearchFragment.result_pending.size()];
				SearchFragment.TP2= new 	Marker[SearchFragment.result_pending.size()];
				SearchFragment.poisaround2=new  LatLng[SearchFragment.result_pending.size()];
				int i=0;
				for (Pending p: SearchFragment.result_pending) {
					SearchFragment.tx2[i] = new TextView(myactivity);
					SearchFragment.tx2[i].setText(p.toString());
					SearchFragment.tx2[i].setTextColor(Color.rgb(213,203,198));
					SearchFragment.layout.addView(SearchFragment.tx2[i]);

					
					   SearchFragment.poisaround2[i] = new LatLng(p.getLatitude(), p.getLongitude());
						try{ 
				         
			
							//SearchFragment.googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(SearchFragment.poisaround2[i], 13));
							SearchFragment.TP2[i] = SearchFragment.googleMap.addMarker(new MarkerOptions().position(SearchFragment.poisaround2[i])
									.title(p.getName()).snippet(p.getType()));
				            Log.i("print","marker");
						}catch (Exception e) {
				         Log.e("PRINT","Error while opening google map");
						}
						
					i++;
				}
				
			}
			if((SearchFragment.result_pending.size()==0) && (SearchFragment.result_values.size()==0))
			{
				SearchFragment.title = new TextView(myactivity);
				SearchFragment.title.setText(" no pois available ");
				SearchFragment.title.setTextColor(Color.rgb(49,244,227));
				SearchFragment.title.setTextSize(25);
				SearchFragment.layout.addView(SearchFragment.title);
			}
			SearchFragment.result_pending.clear();
			SearchFragment.result_values.clear();
	    }
		
		
		
	}
