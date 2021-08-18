/**
 * Program Name: PandemicSimButtonHandler.java
 * Purpose: This is action listener class for PandemicSimulator. It handles all the button functionality.
 * Coder: Brittany Diesbourg (section 4) + Dianne Corpuz (section 2)
 * Date: Aug. 2, 2021
 */

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.Timer;


public class PandemicSimButtonHandler implements ActionListener
{
	PandemicSimulator app;
	PandemicMain obj=null;
	
	private static final int ZERO = 0, FIVE = 5, ONE_HUNDRED = 100, FIVE_HUNDRED = 500; // declaring these as constants because they get used frequently
	private static int popSize = ZERO, numTotalInfected = ZERO, numNonVacInfected = ZERO, numPartVacInfected = ZERO, numFullyVacInfected = ZERO, numRecovered = ZERO, numDied = ZERO;
	private static int  percTotalInfected = ZERO, percUnvacResult = ZERO, percPartVacResult = ZERO, percFullyVacResult = ZERO, percRecoveredResult = ZERO, percDiedResult = ZERO, percFullyVac = ZERO, percPartVac = ZERO, percRecovered = ZERO, percInfected = ONE_HUNDRED;

	public PandemicSimButtonHandler(PandemicSimulator app)
	{
		this.app = app;
	}
	public PandemicMain setMain()
	{
		return obj=new PandemicMain(getRealSize(1),getRealSize(2),getRealSize(3),getRealSize(4),popSize); //call constructor to populate populationSize and immunityStatus
		
	}
	public PandemicMain getMain()
	{
		return obj;		
	}	
	public int getRealSize(int immS)
	{
		int percentage=0;
		 switch(immS)
         {
    		case 1:
    			percentage=percInfected;
           	break;
           	case 2:
           		percentage=percPartVac;
           	break;
           	case 3:
           		percentage=percFullyVac;
           	break;
        	case 4:
        		percentage=percRecovered;
           	break;
          }
		return (percentage*popSize)/ONE_HUNDRED;
	}

	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getActionCommand().equals("+100"))
		{
			popSize += ONE_HUNDRED;
			app.getPopSize().setText(Integer.toString(popSize));
		}
		else if (e.getActionCommand().equals("-100"))
		{
			if (popSize >= ONE_HUNDRED)
			{
				popSize -= ONE_HUNDRED;
				app.getPopSize().setText(Integer.toString(popSize));
			}
		}
		else if (e.getActionCommand().equals("+500"))
		{
			popSize += FIVE_HUNDRED;
			app.getPopSize().setText(Integer.toString(popSize));
		}
		else if (e.getActionCommand().equals("-500"))
		{
			if (popSize >= FIVE_HUNDRED)
			{
				popSize -= FIVE_HUNDRED;
				app.getPopSize().setText(Integer.toString(popSize));	
			}
			else
			{
				popSize = ZERO;
				app.getPopSize().setText(Integer.toString(popSize));
			}
		}
		else if (e.getSource() == app.getPlusFullyVac()) // + % Fully Vaccinated
		{
			if (percInfected > ZERO) 
			{
				percFullyVac += FIVE;
				percInfected -= FIVE;
				app.getPercFullyVac().setText(Integer.toString((int)percFullyVac) + "%");
				app.getTotalPercNumL().setText(Integer.toString((int)percInfected) + '%');
			}
		}
		else if (e.getSource() == app.getMinusFullyVac()) // - % Fully Vaccinated
		{
			if (percInfected < ONE_HUNDRED && percFullyVac > ZERO)
			{
				percFullyVac -= FIVE;
				percInfected += FIVE;
				app.getPercFullyVac().setText(Integer.toString((int)percFullyVac) + "%");
				app.getTotalPercNumL().setText(Integer.toString((int)percInfected) + '%');
			}
		}
		else if (e.getSource() == app.getPlusPartVac()) // + % Partly Vaccinated
		{
			if (percInfected > ZERO) 
			{
				percPartVac += FIVE;
				percInfected -= FIVE;
				app.getPercPartVac().setText(Integer.toString((int)percPartVac) + "%");
				app.getTotalPercNumL().setText(Integer.toString((int)percInfected) + '%');
			}
		}
		else if (e.getSource() == app.getMinusPartVac())  // - % Partly Vaccinated
		{
			if (percInfected < ONE_HUNDRED && percPartVac > ZERO)
			{
				percPartVac -= FIVE;
				percInfected += FIVE;
				app.getPercPartVac().setText(Integer.toString((int)percPartVac) + "%");
				app.getTotalPercNumL().setText(Integer.toString((int)percInfected) + '%');
			}
		}
		else if (e.getSource() == app.getPlusRecovered())  // + % Recovered
		{
			if (percInfected > ZERO) 
			{
				percRecovered += FIVE;
				percInfected -= FIVE;
				app.getPercRecovered().setText(Integer.toString((int)percRecovered) + "%");
				app.getTotalPercNumL().setText(Integer.toString((int)percInfected) + '%');
			}
		}
		else if (e.getSource() == app.getMinusRecovered()) // - % Recovered
		{
			if (percInfected < ONE_HUNDRED && percRecovered > ZERO)
			{
				percRecovered -= FIVE;
				percInfected += FIVE;
				app.getPercRecovered().setText(Integer.toString((int)percRecovered) + "%");
				app.getTotalPercNumL().setText(Integer.toString((int)percInfected) + '%');
			}
		}
		else if (e.getActionCommand().equals("START"))
		{
			app.setPandemicMain(obj);
			
			obj.setStartTimer();
			
			app.getStart().setText("PAUSE");
			app.getStop().setEnabled(true);
			app.getPlusPopSize100().setEnabled(false);
			app.getPlusPopSize500().setEnabled(false);
			app.getMinusPopSize100().setEnabled(false);
			app.getMinusPopSize500().setEnabled(false);
			app.getPlusFullyVac().setEnabled(false);
			app.getPlusPartVac().setEnabled(false);
			app.getPlusRecovered().setEnabled(false);
			app.getMinusFullyVac().setEnabled(false);
			app.getMinusPartVac().setEnabled(false);
			app.getMinusRecovered().setEnabled(false);
			app.getPopReset().setEnabled(false);
			app.getPercReset().setEnabled(false);
			
		}
		else if (e.getActionCommand().equals("PAUSE"))
		{
	
			app.getStart().setText("RESUME");
			obj.setPauseTimer();
			//new PandemicResults(app);
		}
		else if (e.getActionCommand().equals("RESUME"))
		{
			app.getStart().setText("PAUSE");
			obj.setResumeTimer();
		}
		else if (e.getActionCommand().equals("RESET POP. SIZE"))
		{
			popSize = ZERO;
			app.getPopSize().setText(Integer.toString(popSize));
			
			//reset PandemicMain
			//obj=null;
			
		}
		else if (e.getActionCommand().equals("RESET % INPUT"))
		{
			percInfected = ONE_HUNDRED;
			percFullyVac = ZERO;
			percPartVac = ZERO;
			percRecovered = ZERO;
			
			app.getPercFullyVac().setText(Integer.toString((int)percFullyVac) + "%");
			app.getPercPartVac().setText(Integer.toString((int)percPartVac) + "%");
			app.getPercRecovered().setText(Integer.toString((int)percRecovered) + "%");
			app.getTotalPercNumL().setText(Integer.toString((int)percInfected) + '%');
		}
		else if (e.getActionCommand().equals("STOP"))
		{
			obj.setStopTimer();
			
			//new PandemicResults(app);
		}
		else if (e.getActionCommand().equals("About this App"))
		{
			JOptionPane.showMessageDialog(null, "This app will allow the user to input certain parameters \nabout a population in terms of how many people are \nvaccinated or unvaccinated, and then run a simulation \nto see how quickly a disease will spread through \na population of a given size.\n\n\u00a9 PanDEVics 2021", e.getActionCommand(), JOptionPane.INFORMATION_MESSAGE);
		}
		else if (e.getActionCommand().equals("Meet the PanDEVics!"))
		{
			JOptionPane.showMessageDialog(null, "This app was created by \nthe PanDEVics\n(PANdemIC DEVelopers):\n\nBrittany Diesbourg\nSection 4\nStudent # 0920430\n\nDianne Corpuz\nSection 2\nStudent # 0955976\n\n", e.getActionCommand(), JOptionPane.INFORMATION_MESSAGE);
		}
		
		// enables/disables START depending on population size
		if (popSize < ONE_HUNDRED)
			app.getStart().setEnabled(false);
		else
			app.getStart().setEnabled(true);
		
	}
	//end main
	public static int getPopSize()
	{
		return popSize;
	}
}
//end class