package cz.gug.hackathon.mantattan;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class HighscoresActivity extends Activity {
    
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.highscores);
        
    }
}