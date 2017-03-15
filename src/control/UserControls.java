package control;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JFrame;

public class UserControls {
	private Control mapUp;                 
	private Control mapDown;               
	private Control mapUpRight;            
	private Control mapUpLeft;             
	private Control mapDownRight;          
	private Control mapDownLeft;           
	private Control cycleModeUp;                
	private Control cycleModeDown;              
	private Control cycleTypeLeft;             
	private Control cycleTypeRight;
	private Control cycleCommandUp;                
	private Control cycleCommandDown; 
	private Control cycleInstanceLeft;               
	private Control cycleInstanceRight;                
	private Control centerFocus; 
	private Control endTurn;
	private Control toggleResources;
	private Control select;

	public UserControls()
	{
		setDefaultControls();
	}

	public void setDefaultControls() {
		JFrame frame=new JFrame();
		
		mapUp=new Control(new KeyEvent(frame,0,0,0,KeyEvent.VK_NUMPAD8,'8'), "Move Focus North");                 
		mapDown=new Control(new KeyEvent(frame,0,0,0,KeyEvent.VK_NUMPAD2,'2'), "Move Focus South");               
		mapUpRight=new Control(new KeyEvent(frame,0,0,0,KeyEvent.VK_NUMPAD9,'9'), "Move Focus Northeast");            
		mapUpLeft=new Control(new KeyEvent(frame,0,0,0,KeyEvent.VK_NUMPAD7,'7'), "Move Focus Northwest");             
		mapDownRight=new Control(new KeyEvent(frame,0,0,0,KeyEvent.VK_NUMPAD3,'3'), "Move Focus Southeast");          
		mapDownLeft=new Control(new KeyEvent(frame,0,0,0,KeyEvent.VK_NUMPAD1,'1'), "Move Focus Southwest");           
		cycleModeUp=new Control(new KeyEvent(frame,0,0,KeyEvent.CTRL_DOWN_MASK,KeyEvent.VK_UP, ' '), "Cycle Mode");           
		cycleModeDown=new Control(new KeyEvent(frame,0,0,KeyEvent.CTRL_DOWN_MASK,KeyEvent.VK_DOWN,' '), "Reverse Cycle Mode");         
		cycleTypeLeft=new Control(new KeyEvent(frame,0,0,KeyEvent.CTRL_DOWN_MASK,KeyEvent.VK_LEFT,' '), "Cycle Type");         
		cycleTypeRight=new Control(new KeyEvent(frame,0,0,KeyEvent.CTRL_DOWN_MASK,KeyEvent.VK_RIGHT,' '), "Reverse Cycle Type");        
		cycleCommandUp=new Control(new KeyEvent(frame,0,0,0,KeyEvent.VK_UP, ' '), "Cycle Command");         
		cycleCommandDown=new Control(new KeyEvent(frame,0,0,0,KeyEvent.VK_DOWN,' '), "Reverse Cycle Command");      
		cycleInstanceLeft=new Control(new KeyEvent(frame,0,0,0,KeyEvent.VK_LEFT,' '), "Cycle Instance");     
		cycleInstanceRight=new Control(new KeyEvent(frame,0,0,0,KeyEvent.VK_RIGHT,' '), "Reverse Cycle Instance");   
		centerFocus=new Control(new KeyEvent(frame,0,0,0,KeyEvent.VK_NUMPAD5,'5'), "Center Map Focus");
		endTurn=new Control(new KeyEvent(frame,0,0,0,KeyEvent.VK_L,'l'), "End Turn");
		toggleResources=new Control(new KeyEvent(frame,0,0,0,KeyEvent.VK_R,'r'), "Toggle Resources");
		select=new Control(new KeyEvent(frame,0,0,0,KeyEvent.VK_ENTER,'\n'), "Select");
	}

	public Control getMapUp() {
		return mapUp;
	}

	public Control getMapDown() {
		return mapDown;
	}

	public Control getMapUpRight() {
		return mapUpRight;
	}

