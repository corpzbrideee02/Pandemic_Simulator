/**
 * Program Name: Person.java
 * Purpose: This represents a person in the Pandemic Simulator who may or may not be infected.
 * Coder: Brittany Diesbourg (section 4) + Dianne Corpuz (section 2)
 * Date: Jul. 29, 2021
 *        
 */        

import java.awt.*;

public class Person
{
	private int xCoordinate;
	private int yCoordinate;
	private int diameter;
	private Color color;
	private int xIncrementValue;
	private int yIncrementValue;
	private int cycleCounter;	
	private boolean isAlive;
	private boolean isInfected;
	private int immunityStatus;
	private boolean previouslyInfected; // checks if somebody got Covid before so the number of people doesn't increment a second time
	
	public Person(int diam, Color color, int widthValue, int heightValue,int immStat, boolean inf, boolean isAlv, int cycleC)
	{
		this.immunityStatus=immStat;
		this.isInfected=inf;
		this.isAlive=isAlv;
		this.cycleCounter=cycleC;
		this.diameter = diam;
		this.color = color;
		int randomX, randomY;
		boolean loopflag1 = true;
		this.previouslyInfected = false;
		
	  //loop 
		while(loopflag1)
		{
			//generate a random value using widthValue
			randomX = (int)(Math.random() * widthValue);
			if(randomX >= 0 && randomX <= widthValue - this.diameter)
			{
				this.xCoordinate = randomX;
				loopflag1 = false;
			}
		}//end while		
		//reset flag1 to true to start second loop
		loopflag1 = true;
		while(loopflag1)
		{
			//repeat for yCoord
			randomY = (int)(Math.random() * heightValue);
			if(randomY >= 0 && randomY <= heightValue - this.diameter)
			{
				//we have a valid y value, assign it to yCoord
				this.yCoordinate = randomY;
				//System.out.println("STUB:Valid random yCoord value of " + randomY);
			  loopflag1 = false;
			}
		}//end while		
		boolean loopFlag = true;
		while(loopFlag)
		{
			this.xIncrementValue = (int)(Math.random()*11 - 5);
			this.yIncrementValue = (int)(Math.random()*11 - 5);		
			if(this.xIncrementValue ==0 && this.xIncrementValue ==0)
			{
			  //run it again
				this.xIncrementValue = (int)(Math.random()*11 - 5);
				this.yIncrementValue = (int)(Math.random()*11 - 5);
			}
			else
			{
				loopFlag = false;
			}
		}//end loop					
	}//end random constructor

	//getters and setters
	public boolean isPreviouslyInfected()
	{
		return previouslyInfected;
	}
	public void setPreviouslyInfected(boolean previouslyInfected)
	{
		this.previouslyInfected = previouslyInfected;
	}
	public Color getColor()
	{
		return color;
	}
	public void setColor(Color color)
	{
		this.color = color;
	}
	public int getxCoord()
	{
		return xCoordinate;
	}
	public int getyCoord()
	{
		return yCoordinate;
	}
	public int getDiameter()
	{
		return diameter;
	}
	public void setxCoord(int xCoord)
	{
		this.xCoordinate = xCoord;
	}
	public void setyCoord(int yCoord)
	{
		this.yCoordinate = yCoord;
	}
	public int getxIncrement()
	{
		return xIncrementValue;
	}
	public void setxIncrement(int xIncrement)
	{
		this.xIncrementValue = xIncrement;
	}
	public int getyIncrement()
	{
		return yIncrementValue;
	}
	public void setyIncrement(int yIncrement)
	{
		this.yIncrementValue = yIncrement;
	}
	public int getCycleCounter() {
		return cycleCounter;
	}
	public void setCycleCounter(int cycleCounter) {
		this.cycleCounter = cycleCounter;
	}
	public boolean isAlive() {
		return isAlive;
	}
	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}
	public boolean isInfected() {
		return isInfected;
	}
	public void setInfected(boolean isInfected) {
		this.isInfected = isInfected;
	}
	public int getImmunityStatus() {
		return immunityStatus;
	}
	public void setImmunityStatus(int immunityStatus) {
		this.immunityStatus = immunityStatus;
	}
}
//end class