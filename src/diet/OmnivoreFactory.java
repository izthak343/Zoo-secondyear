package diet;

import animals.Animal;
import animals.Bear;

public class OmnivoreFactory implements AbstractZooFactory {

	@Override
	public Animal produceAnimal(String type) {
		// TODO Auto-generated method stub
		Animal an= new Bear();
		return an;
	}

}
