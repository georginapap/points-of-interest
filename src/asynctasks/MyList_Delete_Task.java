package asynctasks;



	


	import java.io.IOException;

	import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpResponseException;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;


import android.app.Activity;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;

	public class MyList_Delete_Task extends AsyncTask<String, Integer, String>{

			
			private static final String NAMESPACE = "http://server/";
			private static final String URL = "http://88.197.8.219:9999/anaptixi/PoiService?WSDL";
			private static final String SOAP_ACTION =  "\"http://server/deleteData\"" ;
			private static final String METHOD_NAME = "deleteData";    //String username,double x , double y
			private static Activity myactivity = null ;
			String result_string= "";
			//private ArrayAdapter<String> listAdapter ;  
			
			public MyList_Delete_Task(FragmentActivity activity) {
				myactivity=activity;
			}



			@Override
		    protected String doInBackground(String... params) {
				 String response = "";
				    SoapObject result = null ;
				    SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
				  		 	
				    	request.addProperty("arg0",params[0]);
				    	Log.i("PRINT", params[0]);
				    	Log.i("PRINT", params[1]);
				    	Log.i("PRINT", params[2]);
				    	request.addProperty("arg1",params[1]);
				    	request.addProperty("arg1",params[2]);
				    	SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(
				        SoapEnvelope.VER11);
				    	soapEnvelope.setOutputSoapObject(request);
				    	HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
				    	androidHttpTransport.debug = true;
				    	// this is the actual part that will call the webservice
				    	Log.i("PRINT", "before");
				    		
							 try {
								androidHttpTransport.call(SOAP_ACTION, soapEnvelope);
								Log.i("PRINT", "before");
					            result = (SoapObject) soapEnvelope.bodyIn;
					        	Log.i("PRINT", " BEFORE  response   :"+ response);
							 	response = result.getProperty(0).toString();
							 	Log.i("PRINT", "response   :"+ response);
							
							} catch (HttpResponseException e) {
								Log.i("PRINT", "Problem with SOAP DeleteData 1 ");
							} catch (IOException e) {
								Log.i("PRINT", "Problem with SOAP  DeleteData 2 ");
							} catch (XmlPullParserException e) {
								Log.i("PRINT", "Problem with SOAP  DeleteData 3 ");
							} 
							 
				
					
					
				    return response;
				   
				    
		}
			
			
			
			protected void onPostExecute(String response) {
				Toast toast = Toast.makeText(myactivity,response ,Toast.LENGTH_SHORT);
				toast.show();
			
		}
			
	}

