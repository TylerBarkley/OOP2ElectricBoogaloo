package control;

import java.awt.event.KeyEvent;
import java.util.Objects;

public class Control {
	private int keyCode;
	private int modifiers;
	private String name;

	public Control(int keyCode, int modifiers, String name) {
		this.keyCode = keyCode;
		this.modifiers = modifiers;
		this.name = name;
	}

	public Control(KeyEvent event, String name)
	{
		this.keyCode=event.getKeyCode();
		this.modifiers=event.getModifiers();
		this.name=name;
	}
	
	public Control(KeyEvent event)
	{
		this(event, "Default");
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public int getKeyCode() {
		return keyCode;
	}

	public void setKeyCode(int keyCode) {
		this.keyCode = keyCode;
	}

	public int getModifiers() {
		return modifiers;
	}

	public void setModifiers(int modifiers) {
		this.modifiers = modifiers;
	}
	
	public int hashCode(){
		return Objects.hash(keyCode,modifiers,name);
	}
	
	public boolean equals(Object o){
		if(o == null || o.getClass() != getClass())
		{
			return false;
		}
		
		Control c=((Control)o);
		
		boolean b=getKeyCode() == c.getKeyCode();
		b&=getModifiers() == c.getModifiers();
		
		return b;
	}
}
