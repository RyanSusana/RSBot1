package org.persuasive.fisher.activities;

import org.persuasive.api.script.framework.Activity;
import org.persuasive.fisher.AEFisher;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.wrappers.interactive.NPC;

public class Fish extends Activity {
	
	private NPC fish = null;
	
	@Override
	public void execute() {
		AEFisher.status = "Fishing";
		if (fish.isOnScreen()) {
			if (fish.interact(AEFisher.fishMode.getInteractionMethod())) {
				sleep(500,600);//doesnt make sense for a dynamic sleep here...Because it will interrupt other activities...
			}
		} else {
			if (fish.getLocation().distanceTo() > 10) {
				Walking.walk(fish.getLocation().randomize(2, 2));
			} else {
				Camera.turnTo(fish);
			}
		}
	} 

	@Override
	public boolean activate() {
		return AEFisher.fishZone.atFish()
				&& !AEFisher.fishMode.isInventoryFull()
				&& ((fish = AEFisher.fishMode.getNearest()) != null)
				&& (!Players.getLocal().isMoving())
				&& (Players.getLocal().isIdle())
				;
	}

	@Override
	public int priority() {
		return 10;
	}

}
