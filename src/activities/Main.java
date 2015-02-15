package activities;



import listeners.MainListeners;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;

import com.firstapp.myfirstapp.R;


public class Main extends Activity {

	 public static String username , password;
	 public static Activity activity ;
	 public static Button buttonNewAcc ;
	 public static Button buttonHomepg;
	 public static  EditText editext1Homepg,editext2Homepg;
	
	 public static final String PREFS_NAME= "MyPrefsFIle";

	 public static SharedPreferences settings ;
	 public static SharedPreferences.Editor  editor;
	 
	 
	@Override
	 protected void onCreate(Bundle savedInstanceState) {
		

		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		buttonNewAcc = (Button) findViewById(R.id.buttonNewAcc);
		buttonHomepg = (Button) findViewById(R.id.buttonHomepg);
		editext1Homepg=(EditText) findViewById(R.id.editext1Homepg);
		editext2Homepg=(EditText) findViewById(R.id.editext2Homepg);
		
		activity=this;
		settings = getSharedPreferences(PREFS_NAME, 0);
		editor =settings.edit();
		username= settings.getString("username", "0");
		password= settings.getString("password", "0");
		if((username!="0" ) && (password !="0"))
		{
			 Intent intent2 = new Intent(Main.activity,TabsActivity.class);
 			Main.activity.startActivity(intent2);
		}
		MainListeners.setButtonOnClickListeners();
		
		


	}


	@Override
	public void onBackPressed() {
		 moveTaskToBack(true);
	}



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}