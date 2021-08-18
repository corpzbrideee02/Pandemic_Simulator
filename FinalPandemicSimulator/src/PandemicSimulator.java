/**
 * Program Name: PandemicSimulator.java
 * Purpose: This is the GUI class for PandemicSimulator. It builds the app when we run it.
 * Coder: Brittany Diesbourg (section 4) + Dianne Corpuz (section 2)
 * Date: Jul. 29, 2021
 */

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.*;
import java.awt.*;

public class PandemicSimulator extends JFrame
{ 
	private JPanel inputMenu, outputMenu, colorLegend, plusMinusPopSize, popSize100,popSize500, plusMinusFullyVac, plusMinusPartVac, plusMinusRecovered, buttonMenu, inputContainer, startStop, aboutDev, popSizeContainer, countDownPanel;
	private JLabel popSizeL, popSize, numInfectedL, numNonVacInfectedL, numPartVacInfectedL, numFullyVacInfectedL, numRecoveredL, numDiedL, numInfected, numNonVacInfected, numPartVacInfected, numFullyVacInfected, numRecovered, numDied;
	private JLabel percInfected, percPartVacL, percFullyVacL, percPartVac, percFullyVac, percRecoveredL, percRecovered, percDied, totalPercL, totalPercNumL, infectedL, deadL, recoveredL, noImmunityL, oneShotL, twoShotL; // L = label
	private JLabel countDownL, countDownNum, weekL, weekNum, redDot, blackDot, greenDot, blueDot, cyanDot, yellowDot; // dots for colorLegend
	private JButton popReset, percReset, about, devs, start, stop, plusPopSize100, plusPopSize500, minusPopSize100, minusPopSize500, plusFullyVac, minusFullyVac, plusPartVac, minusPartVac, plusRecovered, minusRecovered;
	
	private PandemicSimButtonHandler e = new PandemicSimButtonHandler(this);

	// constructor
	PandemicSimulator()
	{
		super("Pandemic Simulator");
		this.setSize(1130, 850);
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());
		this.setResizable(false);// maximize button disable
		
		inputContainer = new JPanel(new GridLayout(5,1));
		
		Color myColor = new Color(227, 255, 235); 
		//this.setBackground(myColor);
		inputContainer.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		popSizeContainer = new JPanel(new GridLayout(1,2));
		popSizeContainer.setBorder(new EmptyBorder(0, 0, 30, 0));
		
		inputMenu = new JPanel(new GridLayout(5, 2));

		inputMenu.setBackground(myColor);
		popSizeContainer.setBackground(myColor);
		
		popSizeL = new JLabel("Population Size: ");
		popSizeL.setFont(new Font("Sans Serif", Font.BOLD, 15));
		popSizeContainer.add(popSizeL);		
		plusMinusPopSize = new JPanel(new GridLayout(3,1));
		popSize100 = new JPanel(new GridLayout(1,2));
		popSize500 = new JPanel(new GridLayout(1,2));		
		minusPopSize100 = new JButton("-100");
		minusPopSize100.addActionListener(e);
		plusPopSize100 = new JButton("+100");
		plusPopSize100.addActionListener(e);
		popSize = new JLabel("0");
		popSize.setFont(new Font("Sans Serif", Font.BOLD, 15));
		popSize.setHorizontalAlignment(JLabel.CENTER);	
		minusPopSize500 = new JButton("-500");
		minusPopSize500.addActionListener(e);
		plusPopSize500 = new JButton("+500");
		plusPopSize500.addActionListener(e);
		popSize100.add(minusPopSize100);
		popSize100.add(plusPopSize100);
		plusMinusPopSize.add(popSize100);
		plusMinusPopSize.add(popSize);
		popSize500.add(minusPopSize500);
		popSize500.add(plusPopSize500);
		plusMinusPopSize.add(popSize500);
		popSizeContainer.add(plusMinusPopSize);
		
		percFullyVacL = new JLabel("% Fully Vaccinated: ");
		inputMenu.add(percFullyVacL);
		plusMinusFullyVac = new JPanel(new GridLayout(1,3));
		minusFullyVac = new JButton("-");
		minusFullyVac.addActionListener(e);
		plusMinusFullyVac.add(minusFullyVac);		
		percFullyVac = new JLabel("0%");
		percFullyVac.setHorizontalAlignment(JLabel.CENTER);
		plusMinusFullyVac.add(percFullyVac);
		plusFullyVac = new JButton("+");
		plusFullyVac.addActionListener(e);
		plusMinusFullyVac.add(plusFullyVac);	
		inputMenu.add(plusMinusFullyVac);
				
