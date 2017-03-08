package model.Map.Resources;

public abstract class RenewableResource extends Resource 
{
	private int maxQuantity;
	private int renewalRate; //Should this be a double?
	
	public RenewableResource(int initialAmmount){
		super(initialAmmount);
		maxQuantity = initialAmmount;
		renewalRate = 10;
	}
	
	public void renew()
	{
		this.addAmount(renewalRate);
	}
}