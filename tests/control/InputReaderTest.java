package control;

import javax.swing.JFrame;

public class InputReaderTest {
	
	//NOT A JUnit TEST. MUST TEST MANUALLY
    public static void main(String[] args){
    	JFrame frame=new JFrame();
    	frame.setSize(100, 100);
    	frame.addKeyListener(new InputReader(null, new UserControls()));
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setVisible(true);
    }
}
