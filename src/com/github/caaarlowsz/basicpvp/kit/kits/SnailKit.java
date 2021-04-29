package com.github.caaarlowsz.basicpvp.kit.kits;

import java.util.Random;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.github.caaarlowsz.basicpvp.apis.WorldGuardAPI;
import com.github.caaarlowsz.basicpvp.kit.Kit;
import com.github.caaarlowsz.basicpvp.kit.KitAPI;

public final class SnailKit extends Kit implements Listener {

	public SnailKit() {
		super("Snail");
	}

	@EventHandler
	private void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
		if (event.getEntity() instanceof Player && event.getDamager() instanceof Player
				&& new Random().nextInt(100) <= 33) {
			Player player = (Player) event.getEntity(), damager = (Player) event.getDamager();
			if (WorldGuardAPI.hasPvP(player) && WorldGuardAPI.hasPvP(damager)
					&& KitAPI.getKit(damager) instanceof SnailKit)
				player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 0), true);
		}
	}
}