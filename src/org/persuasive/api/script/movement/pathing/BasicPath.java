package org.persuasive.api.script.movement.pathing;

import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.map.TilePath;

public class BasicPath extends Path {

	private Tile[] tilePath;

	public BasicPath(Tile[] i) {
		tilePath = i;
	}

	@Override
	public void changeFirstTile(Tile s) {
		tilePath[0] = s;
	}

	public Tile getLastTile() {
		return tilePath[tilePath.length - 1];
	}

	@Override
	public boolean walkReversed() {
		Tile[] j = Walking.newTilePath(tilePath).reverse().toArray();
		final TilePath p = new TilePath(j);
		return (p.traverse());
	}

	@Override
	public boolean walkPath() {
		final TilePath p = new TilePath(tilePath);

		return (p.traverse());
	}
}
