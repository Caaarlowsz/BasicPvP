package com.github.caaarlowsz.basicpvp.apis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import com.github.caaarlowsz.basicpvp.cabeca.CabecaAPI;
import com.github.caaarlowsz.basicpvp.kit.KitAPI;
import com.github.caaarlowsz.basicpvp.utils.Stacks;
import com.github.caaarlowsz.basicpvp.warp.WarpAPI;

public final class StaffAPI {

	private static final ArrayList<UUID> admin = new ArrayList<>(), build = new ArrayList<>(),
			staffChat = new ArrayList<>(), silentStaffChat = new ArrayList<>();

	public static boolean hasAdmin(Player player) {
		return admin.contains(player.getUniqueId());
	}

	public static void addAdmin(Player player) {
		if (!hasAdmin(player))
			admin.add(player.getUniqueId());
		KitAPI.removeKit(player);
		WarpAPI.removeWarp(player);

		Bukkit.getOnlinePlayers().stream().filter(players -> !player.hasPermission("kitpvp.permission.viewadmins"))
				.forEach(players -> players.hidePlayer(player));

		player.getActivePotionEffects().forEach(effect -> player.removePotionEffect(effect.getType()));
		player.setGameMode(GameMode.CREATIVE);
		player.setAllowFlight(true);
		player.setFlying(true);
		player.setHealthScale(20D);
		player.setMaxHealth(20D);
		player.setHealth(20D);

		PlayerInventory inv = player.getInventory();
		inv.setArmorContents(null);
		inv.clear();

		ItemStack sword = Stacks.item(Material.DIAMOND_SWORD, true,
				Arrays.asList(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS),
				"§aEspada de Diamante");
		sword.addEnchantment(Enchantment.DAMAGE_ALL, 1);
		inv.setItem(0, sword);

		ItemStack knockback = Stacks.item(Material.STICK, Arrays.asList(ItemFlag.HIDE_ENCHANTS),
				"§aTeste de Knockback");
		knockback.addUnsafeEnchantment(Enchantment.KNOCKBACK, 10);
		inv.setItem(1, knockback);

		inv.setItem(3, Stacks.item(Material.MAGMA_CREAM, "§aTroca-Rápida"));
		inv.setItem(5, Stacks.item(Material.IRON_FENCE, "§aPrisão"));
		player.updateInventory();

		CabecaAPI.updateCabeca(player);
	}

	public static void removeAdmin(Player player) {
		admin.remove(player.getUniqueId());
		Bukkit.getOnlinePlayers().forEach(players -> players.showPlayer(player));
	}

	public static boolean hasBuild(Player player) {
		return build.contains(player.getUniqueId());
	}

	public static void addBuild(Player player) {
		if (!hasBuild(player))
			build.add(player.getUniqueId());
	}

	public static void removeBuild(Player player) {
		build.remove(player.getUniqueId());
	}

	public static boolean hasStaffChat(Player player) {
		return staffChat.contains(player.getUniqueId());
	}

	public static void addStaffChat(Player player) {
		if (!hasStaffChat(player))
			staffChat.add(player.getUniqueId());
	}

	public static void removeStaffChat(Player player) {
		staffChat.remove(player.getUniqueId());
	}

	public static boolean hasSilentStaffChat(Player player) {
		return silentStaffChat.contains(player.getUniqueId());
	}

	public static void addSilentStaffChat(Player player) {
		if (!hasSilentStaffChat(player))
			silentStaffChat.add(player.getUniqueId());
	}

	public static void removeSilentStaffChat(Player player) {
		silentStaffChat.remove(player.getUniqueId());
	}

	public static void sendToStaffChat(Player player, String message) {
		String format = "§e§l[STAFF-CHAT] §r" + player.getDisplayName() + " §8» §e";
		if (player.hasPermission("kitpvp.staff.chatcolor"))
			message = ChatColor.translateAlternateColorCodes('&', message);

		if (message.split(" ").length == 1) {
			Player target = Bukkit.getPlayer(message.split(" ")[0]);
			if (target != null && target.hasPermission("kitpvp.command.staffchat") && hasSilentStaffChat(target))
				target.sendMessage(format + message);
		}

		Bukkit.getConsoleSender().sendMessage(format + message);
		for (Player players : Bukkit.getOnlinePlayers())
			if (!hasSilentStaffChat(players) && players.hasPermission("kitpvp.command.staffchat"))
				players.sendMessage(format + message);
	}
}