		percPartVacL = new JLabel("% Partly Vaccinated: ");
		inputMenu.add(percPartVacL);
		plusMinusPartVac = new JPanel(new GridLayout(1,3));
		minusPartVac = new JButton("-");
		minusPartVac.addActionListener(e);
		plusMinusPartVac.add(minusPartVac);		
		percPartVac = new JLabel("0%");
		percPartVac.setHorizontalAlignment(JLabel.CENTER);
		plusMinusPartVac.add(percPartVac);
		plusPartVac = new JButton("+");
		plusPartVac.addActionListener(e);
		plusMinusPartVac.add(plusPartVac);		
		inputMenu.add(plusMinusPartVac);
		
		percRecoveredL = new JLabel("% Recovered: ");
		inputMenu.add(percRecoveredL);
		plusMinusRecovered = new JPanel(new GridLayout(1,3));
		minusRecovered = new JButton("-");
		minusRecovered.addActionListener(e);
		plusMinusRecovered.add(minusRecovered);		
		percRecovered = new JLabel("0%");
		percRecovered.setHorizontalAlignment(JLabel.CENTER);
		plusMinusRecovered.add(percRecovered);
		plusRecovered = new JButton("+");
		plusRecovered.addActionListener(e);
		plusMinusRecovered.add(plusRecovered);		
		inputMenu.add(plusMinusRecovered);		
		
		totalPercL = new JLabel("% Unvaccinated: ");
		totalPercL.setFont(new Font("Sans Serif", Font.BOLD, 13));
		inputMenu.add(totalPercL);
		totalPercNumL = new JLabel("100%");
		totalPercNumL.setFont(new Font("Sans Serif", Font.BOLD, 13));
		totalPercNumL.setHorizontalAlignment(JLabel.CENTER);
		inputMenu.add(totalPercNumL);
		
		popReset = new JButton("RESET POP. SIZE");
		popReset.addActionListener(e);
		inputMenu.add(popReset);
		percReset = new JButton("RESET % INPUT");
		percReset.addActionListener(e);
		inputMenu.add(percReset);
		
		buttonMenu = new JPanel(new GridLayout(3,1));		
		
		startStop = new JPanel(new GridLayout(1,3));
		start = new JButton("START");
		start.addActionListener(e);
		start.setEnabled(false);
		startStop.add(start);		
		stop = new JButton("STOP");
		stop.addActionListener(e);
		stop.setEnabled(false);
		startStop.add(stop);
		buttonMenu.add(startStop);
		
		//countdown clock
		countDownL = new JLabel("Time left: ");
		countDownL.setFont(new Font("Sans Serif", Font.BOLD, 15));
		countDownL.setHorizontalAlignment(JLabel.RIGHT);
		countDownNum = new JLabel(" 90s");
		countDownNum.setFont(new Font("Sans Serif", Font.BOLD, 15));
		weekL = new JLabel("Week: ");
		weekL.setFont(new Font("Sans Serif", Font.BOLD, 15));
		weekL.setHorizontalAlignment(JLabel.RIGHT);
		weekNum = new JLabel(" 1");
		weekNum.setFont(new Font("Sans Serif", Font.BOLD, 15));
		countDownPanel = new JPanel(new GridLayout(1,4));
		countDownPanel.add(weekL);
		countDownPanel.add(weekNum);
		countDownPanel.add(countDownL);
		countDownPanel.add(countDownNum);
		countDownPanel.setBackground(myColor);
		buttonMenu.add(countDownPanel);
		buttonMenu.setBackground(myColor);
		colorLegend = new JPanel(new GridLayout(6,2));
		
		infectedL = new JLabel("  Infected");
		infectedL.setFont(new Font("Sans Serif", Font.BOLD, 15));
		redDot = new JLabel("\u25CF ");
		redDot.setForeground(Color.RED);
		redDot.setHorizontalAlignment(JLabel.RIGHT);		
		colorLegend.add(redDot);
		colorLegend.add(infectedL);
		
		deadL = new JLabel("  Dead");
		deadL.setFont(new Font("Sans Serif", Font.BOLD, 15));
		blackDot = new JLabel("\u25CF ");
		blackDot.setHorizontalAlignment(JLabel.RIGHT);
		colorLegend.add(blackDot);
		colorLegend.add(deadL);
		
