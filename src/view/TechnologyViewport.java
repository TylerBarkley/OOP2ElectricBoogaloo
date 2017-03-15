package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.StructureResearchCommand;
import model.Technology;
import model.UnitResearchCommand;
import model.WorkerResearchCommand;
import model.Controllables.Structures.University;
import model.observers.TechnologyObserver;
import model.observers.UniversityObserver;

public class TechnologyViewport extends JPanel implements TechnologyObserver, UniversityObserver {

	private int width;
	private int height;
	private JButton workerDensityButton;
	private JButton workerRadiusButton;
	private JButton productionRateButton;
	private JButton unitEfficiencyButton;
	private JButton unitHealthButton;
	private JButton unitMovementButton;
	private JButton unitAttackButton;
	private JButton unitDefenseButton;
	private JButton unitVisibilityRadiusButton;
	private JButton unitArmorButton;
	private JButton structureEfficiencyButton;
	private JButton structureHealthButton;
	private JButton structureMovementButton;
	private JButton structureAttackButton;
	private JButton structureDefenseButton;
	private JButton structureVisibilityRadiusButton;
	private JButton structureArmorButton;
	private JComboBox unitComboBox;
	private JComboBox structureComboBox;
	private JComboBox productionComboBox;
	private int workerDensityLevel = 1;
	private int workerRadiusLevel = 1;
	private int productionRateLevel = 1;
	
	private int efficiencyIndex = 0;
	private int healthIndex = 1;
	private int movementIndex = 2;
	private int attackIndex = 3;
	private int defenseIndex = 4;
	private int visibilityRadiusIndex = 5;
	private int armorIndex = 6;
	private int maxLevel = 5;
	private int oreProductionIndex = 0;
	private int energyProductionIndex = 1;
	private int foodProductionIndex = 2;
	private int workerProductionIndex = 3;
	private int soldierProductionIndex = 4;
	private int explorerProductionIndex = 5;
	
	private int unitLevels[][] = {{1,1,1,1,1,1,1},{1,1,1,1,1,1,1},{1,1,1,1,1,1,1},{1,1,1,1,1,1,1}};
	private int structureLevels[][] = {{1,1,1,1,1,1,1},{1,1,1,1,1,1,1},{1,1,1,1,1,1,1},{1,1,1,1,1,1,1},{1,1,1,1,1,1,1},{1,1,1,1,1,1,1},{1,1,1,1,1,1,1}};
	private ArrayList<University> unassignedUniversities;
	
	
	private Technology tech; 
	
