package cz.gug.hackathon.mantattan;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

public class HighscoresActivity extends Activity {
    
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.highscores);
        
    }

	@Override
	protected void onResume() {
		super.onResume();
		
		List<Player> highscoresList = WeedCrusherApp.highscoresList;
		
		TextView tv = (TextView) findViewById(R.id.player1);
		tv.setText("1. " + highscoresList.get(0).name + "....." + highscoresList.get(0).completedInTaps + "taps");
		
		tv = (TextView) findViewById(R.id.player2);
		tv.setText("2. " + highscoresList.get(1).name + "....." + highscoresList.get(1).completedInTaps + "taps");
		
		tv = (TextView) findViewById(R.id.player3);
		tv.setText("3. " + highscoresList.get(2).name + "....." + highscoresList.get(2).completedInTaps + "taps");
		
		tv = (TextView) findViewById(R.id.player4);
		tv.setText("4. " + highscoresList.get(3).name + "....." + highscoresList.get(3).completedInTaps + "taps");
		
		tv = (TextView) findViewById(R.id.player5);
		tv.setText("5. " + highscoresList.get(4).name + "....." + highscoresList.get(4).completedInTaps + "taps");
		
	}
}