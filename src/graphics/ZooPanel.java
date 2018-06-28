package graphics;

import java.awt.*;

import graphics.DuplicateDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.imageio.ImageIO;
import javax.swing.*;

import meat.Meat;
import memento.ZooMemento;
import diet.AbstractZooFactory;
import animals.Animal;
import animalsDecorator.DecorateDialog;
import animalsObserved.ZooObserver;
import food.EFoodType;
import plants.Cabbage;
import plants.Lettuce;
import plants.Plant;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/*itzhak cohen 203415138*/
public class ZooPanel extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private final String BACKGROUND_PATH = Animal.PICTURE_PATH +"savanna.jpg";
	private final String MEAT_PATH = Animal.PICTURE_PATH+"meat.gif";
	private EFoodType Food;
	private JPanel p1;
	private JButton[] b_num;
	private String[] names = {"Add Animal","Sleep","Wake up","Clear","Food","Info"
			,"Decorate","Duplicate","Save state","Restore state","Exit"};
	private ArrayList<Animal> animals;
	private Plant plant = null;
	private Meat meat = null;
	private JScrollPane scrollPane;
	private boolean isTableVisible;
	private int totalCount;
	private BufferedImage img, img_m;
	private boolean bgr;
	private Executor threadPoolAnimals;//thread pool instance  
	private static ZooPanel instance;
	private ZooObserver controller; 
	private ZooMemento[] mementos;

	private ZooPanel()
	{
		Food = EFoodType.NOTFOOD;
		totalCount = 0;
		isTableVisible = false;
		animals = new ArrayList<Animal>();
		threadPoolAnimals = Executors.newFixedThreadPool(5);
		controller = new ZooObserver();
		controller.start();	    
		mementos = new ZooMemento[3];
		setBackground(new Color(255,255,255));

		p1=new JPanel();
		p1.setLayout(new GridLayout(2,6,0,0));
		p1.setBackground(new Color(255,255,255));

		b_num=new JButton[names.length];
		for(int i=0;i<names.length;i++)
		{
			b_num[i]=new JButton(names[i]);
			b_num[i].addActionListener(this);
			b_num[i].setBackground(Color.lightGray);
			p1.add(b_num[i]);		
		}
		setLayout(new BorderLayout());
		add(p1, BorderLayout.SOUTH);

		img = img_m = null;
		bgr = false;
		try { img = ImageIO.read(new File(BACKGROUND_PATH)); } 
		catch (IOException e) { System.out.println("Cannot load background"); }
		try { img_m = ImageIO.read(new File(MEAT_PATH)); } 
		catch (IOException e) { System.out.println("Cannot load meat"); }
	}		

	public static ZooPanel getInstance(){
		if(instance == null){
			instance = new ZooPanel();
		}
		return instance;
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);	

		if(bgr && (img!=null))
			g.drawImage(img, 0, 0, getWidth(), getHeight(), this);

		if(Food == EFoodType.MEAT &&( meat !=null ))
			meat.drawObject(g);

		if((Food == EFoodType.VEGETABLE) && (plant != null))
			plant.drawObject(g);

		synchronized(this) {
			for(Animal an : animals)
				if(an.isRuning())
					an.drawObject(g);
		}
	}

	public void setBackgr(int num) {
		switch(num) {
		case 0:
			setBackground(new Color(255,255,255));
			bgr = false; 
			break;
		case 1:
			setBackground(new Color(0,155,0));
			bgr = false; 
			break;
		default:
			bgr = true;   
		}
		repaint();
	}

	synchronized public EFoodType checkFood()
	{
		return Food;
	}

	/**
	 * CallBack function 
	 * @param f
	 */
	synchronized public void eatFood(Animal an)
	{
		if(Food != EFoodType.NOTFOOD)
		{
			if(Food == EFoodType.VEGETABLE)
				plant = null;
			Food = EFoodType.NOTFOOD;
			an.eatInc();
			totalCount++;
			System.out.println("The "+an.getName()+" with "+an.getColor()+" color and size "+an.getSize()+" ate food.");
		}
		else
		{
			System.out.println("The "+an.getName()+" with "+an.getColor()+" color and size "+an.getSize()+" missed food.");
		}
	}
    
	public void addDialog()
	{
		System.out.println("add dialog");
		AbstractZooFactory factory = null;
		AddAnimalDialog dial = null;
		if(animals.size()==10) {
			JOptionPane.showMessageDialog(this, "You cannot add more than " + 10 + " animals");
		}
		else {
			String[] options = {"Herbivore","Omnivore","carnivore"};
			String[] titles={"Please choose animal","Animal choose"};
			int Choose = JOptionPane.showOptionDialog(this,
					titles[0],
							titles[1],
							JOptionPane.YES_NO_CANCEL_OPTION,
							JOptionPane.QUESTION_MESSAGE,
							null,
							options,
							options[options.length - 1]);
			switch(Choose){
			case 0:
				factory = AbstractZooFactory.createAnimalFactory("Plant");
				dial = new AddAnimalDialog(this,"Add an animal to aquarium",factory,"HerbivoreFactory");
				dial.setVisible(true);
				break;
			case 1:
				factory = AbstractZooFactory.createAnimalFactory("Mix");
				dial = new AddAnimalDialog(this,"Add an animal to aquarium",factory,"OmnivoreFactory");
				dial.setVisible(true);
				break;
			case 2:
				factory = AbstractZooFactory.createAnimalFactory("Meat");
				dial = new AddAnimalDialog(this,"Add an animal to aquarium",factory,"CarnivoreFactory");
				dial.setVisible(true);
				break;
			case -1:
				return;
			}
		}
	}

	public void addAnimal(Animal an, int sz, int hor, int ver, String c)
	{
		an.setAnimal(sz, hor, ver, c,this);
		synchronized(this){
			an.addObserver(controller);
			animals.add(an);
			an.setTask(((ExecutorService)threadPoolAnimals).submit(an));
			// execution of Runnable by call of execute()
			System.out.println("Creating a new animal...");
		}
	}

	public void start() {
		for(Animal an : animals)
			an.setResume();
	}

	public void stop() {
		for(Animal an : animals)
			an.setSuspend();
	}

	synchronized public void clear()
	{
		for(int i=0; i<animals.size();i++)
		{
			if(animals.get(i).isRuning())
			{
				animals.get(i).interrupt();
				animals.remove(i);
				i--;
			}
		}

		Food = EFoodType.NOTFOOD;
		plant = null;
		totalCount = 0;
		repaint();
	}

	synchronized public void preyEating(Animal predator, Animal prey)
	{
		predator.eatInc();
		totalCount -= (prey.getEatCount()-1);
	}

	synchronized public void addFood()
	{
		if(Food == EFoodType.NOTFOOD){
			Object[] options = {"Meat", "Cabbage", "Lettuce"}; 
			int n = JOptionPane.showOptionDialog(this, 
					"Please choose food", "Food for animals", 
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, 
					null, options, options[2]);

			switch(n) {
			case 0: // Meat
				Food = EFoodType.MEAT;
				meat = Meat.getInstance();
				break;
			case 1: // Cabbage
				Food = EFoodType.VEGETABLE;
				plant = Cabbage.getInstance();
				break;
			default: // Lettuce
				Food = EFoodType.VEGETABLE;
				plant = Lettuce.getInstance();
				break;
			}
		}
		else {
			Food = EFoodType.NOTFOOD;
			plant = null;
		}
		repaint();
	}

	public void info()
	{  	 
		if(isTableVisible == false)
		{
			int i=0;
			int sz = animals.size();

			String[] columnNames = {"Animal","State","Color","Weight","Hor. speed","Ver. speed","Eat counter"};
			String [][] data = new String[sz+1][columnNames.length];
			for(Animal an : animals)
			{
				data[i][0] = an.getName();
					if(an.isRuning())
					data[i][1] = "Running";
					else data[i][1] = "Bloced";
					data[i][2] = an.getColor();
					data[i][3] = new Integer((int)(an.getWeight())).toString();
					data[i][4] = new Integer(an.getHorSpeed()).toString();
					data[i][5] = new Integer(an.getVerSpeed()).toString();
					data[i][6] = new Integer(an.getEatCount()).toString();
					i++;
			}
			data[i][0] = "Total";
			data[i][5] = new Integer(totalCount).toString();

			JTable table = new JTable(data, columnNames);
			scrollPane = new JScrollPane(table);
			scrollPane.setSize(450,table.getRowHeight()*(sz+1)+24);
			add( scrollPane, BorderLayout.CENTER );
			isTableVisible = true;
		}
		else
		{
			isTableVisible = false;
		}
		scrollPane.setVisible(isTableVisible);
		repaint();
	}

	public void destroy()
	{ 
		for(Animal an : animals)
			an.interrupt();
		controller.interrupt();
		System.exit(0);
	}

	public void decoretor()
	{
		boolean Flag=false;
		for(Animal a:animals)
			if(a.getColor().equals("Natural"))
				Flag=true;
		if(animals.size() == 0 || Flag == false) 
			JOptionPane.showMessageDialog(this, "You have not animals for decoration");
		else
			new DecorateDialog("Decorate an animal",this);
	}

	public void addAnimal(Animal an)
	{
		synchronized(this){
			if(an == null ) return;
			animals.add(an);
			an.addObserver(controller);
			an.setTask(((ExecutorService)threadPoolAnimals).submit(an));
			// execution of Runnable by call of execute()
			System.out.println("Creating a new animal...");
		}
	}

	public Animal getAnimal(int index)
	{
		return animals.get(index);
	}

	
	public ArrayList<Animal> getAnimals() {
		return animals;
	}
	/*
	 * function for duplicate 
	 */
	public void duplicate()
	{
		if( animals.size() == 0 ) 
			JOptionPane.showMessageDialog(this, "You have not animals for duplicate");
		else
			new DuplicateDialog(this);
	}
