package listeners;



import activities.Main;
import activities.SearchFragment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import asynctasks.MyTask_search_mine;
import asynctasks.MyTask_search_others;
import asynctasks.MyTask_search_all;


public class SearchFragmentListeners {


	private static void addListenerOn_button_my(){
		SearchFragment.button_search_my.setOnClickListener(new OnClickListener() {
		@Override
		  public void onClick(View v) {
				Log.i("PRINT","LISTENER button_my");
				getAllchoiches();	
				
				MyTask_search_mine myTask_search_mine = new MyTask_search_mine(SearchFragment.search_activity);
				myTask_search_mine.execute();//SearchFragment.getUserpsw(), poi});
			}
		});

	}
	
	
	private static void addListenerOn_button_other(){
		SearchFragment.button_search_other.setOnClickListener(new OnClickListener() {
		@Override
		  public void onClick(View v) {
				Log.i("PRINT","LISTENER button_other");
				getAllchoiches();
				String poi;
				SearchFragment.setUserpsw(Main.username+"#"+Main.password);
				try
				{
				poi=SearchFragment.latitude.toString()+"#"+SearchFragment.longitude.toString();
				MyTask_search_others myTask_search_others = new MyTask_search_others(SearchFragment.search_activity);
				myTask_search_others.execute(new String[] {SearchFragment.getUserpsw(), poi});
				}
				catch(NullPointerException e)
				{
					
				}
				
				
		  }
		});
	}
	
	private static void addListenerOn_button_all(){
		SearchFragment.button_search_all.setOnClickListener(new OnClickListener() {
		@Override
		  public void onClick(View v) {
				Log.i("PRINT","LISTENER button_all");
				getAllchoiches();
				String poi;
				SearchFragment.setUserpsw(Main.username+"#"+Main.password);
				try
				{
					poi=SearchFragment.latitude.toString()+"#"+SearchFragment.longitude.toString();
					MyTask_search_all myTask_search_all= new MyTask_search_all(SearchFragment.search_activity);
					myTask_search_all.execute(new String[] {SearchFragment.getUserpsw(), poi});
				}
				catch(NullPointerException e)
				{
					
				}
		  }
		});
	}

	
	
	private static void addListenerOn_checkbox_other(){
		SearchFragment.checkBox_other.setOnClickListener(new OnClickListener() {
			@Override
		  public void onClick(View v) {
			if (((CheckBox) v).isChecked()) {
				Log.i("PRINT","1 IS CHECKED");
			}
			}
		});
	}

