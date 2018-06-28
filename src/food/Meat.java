package food;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import mobility.Point;
import food.EFoodType;
import graphics.IDrawable;
import graphics.ZooPanel;


public class Meat implements IEdible ,IDrawable
{
	private ZooPanel MyZoo;
	private BufferedImage img = null;

	public Meat(ZooPanel Zoo)
	{
		MyZoo=Zoo;
	}

	/**
	 * Getter function
	 * @param food type
	 */
	public EFoodType getFoodType() 
	{
		return EFoodType.MEAT;
	}
	
	public String toString()
	{
		return "[" + this.getClass().getSimpleName() + "]";
	}
	
	
	public String getColor() {
		// TODO Auto-generated method stub
		return "red";
	}

	public Point getLocation() {
		return new Point(MyZoo.getWidth()/2,MyZoo.getHeight()/2);
	}
	
	synchronized public void drawObject (Graphics g)
	{
		Point center = new Point((int)(MyZoo.getSize().getWidth()/2) , (int)(MyZoo.getSize().getHeight()/2));
		int foodSize = 60;
		if(img!=null)
			g.drawImage(img , (int)(center.getX() - foodSize/2) ,(int)(center.getY() - foodSize/2),foodSize,foodSize,  MyZoo );
	}
	
	public void loadImages(String nm) {
		try {
			img=ImageIO.read(new File(System.getProperty("user.dir") +PICTURE_PATH + nm));
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	
}

	@Override
	public EFoodType getFoodtype() {
		// TODO Auto-generated method stub
		return null;
	}
}
