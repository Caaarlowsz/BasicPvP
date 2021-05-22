package com.github.caaarlowsz.basicpvp.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import com.github.caaarlowsz.basicpvp.BasicKitPvP;
import com.github.caaarlowsz.basicpvp.kit.KitAPI;
import com.github.caaarlowsz.basicpvp.player.PlayerAPI;
import com.github.caaarlowsz.basicpvp.tag.TagAPI;
import com.github.caaarlowsz.basicpvp.warp.WarpAPI;

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

	public static int getSlotConfigItem(String path) {
		return BasicKitPvP.getInstance().getConfig().getInt(path + ".slot") - 1;
	}

	public static ItemStack getConfigItem(String path) {
		FileConfiguration config = BasicKitPvP.getInstance().getConfig();
		String material = config.getString(path + ".material");
		String display = ChatColor.translateAlternateColorCodes('&', config.getString(path + ".display"))
				.replace("{nome}", Strings.getNome()).replace("{prefixo}", Strings.getPrefixo())
				.replace("{website}", Strings.getWebsite()).replace("{loja}", Strings.getLoja())
				.replace("{discord}", Strings.getDiscord()).replace("{nome}", Strings.getNome());
		ArrayList<String> lore = new ArrayList<>();
		if (config.contains(path + ".lore"))
			config.getStringList(path + ".lore")
					.forEach(line -> lore.add(ChatColor.translateAlternateColorCodes('&', line)
							.replace("{nome}", Strings.getNome()).replace("{prefixo}", Strings.getPrefixo())
							.replace("{website}", Strings.getWebsite()).replace("{loja}", Strings.getLoja())
							.replace("{discord}", Strings.getDiscord()).replace("{nome}", Strings.getNome())));

		ArrayList<ItemFlag> flags = new ArrayList<>();
		if (config.contains(path + ".flags")) {
			if (config.getBoolean(path + ".flags.hide-attributes", false))
				flags.add(ItemFlag.HIDE_ATTRIBUTES);
		}

		Material type = Material.STONE;
		int amount = 1, durability = 0;

		String[] split = material.split(",");
		type = Material.getMaterial(split[0]);
		if (split.length == 2)
			amount = Integer.valueOf(split[1]);
		if (split.length == 3)
			durability = Integer.valueOf(split[2]);

		ItemStack item = Stacks.item(type, amount, durability, flags, display);
		if (lore.size() > 0) {
			ItemMeta mItem = item.getItemMeta();
			mItem.setLore(lore);
			item.setItemMeta(mItem);
		}

		return item;
	}

	public static ItemStack applyPlayerPH(Player player, ItemStack itemStack) {
		ItemStack item = Stacks.item(itemStack.getType(), itemStack.getAmount(), itemStack.getDurability());
		ItemMeta mItemStack = itemStack.getItemMeta(), mItem = item.getItemMeta();
		if (mItemStack.hasDisplayName())
			mItem.setDisplayName(mItemStack.getDisplayName()
					.replace("{player_group}", TagAPI.getMaxTag(player).getColoredName())
					.replace("{player_coins}", PlayerAPI.getMoedas(player))
					.replace("{player_xp}", PlayerAPI.getXP(player))
					.replace("{player_killstreak}", PlayerAPI.getKillStreak(player))
					.replace("{player_kills}", PlayerAPI.getAbates(player))
					.replace("{player_deaths}", PlayerAPI.getMortes(player))
					.replace("{player_rank_icon} {player_rank}", PlayerAPI.getRank(player).getColoredSymbolName())
					.replace("{player_rank} {player_rank_icon}", PlayerAPI.getRank(player).getColoredNameSymbol())
					.replace("{player_rank}", PlayerAPI.getRank(player).getColoredName())
					.replace("{player_kit}", KitAPI.getKit(player).getName())
					.replace("{player_warp}", WarpAPI.getWarp(player).getName())
					.replace("{server_players}/{server_slots}",
							Bukkit.getOnlinePlayers().size() + "/" + Bukkit.getMaxPlayers())
					.replace("{server_players}", "" + Bukkit.getOnlinePlayers().size())
					.replace("{server_slots}", "" + Bukkit.getMaxPlayers()));
		if (mItemStack.hasLore()) {
			ArrayList<String> lore = new ArrayList<>();
			mItemStack.getLore().forEach(line -> lore.add(line
					.replace("{player_group}", TagAPI.getMaxTag(player).getColoredName())
					.replace("{player_coins}", PlayerAPI.getMoedas(player))
					.replace("{player_xp}", PlayerAPI.getXP(player))
					.replace("{player_killstreak}", PlayerAPI.getKillStreak(player))
					.replace("{player_kills}", PlayerAPI.getAbates(player))
					.replace("{player_deaths}", PlayerAPI.getMortes(player))
					.replace("{player_rank_icon} {player_rank}", PlayerAPI.getRank(player).getColoredSymbolName())
					.replace("{player_rank} {player_rank_icon}", PlayerAPI.getRank(player).getColoredNameSymbol())
					.replace("{player_rank}", PlayerAPI.getRank(player).getColoredName())
					.replace("{player_kit}", KitAPI.getKit(player).getName())
					.replace("{player_warp}", WarpAPI.getWarp(player).getName())
					.replace("{server_players}/{server_slots}",
							Bukkit.getOnlinePlayers().size() + "/" + Bukkit.getMaxPlayers())
					.replace("{server_players}", "" + Bukkit.getOnlinePlayers().size())
					.replace("{server_slots}", "" + Bukkit.getMaxPlayers())));
			mItem.setLore(lore);
		}
		if (mItemStack instanceof SkullMeta)
			((SkullMeta) mItem).setOwner(player.getName());
		item.setItemMeta(mItem);
		return item;
	}

	public static ItemStack applyModel(String path, ItemStack item) {
		return applyModel(path, item, new HashMap<>());
	}

	public static ItemStack applyModel(String path, ItemStack item, HashMap<String, String> placeholders) {
		FileConfiguration config = BasicKitPvP.getInstance().getConfig();
		String displayName = "{name}";
		List<String> lore = new ArrayList<>();

		ItemMeta meta = item.getItemMeta();
		if (meta.hasDisplayName())
			displayName = meta.getDisplayName();
		if (meta.hasLore())
			lore = meta.getLore();

		String display = ChatColor.translateAlternateColorCodes('&', config.getString(path + ".display"))
				.replace("{name}", displayName);
		for (Entry<String, String> entry : placeholders.entrySet())
			display = display.replace(entry.getKey(), entry.getValue());
		meta.setDisplayName(display);

		int index = 0;
		ArrayList<String> description = new ArrayList<>();
		for (String line : config.getStringList(path + ".lore")) {
			if (line.contains("{description}")) {
				for (String l : lore) {
					for (Entry<String, String> entry : placeholders.entrySet())
						l = l.replace(entry.getKey(), entry.getValue());
					description.add(index, l);
					index++;
				}
			} else {
				for (Entry<String, String> entry : placeholders.entrySet())
					line = line.replace(entry.getKey(), entry.getValue());
				description.add(ChatColor.translateAlternateColorCodes('&', line));
				index++;
			}
		}
		meta.setLore(description);

		item.setItemMeta(meta);
		return item;
	}
}