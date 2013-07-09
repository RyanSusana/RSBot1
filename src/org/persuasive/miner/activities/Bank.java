package org.persuasive.miner.activities;

import org.persuasive.api.script.MiscMethods;
import org.persuasive.api.script.framework.Activity;
import org.persuasive.api.script.wrappers.AEBank;
import org.persuasive.miner.AEMiner;
import org.powerbot.game.api.methods.tab.Inventory;

public class Bank extends Activity {

	@Override
	public void execute() {
		AEMiner.status = "Banking";
		if (AEMiner.powermine) {
			MiscMethods.dropAllExcept(AEMiner.PICKAXE);
		} else {
			AEBank bank = AEMiner.mine.getBank();
			if (bank.open()) {
				AEBank.depositAllExcept(AEMiner.PICKAXE);
			}
		}
	}

	@Override
	public boolean activate() {
		return AEMiner.powermine ? (Inventory.getCount() >= AEMiner.pmine
				.getToDropAt())
				: (Inventory.isFull() && AEMiner.mine.atBank());
	}

}
