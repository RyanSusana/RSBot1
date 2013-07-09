package org.persuasive.miner.activities;

import org.persuasive.api.script.framework.Activity;
import org.persuasive.miner.AEMiner;
import org.powerbot.game.api.methods.tab.Inventory;

public class GoToBank extends Activity {

	@Override
	public void execute() {
		AEMiner.status = "Going to bank";
		if (AEMiner.powermine) {
			AEMiner.pmine.walk();
		} else {
			AEMiner.mine.goToBank();
		}
	}

	@Override
	public boolean activate() {
		return !AEMiner.powermine
				&& (Inventory.isFull() && !AEMiner.mine.atBank());
	}

}