/*
 * function that save the state
 */
	public void saveState()
	{
		String[] titles={"Please choose state to restore","Saved states"};
		String[] options = {"State 1","State 2","State 3","Cancel"};
		this.stop();
		int Choose = JOptionPane.showOptionDialog(this,
				titles[0],
						titles[1],
						JOptionPane.YES_NO_CANCEL_OPTION,
						JOptionPane.QUESTION_MESSAGE,
						null,
						options,
						options[options.length - 1]);
		ZooMemento zm = null; 
		if (Choose==-1)
		{
			start();
			return;    
		}
		if(meat == null)
			zm = new ZooMemento(animals,plant); // create new ZooMemento object
		else
			zm = new ZooMemento(animals,meat); // create new ZooMemento object

		if (mementos[Choose] == null) //check if this state is open
		{
			mementos[Choose] = zm;
			JOptionPane.showMessageDialog(this, "Saved!");
		}
		else
			JOptionPane.showMessageDialog(this, "Not saved!");
		this.start();

	}
    /*
    * function the play restore state
    */
	public void restoreState()
	{
		boolean flag=false;
		for(int i=0;i<3;i++)
		{
			if(mementos[i]!=null)
				flag=true;
		}
		if(flag) //check if exists saves
		{
			String[] titles={"Please choose state for restore","Saved states"};
			String[] options = {"State 1","State 2","State 3","Cancel"};			
			int Choose = JOptionPane.showOptionDialog(this,
					titles[0],
							titles[1],
							JOptionPane.YES_NO_CANCEL_OPTION,
							JOptionPane.QUESTION_MESSAGE,
							null,
							options,
							options[options.length - 1]);
			ZooMemento zm = null;
			if (Choose!=-1)
			{ 
				zm = mementos[Choose];
				mementos[Choose]=null;
			}
			else	return;
			if(zm!= null) // check if exist save in this state
			{
				this.clear();
				for(Animal a : zm.getList()) // restore all the animals
				{
					try {
						Animal new_animal = (Animal) a.clone();
						new_animal.addObserver(controller);
						new_animal.setTask(((ExecutorService)threadPoolAnimals).submit(new_animal));
						animals.add(new_animal);
					} catch (CloneNotSupportedException e) {
						e.printStackTrace();
					}
				}

				if(zm.getFood() != null)// restore the food
				{
					if(zm.getFood().getFoodtype() ==  EFoodType.VEGETABLE)
					{
						plant = (Plant) zm.getFood();
						Food = EFoodType.VEGETABLE;
					}
					else
					{
						meat =  (Meat)zm.getFood();
						Food = EFoodType.MEAT;
					}
					repaint();
				}
			}
			else
			{
				JOptionPane.showMessageDialog(this, "There is no saving in this mode");
				return;
			}
		}
		else
			JOptionPane.showMessageDialog(this, "You have not saved states");
	}

	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == b_num[0]) // "Add Animal"
			addDialog();
		else if(e.getSource() == b_num[1]) // "Sleep"
			stop();
		else if(e.getSource() == b_num[2]) // "Wake up"
			start();
		else if(e.getSource() == b_num[3]) // "Clear"
			clear();
		else if(e.getSource() == b_num[4]) // "Food"
			addFood();
		else if(e.getSource() == b_num[5]) // "Info"
			info();
		else if(e.getSource() == b_num[6]) // decoretor 
			decoretor();
		else if(e.getSource() == b_num[7]) // duplicate
			duplicate();
		else if(e.getSource() == b_num[8]) // duplicate
			saveState();
		else if(e.getSource() == b_num[9]) // duplicate
			restoreState();
		else if(e.getSource() == b_num[10]) // "Exit"
			destroy();
	}

	public void animalEatAnimal()
	{
		boolean prey_eaten = false;
		synchronized(this) {
			for(Animal predator : animals) {
				for(Animal prey : animals) {
					if(predator != prey && predator.getDiet().canEat(prey.getFoodtype()) && predator.getWeight()/prey.getWeight() >= 2 &&
							(Math.abs(predator.getLocation().getX() - prey.getLocation().getX()) < prey.getSize()) &&
							(Math.abs(predator.getLocation().getY() - prey.getLocation().getY()) < prey.getSize())) {
						preyEating(predator,prey);
						System.out.print("The "+predator+" cought up the "+prey+" ==> ");
						prey.interrupt();
						animals.remove(prey);
						repaint();
						//JOptionPane.showMessageDialog(frame, ""+prey+" killed by "+predator);
						prey_eaten = true;
						break;
					}
				}
				if(prey_eaten)
					return;
			}
		}
	}

	public boolean isChange() {
		boolean rc = false;
		for(Animal an : animals) {
			if(an.getChanges()){
				rc = true;
				an.setChanges(false);
			}
		}
		return rc;
	}
}