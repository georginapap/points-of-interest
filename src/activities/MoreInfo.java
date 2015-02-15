package activities;

import listeners.MoreInfoListeners;

import com.firstapp.myfirstapp.R;

import database.Pending;
import database.Verified;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MoreInfo extends Activity{
 public static Button buttondeleteInfoP;
 public static Button buttondeleteInfoV;
 public static ListView infoV,infoP;
 public Verified verified;
 public Pending pending;
 public static ArrayAdapter<String> adapterP;
 public static ArrayAdapter<String> adapterV;
 public static String[] itemVnull = { "Verified:"," - "," - "," - "," - "," - "};
 public static String[] itemPnull = { "Pending:"," - "," - "," - "," - "," - "};  
 public static Activity moreinfoactivity;
 @Override
  protected void onCreate(Bundle savedInstanceState) {
  
  super.onCreate(savedInstanceState);
  setContentView(R.layout.activity_moreinfo);
  buttondeleteInfoP = (Button) findViewById(R.id.buttondeleteInfoP);
  buttondeleteInfoV = (Button) findViewById(R.id.buttondeleteInfoV);
  infoP=(ListView)findViewById(R.id.infoP);
  infoV=(ListView)findViewById(R.id.infoV);
  moreinfoactivity=this;
  if(MyListFragment.positionCheckedV()==-1){  
   adapterV = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, itemVnull);
  }else{
   verified = (Verified) MyListFragment.mylistviewV.getAdapter().getItem(MyListFragment.positionCheckedV());
   String[] itemV = { "Verified:", "Id Code:   "+verified.getId(),"Name:      " +verified.getName(), "Type:      "+verified.getType(), "Latitude:  "+verified.getLatitude(), "Longitude: "+verified.getLongitude() };
   adapterV = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, itemV);
  }
  
  if(MyListFragment.positionCheckedP()==-1){   
   adapterP = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, itemPnull);
  }else{
   pending = (Pending) MyListFragment.mylistviewP.getAdapter().getItem(MyListFragment.positionCheckedP());
   String[] itemP = { "Pending:", "Id Code:   "+pending.getId(),"Name:      " +pending.getName(), "Type:      "+pending.getType(), "Latitude:  "+pending.getLatitude(), "Longitude: "+pending.getLongitude() };
   adapterP = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, itemP);
  }
 
  infoV.setAdapter(adapterV);
  infoP.setAdapter(adapterP);
  MoreInfoListeners.addMoreInfoListeners();
 } 
 @Override
 public void onBackPressed() {
  Intent intentB = new Intent(this,TabsActivity.class);
        startActivity(intentB);
 }

 @Override
 protected void onDestroy() {
  super.onDestroy();
 }
}