package view;

import java.awt.Dimension;

import javax.swing.JPanel;

public class FixedPanel extends JPanel {

	int width;
	int height;
	
	public FixedPanel(int width, int height) {
		
		this.width = width;
		this.height = height; 
		
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(width,height);
	}
	
	public Dimension getMaximumSize() {
		return getPreferredSize();
	}
}
