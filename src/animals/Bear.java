package animals;

import diet.Omnivore;
import graphics.ZooPanel;
import mobility.Point;

public class Bear extends Animal {

	public Bear(int s,int x, int y, int h, int v, String c, ZooPanel p) {
		super("Bear",(int)(s*0.7),s,h,v,c,p);
		setLocation(new Point(x,y));
		setDiet(new Omnivore());
		loadImages("bea");
		cor_x3 = -size/2;
		cor_x4 = 0;
		cor_y1 = (int) (-30-size/5);
		cor_y3 = (int) (size*0.3);
		cor_x5 = -size*6/7;
		cor_y5 = cor_y6 = -size/3;
		cor_h = (int)(size*2/3);
	}

	public Bear() {
		// TODO Auto-generated constructor stub
		super("Bear",(int)(0),(0)*20,0,0,null,null);
		setLocation(new Point(0,0));
		setDiet(new Omnivore());
		this.img="bea";
	}

	@Override
	public void setSize(int size,boolean needChange) {
		if(needChange)
			this.size=(int)(size*0.7);
		else
			this.size=size;

		this.setWeight(size);

		cor_w = cor_h = this.size;
		cor_x3 = -this.size/2;
		cor_x4 = 0;
		cor_y1 = (int) (-30-this.size/5);
		cor_y3 = (int) (this.size*0.3);
		cor_x5 = -this.size*6/7;
		cor_y5 = cor_y6 = -this.size/3;
		cor_h = (int)(this.size*2/3);
	}

//	public Object clone() {
//		try {
//			Object clone = super.clone();
//			Bear b = (Bear) clone;
//
//			return clone;
//		} catch (CloneNotSupportedException e) {
//			throw new RuntimeException("this could never happen", e);
//		}
//	}
	//	protected Object clone() throws CloneNotSupportedException {
	//		Bear cloned = (Employee)super.clone();
	//	    cloned.setDepartment((Department)cloned.getDepartment().clone());
	//	    return cloned;
}


