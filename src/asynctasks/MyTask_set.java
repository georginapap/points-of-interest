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

import database.Pending;
import database.Verified;

import activities.Main;
import activities.SearchFragment;
import activities.SetFragment;
import activities.TabsActivity;
import android.app.Activity;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;

public class MyTask_set extends AsyncTask<String, Integer, String>{

	private static final String NAMESPACE = "http://server/";
	private static final String URL = "http://88.197.8.219:9999/anaptixi/PoiService?WSDL";
	private static final String SOAP_ACTION =  "\"http://server/setMonitorData\"" ;
	private static final String METHOD_NAME = "setMonitorData";
	private static Activity myactivity = null ;
	private static final Double r=0.00000000001;

	public MyTask_set(FragmentActivity activity) {
		myactivity=activity;
	}

	@Override
	protected String doInBackground(String... params) 
	{
		String response = null;
		SoapObject result = null ;
		SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
		if (params[1].isEmpty())
		{ 
			response ="You need to provide Name of POI ";
			return response;
		}
		else
		{
			List <Verified>  result_verified= new ArrayList<Verified>();
			result_verified=TabsActivity.datasource.getAllVerified();
			if(result_verified.size() != 0)	
			{	
				for(Verified v : result_verified)
				{
					if((Math.abs(SearchFragment.latitude -v.getLatitude())<= r) && (Math.abs(SearchFragment.longitude -v.getLongitude())<= r))
					{
						response="Poi exists with close the same coordinates in your database";
						return response;
					}
				}
				Log.i("DATASOURCE", "it is not in the verified database so keep going");
			}
			// an dn uparxei sth topikh paei na dei sto server
			Log.i("DATASOURCE", "not in verified");
			if(SetFragment.haveNetworkConnection()==1)
				//an exei wifi
			{	
				request.addProperty("arg0",params[0]);
				request.addProperty("arg1",params[1]);
				SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(
						SoapEnvelope.VER11);
				soapEnvelope.setOutputSoapObject(request);
				HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
				androidHttpTransport.debug = true;
				Log.i("PRINT", "we have connection");
				try {
					androidHttpTransport.call(SOAP_ACTION, soapEnvelope);
					Log.i("PRINT", "before");
					result = (SoapObject) soapEnvelope.bodyIn;
					response = result.getProperty(0).toString();
					Log.i("PRINT", "response   :"+ response);
				} catch (HttpResponseException e) {
					Log.i("PRINT", "Problem with SOAP SetMonitor 1 ");
					response="Problem with soap";
				} catch (IOException e) {
					Log.i("PRINT", "Problem with SOAP SetMonitor 2 ");
					response="Problem with soap";
				} catch (XmlPullParserException e) {
					Log.i("PRINT", "Problem with SOAP SetMonitor 3 ");
					response="Problem with soap";
				}

				if (response.equals("Succesfull POI insertion"))
					//an dn uparxei sth kedrikh vash to vasei kai to vasei kai sthn topikh
				{
					Log.i("PRINT", "we will add the poi to the verified table");
					TabsActivity.datasource.createVerified(SearchFragment.latitude, SearchFragment.longitude,
							SetFragment.getSpinner_text(), SetFragment.getPoiName().getText().toString());
					//if (TabsActivity.datasource.findPending(SearchFragment.latitude, SearchFragment.longitude)!=true)
						//otan ta vazw sthn topikh, kalw server gia  ta pendings 
						FromPending();
					

					Log.i("PRINT", "we insert itttttttttt verified");
					//testing();
				}
				else if (response.equals("The poi already exists"))
				{
					TabsActivity.datasource.createVerified(SearchFragment.latitude, SearchFragment.longitude,
							SetFragment.getSpinner_text(), SetFragment.getPoiName().getText().toString());
				}
			}
			else
				//dn exei wifi (dn uparxoun sthn topikh)
			{
				if (TabsActivity.datasource.findPending(SearchFragment.latitude, SearchFragment.longitude)==true)
				{
					Log.i("PRINT", "we will add the poi to the pending table");
					TabsActivity.datasource.createPending(SearchFragment.latitude, SearchFragment.longitude,
							SetFragment.getSpinner_text(), SetFragment.getPoiName().getText().toString());
					Log.i("PRINT", "we insert itttttttttt pending");
					response= " Your poi will be inserted when you have internet connection ";
				}
				else
				{
					response=" You have already inserted this poi!!!lvghvlfthlfx ";
				}
			}

			return response;
		} 

	}

	protected void onPostExecute(String response) {
		Toast toast = Toast.makeText(myactivity,response ,Toast.LENGTH_SHORT);
		toast.show();

	}

	public static void FromPending()
	{Log.i("PRINT", "fp1");
		if (SetFragment.haveNetworkConnection()==1)
		{
			List <Pending>  result_pending= new ArrayList<Pending>();
			result_pending=TabsActivity.datasource.getAllPending();
			Log.i("PRINT", "fp1");
			if(result_pending.size() != 0)	
			{	Log.i("PRINT", "fp2");
				for(Pending p : result_pending)
				{	Log.i("PRINT", "fp3");
					String userpass=Main.username +"#"+ Main.password ;
					String poi = p.getLatitude().toString()+"#"+p.getLongitude().toString()+
					"#"+p.getType()+"#" +p.getName();
					Log.i("PRINT", poi);
			/////////////////////////////////////
					SoapObject result = null ;
					String response=null;
					SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
			
					request.addProperty("arg0",userpass);
					request.addProperty("arg1",poi);
					SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(
					SoapEnvelope.VER11);
					soapEnvelope.setOutputSoapObject(request);
					HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
					androidHttpTransport.debug = true;
					try {
						androidHttpTransport.call(SOAP_ACTION, soapEnvelope);
						Log.i("PRINT", "fp4");
						result = (SoapObject) soapEnvelope.bodyIn;
						response = result.getProperty(0).toString();
						Log.i("PRINT", "response   :fp5"+ response);
					} catch (HttpResponseException e) {
						Log.i("PRINT", "Problem with SOAP SetMonitor 1P ");
						response="Problem with soap";
					} catch (IOException e) {
						Log.i("PRINT", "Problem with SOAP SetMonitor 2 P");
						response="Problem with soap";
					} catch (XmlPullParserException e) {
						Log.i("PRINT", "Problem with SOAP SetMonitor 3 P");
						response="Problem with soap";
					}
					if (response.equals("Succesfull POI insertion"))
					{	
						TabsActivity.datasource.deletePending(p);
						Log.i("PRINT", "we insert ittt from pending to verified ");
					}
			
				}
			}
		}
		else
			Log.i("PRINT", "dn exeis internet na valei ta pending sto server ");
	
	}

}
