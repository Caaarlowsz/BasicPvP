package com.github.caaarlowsz.basicpvp.tag;

import com.github.caaarlowsz.basicpvp.BasicKitPvP;

public final class Tags {

	public Tags(BasicKitPvP plugin) {
		plugin.getCommand("tag").setExecutor(new TagCommand());
	}
}