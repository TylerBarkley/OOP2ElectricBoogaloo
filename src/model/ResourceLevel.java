package model;

public class ResourceLevel 
{
	private Ore ore;
	private Energy energy;
	private Food food;
	
	public int getOreLevel() 
	{
		return ore.getAmount();
	}
	
	public int getEnergyLevel() 
	{
		return energy.getAmount();
	}
	
	public int getFoodLevel() 
	{
		return food.getAmount();
	}
	
	public int mineOreLevel(int amount) 
	{
		return ore.mineAmount(amount);
	}
	
	public int mineEnergyLevel(int amount) 
	{
		return energy.mineAmount(amount);
	}
	
	public int mineFoodLevel(int amount) 
	{
		return food.mineAmount(amount);
	}
	
	public void renew()
	{
		food.renew();
	}
}
