package activities;


import listeners.SignUpListeners;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;


import com.firstapp.myfirstapp.R;

public class SignUp extends Activity {
	
	  
	 public static EditText NewUsername, NewPassword, NewPasswordConf;
	 public static Button buttonCansel;
	 public static Button buttonSignUp;
	 public static Activity activity;
	 @Override
	 protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_up);
		 
		buttonSignUp = (Button) findViewById(R.id.buttonSignUp);
		buttonCansel = (Button) findViewById(R.id.buttonCansel);
		NewUsername = (EditText) findViewById(R.id.NewUsername);
		NewPassword=(EditText) findViewById(R.id.NewPassword);
		NewPasswordConf=(EditText) findViewById(R.id.NewPasswordConf);
		activity=this;
		SignUpListeners.setButtonOnClickListeners();
			
	    
	  
	}
    
	 
	 
	@Override
	public void onBackPressed() {
		Intent intent4 = new Intent(SignUp.this,Main.class);
        startActivity(intent4);
	}



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sign_up, menu);
		return true;
	}
//////////////////////////////////
	////////////////////////////////
	//////////////////////////////////
	//////////////////////////////////
	@Override
	public void onPause() {
	    super.onPause();  

	    //what we don't need to use when we are not in this actvity,when it is paused
	    
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		//final StackTraceElement[] ste = Thread.currentThread().getStackTrace();
	}
	
	@Override
	public void onResume() {
			super.onResume();  
	    // When the user resumes your activity from the Paused state, the system calls the onResume() method
	    //kanei epanakthsh enos activity pou exei kleisei
	    //Oti prepei na ftoiaxtei gia na anoi3ei to activity parelkomeno , ginetai edw
	}
	
	//When your activity receives a call to the onStop() method, it's no longer visible and should release almost 
	//all resources that aren't needed while the user is not using it
	@Override
	protected void onStop() {
	    super.onStop();  // Always call the superclass method first

	}
	
	//The onRestart() method, however, is called only when the activity resumes from the stopped state, 
	//so you can use it to perform special restoration work that might be necessary only if the activity
	//was previously stopped, but not destroyed.
	
	@Override
	protected void onRestart() {
	    super.onRestart();  // Always call the superclass method first
	    
	    // Activity being restarted from stopped state    
	}
	
	
	
}