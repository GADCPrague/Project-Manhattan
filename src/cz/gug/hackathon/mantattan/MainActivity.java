package cz.gug.hackathon.mantattan;

import android.app.Activity;
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
        
        final Button button = (Button) findViewById(R.id.start_new_button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Toast toast = Toast.makeText(v.getContext(), "start", Toast.LENGTH_LONG);
            	toast.show();
            }
        });
        
    }
}