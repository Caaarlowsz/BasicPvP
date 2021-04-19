package com.github.caaarlowsz.basicpvp.warp;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import com.github.caaarlowsz.basicpvp.sidebar.Sidebar;

public class Warp {

	private final String name;
	private final ItemStack icon;
	private Sidebar sidebar;

	public Warp(String name, ItemStack icon) {
		this.name = name;
		this.icon = icon;
	}

	public String getName() {
		return this.name;
	}

	public ItemStack getIcon() {
		return this.icon;
	}

	public Sidebar getSidebar() {
		return this.sidebar;
	}

	public void setSidebar(Sidebar sidebar) {
		this.sidebar = sidebar;
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