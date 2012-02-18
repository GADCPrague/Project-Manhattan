package cz.gug.hackaton.manhattan.actors;

import cz.gug.hackathon.mantattan.R;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;

public class Garden extends View {
	
	
	public static int XRES = 5;
	public static int YRES = 6;
	
	Flower[][] garden = new Flower[XRES][YRES];
	
	private float viewWidth;
	private float viewHeight;
	
	
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
					}
				}
			}
		}		
	}
	
	
	private void initFlowers() {
	

		 final View touchView = findViewById(R.id.ingame_main);
		    touchView.setOnTouchListener(new View.OnTouchListener() {
		        
		        public boolean onTouch(View v, MotionEvent event) {
		           System.out.println("Touch coordinates : " +
		                String.valueOf(event.getX()) + "x" + String.valueOf(event.getY()));
		           
		                checkTap(event.getX(),event.getY());
		           
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
