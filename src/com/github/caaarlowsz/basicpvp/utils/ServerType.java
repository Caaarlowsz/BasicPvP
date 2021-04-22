package com.github.caaarlowsz.basicpvp.utils;

public enum ServerType {
	SIMULATOR("Simulator"), FULLIRON("Full Iron");

	private String name;

	private ServerType(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public static ServerType getTypeByName(String name) {
		for (ServerType type : values()) {
			if (type.getName().equalsIgnoreCase(name))
				return type;
		}
		return null;
	}
}