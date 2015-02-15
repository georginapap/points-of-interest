package listeners;

import activities.Main;
import activities.MoreInfo;
import activities.MyListFragment;
import activities.TabsActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;
import asynctasks.MyList_Delete_Task;
import database.Pending;
import database.Verified;

public class MoreInfoListeners {

 
 public static void addMoreInfoListeners(){
  addButtonInfoPListener();
  addButtonInfoVListener();
 }
 
 public static void addButtonInfoPListener(){
  MoreInfo.buttondeleteInfoP.setOnClickListener(new OnClickListener() {  
   Pending pending = null;
   @Override
   public void onClick(View v) {
    if(MyListFragment.isListViewPChecked( )){
        pending = (Pending) MyListFragment.mylistviewP.getAdapter().getItem(MyListFragment.positionCheckedP());
        MyListFragment.changeListviewPChecked();
        MyListFragment.setSelectedTextP(null);
        MyListFragment.setpositionCheckedP(-1);
        MyListFragmentListeners.cvP.setChecked(false);
        Log.i("LISTENER DELETE MORE INFO P","uncheck: "+MyListFragmentListeners.lastCheckedP);
        TabsActivity.datasource.open();
        TabsActivity.datasource.deletePending(pending);
        Toast toast =Toast.makeText(MoreInfo.moreinfoactivity,"Succesful Delete" ,Toast.LENGTH_SHORT);
        toast.show();
        MyListFragment.adapterP.remove(pending);
        MyListFragment.adapterP.notifyDataSetChanged();
        MoreInfo.adapterP.notifyDataSetChanged();
    }
    else
    {
    	Toast toast =Toast.makeText(MoreInfo.moreinfoactivity,"You have nothing to delete" ,Toast.LENGTH_SHORT);
    	toast.show();
    }   
    
   }
  });
 }
 
 public static void addButtonInfoVListener(){
	 MoreInfo.buttondeleteInfoV.setOnClickListener(new OnClickListener() {  
		 	Verified verified = null;
		 	
		 	@Override
		 	
		 public void onClick(View v) {
		 	
		 	if(MyListFragment.isListViewVChecked( )){
		 		verified = (Verified) MyListFragment.mylistviewV.getAdapter().getItem(MyListFragment.positionCheckedV());
		 	MyListFragment.changeListviewVChecked();
		 	MyListFragment.setSelectedTextV(null);
		 	MyListFragment.setpositionCheckedV(-1);
		 	MyListFragmentListeners.cvV.setChecked(false);
		 	MoreInfo.infoV.clearChoices();
		 	Log.i("LISTENER DELETE MORE INFO V","uncheck: "+MyListFragmentListeners.lastCheckedV);
        	TabsActivity.datasource.open();
            TabsActivity.datasource.deleteVerified(verified);
            MyList_Delete_Task  myTask_delete = new MyList_Delete_Task(MyListFragment.list_activity);
            myTask_delete.execute(new String[] {Main.username,verified.getLatitude().toString(),verified.getLongitude().toString()});
           
            MyListFragment.adapterV.remove(verified);
            MyListFragment.adapterV.notifyDataSetChanged();
            MoreInfo.adapterV.notifyDataSetChanged();
    
		 	}
		 	else
		 	{
		 		Toast toast =Toast.makeText(MoreInfo.moreinfoactivity,"You have nothing to delete" ,Toast.LENGTH_SHORT);
		 		toast.show();
		 	}     
    
		 }
  });
 }
}