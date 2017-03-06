package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.*;

public class StatusViewport extends JPanel {

	private int width;
	private int height;
	
	private JTextArea unitArea;
	private JTextArea structureArea;
	private JLabel currentMode, currentInstance,currentType,currentInstruction;
	
	public StatusViewport(int width, int height) {
		
		this.setSize(width, height);
		this.width = width;
		this.height = height;
		this.setPreferredSize(new Dimension(width,height));
		this.setMaximumSize(new Dimension(width,height));
		
		unitArea = new JTextArea();
		unitArea.setEditable(false);
		structureArea = new JTextArea(); 
		structureArea.setEditable(false);
		
		currentMode = new JLabel("CURRENT MODE= "); 
		currentMode.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		currentInstance = new JLabel("CURRENT INSTANCE= "); 
		currentInstance.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		currentType = new JLabel("CURRENT TYPE= ");
		currentType.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		currentInstruction = new JLabel("CURRENT INSTRUCTION= ");
		currentInstruction.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		displayView();
	}
	
	public void displayView() {
		
		FixedPanel unitPanel = new FixedPanel(width/2,height/2);
		unitPanel.setLayout(new BoxLayout(unitPanel,BoxLayout.Y_AXIS));
		
		unitPanel.add(new JLabel("Unit Status Port",JLabel.CENTER));
		unitPanel.add(new JScrollPane(unitArea));
		
		FixedPanel structurePanel = new FixedPanel(width/2,height/2);
		structurePanel.setLayout(new BoxLayout(structurePanel,BoxLayout.Y_AXIS));
		
		structurePanel.add(new JLabel("Structure Status Port",JLabel.CENTER)); 
		structurePanel.add(new JScrollPane(structureArea));
		
		JPanel mixedPanel = new JPanel();
		mixedPanel.setLayout(new BoxLayout(mixedPanel,BoxLayout.X_AXIS));
		
		mixedPanel.add(Box.createHorizontalStrut(10));
		mixedPanel.add(unitPanel);
		mixedPanel.add(Box.createHorizontalStrut(10));
		mixedPanel.add(structurePanel);
		mixedPanel.add(Box.createHorizontalStrut(10));
		
		JLabel title = new JLabel("Status ViewPort");
		title.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		
		this.add(title);
		this.add(mixedPanel);
		this.add(currentMode);
		this.add(currentType);
		this.add(currentInstance);
		this.add(currentInstruction);
		this.setBackground(Color.orange);
	}
	
}
