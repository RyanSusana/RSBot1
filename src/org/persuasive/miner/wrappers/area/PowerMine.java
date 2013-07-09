package org.persuasive.miner.wrappers.area;

import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.wrappers.Area;
import org.powerbot.game.api.wrappers.Locatable;
import org.powerbot.game.api.wrappers.Tile;

public class PowerMine {
	private int tillDrop = 28;
	private Area toMine = null;

	public PowerMine(Area around, int tilllDrop) {
		tillDrop = tilllDrop;
		toMine = around;
	}

	public PowerMine(Area place) {
		tillDrop = 28;
		toMine = place;
	}

	public void walk() {
		Walking.walk(getCenter());
	}

	public void changeArea(Area r) {
		toMine = r;
	}

	public Area getArea() {
		return toMine;
	}

	public void changeToDropAt(int i) {
		tillDrop = i;
	}

	public int getToDropAt() {
		return tillDrop;
	}

	public Tile getCenter() {
		return toMine.getCentralTile();
	}

	public boolean atMine(Locatable l) {
		return toMine.contains(l);
	}

	public boolean atMine() {
		return atMine(Players.getLocal());
	}
}
