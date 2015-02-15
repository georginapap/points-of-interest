package listeners;

import java.io.IOException;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpResponseException;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import activities.Main;
import activities.SignUp;
import activities.TabsActivity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class MainListeners {

	
	
	
	 private static final String NAMESPACE = "http://server/";
	 private static final String URL =  "http://88.197.8.219:9999/anaptixi/PoiService?WSDL";
	 private static final String SOAP_ACTION = "\"http://server/isUser\"";
	 private static final String METHOD_NAME = "isUser";
	
	 
	 public static void setButtonOnClickListeners()
	{
		
		Main.buttonNewAcc.setOnClickListener(new OnClickListener() {

	        @Override
	        public void onClick(View v) {
	        	
	            Intent intent1 = new Intent(Main.activity,SignUp.class);
	            Main.activity.startActivity(intent1);
	        }
	    });
		
		Main.buttonHomepg.setOnClickListener(new OnClickListener() {

	        @Override
	        public void onClick(View v) {
	        	
		    	Thread testThread = new Thread() {
		    		public void run() {
		    		
		    			// editor.putString("username", "www");
		    			
		    			String username= Main.settings.getString("username", "0");
		    			String password = Main.settings.getString("password", "0");
		    			if(username.equals(Main.editext1Homepg.getText().toString()) && password.equals(Main.editext2Homepg.getText().toString()) )
		    			{
		    				System.out.println("yuuuuuuuuuuu");
		    				Main.editor.putString("username", Main.editext1Homepg.getText().toString());
		    				Main.editor.putString("username", Main.editext2Homepg.getText().toString());
			    			Main.username=Main.editext1Homepg.getText().toString();
						    Main.password=Main.editext2Homepg.getText().toString();
						    Main.editor.commit();
			    			Intent intent2 = new Intent(Main.activity,TabsActivity.class);
			    			Main.activity.startActivity(intent2);
		    			}//Log.i("print",mpla);
		    			SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
		    			request.addProperty("arg0",Main.editext1Homepg.getText().toString());
				    	request.addProperty("arg1",Main.editext2Homepg.getText().toString());
				    	SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
				    	SoapPrimitive response=null;
				    	envelope.setOutputSoapObject(request);
				    	HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
				    	androidHttpTransport.debug = true;
				    	try
						{	
							androidHttpTransport.call(SOAP_ACTION, envelope);
							response = (SoapPrimitive)envelope.getResponse();	
						} catch (HttpResponseException e) {
							Log.i("PRINT", "Problem with SOAP 1  ");
						} catch (IOException e) {
							Log.i("PRINT", "Problem with SOAP 2  ");
							e.printStackTrace();
						} catch (XmlPullParserException e) {
							Log.i("PRINT", "Problem with SOAP  3 ");
						}
				        
				        if (response!=null)
				        { 
				        	 String answer = response.toString();
				        	 int answerFinal = Integer.parseInt(answer);
				        	if ( (answerFinal== -1) ||( answerFinal== 2) )
				    		{
				        		Main.activity.runOnUiThread( new Runnable() {

									@Override
									public void run() {
										Toast toast = Toast.makeText(Main.activity,"wrong username or password " ,Toast.LENGTH_SHORT);
										toast.show();
										
									}
	    							
	    						});
				    		}
				    		else if ( answerFinal== 1)
				    		{
				    			System.out.println("youhouuuuuuuuuuuuuuuuuuuuuu");
				    			Main.editor.putString("username", Main.editext1Homepg.getText().toString());
				    			Main.editor.putString("password", Main.editext2Homepg.getText().toString());
				    			Main.username=Main.editext1Homepg.getText().toString();
							    Main.password=Main.editext2Homepg.getText().toString();
							    Main.editor.commit();
							    Intent intent2 = new Intent(Main.activity,TabsActivity.class);
				    			Main.activity.startActivity(intent2);
						      
				    		}
				        	
					    }
				        else
				        {
				        	
				        	System.out.println("no resultssssssssss main");
				        }
						 
		    		
		    	}
		    	};
		    	testThread.start();
		 
	        }
	    });
	}
}