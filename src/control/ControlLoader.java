package control;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class ControlLoader {
	private static ControlLoader instance;
	
	private ControlLoader(){}

	public static ControlLoader getInstance() 
	{
		if(instance==null){
			instance=new ControlLoader();
		}
		return instance;
	}
	
	public String[] availableControls(){
		ArrayList<String> results = new ArrayList<String>();

		File[] files = new File("controls").listFiles();
		//If this pathname does not denote a directory, then listFiles() returns null. 

		for (File file : files) {
		    if (file.isFile()) {
		    	String name=file.getName();
		        results.add(name.substring(0, name.indexOf(".txt")));
		    }
		}
		
		String[] finalResult=new String[results.size()];
		results.toArray(finalResult);
		
		return finalResult;
	}
	
	public void saveControl(UserControls controls, String name){
		try{
		    PrintWriter writer = new PrintWriter(name, "UTF-8");
		    
		    for(Control control: controls.getAllControls())
		    {
		    	writer.print(control.getName()+" ");
		    	writer.print(control.getModifiers()+" ");
		    	writer.print(control.getKeyCode()+" ");
		    }
		    
		    writer.close();
		} catch (IOException e) {
		   // do something
		}
	}
}
