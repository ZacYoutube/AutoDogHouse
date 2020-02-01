package DogHouse;

public class DistanceFromHouseV 
{
	int distanceV = 3;
	
	DistanceFromHouseV() {}
	
	public void setDistance(int newDistance)
	{
		distanceV = newDistance;
	}
	
	public void Upward()
	{
		distanceV = distanceV + 3;
	}
	
	public void Downward()
	{
		distanceV = distanceV -3;
	}
}