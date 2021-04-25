package com.github.caaarlowsz.basicpvp.utils;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.PlayerInventory;

public enum KitType {
	SIMULATOR("Simulator"), FULLIRON("Full Iron"), PRESET01("Preset 01");

	private String name;

	private KitType(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void applyKit(Player player) {
		PlayerInventory inv = player.getInventory();

		if (this == FULLIRON) {
			inv.setHelmet(Stacks.item(Material.IRON_HELMET, true, Arrays.asList(ItemFlag.HIDE_UNBREAKABLE),
					Strings.getCapacete()));
			inv.setChestplate(Stacks.item(Material.IRON_CHESTPLATE, true, Arrays.asList(ItemFlag.HIDE_UNBREAKABLE),
					Strings.getPeitoral()));
			inv.setLeggings(Stacks.item(Material.IRON_LEGGINGS, true, Arrays.asList(ItemFlag.HIDE_UNBREAKABLE),
					Strings.getCalca()));
			inv.setBoots(Stacks.item(Material.IRON_BOOTS, true, Arrays.asList(ItemFlag.HIDE_UNBREAKABLE),
					Strings.getBotas()));

			inv.setItem(0, Stacks.item(Material.DIAMOND_SWORD, true,
					Arrays.asList(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE), Strings.getEspada()));
		} else {
			if (this == PRESET01)
				inv.setChestplate(Stacks.item(Material.LEATHER_CHESTPLATE, true,
						Arrays.asList(ItemFlag.HIDE_UNBREAKABLE), Strings.getPeitoral()));

			inv.setItem(0, Stacks.item(Material.STONE_SWORD, true,
					Arrays.asList(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE), Strings.getEspada()));
		}
	}

	public static KitType getTypeByName(String name) {
		for (KitType type : values()) {
			if (type.getName().equalsIgnoreCase(name))
				return type;
		}
		return null;
	}
}