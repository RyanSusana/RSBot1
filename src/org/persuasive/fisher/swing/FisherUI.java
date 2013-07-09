package org.persuasive.fisher.swing;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import org.persuasive.fisher.AEFisher;
import org.persuasive.fisher.wrappers.FishMode;
import org.persuasive.fisher.wrappers.FishZone;

import javax.swing.DefaultComboBoxModel;

public class FisherUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtX;
	JComboBox<FishMode> Type;
	JComboBox<String> Method;
	JComboBox<FishZone> Location;
	JCheckBox DropTuna;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FisherUI frame = new FisherUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FisherUI() {
		setResizable(false);
		setTitle("AEFisher by Persuasive");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 393);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);

		JPanel panel = new JPanel();
		tabbedPane.addTab("Regular Locations", null, panel, null);

		JSeparator separator = new JSeparator();

		DropTuna = new JCheckBox("Drop Tuna");
		DropTuna.setToolTipText("Drops Tuna when your inventory is full.");
		DropTuna.setEnabled(false);

		JLabel lblMethod = new JLabel("Method:");

		Type = new JComboBox<FishMode>();
		Type.setToolTipText("What would you like to fish?");

		JLabel lblFishingLocation = new JLabel("Fishing Location:");

		Location = new JComboBox<FishZone>();
		Location.setModel(new DefaultComboBoxModel<FishZone>(FishZone.values()));
		Location.setToolTipText("Where would you like to fish?");

		JLabel lblFish = new JLabel("Fish ");

		txtX = new JTextField();
		txtX.setToolTipText("How much do you want to fish before you drop?");
		txtX.setEditable(false);
		txtX.setColumns(10);

		JLabel lblDropAll = new JLabel("drop all.");

		JLabel lblNewLabel = new JLabel("Fishing Type:");

		Method = new JComboBox<String>();
		Method.setModel(new DefaultComboBoxModel<String>(new String[] {
				"Banking", "Powerfishing" }));
		Method.setToolTipText("How would you like to fish?");

		JSeparator separator_1 = new JSeparator();

		JSeparator separator_2 = new JSeparator();

		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AEFisher.fishMode = (FishMode) (Type.getSelectedItem());
				AEFisher.fishZone = (FishZone) (Location.getSelectedItem());
				final String mtd = Method.getSelectedItem().toString();
				if (mtd.contains("power")) {
					AEFisher.fishMode.setPowerfish(true);
					int x = 28;
					try {
						x = Integer.parseInt(txtX.getText());
					} catch (Exception b) {
						x = 28;
					}
					AEFisher.fishMode.setToDropAt(x);
				}
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel
				.createParallelGroup(Alignment.TRAILING)
				.addGroup(
						gl_panel.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.LEADING)
												.addGroup(
														gl_panel.createSequentialGroup()
																.addGroup(
																		gl_panel.createParallelGroup(
																				Alignment.LEADING)
																				.addGroup(
																						gl_panel.createSequentialGroup()
																								.addComponent(
																										lblMethod)
																								.addPreferredGap(
																										ComponentPlacement.RELATED)
																								.addComponent(
																										separator,
																										GroupLayout.PREFERRED_SIZE,
																										GroupLayout.DEFAULT_SIZE,
																										GroupLayout.PREFERRED_SIZE)
																								.addPreferredGap(
																										ComponentPlacement.RELATED,
																										121,
																										Short.MAX_VALUE)
																								.addComponent(
																										Method,
																										GroupLayout.PREFERRED_SIZE,
																										GroupLayout.DEFAULT_SIZE,
																										GroupLayout.PREFERRED_SIZE))
																				.addGroup(
																						gl_panel.createSequentialGroup()
																								.addComponent(
																										lblFish)
																								.addGap(5)
																								.addComponent(
																										txtX,
																										GroupLayout.PREFERRED_SIZE,
																										GroupLayout.DEFAULT_SIZE,
																										GroupLayout.PREFERRED_SIZE)
																								.addGap(5)
																								.addComponent(
																										lblDropAll))
																				.addGroup(
																						gl_panel.createSequentialGroup()
																								.addComponent(
																										lblNewLabel)
																								.addPreferredGap(
																										ComponentPlacement.RELATED,
																										68,
																										Short.MAX_VALUE)
																								.addComponent(
																										Type,
																										GroupLayout.PREFERRED_SIZE,
																										GroupLayout.DEFAULT_SIZE,
																										GroupLayout.PREFERRED_SIZE))
																				.addGroup(
																						gl_panel.createSequentialGroup()
																								.addComponent(
																										lblFishingLocation)
																								.addPreferredGap(
																										ComponentPlacement.RELATED,
																										97,
																										Short.MAX_VALUE)
																								.addComponent(
																										Location,
																										GroupLayout.PREFERRED_SIZE,
																										GroupLayout.DEFAULT_SIZE,
																										GroupLayout.PREFERRED_SIZE))
																				.addComponent(
																						separator_1,
																						GroupLayout.DEFAULT_SIZE,
																						251,
																						Short.MAX_VALUE))
																.addContainerGap())
												.addGroup(
														gl_panel.createSequentialGroup()
																.addComponent(
																		separator_2,
																		GroupLayout.DEFAULT_SIZE,
																		251,
																		Short.MAX_VALUE)
																.addContainerGap())
												.addGroup(
														gl_panel.createSequentialGroup()
																.addComponent(
																		DropTuna)
																.addGap(186))
												.addGroup(
														Alignment.TRAILING,
														gl_panel.createSequentialGroup()
																.addComponent(
																		btnStart)
																.addGap(141)))));
		gl_panel.setVerticalGroup(gl_panel
				.createParallelGroup(Alignment.LEADING)
				.addGroup(
						gl_panel.createSequentialGroup()
								.addGap(10)
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.LEADING)
												.addGroup(
														gl_panel.createParallelGroup(
																Alignment.BASELINE)
																.addComponent(
																		lblMethod)
																.addComponent(
																		Method,
																		GroupLayout.PREFERRED_SIZE,
																		GroupLayout.DEFAULT_SIZE,
																		GroupLayout.PREFERRED_SIZE))
												.addComponent(
														separator,
														GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.LEADING)
												.addGroup(
														gl_panel.createSequentialGroup()
																.addGap(3)
																.addComponent(
																		lblFish))
												.addComponent(
														txtX,
														GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE)
												.addGroup(
														gl_panel.createSequentialGroup()
																.addGap(3)
																.addComponent(
																		lblDropAll)))
								.addGap(18)
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.BASELINE)
												.addComponent(
														lblFishingLocation)
												.addComponent(
														Location,
														GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE))
								.addGap(3)
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.BASELINE)
												.addComponent(lblNewLabel)
												.addComponent(
														Type,
														GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(separator_1,
										GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(DropTuna)
								.addGap(4)
								.addComponent(separator_2,
										GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(btnStart)
								.addContainerGap(29, Short.MAX_VALUE)));
		panel.setLayout(gl_panel);
		Method.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String mes = Method.getSelectedItem().toString().toLowerCase();
				if (mes.contains("bank")) {
					txtX.setText("");
					txtX.setEditable(false);
				} else if (mes.contains("powerfish")) {
					txtX.setEditable(true);
				}
			}
		});
		Location.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				Type.setModel(new DefaultComboBoxModel<FishMode>(
						((FishZone) Location.getSelectedItem())
								.getAvailableModes()));
			}
		});
		Type.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
			}
		});

		JLabel lblAntibanOther = new JLabel("Antiban & Other options");

		JLabel lblCurrentlyNoOther = new JLabel("Currently no other options");
		lblCurrentlyNoOther.setForeground(Color.LIGHT_GRAY);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane
				.setHorizontalGroup(gl_contentPane
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_contentPane
										.createSequentialGroup()
										.addGroup(
												gl_contentPane
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																gl_contentPane
																		.createSequentialGroup()
																		.addContainerGap()
																		.addComponent(
																				lblAntibanOther))
														.addGroup(
																gl_contentPane
																		.createSequentialGroup()
																		.addContainerGap()
																		.addComponent(
																				lblCurrentlyNoOther))
														.addComponent(
																tabbedPane,
																GroupLayout.DEFAULT_SIZE,
																374,
																Short.MAX_VALUE))
										.addContainerGap()));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(
				Alignment.LEADING).addGroup(
				gl_contentPane
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE,
								252, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(lblAntibanOther)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(lblCurrentlyNoOther)
						.addContainerGap(52, Short.MAX_VALUE)));
		contentPane.setLayout(gl_contentPane);
		Type.setModel(new DefaultComboBoxModel<FishMode>(((FishZone) Location
				.getSelectedItem()).getAvailableModes()));
	}
}
