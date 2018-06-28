package animals;

import diet.Herbivore;
import graphics.ZooPanel;
import mobility.Point;

public class Giraffe extends Animal {

	public Giraffe(int s,int x, int y, int h, int v, String c, ZooPanel p) {
		super("Giraffe",s*4/3,s*2,h,v,c,p);
		setLocation(new Point(0,0));
		setDiet(new Herbivore());
		loadImages("grf");
		cor_x1 = size/4;
		cor_x2 = (-size/4);
		cor_x3 = (int) (- size*0.25);
		cor_x4 = (int) (size*0.25);
		cor_y1 = (int) (-30 - size*9/10);
		cor_y3 = size/10;
		cor_x5 = -size/2;
		cor_y5 = cor_y6 = -size/10;
		cor_w = (int)(size*0.7);
	}

	public Giraffe() {
		// TODO Auto-generated constructor stub
		super("Giraffe",(int)(0),(0)*20,0,0,null,null);
		setLocation(new Point(0,0));
		setDiet(new Herbivore());
		this.img="grf";
	}

	@Override
	public void setSize(int size,boolean needChange) {
		if(needChange)
		{
			this.setWeight(size*2);
			this.size=size*4/3;
		}
		else
			this.size=size;

		cor_w = cor_h = this.size;
		cor_x1 = this.size/4;
		cor_x2 = (-this.size/4);
		cor_x3 = (int) (- this.size*0.25);
		cor_x4 = (int) (this.size*0.25);
		cor_y1 = (int) (-30 - this.size*9/10);
		cor_y3 = this.size/10;
		cor_x5 = -this.size/2;
		cor_y5 = cor_y6 = -this.size/10;
		cor_w = (int)(this.size*0.7);
	}
}
