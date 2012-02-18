package cz.gug.hackathon.mantattan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
//import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	//private static final String TAG = MainActivity.class.getSimpleName();
	//private DataTable dataTable;
	    
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);

//        dataTable = new DataTable(3, 2);
//        Log.d(TAG, "====================================================");
//        dataTable.shuffle(0);
//        Log.d(TAG, "====================================================");
        
        final Button start = (Button) findViewById(R.id.start_new_button);
        start.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Toast toast = Toast.makeText(v.getContext(), "start", Toast.LENGTH_LONG);
            	toast.show();
            }
        });
        
        final Button about = (Button) findViewById(R.id.about_button);
        about.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Intent intent = new Intent(v.getContext(), AboutActivity.class);
            	startActivity(intent);
            }
        });
        

    }
}