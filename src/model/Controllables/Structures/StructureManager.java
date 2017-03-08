package model.Controllables.Structures;

import java.util.ArrayList;

import model.ControllableID;
import model.StructureID;
import model.Controllables.Units.Unit;
import model.observers.DeathObserver;
import model.player.PlayerID;

public class StructureManager implements DeathObserver{
	private ArrayList<Capital> capitals;
	private ArrayList<Farm> farms;
	private ArrayList<Fort> forts;
	private ArrayList<Mine> mines;
	private ArrayList<ObservationTower> towers;
	private ArrayList<PowerPlant> plants;
	private ArrayList<University> universities;

	private PlayerID playerID;

	public StructureManager(PlayerID playerID)
	{
		capitals = new ArrayList<Capital>();        
		farms= new ArrayList<Farm>();               
		forts= new ArrayList<Fort>();               
		mines= new ArrayList<Mine>();               
		towers= new ArrayList<ObservationTower>();  
		plants= new ArrayList<PowerPlant>();        
		universities= new ArrayList<University>();

		this.playerID=playerID;
	}

	public boolean add(Capital structure)
	{
		StructureID id = new StructureID(playerID, StructureID.CAPITAL_TYPE_ID, capitals.size());
		capitals.add(structure);
		structure.addObserver(this);
		structure.setID(id);
		return true;
	}

	public boolean add(Farm structure)
	{
		StructureID id = new StructureID(playerID, StructureID.FARM_TYPE_ID, farms.size());
		farms.add(structure);
		structure.addObserver(this);
		structure.setID(id);
		return true;
	}
	
	public boolean add(Fort structure)
	{
		StructureID id = new StructureID(playerID, StructureID.FORT_TYPE_ID, forts.size());
		forts.add(structure);
		structure.addObserver(this);
		structure.setID(id);
		return true;
	}
	
	public boolean add(Mine structure)
	{
		StructureID id = new StructureID(playerID, StructureID.MINE_TYPE_ID, mines.size());
		mines.add(structure);
		structure.addObserver(this);
		structure.setID(id);
		return true;
	}
	
	public boolean add(ObservationTower structure)
	{
		StructureID id = new StructureID(playerID, StructureID.OBSERVATIONTOWER_TYPE_ID, towers.size());
		towers.add(structure);
		structure.addObserver(this);
		structure.setID(id);
		return true;
	}
	
	public boolean add(PowerPlant structure)
	{
		StructureID id = new StructureID(playerID, StructureID.POWERPLANT_TYPE_ID, plants.size());
		plants.add(structure);
		structure.addObserver(this);
		structure.setID(id);
		return true;
	}
	
	public boolean add(University structure)
	{
		StructureID id = new StructureID(playerID, StructureID.UNIVERSITY_TYPE_ID, universities.size());
		universities.add(structure);
		structure.addObserver(this);
		structure.setID(id);
		return true;
	}
	
	public ArrayList<Structure> getStructures()
	{
		ArrayList<Structure> structures=new ArrayList<Structure>();
		
		structures.addAll(capitals);
		structures.addAll(farms);
		structures.addAll(forts);
		structures.addAll(mines);
		structures.addAll(towers);
		structures.addAll(plants);
		structures.addAll(universities);
		
		return structures;
	}
	
	@Override
	public void update(ControllableID id) {
		int instanceNumber=id.getInstanceNumber();

		switch(id.getType())
		{
		case StructureID.CAPITAL_TYPE_ID:
			capitals.remove(instanceNumber);
			
			for(int i=instanceNumber; i<capitals.size(); i++)
			{
				capitals.get(i).getID().setInstanceNumber(i);
			}
			
			break;
		case StructureID.FARM_TYPE_ID:
			farms.remove(instanceNumber);
			
			for(int i=instanceNumber; i<farms.size(); i++)
			{
				farms.get(i).getID().setInstanceNumber(i);
			}
			
			break;
		case StructureID.FORT_TYPE_ID:
			forts.remove(instanceNumber);
			
			for(int i=instanceNumber; i<forts.size(); i++)
			{
				forts.get(i).getID().setInstanceNumber(i);
			}
			
			break;
		case StructureID.MINE_TYPE_ID:
			mines.remove(instanceNumber);
			
			for(int i=instanceNumber; i<mines.size(); i++)
			{
				mines.get(i).getID().setInstanceNumber(i);
			}
			
			break;
		case StructureID.OBSERVATIONTOWER_TYPE_ID:
			towers.remove(instanceNumber);
			
			for(int i=instanceNumber; i<towers.size(); i++)
			{
				towers.get(i).getID().setInstanceNumber(i);
			}
			
			break;
		case StructureID.POWERPLANT_TYPE_ID:
			plants.remove(instanceNumber);
			
			for(int i=instanceNumber; i<plants.size(); i++)
			{
				plants.get(i).getID().setInstanceNumber(i);
			}
			
			break;
		case StructureID.UNIVERSITY_TYPE_ID:
			universities.remove(instanceNumber);
			
			for(int i=instanceNumber; i<universities.size(); i++)
			{
				universities.get(i).getID().setInstanceNumber(i);
			}
			
			break;
		}
	}
}
