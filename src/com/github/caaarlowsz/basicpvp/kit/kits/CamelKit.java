package com.github.caaarlowsz.basicpvp.kit.kits;

import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.github.caaarlowsz.basicpvp.apis.WorldGuardAPI;
import com.github.caaarlowsz.basicpvp.kit.Kit;
import com.github.caaarlowsz.basicpvp.kit.KitAPI;

public final class CamelKit extends Kit implements Listener {

	public CamelKit() {
		super("Camel");
	}

	@EventHandler
	private void onPlayerMove(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		if (KitAPI.getKit(player) instanceof CamelKit && WorldGuardAPI.hasPvP(player)
				&& event.getTo().getBlock().getRelative(BlockFace.DOWN).getType().name().contains("SAND")) {
			player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 100, 0), true);
			player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100, 0), true);
		}
	}
}