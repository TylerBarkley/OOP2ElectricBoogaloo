package view;

import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JScrollPane;

public class FixedScrollPane extends JScrollPane {

	private int width, height;
	
	public FixedScrollPane(Component view,int width, int height) {
		super(view);
		this.width = width;
		this.height = height;
		
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(width,height);
	}
	
	public Dimension getMaximumSize() {
		return new Dimension(super.getMaximumSize().width,height);
	}
}
