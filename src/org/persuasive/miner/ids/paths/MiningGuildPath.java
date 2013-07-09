package org.persuasive.miner.ids.paths;
import org.persuasive.api.script.movement.pathing.*;
import org.powerbot.core.script.job.Task;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.wrappers.Area;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.map.TilePath;
import org.powerbot.game.api.wrappers.node.SceneObject;



/*Quick note: this is still in the making...*/
public class MiningGuildPath extends Path{

	public static Area miningArea = new Area(new Tile(3055, 9751, 0), new Tile(3025,
			9731, 0));
	public static int[] ladder = { 6226, 2113 };
	public static TilePath toBank =new TilePath( new Tile[]{ new Tile(3021, 3338, 0),
			new Tile(3028, 3337, 0), new Tile(3029, 3344, 0),
			new Tile(3024, 3349, 0), new Tile(3021, 3356, 0),
			new Tile(3015, 3360, 0), new Tile(3013, 3355, 0) });
	public static Area bankArea = new Area(new Tile[] {
			new Tile(3008, 3359, 0), new Tile(3021, 3360, 0),
			new Tile(3022, 3353, 0), new Tile(3007, 3352, 0) });

	static Tile ladderTile = new Tile(3021, 9740, 0);
	public static boolean isUp() {
		return !(Players.getLocal().getLocation().getY() > 5000);
	}

	public static boolean walkToBank() {

		return toBank.traverse();
	}

	public static boolean walkToLadder() {
		if (isUp()) {
			return toBank.reverse().traverse();
		} else {
			SceneObject lad = SceneEntities.getNearest(ladder[0]);
			if (lad != null) {
				if (ladderTile == null) {
					ladderTile = lad.getLocation();
				}
				if (Walking.walk(ladderTile))
					Task.sleep(500);
				return true;
			} else {
				System.out.println("Ladder not found");
			}
		}
		return false;
	}

	public static boolean climb(boolean up) {
		SceneObject ladderUp = SceneEntities.getNearest(ladder[0]);
		SceneObject ladderDown = SceneEntities.getNearest(ladder[1]);
		SceneObject ladder = up ? ladderUp : ladderDown;

		if (ladder != null) {
			if (ladder.isOnScreen()) {
				ladder.interact("Climb-" + (up ? "up" : "down"));
				Task.sleep(1000, 1500);
			} else {
				walkToLadder();
			}
		}
		return up ? isUp() : !isUp();
	}

	

	public boolean atMine() {
		
		return bankArea.contains(Players.getLocal());
	}

	public static boolean atBank() {

		return miningArea.contains(Players.getLocal().getLocation());
	}

	@Override
	public boolean walkPath() {
		if (isUp()) {
			return walkToBank();
		} else {
			return climb(true);
		}
	}

	@Override
	public boolean walkReversed() {
		
		return climb(false);
	}

	@Override
	public void changeFirstTile(Tile t) {
		
	}
	
}
