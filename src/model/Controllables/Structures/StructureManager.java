
package model.Controllables.Structures;

import java.util.ArrayList;

import model.Controllables.Units.Colonist;
import model.Controllables.Units.Unit;
import model.Controllables.Units.UnitID;
import model.Map.Resources.Energy;
import model.Map.Resources.Ore;
import model.observers.StructureObserver;
import model.player.PlayerID;

public class StructureManager implements StructureObserver{
	private Capital[] capitals;
	private Farm[] farms;
	private Fort[] forts;
	private Mine[] mines;
	private ObservationTower[] towers;
	private PowerPlant[] plants;
	private University[] universities;


	private PlayerID playerID;

	public StructureManager(PlayerID playerID)
	{
		capitals = new Capital[10];        
		farms= new Farm[10];               
		forts= new Fort[10];               
		mines= new Mine[10];               
		towers= new ObservationTower[10];  
		plants= new PowerPlant[10];        
		universities= new University[10];

		this.playerID=playerID;
	}

	public boolean add(Capital structure)
	{
		for(int i=0; i<capitals.length; i++)
		{
			if(capitals[i] == null)
			{
				capitals[i]=structure;

				StructureID id = new StructureID(playerID, StructureID.CAPITAL_TYPE_ID, i);
				structure.setID(id);
				structure.addObserver(this);

				return true;
			}
		}
		
		return false;
	}

	public boolean add(Farm structure)
	{
		for(int i=0; i<capitals.length; i++)
		{
			if(farms[i] == null)
			{
				farms[i]=structure;

				StructureID id = new StructureID(playerID, StructureID.FARM_TYPE_ID, i);
				structure.setID(id);
				structure.addObserver(this);

				return true;
			}
		}
		
		return false;
	}

	public boolean add(Fort structure)
	{
		for(int i=0; i<capitals.length; i++)
		{
			if(forts[i] == null)
			{
				forts[i]=structure;

				StructureID id = new StructureID(playerID, StructureID.FORT_TYPE_ID, i);
				structure.setID(id);
				structure.addObserver(this);

				return true;
			}
		}
		
		return false;
	}

	public boolean add(Mine structure)
	{
		for(int i=0; i<capitals.length; i++)
		{
			if(mines[i] == null)
			{
				mines[i]=structure;

				StructureID id = new StructureID(playerID, StructureID.MINE_TYPE_ID, i);
				structure.setID(id);
				structure.addObserver(this);

				return true;
			}
		}
		
		return false;
	}

	public boolean add(ObservationTower structure)
	{
		for(int i=0; i<capitals.length; i++)
		{
			if(towers[i] == null)
			{
				towers[i]=structure;

				StructureID id = new StructureID(playerID, StructureID.OBSERVATIONTOWER_TYPE_ID, i);
				structure.setID(id);
				structure.addObserver(this);

				return true;
			}
		}
		
		return false;
	}

	public boolean add(PowerPlant structure)
	{
		for(int i=0; i<capitals.length; i++)
		{
			if(plants[i] == null)
			{
				plants[i]=structure;

				StructureID id = new StructureID(playerID, StructureID.POWERPLANT_TYPE_ID, i);
				structure.setID(id);
				structure.addObserver(this);

				return true;
			}
		}
		
		return false;
	}

	public boolean add(University structure)
	{
		for(int i=0; i<capitals.length; i++)
		{
			if(universities[i] == null)
			{
				universities[i]=structure;

				StructureID id = new StructureID(playerID, StructureID.UNIVERSITY_TYPE_ID, i);
				structure.setID(id);
				structure.addObserver(this);

				return true;
			}
		}
		
		return false;
	}

	public ArrayList<Structure> getStructures()
	{
		ArrayList<Structure> structures=new ArrayList<Structure>();

		addNonNullElements(structures, capitals);
		addNonNullElements(structures, farms);
		addNonNullElements(structures, forts);
		addNonNullElements(structures, mines);
		addNonNullElements(structures, towers);
		addNonNullElements(structures, plants);
		addNonNullElements(structures, universities);

		return structures;
	}

