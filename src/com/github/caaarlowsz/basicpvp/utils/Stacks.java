package com.github.caaarlowsz.basicpvp.utils;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public final class Stacks {

	public static ItemStack item(Material type) {
		return item(type, 1);
	}

	public static ItemStack item(Material type, int amount) {
		return item(type, amount, 0);
	}

	public static ItemStack item(Material type, int amount, int durability) {
		return item(type, amount, durability, false);
	}

	public static ItemStack item(Material type, String displayName, String... lore) {
		return item(type, 1, displayName, lore);
	}

	public static ItemStack item(Material type, int amount, String displayName, String... lore) {
		return item(type, amount, 0, displayName, lore);
	}

	public static ItemStack item(Material type, int amount, int durability, String displayName, String... lore) {
		return item(type, amount, durability, false, displayName, lore);
	}

	public static ItemStack item(Material type, boolean unbreakable) {
		return item(type, 1, unbreakable);
	}

	public static ItemStack item(Material type, int amount, boolean unbreakable) {
		return item(type, amount, 0, unbreakable);
	}

	public static ItemStack item(Material type, int amount, int durability, boolean unbreakable) {
		ItemStack item = new ItemStack(type, amount, (short) durability);
		ItemMeta mItem = item.getItemMeta();
		mItem.spigot().setUnbreakable(unbreakable);
		item.setItemMeta(mItem);
		return item;
	}

	public static ItemStack item(Material type, boolean unbreakable, String displayName, String... lore) {
		return item(type, 1, unbreakable, displayName, lore);
	}

	public static ItemStack item(Material type, int amount, boolean unbreakable, String displayName, String... lore) {
		return item(type, amount, 0, unbreakable, displayName, lore);
	}

	public static ItemStack item(Material type, int amount, int durability, boolean unbreakable, String displayName,
			String... lore) {
		ItemStack item = item(type, amount, durability, unbreakable);
		ItemMeta mItem = item.getItemMeta();
		mItem.setDisplayName(displayName);
		mItem.setLore(Arrays.asList(lore));
		item.setItemMeta(mItem);
		return item;
	}

	public static ItemStack skull(int amount, String owner, String displayName, String... lore) {
		ItemStack skull = item(Material.SKULL_ITEM, amount, 3, displayName, lore);
		SkullMeta mSkull = (SkullMeta) skull.getItemMeta();
		mSkull.setOwner(owner);
		skull.setItemMeta(mSkull);
		return skull;
	}
}