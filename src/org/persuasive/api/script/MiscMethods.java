package org.persuasive.api.script;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;

import org.persuasive.api.script.framework.Activatable;
import org.powerbot.core.script.job.Task;
import org.powerbot.game.api.methods.Tabs;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.Menu;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.wrappers.Area;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.interactive.Player;
import org.powerbot.game.api.wrappers.node.Item;
import org.powerbot.game.api.wrappers.node.SceneObject;
import org.powerbot.game.api.wrappers.widget.WidgetChild;

public class MiscMethods {

	public static void dropAllExcept(int... ids) {
		dropAllExcept(Random.nextBoolean(), ids);
	}

	public static SceneObject getInteracting() {
		Player me = Players.getLocal();
		int or = me.getOrientation();
		if (or == 315 || or == 225 || or == 135 || or == 45
				|| me.getAnimation() == -1) {
			return null;
		}
		return SceneEntities.getAt(getTileInfrontOfMe());

	}

	public static Tile getTileInfrontOfMe() {
		final Player p = Players.getLocal();
		final Tile t = p.getLocation();
		final int or = p.getOrientation();
		switch (or) {
		case 0:
			return new Tile(t.getX() + 1, t.getY(), t.getPlane());

		case 315:
			return new Tile(t.getX() + 1, t.getY() - 1, t.getPlane());
		case 270:
			return new Tile(t.getX(), t.getY() - 1, t.getPlane());
		case 225:
			return new Tile(t.getX() - 1, t.getY() - 1, t.getPlane());
		case 180:
			return new Tile(t.getX() - 1, t.getY(), t.getPlane());
		case 135:
			return new Tile(t.getX() - 1, t.getY() + 1, t.getPlane());
		case 90:
			return new Tile(t.getX(), t.getY() + 1, t.getPlane());
		case 45:
			return new Tile(t.getX() + 1, t.getY() + 1, t.getPlane());
		default:
			return null;

		}
	}
	public static boolean waitFor(final Activatable c, final long timeout) {
		boolean isValid = false;
		final long past = System.currentTimeMillis();
		final long total = (past + timeout);
		while (System.currentTimeMillis() < total) {
			if (c.activate()) {
				isValid = true;
				break;
			}
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
		}
		return isValid;
	}
	public static Area getRadiusAroundPlayer(int squareTiles) {
		final Tile t = Players.getLocal().getLocation();
		return new Area(new Tile(t.getX() + squareTiles,
				t.getY() + squareTiles, t.getPlane()), new Tile(t.getX()
				- squareTiles, t.getY() - squareTiles, t.getPlane()));

	}

	/**
	 * Looks up grand exchange information and returns a string array with the
	 * following contents String[0] = item name String[1] = item price
	 * 
	 * @param itemID
	 *            for the item being looked up on the grand exchange
	 * @return : a string array of grand exchange information on the item id
	 *         provided
	 */
	public static String[] lookup(final int itemID) {
		try {
			String[] info = { "0", "0" };
			final URL url = new URL(
					"http://www.tip.it/runescape/index.php?gec&itemid="
							+ itemID);
			final BufferedReader br = new BufferedReader(new InputStreamReader(
					url.openStream()));
			String input;
			while ((input = br.readLine()) != null) {
				if (input.startsWith("<h2>")) {
					info[0] = input.substring(4, input.length() - 5);
				}
				if (input
						.startsWith("<tr><td colspan=\"4\"><b>Current Market Price: </b>")) {
					info[1] = input.substring(49, input.lastIndexOf("gp"))
							.replaceAll(",", "");
					return info;
				}
			}
		} catch (final Exception ignored) {
		}
		return null;
	}

	public static int getPrice(int id) {
		try {
			return Integer.parseInt(lookup(id)[1]);
		} catch (Exception e) {
			return -1;
		}
	}

	public static void dropAllExcept(final boolean mousekeys, final int... ids) {
		if (Tabs.getCurrent() != Tabs.INVENTORY)
			Tabs.INVENTORY.open();

		if (!mousekeys) {
			for (Item item : Inventory.getItems()) {
				if (Arrays.binarySearch(ids, item.getId()) < 0) {
					item.getWidgetChild().interact("Drop");
					Task.sleep(20, 40);
				}
			}
		} else {
			for (int x = 0; x < 4; x++) {
				for (int y = x; y < 28; y += 4) {
					WidgetChild inv = Inventory.getWidget(true);

					if (arrayContains(ids, inv.getChild(y).getChildId())
							|| inv.getChild(y) == null
							|| inv.getChild(y).getChildId() == -1)
						continue;

					if (!inv.getChild(y).getBoundingRectangle()
							.contains(Mouse.getLocation()))
						Mouse.move(
								inv.getChild(y).getCentralPoint().x
										+ Random.nextInt(-3, 3), inv
										.getChild(y).getCentralPoint().y
										+ Random.nextInt(-3, 3));
					if (inv.getChild(y).getBoundingRectangle()
							.contains(Mouse.getLocation())) {
						Mouse.click(false);

						final int yy = getY();

						if (yy == -1)
							Mouse.click(true);
						else {
							Mouse.hop(Mouse.getX(), yy);
							Mouse.click(true);
						}
					}
				}
			}
		}
	}

	private static int getY() {
		final String[] actions = Menu.getActions();
		for (int i = 0; i < actions.length; i++)
			if (actions[i].toLowerCase().contains("drop"))
				return (int) (Menu.getLocation().getY() + 21 + 16 * i);
		return -1;
	}

	public static boolean arrayContains(final int[] arr, final int a) {
		for (final int i : arr)
			if (i == a)
				return true;
		return false;
	}

	public static boolean depositAllExcept(final int... itemIDs) {
		for (Integer i : itemIDs) {
			if (i == null) {
				return false;
			}
		}
		if (Bank.isOpen()) {
			if (Inventory.getCount(true) - Inventory.getCount(true, itemIDs) <= 0) {
				return true;
			}
			if (Inventory.getCount() == 0) {
				return true;
			}
			if (Inventory.getCount(true, itemIDs) == 0) {
				return Bank.depositInventory();
			}
			outer: for (final Item item : Inventory.getItems()) {
				if (item != null && item.getId() != -1) {
					for (final int itemID : itemIDs) {
						if (item.getId() == itemID) {
							continue outer;
						}
					}
					for (int j = 0; j < 5
							&& Inventory.getCount(item.getId()) != 0; j++) {
						if (Bank.deposit(item.getId(), 0)) {
							Task.sleep(40, 120);
						}
					}
				}
			}
			return Inventory.getCount(true) - Inventory.getCount(true, itemIDs) <= 0;
		}
		return false;
	}
}