		recoveredL = new JLabel("  Recovered");
		recoveredL.setFont(new Font("Sans Serif", Font.BOLD, 15));
		greenDot = new JLabel("\u25CF ");
		greenDot.setForeground(Color.GREEN);
		greenDot.setHorizontalAlignment(JLabel.RIGHT);
		colorLegend.add(greenDot);
		colorLegend.add(recoveredL);
		
		noImmunityL = new JLabel("  No Immunity");
		noImmunityL.setFont(new Font("Sans Serif", Font.BOLD, 15));
		blueDot = new JLabel("\u25CF ");
		blueDot.setForeground(Color.BLUE);
		blueDot.setHorizontalAlignment(JLabel.RIGHT);
		colorLegend.add(blueDot);
		colorLegend.add(noImmunityL);
		
		oneShotL = new JLabel ("  One Shot Immunity");
		oneShotL.setFont(new Font("Sans Serif", Font.BOLD, 15));
		cyanDot = new JLabel("\u25CF ");
		cyanDot.setForeground(Color.CYAN);
		cyanDot.setHorizontalAlignment(JLabel.RIGHT);
		colorLegend.add(cyanDot);
		colorLegend.add(oneShotL);
		
		twoShotL = new JLabel("  Two Shot Immunity");
		twoShotL.setFont(new Font("Sans Serif", Font.BOLD, 15));
		yellowDot = new JLabel("\u25CF ");
		yellowDot.setForeground(Color.YELLOW);
		yellowDot.setHorizontalAlignment(JLabel.RIGHT);	
		colorLegend.add(yellowDot);
		colorLegend.add(twoShotL);
		colorLegend.setBorder(new EmptyBorder(0, -80, 0, 60));
		
		colorLegend.setBackground(myColor);
		
		aboutDev = new JPanel(new GridLayout(2,1));
		aboutDev.setBorder(new EmptyBorder(90, 0, 0, 0));
		about = new JButton("About this App");
		about.addActionListener(e);
		aboutDev.add(about);
		devs = new JButton("Meet the PanDEVics!");
		devs.addActionListener(e);
		aboutDev.add(devs);
		aboutDev.setBackground(myColor);
		
		inputContainer.add(popSizeContainer);
		inputContainer.add(inputMenu);
		inputContainer.add(buttonMenu);
		inputContainer.add(colorLegend);
		inputContainer.add(aboutDev);
				
		outputMenu = new JPanel(new GridLayout(3,4));
		outputMenu.setBorder(new EmptyBorder(20, 20, 20, 20));
		
		numInfectedL = new JLabel("Total # of people infected: ");
		numInfectedL.setFont(new Font("Sans Serif", Font.BOLD, 13));
		outputMenu.add(numInfectedL);
		numInfected = new JLabel("0");
		numInfected.setFont(new Font("Sans Serif", Font.BOLD, 13));
		numInfected.setHorizontalAlignment(JLabel.CENTER);
		outputMenu.add(numInfected);
		
		numNonVacInfectedL = new JLabel("# of non-vaccinated people infected: ");
		numNonVacInfectedL.setFont(new Font("Sans Serif", Font.BOLD, 13));
		outputMenu.add(numNonVacInfectedL);
		numNonVacInfected = new JLabel("0");
		numNonVacInfected.setFont(new Font("Sans Serif", Font.BOLD, 13));
		numNonVacInfected.setHorizontalAlignment(JLabel.CENTER);
		outputMenu.add(numNonVacInfected);
		
		numPartVacInfectedL = new JLabel("# of partially-vaccinated people infected: ");
		numPartVacInfectedL.setFont(new Font("Sans Serif", Font.BOLD, 13));
		outputMenu.add(numPartVacInfectedL);
		numPartVacInfected = new JLabel("0");
		numPartVacInfected.setFont(new Font("Sans Serif", Font.BOLD, 13));
		numPartVacInfected.setHorizontalAlignment(JLabel.CENTER);
		outputMenu.add(numPartVacInfected);
		
		numFullyVacInfectedL = new JLabel("# of fully-vaccinated people infected: ");
		numFullyVacInfectedL.setFont(new Font("Sans Serif", Font.BOLD, 13));
		outputMenu.add(numFullyVacInfectedL);
		numFullyVacInfected = new JLabel("0");
		numFullyVacInfected.setFont(new Font("Sans Serif", Font.BOLD, 13));
		numFullyVacInfected.setHorizontalAlignment(JLabel.CENTER);
		outputMenu.add(numFullyVacInfected);
		
