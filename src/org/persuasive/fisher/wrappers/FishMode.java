package org.persuasive.fisher.wrappers;

import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.util.Filter;
import org.powerbot.game.api.wrappers.interactive.NPC;
import org.powerbot.game.api.wrappers.node.Item;

public enum FishMode {

	TUNA_SWORDFISH("harpoon", new Fish[] { Fish.TUNA, Fish.SWORDFISH }, 324), LOBSTER(
			"cage", new Fish[] { Fish.LOBSTER }, 324),

	SHRIMP_ANCHOVY("Net", new Fish[] { Fish.SHRIMP, Fish.ANCHOVIES }, 327);

	private int[] npcIds;
	private String interaction;
	private Fish[] fish;
	private boolean power = false;
	private int toDropAt = 28;

	FishMode(String interaction, Fish[] fishies, int... npcIds) {
		this.npcIds = npcIds;
		fish = fishies;
		this.interaction = interaction;
	}

	public boolean isInventoryFull() {
		return isPowerfish() ? Inventory.getCount() >= toDropAt : Inventory
				.isFull();

	}

	public void setToDropAt(int i) {
		if (isPowerfish()) {
			if (i > 0 && i < 29) {
				toDropAt = i;
			}
		}
	}

	public boolean isPowerfish() {
		return power;
	}

	public void setPowerfish(boolean l) {
		power = l;
	}

	public Fish[] getFish() {
		return fish;
	}

	public String getInteractionMethod() {
		return interaction;
	}

	public int[] getNpcIds() {
		return npcIds;
	}

	public Item[] getAllInventory() {
		return Inventory.getItems(new Filter<Item>() {

			@Override
			public boolean accept(Item i) {
				for (int j = 0; j < fish.length; j++) {
					if (fish[j].getId() == i.getId()) {
						return true;
					}
				}
				return false;
			}
		});
	}

	public int getFishCount() {
		int f = 0;
		for (Fish i : fish) {
			f = f + i.count;
		}
		return f;
	}

	public int getCashCount() {
		int f = 0;
		for (Fish i : fish) {
			f = f + i.profit;
		}
		return f;
	}

	public NPC getNearest() {
		return NPCs.getNearest(npcIds);
	}

	public NPC[] getAll() {
		return NPCs.getLoaded(npcIds);
	}
}
