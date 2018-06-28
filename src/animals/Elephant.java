package animals;

import diet.Herbivore;
import graphics.ZooPanel;
import mobility.Point;

public class Elephant extends Animal {

	public Elephant(int s,int x, int y, int h, int v, String c, ZooPanel p) {
		super("Elephant",(int)(s*1.4),(s-45)*20,h,v,c,p);
		setLocation(new Point(x,y));
		setDiet(new Herbivore());
		loadImages("elf");
		cor_x3 = (int) (-size*0.3);
		cor_y1 = (int) (-30-size*0.45);
		cor_y3 = (int) (size*0.25);
		cor_x5 = -size*3/4;
		cor_x6 = -size*1/5;
		cor_y5 = cor_y6 = -size/4;
		cor_h = (int)(size*0.7);
	}

	public Elephant() {
		// TODO Auto-generated constructor stub
		super("Elephant",(int)(0),(0)*20,0,0,null,null);
		setLocation(new Point(0,0));
		setDiet(new Herbivore());
		this.img="elf";
	}

	public void setSize(int s,boolean needChange) {
		if(needChange)
		{
			this.setWeight((s-45)*20);
			this.size = (int) (s*1.4);
		}
		else
			this.size=s;
		
		cor_w = cor_h = this.size;
		cor_x3 = (int) (-this.size*0.3);
		cor_y1 = (int) (-30-this.size*0.45);
		cor_y3 = (int) (this.size*0.25);
		cor_x5 = -this.size*3/4;
		cor_x6 = -this.size*1/5;
		cor_y5 = cor_y6 = -this.size/4;
		cor_h = (int)(this.size*0.7);
	}	 
}
