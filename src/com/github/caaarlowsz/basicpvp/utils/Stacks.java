package com.github.caaarlowsz.basicpvp.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
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
		return item(type, amount, durability, unbreakable, new ArrayList<>());
	}

	public static ItemStack item(Material type, List<ItemFlag> flags) {
		return item(type, 1, flags);
	}

	public static ItemStack item(Material type, int amount, List<ItemFlag> flags) {
		return item(type, amount, 0, flags);
	}

	public static ItemStack item(Material type, int amount, int durability, List<ItemFlag> flags) {
		return item(type, amount, durability, false, flags);
	}

	public static ItemStack item(Material type, boolean unbreakable, List<ItemFlag> flags) {
		return item(type, 1, unbreakable, flags);
	}

	public static ItemStack item(Material type, int amount, boolean unbreakable, List<ItemFlag> flags) {
		return item(type, amount, 0, unbreakable, flags);
	}

	public static ItemStack item(Material type, int amount, int durability, boolean unbreakable, List<ItemFlag> flags) {
		ItemStack item = new ItemStack(type, amount, (short) durability);
		ItemMeta mItem = item.getItemMeta();
		mItem.spigot().setUnbreakable(unbreakable);
		mItem.addItemFlags(flags.toArray(new ItemFlag[] {}));
		item.setItemMeta(mItem);
		return item;
	}

	public static ItemStack item(Material type, boolean unbreakable, String displayName, String... lore) {
		return item(type, 1, unbreakable, new ArrayList<>(), displayName, lore);
	}

	public static ItemStack item(Material type, int amount, boolean unbreakable, String displayName, String... lore) {
		return item(type, amount, 0, unbreakable, new ArrayList<>(), displayName, lore);
	}

	public static ItemStack item(Material type, int amount, int durability, boolean unbreakable, String displayName,
			String... lore) {
		return item(type, amount, durability, unbreakable, new ArrayList<>(), displayName, lore);
	}

	public static ItemStack item(Material type, List<ItemFlag> flags, String displayName, String... lore) {
		return item(type, 1, flags, displayName, lore);
	}

	public static ItemStack item(Material type, int amount, List<ItemFlag> flags, String displayName, String... lore) {
		return item(type, amount, 0, flags, displayName, lore);
	}

	public static ItemStack item(Material type, int amount, int durability, List<ItemFlag> flags, String displayName,
			String... lore) {
		return item(type, amount, durability, false, flags, displayName, lore);
	}

	public static ItemStack item(Material type, boolean unbreakable, List<ItemFlag> flags, String displayName,
			String... lore) {
		return item(type, 1, unbreakable, flags, displayName, lore);
	}

	public static ItemStack item(Material type, int amount, boolean unbreakable, List<ItemFlag> flags,
			String displayName, String... lore) {
		return item(type, amount, 0, unbreakable, flags, displayName, lore);
	}

	public static ItemStack item(Material type, int amount, int durability, boolean unbreakable, List<ItemFlag> flags,
			String displayName, String... lore) {
		ItemStack item = item(type, amount, durability, unbreakable, flags);
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