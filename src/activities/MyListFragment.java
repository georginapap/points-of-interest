package activities;


import java.util.List;

import listeners.MyListFragmentListeners;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.firstapp.myfirstapp.R;

import database.Pending;
import database.Verified;

public class MyListFragment extends Fragment {  


 public static ListView mylistviewV,mylistviewP;
 public static List<Verified> valuesV;
 public static List<Pending> valuesP;
 public static ArrayAdapter<Verified> adapterV;
 public static ArrayAdapter<Pending> adapterP;
 public static FragmentActivity list_activity;
 public static Button buttonview,buttondelete;
 public static boolean checkedV;
 public static boolean checkedP;
 public static int positioncheckedV;
 public static int positioncheckedP;
 public static String textcheckedV;
 public static String textcheckedP;

 @Override
 public View onCreateView(LayoutInflater inflater, ViewGroup container,
   Bundle savedInstanceState) {
  
  View rootView = inflater.inflate(R.layout.fragment_mylist, container, false);
  list_activity=getActivity();
  mylistviewV = (ListView) rootView.findViewById(R.id.mylistviewV);  
  mylistviewP = (ListView) rootView.findViewById(R.id.mylistviewP);   
  buttonview= (Button) rootView.findViewById(R.id.buttonview);
  buttondelete= (Button) rootView.findViewById(R.id.buttondelete);
  checkedV=false;
  checkedP=false;
  positioncheckedV=-1;
  positioncheckedP=-1;
  MyListFragmentListeners.addAllListListeners();
  //verified
  valuesV = TabsActivity.datasource.getAllVerified();
  adapterV = new ArrayAdapter<Verified>(getActivity().getApplicationContext(),android.R.layout.simple_list_item_single_choice, valuesV);
  Log.i("DATASOURCE","VERIFIED TABLE:\n"+valuesV.toString());
  if(valuesV.toString()==""){
   Log.i("PRINT","NOTHING IN VERIFIED TABLE ");
  }
  mylistviewV.setAdapter(adapterV);
  //pending
  valuesP = TabsActivity.datasource.getAllPending();
  adapterP = new ArrayAdapter<Pending>(getActivity().getApplicationContext(),android.R.layout.simple_list_item_single_choice, valuesP);
  Log.i("DATASOURCE","PENDING TABLE:\n"+valuesP.toString());
  if(valuesP==null){
   Log.i("PRINT","NOTHING IN PENDINGTABLE ");
   //adapterV.add();
  }  
  mylistviewP.setAdapter(adapterP);
 
  return rootView;
 }
 


 //SYNARTHSEIS GIA DEDOMENA lISTvIEW_V
 public static void changeListviewVChecked( ) {
  if(checkedV==true)
   checkedV=false;
  else if(checkedV==false)
   checkedV=true;
 }

 public static boolean isListViewVChecked( ) {
  return checkedV;
 }

 public static void setpositionCheckedV(int i) {
  positioncheckedV=i;
 }

 public static int positionCheckedV() {
  return positioncheckedV;
 }
 
 public static void setSelectedTextV(String s){
  textcheckedV=s;
 }
 
 public static String getSelectedTextV(){
  return textcheckedV;
 }

 //SYNARTHSEIS GIA DEDOMENA lISTVIEW_P
 public static void changeListviewPChecked( ) {
  if(checkedP==true)
   checkedP=false;
  else if(checkedP==false)
   checkedP=true;
 }
 
 public static boolean isListViewPChecked( ) {
  return checkedP;
 }

 public static void setpositionCheckedP(int i) {
  positioncheckedP=i;
 }

 public static int positionCheckedP() {
  return positioncheckedP;
 }
 
 public static void setSelectedTextP(String s){
  textcheckedP=s;
 }
 
 public static String getSelectedTextP(){
  return textcheckedP;
 }
}