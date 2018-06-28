package diet;

import animals.Animal;
import animals.Elephant;
import animals.Giraffe;
import animals.Turtle;

public class HerbivoreFactory implements AbstractZooFactory {

	@Override
	public Animal produceAnimal(String type) {
		// TODO Auto-generated method stub
		Animal an=null;
		switch(type)
		{
		case "Elephant":
			an=new Elephant();
			break;
		case "Giraffe":
			an=new Giraffe();
			break;
		case "Turtle":
			an=new Turtle();
			break;
		}
		return an;
	}
//		System.out.println("herb factory");
//					AddAnimalDialog dial = new AddAnimalDialog(zoo,"Add an animal to aquarium",type);
//					dial.setVisible(true);
//	}
}
