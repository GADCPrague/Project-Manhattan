package cz.gug.hackathon.mantattan;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {
	
	private static final String TAG = MainActivity.class.getSimpleName();
	private DataTable dataTable;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
//        dataTable = new DataTable(3, 2);
//        Log.d(TAG, "====================================================");
//        dataTable.shuffle(0);
//        Log.d(TAG, "====================================================");
    }
}