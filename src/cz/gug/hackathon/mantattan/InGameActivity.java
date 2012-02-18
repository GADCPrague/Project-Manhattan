package cz.gug.hackathon.mantattan;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;

public class InGameActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	    setContentView(R.layout.ingame);
		
		
		 final View touchView = findViewById(R.id.ingame_main);
		    touchView.setOnTouchListener(new View.OnTouchListener() {
		        
		        public boolean onTouch(View v, MotionEvent event) {
		           System.out.println("Touch coordinates : " +
		                String.valueOf(event.getX()) + "x" + String.valueOf(event.getY()));
		                return true;
		        }
			
		    });
		   
		    
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	
	
}
