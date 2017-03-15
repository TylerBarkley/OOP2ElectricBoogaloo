package model.Map.Items;

public abstract class Item {
	private ItemID id;
	
	public Item(){
		id=new ItemID();
	}
	
	public ItemID getID(){
		return id;
	}
}
