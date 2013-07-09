package org.persuasive.api.script.objects;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Logger {
	private String name = null;
	ArrayList<String> list = new ArrayList<String>();

	public Logger(String name) {
		this.name = name;
		list.add("Welcome to " + name);
	}
 
	public void log(String text) {
		String timeStamp = new SimpleDateFormat("HH:mm").format(Calendar
				.getInstance().getTime());
		System.out.println("["+timeStamp +"]"+ " "+ name + " : " + text);
		list.add("["+timeStamp +"]"+ " : " + text);
	}
	public String getName(){
		return name;
	}

	public int size() {
		return list.size();
	}

	public String get(int i) {
		return list.get(i);
	}

	public String getLog() {
		String s = "";
		for (int i = 0; i < size(); i++) {
			s = s + list.get(i) + "\n";
		}
		return s;
	}

	
	public ArrayList<String> getList() {
		return list;
	}
}
