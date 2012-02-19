package cz.gug.hackathon.mantattan;

import java.io.IOException;

import android.app.Service;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.IBinder;
import android.util.Log;

public class MusicService extends Service implements OnPreparedListener {

	static final String TAG = "MusicService";

	private WeedCrusherApp weedCrusherApp;
	AssetManager assetManager;
	MediaPlayer mediaPlayer;
	private boolean playerOK = false;
	private boolean runFlag = false;
	SQLiteDatabase db;

	@Override
	public void onCreate() {
		super.onCreate();

		weedCrusherApp = (WeedCrusherApp) getApplication();
		assetManager = getApplicationContext().getAssets();

		mediaPlayer = new MediaPlayer();
		AssetFileDescriptor descriptor;
		try {			
			descriptor = assetManager.openFd("theme.ogg");
			mediaPlayer.setDataSource(descriptor.getFileDescriptor(),
					descriptor.getStartOffset(), descriptor.getLength());
			mediaPlayer.setOnPreparedListener(this);
			mediaPlayer.prepareAsync();
			mediaPlayer.setLooping(true);
			
		} catch (IOException e) {
			Log.d(TAG, "Unable to open audio file.");
			e.printStackTrace();
		} catch (IllegalStateException e) {
			Log.d(TAG, "Unable to start audio playback.");
			e.printStackTrace();
		}

		Log.d(TAG, "onCreated");
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		super.onStartCommand(intent, flags, startId);

		this.runFlag = true;

		if (playerOK) {
			if (!(mediaPlayer.isPlaying())) {
				mediaPlayer.start();
			}
			this.weedCrusherApp.setMusicServiceRunning(runFlag);
		}

		Log.d(TAG, "onStarted");
		return START_STICKY;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();

		this.runFlag = false;
		if (playerOK) {
			if (mediaPlayer.isPlaying()) {
				mediaPlayer.stop();
				mediaPlayer.release();
			}
			this.weedCrusherApp.setMusicServiceRunning(runFlag);
		}

		Log.d(TAG, "onDestroyed");
	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public void onPrepared(MediaPlayer mp) {
		playerOK = true;		
	}

}
