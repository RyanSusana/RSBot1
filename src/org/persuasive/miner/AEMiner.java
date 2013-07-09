package org.persuasive.miner;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import org.persuasive.api.script.framework.Activity;
import org.persuasive.api.script.framework.ActivityBranch;
import org.persuasive.api.script.objects.Logger;
import org.persuasive.api.script.objects.WebPack;
import org.persuasive.miner.activities.Bank;
import org.persuasive.miner.activities.GoToBank;
import org.persuasive.miner.activities.GoToMine;
import org.persuasive.miner.gui.MainUI;
import org.persuasive.miner.wrappers.Rock;
import org.persuasive.miner.wrappers.RockGroup;
import org.persuasive.miner.wrappers.area.Mine;
import org.persuasive.miner.wrappers.area.PowerMine;
import org.powerbot.core.event.events.MessageEvent;
import org.powerbot.core.event.listeners.MessageListener;
import org.powerbot.core.event.listeners.PaintListener;
import org.powerbot.core.script.ActiveScript;
import org.powerbot.game.api.Manifest;
import org.powerbot.game.api.methods.Game;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Skills;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.wrappers.Area;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.node.SceneObject;

@Manifest(authors = { "Persuasive" }, description = "Start the script while logged in", version = 1.0, name = "AEMiner")
public class AEMiner extends ActiveScript implements MessageListener,
		PaintListener, MouseListener {
	public static RockGroup group = new RockGroup();
	public static boolean powermine = false;
	public static Mine mine = null;
	public static PowerMine pmine = null;
	public static final int[] PICKAXE = { 1265, 1267, 1269, 1271, 1273, 1275,
			14099, 14107, 15259, 1831, 1829, 1827, 1825, 1823, 946, 12140,
			12142, 12144, 12146, 14277, 14279, 14281, 14283, 14285, 12788 };
	public static boolean start = false;
	private MainUI gui = null;
	public static final Logger LOGGER = new Logger("AEMiner");
	public static int startExp;
	public static long startTime;
	public static int startLev;
	public static ActivityBranch branch;
	public static String status;
	WebPack web;
	MinerPaint mp;

	@Override
	public void onStop() {
		LOGGER.log("Script stopped...Thank you again for choosing me!");
	}

	@Override
	public void onStart() {

		LOGGER.log("Initializing...");
		if (!Game.isLoggedIn()) {
			LOGGER.log("Please start the script logged in");
			stop();
		}
		web = new WebPack(
				"http://aescripts.comuv.com/AEMiner/faq",
				"http://www.powerbot.org/community/topic/1041918-petition-for-my-script-writer/",
				true);

		startExp = Skills.getExperience(Skills.MINING);
		startTime = System.currentTimeMillis();
		startLev = Skills.getLevel(Skills.MINING);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					gui = new MainUI();
					gui.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		LOGGER.log("GUI loaded!");
		mp = new MinerPaint();
		LOGGER.log("Paint loaded");
	}

	public ActivityBranch branch() {
		return new ActivityBranch(new Activity[] {
				new org.persuasive.miner.activities.Mine(), new GoToBank(),
				new GoToMine(), new Bank() });
	}

	@Override
	public int loop() {
		if (!start) {
			return 100;
		} else {
			if (gui.isVisible()) {
				gui.setVisible(false);
			}
		}

		try {
			if (branch().getCurrentActivity() != null) {
				branch().getCurrentActivity().execute();
			}
		} catch (Exception e) {
			LOGGER.log("Exception in the loop[evaded]...attempting to continue");
		}

		return Random.nextInt(50, 200);
	}

	public static Area getRadiusAroundPlayer(int squareTiles) {
		final Tile t = Players.getLocal().getLocation();
		return new Area(new Tile(t.getX() + squareTiles,
				t.getY() + squareTiles, t.getPlane()), new Tile(t.getX()
				- squareTiles, t.getY() - squareTiles, t.getPlane()));

	}

	@Override
	public void messageReceived(MessageEvent e) {
		String mes = e.getMessage().toLowerCase();
		if (e.getSender() == Players.getLocal().getName()) {
			if (mes.contains("add")) {
				for (Rock value : Rock.values()) {
					if (mes.contains(value.name().toLowerCase())) {
						group.add(value);
						LOGGER.log("Rock added:  " + value.name());
					}
				}
			} else if (mes.contains("remove")) {
				for (Rock value : Rock.values()) {
					if (mes.contains(value.name().toLowerCase())) {
						group.remove(value);
						LOGGER.log("Rock removed:  " + value.name());
					}
				}
			} else if (mes.equals("clear")) {
				group.clear();
				LOGGER.log("RockList cleared! Add more rocks by typing 'add copper' for example");

			} else if (mes.contains("powermine")) {
				LOGGER.log("This function no longer works...\nTry resetting the script or change the options on the GUI 'AiMiner' and hit start!!");
			} else if (mes.contains("guipopup")) {
				start = false;
				gui.setVisible(true);
			}
		}

		if (mes.contains("to mine")) {
			for (Rock i : group.array()) {
				if (mes.contains(i.name().toLowerCase())) {
					i.gained++;
					if (!powermine) {
						i.profit = i.profit + i.getPrice();
					}
				}
			}
		}
	}

	public void onRepaint(Graphics g2) {
		if (!start) {
			return;
		}
		Graphics2D g = (Graphics2D) g2;
		SceneObject nearest = null;
		SceneObject secNearest = null;
		SceneObject[] all = null;
		try {

			nearest = group.getNearest(powermine ? pmine.getArea() : mine
					.getMineArea());

			secNearest = group.getSecondNearest(powermine ? pmine.getArea()
					: mine.getMineArea());
			all = group.getAll();
		} catch (Exception e) {

		}

		if (all != null) {
			g.setColor(Color.red);
			for (SceneObject i : all) {
				if (i != null && i.getLocation().isOnScreen()) {
					i.getLocation().draw(g);
				}
			}
		}
		if (secNearest != null && secNearest.getLocation().isOnScreen()) {
			g.setColor(Color.yellow);
			secNearest.getLocation().draw(g);
		}
		if (nearest != null && nearest.getLocation().isOnScreen()) {
			g.setColor(Color.GREEN);
			nearest.getLocation().draw(g);
		}
		if (powermine) {
			g.setColor(Color.black);
			for (Tile t : pmine.getArea().getTileArray()) {
				if (t != null && t.isOnScreen()) {
					t.draw(g);
				}
			}
		}
		mp.draw(g);
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {

	}

	@Override
	public void mousePressed(MouseEvent arg0) {

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {

	}

	public static int getXpGained() {
		return startExp - Skills.getExperience(Skills.MINING);
	}

	public static long getRuntime() {
		return System.currentTimeMillis() - startTime;
	}

}
