package org.persuasive.api.script.framework;

import java.util.ArrayList;

public class ActivityBranch implements Activatable, Executable, Prioritizable {
	private ArrayList<Activity> activities = new ArrayList<Activity>(0);

	public ActivityBranch(Activity... actives) {
		for (Activity i : actives) {
			activities.add(i);
		}
	}

	public ActivityBranch(ArrayList<Activity> actives) {
		activities = actives;
	}

	public Activity getCurrentActivity() {
		Activity cur = null;
		for (Activity i : activities) {
			try {
				if (cur != null) {
					if (i.priority() > cur.priority()) {
						if (i.activate()) {
							cur = i;
						}
					}
				} else {
					if (i.activate()) {
						cur = i;
					}
				}
			} catch (Exception e) {

			}
		}
		return cur;
	}

	public Activity get(int i) {
		return activities.get(i);
	}

	public void add(Activity... z) {
		for (Activity i : activities) {
			activities.add(i);
		}
	}

	public void remove(Activity... z) {
		for (Activity i : activities) {
			activities.remove(i);
		}
	}

	public ArrayList<Activity> getAll() {
		return activities;
	}

	@Override
	public boolean activate() {
		return true;
	}

	@Override
	public void execute() {
		if (activate() && getCurrentActivity() != null) {
			getCurrentActivity().execute();
		}
	}

	@Override
	public int priority() {
		return 0;
	}
}
