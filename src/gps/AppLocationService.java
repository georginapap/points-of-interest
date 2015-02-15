package gps;

import com.google.android.gms.location.LocationRequest;

import activities.SearchFragment;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class AppLocationService extends Service implements LocationListener {
		public LocationManager locationManager;
		public Location location;
		Context ctx;
		LocationRequest mLocationRequest;
		 private static final int MIN_DISTANCE_FOR_UPDATE=10;
		 private static final long MIN_TIME_FOR_UPDATE = 1000 * 60 * 2;
	
        
	    
	    public AppLocationService(Context context) {
		    locationManager = (LocationManager)context.getSystemService(LOCATION_SERVICE);
		    ctx=context;
	  }
	  
	    public Location getLocation(String provider) {
	        if (locationManager.isProviderEnabled(provider)) {
	          locationManager.requestLocationUpdates(provider,MIN_TIME_FOR_UPDATE, MIN_DISTANCE_FOR_UPDATE, this);
	          if (locationManager != null) {
	            location = locationManager.getLastKnownLocation(provider);
	            return location;
	          }
	        }
	        return null;
	      }
	@Override
	public void onLocationChanged(Location location) {
		if(location != null){
	        SearchFragment.latitude = location.getLatitude();
	        SearchFragment.longitude=location.getLongitude();
	        String msg = "Updated Location: " + SearchFragment.latitude+ SearchFragment.longitude;
	        Toast.makeText(ctx, msg, Toast.LENGTH_SHORT).show();
	        Log.i("PRINT",msg);
	}
	}
	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onProviderEnabled(String provider) {
		Toast.makeText(ctx, "Enabled new provider " + provider,Toast.LENGTH_SHORT).show();
		 Log.i("PRINT", "Enabled new provider " + provider);
	}
	@Override
	public void onProviderDisabled(String provider) {
		 Toast.makeText(ctx, "Disabled provider " + provider,Toast.LENGTH_SHORT).show();
		Log.i("PRINT", "Disabled provider " + provider);
	}
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	 
public void stopUsingGPS(){
    if(locationManager != null){
        locationManager.removeUpdates(AppLocationService .this);
    }       
}





  
}