	public static final String UNITNAMES[]= {"COLONIST","EXPLORER","MELEE","RANGE"};
	public static final String STRUCTURENAMES[] = {"CAPITAL","FARM","FORT","MINE","OBSERVATION TOWER","POWER PLANT","UNIVERSITY"};
	public static final String PRODUCTIONNAMES[] = {"ORE PRODUCTION","ENERGY PRODUCTION","FOOD PRODUCTION","WORKER BREEDING","SOLDIER TRAINING","EXPLORERER TRAINING"};
	
	
	public TechnologyViewport(int width, int height) {
		
		this.width = width;
		this.height = height;
		this.setSize(width, height);
		
		workerDensityButton = new JButton(" Workder Density Level: 1");
		workerRadiusButton = new JButton(" Worker Radius Level: 0");
		productionRateButton = new JButton(" Production Rate Level: 1");
		
		unitEfficiencyButton = new JButton(" Efficiency Level: 1");
		unitHealthButton = new JButton(" Health Level: 1");
		unitMovementButton = new JButton(" Movement Level: 1");
		unitAttackButton = new JButton(" Attack Level: 1");
		unitDefenseButton = new JButton(" Defense Level: 1");
		unitVisibilityRadiusButton = new JButton(" Visibility Radius Level: 1");
		unitArmorButton = new JButton("Armor level: 1");
		
		structureEfficiencyButton = new JButton(" Efficiency Level: 1");
		structureHealthButton = new JButton(" Health Level: 1");
		structureMovementButton = new JButton(" Movement Level: 1");
		structureAttackButton = new JButton(" Attack Level: 1");
		structureDefenseButton = new JButton(" Defense Level: 1");
		structureVisibilityRadiusButton = new JButton(" Visibility Radius Level: 1");
		structureArmorButton = new JButton("Armor level: 1");
		
		workerDensityButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		workerRadiusButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		productionRateButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		unitEfficiencyButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		unitHealthButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		unitMovementButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		unitAttackButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		unitDefenseButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		unitVisibilityRadiusButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		unitArmorButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		structureEfficiencyButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		structureHealthButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		structureMovementButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		structureAttackButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		structureDefenseButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		structureVisibilityRadiusButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		structureArmorButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		workerDensityButton.setFocusable(false);
		workerRadiusButton.setFocusable(false);
		productionRateButton.setFocusable(false);
		
		unitEfficiencyButton.setFocusable(false);
		unitHealthButton.setFocusable(false);
		unitMovementButton.setFocusable(false);
		unitAttackButton.setFocusable(false);
		unitDefenseButton.setFocusable(false);
		unitVisibilityRadiusButton.setFocusable(false);
		unitArmorButton.setFocusable(false);
		
		structureEfficiencyButton.setFocusable(false);
		structureHealthButton.setFocusable(false);
		structureMovementButton.setFocusable(false);
		structureAttackButton.setFocusable(false);
		structureDefenseButton.setFocusable(false);
		structureVisibilityRadiusButton.setFocusable(false);
		structureArmorButton.setFocusable(false);
		
		unitComboBox = new JComboBox(UNITNAMES);
		structureComboBox = new JComboBox(STRUCTURENAMES);
		productionComboBox = new JComboBox(PRODUCTIONNAMES);
		
		productionComboBox.setSelectedIndex(0);
		
		unitComboBox.setAlignmentX(Component.CENTER_ALIGNMENT);
		unitComboBox.setSelectedIndex(0);
		unitComboBox.setPreferredSize(new Dimension(200,25));
		unitComboBox.setMaximumSize(new Dimension(200,25));
		unitComboBox.setFocusable(false);
		
		structureComboBox.setAlignmentX(Component.CENTER_ALIGNMENT);
		structureComboBox.setSelectedIndex(0);
		structureComboBox.setPreferredSize(new Dimension(200,25));
		structureComboBox.setMaximumSize(new Dimension(200,25));
		structureComboBox.setFocusable(false);
		
		disableButtons();
		addActionListeners();
		displayView();
	}
	
