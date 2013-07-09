package org.persuasive.fisher.wrappers;

import org.persuasive.api.script.MiscMethods;
import org.persuasive.fisher.AEFisher;

public enum Fish {

	TUNA(359, 360), SWORDFISH(371, 372), LOBSTER(377, 378), PIKE(349, 350), SALMON(
			331, 332), SHRIMP(317, 318), TROUT(335, 336), ANCHOVIES(321, 322), HERRING(
			345, 346), SARDINE(327, 328);

	private int itemId, noteId, price = -1;
	public int count = 0, profit;

	Fish(int itemId, int notedId) {
		this.itemId = itemId;
		this.noteId = notedId;
	}

	public int getPrice() {
		if (price == -1) {
			
				price = MiscMethods.getPrice(itemId);
				AEFisher.LOGGER.log("Price of[" + this.name()
						+ "] set to: " + price);
			
		}
		return price;
	}

	public int getId() {
		return itemId;
	}

	public int getNotedId() {
		return noteId;
	}

}
