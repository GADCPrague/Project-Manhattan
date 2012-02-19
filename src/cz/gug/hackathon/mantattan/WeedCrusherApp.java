package cz.gug.hackathon.mantattan;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

public class WeedCrusherApp extends Application {

	private static final String TAG = WeedCrusherApp.class.getSimpleName();
	public SharedPreferences prefs;
	private DataTable dataTable;
	private int numRows, numCols;
	public static int highscoreMaxPlayers = 5;
	public static List<Player> highscoresList = new ArrayList<Player>();
	

	public DataTable getDataTable() {
		return dataTable;
	}

	@Override
	public void onCreate() { //
		super.onCreate();
		numRows = 6;
		numCols = 5;
		
		dataTable = new DataTable(numRows, numCols);
		Log.d(TAG, "Before getSharedPrefs()");
		prefs = PreferenceManager.getDefaultSharedPreferences(this); 
		Log.d(TAG, "After getSharedPrefs()");
		Log.d(TAG, "===========================================");
		for (int i = 0; i < highscoreMaxPlayers; i++) {
			highscoresList.add(new Player(prefs.getString("player" + (i+1), "Marco Carola=====" + 7 * (i + 2) + "=====" + (60 * (10 - i)))));
			Log.d(TAG, highscoresList.get(i).toString());
		}	
		Collections.sort(highscoresList, new PlayerTapsComparable());
		Log.d(TAG, "===========================================");
		Log.d(TAG, "Sorted:");
		for (int i = 0; i < highscoreMaxPlayers; i++) {			
			Log.d(TAG, highscoresList.get(i).toString());
		}
		Log.d(TAG, "===========================================");
		Log.i(TAG, "onCreated");
	}

	@Override
	public void onTerminate() { //
		super.onTerminate();
		
		SharedPreferences.Editor editor = prefs.edit();
	    
		for (int i = 0; i < highscoreMaxPlayers; i++) {
			editor.putString("player" + (i + 1), highscoresList.get(i).toString());
		}

	    editor.commit();
		
		Log.i(TAG, "onTerminated");
	}	

}
