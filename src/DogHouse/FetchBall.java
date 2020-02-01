package DogHouse;

public class FetchBall {
	private int tennisBallX;
	private boolean tennisBallPresent = false;
	private boolean dogHasBall = false;
	
	public int getTennisBallDistance(){
		return tennisBallX;
	}
	
	public void setTennisBallDistance(){
        this.tennisBallX = 33;
    }

	
	public boolean isBallPresent(){//Is ball present at house
		return tennisBallPresent;
	}
	
	public void setBallPresent(boolean x){
		tennisBallPresent = x;
	}
	
	public boolean doesDogHasBall(){
		return dogHasBall;
	}
	
	public void setDogHasBall(boolean x){
		dogHasBall = x;
	}
}
