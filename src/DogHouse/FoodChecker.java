package DogHouse;

import java.util.Random;

public class FoodChecker {
	
	public boolean FoodCheck() {
		 Random randomBoolean = new Random();
		 return randomBoolean.nextBoolean();
	}
	public boolean WaterCheck() {
		 Random randomBoolean = new Random();
		 return randomBoolean.nextBoolean();
	}
	
}
