package model;

public abstract class Resource 
{
	private int quantity;
	
	//TODO Constructors
	
	public int getAmount()
	{
		return quantity;
	}
	
	protected void setAmount(int quantity)
	{
		this.quantity=quantity;
	}
	
	protected int addAmount(int quantity)
	{
		quantity+=quantity;
		return quantity;
	}
	
	public int mineAmount(int quantity)
	{
		int amountMined=quantity;
		
		if(quantity>this.quantity)
		{
			amountMined=this.quantity;
		}
		
		this.quantity-=amountMined;
		
		return amountMined;
	}
}
