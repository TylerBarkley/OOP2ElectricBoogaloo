package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TechnologyViewport extends JPanel {

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
	
	private int unitLevels[][] = {{1,1,1,1,1,1,1},{1,1,1,1,1,1,1},{1,1,1,1,1,1,1},{1,1,1,1,1,1,1}};
	private int structureLevels[][] = {{1,1,1,1,1,1,1},{1,1,1,1,1,1,1},{1,1,1,1,1,1,1},{1,1,1,1,1,1,1},{1,1,1,1,1,1,1},{1,1,1,1,1,1,1},{1,1,1,1,1,1,1}};
	
	public static final String UNITNAMES[]= {"COLONIST","EXPLORER","MELEE","RANGE"};
	public static final String STRUCTURENAMES[] = {"CAPITAL","FARM","FORT","MINE","OBSERVATION TOWER","POWER PLANT","UNIVERSITY"};
	
	
	public TechnologyViewport(int width, int height) {
		
		this.width = width;
		this.height = height;
		this.setSize(width, height);
		
		workerDensityButton = new JButton(" Workder Density Level: 1");
		workerRadiusButton = new JButton(" Worker Radius Level: 0");
		productionRateButton = new JButton(" Production Rate Level: 1");
		
		unitEfficiencyButton = new JButton(" Efficienty Level: 1");
		unitHealthButton = new JButton(" Health Level: 1");
		unitMovementButton = new JButton(" Movement Level: 1");
		unitAttackButton = new JButton(" Attack Level: 1");
		unitDefenseButton = new JButton(" Defense Level: 1");
		unitVisibilityRadiusButton = new JButton(" Visibility Radius Level: 1");
		unitArmorButton = new JButton("Armor level: 1");
		
		structureEfficiencyButton = new JButton(" Efficienty Level: 1");
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
		
		unitComboBox = new JComboBox(UNITNAMES);
		structureComboBox = new JComboBox(STRUCTURENAMES);
		
		unitComboBox.setAlignmentX(Component.CENTER_ALIGNMENT);
		unitComboBox.setSelectedIndex(0);
		unitComboBox.setPreferredSize(new Dimension(200,25));
		unitComboBox.setMaximumSize(new Dimension(200,25));
		
		structureComboBox.setAlignmentX(Component.CENTER_ALIGNMENT);
		structureComboBox.setSelectedIndex(0);
		structureComboBox.setPreferredSize(new Dimension(200,25));
		structureComboBox.setMaximumSize(new Dimension(200,25));
		
		workerDensityButton.setEnabled(true);
		workerRadiusButton.setEnabled(false);
		productionRateButton.setEnabled(false);
		
		unitEfficiencyButton.setEnabled(true);
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
		
		JLabel workerTitle = new JLabel("Worker and Productions Tree");
		workerTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		workerPanel.add(Box.createVerticalStrut(20));
		workerPanel.add(workerTitle);
		workerPanel.add(Box.createVerticalStrut(20));
		workerPanel.add(workerDensityButton);
		workerPanel.add(Box.createVerticalStrut(20));
		
		JPanel twoWorkerButtonPanel = new JPanel();
		twoWorkerButtonPanel.add(workerRadiusButton);
		twoWorkerButtonPanel.add(Box.createHorizontalStrut(20));
		twoWorkerButtonPanel.add(productionRateButton);
		
		workerPanel.add(twoWorkerButtonPanel);
		
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
		this.add(treePanel);
		
		
	}
	
	public void addActionListeners() {
		
		workerDensityButton.addActionListener(new ActionListener() {
			public void  actionPerformed(ActionEvent e) {
				
				if(workerDensityLevel < maxLevel) {
					workerDensityLevel++;
					workerDensityButton.setText("Worker Density Level: " + workerDensityLevel);
					workerRadiusButton.setEnabled(true);
					productionRateButton.setEnabled(true);
				}
			}
		});
		
		workerRadiusButton.addActionListener(new ActionListener() {
			public void  actionPerformed(ActionEvent e) {
		
				if(workerRadiusLevel < maxLevel) {
					workerRadiusLevel++;
					workerRadiusButton.setText("Worker Radius Level: " + workerRadiusLevel);
				}
			}
		});
		
		productionRateButton.addActionListener(new ActionListener() {
			public void  actionPerformed(ActionEvent e) {
				
				if(productionRateLevel < maxLevel) {
					productionRateLevel++;
					productionRateButton.setText("Production Rate Level: " + productionRateLevel);
				}
			}
		});
		
		unitEfficiencyButton.addActionListener(new ActionListener() {
			public void  actionPerformed(ActionEvent e) {
				
				if(unitLevels[unitComboBox.getSelectedIndex()][efficiencyIndex] < maxLevel) {
					unitLevels[unitComboBox.getSelectedIndex()][efficiencyIndex]++;
					unitEfficiencyButton.setText("Efficiency Level: " + unitLevels[unitComboBox.getSelectedIndex()][efficiencyIndex] );
					unitHealthButton.setEnabled(true);
					unitMovementButton.setEnabled(true);
				}
			}
		});
		
		unitHealthButton.addActionListener(new ActionListener() {
			public void  actionPerformed(ActionEvent e) {
				
				if(unitLevels[unitComboBox.getSelectedIndex()][healthIndex] < maxLevel) {
					unitLevels[unitComboBox.getSelectedIndex()][healthIndex]++;
					unitHealthButton.setText("Health Level: " + unitLevels[unitComboBox.getSelectedIndex()][healthIndex] );
					unitAttackButton.setEnabled(true);
					unitDefenseButton.setEnabled(true);
				}
			}
		});
		
		unitMovementButton.addActionListener(new ActionListener() {
			public void  actionPerformed(ActionEvent e) {
				
				if(unitLevels[unitComboBox.getSelectedIndex()][movementIndex] < maxLevel) {
					unitLevels[unitComboBox.getSelectedIndex()][movementIndex]++;
					unitMovementButton.setText("Movement Level: " + unitLevels[unitComboBox.getSelectedIndex()][movementIndex] );
					unitVisibilityRadiusButton.setEnabled(true);
					unitArmorButton.setEnabled(true);
				}
			}
		});
		
		unitAttackButton.addActionListener(new ActionListener() {
			public void  actionPerformed(ActionEvent e) {
			
				if(unitLevels[unitComboBox.getSelectedIndex()][attackIndex] < maxLevel) {
					unitLevels[unitComboBox.getSelectedIndex()][attackIndex]++;
					unitAttackButton.setText("Attack Level: " + unitLevels[unitComboBox.getSelectedIndex()][attackIndex] );
				}
			}
		});
		
		unitDefenseButton.addActionListener(new ActionListener() {
			public void  actionPerformed(ActionEvent e) {
				
				if(unitLevels[unitComboBox.getSelectedIndex()][defenseIndex] < maxLevel) {
					unitLevels[unitComboBox.getSelectedIndex()][defenseIndex]++;
					unitDefenseButton.setText("Defense Level: " + unitLevels[unitComboBox.getSelectedIndex()][defenseIndex] );
				}
			}
		});
		
		unitVisibilityRadiusButton.addActionListener(new ActionListener() {
			public void  actionPerformed(ActionEvent e) {
				
				if(unitLevels[unitComboBox.getSelectedIndex()][visibilityRadiusIndex] < maxLevel) {
					unitLevels[unitComboBox.getSelectedIndex()][visibilityRadiusIndex]++;
					unitVisibilityRadiusButton.setText("Visibility Radius Level: " + unitLevels[unitComboBox.getSelectedIndex()][visibilityRadiusIndex] );
				}
			}
		});
		
		unitArmorButton.addActionListener(new ActionListener() {
			public void  actionPerformed(ActionEvent e) {
				
				if(unitLevels[unitComboBox.getSelectedIndex()][armorIndex] < maxLevel) {
					unitLevels[unitComboBox.getSelectedIndex()][armorIndex]++;
					unitArmorButton.setText("Armor Level: " + unitLevels[unitComboBox.getSelectedIndex()][armorIndex] );
				}
			}
		});
		
		structureEfficiencyButton.addActionListener(new ActionListener() {
			public void  actionPerformed(ActionEvent e) {
				
				if(structureLevels[structureComboBox.getSelectedIndex()][efficiencyIndex] < maxLevel) {
					structureLevels[structureComboBox.getSelectedIndex()][efficiencyIndex]++;
					structureEfficiencyButton.setText("Efficiency Level: " + structureLevels[structureComboBox.getSelectedIndex()][efficiencyIndex] );
					structureHealthButton.setEnabled(true);
					structureMovementButton.setEnabled(true);
				}
			}
		});
		
		structureHealthButton.addActionListener(new ActionListener() {
			public void  actionPerformed(ActionEvent e) {
				
				if(structureLevels[structureComboBox.getSelectedIndex()][healthIndex] < maxLevel) {
					structureLevels[structureComboBox.getSelectedIndex()][healthIndex]++;
					structureHealthButton.setText("Health Level: " + structureLevels[structureComboBox.getSelectedIndex()][healthIndex] );
					structureAttackButton.setEnabled(true);
					structureDefenseButton.setEnabled(true);
				}
			}
		});
		
		structureMovementButton.addActionListener(new ActionListener() {
			public void  actionPerformed(ActionEvent e) {
				
				if(structureLevels[structureComboBox.getSelectedIndex()][movementIndex] < maxLevel) {
					structureLevels[structureComboBox.getSelectedIndex()][movementIndex]++;
					structureMovementButton.setText("Movement Level: " + structureLevels[structureComboBox.getSelectedIndex()][movementIndex] );
					structureVisibilityRadiusButton.setEnabled(true);
					structureArmorButton.setEnabled(true);
				}
			}
		});
		
		structureAttackButton.addActionListener(new ActionListener() {
			public void  actionPerformed(ActionEvent e) {
			
				if(structureLevels[structureComboBox.getSelectedIndex()][attackIndex] < maxLevel) {
					structureLevels[structureComboBox.getSelectedIndex()][attackIndex]++;
					structureAttackButton.setText("Attack Level: " + structureLevels[structureComboBox.getSelectedIndex()][attackIndex] );
				}
			}
		});
		
		structureDefenseButton.addActionListener(new ActionListener() {
			public void  actionPerformed(ActionEvent e) {
				
				if(structureLevels[structureComboBox.getSelectedIndex()][defenseIndex] < maxLevel) {
					structureLevels[structureComboBox.getSelectedIndex()][defenseIndex]++;
					structureDefenseButton.setText("Defense Level: " + structureLevels[structureComboBox.getSelectedIndex()][defenseIndex] );
				}
			}
		});
		
		structureVisibilityRadiusButton.addActionListener(new ActionListener() {
			public void  actionPerformed(ActionEvent e) {
				
				if(structureLevels[structureComboBox.getSelectedIndex()][visibilityRadiusIndex] < maxLevel) {
					structureLevels[structureComboBox.getSelectedIndex()][visibilityRadiusIndex]++;
					structureVisibilityRadiusButton.setText("Visibility Radius Level: " + structureLevels[structureComboBox.getSelectedIndex()][visibilityRadiusIndex] );
				}
			}
		});
		
		structureArmorButton.addActionListener(new ActionListener() {
			public void  actionPerformed(ActionEvent e) {
				
				if(structureLevels[structureComboBox.getSelectedIndex()][armorIndex] < maxLevel) {
					structureLevels[structureComboBox.getSelectedIndex()][armorIndex]++;
					structureArmorButton.setText("Armor Level: " + structureLevels[structureComboBox.getSelectedIndex()][armorIndex] );
				}
			}
		});
		
		unitComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JComboBox unitBox = (JComboBox)e.getSource();
				int currentUnit = unitBox.getSelectedIndex();
				
				unitEfficiencyButton.setText("Efficiency Level: " + unitLevels[currentUnit][efficiencyIndex]);
				unitHealthButton.setText("Health Level: " + unitLevels[currentUnit][healthIndex]);
				unitMovementButton.setText("Movement Level: " + unitLevels[currentUnit][movementIndex]);
				unitAttackButton.setText("Attack Level: " + unitLevels[currentUnit][attackIndex]);
				unitDefenseButton.setText("Defense Level: " + unitLevels[currentUnit][defenseIndex]);
				unitVisibilityRadiusButton.setText("Visibility Radius Level: " + unitLevels[currentUnit][visibilityRadiusIndex]);
				unitArmorButton.setText("Armor Level: " + unitLevels[currentUnit][armorIndex]);
				
				if(unitLevels[currentUnit][efficiencyIndex] > 1) unitHealthButton.setEnabled(true);
				else unitHealthButton.setEnabled(false);
				
				if(unitLevels[currentUnit][efficiencyIndex] > 1) unitMovementButton.setEnabled(true);
				else unitMovementButton.setEnabled(false);
				
				if(unitLevels[currentUnit][healthIndex] > 1) unitAttackButton.setEnabled(true);
				else unitAttackButton.setEnabled(false);
				
				if(unitLevels[currentUnit][healthIndex] > 1) unitDefenseButton.setEnabled(true);
				else unitDefenseButton.setEnabled(false);
				
				if(unitLevels[currentUnit][movementIndex] > 1) unitVisibilityRadiusButton.setEnabled(true);
				else unitVisibilityRadiusButton.setEnabled(false);
				
				if(unitLevels[currentUnit][movementIndex] > 1) unitArmorButton.setEnabled(true);
				else unitArmorButton.setEnabled(false);
			}
		});
		
		structureComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JComboBox structureBox = (JComboBox)e.getSource();
				int currentStructure = structureBox.getSelectedIndex();
				
				structureEfficiencyButton.setText("Efficiency Level: " + structureLevels[currentStructure][efficiencyIndex]);
				structureHealthButton.setText("Health Level: " + structureLevels[currentStructure][healthIndex]);
				structureMovementButton.setText("Movement Level: " + structureLevels[currentStructure][movementIndex]);
				structureAttackButton.setText("Attack Level: " + structureLevels[currentStructure][attackIndex]);
				structureDefenseButton.setText("Defense Level: " + structureLevels[currentStructure][defenseIndex]);
				structureVisibilityRadiusButton.setText("Visibility Radius Level: " + structureLevels[currentStructure][visibilityRadiusIndex]);
				structureArmorButton.setText("Armor Level: " + structureLevels[currentStructure][armorIndex]);
				
				if(structureLevels[currentStructure][efficiencyIndex] > 1) structureHealthButton.setEnabled(true);
				else structureHealthButton.setEnabled(false);
				
				if(structureLevels[currentStructure][efficiencyIndex] > 1) structureMovementButton.setEnabled(true);
				else structureMovementButton.setEnabled(false);
				
				if(structureLevels[currentStructure][healthIndex] > 1) structureAttackButton.setEnabled(true);
				else structureAttackButton.setEnabled(false);
				
				if(structureLevels[currentStructure][healthIndex] > 1) structureDefenseButton.setEnabled(true);
				else structureDefenseButton.setEnabled(false);
				
				if(structureLevels[currentStructure][movementIndex] > 1) structureVisibilityRadiusButton.setEnabled(true);
				else structureVisibilityRadiusButton.setEnabled(false);
				
				if(structureLevels[currentStructure][movementIndex] > 1) structureArmorButton.setEnabled(true);
				else structureArmorButton.setEnabled(false);
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
}
