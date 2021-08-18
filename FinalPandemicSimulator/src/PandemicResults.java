/**
 * Program Name: PandemicResults.java
 * Purpose: This displays a panel with the percentage results from the Pandemic Simulator.
 * Coder: Brittany Diesbourg (section 4) + Dianne Corpuz (section 2)
 * Date: Aug. 7, 2021
 */

import java.awt.*;
import java.text.DecimalFormat;
import javax.swing.*;

public class PandemicResults extends JFrame
{
	private JLabel percTotalL, percTotal, percTotalDeadL, percTotalDead, percUnvacL, percUnvac, percUnvacDeadL, percUnvacDead, percPartVacL, percPartVac,percPartVacDeadL, percPartVacDead;
	private JLabel percFullyVacL, percFullyVac, percFullyVacDeadL, percFullyVacDead, percRecoveredL, percRecovered, percRecoveredDeadL, percRecoveredDead;
	private double divideBy = 0, percTotalNum;
	private PandemicMain pm;
	
	public PandemicResults(PandemicMain pm, int popSize)
	{		
		super("Pandemic Simulator Results");
		this.setSize(500, 300);
		this.setLocationRelativeTo(null);
		this.setLayout(new GridLayout(10,2));
		this.setResizable(false);// maximize button disable
		this.pm = pm;
	
		divideBy = popSize / 100; // divide numbers by this to get percentage
		DecimalFormat df = new DecimalFormat("0.##");
		
		percTotalL = new JLabel("% of the total population infected:");
		percTotalL.setHorizontalAlignment(JLabel.RIGHT);	
		percTotal = new JLabel(df.format(pm.totalInfectedNum / divideBy) + "%");
		percTotal.setHorizontalAlignment(JLabel.CENTER);
		percTotalDeadL = new JLabel("Total % dead:");
		percTotalDeadL.setHorizontalAlignment(JLabel.RIGHT);
		percTotalDeadL.setForeground(Color.RED);
		percTotalDead = new JLabel(df.format(pm.counterDied / divideBy) + "%");
		percTotalDead.setHorizontalAlignment(JLabel.CENTER);
		percTotalDead.setForeground(Color.RED);
		
		percUnvacL = new JLabel("% of unvaccinated people infected:");
		percUnvacL.setHorizontalAlignment(JLabel.RIGHT);	
		percUnvac = new JLabel(df.format(pm.unvaccinatedInfectedNum / divideBy) + "%");
		percUnvac.setHorizontalAlignment(JLabel.CENTER);
		percUnvacDeadL = new JLabel("% unvaccinated dead:");
		percUnvacDeadL.setForeground(Color.RED);
		percUnvacDeadL.setHorizontalAlignment(JLabel.RIGHT);	
		percUnvacDead = new JLabel(df.format(pm.numDiedUnvac / divideBy) + "%");
		percUnvacDead.setHorizontalAlignment(JLabel.CENTER);
		percUnvacDead.setForeground(Color.RED);
		
		percPartVacL = new JLabel("% of partially-vaccinated people infected:");
		percPartVacL.setHorizontalAlignment(JLabel.RIGHT);	
		percPartVac = new JLabel((df.format(pm.oneShotInfectedNum / divideBy) + "%"));
		percPartVac.setHorizontalAlignment(JLabel.CENTER);
		percPartVacDeadL = new JLabel("% partially-vaccinated dead:");
		percPartVacDeadL.setForeground(Color.RED);
		percPartVacDeadL.setHorizontalAlignment(JLabel.RIGHT);	
		percPartVacDead = new JLabel(df.format(pm.numDiedPartVac / divideBy) + "%");
		percPartVacDead.setHorizontalAlignment(JLabel.CENTER);
		percPartVacDead.setForeground(Color.RED);
		
		percFullyVacL = new JLabel("% of fully-vaccinated people infected:");
		percFullyVacL.setHorizontalAlignment(JLabel.RIGHT);	
		percFullyVac = new JLabel(df.format(pm.twoShotInfectedNum / divideBy) + "%");
		percFullyVac.setHorizontalAlignment(JLabel.CENTER);
		percFullyVacDeadL = new JLabel("% fully-vaccinated dead:");
		percFullyVacDeadL.setForeground(Color.RED);
		percFullyVacDeadL.setHorizontalAlignment(JLabel.RIGHT);	
		percFullyVacDead = new JLabel(df.format(pm.numDiedFullyVac / divideBy) + "%");
		percFullyVacDead.setHorizontalAlignment(JLabel.CENTER);
		percFullyVacDead.setForeground(Color.RED);
		
		percRecoveredL = new JLabel("% of infected people that recovered:");
		percRecoveredL.setHorizontalAlignment(JLabel.RIGHT);	
		percRecovered = new JLabel(df.format(pm.recoveredInfectedNum / divideBy) + "%");
		percRecovered.setHorizontalAlignment(JLabel.CENTER);
		percRecoveredDeadL = new JLabel("% recovered dead:");
		percRecoveredDeadL.setForeground(Color.RED);
		percRecoveredDeadL.setHorizontalAlignment(JLabel.RIGHT);	
		percRecoveredDead = new JLabel(df.format(pm.numDiedRecovered / divideBy) + "%");
		percRecoveredDead.setHorizontalAlignment(JLabel.CENTER);
		percRecoveredDead.setForeground(Color.RED);
		
		this.add(percTotalL);
		this.add(percTotal);
		this.add(percTotalDeadL);
		this.add(percTotalDead);
		
		this.add(percUnvacL);
		this.add(percUnvac);
		this.add(percUnvacDeadL);
		this.add(percUnvacDead);
		
		this.add(percPartVacL);
		this.add(percPartVac);
		this.add(percPartVacDeadL);
		this.add(percPartVacDead);
		
		this.add(percFullyVacL);
		this.add(percFullyVac);
		this.add(percFullyVacDeadL);
		this.add(percFullyVacDead);
		
		this.add(percRecoveredL);
		this.add(percRecovered);
		this.add(percRecoveredDeadL);
		this.add(percRecoveredDead);
		
		this.setVisible(true);		
	}
}
//end class