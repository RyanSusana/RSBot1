package org.persuasive.fisher;

import java.awt.Graphics;

import org.persuasive.api.script.framework.ActivityBranch;
import org.persuasive.api.script.objects.Logger;
import org.persuasive.api.script.objects.WebPack;
import org.persuasive.fisher.activities.Bank;
import org.persuasive.fisher.activities.Fish;
import org.persuasive.fisher.activities.GoToBank;
import org.persuasive.fisher.activities.GoToFish;
import org.persuasive.fisher.activities.WhileFishing;
import org.persuasive.fisher.wrappers.FishMode;
import org.persuasive.fisher.wrappers.FishZone;
import org.powerbot.core.event.events.MessageEvent;
import org.powerbot.core.event.listeners.MessageListener;
import org.powerbot.core.event.listeners.PaintListener;
import org.powerbot.core.script.ActiveScript;
import org.powerbot.game.api.Manifest;
import org.powerbot.game.api.methods.Game;
import org.powerbot.game.api.methods.tab.Skills;
import org.powerbot.game.api.util.Random;

@Manifest(authors = { "Persuasive" }, description = "Start the script while logged in [Warning slow loadup!]", name = "AEFisher")
public class AEFisher extends ActiveScript implements PaintListener,
		MessageListener {
	public int startLev;
	public int startExp;
	public long startTime;
	public static boolean pause = true;
	public static FishZone fishZone = FishZone.KARAMJA;
	public static FishMode fishMode = FishMode.TUNA_SWORDFISH;
	public static final Logger LOGGER = new Logger("AEFisher");
	public final WebPack WEB = new WebPack("", "", false);
	public static String status;

	public Paint paint;

	private ActivityBranch branch() {
		return new ActivityBranch(new Bank(), new Fish(), new GoToFish(),
				new GoToBank(), new WhileFishing());
	}

	@Override
	public void onStop() {

	}

	@Override
	public void onStart() {
		if (!Game.isLoggedIn()) {
			LOGGER.log("Please log in and restart the script");
			stop();
		}
		startLev = Skills.getLevel(Skills.FISHING);
		startExp = Skills.getExperience(Skills.FISHING);
		startTime = System.currentTimeMillis();

		
		paint = new Paint(this);
	}

	@Override
	public int loop() {
		if (pause) {
			return 200;
		}
		if (branch().getCurrentActivity() != null) {
			try {
				branch().getCurrentActivity().execute();
			} catch (Exception e) {
				LOGGER.log("Exception in the loop[evaded]...attempting to continue");
			}
		}
		return Random.nextInt(50, 200);
	}

	@Override
	public void onRepaint(Graphics g) {
		if (pause) {
			return;
		}
		paint.draw(g);
	}

	@Override
	public void messageReceived(MessageEvent e) {
		String mes = e.getMessage().toString().toLowerCase();

		if (mes.contains("you catch")) {
			for (org.persuasive.fisher.wrappers.Fish i : org.persuasive.fisher.wrappers.Fish
					.values()) {
				if (mes.contains(i.toString().toLowerCase())) {
					i.count++;
					i.profit = i.profit + i.getPrice();
				}
			}
		}

	}

}
