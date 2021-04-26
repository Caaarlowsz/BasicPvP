package com.github.caaarlowsz.basicpvp.cabeca;

import org.bukkit.inventory.ItemStack;

import com.github.caaarlowsz.basicpvp.utils.Stacks;

public class Cabeca {
	private final String name;
	private final ItemStack icon;

	public Cabeca(String name) {
		this(name, Stacks.getConfigItem("icones.cabecas." + name.toLowerCase().replace(" ", "-")));
	}

	public Cabeca(String name, ItemStack icon) {
		this.name = name;
		this.icon = icon;
	}

	public String getName() {
		return this.name;
	}

	public ItemStack getIcon() {
		return this.icon;
	}
}