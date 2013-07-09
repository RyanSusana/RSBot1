package org.persuasive.fisher.activities;

import org.persuasive.api.script.framework.Activity;
import org.persuasive.fisher.AEFisher;
import org.powerbot.game.api.methods.tab.Inventory;

public class GoToFish extends Activity {
	
	@Override
	public void execute() {

		AEFisher.status = "Going to the fish";
		AEFisher.fishZone.goToFish();
	}

	@Override
	public boolean activate() {
		return !AEFisher.fishMode.isInventoryFull() && !AEFisher.fishZone.atFish();
	}

	@Override
	public int priority() {
		return 0;
	}
}
