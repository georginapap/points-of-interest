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

public class SignUpListeners {

	 private static final String NAMESPACE = "http://server/";
	 private static final String URL =  "http://88.197.8.219:9999/anaptixi/PoiService?WSDL";
	 private static final String SOAP_ACTION = "\"http://server/registerUser\"";
	 private static final String METHOD_NAME = "registerUser";
	 public static void setButtonOnClickListeners()
     {
    	SignUp.buttonSignUp.setOnClickListener(new OnClickListener() {

		    @Override
		    public void onClick(View v) {
		    	
		    	Thread testThread = new Thread() {
		    		public void run() {
		    			String arg;
		    			if((SignUp.NewPassword.getText().toString().equals(SignUp.NewPasswordConf.getText().toString())) && (!SignUp.NewPassword.getText().toString().isEmpty()) )
		    			{
		    				if((!SignUp.NewUsername.getText().toString().isEmpty()) )
		    				{
		    					SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
		    					arg=SignUp.NewUsername.getText().toString()+"#"+SignUp.NewPassword.getText().toString()+
		    							"#"+SignUp.NewPasswordConf.getText().toString();
		    					request.addProperty("arg0",arg);
		    					SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		    					SoapPrimitive response=null;
		    					envelope.setOutputSoapObject(request);
		    					try
		    					{	HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
		    						Log.i("PRINT", "before");
		    						androidHttpTransport.call(SOAP_ACTION, envelope);
		    						Log.i("PRINT", "after");
		    						response = (SoapPrimitive)envelope.getResponse();
		    						Log.i("PRINT", "object");
		    					} catch (HttpResponseException e) {
		    						Log.i("PRINT", "Problem with SOAP 1 sign in ");
		    					} catch (IOException e) {
		    						Log.i("PRINT", "Problem with SOAP 2  sign in");
		    						e.printStackTrace();
		    					} catch (XmlPullParserException e) {
		    						Log.i("PRINT", "Problem with SOAP  3 sign in");
		    					}
		    					if (response!=null)
		    					{
		    						String answer = response.toString();
		    						if(answer.equals("Success"))
		    						{
		    							System.out.println(response);
		    							Main.username=SignUp.NewUsername.getText().toString();
		    							Main.password=SignUp.NewPassword.getText().toString();
		    							
		    							Intent intent3 = new Intent(SignUp.activity,TabsActivity.class);
		    							SignUp.activity.startActivity(intent3);
		    						}
		    						else if (answer.equals("Username already exists"))
		    						{
		    						
		    							SignUp.activity.runOnUiThread( new Runnable() {

										@Override
											public void run() {
												Toast toast = Toast.makeText(SignUp.activity,"Username already exists" ,Toast.LENGTH_SHORT);
												toast.show();
											
											}
		    							
		    							});
				        		
		    						}
		    						else 
		    						{
		    							SignUp.activity.runOnUiThread( new Runnable() {

			    						@Override
			    							public void run() {
		    									Toast toast = Toast.makeText(SignUp.activity,"Wrong username or password" ,Toast.LENGTH_SHORT);
		    									toast.show();
											
		    								}
		    								
		    							});
		    						}	
					        
		    					}
		    					else
		    					{
		    						System.out.println("no resultssssssssssssssss");
		    					}
		    				}
		    				else 
		    				{
		    					SignUp.activity.runOnUiThread( new Runnable() {

		    						@Override
									public void run() {
		    							Toast toast = Toast.makeText(SignUp.activity,"field must contain at least one character" ,Toast.LENGTH_SHORT);
		    							toast.show();
		    							
		    						}
    							
		    					});
    						
		    				}
		    			}
		    			else
		    			{
		    				SignUp.activity.runOnUiThread( new Runnable() {

								@Override
								public void run() {
									Toast toast = Toast.makeText(SignUp.activity,"passwords Do not match" ,Toast.LENGTH_SHORT);
									toast.show();
									
								}
    							
    						});
		    			}
		    			
		    			
		    			
		    		}
		    	};
		    	testThread.start();
		    }
	    });

    	SignUp.buttonCansel.setOnClickListener(new OnClickListener() {

	        @Override
	        public void onClick(View v) {
	        	Intent intent4 = new Intent(SignUp.activity,Main.class);
	        	SignUp.activity.startActivity(intent4);
	        }
	    });
    }
}
