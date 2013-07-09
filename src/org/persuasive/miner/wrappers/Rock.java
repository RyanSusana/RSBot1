package org.persuasive.miner.wrappers;

import org.persuasive.api.script.MiscMethods;
import org.persuasive.miner.AEMiner;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.util.Filter;
import org.powerbot.game.api.wrappers.Area;
import org.powerbot.game.api.wrappers.node.SceneObject;

public enum Rock {
	
	CLAY(new int[]{ 11504, 11503, 11505, 9711, 9713, 15503,
		15505, 46320, 46322, 46324, 72077, 72076, 72075 }, 434), 
	TIN(new int[]{ 3038, 3245, 9716, 9714, 5777,
		5778, 5776, 11934, 11933, 11935, 11959, 11957, 11958, 2094,
		2095, 3038, 3245, 5776, 5777, 5778, 9714, 9716, 11933, 11934,
		11935, 11957, 11958, 11959, 14902, 14903, 21293, 21294, 21295,
		72092, 72093, 72094 }, 438), 
	COPPER(new int[]{ 3229, 3027, 9710, 9708, 9709,
		5779, 5780, 5781, 11961, 11960, 11962, 11936, 11937, 11938,
		3027, 72100, 72099, 72098 },436), 
	IRON(new int[] { 11956, 11955, 11954, 2093, 2092, 9719, 9717,
			9718, 11956, 11954, 37307, 37309, 37308, 31072, 31073, 31071, 9719,
			5774, 5775, 14914, 14913, 5773, 72081, 72082, 72083 }, 430),
	SILVER(new int[]{ 2311, 11186, 11187, 11188, 11948,
		11949, 11950, 15580, 15581, 29224, 29225, 29226, 32444, 32445,
		32446, 37304, 37305, 37306 }, 440),
	COAL(new int[]{ 3032, 2097, 3233, 5770, 5771, 5772,
		10948, 11930, 11931, 11932, 14850, 14851, 21287, 21288, 21289,
		29215, 29216, 29217, 32426, 32427, 32428 }, 442),
	GOLD( new int[]{ 5768, 5769, 9720, 9722, 10574, 10575,
		10576, 11183, 11184, 11185, 11943, 15576, 15577, 15578, 32432,
		32433, 32434, 45067, 45068, 72087, 72088 }, 453),
	MITHRIL(new int[]{ 2102, 2103, 3041, 3280, 5784, 5785,
		5786, 11942, 11943, 11944, 21278, 21279, 21280, 32438, 32439,
		32440, 32439 }, 444), 
	ADAMANTITE(new int[] { 3040, 3273, 5782, 5783, 11939,
		11941, 21275, 21276, 21277, 29233, 29235, 32425, 32436, 32437 }, 447), 
	RUNITE(new int[]{ 33078 }, 451), 
	LIMESTONE(new int[]{ 4027, 4028, 4029 }, 3211),
	;
	private int InventoryID;
	private int[] RockIDs;
	public int gained = 0, price = -1,profit;



	Rock(int[] ID, int inv) {
		InventoryID = inv;
		RockIDs = ID;
	}

	public void addToGained() {
		gained++;
	}
	public int getPrice() {
		if (price == -1) {
			
				price = MiscMethods.getPrice(InventoryID);
				AEMiner.LOGGER.log("Price of[" + this.name()
						+ "] set to: " + price);
			
		}
		return price;
	}
	public int rocksGained() {
		return gained;
	}

	
	public SceneObject getNearest() {
		return SceneEntities.getNearest(RockIDs);
	}

	public SceneObject getSecondNearest(final Area n) {
		return SceneEntities.getNearest(new Filter<SceneObject>() {

			@Override
			public boolean accept(SceneObject e) {
				if (n.contains(e)) {
					for (int i : RockIDs) {
						if ((i == e.getId())
								&& e.getId() != getNearest(n).getId()) {
							return true;
						}
					}
				}
				return false;
			}
		});
	}

	public SceneObject getNearest(final Area n) {
		return SceneEntities.getNearest(new Filter<SceneObject>() {

			@Override
			public boolean accept(SceneObject e) {
				if (n.contains(e)) {
					for (int i : RockIDs) {
						if (i == e.getId()) {
							return true;
						}
					}
				}
				return false;
			}
		});
	}

	public SceneObject[] getAll(final Area n) {
		return SceneEntities.getLoaded(new Filter<SceneObject>() {
			@Override
			public boolean accept(SceneObject e) {
				if (n.contains(e)) {
					for (int i : RockIDs) {
						if (i == e.getId()) {
							return true;
						}
					}
				}
				return false;
			}
		});
	}

	public SceneObject[] getAll() {
		return SceneEntities.getLoaded(RockIDs);
	}

	public int getID() {
		return getNearest().getId();
	}

	public int getInveID() {
		return InventoryID;
	}

	public static boolean waitFor(final boolean c, final long timeout) {
		boolean isValid = false;
		final long past = System.currentTimeMillis();
		final long total = (past + timeout);
		while (System.currentTimeMillis() < total) {
			if (c) {
				isValid = true;
				break;
			}
		}
		return isValid;
	}
}
