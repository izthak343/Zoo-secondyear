package diet;

import utilities.MessageUtility;
import animals.Animal;
import food.IEdible;
import food.EFoodType;

/**
 *The class represent animal that are omnivore 
 * @author Oshry nahmani and shay miara
 * @date 17.4.17
 * @see IDiet
 */
public class Omnivore implements IDiet{
	
	/**
	 * The function checked if animal can eat some food
	 * @param EFoodType food
	 * @return true or false
	 */
	public boolean canEat(EFoodType food){
		if (food==EFoodType.MEAT || food==EFoodType.VEGETABLE)
			return true;
		return false;
		}
	
	/**
	 * The function simulates eating of food
	 * @param Animal animal, IEdible food
	 * @return True or false
	 */
	public boolean eat(Animal animal, IEdible food){
	
			if(food.getFoodtype()==EFoodType.MEAT)
			{
				animal.setWeight(Math.round(1.10*animal.getWeight()*100 /(double)100));
				MessageUtility.logBooleanFunction(animal.getName(), "eat", food, true);
				return true;
			}
			else if(food.getFoodtype()==EFoodType.VEGETABLE)
			{
				MessageUtility.logBooleanFunction(animal.getName(), "eat", food, true);
				animal.setWeight(Math.round(1.07*animal.getWeight()*100 /(double)100));
				
				return true;
			}
			else
			{
				MessageUtility.logBooleanFunction(animal.getName(), "eat", food, false);
				return false;
			}
				
		}

	public String toString() {
		return "[" + this.getClass().getSimpleName() + "] ";
	}
}
