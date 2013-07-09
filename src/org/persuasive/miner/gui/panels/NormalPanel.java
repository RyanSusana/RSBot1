package org.persuasive.miner.gui.panels;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;

import org.persuasive.miner.AEMiner;
import org.persuasive.miner.gui.MainUI;
import org.persuasive.miner.wrappers.Rock;
import org.persuasive.miner.wrappers.RockGroup;
import org.persuasive.miner.wrappers.area.Mine;
import org.persuasive.miner.wrappers.area.PowerMine;

public class NormalPanel extends JPanel {
	/**
	 *  
	 */
	private static final long serialVersionUID = 1L;
	private static JTextField textField;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private ArrayList<String> listLeft = asList();
	private static ArrayList<String> listRight = new ArrayList<String>(0);

	public ArrayList<String> asList() {
		ArrayList<String> x = new ArrayList<String>();
		for (int i = 0; i < listbefore().length; i++) {
			x.add(listbefore()[i]);
		}
		return x;
	}

	public static int getTillDrop() {
		int i = 28;
		try {
			i = Integer.parseInt(textField_1.getText());
		} catch (Exception e) {

		}
		if (i < 1) {
			return 28;
		} else if (i > 28) {
			return 28;
		}
		return i;
	}

	public static RockGroup getRocks() {
		RockGroup x = new RockGroup();
		for (int i = 0; i < listRight.size(); i++) {
			x.add(Rock.valueOf(listRight.get(i)));
		}
		return x;
	}

	public static int radiusAround() {
		int i = 4;
		try {
			i = Integer.parseInt(textField.getText());
		} catch (Exception e) {

		}
		return i;
	}

	public void start() {
		AEMiner.group = getRocks();
		String method = MethodBox.getSelectedItem().toString().toLowerCase();
		if (method.contains("bank")) {
			AEMiner.powermine = false;
			AEMiner.mine = (Mine) (PresetCombo.getSelectedItem());
			if (RadiusBox.isSelected()) {
				AEMiner.mine.setMineArea(AEMiner
						.getRadiusAroundPlayer(radiusAround()));
			}
		} else {
			AEMiner.powermine = true;
			if (PresetBox.isSelected()) {
				Mine x = (Mine) (PresetCombo.getSelectedItem());
				PowerMine m = new PowerMine(x.getMineArea(), getTillDrop());
				AEMiner.pmine = m;
			} else {
				PowerMine m = new PowerMine(
						AEMiner.getRadiusAroundPlayer(radiusAround()),
						getTillDrop());
				AEMiner.pmine = m;
			}
		}
		MainUI.start();
	}

	public String[] getListLeft() {
		String[] news = new String[listLeft.size()];
		for (int i = 0; i < listLeft.size(); i++) {
			news[i] = (String) (listLeft.get(i));
		}
		return news;
	}

	public List<String> leftRemove(int i) {

		ArrayList<String> x = new ArrayList<String>(0);
		for (int j = 0; i < listLeft.size(); i++) {
			if (!listLeft.get(j).equals(listLeft.get(i))) {
				x.add((String) listLeft.get(j));
			}
		}

		state();
		return x;
	}

	public String[] getListRight() {
		String[] news = new String[listRight.size()];
		for (int i = 0; i < listRight.size(); i++) {
			news[i] = listRight.get(i);
		}
		return news;
	}

