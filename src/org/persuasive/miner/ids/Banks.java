package org.persuasive.miner.ids;

import org.persuasive.api.script.wrappers.AEBank;
import org.powerbot.game.api.wrappers.Area;
import org.powerbot.game.api.wrappers.Tile;

public class Banks {

	private static final Area D_RESOURCE_BANK = new Area(
			new Tile(1041, 4574, 0), new Tile(1044, 4578, 0));

	private static final Area L_EAST_BANK = new Area(new Tile(3270, 3172, 0),
			new Tile(3269, 3161, 0));

	private static final Area L_WEST_BANK = new Area(new Tile[] {
			new Tile(3264, 3174, 0), new Tile(3273, 3174, 0),
			new Tile(3274, 3159, 0), new Tile(3263, 3160, 0) });

	private static final Area VARROCK_EAST_BANK = new Area(new Tile(3258, 3414,
			0), new Tile(3248, 3427, 0));

	private static final Area VARROCK_WEST_BANK = new Area(new Tile[] {
			new Tile(3172, 3451, 0), new Tile(3172, 3428, 0),
			new Tile(3197, 3428, 0), new Tile(3195, 3447, 0) });

	private static final Area BARBARIAN_BANK = new Area(new Tile[] {
			new Tile(3090, 3500, 0), new Tile(3099, 3500, 0),
			new Tile(3101, 3486, 0), new Tile(3089, 3487, 0) });

	private static final Area KHAZARD_BANK = new Area(new Tile(2668, 3155, 0),
			new Tile(2658, 3167, 0));

	private static final Area RIMMINGTON_BANK = new Area(new Tile[] {
			new Tile(3039, 3239, 0), new Tile(3040, 3230, 0),
			new Tile(3055, 3230, 0), new Tile(3053, 3239, 0) });

	private static final Area FALADOR_BANK = new Area(new Tile(3007, 3360, 0),
			new Tile(3022, 3351, 0));

	private static final Area TAVERLY_BANK = new Area(new Tile(2873, 3419, 0),
			new Tile(2878, 3412, 0));

	private static final Area HOBGOBLIN_BANK = new Area(
			new Tile(3090, 3497, 0), new Tile(3098, 3487, 0));

	private static final Area SHILO_BANK = new Area(new Tile(2842, 2957, 0),
			new Tile(2862, 2952, 0));

	private static final Area S_ARDOUGNE_BANK = new Area(
			new Tile(2608, 3221, 0), new Tile(2601, 3237, 0));

	public static final AEBank LUM_EAST = new AEBank(L_EAST_BANK, false),
			LUM_WEST = new AEBank(L_WEST_BANK, false),
			VARROCK_EAST = new AEBank(VARROCK_EAST_BANK, false),
			VARROCK_WEST = new AEBank(VARROCK_WEST_BANK, false),
			RIMMINGTON = new AEBank(RIMMINGTON_BANK, true),
			BARBARIAN = new AEBank(BARBARIAN_BANK, false),
			DWARVEN_RESOURCE = new AEBank(D_RESOURCE_BANK, true),
			KHAZARD = new AEBank(KHAZARD_BANK,false),
			FALADOR = new AEBank(FALADOR_BANK,false),
			TAVERLY = new AEBank(TAVERLY_BANK,false),
			HOBGOBLIN = new AEBank(HOBGOBLIN_BANK , false),
			SHILO = new AEBank (SHILO_BANK,false),
			SOUTH_ARDOUGNE = new AEBank(S_ARDOUGNE_BANK,false);
	
}
