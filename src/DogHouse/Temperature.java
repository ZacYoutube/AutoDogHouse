package DogHouse;

import java.util.Random;

public class Temperature {

    int temperature;
	static int optTemp = 0;
    public int GetTemperature() 
    {
    	Random rand = new Random();
    	int randomNum = rand.nextInt((85 - 70) + 1) + 70;
        return temperature = randomNum;
    }
    public int GetTemp() {
    	return temperature;
    }
    public int GetOptimalTemp()
    {
    	if(optTemp == 0)
    	{
    		return optTemp = 70;
    	}
    	else 
    	{
    		return optTemp;
    	}
    	
    }
    public int SetCurrentTemp(int optTemperature)
    {
    	temperature = optTemp;
    	return temperature;
    }
    public static int SetOptimalTemp(int optTemperature)
    {
    	optTemp = optTemperature;
    	return optTemp;
    }
    public int SetTemperatureUp()
    {
        temperature = temperature + 5;
        return temperature;
    }
    public int SetTemperatureDown()
    {
        temperature = temperature - 5;
        return temperature;
    }

}