	public String[] listbefore() {
		String[] news = new String[Rock.values().length];
		for (int i = 0; i < Rock.values().length; i++) {
			news[i] = Rock.values()[i].name();

		}
		return news;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void state() {
		String method = MethodBox.getSelectedItem().toString();
		boolean usingPres = PresetBox.isSelected();
		if (method.equals("Bank")) {
			textField.setText("");
			textField.setEnabled(true);
			textField_1.setEnabled(false);
			textField_1.setText("");

		} else {
			RadiusBox.setEnabled(true);
			PresetBox.setEnabled(true);
			textField.setEnabled(true);
			textField_1.setEnabled(true);
			if (usingPres) {
				RadiusBox.setSelected(false);
			} else {
				PresetBox.setSelected(false);
			}
		}
		list.setModel(new AbstractListModel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			String[] values = getListRight();

			public int getSize() {
				return values.length;
			}

			public Object getElementAt(int index) {
				return values[index];
			}
		});
		AddTo.setModel(new AbstractListModel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			String[] values = getListLeft();

			public int getSize() {
				return values.length;
			}

			public Object getElementAt(int index) {
				return values[index];
			}
		});
	}

	/**
	 * Create the panel.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public NormalPanel() {

		JLabel lblNormalAreas = new JLabel("Normal Areas");
		lblNormalAreas.setFont(new Font("Tahoma", Font.BOLD, 15));

		JScrollPane scrollPane = new JScrollPane();

		JButton btnAdd = new JButton("Add ->");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (AddTo.isSelectionEmpty()) {
					return;
				}
				int i = AddTo.getSelectedIndex();
				String iS = getListLeft()[i];
				listLeft.remove(i);
				listRight.add(iS);
				state();
			}
		});

		list = new JList();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setModel(new AbstractListModel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			String[] values = getListRight();

			public int getSize() {
				return values.length;
			}

			public Object getElementAt(int index) {
				return values[index];
			}
		});

		JButton button = new JButton("<-Remove");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (list.isSelectionEmpty()) {
					return;
				}
				int i = list.getSelectedIndex();
				String iS = getListRight()[i];
				listRight.remove(iS);
				listLeft.add(iS);
				state();
			}
		});

		MethodBox = new JComboBox();
		MethodBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				state();
			}
		});
		MethodBox.setModel(new DefaultComboBoxModel(new String[] { "Bank",
				"Powermine" }));

		JLabel lblMethod = new JLabel("Method:");

		JLabel label = new JLabel("");

		PresetCombo = new JComboBox();

		PresetCombo.setModel(new DefaultComboBoxModel(Mine.values()));
		PresetCombo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				state();
			}
		});

		JLabel lblPresetArea = new JLabel("Preset Area:");

		JLabel lblRadiusAroundMe = new JLabel("Radius Around:");

		textField = new JTextField();
		textField.setColumns(10);

		JLabel lblTiles = new JLabel("Tiles");

		PresetBox = new JCheckBox("");
		PresetBox.setSelected(true);
		PresetBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				state();
			}
		});
		buttonGroup.add(PresetBox);

		RadiusBox = new JCheckBox("");
		RadiusBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				state();
			}
		});
		buttonGroup.add(RadiusBox);

		JLabel lblAllRocks = new JLabel("All Rocks:");

		JLabel lblSelectedRocksprioritized = new JLabel(
				"Selected rocks(Prioritized):");

		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				start();
			}
		});

		JLabel lblMine = new JLabel("Mine");

		textField_1 = new JTextField();
		textField_1.setColumns(10);

		JLabel lblDropAll = new JLabel(",drop all!");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout
				.setHorizontalGroup(groupLayout
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addContainerGap()
																		.addComponent(
																				label))
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addContainerGap()
																		.addGroup(
																				groupLayout
																						.createParallelGroup(
																								Alignment.TRAILING,
																								false)
																						.addGroup(
																								groupLayout
																										.createSequentialGroup()
																										.addComponent(
																												lblPresetArea)
																										.addGap(18)
																										.addComponent(
																												PresetCombo,
																												GroupLayout.PREFERRED_SIZE,
																												137,
																												GroupLayout.PREFERRED_SIZE))
																						.addGroup(
																								groupLayout
																										.createSequentialGroup()
																										.addComponent(
																												lblRadiusAroundMe,
																												GroupLayout.PREFERRED_SIZE,
																												89,
																												GroupLayout.PREFERRED_SIZE)
																										.addPreferredGap(
																												ComponentPlacement.RELATED,
																												GroupLayout.DEFAULT_SIZE,
																												Short.MAX_VALUE)
																										.addComponent(
																												textField,
																												GroupLayout.PREFERRED_SIZE,
																												55,
																												GroupLayout.PREFERRED_SIZE)
																										.addPreferredGap(
																												ComponentPlacement.RELATED)
																										.addComponent(
																												lblTiles)))
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addGroup(
																				groupLayout
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addComponent(
																								RadiusBox)
																						.addComponent(
																								PresetBox)))
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addContainerGap()
																		.addComponent(
																				lblMethod)
																		.addGap(26)
																		.addGroup(
																				groupLayout
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addGroup(
																								groupLayout
																										.createSequentialGroup()
																										.addGap(10)
																										.addComponent(
																												MethodBox,
																												GroupLayout.PREFERRED_SIZE,
																												154,
																												GroupLayout.PREFERRED_SIZE))
																						.addComponent(
																								lblNormalAreas)))
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addGap(107)
																		.addComponent(
																				btnStart))
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addContainerGap()
																		.addGroup(
																				groupLayout
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addComponent(
																								scrollPane,
																								GroupLayout.PREFERRED_SIZE,
																								110,
																								GroupLayout.PREFERRED_SIZE)
																						.addGroup(
																								groupLayout
																										.createSequentialGroup()
																										.addGap(21)
																										.addComponent(
																												btnAdd))
																						.addComponent(
																								lblAllRocks))
																		.addPreferredGap(
																				ComponentPlacement.UNRELATED)
																		.addGroup(
																				groupLayout
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addGroup(
																								groupLayout
																										.createSequentialGroup()
																										.addGap(10)
																										.addComponent(
																												button))
																						.addGroup(
																								groupLayout
																										.createSequentialGroup()
																										.addGap(8)
																										.addGroup(
																												groupLayout
																														.createParallelGroup(
																																Alignment.LEADING)
																														.addComponent(
																																lblSelectedRocksprioritized)
																														.addComponent(
																																list,
																																GroupLayout.PREFERRED_SIZE,
																																108,
																																GroupLayout.PREFERRED_SIZE)))))
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addContainerGap()
																		.addComponent(
																				lblMine)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				textField_1,
																				GroupLayout.PREFERRED_SIZE,
																				52,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				lblDropAll)))
										.addGap(44)));
		groupLayout
				.setVerticalGroup(groupLayout
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addGap(19)
										.addComponent(lblNormalAreas)
										.addPreferredGap(
												ComponentPlacement.UNRELATED)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(lblMethod)
														.addComponent(
																MethodBox,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addComponent(label)
										.addGap(18)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																groupLayout
																		.createParallelGroup(
																				Alignment.BASELINE)
																		.addComponent(
																				lblPresetArea)
																		.addComponent(
																				PresetCombo,
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE))
														.addComponent(PresetBox))
										.addGap(5)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.LEADING)
														.addComponent(RadiusBox)
														.addGroup(
																groupLayout
																		.createParallelGroup(
																				Alignment.BASELINE)
																		.addComponent(
																				lblRadiusAroundMe)
																		.addComponent(
																				lblTiles)
																		.addComponent(
																				textField,
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE)))
										.addPreferredGap(
												ComponentPlacement.UNRELATED)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(lblMine)
														.addComponent(
																textField_1,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																lblDropAll))
										.addPreferredGap(
												ComponentPlacement.RELATED,
												123, Short.MAX_VALUE)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																lblAllRocks)
														.addComponent(
																lblSelectedRocksprioritized))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addComponent(
																				scrollPane,
																				GroupLayout.PREFERRED_SIZE,
																				165,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				btnAdd))
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addComponent(
																				list,
																				GroupLayout.PREFERRED_SIZE,
																				163,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				button)))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addComponent(btnStart).addGap(20)));

		AddTo = new JList();
		AddTo.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		AddTo.setModel(new AbstractListModel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			String[] values = getListLeft();

			public int getSize() {
				return values.length;
			}

			public Object getElementAt(int index) {
				return values[index];
			}
		});
		scrollPane.setViewportView(AddTo);
		setLayout(groupLayout);
		state();
	}

	@SuppressWarnings("rawtypes")
	JList AddTo;
	@SuppressWarnings("rawtypes")
	JList list;
	@SuppressWarnings("rawtypes")
	static JComboBox PresetCombo;
	@SuppressWarnings("rawtypes")
	static JComboBox MethodBox;
	static JCheckBox PresetBox;
	JCheckBox RadiusBox;
	private static JTextField textField_1;
}
