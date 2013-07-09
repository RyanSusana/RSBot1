package org.persuasive.miner.activities;

import java.awt.Polygon;

import org.persuasive.api.script.MiscMethods;
import org.persuasive.api.script.framework.Activatable;
import org.persuasive.api.script.framework.Activity;
import org.persuasive.miner.AEMiner;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.wrappers.node.SceneObject;
import org.powerbot.game.api.wrappers.widget.WidgetChild;

public class Mine extends Activity {
	private SceneObject rock;
	private SceneObject currentRock;

	private boolean polygonContainsMouse(Polygon[] poly) {
		boolean con = false;
		for (Polygon i : poly) {
			if (i.contains(Mouse.getLocation())) {
				con = true;
			}
		}
		return con;
	}

	@Override
	public void execute() {
		AEMiner.status = "Mining";
		currentRock = rock;
		if (currentRock.isOnScreen()) {
			if (currentRock.interact("mine")) {
				MiscMethods.waitFor(new Activatable() {

					@Override
					public boolean activate() {
						SceneObject s = AEMiner.group
								.getSecondNearest(AEMiner.powermine ? AEMiner.pmine
										.getArea() : AEMiner.mine.getMineArea());
						if (s != null) {
							if (s.isOnScreen()) {
								if (!polygonContainsMouse(s.getModel()
										.getTriangles())) {
									s.hover();
								}
							} else {
								Camera.turnTo(s);
							}
						} else {
							WidgetChild wc = Widgets.get(1213).getChild(42);
							if (wc.validate()
									&& wc.isOnScreen()
									&& !(Mouse.getY() < 105
											&& Mouse.getX() > 230 && Mouse
											.getX() < 288)) {
								if (wc.hover())
									;
							}
						}
						return !currentRock.validate();
					}
				}, 15000);
			}
		} else {
			if (currentRock.getLocation().distanceTo() > 7) {
				Walking.walk(currentRock.getLocation().randomize(1, 1));
			} else {
				Camera.turnTo(currentRock);
			}
		}
	}

	@Override
	public boolean activate() {

		if ((AEMiner.powermine ? ((Inventory.getCount() < AEMiner.pmine
				.getToDropAt()) && AEMiner.pmine.atMine()) : (!Inventory
				.isFull() && AEMiner.mine.atMine()))) {
			rock = AEMiner.group.getNearest(AEMiner.powermine ? AEMiner.pmine
					.getArea() : AEMiner.mine.getMineArea());
			if (rock != null) {
				if (currentRock != rock) {
					return true;
				}
			}
		}

		return false;
	}

	public class RockID {

	}
}
