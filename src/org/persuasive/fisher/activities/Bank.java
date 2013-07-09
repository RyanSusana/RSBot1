package org.persuasive.fisher.activities;

import org.persuasive.api.script.framework.Activity;
import org.persuasive.fisher.AEFisher;

public class Bank extends Activity {
	
	@Override
	public void execute() {

		AEFisher.status = "Banking.";
		AEFisher.fishZone.doBank();
		
	}

	@Override
	public boolean activate() {
		return AEFisher.fishMode.isInventoryFull() && AEFisher.fishZone.atBank() ;
	}

}
