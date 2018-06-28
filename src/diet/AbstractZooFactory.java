package diet;

import animals.Animal;

public interface AbstractZooFactory {

	public Animal produceAnimal(String type);

	public static AbstractZooFactory createAnimalFactory(String foodType)
	{
		AbstractZooFactory factory=null;
		
		switch(foodType){
		case "Plant": // Herbivore
			factory = new HerbivoreFactory();
			break;
		case "Mix": // Omnivore
			factory = new OmnivoreFactory();
			break;
		case "Meat": // Carnivore
			factory = new CarnivoreFactory();
			break;
		}
		return factory;
	}
}
