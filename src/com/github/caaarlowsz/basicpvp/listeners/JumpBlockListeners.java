package com.github.caaarlowsz.basicpvp.listeners;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerMoveEvent;

public final class JumpBlockListeners implements Listener {

	private static final ArrayList<UUID> noFallDamage = new ArrayList<>();

	@EventHandler
	private void onPlayerMove(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		Block block = event.getTo().getBlock();
		Material type = block.getRelative(BlockFace.DOWN).getType();

		if (block.getType().equals(Material.CARPET)) {
			if (type.equals(Material.COAL_BLOCK)) {
				player.setVelocity(event.getTo().getDirection().multiply(15D).setY(0.8D));
				player.playSound(player.getLocation(), Sound.ARROW_HIT, 6F, 1F);
				if (!noFallDamage.contains(player.getUniqueId()))
					noFallDamage.add(player.getUniqueId());
			} else if (type.equals(Material.SPONGE)) {
				player.setVelocity(player.getLocation().getDirection().multiply(0).setY(2.5D));
				player.playSound(player.getLocation(), Sound.ORB_PICKUP, 6F, 1F);
				if (!noFallDamage.contains(player.getUniqueId()))
					noFallDamage.add(player.getUniqueId());
			}
		}
	}

	@EventHandler
	private void onEntityDamage(EntityDamageEvent event) {
		if (event.getEntity() instanceof Player && event.getCause() == DamageCause.FALL) {
			Player player = (Player) event.getEntity();
			if (noFallDamage.contains(player.getUniqueId())) {
				noFallDamage.remove(player.getUniqueId());
				event.setCancelled(true);
			}
		}
	}
}