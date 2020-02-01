package DogHouse;

import java.time.LocalDate;

public class Dog
{

	static int count = 0;
	
	String name, breed, furColor, gender, userEmail;
	double weight, height;
	boolean neuterSpay, microchip;
	LocalDate birthday;
	int id;
	//getAge() from birthday for age
	public Dog(String name, String breed, String gender, String furColor, double weight, double height, LocalDate birthday, boolean neuterSpay, boolean microchip, String userEmail) 
	{
		this.id = count++;
		this.name = name;
		this.breed = breed;
		this.gender = gender;
		this.furColor = furColor;
		this.weight = weight;
		this.height = height;
		this.birthday = birthday;
		this.neuterSpay = neuterSpay;
		this.microchip = microchip;
		this.userEmail = userEmail;
	}
	
	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public LocalDate getBirthday() {
		return birthday;
	}
	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBreed() {
		return breed;
	}
	public void setBreed(String breed) {
		this.breed = breed;
	}
	public String getFurColor() {
		return furColor;
	}
	public void setFurColor(String furColor) {
		this.furColor = furColor;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public boolean getNeuterSpay() {
		return neuterSpay;
	}
	public void setNeuterSpay(boolean neuterSpay) {
		this.neuterSpay = neuterSpay;
	}
	public boolean getMicrochip() {
		return microchip;
	}
	public void setMicrochip(boolean microchip) {
		this.microchip = microchip;
	}
	public int getId() {
		return id;
	}
	

	
}