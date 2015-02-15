package asynctasks;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpResponseException;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

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


	public class MyTask_search_all extends AsyncTask<String, Integer, String>{

		
		private static final String NAMESPACE = "http://server/";
		private static final String URL = "http://88.197.8.219:9999/anaptixi/PoiService?WSDL";
		private static final String SOAP_ACTION =  "\"http://server/getMapData\"" ;
		private static final String METHOD_NAME = "getMapData";
		private static Activity myactivity = null ;
		String result_string= "";
		//private ArrayAdapter<String> listAdapter ;  
		
		public MyTask_search_all(FragmentActivity activity) {
			myactivity=activity;
		}



		@Override
	    protected String doInBackground(String... params) {
			 String response = "";
			    SoapObject result = null ;
			    SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
			    if (params[1].isEmpty())
			    {	
			    	response ="You need to provide Name of POI ";
			    	return response;
			    }
			    else
			    {
			    	List<Verified> values = TabsActivity.datasource.getAllVerified();
					List <Pending> pending =TabsActivity.datasource.getAllPending();
					List<String> list = new ArrayList<String>();
					list= SearchFragment.selectFromCheckList();
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
								
			    	request.addProperty("arg0",params[0]);
			    	request.addProperty("arg1",params[1]);
			    	SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(
			        SoapEnvelope.VER11);
			    	soapEnvelope.setOutputSoapObject(request);
			    	HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
			    	androidHttpTransport.debug = true;
			    	// this is the actual part that will call the webservice
			    		
						 try {
							androidHttpTransport.call(SOAP_ACTION, soapEnvelope);
							Log.i("PRINT", "before");
				            result = (SoapObject) soapEnvelope.bodyIn;
				        	Log.i("PRINT", " BEFORE  response   :"+ response);
						 	response = result.getProperty(0).toString();
						 	Log.i("PRINT", "response   :"+ response);
							   try{
								   String[] part = response.split("#"); 
								   for(int i =2 ; i < part.length ;i+=4){
									   	String temp;
									   	for (String s :list)
									   		if(part[i].equals(s))
									   		{
									   			if((Math.abs(Double.parseDouble(part[i-2])-SearchFragment.longitude) <= SearchFragment.aktina)
														 &&(Math.abs(Double.parseDouble(part[i-1])-SearchFragment.latitude) <=SearchFragment.aktina))
												   		 {
															temp= part[i-2] +","+ part[i-1] +","+ part[i+1] +","+ part[i]+"/n" ;
												   			result_string+=temp;	
												   		}
									   			break;
									   		}
								   	}
								}
								catch (NullPointerException e)
								{
									response="server unavailable";
								}
						} catch (HttpResponseException e) {
							Log.i("PRINT", "Problem with SOAP SetMonitor 1 ");
						} catch (IOException e) {
							Log.i("PRINT", "Problem with SOAP SetMonitor 2 ");
						} catch (XmlPullParserException e) {
							Log.i("PRINT", "Problem with SOAP SetMonitor 3 ");
						}
						
						 
						 
				Log.i("PRINT", "response   :"+ result_string);	
				
				 list.clear();
			    return result_string;
			   
			    }
	}
		
		
		
		protected void onPostExecute(String response) {
			SearchFragment.removeViews();
			if((!response.equals("")) || (SearchFragment.result_pending.size()!=0) || (SearchFragment.result_values.size()!=0))
			{
				SearchFragment.title = new TextView(myactivity);
				SearchFragment.title.setText("Near by pois ");
				SearchFragment.title.setTextColor(Color.rgb(49,244,227));
				SearchFragment.title.setTextSize(25);
				SearchFragment.layout.addView(SearchFragment.title);
			}
			if(!response.equals(""))
			{
				String [] part;
				part=response.split("/n");
				for (int i = 0;i< part.length; i++)
					Log.i("response",part[i]);
				
				SearchFragment.TP= new 	Marker[part.length];
				SearchFragment.poisaround=new  LatLng[part.length];
				
	           
				
				for (int i = 0;i< part.length; i++) {
					String part2[]=part[i].split(",");
					try
					{
							SearchFragment.tx[i] = new TextView(myactivity);
							Log.i("print!!!!!!!!!!!!!!!!!!!!!!!",part2[2]);
							SearchFragment.tx[i].setText(part2[2]+"("+ part2[3]+")");
							SearchFragment.tx[i].setTextColor(Color.rgb(213,203,198));
							SearchFragment.layout.addView(SearchFragment.tx[i]);
							SearchFragment.poisaround[i] = new LatLng(Double.parseDouble(part2[0]), Double.parseDouble(part2[1]));
						try{ 
	         
								SearchFragment.googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
								SearchFragment. googleMap.getUiSettings().setZoomGesturesEnabled(true);
								SearchFragment. googleMap.setMyLocationEnabled(true);
								SearchFragment.googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(SearchFragment.poisaround[i], 13));
								SearchFragment.TP[i] = SearchFragment.googleMap.addMarker(new MarkerOptions().position(SearchFragment.poisaround[i]).title(part2[2]).snippet(part2[3]));
								Log.i("print","marker");
						}catch (Exception e) {
						Log.e("PRINT","Error while opening google map");
						}	
					}
					catch(NullPointerException e)
					{
						
					}
					
				}
			}
		/////////////////////////////////////
		if(SearchFragment.result_values.size()>0)
		{

			SearchFragment.tx1 = new TextView[SearchFragment.result_values.size()];
			SearchFragment.TP1= new 	Marker[SearchFragment.result_values.size()];
			SearchFragment.poisaround1=new  LatLng[SearchFragment.result_values.size()];
			
			int i=0;
			for (Verified v: SearchFragment.result_values) {
				SearchFragment.tx1[i] = new TextView(myactivity);
				SearchFragment.tx1[i].setText(v.toString());
				SearchFragment.tx1[i].setTextColor(Color.rgb(213,203,198));
				SearchFragment.layout.addView(SearchFragment.tx1[i]);
				
			
			   SearchFragment.poisaround1[i] = new LatLng(v.getLatitude(), v.getLongitude());
				try{ 
		         
					
					SearchFragment.googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(SearchFragment.poisaround1[i], 13));
					SearchFragment.TP[i] = SearchFragment.googleMap.addMarker(new MarkerOptions().position(SearchFragment.poisaround1[i])
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
		if((SearchFragment.result_pending.size()==0) && (SearchFragment.result_values.size()==0) && (result_string.equals("")))
		{
			SearchFragment.title = new TextView(myactivity);
			SearchFragment.title.setText(" no pois available ");
			SearchFragment.title.setTextColor(Color.rgb(49,244,227));
			SearchFragment.title.setTextSize(25);
			SearchFragment.layout.addView(SearchFragment.title);
		}
		SearchFragment.result_pending.clear();
		SearchFragment.result_values.clear();
		
	}}