	private static void addNonNullElements(ArrayList<Structure> list, Structure[] arr)
	{
		for(Structure structure: arr)
		{
			if(structure !=null)
			{
				list.add(structure);
			}
		}
	}
	
	@Override
	public void update(Structure observable) {
		if(observable.isAlive())
		{
			return;
		}

		StructureID id=observable.getID();
		int instanceNumber=id.getInstanceNumber();
		Structure structure=null;
		
		switch(id.getType())
		{
		case StructureID.CAPITAL_TYPE_ID:
			structure=capitals[instanceNumber];
			capitals[instanceNumber]=null;
			break;
		case StructureID.FARM_TYPE_ID:
			structure=farms[instanceNumber];
			farms[instanceNumber]=null;
			break;
		case StructureID.FORT_TYPE_ID:
			structure=forts[instanceNumber];
			forts[instanceNumber]=null;
			break;
		case StructureID.MINE_TYPE_ID:
			structure=mines[instanceNumber];
			mines[instanceNumber]=null;
			break;
		case StructureID.OBSERVATIONTOWER_TYPE_ID:
			structure=towers[instanceNumber];
			towers[instanceNumber]=null;
			break;
		case StructureID.POWERPLANT_TYPE_ID:
			structure=plants[instanceNumber];
			plants[instanceNumber]=null;
			break;
		case StructureID.UNIVERSITY_TYPE_ID:
			structure=universities[instanceNumber];
			universities[instanceNumber]=null;
			break;
		}
	}

	public ArrayList<Capital> getCapitals() {
		ArrayList<Capital> list=new ArrayList<Capital>();
		
		for(Capital unit: capitals)
		{
			list.add(unit);	
		}
		
		return list;
	}

	public ArrayList<Farm> getFarms() {
		ArrayList<Farm> list=new ArrayList<Farm>();
		
		for(Farm unit: farms)
		{
			list.add(unit);	
		}
		
		return list;
	}

	public ArrayList<Fort> getForts() {
		ArrayList<Fort> list=new ArrayList<Fort>();
		
		for(Fort unit: forts)
		{
			list.add(unit);	
		}
		
		return list;
	}

	public ArrayList<Mine> getMines() {
		ArrayList<Mine> list=new ArrayList<Mine>();
		
		for(Mine unit: mines)
		{
			list.add(unit);	
		}
		
		return list;
	}

	public ArrayList<ObservationTower> getTowers() {
		ArrayList<ObservationTower> list=new ArrayList<ObservationTower>();
		
		for(ObservationTower unit: towers)
		{
			list.add(unit);	
		}
		
		return list;
	}

	public ArrayList<PowerPlant> getPlants() {
		ArrayList<PowerPlant> list=new ArrayList<PowerPlant>();
		
		for(PowerPlant unit: plants)
		{
			list.add(unit);	
		}
		
		return list;
	}

	public ArrayList<University> getUniversities() {
		ArrayList<University> list=new ArrayList<University>();
		
		for(University unit: universities)
		{
			list.add(unit);	
		}
		
		return list;
	}

	public void chargeResources(Ore metal, Energy power) {
		ArrayList<Structure> structures=getStructures();
		int metalQuantity = metal.getAmount();
		int powerQuantity = metal.getAmount();

		for(Structure structure: structures)
		{
			int cost = structure.getUpkeep();
			if(structure.getID().getType() == StructureID.UNIVERSITY_TYPE_ID)
			{
				if(powerQuantity < cost)
				{
					structure.malnourish();
				}
				else
				{
					powerQuantity-=cost;
				}
			}
			else
			{
				if(metalQuantity < cost)
				{
					structure.malnourish();
				}
				else
				{
					metalQuantity-=cost;
				}
			}
		}

		metal.setAmount(metalQuantity);
		power.setAmount(powerQuantity);
	}
}
