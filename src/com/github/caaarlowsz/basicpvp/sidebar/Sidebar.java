package com.github.caaarlowsz.basicpvp.sidebar;

import java.util.HashMap;

public final class Sidebar implements Cloneable {

	private String displayName;
	private HashMap<Integer, String> lines;
	private HashMap<String, String> suffixes;

	public Sidebar(String displayName) {
		this.displayName = displayName;
		this.lines = new HashMap<>();
		this.suffixes = new HashMap<>();
	}

	public String getDisplayName() {
		return this.displayName;
	}

	public HashMap<Integer, String> getLines() {
		return this.lines;
	}

	private String fixDuplicates(String input) {
		while (this.getLines().containsValue(input))
			input = "§r" + input;
		return input;
	}

	public void addLine(String line) {
		this.getLines().put(this.getLines().size(), this.fixDuplicates(line));
	}

	public void addLine(int index, String line) {
		this.getLines().put(index, this.fixDuplicates(line));
	}

	public HashMap<String, String> getSuffixes() {
		return this.suffixes;
	}

	public void setSuffix(String suffix, String line) {
		this.getSuffixes().put(suffix, line);
	}

	@Override
	public Sidebar clone() {
		Sidebar sidebar = new Sidebar(this.getDisplayName());
		sidebar.getLines().putAll(this.getLines());
		sidebar.getSuffixes().putAll(this.getSuffixes());
		return sidebar;
	}
}