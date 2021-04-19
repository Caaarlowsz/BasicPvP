package com.github.caaarlowsz.basicpvp.kit;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import com.github.caaarlowsz.basicpvp.utils.Strings;

public final class KitListeners implements Listener {

	@EventHandler
	private void onPlayerInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();

		if (KitAPI.hasKit(player) && event.getMaterial() == Material.COMPASS) {
			for (Entity entity : player.getNearbyEntities(100D, 150D, 100D)) {
				if (entity instanceof Player && player.getLocation().distance(entity.getLocation()) > 10D) {
					Player target = (Player) entity;
					player.setCompassTarget(target.getLocation());
					player.sendMessage(Strings.getPrefixo() + " §eBússola apontando para: " + target.getName());
					return;
				}
			}

			player.setCompassTarget(player.getLocation());
			player.sendMessage(Strings.getPrefixo() + " §cNenhum alvo foi encontrado.");
		}
	}
}