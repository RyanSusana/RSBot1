package org.persuasive.api.script.movement.pathing;

import org.powerbot.game.api.wrappers.Tile;

public abstract class Path {
	public abstract boolean walkPath();
	public abstract boolean walkReversed();
	public abstract void changeFirstTile(Tile t);

}
