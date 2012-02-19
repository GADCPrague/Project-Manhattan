package cz.gug.hackaton.manhattan.actors;

import java.util.Iterator;

import cz.gug.hackathon.mantattan.DataTable;
import cz.gug.hackathon.mantattan.R;
import cz.gug.hackaton.manhattan.actors.accessors.PlantHolderAccessor;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;
import aurelienribon.tweenengine.TweenCallback.EventType;
import aurelienribon.tweenengine.equations.Bounce;
import aurelienribon.tweenengine.equations.Back;

public class Garden extends View {
	
	
	public static int XRES = 5;
	public static int YRES = 6;
	
	Flower[][][] garden = new Flower[XRES][YRES][2];  // two states per cell
	
	
	TweenManager manager = new TweenManager();
	
	private Handler handler = new Handler();
	
	private float viewWidth;
	private float viewHeight;
	private boolean isRunning = false;
	
	private boolean tapLock = false;
	
	private Thread thread = null;
	
	private DataTable table;
	
	
	Runnable mUpdate = new Runnable() {
		  private long lastMillis = System.currentTimeMillis();

		  public void run() {
		  
			int deltaMillis = (int) (System.currentTimeMillis() - lastMillis );
			manager.update(deltaMillis);
			lastMillis = System.currentTimeMillis();
			
		    Garden.this.invalidate();
		    if (isRunning) {
		    	handler.postDelayed(mUpdate, 10);
		    }
		  }
		};
	
	
	public Garden(Context context) {
		super(context);
		initFlowers();
	}

	public Garden(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initFlowers();
	}

	public Garden(Context context, AttributeSet attrs) {
		super(context, attrs);
		initFlowers();
	}
	
	
	
