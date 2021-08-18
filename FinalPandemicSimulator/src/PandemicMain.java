/**
 * Program Name: PandemicMain.java
 * Purpose: This is the Main class for the simulator. It also contains the class that builds and runs the animation.
 * Coder: Brittany Diesbourg (section 4) + Dianne Corpuz (section 2)
 * Date: Jul. 29, 2021
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.TimerTask;

public class PandemicMain extends JPanel
{
	private final int WIDTH = 800, HEIGHT = 700;//size of JPanel
	private final int LAG_TIME = 200; // time in milliseconds between re-paints of screen
	private Timer time;//Timer class object that will fire events every LAG_TIME interval
	private final int IMG_DIM =10; //size of person to be drawn
	private final int CYCLE_COUNTER_START=0;
	
	public static int totalInfectedNum=1;     //total of infectedPeople
	public static int unvaccinatedInfectedNum=1; 
	public  static int oneShotInfectedNum=0; //patially vaccinated who contracted the disease
	public  static int twoShotInfectedNum=0; //fully vaccinated who contracted the disease
	public  static int recoveredInfectedNum=0;
	
	public static int numDiedUnvac = 0;
	public static int numDiedFullyVac = 0;
	public static int numDiedPartVac = 0;
	public static int numDiedRecovered = 0;
	
	
	public static int counterDied=0;
	private static int	countTime=0, countTimeDead=0, countSeconds = 1, weekCount = 2; //counters
	
	private static int no_imm_, one_shot_,two_shot_,nat_imm_,array_size_;
	
	private Person [] personList = null;
	static PandemicSimulator ps= null;
	
	//constructor
	public PandemicMain(int noImm, int oneS, int twoS, int natImm, int popSize)
	{
		no_imm_=noImm;
		one_shot_=oneS;
		two_shot_=twoS;
		nat_imm_=natImm;
		array_size_=popSize;

		personList=new Person[array_size_]; 
		//set properties of 1st person
		personList[0] = new Person(IMG_DIM, Color.RED,WIDTH, HEIGHT,1,true,true,CYCLE_COUNTER_START); //assume 1st infected person has no immunity, 0 as cycleCounter
		personList[0].setCycleCounter(personList[0].getCycleCounter()+LAG_TIME);
		boolean isInfected_=false;
		boolean isAlive_=true;
		
			for(int i = 1; i < personList.length; i++)
			{
					if(i<no_imm_)
					{
						//put 1 in immunityStatus
						//System.out.print(i);
						//System.out.println(" -> 1");
						personList[i] = new Person(IMG_DIM, Color.BLUE, WIDTH, HEIGHT,1,isInfected_,isAlive_,CYCLE_COUNTER_START);	
					}
					else if(i<(no_imm_+one_shot_))
					{
						//put 2 in immunityStatus
						personList[i] = new Person(IMG_DIM, Color.CYAN, WIDTH, HEIGHT,2,isInfected_,isAlive_,CYCLE_COUNTER_START);	
					}
					else if(i<(no_imm_+one_shot_+two_shot_))
					{
						//put 3 in immunityStatus
						personList[i] = new Person(IMG_DIM, Color.YELLOW, WIDTH, HEIGHT,3,isInfected_,isAlive_,CYCLE_COUNTER_START);
					}
					else if(i<=(no_imm_+one_shot_+two_shot_+nat_imm_))
					{
						//put 4 in immunityStatus
						personList[i] = new Person(IMG_DIM, Color.GREEN, WIDTH, HEIGHT,4,isInfected_,isAlive_,CYCLE_COUNTER_START);
					}
					 
			}//end for
			this.setPreferredSize(new Dimension(WIDTH, HEIGHT) );
			this.setBackground(Color.WHITE);
			
		System.out.println("From main ArraySize: "+array_size_);
		System.out.println("imm[1]: "+no_imm_);
		System.out.println("imm[2]: "+one_shot_);
		System.out.println("imm[3]: "+two_shot_);
		System.out.println("imm[4]: "+nat_imm_);
		
	}//end constructor
	
	public void setStartTimer()
	{
		time = new Timer(LAG_TIME, new BounceListener() );
		time.start();
		ps.getCountDownNum().setText(" 90s");
		ps.getWeekNum().setText(" 1");
	}
	public void setStopTimer()
	{
		if (ps.getStart().getActionCommand() != "RESUME") // prevents the results popping up again after hitting pause
				new PandemicResults(this, ps.getButtonHandler().getPopSize());
			
		time.stop();
		//reset the totals and counters
		totalInfectedNum=1;
		unvaccinatedInfectedNum=1; 
		oneShotInfectedNum=0; 
		twoShotInfectedNum=0;
		recoveredInfectedNum=0;
		counterDied=0;
		countTime=0;
		countTimeDead=0;
		countSeconds = 1;
		weekCount  = 2;
		numDiedUnvac = 0;
		numDiedFullyVac = 0;
		numDiedPartVac = 0;
		numDiedRecovered = 0;
		
		//enable buttons
		ps.getStart().setText("START");
		ps.getStop().setEnabled(false);
		ps.getPlusPopSize100().setEnabled(true);
		ps.getPlusPopSize500().setEnabled(true);
		ps.getMinusPopSize100().setEnabled(true);
		ps.getMinusPopSize500().setEnabled(true);
		ps.getPlusFullyVac().setEnabled(true);
		ps.getPlusPartVac().setEnabled(true);
		ps.getPlusRecovered().setEnabled(true);
		ps.getMinusFullyVac().setEnabled(true);
		ps.getMinusPartVac().setEnabled(true);
		ps.getMinusRecovered().setEnabled(true);
		ps.getPopReset().setEnabled(true);
		ps.getPercReset().setEnabled(true);
	}
	public void setPauseTimer()
	{
		time.stop();
		new PandemicResults(this, ps.getButtonHandler().getPopSize());
	}
	public void setResumeTimer()
	{
		time.start();	
	}
	
	
	//OVER-RIDE the JPanel's paintComponent() method
	public void paintComponent(Graphics g)//The Graphics object 'g' is your paint brush
	{
		super.paintComponent(g);
		g.setColor(Color.PINK);
		for(int i = 0; i < personList.length; i++)
		{//get the color
			g.setColor(personList[i].getColor());
			g.fillOval(personList[i].getxCoord(), personList[i].getyCoord(),  personList[i].getDiameter(), personList[i].getDiameter());
		}//draw a circle shape
	}//end paintComponent over-ride
	
	public class BounceListener implements ActionListener //INNER CLASS GOES HERE
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			
			if (countTime == countSeconds * 1000) // time left
			{
				ps.getCountDownNum().setText(' ' + Integer.toString(90 - countSeconds) + 's');
				++countSeconds;
				
				if (countTime == 30000 || countTime == 60000) //  current week
				{
					ps.getWeekNum().setText(' ' + Integer.toString(weekCount));
					++weekCount;
				}
			}
			
			if(countTime==90000) //the simulation will finish
			{
				setStopTimer();
				System.out.println("Timer stopped");		
			}
			else
			{
				for(int i = 0; i < personList.length; i++)
				{
					if(personList[i].isAlive())
					{
						calcPosition(personList[i]);
					}
					else  if(!personList[i].isAlive())
					{
						personList[i].setyIncrement(0);
						personList[i].setxIncrement(0);
						personList[i].setColor(Color.BLACK);
					}
				}
				
				int deltaX;//difference in pixels of the x coordinates of the two persons being compared.
				int deltaY;//difference in pixels of the y coordinates of the two persons being compared.
				int firstPersonX,  firstPersonY, secondPersonX, secondPersonY;
				//outer loop gets the firstPerson of the pair and its coordinates.
				for(int i = 0; i < personList.length -1; i++)//LCC to length-1 to avoid out of bounds
				{//get the x and y co-ords of  first Person of the pair
					firstPersonX = personList[i].getxCoord();
					firstPersonY = personList[i].getyCoord();
					
					//Inner loop gets the second person of the pair , start inner loop counter at i+1 so we don't compare the first person to itself.
					for(int j = i+1; j < personList.length; j++)
					{
						secondPersonX = personList[j].getxCoord();
						secondPersonY = personList[j].getyCoord();
						deltaX = firstPersonX - secondPersonX;  //now calculate deltaX and deltaY for the pair of person
						deltaY = firstPersonY - secondPersonY;
							  
						if(Math.sqrt(deltaX *deltaX + deltaY * deltaY) <= IMG_DIM)//if true, they have touched
						{
								personList[i].setxIncrement(personList[i].getxIncrement() * -1);
								personList[i].setyIncrement(personList[i].getyIncrement() * -1);
								
								//now do the second
								personList[j].setxIncrement(personList[j].getxIncrement() * -1);
								personList[j].setyIncrement(personList[j].getyIncrement() * -1);
								
								int firstPersonnewxIncrement = (int)(Math.random()*11 - 5);
								int firstPersonnewyIncrement = (int)(Math.random()*11 - 5);
								int secondPersonnewxIncrement = (int)(Math.random()*11 - 5);
								int secondPersonnewyIncrement = (int)(Math.random()*11 - 5);

								//this will prevent from "getting stuck" on the borders.
								personList[i].setxIncrement(firstPersonnewxIncrement);
								personList[i].setyIncrement(firstPersonnewyIncrement);
								personList[j].setxIncrement(secondPersonnewxIncrement);
								personList[j].setyIncrement(secondPersonnewyIncrement);
						
								//set Properties of Person after collision
								setProperties(personList[j],personList[i],1);
								setProperties(personList[j],personList[i],2);
								setProperties(personList[j],personList[i],3);
								setProperties(personList[j],personList[i],4);
						
						}//end if CALCULATION
						
					}//end inner for				
				}//end outer loop
			}									
			//call repaint(), which in turn calls paintComponent() 
			repaint();
			//System.out.println("Timer is ticking");
			countTime+=LAG_TIME;
			countTimeDead++;
		}//end method
	}//end inner class
	
	public void calcPosition(Person person)
	{
		if(person.getxCoord() >= WIDTH - person.getDiameter() )
		{
			//we are at right side, so change xIncrement to a negative
			person.setxIncrement(person.getxIncrement() * -1);
		}
		if(person.getxCoord() <= 0)//changed operator to <=
		{
			//if true, we're at left edge, flip the flag
			person.setxIncrement(person.getxIncrement() * -1);;
		}
		if(person.getyCoord() >= HEIGHT - person.getDiameter() )
		{
			person.setyIncrement(person.getyIncrement() * -1);
		}
		if(person.getyCoord() <= 0)
		{
			//if true, we're at left edge, flip the flag
			person.setyIncrement(person.getyIncrement() * -1);;
		}
		person.setxCoord(person.getxCoord() + person.getxIncrement()); //adjust the person positions using the getters and setters
		person.setyCoord(person.getyCoord() + person.getyIncrement());
		
	}//end calcPosition
	
	//probability of getting infected
	boolean infectedProb(int immStat){
		boolean returnB=false;
		double prob=Math.random();
		if(immStat==1)
		{
			  if(prob <= 0.8) returnB= true;  //probability 0.8
			  else returnB= false;  //probability 0.2
		}
		else if(immStat==2)
		{
			  if(prob <= 0.4) returnB= true;  //probability 0.4
			  else returnB= false;  //probability 0.6
		}
		else if(immStat==3 || immStat==4 || immStat==5)
		{
			  if(prob <= 0.1) returnB=true;  //probability 0.1
			  else returnB=false;  //probability 0.9
		}
	 return returnB;
	}
	
	//probability of dying
	boolean dyingProb(int numOfProb){
		boolean returnB=false;
		double prob=Math.random();
		if(numOfProb==1)
		{
			  if(prob <= 0.1) returnB= true;  //probability 0.1
			  else returnB= false;  
		}
		else if(numOfProb==2)
		{
			  if(prob <= 0.05) returnB= true;  //probability 0.05
			  else returnB= false;  
		}
		else if(numOfProb==3)
		{
			  if(prob <= 0.01) returnB=true;  //probability 0.01
			  else returnB=false; 
		}
		else if(numOfProb==4)
		{
			  if(prob <= 0.003) returnB=true;  //probability 0.003
			  else returnB=false; 
		}
	 return returnB;
	}
	
	
	//set Properties of Person after collision
	public void setProperties(Person person1, Person person2, int immStatus)
	{
	    if(person1.getImmunityStatus()==immStatus||person2.getImmunityStatus()==immStatus)
	    {
	        if(person2.isInfected() &&(!person1.isInfected())) //only change the properties if the other person is not yet infected, otherwise don't change anything
	        {
	            person1.setInfected(infectedProb(immStatus));

	            if(person1.isInfected()&&person1.isAlive()) //if it returns true from the infectedProb, add infected count and change color to red and increment cycleCounter
	            {
	                person1.setColor(Color.RED);
	                
	                if (!person1.isPreviouslyInfected())
	                {	                	
	                	totalInfectedNum++; ps.getNumInfected().setText(String.valueOf(totalInfectedNum));
	                	switch(person1.getImmunityStatus())
		                {
			                case 1:
		               			unvaccinatedInfectedNum++; ps.getNumNonVacInfected().setText(String.valueOf(unvaccinatedInfectedNum)); 
					           	break;
					           	case 2:
					           		oneShotInfectedNum++; ps.getNumPartVacInfected().setText(String.valueOf(oneShotInfectedNum));
					           	break;
					           	case 3:
					           		twoShotInfectedNum++; ps.getNumFullyVacInfected().setText(String.valueOf(twoShotInfectedNum));
					           	break;
		                 }
	                }
	                person1.setCycleCounter(person1.getCycleCounter()+countTimeDead);
	                System.out.println(person1.getCycleCounter());
	                   if(person1.getCycleCounter()>=150&&person1.isAlive()) //if the cycleCounter is already 30000 or 150 cycles, get the chance of dying
	                   {
	                       System.out.println("stopped infecting");
	                       person1.setAlive(!dyingProb(immStatus)); //if the dyingProb returns true, set to setAlive() to false, then change color to black
	                       if(!person1.isAlive()&&person2.isAlive()) 
	                       {
	                      	 	switch(person1.getImmunityStatus())
	                      	 	{
	                      	 	case 1:
	                      	 		++numDiedUnvac;
	                      	 		break;
	                      	 	case 2:
	                      	 		++numDiedPartVac;
	                      	 		break;
	                      	 	case 3:
	                      	 		++numDiedFullyVac;
	                      	 		break;
	                      	 	case 4:
	                      	 		++numDiedRecovered;
	                      	 		break;
	                      	 	}
	                           counterDied++;    ps.getNumDied().setText(String.valueOf(counterDied));
	                           person1.setColor(Color.BLACK);
	                           person1.setInfected(false);
	                           //stop moving
	                           person1.setxIncrement(0);
	                           person1.setyIncrement(0);
	                       }
	                       else if(person1.isAlive()&&person2.isAlive())
                         {
	                      	 if(!person1.isPreviouslyInfected())
                        		 recoveredInfectedNum++; ps.getNumRecovered().setText(String.valueOf(recoveredInfectedNum));
                        	 person1.setColor(Color.GREEN);
                        	 person1.setInfected(false);
                        	 person1.setImmunityStatus(4);
                        	 person1.setPreviouslyInfected(true);
                         }
	                   }
	            }//end if person1.isInfected()&&person1.isAlive()
	        }
	        else if(person1.isInfected() && (!person2.isInfected()))
	        {
	            person2.setInfected(infectedProb(immStatus));
	            if(person2.isInfected()&&person2.isAlive())
	            {
	                   person2.setColor(Color.RED);
	                   
	                   
	                   if (!person2.isPreviouslyInfected())
	                   {
	                  	 totalInfectedNum++; ps.getNumInfected().setText(String.valueOf(totalInfectedNum)); 
	                  	 
	                  	 switch(person2.getImmunityStatus())
	 		                {
	                    		case 1:
	                    			unvaccinatedInfectedNum++; ps.getNumNonVacInfected().setText(String.valueOf(unvaccinatedInfectedNum)); 
		 					           	break;
		 					           	case 2:
		 					           		oneShotInfectedNum++; ps.getNumPartVacInfected().setText(String.valueOf(oneShotInfectedNum));
		 					           	break;
		 					           	case 3:
		 					           		twoShotInfectedNum++; ps.getNumFullyVacInfected().setText(String.valueOf(twoShotInfectedNum));
		 					           	break;				           	
	 		                 }
	                   }	                   
	                    person2.setCycleCounter(person2.getCycleCounter()+countTimeDead);
	                    System.out.println(person2.getCycleCounter());
	                       if(person2.getCycleCounter()>=150&&person2.isAlive()) //if the cycleCounter is already 30000 or 150 cycles, get the chance of dying
	                       {
	                           person2.setAlive(!dyingProb(immStatus)); //if the dyingProb returns true, set to setAlive() to false, then change color to black
	                           if(!person2.isAlive()&&person1.isAlive()) 
	                           {
	                          	 switch(person1.getImmunityStatus())
	 	                      	 	{
	 	                      	 	case 1:
	 	                      	 		++numDiedUnvac;
	 	                      	 		break;
	 	                      	 	case 2:
	 	                      	 		++numDiedPartVac;
	 	                      	 		break;
	 	                      	 	case 3:
	 	                      	 		++numDiedFullyVac;
	 	                      	 		break;
	 	                      	 	case 4:
	 	                      	 		++numDiedRecovered;
	 	                      	 		break;
	 	                      	 	}	                          	 
                               counterDied++; ps.getNumDied().setText(String.valueOf(counterDied));
                               person2.setColor(Color.BLACK);
                              person2.setInfected(false);
                             //stop moving
                               person2.setxIncrement(0);
                               person2.setyIncrement(0);
	                           }
	                           else if (person2.isAlive()&&person1.isAlive())
	                           {
	                          	 if(!person2.isPreviouslyInfected())
	                          		 recoveredInfectedNum++; ps.getNumRecovered().setText(String.valueOf(recoveredInfectedNum));
	                          	 person2.setColor(Color.GREEN);
	                          	 person2.setInfected(false);
	                          	 person2.setImmunityStatus(4);
	                          	 person2.setPreviouslyInfected(true);
	                           }
	                       }
	            }//end if person2.isInfected()&&person2.isAlive()
	        }
	    }
	}
	
	public static void main(String[] args)
	{
		// create a JFrame to hold the JPanel
		JFrame frame = new JFrame("Pandemic Simulator Application");
		//boilerplate
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new FlowLayout() );//ANONYMOUS object
		frame.setSize(1200,1000);
		frame.setLocationRelativeTo(null);
	
		ps=new PandemicSimulator();
	}//end main

}//end class
