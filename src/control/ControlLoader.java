package control;

import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Stream;

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
				if(name.endsWith(".OOP2ElectricBoogaloo"))
				{
					results.add(name.substring(0, name.indexOf(".OOP2ElectricBoogaloo")));
				}
			}
		}

		String[] finalResult=new String[results.size()];
		results.toArray(finalResult);

		return finalResult;
	}

	public void saveControl(UserControls controls, String name){
		try{
			PrintWriter writer = new PrintWriter("controls/"+name+".OOP2ElectricBoogaloo", "UTF-8");

			for(Control control: controls.getAllControls())
			{
				writer.print(control.getModifiers()+" ");
				writer.print(control.getKeyCode()+" ");
				writer.println(control.getName());
			}

			writer.close();
		} catch (IOException e) {
			System.err.println("Unable to write controls to file\n"+e);
		}
	}

	public boolean loadControl(UserControls controls, String name) {
		try
		{
			FileInputStream fileStream = new FileInputStream("controls/"+name+".OOP2ElectricBoogaloo");
			BufferedReader reader = new BufferedReader(new InputStreamReader(fileStream));

			String line;

			while ((line = reader.readLine()) != null)   {
				line=line.trim();

				String modifiersString = line.substring(0, line.indexOf(' '));
				line=line.substring(line.indexOf(' ')+1, line.length());
				String keyCodeString = line.substring(0, line.indexOf(' '));
				line=line.substring(line.indexOf(' ')+1, line.length());

				int modifiers=Integer.parseInt(modifiersString);
				int keyCode=Integer.parseInt(keyCodeString);

				boolean success=setControlValue(controls, modifiers, keyCode, line);

				if(!success){
					System.err.println("Unknown control found: "+line);
				}
			}

			reader.close();
			return true;
		}
		catch(IOException e){
			System.err.println("Unable to read controls from file\n"+e);
		}
		catch(IndexOutOfBoundsException e){
			System.err.println("Unable to read controls from file\n"+e);
		}

		return false;
	}

	private boolean setControlValue(UserControls controls, int modifiers, int keyCode, String name) {
		for(Control c: controls.getAllControls()){
			if(c.getName().equals(name))
			{
				c.setKeyCode(keyCode);
				c.setModifiers(modifiers);
				return true;
			}
		}

		return false;
	}
}
