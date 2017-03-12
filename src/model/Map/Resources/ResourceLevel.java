package model.Map.Resources;

public class ResourceLevel 
{
	private Ore ore;
	private Energy energy;
	private Food food;
	private Boolean working;

	public ResourceLevel(int oreLevel, int energyLevel, int foodLevel){
		ore = new Ore(oreLevel);
		energy = new Energy(energyLevel);
		food = new Food(foodLevel);
		working = false;
	}

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

	public Boolean getWorking() {
		return working;
	}

	public void setWorking(Boolean working) {
		this.working = working;
	}
}
