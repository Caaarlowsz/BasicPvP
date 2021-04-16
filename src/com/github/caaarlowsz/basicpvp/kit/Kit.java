package com.github.caaarlowsz.basicpvp.kit;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

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
	}
}