package meat;

import food.EFoodType;
import food.IEdible;
import graphics.IDrawable;
import graphics.ZooPanel;
import mobility.ILocatable;
import mobility.Point;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Meat implements IEdible, ILocatable, IDrawable {

	private Point location;
	protected BufferedImage img;
	private static Meat instance;

	private Meat() {
		this.location = new Point(ZooPanel.getInstance().getWidth()/2,ZooPanel.getInstance().getHeight()/2);
		loadImages("meat");
	}

	public static Meat getInstance(){
		if(instance == null){
			instance = new Meat();
		}
		return instance;
	}
	
	public void loadImages(String nm){
			try { 
				img = ImageIO.read(new File(PICTURE_PATH + nm + ".gif"));
			}
			catch (IOException e) { System.out.println("Cannot load picture"); }
	}

	public void drawObject(Graphics g) {
		g.drawImage(img, location.getX()-20, location.getY()-20, 40, 40, ZooPanel.getInstance());
	}
	
	public EFoodType getFoodtype() { return EFoodType.MEAT; }
	public String getColor() { return null; }	 
	public Point getLocation() { return null; }
	public boolean setLocation(Point location) { return false; }
}
