package listeners;

import activities.Main;
import activities.SearchFragment;
import activities.SetFragment;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import asynctasks.MyTask_set;


public class SetFragmentListeners {

	

	
	
public static void addItemSelectedListenerToSpinner(){
		
		SetFragment.getSpinner().setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
					SetFragment.setSpinner_text(SetFragment.getSpinner().getSelectedItem().toString());
				
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				SetFragment.setSpinner_text(null);
			}
		});
	}
	
	
	
	public static void setButtonOnClickListeners(){
		
		
		SetFragment.getInsert().setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {
			
				String poi;
					
					Editable new_name= SetFragment.getPoiName().getText();
					SetFragment.setUserpsw(Main.username+"#"+Main.password);
					try
					{
					if (!new_name.toString().isEmpty())
						poi=SearchFragment.latitude.toString()+"#"+SearchFragment.longitude.toString()+"#"+ SetFragment.getSpinner_text()+"#"+new_name;
					else 
						poi="";
					Log.i("PRINT", poi);
					MyTask_set myTask = new MyTask_set(SetFragment.activity);
					myTask.execute(new String[] {SetFragment.getUserpsw(), poi});
					}
					catch(NullPointerException e)
					{
						
					}
			}
			
		});
		
	}	
}
