package control;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public abstract class KeyReader extends KeyAdapter{
	private KeyEvent event;

	@Override
	public void keyReleased(KeyEvent e) {
		if(event==null)
		{
			event=e;
			onKeyDetected();
		}
	}

	public abstract void onKeyDetected();
	
	public boolean keyDetected(){
		return event != null;
	}

	public KeyEvent peek(){
		return event;
	}
	
	public KeyEvent retrieve(){
		KeyEvent e=event;
		event=null;
		return e;
	}
}
