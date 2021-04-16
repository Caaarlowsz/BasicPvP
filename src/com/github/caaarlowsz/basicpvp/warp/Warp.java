package com.github.caaarlowsz.basicpvp.warp;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;

public class Warp {

	private final String name;

	public Warp(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void giveItems(Player player) {
		player.setGameMode(GameMode.SURVIVAL);
		player.setAllowFlight(false);
		player.setFlying(false);
		player.setHealthScale(20D);
		player.setMaxHealth(20D);
		player.setHealth(20D);

		PlayerInventory inv = player.getInventory();
		inv.setArmorContents(null);
		inv.clear();
	}
}