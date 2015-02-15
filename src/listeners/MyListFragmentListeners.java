package listeners;


import activities.Main;
import activities.MoreInfo;
import activities.MyListFragment;
import activities.TabsActivity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import asynctasks.MyList_Delete_Task;
import database.Pending;
import database.Verified;


public class MyListFragmentListeners {
 static CheckedTextView cvP;
 static int lastCheckedP;
 static CheckedTextView cvV;
 static int lastCheckedV;

 public static void addItemSelectedListenerTolistViewP(){ 
 MyListFragment.mylistviewP.setOnItemClickListener(new OnItemClickListener(){
  
	 @Override
	    public void onItemClick(AdapterView<?> arg0, View view, int position,long id) {
	   ListView  listviewP = (ListView) arg0;
	   TextView tv = (TextView) listviewP.getChildAt(position);
	    cvP = (CheckedTextView) tv;
	   if(MyListFragment.isListViewPChecked( )){        
	          MyListFragment.changeListviewPChecked();
	          MyListFragment.setSelectedTextP(null);
	          MyListFragment.setpositionCheckedP(-1);
	          cvP.setChecked(false);
	          Log.i("Listener ListP","uncheck: "+lastCheckedP);
	          if(listviewP.getChildAt(lastCheckedP)!=listviewP.getChildAt(position)){
	        	  if(!cvP.isChecked())
	        		  cvP.setChecked(true);      
	        	  lastCheckedP = position;
	        	  MyListFragment.changeListviewPChecked();
	        	  Log.i("LIST CHECK TRUE: ",""+MyListFragment.isListViewPChecked( ));
	        	  MyListFragment.setSelectedTextP(tv.getText().toString());
	        	  MyListFragment.setpositionCheckedP(position);
	        	  Log.i("LISTENER LIST P","checked:"+  MyListFragment.getSelectedTextP()+"\n");
	        	  Toast.makeText(MyListFragment.list_activity,"Click ListItem Number P " + position, Toast.LENGTH_LONG).show();
	          	}
	   }
	   else
	   {
		   if(!cvP.isChecked())
	       cvP.setChecked(true);      
	        lastCheckedP = position;
	        MyListFragment.changeListviewPChecked();
	        Log.i("LIST CHECK TRUE: ",""+MyListFragment.isListViewPChecked( ));
	        MyListFragment.setSelectedTextP(tv.getText().toString());
	        MyListFragment.setpositionCheckedP(position);
	        Log.i("LISTENER LIST P","checked:"+  MyListFragment.getSelectedTextP()+"\n");
	        Toast.makeText(MyListFragment.list_activity,
	          "Click ListItem Number P " + position, Toast.LENGTH_LONG)
	           .show();
	    }
	  }
	 });
	}

 public static void addItemSelectedListenerTolistViewV(){ 
	 MyListFragment.mylistviewV.setOnItemClickListener(new OnItemClickListener(){
	  
	  @Override
	    public void onItemClick(AdapterView<?> arg0, View view, int position,long id) {
	   ListView  listviewV = (ListView) arg0;
	   TextView tv = (TextView) listviewV.getChildAt(position);
	    cvV = (CheckedTextView) tv;
	   if(MyListFragment.isListViewVChecked( )){        
	          MyListFragment.changeListviewVChecked();
	          MyListFragment.setSelectedTextV(null);
	          MyListFragment.setpositionCheckedV(-1);
	          cvV.setChecked(false);
	          Log.i("Listener ListV","uncheck: "+lastCheckedV);
	          if(listviewV.getChildAt(lastCheckedV)!=listviewV.getChildAt(position)){
	        	  if(!cvV.isChecked())
	        		  cvV.setChecked(true);      
	           lastCheckedV = position;
	           MyListFragment.changeListviewVChecked();
	           Log.i("LIST CHECK TRUE: ",""+MyListFragment.isListViewVChecked( ));
	           MyListFragment.setSelectedTextV(tv.getText().toString());
	           MyListFragment.setpositionCheckedV(position);
	           Log.i("LISTENER LIST V","checked:"+  MyListFragment.getSelectedTextV()+"\n");
	           Toast.makeText(MyListFragment.list_activity,"Click ListItem Number V " + position, Toast.LENGTH_LONG).show();
	          }
	  }
	   else
	  {
		  if(!cvV.isChecked())
			  cvV.setChecked(true);      
		  lastCheckedV = position;
		  MyListFragment.changeListviewVChecked();
		  Log.i("LIST CHECK TRUE: ",""+MyListFragment.isListViewVChecked( ));
		  MyListFragment.setSelectedTextV(tv.getText().toString());
		  MyListFragment.setpositionCheckedV(position);
		  Log.i("LISTENER LIST V","checked:"+  MyListFragment.getSelectedTextV()+"\n");
		  Toast.makeText(MyListFragment.list_activity,"Click ListItem Number V " + position, Toast.LENGTH_LONG).show();
	  }
	   
	  }
	 });
	}

private static void addListenerOn_buttonview(){
 MyListFragment.buttonview.setOnClickListener(new OnClickListener() {
 @Override
   public void onClick(View v) {
	 Intent intentlist = new Intent(MyListFragment.list_activity,MoreInfo.class);
	 MyListFragment.list_activity.startActivity(intentlist);
	 Log.i("LISTENER LIST","VIEW!");
  
   }
 });
}

private static void addListenerOn_buttondelete(){
 MyListFragment.buttondelete.setOnClickListener(new OnClickListener() {
  Verified verified = null;
     Pending pending = null;
    
  @Override
   public void onClick(View v) {
    if(MyListFragment.isListViewVChecked()){
          verified = (Verified) MyListFragment.mylistviewV.getAdapter().getItem(MyListFragment.positionCheckedV());
          MyListFragment.changeListviewVChecked();
     	  MyListFragment.setSelectedTextV(null);
          MyListFragment.setpositionCheckedV(-1);
          cvV.setChecked(false);        
          TabsActivity.datasource.deleteVerified(verified);
          MyListFragment.adapterV.remove(verified);
          MyListFragment.adapterV.notifyDataSetChanged();     
          MyList_Delete_Task  myTask_delete = new MyList_Delete_Task(MyListFragment.list_activity);
          myTask_delete.execute(new String[] {Main.username,verified.getLatitude().toString(),verified.getLongitude().toString()});
        
        }
 
    	if(MyListFragment.isListViewPChecked( )){
    			pending = (Pending) MyListFragment.mylistviewP.getAdapter().getItem(MyListFragment.positionCheckedP());
    			MyListFragment.changeListviewPChecked();
    			MyListFragment.setSelectedTextP(null);
    			MyListFragment.setpositionCheckedP(-1);
    			cvP.setChecked(false);
    			Log.i("Listener ListP","uncheck: "+lastCheckedP);
    			TabsActivity.datasource.deletePending(pending);
    			MyListFragment.adapterP.remove(pending);
    			MyListFragment.adapterP.notifyDataSetChanged();
    	} 
   	}  
 });
}



	public static void addAllListListeners()
	{
			addItemSelectedListenerTolistViewV();
			addItemSelectedListenerTolistViewP();
			addListenerOn_buttonview();
			addListenerOn_buttondelete();
	}

}