	private static void addListenerOn_checkbox_entertainment(){
		SearchFragment.checkBox_entertainment.setOnClickListener(new OnClickListener() {
				@Override
		public void onClick(View v) {
				if (((CheckBox) v).isChecked()) {
					Log.i("PRINT","2 IS CHECKED");
				}
				
			}
		});
	}
	private static void addListenerOn_checkbox_drink(){
		SearchFragment.checkBox_drink.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (((CheckBox) v).isChecked()) {
					Log.i("PRINT","3 IS CHECKED");
				}
				
			}
		});
	
	}
	private static  void addListenerOn_checkbox_cinema(){
		SearchFragment.checkBox_cinema.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (((CheckBox) v).isChecked()) {
					Log.i("PRINT","4 IS CHECKED");
				}
				
			}
		});
	
	}
	private static void addListenerOn_checkbox_seeing(){
		SearchFragment.checkBox_seeing.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (((CheckBox) v).isChecked()) {
					Log.i("PRINT","5 IS CHECKED");
				}
				
			}
		});
	
	}
	private static void addListenerOn_checkbox_education(){
		SearchFragment.checkBox_education.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (((CheckBox) v).isChecked()) {
					Log.i("PRINT","6 IS CHECKED");
				}
				
			}
		});
	
	}
	private static void addListenerOn_checkbox_library(){
		SearchFragment.checkBox_library.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (((CheckBox) v).isChecked()) {
					Log.i("PRINT","7 IS CHECKED");
				}
				
			}
		});
	
	}
	private static  void addListenerOn_checkbox_university(){
		SearchFragment.checkBox_university.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (((CheckBox) v).isChecked()) {
					Log.i("PRINT","8 IS CHECKED");
				}
				
			}
		});
	
	}
	private static void addListenerOn_checkbox_food(){
		SearchFragment.checkBox_food.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (((CheckBox) v).isChecked()) {
					Log.i("PRINT","9 IS CHECKED");
				}
				
			}
		});
	
	}
	private static void addListenerOn_checkbox_fastfood(){
		SearchFragment.checkBox_fastfood.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (((CheckBox) v).isChecked()) {
					Log.i("PRINT","10 IS CHECKED");
				}
				
			}
		});
	
	}
	private static void addListenerOn_checkbox_takeaway(){
		SearchFragment.checkBox_takeaway.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (((CheckBox) v).isChecked()) {
					Log.i("PRINT","11 IS CHECKED");
				}
				
			}
		});
	
	}
	private static void addListenerOn_checkbox_typical(){
		SearchFragment.checkBox_typical.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (((CheckBox) v).isChecked()) {
					Log.i("PRINT","12 IS CHECKED");
				}
				
			}
		});
	
	}
	
	
	public static void addAllSearchListeners(){
		//listeners for buttons
		addListenerOn_button_my();
		addListenerOn_button_other();
		addListenerOn_button_all();
		//listeners for checkboxes
		addListenerOn_checkbox_other();
		addListenerOn_checkbox_entertainment();
		addListenerOn_checkbox_drink();
		addListenerOn_checkbox_cinema();
		addListenerOn_checkbox_seeing();
		addListenerOn_checkbox_education();
		addListenerOn_checkbox_library();
		addListenerOn_checkbox_university();
		addListenerOn_checkbox_food();
		addListenerOn_checkbox_fastfood();
		addListenerOn_checkbox_takeaway();
		addListenerOn_checkbox_typical();
	}
	
	
	private static void getAllchoiches(){
		Log.i("PRINT","Latitude: "+SearchFragment.latitude+" Longitude:"+SearchFragment.longitude);	 
		if(!(SearchFragment.checklist.isEmpty())){
			SearchFragment.checklist.clear();
			Log.i("PRINT","h lista adeiase");
		}
		if(SearchFragment.checklist.isEmpty()){								
			if (SearchFragment.checkBox_other.isChecked()){
				SearchFragment.checklist.add("Other"); 
				SearchFragment.checkBox_other.toggle();
			}
			if (SearchFragment.checkBox_entertainment.isChecked()){
				SearchFragment.checklist.add("Entertainment"); 
				SearchFragment.checkBox_entertainment.toggle();
			}
			if (SearchFragment.checkBox_drink.isChecked()){
				SearchFragment.checklist.add("Drink"); 
				SearchFragment.checkBox_drink.toggle();
			}
			if (SearchFragment.checkBox_cinema.isChecked()){
				SearchFragment.checklist.add("Cinema"); 
				SearchFragment.checkBox_cinema.toggle();
			}
			if (SearchFragment.checkBox_seeing.isChecked()){
				SearchFragment.checklist.add("Site seeing"); 
				SearchFragment.checkBox_seeing.toggle();
			}
			if (SearchFragment.checkBox_education.isChecked()){
				SearchFragment.checklist.add("Education"); 
				SearchFragment.checkBox_education.toggle();
			}
			if (SearchFragment.checkBox_library.isChecked()){
				SearchFragment.checklist.add("Library"); 
				SearchFragment.checkBox_library.toggle();
			}
			if (SearchFragment.checkBox_university.isChecked()){
				SearchFragment.checklist.add("University"); 
				SearchFragment.checkBox_university.toggle();
			}
			if (SearchFragment.checkBox_food.isChecked()){ 
				SearchFragment.checklist.add("Food"); 
				SearchFragment.checkBox_food.toggle();
			}
			if (SearchFragment.checkBox_fastfood.isChecked()){ 
				SearchFragment.checklist.add("Fast food Restaurant"); 
				SearchFragment.checkBox_fastfood.toggle();
			}
			if (SearchFragment.checkBox_takeaway.isChecked()){ 
				SearchFragment.checklist.add("Take away Restaurant"); 
				SearchFragment.checkBox_takeaway.toggle();
			}
			if (SearchFragment.checkBox_typical.isChecked()) {
				SearchFragment.checklist.add("Typical Restaurant"); 
				SearchFragment.checkBox_typical.toggle();
			}
			Log.i("PRINT","O XRHSTHS EPELEKSE: \n");
			for (int i=0; i<SearchFragment.checklist.size();i++ ) {
				Log.i("PRINT",SearchFragment.checklist.get(i));
			}
			//SearchFragment.showChoices();
		}
		
	}
	
};
