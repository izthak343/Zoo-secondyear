package diet;

import utilities.MessageUtility;
import animals.Animal;
import food.IEdible;
import food.EFoodType;

/**
 *The class represent animal that are herbivore
 * @author Oshry nahmani and Shay miara
 * @date 17.4.17
 * @see IDiet
 */
public class Herbivore implements IDiet{
	
	/**
	 * The function checked if animal can eat some food
	 * @param EFoodType food
	 * @return true or false
	 */
	public boolean canEat(EFoodType food){
		if (food==EFoodType.VEGETABLE)
			return true;
		return false;
		}

	/**
	 * The function simulates eating of food and update the weight and make a sound
	 * @param Animal animal, IEdible food
	 * @return True or false
	 */
	public boolean eat(Animal animal, IEdible food)
	{
		if (this.canEat(food.getFoodtype()))
		{
			animal.setWeight(Math.round(1.07*animal.getWeight()*100 /(double)100));
			MessageUtility.logBooleanFunction(animal.getName(), "eat", food, true);
			return true;
		}
		MessageUtility.logBooleanFunction(animal.getName(), "eat", food, false);
		return false;
	}
	
	public String toString() {
		return "[" + this.getClass().getSimpleName() + "] ";
	}

}
