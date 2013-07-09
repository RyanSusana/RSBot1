package org.persuasive.api.script.movement.pathing;

import org.persuasive.api.script.movement.teleportation.TeleportMethod;
import org.powerbot.game.api.methods.Calculations;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.map.TilePath;

public class TeleportationPath extends Path {
	private TilePath pathTo;
	private TilePath pathFrom;
	private TeleportMethod teleMeth;

	public TeleportationPath(TilePath to, TeleportMethod tm, TilePath from) {
		pathTo = to;
		pathFrom = from;
		teleMeth = tm;
	}

	@Override
	public boolean walkPath() {
		if (pathToIsNear()) {
			return pathTo.traverse();
		} else {
			if (!teleMeth.isUsable()) {
				return false;
			}
			return teleMeth.teleport();
		}
	}

	@Override
	public boolean walkReversed() {
		if (pathFromIsNear()) {
			return pathFrom.traverse();
		} else {
			if (!teleMeth.isUsable()) {
				return false;
			}
			return teleMeth.teleport();
		}
	}

	@Override
	public void changeFirstTile(Tile t) {
		Tile[] i = pathFrom.toArray();
		i[0] = t;
		pathFrom = new TilePath(i);
	}

	private boolean pathToIsNear() {
		return (pathTo.getNext() != null && Calculations.distanceTo(pathTo
				.getNext()) < 10);

	}

	private boolean pathFromIsNear() {
		return (pathFrom.getNext() != null && Calculations.distanceTo(pathFrom
				.getNext()) < 10);

	}
}
