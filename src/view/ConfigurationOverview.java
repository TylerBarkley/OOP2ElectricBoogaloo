package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import control.Control;
import control.KeyReader;
import control.UserControls;

public class ConfigurationOverview extends JPanel
{
	public ConfigurationOverview(UserControls controls, int width, int height)
	{
		this.setSize(width, height);
		
		setLayout(new GridLayout(8, 4));
		
		for(Control c: controls.getAllControls())
		{
			addButton(c);
		}
	}
	
	private void addButton(Control control)
	{
		String keyText=getDisplayText(control);
		JButton button=new JButton(keyText);
		
		KeyReader keyReader = new KeyReader() 
		{
			public void onKeyDetected() 
			{
				control.setEvent(retrieve());
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
		label.setHorizontalAlignment(SwingConstants.CENTER);
		add(label);
		add(button);
	}
	
	private static String getDisplayText(Control control)
	{
		KeyEvent event=control.getEvent();
		int kc=event.getKeyCode();
		int mod=event.getModifiers();
		
		String keyText=KeyEvent.getKeyText(kc).trim();
		String modText=KeyEvent.getKeyModifiersText(mod).trim();
		
		if(!modText.equals(""))
		{
			keyText=modText+"+"+keyText;
		}
		
		return keyText;
	}
}
