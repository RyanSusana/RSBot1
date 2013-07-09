package org.persuasive.fisher.activities;

import org.persuasive.api.script.framework.Activity;
import org.persuasive.fisher.AEFisher;
import org.powerbot.game.api.methods.tab.Inventory;

public class GoToBank extends Activity {
	
	@Override
	public void execute(){
		AEFisher.status = "Going to the bank";
		AEFisher.fishZone.goToBank();
	}

	@Override
	public boolean activate() {
		return AEFisher.fishMode.isInventoryFull() && !AEFisher.fishZone.atBank();
	}

	@Override
	public int priority() {
		return 5;
	}

}
