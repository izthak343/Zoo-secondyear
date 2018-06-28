package diet;
import food.EFoodType;
import food.*;
import animals.*;

public interface IDiet {


/**
 *Theinterface represent functionality of food.
 * @author Oshry nahmani 
 * @date 17.4.17
 * @see Carnivore, Ominivore, Herbivor
 */
	boolean canEat(EFoodType food);
	boolean eat(Animal animal, IEdible food);
	
}