	public void displayView() {
		
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		
		JLabel title = new JLabel("Technology Tree");
		title.setAlignmentX(CENTER_ALIGNMENT);
		
		this.add(title);
		
		
		JPanel treePanel = new JPanel();
		
		JPanel workerPanel = new JPanel();
		workerPanel.setLayout(new BoxLayout(workerPanel,BoxLayout.Y_AXIS));
		
		JLabel workerTitle = new JLabel("Worker Tree");
		workerTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		workerPanel.add(Box.createVerticalStrut(20));
		workerPanel.add(workerTitle);
		workerPanel.add(Box.createVerticalStrut(20));
		workerPanel.add(workerDensityButton);
		workerPanel.add(Box.createVerticalStrut(20));
		workerPanel.add(workerRadiusButton);
		
		JPanel productionPanel = new JPanel();
		productionPanel.setLayout(new BoxLayout(productionPanel,BoxLayout.Y_AXIS));
		
		JLabel productionTitle = new JLabel("Production Tree");
		productionTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		productionPanel.add(Box.createVerticalStrut(20));
		productionPanel.add(productionTitle);
		productionPanel.add(Box.createVerticalStrut(20));
		productionPanel.add(productionComboBox);
		productionPanel.add(Box.createVerticalStrut(20));
		productionPanel.add(productionRateButton);
		
		JPanel unitPanel = new JPanel();
		unitPanel.setLayout(new BoxLayout(unitPanel,BoxLayout.Y_AXIS));
		
		JLabel unitTitle = new JLabel("Unit Tree");
		unitTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		unitPanel.add(Box.createVerticalStrut(20));
		unitPanel.add(unitTitle);
		unitPanel.add(Box.createVerticalStrut(20));
		unitPanel.add(unitComboBox);
		unitPanel.add(Box.createVerticalStrut(20));
		unitPanel.add(unitEfficiencyButton);
		unitPanel.add(Box.createVerticalStrut(20));
		
		JPanel twoUnitButtonPanel = new JPanel();
		twoUnitButtonPanel.add(unitHealthButton);
		twoUnitButtonPanel.add(Box.createHorizontalStrut(20));
		twoUnitButtonPanel.add(unitMovementButton);
		
		unitPanel.add(twoUnitButtonPanel);
		unitPanel.add(Box.createVerticalStrut(20));
		
		JPanel fourUnitButtonPanel = new JPanel();
		fourUnitButtonPanel.add(unitAttackButton);
		fourUnitButtonPanel.add(Box.createHorizontalStrut(20));
		fourUnitButtonPanel.add(unitDefenseButton);
		fourUnitButtonPanel.add(Box.createHorizontalStrut(20));
		fourUnitButtonPanel.add(unitVisibilityRadiusButton);
		fourUnitButtonPanel.add(Box.createHorizontalStrut(20));
		fourUnitButtonPanel.add(unitArmorButton);
		
		unitPanel.add(fourUnitButtonPanel);
		
		JPanel structurePanel = new JPanel();
		structurePanel.setLayout(new BoxLayout(structurePanel,BoxLayout.Y_AXIS));
		
		JLabel structureTitle = new JLabel("Structure Tree");
		structureTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		structurePanel.add(Box.createVerticalStrut(20));
		structurePanel.add(structureTitle);
		structurePanel.add(Box.createVerticalStrut(20));
		structurePanel.add(structureComboBox);
		structurePanel.add(Box.createVerticalStrut(20));
		structurePanel.add(structureEfficiencyButton);
		structurePanel.add(Box.createVerticalStrut(20));
		
		JPanel twostructureButtonPanel = new JPanel();
		twostructureButtonPanel.add(structureHealthButton);
		twostructureButtonPanel.add(Box.createHorizontalStrut(20));
		twostructureButtonPanel.add(structureMovementButton);
		
		structurePanel.add(twostructureButtonPanel);
		structurePanel.add(Box.createVerticalStrut(20));
		
		JPanel fourstructureButtonPanel = new JPanel();
		fourstructureButtonPanel.add(structureAttackButton);
		fourstructureButtonPanel.add(Box.createHorizontalStrut(20));
		fourstructureButtonPanel.add(structureDefenseButton);
		fourstructureButtonPanel.add(Box.createHorizontalStrut(20));
		fourstructureButtonPanel.add(structureVisibilityRadiusButton);
		fourstructureButtonPanel.add(Box.createHorizontalStrut(20));
		fourstructureButtonPanel.add(structureArmorButton);
		
		structurePanel.add(fourstructureButtonPanel);
		
		
		
		treePanel.add(unitPanel);
		treePanel.add(Box.createHorizontalStrut(20));
		treePanel.add(structurePanel);
		treePanel.add(Box.createHorizontalStrut(20));
		treePanel.add(workerPanel);
		treePanel.add(Box.createHorizontalStrut(20));
		treePanel.add(productionPanel);
		this.add(treePanel);
		this.setBackground(Color.orange);
		
		
	}
	
