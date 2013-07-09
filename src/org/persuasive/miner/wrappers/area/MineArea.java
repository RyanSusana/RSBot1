package org.persuasive.miner.wrappers.area;

import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.wrappers.Area;
import org.powerbot.game.api.wrappers.Locatable;
import org.powerbot.game.api.wrappers.Tile;

public class MineArea {
	private Area b = null;

	public static final MineArea D_RESOURCE_MINE = new MineArea(new Area(
			new Tile(1045, 4550, 0), new Tile(1080, 4590, 0)));
	public static final MineArea L_EAST_MINE = new MineArea(new Area(new Tile(
			3236, 3153, 0), new Tile(3221, 3144, 0)));

	public static final MineArea L_WEST_MINE = new MineArea(new Area(
			new Tile[] { new Tile(3138, 3152,0), new Tile(3138, 3138,0),
					new Tile(3157, 3139,0), new Tile(3154, 3152,0) }));
	public static final MineArea AL_MINE = new MineArea(new Area(new Tile(3309,
			3286, 0), new Tile(3282, 3318, 0)));

	public static final MineArea VARROCK_EAST_MINE = new MineArea(new Area(
			new Tile(3296, 3356, 0), new Tile(3275, 3372, 0)));

	public static final MineArea VARROCK_WEST_MINE = new MineArea(new Area(
			new Tile(3186, 3360, 0), new Tile(3170, 3383, 0)));

	public static final MineArea BARBARIAN_MINE = new MineArea(new Area(
			new Tile(3070, 3412, 0), new Tile(3088, 3432, 0)));

	public static final MineArea KHAZARD_MINE = new MineArea(new Area(new Tile(
			2640, 3131, 0), new Tile(2621, 3158, 0)));

	public static final MineArea RIMMINGTON_MINE = new MineArea(new Area(
			new Tile(2992, 3225, 0), new Tile(2961, 3253, 0)));

	public static final MineArea TAVERLY_MINE = new MineArea(new Area(new Tile(
			2932, 3331, 0), new Tile(2925, 3343, 0)));

	public static final MineArea HOBGLOBIN_MINE = new MineArea(new Area(
			new Tile(3018, 3813, 0), new Tile(3035, 3785, 0)));

	public static final MineArea BARB_CLAY = new MineArea(new Area(new Tile(
			3075, 3405, 0), new Tile(3088, 3392, 0)));

	public static final MineArea SHILO_MINE = new MineArea(new Area(new Tile(
			2830, 3008, 0), new Tile(2815, 2990, 0)));

	public static final MineArea S_ARDOUGNE_MINE = new MineArea(new Area(
			new Tile(2830, 3008, 0), new Tile(2815, 2990, 0)));

	public MineArea(Area n) {
		b = n;
	}

	public void setArea(Area n) {
		b = n;
	}

	public boolean atMine() {
		return b.contains(Players.getLocal());
	}

	public boolean atMine(Locatable i) {
		return b.contains(i);
	}

	public Area getArea() {
		return b;
	}
}
