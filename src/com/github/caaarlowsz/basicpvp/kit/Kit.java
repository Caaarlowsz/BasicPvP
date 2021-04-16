package com.github.caaarlowsz.basicpvp.kit;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class Kit {

	private final String name;
	private final ItemStack icon;

	public Kit(String name, ItemStack icon) {
		this.name = name;
		this.icon = icon;
	}

	public String getName() {
		return this.name;
	}

	public ItemStack getIcon() {
		return this.icon;
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