	public void addActionListeners() {
		
		workerDensityButton.addActionListener(new ActionListener() {
			public void  actionPerformed(ActionEvent e) {
				
				int index = assignTechnology();
				
				if(index >= 0) {
					University university = unassignedUniversities.get(index);
					university.assignResearch(new WorkerResearchCommand(tech,-1,Technology.WorkerDensity));
				}
			}
		});
		
		workerRadiusButton.addActionListener(new ActionListener() {
			public void  actionPerformed(ActionEvent e) {
		
				int index = assignTechnology();
				
				if(index >= 0) {
					University university = unassignedUniversities.get(index);
					university.assignResearch(new WorkerResearchCommand(tech,-1,Technology.WorkerRadius));
					unassignedUniversities.remove(university);
				}
			}
		});
		
		productionRateButton.addActionListener(new ActionListener() {
			public void  actionPerformed(ActionEvent e) {
				
				int index = assignTechnology();
				
				if(index >= 0) {
					University university = unassignedUniversities.get(index);
					int currentProduction = productionComboBox.getSelectedIndex();
					if(currentProduction == oreProductionIndex) {
						university.assignResearch(new WorkerResearchCommand(tech,-1,Technology.OreProduction));
					}
					
					if(currentProduction == energyProductionIndex) {
						university.assignResearch(new WorkerResearchCommand(tech,-1,Technology.EnergyProduction));
					}
					
					if(currentProduction == foodProductionIndex) {
						university.assignResearch(new WorkerResearchCommand(tech,-1,Technology.FoodProduction));
					}
					
					if(currentProduction == workerProductionIndex) {
						university.assignResearch(new WorkerResearchCommand(tech,-1,Technology.Breeding));
					}
					
					if(currentProduction == soldierProductionIndex) {
						university.assignResearch(new WorkerResearchCommand(tech,-1,Technology.SoldierTraining));
					}
					
					if(currentProduction == explorerProductionIndex) {
						university.assignResearch(new WorkerResearchCommand(tech,-1,Technology.ExplorerTraining));
					}
					
					
				}
			}
		});
		
		unitEfficiencyButton.addActionListener(new ActionListener() {
			public void  actionPerformed(ActionEvent e) {
				
				int index = assignTechnology();
				
				if(index >= 0) {
					University university = unassignedUniversities.get(index);
					int currentType = unitComboBox.getSelectedIndex() + 1;
					university.assignResearch(new UnitResearchCommand(tech,currentType,Technology.Upkeep));
				}
			}
		});
		
		unitHealthButton.addActionListener(new ActionListener() {
			public void  actionPerformed(ActionEvent e) {
				
				int index = assignTechnology();
				
				if(index >= 0) {
					University university = unassignedUniversities.get(index);
					int currentType = unitComboBox.getSelectedIndex() + 1;
					university.assignResearch(new UnitResearchCommand(tech,currentType,Technology.Health));
				}
			}
		});
		
		unitMovementButton.addActionListener(new ActionListener() {
			public void  actionPerformed(ActionEvent e) {
				
				int index = assignTechnology();
				
				if(index >= 0) {
					University university = unassignedUniversities.get(index);
					int currentType = unitComboBox.getSelectedIndex() + 1;
					university.assignResearch(new UnitResearchCommand(tech,currentType,Technology.Movement));
				}
			}
		});
		
		unitAttackButton.addActionListener(new ActionListener() {
			public void  actionPerformed(ActionEvent e) {
				
				int index = assignTechnology();
				
				if(index >= 0) {
					University university = unassignedUniversities.get(index);
					int currentType = unitComboBox.getSelectedIndex() + 1;
					university.assignResearch(new UnitResearchCommand(tech,currentType,Technology.OffensiveDamage));
				}
			}
		});
		
		unitDefenseButton.addActionListener(new ActionListener() {
			public void  actionPerformed(ActionEvent e) {
				
				int index = assignTechnology();
				
				if(index >= 0) {
					University university = unassignedUniversities.get(index);
					int currentType = unitComboBox.getSelectedIndex() + 1;
					university.assignResearch(new UnitResearchCommand(tech,currentType,Technology.DefensiveDamage));
				}
			}
		});
		
		unitVisibilityRadiusButton.addActionListener(new ActionListener() {
			public void  actionPerformed(ActionEvent e) {
				
				int index = assignTechnology();
				
				if(index >= 0) {
					University university = unassignedUniversities.get(index);
					int currentType = unitComboBox.getSelectedIndex() + 1;
					university.assignResearch(new UnitResearchCommand(tech,currentType,Technology.InfluenceRadius));
				}
			}
		});
		
		unitArmorButton.addActionListener(new ActionListener() {
			public void  actionPerformed(ActionEvent e) {
				
				int index = assignTechnology();
				
				if(index >= 0) {
					University university = unassignedUniversities.get(index);
					int currentType = unitComboBox.getSelectedIndex() + 1;
					university.assignResearch(new UnitResearchCommand(tech,currentType,Technology.Armor));
				}
			}
		});
		
		structureEfficiencyButton.addActionListener(new ActionListener() {
			public void  actionPerformed(ActionEvent e) {
				
				int index = assignTechnology();
				
				if(index >= 0) {
					University university = unassignedUniversities.get(index);
					int currentType = structureComboBox.getSelectedIndex() + 1;
					university.assignResearch(new StructureResearchCommand(tech,currentType,Technology.Upkeep));
				}
			}
		});
		
		structureHealthButton.addActionListener(new ActionListener() {
			public void  actionPerformed(ActionEvent e) {
				
				int index = assignTechnology();
				
				if(index >= 0) {
					University university = unassignedUniversities.get(index);
					int currentType = structureComboBox.getSelectedIndex() + 1;
					university.assignResearch(new StructureResearchCommand(tech,currentType,Technology.Health));
				}
			}
		});
		
		structureMovementButton.addActionListener(new ActionListener() {
			public void  actionPerformed(ActionEvent e) {
				
				int index = assignTechnology();
				
				if(index >= 0) {
					University university = unassignedUniversities.get(index);
					int currentType = structureComboBox.getSelectedIndex() + 1;
					university.assignResearch(new StructureResearchCommand(tech,currentType,Technology.Movement));
				}
			}
		});
		
		structureAttackButton.addActionListener(new ActionListener() {
			public void  actionPerformed(ActionEvent e) {
				
				int index = assignTechnology();
				
				if(index >= 0) {
					University university = unassignedUniversities.get(index);
					int currentType = structureComboBox.getSelectedIndex() + 1;
					university.assignResearch(new StructureResearchCommand(tech,currentType,Technology.OffensiveDamage));
				}
			}
		});
		
		structureDefenseButton.addActionListener(new ActionListener() {
			public void  actionPerformed(ActionEvent e) {
				
				int index = assignTechnology();
				
				if(index >= 0) {
					University university = unassignedUniversities.get(index);
					int currentType = structureComboBox.getSelectedIndex() + 1;
					university.assignResearch(new StructureResearchCommand(tech,currentType,Technology.DefensiveDamage));
				}
			}
		});
		
		structureVisibilityRadiusButton.addActionListener(new ActionListener() {
			public void  actionPerformed(ActionEvent e) {
				
				int index = assignTechnology();
				
				if(index >= 0) {
					University university = unassignedUniversities.get(index);
					int currentType = structureComboBox.getSelectedIndex() + 1;
					university.assignResearch(new StructureResearchCommand(tech,currentType,Technology.InfluenceRadius));
				}
			}
		});
		
		structureArmorButton.addActionListener(new ActionListener() {
			public void  actionPerformed(ActionEvent e) {
				
				int index = assignTechnology();
				
				if(index >= 0) {
					University university = unassignedUniversities.get(index);
					int currentType = structureComboBox.getSelectedIndex() + 1;
					university.assignResearch(new StructureResearchCommand(tech,currentType,Technology.Armor));
				}
			}
		});
		
		unitComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JComboBox cb = (JComboBox) e.getSource();
				int currentUnitType = cb.getSelectedIndex() + 1;
				
				if(unassignedUniversities.size() > 0) {
					unitEfficiencyButton.setEnabled(true);
					if(tech.getUnitStatCurrentLevel(currentUnitType, Technology.Upkeep) > 1) {
						unitHealthButton.setEnabled(true);
						unitMovementButton.setEnabled(true);
					}
					if(tech.getUnitStatCurrentLevel(currentUnitType, Technology.Health) > 1 ) {
						unitAttackButton.setEnabled(true);
						unitDefenseButton.setEnabled(true);
					}
					if(tech.getUnitStatCurrentLevel(currentUnitType, Technology.Movement) > 1) {
						unitVisibilityRadiusButton.setEnabled(true);
						unitArmorButton.setVisible(true);
					}
				}
				else {
					unitEfficiencyButton.setEnabled(false);
					unitHealthButton.setEnabled(false);
					unitMovementButton.setEnabled(false);
					unitAttackButton.setEnabled(false);
					unitDefenseButton.setEnabled(false);
					unitVisibilityRadiusButton.setEnabled(false);
					unitArmorButton.setVisible(false);
				}
	
			}
		});
		
		
		structureComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JComboBox cb = (JComboBox)e.getSource();
				int currentStructureType = cb.getSelectedIndex()+1;
				
				if(unassignedUniversities.size() > 0) {
					structureEfficiencyButton.setEnabled(true);
					if(tech.getStructureStatsCurrentLevel(currentStructureType, Technology.Upkeep) > 1) {
						structureHealthButton.setEnabled(true);
						structureMovementButton.setEnabled(true);
					}
					if(tech.getStructureStatsCurrentLevel(currentStructureType, Technology.Health) > 1 ) {
						structureAttackButton.setEnabled(true);
						structureDefenseButton.setEnabled(true);
					}
					if(tech.getStructureStatsCurrentLevel(currentStructureType, Technology.Movement) > 1) {
						structureVisibilityRadiusButton.setEnabled(true);
						structureArmorButton.setVisible(true);
					}
				}
				else {
					structureEfficiencyButton.setEnabled(false);
					structureHealthButton.setEnabled(false);
					structureMovementButton.setEnabled(false);
					structureAttackButton.setEnabled(false);
					structureDefenseButton.setEnabled(false);
					structureVisibilityRadiusButton.setEnabled(false);
					structureArmorButton.setVisible(false);
				}
			}
		});
	}
	
	public static void main(String args[]) {
		JFrame frame = new JFrame();
		frame.setSize(900,1800);
		
		frame.add(new TechnologyViewport(900,1800));
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void udpate(Technology tech) {
		// TODO Auto-generated method stub
		this.tech = tech;
	}
	
	public void displayTechLevels(Technology tech) {
		
		workerDensityButton.setText("Worker Density Level: " + tech.getWorkerStatsCurrentLevel(Technology.WorkerDensity));
		workerRadiusButton.setText("Worker Radius Level: " + tech.getWorkerStatsCurrentLevel(Technology.WorkerRadius));
		
		int currentProductionType = productionComboBox.getSelectedIndex();
		if(currentProductionType == oreProductionIndex) {
			productionRateButton.setText("Production Rate Level: " + tech.getWorkerStatsCurrentLevel(Technology.OreProduction));
		}
		
		if(currentProductionType == energyProductionIndex) {
			productionRateButton.setText("Production Rate Level: " + tech.getWorkerStatsCurrentLevel(Technology.EnergyProduction));
		}
		
		if(currentProductionType == foodProductionIndex) {
			productionRateButton.setText("Production Rate Level: " + tech.getWorkerStatsCurrentLevel(Technology.FoodProduction));
		}
		
		if(currentProductionType == workerProductionIndex) {
			productionRateButton.setText("Production Rate Level: " + tech.getWorkerStatsCurrentLevel(Technology.Breeding));
		}
		
		if(currentProductionType == soldierProductionIndex) {
			productionRateButton.setText("Production Rate Level: " + tech.getWorkerStatsCurrentLevel(Technology.SoldierTraining));
		}
		
		if(currentProductionType == explorerProductionIndex) {
			productionRateButton.setText("Production Rate Level: " + tech.getWorkerStatsCurrentLevel(Technology.ExplorerTraining));
		}
		
		int currentUnitType = unitComboBox.getSelectedIndex() + 1;
		
		unitEfficiencyButton.setText("Efficiency Level: " + tech.getUnitStatCurrentLevel(currentUnitType, Technology.Upkeep));
		unitHealthButton.setText("Health Level: " + tech.getUnitStatCurrentLevel(currentUnitType, Technology.Health));
		unitMovementButton.setText("Movement Level: " + tech.getUnitStatCurrentLevel(currentUnitType, Technology.Movement));
		unitAttackButton.setText("Attack Level: " + tech.getUnitStatCurrentLevel(currentUnitType, Technology.OffensiveDamage));
		unitDefenseButton.setText("Defense Level: " + tech.getUnitStatCurrentLevel(currentUnitType, Technology.DefensiveDamage));
		unitVisibilityRadiusButton.setText("Visibility Radius Level: " + tech.getUnitStatCurrentLevel(currentUnitType, Technology.InfluenceRadius));
		unitArmorButton.setText("Armor Level: " + tech.getUnitStatCurrentLevel(currentUnitType, Technology.Armor));
		
		int currentStructureType = structureComboBox.getSelectedIndex() + 1;
		
		structureEfficiencyButton.setText("Efficiency Level: " + tech.getStructureStatsCurrentLevel(currentStructureType, Technology.Upkeep));
		structureHealthButton.setText("Health Level: " + tech.getStructureStatsCurrentLevel(currentStructureType, Technology.Health));
		structureMovementButton.setText("Movement Level: " + tech.getStructureStatsCurrentLevel(currentStructureType, Technology.Movement));
		structureAttackButton.setText("Attack Level: " + tech.getStructureStatsCurrentLevel(currentStructureType, Technology.OffensiveDamage));
		structureDefenseButton.setText("Defense Level: " + tech.getStructureStatsCurrentLevel(currentStructureType, Technology.DefensiveDamage));
		structureVisibilityRadiusButton.setText("Visibility Radius Level: " + tech.getStructureStatsCurrentLevel(currentStructureType, Technology.InfluenceRadius));
		structureArmorButton.setText("Armor Level: " + tech.getStructureStatsCurrentLevel(currentStructureType, Technology.Armor));
	}

	@Override
	public void update(University university) {
		// TODO Auto-generated method stub

		if(university.isAssigned()) {
			unassignedUniversities.remove(university);
		} 
		else {
			unassignedUniversities.add(university);
		}
		
		if(unassignedUniversities.size() > 0) {
			enableButtons();
		} 
		else {
			disableButtons();
		}
	}
	
	private void enableButtons() {
		workerDensityButton.setEnabled(true);
		if(tech.getWorkerStatsCurrentLevel(Technology.WorkerDensity) > 1) workerRadiusButton.setEnabled(true);
		productionRateButton.setEnabled(true);
		unitEfficiencyButton.setEnabled(true);
		structureEfficiencyButton.setEnabled(true);
		
		
		int currentUnitType = unitComboBox.getSelectedIndex() + 1;
		if(tech.getUnitStatCurrentLevel(currentUnitType, Technology.Upkeep) > 1) {
			unitHealthButton.setEnabled(true);
			unitMovementButton.setEnabled(true);
		}
		if(tech.getUnitStatCurrentLevel(currentUnitType, Technology.Health) > 1 ) {
			unitAttackButton.setEnabled(true);
			unitDefenseButton.setEnabled(true);
		}
		if(tech.getUnitStatCurrentLevel(currentUnitType, Technology.Movement) > 1) {
			unitVisibilityRadiusButton.setEnabled(true);
			unitArmorButton.setVisible(true);
		}
		
		int currentStructureType = structureComboBox.getSelectedIndex() + 1;
		if(tech.getStructureStatsCurrentLevel(currentStructureType, Technology.Upkeep) > 1) {
			structureHealthButton.setEnabled(true);
			structureMovementButton.setEnabled(true);
		}
		if(tech.getStructureStatsCurrentLevel(currentStructureType, Technology.Health) > 1 ) {
			structureAttackButton.setEnabled(true);
			structureDefenseButton.setEnabled(true);
		}
		if(tech.getStructureStatsCurrentLevel(currentStructureType, Technology.Movement) > 1) {
			structureVisibilityRadiusButton.setEnabled(true);
			structureArmorButton.setVisible(true);
		}
	}
	
	private void disableButtons() {
		workerDensityButton.setEnabled(false);
		workerRadiusButton.setEnabled(false);
		productionRateButton.setEnabled(false);
		
		unitEfficiencyButton.setEnabled(false);
		unitHealthButton.setEnabled(false);
		unitMovementButton.setEnabled(false);
		unitAttackButton.setEnabled(false);
		unitDefenseButton.setEnabled(false);
		unitVisibilityRadiusButton.setEnabled(false);
		unitArmorButton.setEnabled(false);
		
		structureEfficiencyButton.setEnabled(true);
		structureHealthButton.setEnabled(false);
		structureMovementButton.setEnabled(false);
		structureAttackButton.setEnabled(false);
		structureDefenseButton.setEnabled(false);
		structureVisibilityRadiusButton.setEnabled(false);
		structureArmorButton.setEnabled(false);
	}
	
	private int assignTechnology() {
		
		ArrayList<String> universityNames = new ArrayList<String>(unassignedUniversities.size());
		
		for(University university: unassignedUniversities) {
			universityNames.add("University " + university.getID().getInstanceNumber());
		}
		
		String input = (String) JOptionPane.showInputDialog(this, "Select the University to assign this to", "Assign University",JOptionPane.PLAIN_MESSAGE,null,(String[]) universityNames.toArray(),universityNames.get(0));
		if(input == null || input.length() == 0) return -1; 
		
		return universityNames.indexOf(input);
	}
}
