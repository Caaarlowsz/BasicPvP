package com.github.caaarlowsz.basicpvp.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.player.PlayerAchievementAwardedEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.github.caaarlowsz.basicpvp.apis.StaffAPI;
import com.github.caaarlowsz.basicpvp.utils.Strings;

public final class WorldListeners implements Listener {

	@EventHandler
	private void onServerListPing(ServerListPingEvent event) {
		if (Bukkit.hasWhitelist())
			event.setMotd(Strings.getWhitelistMOTD());
		else
			event.setMotd(Strings.getMOTD());
	}

	@EventHandler
	private void onPlayerInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();

		if (event.getMaterial() == Material.MUSHROOM_SOUP && event.getAction().name().contains("RIGHT")) {
			if (player.getHealth() != player.getMaxHealth()) {
				player.setHealth(player.getHealth() < (player.getMaxHealth() - 7D) ? (player.getHealth() + 7D)
						: player.getMaxHealth());

				event.getItem().setType(Material.BOWL);
				ItemMeta mItem = event.getItem().getItemMeta();
				mItem.setDisplayName("§aPote");
				event.getItem().setItemMeta(mItem);
			}
		}
	}

	@EventHandler
	private void onItemSpawn(ItemSpawnEvent event) {
		event.getEntity().getWorld().playEffect(event.getEntity().getLocation(), Effect.SMOKE, 1);
	}

	@EventHandler
	private void onPlayerPickupItem(PlayerPickupItemEvent event) {
		event.setCancelled(true);
	}

	@EventHandler
	private void onPlayerDropItem(PlayerDropItemEvent event) {
		ItemStack item = event.getItemDrop().getItemStack();

		if (item.hasItemMeta() || item.getItemMeta().hasDisplayName())
			event.setCancelled(true);
		if (item.getType().equals(Material.BOWL) || item.getType().equals(Material.MUSHROOM_SOUP)
				|| item.getType().equals(Material.RED_MUSHROOM) || item.getType().equals(Material.BROWN_MUSHROOM))
			event.setCancelled(false);
	}

	@EventHandler
	private void onBlockPlace(BlockPlaceEvent event) {
		Player player = event.getPlayer();
		if (StaffAPI.hasBuild(player) && player.getGameMode().equals(GameMode.CREATIVE))
			return;
		event.setCancelled(true);
	}

	@EventHandler
	private void onBlockBreak(BlockBreakEvent event) {
		Player player = event.getPlayer();
		if (StaffAPI.hasBuild(player) && player.getGameMode().equals(GameMode.CREATIVE))
			return;
		event.setCancelled(true);
	}

	@EventHandler
	private void onWeatherChange(WeatherChangeEvent event) {
		if (event.toWeatherState())
			event.setCancelled(true);
	}

	@EventHandler
	private void onFoodLevelChange(FoodLevelChangeEvent event) {
		event.setCancelled(true);
	}

	@EventHandler
	private void onSignChange(SignChangeEvent event) {
		for (int index = 0; index < 4; index++)
			event.setLine(index, ChatColor.translateAlternateColorCodes('&', event.getLine(index)));
	}

	@EventHandler
	private void onPlayerAchievementAwarded(PlayerAchievementAwardedEvent event) {
		event.setCancelled(true);
	}

	@EventHandler
	private void onCreatureSpawn(CreatureSpawnEvent event) {
		event.setCancelled(true);
	}

	@EventHandler
	private void onEntityExplode(EntityExplodeEvent event) {
		event.setCancelled(true);
	}
}