		numRecoveredL = new JLabel("# of infected people who have recovered: ");
		numRecoveredL.setFont(new Font("Sans Serif", Font.BOLD, 13));
		outputMenu.add(numRecoveredL);
		numRecovered = new JLabel("0");
		numRecovered.setFont(new Font("Sans Serif", Font.BOLD, 13));
		numRecovered.setHorizontalAlignment(JLabel.CENTER);
		outputMenu.add(numRecovered);
		
		numDiedL = new JLabel("# of people who have died: ");
		numDiedL.setFont(new Font("Sans Serif", Font.BOLD, 13));
		outputMenu.add(numDiedL);
		numDied = new JLabel("0");
		numDied.setFont(new Font("Sans Serif", Font.BOLD, 13));
		numDied.setHorizontalAlignment(JLabel.CENTER);
		outputMenu.add(numDied);
		
		//Color myColor2= new Color(195,235,208);
		 
		 plusMinusPartVac.setBackground(myColor);
		 plusMinusRecovered.setBackground(myColor);
		plusMinusFullyVac.setBackground(myColor);
		
		inputContainer.setBackground(myColor);
		outputMenu.setBackground(new Color(227,255,245));
		this.add(inputContainer, BorderLayout.EAST);
		this.add(outputMenu, BorderLayout.SOUTH);
		
		this.setVisible(true);	
		
		
	}
	
	// getters for buttons and labels
	public JButton getPlusPopSize100()
	{
		return plusPopSize100;
	}
	public JButton getPlusPopSize500()
	{
		return plusPopSize500;
	}
	public JButton getMinusPopSize100()
	{
		return minusPopSize100;
	}
	public JButton getMinusPopSize500()
	{
		return minusPopSize500;
	}
	public JLabel getNumInfected()
	{
		return numInfected;
	}
	public JLabel getNumNonVacInfected()
	{
		return numNonVacInfected;
	}
	public JLabel getNumPartVacInfected()
	{
		return numPartVacInfected;
	}
	public JLabel getNumFullyVacInfected()
	{
		return numFullyVacInfected;
	}
	public JLabel getNumRecovered()
	{
		return numRecovered;
	}
	public JLabel getNumDied()
	{
		return numDied;
	}
	public JLabel getPercInfected()
	{
		return percInfected;
	}
	public JLabel getPercPartVac()
	{
		return percPartVac;
	}
	public JLabel getPercFullyVac()
	{
		return percFullyVac;
	}
	public JLabel getPercRecovered()
	{
		return percRecovered;
	}
	public JLabel getPercDied()
	{
		return percDied;
	}
	public JLabel getPopSize()
	{
		return popSize;
	}
	public JButton getPopReset()
	{
		return popReset;
	}
	public JButton getPercReset()
	{
		return percReset;
	}
	public JButton getAbout()
	{
		return about;
	}
	public JButton getDevs()
	{
		return devs;
	}
	public JButton getStart()
	{
		return start;
	}
	public JButton getStop()
	{
		return stop;
	}
	public JButton getPlusFullyVac()
	{
		return plusFullyVac;
	}
	public JButton getMinusFullyVac()
	{
		return minusFullyVac;
	}
	public JButton getPlusPartVac()
	{
		return plusPartVac;
	}
	public JButton getMinusPartVac()
	{
		return minusPartVac;
	}
	public JButton getPlusRecovered()
	{
		return plusRecovered;
	}
	public JButton getMinusRecovered()
	{
		return minusRecovered;
	}
	public JLabel getTotalPercNumL()
	{
		return totalPercNumL;
	}
	public JLabel getCountDownNum()
	{
		return countDownNum;
	}
	public JLabel getWeekNum()
	{
		return weekNum;
	}
	public PandemicSimButtonHandler getButtonHandler()
	{
		return e;
	}
	
	public PandemicMain setPandemicMain(PandemicMain m) {

			e.setMain();
			this.add(e.getMain(), BorderLayout.CENTER);
			//set the total infected and ASSUMED immunityStatus of the first person (should include 1 person infected at the start)
			numInfected.setText(String.valueOf(e.getMain().totalInfectedNum));
			numNonVacInfected.setText(String.valueOf(e.getMain().unvaccinatedInfectedNum));
			numPartVacInfected.setText(String.valueOf(e.getMain().oneShotInfectedNum));
			numFullyVacInfected.setText(String.valueOf(e.getMain().twoShotInfectedNum));
			numRecovered.setText(String.valueOf(e.getMain().recoveredInfectedNum));
			numDied.setText(String.valueOf(e.getMain().counterDied));
			

		return m;
	}	
}
//end class