	private void checkTap(float x,float y) {
		
		if (garden != null ) {
			
			for (int u = 0; u < XRES; u++) {
				for (int v = 0; v < YRES; v++) {
					
					if (garden[u][v][0].wasCrushed(x, y)) {
						System.out.println("tapped u:" + u +  ", v:" + v);
						handleTap(u, v);
					}
				
					/*
					if (0 == 0) {
						if (garden[u][v][0].wasCrushed(x, y)) {
							System.out.println("tapped u:" + u +  ", v:" + v);
						
							manager.killTarget(garden[u][v][0]);
							garden[u][v][0].setCrush(0f);
						
							final int tx = u;
							final int ty = v;
							TweenCallback delayedTap = new TweenCallback() {
								
								public void onEvent(EventType arg0, BaseTween arg1) {
									
									if (table != null) {
										table.tap(ty, tx);
									}
									
									manager.killTarget(garden[tx][ty][1]);
									garden[tx][ty][1].setCrush(1f);
								
								
									Tween.to(garden[tx][ty][1], PlantHolderAccessor.CRUSH, 500)
									.target(0)
									.ease(Bounce.INOUT)
									.delay(100)
									.start(manager);
									
								}
							};
						
							Tween.to(garden[u][v][0], PlantHolderAccessor.CRUSH, 500)
							.target(1)
							.ease(Bounce.INOUT)
							.delay(100)
							.call(delayedTap)
							.start(manager);
						}
					}
					
					if (1 == 1) {
						if (garden[u][v][1].wasCrushed(x, y)) {
							System.out.println("tapped u:" + u +  ", v:" + v);
						
							manager.killTarget(garden[u][v][1]);
							garden[u][v][1].setCrush(0f);
						
						
							Tween.to(garden[u][v][1], PlantHolderAccessor.CRUSH, 500)
							.target(1)
							.ease(Bounce.INOUT)
							.delay(100)
							.repeatYoyo(1, 500)
							.start(manager);
						}
					}
					*/
				}
			}
		}		
	}
	
	
	private void handleTap(int u,int v) {
		
		// save previous state
		// process turn
		// compere new and previous state
		// fire animations
		
		System.out.println("Garden.handleTap(" + u + "," + v + ")");
		
		if (!tapLock && table != null) {
		//	tapLock = true;

			int[][] oldState = new int[XRES][YRES];
			
			for (int i = 0; i < XRES; i++) 
				for (int j = 0; j < YRES; j++) {
					oldState[i][j] = table.getValues(i, j);
				}
			
			table.tap(v, u);
			
			int[][] newState = new int[XRES][YRES];
			
			for (int i = 0; i < XRES; i++) 
				for (int j = 0; j < YRES; j++) {
					newState[i][j] = table.getValues(i, j);
				}
			
			// -- compare states --
			
			int maxDelay = 0;
			
			for (int i = 0; i < XRES; i++) 
				for (int j = 0; j < YRES; j++) {

					if (newState[i][j] != oldState[i][j]) {
						
						System.out.println("difference at " + i + "," + j);
		
						//get distance to tap origin
						int dx = u-i;
						int dy = v-j;
						int l = (int)Math.sqrt(dx*dx+dy*dy);
						
						int delayFactor = l * 300; // compute animation start delay based on distance to tap origin
						
						if (maxDelay < delayFactor) maxDelay = delayFactor;
						
						// init animation
						
					}
				}
			
			// schedule tap unlock after last animation finished
			Tween.call(new TweenCallback() {
				public void onEvent(EventType arg0, BaseTween arg1) {
					Garden.this.tapLock = false;
				}
			}).delay(maxDelay+1000).start(manager);
			
		}
		
	}
	

	
	
	
	private void initFlowers() {
	
		Tween.registerAccessor(Flower.class, new PlantHolderAccessor());

		 final View touchView = findViewById(R.id.ingame_main);
		    touchView.setOnTouchListener(new View.OnTouchListener() {
		        
		        public boolean onTouch(View v, MotionEvent event) {
		        	if (event.getAction() == MotionEvent.ACTION_DOWN) {
		        		System.out.println("Touch coordinates : " +
		                String.valueOf(event.getX()) + "x" + String.valueOf(event.getY()));
		           
		                checkTap(event.getX(),event.getY());
		        	}
		           
		                return true;
		        }
			
		    });
		   
		
		ViewTreeObserver viewTreeObserver = this.getViewTreeObserver();
		if (viewTreeObserver.isAlive()) {
		  viewTreeObserver.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
		    public void onGlobalLayout() {
		      Garden.this.getViewTreeObserver().removeGlobalOnLayoutListener(this);
		      viewWidth = Garden.this.getWidth();
		      viewHeight = Garden.this.getHeight();
		      initGarden();
		    }
		  });
		}
	}
	
	private void initGarden() {
		
		thread = new Thread(mUpdate);
		thread.start();
		this.isRunning = true;
		
		if (table == null) {
			table = new DataTable(XRES,YRES);
			table.shuffle(5);
		}
		
		float xwidth = viewWidth/XRES;
		float xheight = xwidth; 
		
		float yOffset = viewHeight - xheight*YRES;
		
		for (int u = 0; u < XRES; u++) {
			for (int v = 0; v < YRES; v++) {
				garden[u][v][0] = new NicePlant(u*xwidth, v*xheight+yOffset, xwidth, xheight, null, null, null, null, null, null);
				garden[u][v][1] = new NastyPlant(u*xwidth, v*xheight+yOffset, xwidth, xheight, null, null, null, null, null, null);
			}
		}
	}

	
	protected void onDraw(Canvas canvas) {
	
		if (garden != null && table != null ) {
			for (int u = 0; u < XRES; u++) {
				for (int v = 0; v < YRES; v++) {
					
					int val = table.getValues(v, u);
					
					if (val == 0) {  // good plant 
						garden[u][v][0].render(canvas);
					}
					
					if (val == 1) {  // bad plant
						garden[u][v][1].render(canvas);
					}
					
				}
			}
		}
	}
}
