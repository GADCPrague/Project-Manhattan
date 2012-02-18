package cz.gug.hackaton.manhattan.actors;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.Rect;
import android.graphics.RectF;

public class Flower extends PlantHolder implements Renderable {
		
	private Picture flowerHead;
	private Picture flowerBody;
	private Picture flowerLeaf1;
	private Picture flowerLeaf2;
	private Picture cursor;
	private Picture background;
	
	private float idleFactor;
	private float alphaFactor;
	private float crushFactor;
	private float growFactor;
	private float cursorAlphaFactor;
	private float cursorIdleFactor;

	public Flower(float xpos, float ypos, float holder_width,float holder_height,
			      Picture flowerHead,Picture flowerBody,Picture flowerLeaf1,
			      Picture flowerLeaf2,Picture cursor,Picture background ) {
		
		super(xpos, ypos, holder_width, holder_height);
	
		
		// init dummy pictures
		
		//flower head
		Canvas fc1 = flowerHead.beginRecording(60, 60);
		Paint fp1 = new Paint();
		fp1.setColor(Color.RED);
		fc1.drawCircle(30, 30, 15, fp1);
		for (int i = 0; i < 8; i++) {
			fc1.drawCircle((float)(30+Math.cos(i*Math.PI/4)*15), 
					       (float)(30+Math.sin(i*Math.PI/4)*15), 5, fp1);
		}
		flowerHead.endRecording();
		
		//flower body
		fc1 = flowerBody.beginRecording(5, 30);
		fp1.setColor(Color.GREEN);
		fc1.drawRect(new Rect(0,0,3,30), fp1);
		flowerBody.endRecording();
		
		//flowerLeaf1
		fc1 = flowerLeaf1.beginRecording(15, 15);
		fc1.drawCircle(7, 7, 7, fp1);
		flowerLeaf1.endRecording();
		
		//flowerLeaf1
		fc1 = flowerLeaf2.beginRecording(15, 15);
		fc1.drawCircle(7, 7, 7, fp1);
		flowerLeaf2.endRecording();
		
        //cursor
		fc1 = cursor.beginRecording(30, 20);
		fp1.setColor(Color.WHITE);
		fp1.setAlpha(100);
		fc1.drawArc(new RectF(0,0,30,20), 0, 360, false, fp1);
		cursor.endRecording();
		
		//background
		fc1 = background.beginRecording(30, 30);
		fp1.setColor(Color.LTGRAY);
		fc1.drawRoundRect(new RectF(0,0,30,30), 8, 0, fp1);
		background.endRecording();
		
		if (flowerHead != null)
				this.flowerHead = flowerHead;
		
		if (flowerBody != null)
				this.flowerBody = flowerBody;
		
		if (flowerLeaf1 != null)
			this.flowerLeaf1 = flowerLeaf1;
		
		if (flowerLeaf2 != null)
			this.flowerLeaf2 = flowerLeaf2;
		
		if (cursor != null)
				this.cursor = cursor;
		
		if (background != null)
				this.background = background;
	}

	
	public void render(Canvas canvas) {
		
		canvas.save();
		Paint p = new Paint();
		p.setAlpha((int)(255f*alphaFactor));
		canvas.saveLayer(0, 0, 0, 0, p, 0);
		canvas.translate(this.xpos, this.ypos);
		
		canvas.save();
	    canvas.drawPicture(background);
		canvas.restore();
		
		canvas.save();
		canvas.drawPicture(cursor);
		canvas.restore();
		
		canvas.save();
		canvas.translate(0, -30);
		canvas.drawPicture(flowerBody);
		canvas.restore();
		
		canvas.save();
		canvas.translate(-20, -20);
		canvas.drawPicture(flowerLeaf1);
		canvas.restore();
		
		canvas.save();
		canvas.translate(20, -20);
		canvas.drawPicture(flowerLeaf2);
		canvas.restore();
		
		canvas.save();
		canvas.translate(0, -30);
		canvas.drawPicture(flowerHead);
		canvas.restore();
		
		canvas.restore();
	}
	
	public float getX() {
		return this.xpos;
	}

	public float getY() {
	
		return this.ypos;
	}

	public float getIdle() {
		
		return idleFactor;
	}

	public float getAlpha() {
	
		return alphaFactor;
	}

	public float getCrush() {
		
		return crushFactor;
	}

	public float getGrow() {
	
		return growFactor;
	}

	public void setX(float x) {
		
		this.xpos = x;
	}

	public void setY(float y) {
		
		this.ypos = y;
	}

	public void setIdle(float idleFactor) {
	
		this.idleFactor = idleFactor;
	}

	public void setAlpha(float alphaFactor) {

		this.alphaFactor = alphaFactor;
		
	}

	public void setCrush(float crushFactor) {
	
		this.crushFactor = crushFactor;
	}

	public void setGrow(float growFactor) {
		
		this.growFactor = growFactor;
		
	}

	public float getCursorAlpha() {
		
		return cursorAlphaFactor;
	}

	public float getCursorIdle() {
	
		return cursorIdleFactor;
	}

	public void setCursorAlpha(float f) {
		this.cursorAlphaFactor = f;		
	}

	public void setCursorIdle(float f) {
	
		this.cursorIdleFactor = f;
	}

	


}
