package cz.gug.hackathon.mantattan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
    
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);
        
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