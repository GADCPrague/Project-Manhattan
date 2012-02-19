package cz.gug.hackathon.mantattan;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

public class WeedCrusherApp extends Application {

	private static final String TAG = WeedCrusherApp.class.getSimpleName();

	private boolean musicServiceRunning;
	private DataTable dataTable;
	private int numRows, numCols;

	public boolean isMusicServiceRunning() {
		return musicServiceRunning;
	}

	public void setMusicServiceRunning(boolean musicServiceRunning) {
		this.musicServiceRunning = musicServiceRunning;
	}

	public DataTable getDataTable() {
		return dataTable;
	}

	@Override
	public void onCreate() { //
		super.onCreate();
		numRows = 5;
		numCols = 6;
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