	public Control getMapUpLeft() {
		return mapUpLeft;
	}

	public Control getMapDownRight() {
		return mapDownRight;
	}

	public Control getMapDownLeft() {
		return mapDownLeft;
	}

	public Control getCycleModeUp() {
		return cycleModeUp;
	}

	public Control getCycleModeDown() {
		return cycleModeDown;
	}

	public Control getCycleTypeLeft() {
		return cycleTypeLeft;
	}

	public Control getCycleTypeRight() {
		return cycleTypeRight;
	}

	public Control getCycleCommandUp() {
		return cycleCommandUp;
	}

	public Control getCycleCommandDown() {
		return cycleCommandDown;
	}

	public Control getCycleInstanceLeft() {
		return cycleInstanceLeft;
	}

	public Control getCycleInstanceRight() {
		return cycleInstanceRight;
	}

	public Control getCenterFocus() {
		return centerFocus;
	}

	public Control getEndTurn() {
		return endTurn;
	}
	
	public Control getToggleResources() {
		return toggleResources;
	}

	public Control getSelect() {
		return select;
	}

	public void setMapUp(Control mapUp) {
		this.mapUp = mapUp;
	}

	public void setMapDown(Control mapDown) {
		this.mapDown = mapDown;
	}

	public void setMapUpRight(Control mapUpRight) {
		this.mapUpRight = mapUpRight;
	}

	public void setMapUpLeft(Control mapUpLeft) {
		this.mapUpLeft = mapUpLeft;
	}

	public void setMapDownRight(Control mapDownRight) {
		this.mapDownRight = mapDownRight;
	}

	public void setMapDownLeft(Control mapDownLeft) {
		this.mapDownLeft = mapDownLeft;
	}

	public void setCycleModeUp(Control cycleModeUp) {
		this.cycleModeUp = cycleModeUp;
	}

	public void setCycleModeDown(Control cycleModeDown) {
		this.cycleModeDown = cycleModeDown;
	}

	public void setCycleTypeLeft(Control cycleTypeLeft) {
		this.cycleTypeLeft = cycleTypeLeft;
	}

	public void setCycleTypeRight(Control cycleTypeRight) {
		this.cycleTypeRight = cycleTypeRight;
	}

	public void setCycleCommandUp(Control cycleCommandUp) {
		this.cycleCommandUp = cycleCommandUp;
	}

	public void setCycleCommandDown(Control cycleCommandDown) {
		this.cycleCommandDown = cycleCommandDown;
	}

	public void setCycleInstanceLeft(Control cycleInstanceLeft) {
		this.cycleInstanceLeft = cycleInstanceLeft;
	}

	public void setCycleInstanceRight(Control cycleInstanceRight) {
		this.cycleInstanceRight = cycleInstanceRight;
	}

	public void setCenterFocus(Control centerFocus) {
		this.centerFocus = centerFocus;
	}

	public void setEndTurn(Control endTurn) {
		this.endTurn = endTurn;
	}

	public void setToggleResources(Control toggleResources) {
		this.toggleResources = toggleResources;
	}

	public void setSelect(Control select) {
		this.select = select;
	}
	
	public ArrayList<Control> getAllControls() 
	{
		ArrayList<Control> list=new ArrayList<Control>();
		
		list.add(mapUp);
		list.add(mapDown);
		list.add(mapUpRight);
		list.add(mapUpLeft);
		list.add(mapDownRight);
		list.add(mapDownLeft);      
		list.add(cycleModeUp);
		list.add(cycleModeDown);
		list.add(cycleTypeLeft);
		list.add(cycleTypeRight);
		list.add(cycleCommandUp);
		list.add(cycleCommandDown);
		list.add(cycleInstanceLeft);
		list.add(cycleInstanceRight);
		list.add(centerFocus);
		list.add(endTurn);
		list.add(toggleResources);
		list.add(select);
		
		return list;
	}
}
