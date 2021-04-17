package com.github.caaarlowsz.basicpvp.listeners;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import com.github.caaarlowsz.basicpvp.BasicKitPvP;
import com.github.caaarlowsz.basicpvp.apis.StaffAPI;
import com.github.caaarlowsz.basicpvp.utils.Strings;

public final class AdminListeners implements Listener {

	@EventHandler
	private void onPlayerInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		if (StaffAPI.hasAdmin(player) && event.hasItem() && event.getMaterial() == Material.MAGMA_CREAM
				&& event.getAction().name().contains("RIGHT")) {
			event.setCancelled(true);

			player.setGameMode(GameMode.SURVIVAL);
			Bukkit.getOnlinePlayers().forEach(players -> players.showPlayer(player));

			Bukkit.getScheduler().runTaskLater(BasicKitPvP.getInstance(), () -> {
				player.setGameMode(GameMode.CREATIVE);
				Bukkit.getOnlinePlayers().stream()
						.filter(players -> !players.hasPermission("kitpvp.permission.viewadmins"))
						.forEach(players -> players.hidePlayer(player));
			}, 10L);
		}
	}

	@EventHandler
	private void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
		Player player = event.getPlayer();
		if (StaffAPI.hasAdmin(player) && event.getRightClicked() instanceof Player) {
			Player righted = (Player) event.getRightClicked();
			event.setCancelled(true);
			if (player.getItemInHand() == null) {
				player.openInventory(righted.getInventory());
				player.sendMessage(Strings.getPrefixo() + " §aVocê abriu o inventário de " + righted.getName() + ".");
			} else if (player.getItemInHand().getType() == Material.IRON_FENCE)
				Bukkit.dispatchCommand(player, "cage " + righted.getName());
		}
	}
}