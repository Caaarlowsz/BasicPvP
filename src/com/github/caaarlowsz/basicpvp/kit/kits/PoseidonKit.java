package com.github.caaarlowsz.basicpvp.kit.kits;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.github.caaarlowsz.basicpvp.apis.WorldGuardAPI;
import com.github.caaarlowsz.basicpvp.kit.Kit;
import com.github.caaarlowsz.basicpvp.kit.KitAPI;

public final class PoseidonKit extends Kit implements Listener {

	public PoseidonKit() {
		super("Poseidon");
	}

	@EventHandler
	private void onPlayerMove(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		if (KitAPI.getKit(player) instanceof PoseidonKit && WorldGuardAPI.hasPvP(player)
				&& player.getLocation().getBlock().getType().name().contains("WATER")) {
			player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 100, 0), true);
			player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 100, 1), true);
			player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100, 1), true);
		}
	}
}