package com.github.caaarlowsz.basicpvp.kit;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import com.github.caaarlowsz.basicpvp.utils.Strings;

public final class KitListeners implements Listener {

	@EventHandler
	private void onPlayerInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();

		if (KitAPI.hasKit(player)) {
			if (event.hasBlock()) {
				Material type = event.getClickedBlock().getType();
				if (type.equals(Material.ENCHANTMENT_TABLE) || type.equals(Material.CHEST)
						|| type.equals(Material.ENDER_CHEST) || type.equals(Material.TRAPPED_CHEST)
						|| type.equals(Material.BREWING_STAND) || type.equals(Material.ANVIL)
						|| type.equals(Material.FURNACE) || type.equals(Material.WORKBENCH))
					event.setCancelled(true);
			}
			if (event.getMaterial() == Material.COMPASS) {
				player.getNearbyEntities(100D, 150D, 100D).stream().filter(
						entity -> entity instanceof Player && player.getLocation().distance(entity.getLocation()) > 10D)
						.forEach(entity -> {
							Player target = (Player) entity;
							player.setCompassTarget(target.getLocation());
							player.sendMessage(Strings.getPrefixo() + " §eBússola apontando para: " + target.getName());
							return;
						});

				player.setCompassTarget(player.getLocation());
				player.sendMessage(Strings.getPrefixo() + " §cNenhum alvo foi encontrado.");
			}
		}
	}
}