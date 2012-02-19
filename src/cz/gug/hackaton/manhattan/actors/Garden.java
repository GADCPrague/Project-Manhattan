package cz.gug.hackaton.manhattan.actors;

import java.util.Iterator;

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
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;
import aurelienribon.tweenengine.equations.Bounce;
import aurelienribon.tweenengine.equations.Back;

public class Garden extends View {
	
	
	public static int XRES = 5;
	public static int YRES = 6;
	
	Flower[][] garden = new Flower[XRES][YRES];
	
	TweenManager manager = new TweenManager();
	
	private Handler handler = new Handler();
	
	private float viewWidth;
	private float viewHeight;
	private boolean isRunning = false;
	
	private Thread thread = null;
	
	
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
		
		if (garden != null) {
			for (int u = 0; u < XRES; u++) {
				for (int v = 0; v < YRES; v++) {
					if (garden[u][v].wasCrushed(x, y)) {
						System.out.println("tapped u:" + u +  ", v:" + v);
						
						manager.killTarget(garden[u][v]);
						garden[u][v].setCrush(0f);
						
						
						Tween.to(garden[u][v], PlantHolderAccessor.CRUSH, 500)
					    .target(1)
					    .ease(Bounce.INOUT)
					    .delay(100)
					    .repeatYoyo(1, 500)
					    .start(manager);
					}
				}
			}
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
		
		float xwidth = viewWidth/XRES;
		float xheight = xwidth; 
		
		float yOffset = viewHeight - xheight*YRES;
		
		for (int u = 0; u < XRES; u++) {
			for (int v = 0; v < YRES; v++) {
				garden[u][v] = new Flower(u*xwidth, v*xheight+yOffset, xwidth, xheight, null, null, null, null, null, null);
			}
		}
	}

	
	protected void onDraw(Canvas canvas) {
	
		if (garden != null) {
			for (int u = 0; u < XRES; u++) {
				for (int v = 0; v < YRES; v++) {
					garden[u][v].render(canvas);
				}
			}
		}
	}
}
