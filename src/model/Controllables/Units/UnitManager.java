package model.Controllables.Units;

import java.util.ArrayList;

import model.Controllables.ControllableID;
import model.Map.Resources.Food;
import model.observers.DeathObserver;
import model.player.PlayerID;

public class UnitManager implements DeathObserver{
	private Colonist[] colonists;
	private Explorer[] explorers;
	private Melee[] soldiers;
	private Ranged[] rangedSoldiers;

	private PlayerID playerID;

	private int totalUnits;

	public UnitManager(PlayerID playerID)
	{
		colonists=new Colonist[10];
		explorers=new Explorer[10];
		soldiers=new Melee[10];
		rangedSoldiers=new Ranged[10];

		this.playerID=playerID;

		totalUnits=0;
	}

	public boolean add(Colonist unit)
	{
		if(totalUnits<25)
		{
			for(int i=0; i<colonists.length; i++)
			{
				if(colonists[i] == null)
				{
					colonists[i]=unit;
					totalUnits++;

					UnitID id = new UnitID(playerID, UnitID.COLONIST_TYPE_ID, i);
					unit.setID(id);
					unit.addObserver(this);

					return true;
				}
			}
		}

		return false;
	}

	public boolean add(Explorer unit)
	{
		if(totalUnits<25)
		{
			for(int i=0; i<explorers.length; i++)
			{
				if(explorers[i] == null)
				{
					explorers[i]=unit;
					totalUnits++;

					UnitID id = new UnitID(playerID, UnitID.EXPLORER_TYPE_ID, i);
					unit.setID(id);
					unit.addObserver(this);

					return true;
				}
			}
		}

		return false;
	}

	public boolean add(Melee unit)
	{
		if(totalUnits<25)
		{
			for(int i=0; i<soldiers.length; i++)
			{
				if(soldiers[i] == null)
				{
					soldiers[i]=unit;
					totalUnits++;

					UnitID id = new UnitID(playerID, UnitID.MELEE_TYPE_ID, i);
					unit.setID(id);
					unit.addObserver(this);

					return true;
				}
			}
		}

		return false;
	}

	public boolean add(Ranged unit)
	{
		if(totalUnits<25)
		{
			for(int i=0; i<rangedSoldiers.length; i++)
			{
				if(rangedSoldiers[i] == null)
				{
					rangedSoldiers[i]=unit;
					totalUnits++;

					UnitID id = new UnitID(playerID, UnitID.RANGED_TYPE_ID, i);
					unit.setID(id);
					unit.addObserver(this);

					return true;
				}
			}
		}

		return false;
	}

	public ArrayList<Unit> getUnits()
	{
		ArrayList<Unit> units=new ArrayList<Unit>();
		
		addNonNullElements(units, colonists);
		addNonNullElements(units, explorers);
		addNonNullElements(units, soldiers);
		addNonNullElements(units, rangedSoldiers);
		
		return units;
	}

	private static void addNonNullElements(ArrayList<Unit> list, Unit[] arr)
	{
		for(Unit unit: arr)
		{
			if(unit !=null)
			{
				list.add(unit);
			}
		}
	}
	
	@Override
	public void update(ControllableID id) {
		int instanceNumber=id.getInstanceNumber();
		Unit unit=null;

		switch(id.getType())
		{
		case UnitID.COLONIST_TYPE_ID:
			unit=colonists[instanceNumber];
			colonists[instanceNumber]=null;
			break;
		case UnitID.EXPLORER_TYPE_ID:
			unit=explorers[instanceNumber];
			explorers[instanceNumber]=null;
			break;
		case UnitID.MELEE_TYPE_ID:
			unit=soldiers[instanceNumber];
			soldiers[instanceNumber]=null;
			break;
		case UnitID.RANGED_TYPE_ID:
			unit=rangedSoldiers[instanceNumber];
			rangedSoldiers[instanceNumber]=null;
			break;
		}

		if(unit != null)
		{
			totalUnits--;
		}
	}

	public void chargeResources(Food food) {
		ArrayList<Unit> units=getUnits();
		int foodQuantity = food.getAmount();
		
		for(Unit unit: units)
		{
			int cost = unit.getUpkeep();
			
			if(foodQuantity < cost)
			{
				unit.malnourish();
			}
			else
			{
				foodQuantity-=cost;
			}
		}
	}
}
