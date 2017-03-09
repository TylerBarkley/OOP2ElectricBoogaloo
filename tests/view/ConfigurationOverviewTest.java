package view;

import javax.swing.JFrame;

import control.UserControls;

public class ConfigurationOverviewTest {
	
	//NOT a JUnit Test. MUST TEST MANUALLY
	public static void main(String[] args){
		JFrame frame=new JFrame();
		frame.setSize(800, 600);
		frame.add(new ConfigurationOverview(new UserControls(), 800, 600));
		frame.setVisible(true);
	}
}
