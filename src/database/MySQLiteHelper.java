package database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {
	
	public static final String DATABASE_NAME = "POIS";
	public static final String TABLE_NAME_Pending = "Pending";
 	public static final String TABLE_NAME_Verified = "Verified";
 	public static final String COLUMN_id="id";
	public static final String COLUMN_latitude="latitude";
 	public static final String COLUMN_longitude="longitude";
 	public static final String COLUMN_type="type";
 	public static final String COLUMN_name="name";
 	public static final String TABLE_CREATE_P= " CREATE TABLE IF NOT EXISTS "  + TABLE_NAME_Pending + "(" + 
 								COLUMN_id+ " integer primary key autoincrement , "+
 								COLUMN_latitude + " double not null, " + 
 								COLUMN_longitude + " double not null, "+ 
 								COLUMN_type + " text, "+ 
 								COLUMN_name + " text , "+
 								"UNIQUE ("+ COLUMN_id +" , " +COLUMN_latitude+" , "+COLUMN_longitude+"));";
	public static final String TABLE_CREATE_V= " CREATE TABLE IF NOT EXISTS "  + TABLE_NAME_Verified + "(" + 
				COLUMN_id+ " integer primary key autoincrement , "+
				COLUMN_latitude + " double not null, " + 
				COLUMN_longitude + " double not null, "+ 
				COLUMN_type + " text, "+ 
				COLUMN_name + " text , "+
				"UNIQUE ("+ COLUMN_id +" , " +COLUMN_latitude+" , "+COLUMN_longitude+"));";
	private static final int DATABASE_VERSION = 1;

  
  //constructor 
  public MySQLiteHelper(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }
  
  @Override
  public void onCreate(SQLiteDatabase database) {
	  try{
	  database.execSQL(TABLE_CREATE_P);
	  database.execSQL(TABLE_CREATE_V);
	  Log.i("DATASOURCE","TABLES CREATED!");
	  }catch(SQLiteException e){
		  Log.e("DATASOURCE","Error while creating tables!"+e.toString());
		  Log.d("DATASOURCE","PENDING: "+TABLE_CREATE_P);
		  Log.d("DATASOURCE","VERIFIED: "+TABLE_CREATE_V);
		
	  }
  }

  @Override	
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    Log.w(MySQLiteHelper.class.getName(),
        "Upgrading database from version " + oldVersion + " to "
            + newVersion + ", which will destroy all old data");
    db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_Pending);
    db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_Verified);
    onCreate(db);
  }

} 