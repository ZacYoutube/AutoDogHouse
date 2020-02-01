package DogHouse;

public class DistanceFromHouse {
	  double distance = 18;
      double distanceV = 0;
      double total = Math.sqrt((distance * distance) + (distanceV * distanceV));
      
       DistanceFromHouse() {}

       public void setDistance(int newDistance)
       {
           distance = newDistance;

       }

       public void moveForward()
       {
           distance = distance - 3;
         if(distance < 0)
         {distance = 0;}
       }

       public void moveBackward()
       {
           distance = distance + 3;
          

       }
      
       public void setDistanceV(int newDistanceV)
       {
           distanceV = newDistanceV;
       }
      
       public void Upward()
       {
           distanceV = distanceV + 3;
       }
      
       public void Downward()
       {
           distanceV = distanceV - 3;
       }
      
      public void totalDistance()
       {
          
       total = Math.sqrt((distance * distance) + (distanceV * distanceV));
      
       }
}