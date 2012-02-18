package cz.gug.hackaton.manhattan.actors;

import android.graphics.Picture;

public class Flower extends PlantHolder{
		
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
		this.flowerHead = flowerHead;
		this.flowerBody = flowerBody;
		this.flowerLeaf1 = flowerLeaf1;
		this.flowerLeaf2 = flowerLeaf2;
		this.cursor = cursor;
		this.background = background;
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
