package control;

import java.awt.event.KeyEvent;

public class Control {
	private KeyEvent event;
	private String name;

	public Control(KeyEvent event, String name)
	{
		this.event=event;
		this.name=name;
	}
	
	public Control(KeyEvent event)
	{
		this(event, "Default");
	}
	
	public String getName() {
		return name;
	}
	
	public int hashCode(){
		return event.hashCode() + super.hashCode();
	}
	
	public boolean equals(Object o){
		if(o == null || o.getClass() != getClass())
		{
			return false;
		}
		
		KeyEvent e=((Control)o).event;
		
		boolean b=event.getKeyCode() == e.getKeyCode();
		b&=event.getModifiers() == e.getModifiers();
		
		return b;
	}
}
