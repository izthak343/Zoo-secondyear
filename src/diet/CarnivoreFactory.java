package diet;

import animals.Animal;
import animals.Lion;

public class CarnivoreFactory implements AbstractZooFactory {

	@Override
	public Animal produceAnimal(String type) {
		// TODO Auto-generated method stub
		Animal an= new Lion();
		return an;
	}
}
