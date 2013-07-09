package org.persuasive.miner.wrappers.area;

import org.persuasive.api.script.movement.pathing.Path;
import org.persuasive.api.script.wrappers.AEBank;
import org.persuasive.miner.AEMiner;
import org.persuasive.miner.ids.Banks;
import org.persuasive.miner.ids.Paths;
import org.powerbot.game.api.wrappers.Area;
import org.powerbot.game.api.wrappers.Locatable;

public enum Mine {
	LUMBRIDGE_EAST(MineArea.L_EAST_MINE, Paths.LUM_EAST_BANK, Banks.LUM_EAST), LUMBRIDGE_WEST(
			MineArea.L_WEST_MINE, Paths.LUM_WEST_BANK, Banks.LUM_WEST), VARROCK_EAST(
			MineArea.VARROCK_EAST_MINE, Paths.VE_BANK, Banks.VARROCK_EAST), VARROCK_WEST(
			MineArea.VARROCK_WEST_MINE, Paths.VW_BANK, Banks.VARROCK_WEST), BARBARIAN(
			MineArea.BARBARIAN_MINE, Paths.BARB_BANK , Banks.BARBARIAN), BARBARIAN_CLAY(
			MineArea.BARB_CLAY, Paths.BARB_BANK, Banks.BARBARIAN), RIMMINGTON(
			MineArea.RIMMINGTON_MINE, Paths.RIMMINGTON_BANK, Banks.RIMMINGTON);
	private MineArea mArea = null;
	private AEBank bArea = null;
	private Path toBank = null;



	Mine(MineArea m, Path toBank, AEBank b) {
		mArea = m;
		bArea = b;
		this.toBank = toBank;
	}

	public void setBankPath(Path path) {
		toBank = path;
	}

	public AEBank getBank() {
		return bArea;
	}

	public void setMineArea(Area n) {
		mArea.setArea(n);
		toBank.changeFirstTile(n.getCentralTile());
	}

	public boolean atBank() {
		return bArea.atBank();
	}

	public boolean atMine() {
		return mArea.atMine();
	}

	public boolean atMine(Locatable l) {
		return mArea.atMine(l);
	}

	public Area getMineArea() {
		return mArea.getArea();
	}

	public boolean goToMine() {
		if (!atMine()) {
			return toBank.walkReversed();
		} else {
			AEMiner.LOGGER
					.log("[[Error]: Located in the 'Mine' class] trying to go to the mine");
		}
		return false;
	}

	public boolean goToBank() {
		if (!atBank()) {
			return toBank.walkPath();
		} else {
			AEMiner.LOGGER
					.log("[[Error]: Located in the 'Mine' class] trying to go to the bank");
		}
		return false;
	}
}
