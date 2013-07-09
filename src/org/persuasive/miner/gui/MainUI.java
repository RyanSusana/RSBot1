package org.persuasive.miner.gui;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

import org.persuasive.miner.AEMiner;
import org.persuasive.miner.gui.panels.NormalPanel;

@SuppressWarnings("serial")
public class MainUI extends JFrame {

	private JPanel contentPane;
	NormalPanel p = new NormalPanel();



	public static void start() {
		if (AEMiner.powermine) {
			AEMiner.LOGGER.log("\nMode                      : Powermine Mode");
			AEMiner.LOGGER.log("Inventory number till drop: "
					+ AEMiner.pmine.getToDropAt());
		} else {
			AEMiner.LOGGER.log("\nMode        : Bank Mode");
			AEMiner.LOGGER.log("Name of area: " + AEMiner.mine.name());

		}
		AEMiner.LOGGER
				.log("\n          Rock List(From most priorities to least priorities):");
		for (int i = 0; i < AEMiner.group.size(); i++) {
			AEMiner.LOGGER.log(AEMiner.group.get(i).name());
		}
		AEMiner.start = true;
		AEMiner.LOGGER
				.log("\nScript has started...Thank you for choosing me.\n\n\n");
		AEMiner.start=true;
	}

	/**
	 * Create the frame.
	 */
	public MainUI() {
		
		setResizable(false);
		setTitle("AEMiner");
		setBounds(100, 100, 316, 535);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(
				Alignment.LEADING).addComponent(tabbedPane,
				GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(
				Alignment.LEADING).addComponent(tabbedPane,
				GroupLayout.DEFAULT_SIZE, 364, Short.MAX_VALUE));
		contentPane.setLayout(gl_contentPane);
		tabbedPane.addTab("Normal Areas", null, p, null);
	}

	JTabbedPane tabbedPane;
}
