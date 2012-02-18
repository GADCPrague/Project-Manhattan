package cz.gug.hackaton.manhattan.actors;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

public class Garden extends View {
	
	



	public static int XRES = 5;
	public static int YRES = 6;
	
	Flower[][] garden = new Flower[XRES][YRES];
	
	public Garden(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	

}
