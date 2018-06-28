package graphics;

import java.awt.Graphics;

public interface IDrawable {
	 public final static String PICTURE_PATH = "src\\pictures\\";
	 public void loadImages(String nm);
	 public void drawObject(Graphics g);
	 public String getColor();	 
}

