package plants;

import graphics.ZooPanel;

public class Lettuce extends Plant {
	private static Lettuce instance;

	private Lettuce() {
		super();
		loadImages("lettuce");
	}

	public static Lettuce getInstance(){
		if(instance == null){
			instance = new Lettuce();
		}
		return instance;
	}
}
