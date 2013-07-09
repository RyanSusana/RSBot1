package org.persuasive.api.script.framework;

import org.powerbot.core.script.job.state.Node;

public abstract class Activity extends Node implements Activatable,Executable, Prioritizable{

	@Override
	public abstract void execute();

	@Override
	public abstract boolean activate();
	
	public int stall(){
		return 100;
	}
	@Override
	public int priority(){
		return 0;
	}
	public int loop(){
		execute();
		return stall();
	}

}
