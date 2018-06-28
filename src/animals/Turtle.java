package animals;

import diet.Herbivore;
import graphics.ZooPanel;
import mobility.Point;

/**
 * @author Sergey Tregulov
 *
 */
public class Turtle extends Animal {

	public Turtle(int s,int x, int y, int h, int v, String c, ZooPanel p) {
		super("Turtle",s/2,s/2,h,v,c,p);
		setLocation(new Point(x,y));
		setDiet(new Herbivore());
		loadImages("trt");
		cor_x1 = size/4;
		cor_x2 = (int) (-size/4);
		cor_x3 = (int) (-size*0.25);
		cor_x4 = (int) (size*0.25);
		cor_y1 = (int) (-30-size*0.125);
		cor_y3 = size/8;
		cor_x5 = -size;
		cor_y5 = cor_y6 = -size/4;
		cor_h = (int)(size*0.68);
	}

	public Turtle() {
		// TODO Auto-generated constructor stub
		super("Turtle",(int)(0),(0)*20,0,0,null,null);
		setLocation(new Point(0,0));
		setDiet(new Herbivore());
		this.img="trt";
	}

	@Override
	public void setSize(int size,boolean needChange) {
		if(needChange)
		{
			this.setWeight(size/2);
			this.size=size/2;
		}
		else
			this.size=size;

		cor_x1 = this.size/4;
		cor_x2 = (int) (-this.size/4);
		cor_x3 = (int) (-this.size*0.25);
		cor_x4 = (int) (this.size*0.25);
		cor_y1 = (int) (-30-this.size*0.125);
		cor_y3 = this.size/8;
		cor_x5 = -this.size;
		cor_y5 = cor_y6 = -this.size/4;
		cor_h = (int)(this.size*0.68);
		cor_w = cor_h = this.size;
	}
}
