package com.github.caaarlowsz.basicpvp.kit.kits;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import com.github.caaarlowsz.basicpvp.kit.Kit;
import com.github.caaarlowsz.basicpvp.kit.KitAPI;

public final class NeoKit extends Kit implements Listener {

	public NeoKit() {
		super("Neo");
	}

	@EventHandler
	private void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
		if (event.getEntity() instanceof Player && event.getCause() == DamageCause.PROJECTILE) {
			Player player = (Player) event.getEntity();
			if (KitAPI.getKit(player) instanceof NeoKit)
				event.setCancelled(true);
		}
	}
}