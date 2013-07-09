package org.persuasive.miner.wrappers;

import java.util.ArrayList;

import org.powerbot.game.api.wrappers.Area;
import org.powerbot.game.api.wrappers.node.SceneObject;

public class RockGroup {
	private ArrayList<Rock> RockList = new ArrayList<Rock>(0);

	public RockGroup(Rock... i) {
		for (int j = 0; j < i.length; j++) {
			RockList.add(i[j]);
		}
	}
	public Rock[] array(){
		Rock[] cur = new Rock[size()];
		for(int i = 0;i<size();i++){
			cur[i]=get(i);
		}
		return cur;
	}
	public int getOreCount() {
		int f = 0;
		for (Rock i :array()) {
			f = f + i.gained;
		}
		return f;
	}

	public int getCashCount() {
		int f = 0;
		for (Rock i : array()) {
			f = f + i.profit;
		}
		return f;
	}

	public RockGroup(ArrayList<Rock> i) {
		RockList = i;
	}

	public void add(Rock i) {
		RockList.add(i);
	}

	public void remove(Rock i) {
		RockList.remove(i);
	}

	public void remove(int i) {
		RockList.remove(i);
	}

	public Rock get(Rock z) {
		int x = -1;
		for (int i = 0; i < size(); i++) {
			if (z.getID() == get(i).getID()) {
				x = i;
			}
		}
		return get(x);
	}

	private Rock[] getAllRocks() {
		Rock[] ids = new Rock[size()];
		for (int i = 0; i < size(); i++) {
			ids[i] = get(i);
		}
		return ids;
	}

	public SceneObject[] getAll() {
		ArrayList<SceneObject> list = new ArrayList<SceneObject>(0);
		for (Rock j : getAllRocks()) {
			for (SceneObject i : j.getAll()) {
				if (i != null) {
					list.add(i);
				}
			}
		}
		SceneObject[] m = new SceneObject[list.size()];
		for (int i = 0; i < list.size(); i++) {
			m[i] = list.get(i);
		}
		return m;
	}

	public Rock get(int i) {
		return RockList.get(i);
	}

	public int size() {
		return RockList.size();
	}

	public Rock getClosest() {
		for (int i = 0; i < RockList.size(); i++) {
			if (RockList.get(i).getNearest() != null) {
				return RockList.get(i);
			}
		}
		return null;
	}

	public Rock getClosest(Area n) {
		for (int i = 0; i < RockList.size(); i++) {
			if (RockList.get(i).getNearest(n) != null) {
				return RockList.get(i);
			}
		}
		return null;
	}

	public Rock getSecondClosest(Area n) {
		for (int i = 0; i < RockList.size(); i++) {
			if (RockList.get(i).getSecondNearest(n) != null) {
				return RockList.get(i);
			}
		}
		return null;
	}

	public boolean contains(Rock r) {
		return (RockList.contains(r));
	}

	public boolean containsInventoryID(int id) {
		for (int i = 0; i < size(); i++) {
			if (RockList.get(i).getInveID() == id) {
				return true;
			}
		}
		return false;
	}

	public void clear() {
		RockList.clear();
	}

	public SceneObject getNearest() {
		Rock i = getClosest();
		return i.getNearest();
	}

	public SceneObject getNearest(Area n) {
		Rock p = getClosest(n);
		if (p != null) {
			return p.getNearest(n);
		}
		return null;
	}

	public SceneObject getSecondNearest(Area n) {
		Rock p = getSecondClosest(n);
		if (p != null) {
			return p.getSecondNearest(n);
		}
		return null;
	}

	public Rock[] toArray() {
		Rock[] z = new Rock[size()];
		for (int i = 0; i < size(); i++) {
			z[i] = RockList.get(i);
		}
		return z;
	}
}
