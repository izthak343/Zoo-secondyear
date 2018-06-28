package memento;

import java.util.ArrayList;
import food.IEdible;
import animals.Animal;

public class ZooMemento {

	private ArrayList<Animal> animals;
	private IEdible food;

	public ZooMemento(ArrayList<Animal> an,IEdible fd)
	{
		animals = new ArrayList<Animal>();
		for(Animal animal : an)
		{
			try {
				Animal new_animal = (Animal) animal.clone();
				animals.add(new_animal);
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
		}
		food = fd;
	}

	public ArrayList<Animal> getList(){
		return animals;
	}

	public IEdible getFood(){
		return food;
	}
}
