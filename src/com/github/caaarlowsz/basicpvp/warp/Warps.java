package com.github.caaarlowsz.basicpvp.warp;

import java.util.ArrayList;

public final class Warps {

	private static final ArrayList<Warp> warps = new ArrayList<>();
	private static final Warp NONE_WARP = new Warp("Nenhuma");

	public static Warp getNoneWarp() {
		return NONE_WARP;
	}

	public static ArrayList<Warp> getWarps() {
		return warps;
	}

	public static Warp getByName(String name) {
		return getWarps().stream().filter(warp -> warp.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
	}

	public Warps() {
		getWarps().clear();
	}
}