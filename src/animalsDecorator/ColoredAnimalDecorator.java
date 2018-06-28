package animalsDecorator;

import animals.Animal;
/*
 * class ColoredAnimalDecorator
 */
public class ColoredAnimalDecorator implements ColoredAnimal {

	private ColoredAnimal animal;
	
	public ColoredAnimalDecorator(ColoredAnimal a)
	{
		this.animal=a;
	}
	/*
	 * function that replace the color of the animal
	 * @see animalsDecorator.ColoredAnimal#PaintAnimal(java.lang.String)
	 */
	public void PaintAnimal(String color) {
		// TODO Auto-generated method stub		
		Animal a =((Animal)animal);
		a.setCol(color);
		String nm = null;
		
		switch (a.getName()) {
		case "Bear":
			nm = "bea";
			break;
		case "Elephant":
			nm = "elf";
			break;
		case "Giraffe":
			nm = "grf";
			break;
		case "Lion":
			nm = "lio";
			break;
		case "Turtle":
			nm = "trt";
			break;
		}
		a.loadImages(nm);
	}

}
