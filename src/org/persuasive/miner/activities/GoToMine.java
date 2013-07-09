package org.persuasive.miner.activities;

import org.persuasive.api.script.framework.Activity;
import org.persuasive.miner.AEMiner;
import org.powerbot.game.api.methods.tab.Inventory;

public class GoToMine extends Activity {
	private boolean powermine() {
		return AEMiner.powermine;
	}

	@Override
	public void execute() {
		AEMiner.status = "Going to mine";
		if (powermine()) {
			AEMiner.pmine.walk();
		} else {
			AEMiner.mine.goToMine();
		}
	}

	@Override
	public boolean activate() {
		return AEMiner.powermine ? (Inventory.getCount() >= AEMiner.pmine
				.getToDropAt() && !AEMiner.pmine.atMine()) : (!Inventory
				.isFull() && !AEMiner.mine.atMine());
	}

}
