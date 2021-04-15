package com.github.caaarlowsz.basicpvp;

import org.bukkit.plugin.java.JavaPlugin;

public final class BasicKitPvP extends JavaPlugin {

	public static BasicKitPvP getInstance() {
		return getPlugin(BasicKitPvP.class);
	}

	@Override
	public void onEnable() {
		super.onEnable();
		this.saveDefaultConfig();
	}

	@Override
	public void onDisable() {
		super.onDisable();
	}
}