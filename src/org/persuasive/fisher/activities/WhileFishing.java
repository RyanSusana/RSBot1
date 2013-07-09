package org.persuasive.fisher.activities;

import org.persuasive.api.script.framework.Activity;
import org.persuasive.fisher.AEFisher;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.wrappers.widget.WidgetChild;

public class WhileFishing extends Activity {

	@Override
	public void execute() {
		WidgetChild wc = Widgets.get(1213).getChild(44);
		AEFisher.status = "Fishing";
		if (wc.validate()
				&& wc.isOnScreen()
				&& !(Mouse.getY() < 105 && Mouse.getX() > 230 && Mouse.getX() < 288)) {
			if (wc.hover());
		}
	}

	@Override
	public boolean activate() {
		return Players.getLocal().getAnimation() != -1;
	}

	@Override
	public int priority() {
		return 3;
	}

}
