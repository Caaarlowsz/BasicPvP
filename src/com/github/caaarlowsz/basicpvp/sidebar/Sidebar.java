package com.github.caaarlowsz.basicpvp.sidebar;

import java.util.HashMap;

public final class Sidebar {

	private String displayName;
	private HashMap<Integer, String> lines;

	public Sidebar(String displayName) {
		this.displayName = displayName;
		this.lines = new HashMap<>();
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

	public void addBlankLine(int index) {
		this.addLine(index, this.fixDuplicates(" "));
	}

	public void addLine(int index, String line) {
		this.getLines().put(index, this.fixDuplicates(line));
	}
}