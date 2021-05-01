package com.github.caaarlowsz.basicpvp.kit.kits;

import java.util.Random;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerMoveEvent;

import com.github.caaarlowsz.basicpvp.apis.WorldGuardAPI;
import com.github.caaarlowsz.basicpvp.kit.Kit;
import com.github.caaarlowsz.basicpvp.kit.KitAPI;

public final class MagmaKit extends Kit implements Listener {

	public MagmaKit() {
		super("Magma");
	}

	@EventHandler
	private void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
		if (event.getEntity() instanceof Player && event.getDamager() instanceof Player
				&& new Random().nextInt(100) <= 33) {
			Player player = (Player) event.getEntity(), damager = (Player) event.getDamager();
			if (WorldGuardAPI.hasPvP(player) && WorldGuardAPI.hasPvP(damager)
					&& KitAPI.getKit(player) instanceof MagmaKit)
				damager.setFireTicks(150);
		}
	}

	@EventHandler
	private void onEntityDamage(EntityDamageEvent event) {
		if (event.getEntity() instanceof Player
				&& (event.getCause().equals(DamageCause.LAVA) || event.getCause().name().contains("FIRE"))) {
			Player player = (Player) event.getEntity();
			if (KitAPI.getKit(player) instanceof MagmaKit && WorldGuardAPI.hasPvP(player))
				event.setCancelled(true);
		}
	}

	@EventHandler
	private void onPlayerMove(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		if (KitAPI.getKit(player) instanceof MagmaKit && WorldGuardAPI.hasPvP(player)
				&& event.getTo().getBlock().getType().name().contains("WATER"))
			player.damage(2D);
	}
}