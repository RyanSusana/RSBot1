package org.persuasive.fisher.wrappers;

import org.persuasive.api.script.MiscMethods;
import org.persuasive.api.script.movement.pathing.BasicPath;
import org.persuasive.api.script.movement.pathing.Path;
import org.persuasive.api.script.wrappers.AEBank;
import org.persuasive.fisher.AEFisher;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.wrappers.Area;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.interactive.NPC;

public enum FishZone {

	KARAMJA, DRAYNOR(new Area(new Tile[] { new Tile(3078, 3232, 0),
			new Tile(3086, 3237, 0), new Tile(3099, 3226, 0),
			new Tile(3088, 3219, 0) }),
			new BasicPath(new Tile[] { new Tile(3086, 3231,0),
					new Tile(3088, 3238,0), new Tile(3092, 3243,0) }), new Area(
					new Tile[] { new Tile(3085, 3246, 0),
							new Tile(3086, 3239, 0), new Tile(3100, 3240, 0),
							new Tile(3099, 3246, 0) }), false,
			FishMode.SHRIMP_ANCHOVY);

	private int mode = -1;
	private Area fishArea = null;
	private AEBank bank = null;
	private Path toBank = null;
	private final int stilesId = 11267;
	private FishMode[] availableModes;

	FishZone() {
		mode = 0;
		toBank = new BasicPath(new Tile[] { new Tile(2918, 3173, 0),
				new Tile(2907, 3171, 0), new Tile(2896, 3163, 0),
				new Tile(2892, 3151, 0), new Tile(2882, 3147, 0),
				new Tile(2870, 3147, 0), new Tile(2858, 3145, 0),
				new Tile(2851, 3142, 0) });
		fishArea = new Area(new Tile[] { new Tile(2916, 3187, 0),
				new Tile(2917, 3174, 0), new Tile(2932, 3173, 0),
				new Tile(2932, 3186, 0) });

		availableModes = new FishMode[] { FishMode.LOBSTER,
				FishMode.TUNA_SWORDFISH };
	}

	FishZone(Area fish, Path t, Area bank, boolean bool, FishMode... modes) {
		mode = 1;
		fishArea = fish;
		this.bank = new AEBank(bank, bool);
		toBank = t;
		availableModes = modes;
	}

	FishZone(Area p) {
		mode = 2;
		fishArea = p;
	}

	public void goToBank() {
		if (isBank() || isStiles()) {
			toBank.walkPath();
		}
	}

	public FishMode[] getAvailableModes() {
		return availableModes;
	}

	public void goToFish() {
		if (isStiles() || isBank()) {
			toBank.walkReversed();
		} else {
			Walking.walk(fishArea.getCentralTile().randomize(1, 1));
		}
	}

	public void doBank() {
		if (atBank()) {
			if (isPowerfish()) {
				MiscMethods.dropAllExcept();
			} else if (isStiles()) {
				final NPC stiles = NPCs.getNearest(stilesId);
				if (stiles.isOnScreen()) {
					if (stiles.interact("Exchange")) {
						AEFisher.sleep(500);
					}
				} else {
					if (stiles.getLocation().distanceTo() > 9) {
						Walking.walk(stiles);
					} else {
						Camera.turnTo(stiles);
					}
				}
			} else {
				if (bank.isOpen()) {
					MiscMethods.depositAllExcept();
				} else {
					bank.open();
				}
			}
		}
	}

	public boolean isStiles() {
		return mode == 0;
	}

	public boolean isPowerfish() {
		return AEFisher.fishMode.isPowerfish();
	}

	public boolean isBank() {
		return mode == 1;
	}

	public boolean atBank() {
		if (isStiles()) {
			return (NPCs.getNearest(stilesId) != null);
		}
		if (isPowerfish())
			return true;
		return bank.atBank();
	}

	public AEBank getBank() {
		return bank;
	}

	public Area getFishArea() {
		return fishArea;
	}

	public boolean atFish() {
		return fishArea.contains(Players.getLocal());
	}
}
