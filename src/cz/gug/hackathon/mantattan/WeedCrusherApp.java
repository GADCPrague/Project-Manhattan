package cz.gug.hackathon.mantattan;

import android.app.Application;
import android.util.Log;

public class WeedCrusherApp extends Application {

	private static final String TAG = WeedCrusherApp.class.getSimpleName();

	private DataTable dataTable;
	private int numRows, numCols;

	public DataTable getDataTable() {
		return dataTable;
	}

	@Override
	public void onCreate() { //
		super.onCreate();
		numRows = 6;
		numCols = 5;
//		this.prefs = PreferenceManager.getDefaultSharedPreferences(this);
//		this.prefs.registerOnSharedPreferenceChangeListener(this);
		dataTable = new DataTable(numRows, numCols);
		
		Log.i(TAG, "onCreated");
	}

	@Override
	public void onTerminate() { //
		super.onTerminate();
		
		Log.i(TAG, "onTerminated");
	}	

}
