package graphics;

import java.awt.Graphics;

public interface Drawable {
	 public final static String PICTURE_PATH = "D:\\MihlalaSCE\\TihnutMitkadem\\homeWorks\\2017_spring\\HW3\\pictures\\";
	 public void loadImages(String nm);
	 public void drawObject(Graphics g);
	 public String getColor();	 
}

