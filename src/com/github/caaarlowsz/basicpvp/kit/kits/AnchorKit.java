package com.github.caaarlowsz.basicpvp.kit.kits;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.util.Vector;

import com.github.caaarlowsz.basicpvp.apis.WorldGuardAPI;
import com.github.caaarlowsz.basicpvp.kit.Kit;
import com.github.caaarlowsz.basicpvp.kit.KitAPI;

public final class AnchorKit extends Kit implements Listener {

	public AnchorKit() {
		super("Anchor");
	}

	@EventHandler
	private void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
		if (event.getEntity() instanceof Player && event.getDamager() instanceof Player) {
			Player player = (Player) event.getEntity(), damager = (Player) event.getDamager();
			if (WorldGuardAPI.hasPvP(player) && WorldGuardAPI.hasPvP(damager)) {
				if (KitAPI.getKit(player) instanceof AnchorKit || KitAPI.getKit(damager) instanceof AnchorKit) {
					player.setVelocity(new Vector());
					player.playSound(player.getLocation(), Sound.ANVIL_BREAK, 4F, 4F);

					damager.setVelocity(new Vector());
					damager.playSound(damager.getLocation(), Sound.ANVIL_BREAK, 4F, 4F);
				}
			}
		}
	}
}