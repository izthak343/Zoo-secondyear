package plants;

import graphics.ZooPanel;

public class Cabbage extends Plant {
	
	private static Cabbage instance;

	
	private Cabbage() {
		super();
		loadImages("cabbage");
	}
	
	public static Cabbage getInstance(){
		if(instance == null){
			instance = new Cabbage();
		}
		return instance;
	}
}
