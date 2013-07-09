package org.persuasive.miner;

import org.persuasive.api.AEPaint;
import org.powerbot.game.api.methods.tab.Skills;
import org.powerbot.game.api.util.Time;

public class MinerPaint extends AEPaint {

	public MinerPaint() {
		super(AEMiner.LOGGER.getName());
		// TODO Auto-generated constructor stub
	}

	public int getPerHour(int current) {
		return (int) ((current) * 3600000D / (System.currentTimeMillis() - AEMiner.startTime));
	}

	@Override
	public String[] txtList1() {

		return new String[] {
				"[Ore & Cash info]",
				"Ore[p/h]: " + AEMiner.group.getOreCount() + "["
						+ getPerHour(AEMiner.group.getOreCount()) + "p/h]",
				"Cash[p/h]: " + AEMiner.group.getCashCount() + "gp["
						+ getPerHour(AEMiner.group.getCashCount()) + "gp/h]" };
	}

	@Override
	public String[] txtList2() {
		return new String[] {
				"[Script Info]",
				"Runtime: "
						+ Time.format(System.currentTimeMillis() - AEMiner.startTime),
				"Location: "+(AEMiner.powermine?"Powermining" : AEMiner.mine.name()),
				 };
	}

	@Override
	public String[] txtList3() {

		return new String[] {
				"[Level & Exp info]",
				"Level[Gained]: " + Skills.getLevel(skill()) + "["
						+ (Skills.getLevel(skill()) - AEMiner.startLev)
						+ "]",
				"Exp[ph]: "
						+ (Skills.getExperience(skill()) - AEMiner.startExp)
						+ "["
						+ getPerHour(Skills.getExperience(skill())
								- AEMiner.startExp) + "p/h]" };
	}

	@Override
	public String status() {
		return AEMiner.status;
	}

	@Override
	public int skill() {
		return Skills.MINING;
	}

}
