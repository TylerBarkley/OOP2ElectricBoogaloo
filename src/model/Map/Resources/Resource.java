package model.Map.Resources;

public abstract class Resource 
{
	private int quantity;
	
	public Resource(int quantity){
		this.quantity = quantity;
	}
	
	public int getAmount()
	{
		return quantity;
	}
	
	protected void setAmount(int quantity)
	{
		this.quantity=quantity;
	}
	
	public int addAmount(int quantity)
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
