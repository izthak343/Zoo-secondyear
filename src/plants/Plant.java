package plants;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import food.EFoodType;
import food.IEdible;
import graphics.IDrawable;
import graphics.ZooPanel;
import mobility.ILocatable;
import mobility.Point;

public abstract class Plant implements IEdible, ILocatable, IDrawable {

	private Point location;
	protected BufferedImage img;
	
	public Plant() {
		this.location = new Point(ZooPanel.getInstance().getWidth()/2,ZooPanel.getInstance().getHeight()/2);
	}

	public void loadImages(String nm){
			try { 
				img = ImageIO.read(new File(PICTURE_PATH + nm + ".png"));
			}
			catch (IOException e) { System.out.println("Cannot load picture"); }
	}

	public void drawObject(Graphics g) {
		g.drawImage(img, location.getX()-20, location.getY()-20, 40, 40, ZooPanel.getInstance());
	}
	
	public EFoodType getFoodtype() { return EFoodType.VEGETABLE; }
	public String getColor() { return "Green"; }	 
	public Point getLocation() { return null; }
	public boolean setLocation(Point location) { return false; }


}
