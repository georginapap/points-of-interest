package database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

public class DataSource {
	// Database fields
	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;
	private String[] allColumns = { MySQLiteHelper.COLUMN_id,MySQLiteHelper.COLUMN_latitude,
			MySQLiteHelper.COLUMN_longitude, MySQLiteHelper.COLUMN_type,MySQLiteHelper.COLUMN_name };

	public DataSource(Context context) {
		dbHelper = new MySQLiteHelper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}



	public Verified createVerified(Double x, Double y, String type, String name) {
		ContentValues values = new ContentValues();
		values.put(MySQLiteHelper.COLUMN_latitude, x);
		values.put(MySQLiteHelper.COLUMN_longitude,y);
		values.put(MySQLiteHelper.COLUMN_type, type);
		values.put(MySQLiteHelper.COLUMN_name, name);
		Cursor cursor=null;
		Verified newVerified = null;
		try{
			long insertId = database.insert(MySQLiteHelper.TABLE_NAME_Verified, null,
					values);
			cursor = database.query(MySQLiteHelper.TABLE_NAME_Verified,
					allColumns, MySQLiteHelper.COLUMN_id + " = " + insertId, null,null, null, null);
			cursor.moveToFirst();
			newVerified = cursorToVerified(cursor);
		}
		catch(SQLiteException e){
			Log.e("DATASOURCE","ERROR ON INSERT\n"+e.toString());
		}finally{
			if(cursor!=null)
				cursor.close();	
		}

		return newVerified;
	}

	public void deleteVerified(Verified verified) {
		long id = verified.getId();
		Log.i("DATASOURCE","Comment deleted with id: " + id);
		try{
			database.delete(MySQLiteHelper.TABLE_NAME_Verified, MySQLiteHelper.COLUMN_id
					+ " = " + id, null);
		}catch(SQLiteException e) {
			Log.e("DATASOURCE","ERROR ON DELETE\n"+e.toString());
		}
	}

	public List<Verified> getAllVerified() {
		List<Verified> listverified = new ArrayList<Verified>();
		Cursor cursor = null;
		try{
			cursor = database.query(MySQLiteHelper.TABLE_NAME_Verified,
					allColumns, null, null, null, null, null);
			cursor.moveToFirst();
			while (!cursor.isAfterLast()) {
				Verified verified = cursorToVerified(cursor);
				listverified.add(verified);
				cursor.moveToNext();
			}
		}catch(SQLiteException e){
			Log.e("DATASOURCE","Error at getAllVerified \n"+e.toString());
		}finally{
			if(cursor!=null)
				cursor.close();
		}
		return listverified;
	}

	private Verified cursorToVerified(Cursor cursor) {
		Verified verified = new Verified();
		verified.setId(cursor.getInt(0));
		verified.setLatitude(cursor.getDouble(1));
		verified.setLongitude(cursor.getDouble(2));
		verified.setType(cursor.getString(3));
		verified.setName(cursor.getString(4));
		Log.i("DATASOURCE",verified.toString());
		return verified;
	}

	public boolean findVerified(Double x , Double y) {
		List<Verified> listverified = new ArrayList<Verified>();
		Cursor cursor = null;
		String where = "("+ MySQLiteHelper.COLUMN_latitude + "= ?) AND (" + MySQLiteHelper.COLUMN_longitude +" = ?)";
		String[] args = new String[]{x.toString(),y.toString()};
		try{
			cursor = database.query(MySQLiteHelper.TABLE_NAME_Verified,allColumns, where, args, null,null, null, null);
			cursor.moveToFirst();
			while (!cursor.isAfterLast()) {
				Verified verified = cursorToVerified(cursor);
				listverified.add(verified);
				cursor.moveToNext();
			}
		}catch(SQLiteException e){
			Log.e("DATASOURCE","Error at findVerified\n"+e.toString());
		}finally{
			if(cursor!=null)
				cursor.close();
		}
		return listverified.isEmpty();
	}

	public Pending createPending(Double x, Double y, String type, String name) {
		ContentValues values = new ContentValues();
		values.put(MySQLiteHelper.COLUMN_latitude, x);
		values.put(MySQLiteHelper.COLUMN_longitude,y);
		values.put(MySQLiteHelper.COLUMN_type, type);
		values.put(MySQLiteHelper.COLUMN_name, name);
		Cursor cursor=null;
		Pending newPending = null;
		try{
			long insertId = database.insert(MySQLiteHelper.TABLE_NAME_Pending, null,
					values);
			cursor = database.query(MySQLiteHelper.TABLE_NAME_Pending,allColumns, MySQLiteHelper.COLUMN_id + " = " + insertId, null,null, null, null);
			cursor.moveToFirst();
			newPending = cursorToPending(cursor);
			cursor.close();
		}
		catch(SQLiteException e){
			Log.e("DATASOURCE","ERROR ON INSERT\n"+e.toString());
		}finally{
			if(cursor!=null)
				cursor.close(); 
		}     
		return newPending;
	}

	public void deletePending(Pending pending) {
		long id = pending.getId();
		Log.i("DATASOURCE","Comment deleted with id: " + id);
		try{
			database.delete(MySQLiteHelper.TABLE_NAME_Pending, MySQLiteHelper.COLUMN_id
					+ " = " + id, null);
		}catch(SQLiteException e) {
			Log.e("DATASOURCE","ERROR ON DELETE\n"+e.toString());
		}
	}

	public List<Pending> getAllPending() {
		List<Pending> listpending = new ArrayList<Pending>();
		Cursor cursor = null;
		try{
			cursor = database.query(MySQLiteHelper.TABLE_NAME_Pending,
					allColumns, null, null, null, null, null);
			cursor.moveToFirst();
			while (!cursor.isAfterLast()) {
				Pending pending = cursorToPending(cursor);
				listpending.add(pending);
				cursor.moveToNext();
			}
		}catch(SQLiteException e){
			Log.e("DATASOURCE","Error at getAllPending \n"+e.toString());
		}finally{
			if(cursor!=null)
				cursor.close();
		}
		return listpending;
	}

	private Pending cursorToPending(Cursor cursor) {
		Pending pending = new Pending();
		pending.setId(cursor.getLong(0));
		pending.setLatitude(cursor.getDouble(1));
		pending.setLongitude(cursor.getDouble(2));
		pending.setType(cursor.getString(3));
		pending.setName(cursor.getString(4));
		Log.i("DATASOURCE",pending.toString());
		return pending;
	}

	public boolean findPending(Double x , Double y) {
		List<Pending> listpending = new ArrayList<Pending>();
		Cursor cursor = null;
		String where = "("+ MySQLiteHelper.COLUMN_latitude + "= ?) AND (" + MySQLiteHelper.COLUMN_longitude +" = ?)";
		String[] args = new String[]{x.toString(),y.toString()};
		try{
			cursor = database.query(MySQLiteHelper.TABLE_NAME_Pending,allColumns, where, args, null,null, null, null);
			cursor.moveToFirst();
			while (!cursor.isAfterLast()) {
				Pending pending = cursorToPending(cursor);
				listpending.add(pending);
				cursor.moveToNext();
			}
		}catch(SQLiteException e){
			Log.e("DATASOURCE","ERROR ON FINDVERIED\n"+e.toString());
		}finally{
			if(cursor!=null)
				cursor.close();
		}

		return listpending.isEmpty();

	}

}