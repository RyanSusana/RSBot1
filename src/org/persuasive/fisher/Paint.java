package org.persuasive.fisher;

import org.persuasive.api.AEPaint;
import org.powerbot.game.api.methods.tab.Skills;
import org.powerbot.game.api.util.Time;

public class Paint extends AEPaint {

	
	private AEFisher inst;

	public Paint(AEFisher inst) {
		super(AEFisher.LOGGER.getName());
		this.inst = inst;
	}

	public int getPerHour(int current) {
		return (int) ((current) * 3600000D / (System.currentTimeMillis() - inst.startTime));
	}

	@Override
	public String[] txtList1() {

		return new String[] {
				"[Fish & Cash info]",
				"Fish[p/h]: " + AEFisher.fishMode.getFishCount() + "["
						+ getPerHour(AEFisher.fishMode.getFishCount()) + "p/h]",
				"Cash[p/h]: " + AEFisher.fishMode.getCashCount() + "gp["
						+ getPerHour(AEFisher.fishMode.getCashCount()) + "gp/h]" };
	}

	@Override
	public String[] txtList2() {
		return new String[] {
				"[Script Info]",
				"Status: " + AEFisher.status,
				"Runtime: "
						+ Time.format(System.currentTimeMillis() - inst.startTime),
				"Location: " + AEFisher.fishZone.name(),
				"Fishing: " + AEFisher.fishMode.name() };
	}

	@Override
	public String[] txtList3() {

		return new String[] {
				"[Level & Exp info]",
				"Level[Gained]: " + Skills.getLevel(Skills.FISHING) + "["
						+ (Skills.getLevel(Skills.FISHING) - inst.startLev) + "]",
				"Exp[ph]: "
						+ (Skills.getExperience(Skills.FISHING) - inst.startExp)
						+ "["
						+ getPerHour(Skills.getExperience(Skills.FISHING)
								- inst.startExp) + "p/h]" };
	}

	@Override
	public String status() {
		return AEFisher.status;
	}

	@Override
	public int skill() {
		return Skills.FISHING;
	}

}
