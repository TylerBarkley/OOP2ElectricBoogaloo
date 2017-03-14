package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import control.Control;
import control.ControlLoader;
import control.KeyReader;
import control.UserControls;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ConfigurationOverview extends JPanel
{
	private JPanel controlPanel;
	private JPanel savePanel;
	private JTextField saveField;
	private ControlLoader controlLoader;
	
	public ConfigurationOverview(UserControls controls, int width, int height)
	{
		this.setSize(width, height);
		
		controlLoader = ControlLoader.getInstance();
		
		setLayout(new BorderLayout());
		setupControlsPanel(controls);
		setupSavePanel();
	}

	private void setupControlsPanel(UserControls controls){
		controlPanel = new JPanel();
		controlPanel.setLayout(new GridLayout(0, 4, 7, 1));
		
		for(Control c: controls.getAllControls())
		{
			addButton(c);
		}
		
		controlPanel.add(new JLabel(" "));
		add(controlPanel, BorderLayout.CENTER);
	}
	
	private void addButton(Control control)
	{
		String keyText=getDisplayText(control);
		JButton button=new JButton(keyText);
		
		KeyReader keyReader = new KeyReader() 
		{
			public void onKeyDetected() 
			{
				KeyEvent event=retrieve();
				control.setKeyCode(event.getKeyCode());
				control.setModifiers(event.getModifiers());
				button.removeKeyListener(this);
				button.setText(getDisplayText(control));
			}
		};
		
		button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				button.addKeyListener(keyReader);
			}
		});
		
		JLabel label= new JLabel(control.getName());
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		controlPanel.add(label);
		controlPanel.add(button);
	}
	
	private static String getDisplayText(Control control)
	{
		int kc=control.getKeyCode();
		int mod=control.getModifiers();
		
		String keyText=KeyEvent.getKeyText(kc).trim();
		String modText=KeyEvent.getKeyModifiersText(mod).trim();
		
		if(!modText.equals(""))
		{
			keyText=modText+"+"+keyText;
		}
		
		return keyText;
	}
	
	private void setupSavePanel() {
		savePanel=new JPanel();
		savePanel.setLayout(new BoxLayout(savePanel,BoxLayout.X_AXIS));
		
		saveField=new JTextField();
		savePanel.add(saveField);
		
		JButton saveButton=new JButton("Save");
		
		saveButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				//TODO
			}
		});
		
		savePanel.add(saveButton);
		
		JComboBox<String> dropdown=new JComboBox<String>(controlLoader.availableControls());
		
		savePanel.add(new Label("       "));
		savePanel.add(dropdown);
		
		JButton loadButton=new JButton("Load");
		
		loadButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				//TODO
			}
		});
		
		savePanel.add(loadButton);
		
		
		add(savePanel, BorderLayout.PAGE_END);
	}
}
