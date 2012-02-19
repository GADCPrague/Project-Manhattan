package cz.gug.hackaton.manhattan.actors;

public class PlantHolder implements Crushable {
	
	protected float xpos;
	protected float ypos;
	protected float holder_width,holder_height;
	
	
	public PlantHolder() {
		
	}
	
	public PlantHolder(float xpos, float ypos, float holder_width,
			float holder_height) {
		super();
		this.xpos = xpos;
		this.ypos = ypos;
		this.holder_width = holder_width;
		this.holder_height = holder_height;
	}


	
	public boolean wasCrushed(float x, float y) {
	
		if (x >= xpos && x <= xpos + holder_width &&
		    y >= ypos && y <= ypos + holder_height) {
			return true;
		}
		
		return false;
	}
	
	

}
