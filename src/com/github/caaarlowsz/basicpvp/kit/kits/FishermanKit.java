package com.github.caaarlowsz.basicpvp.kit.kits;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;

import com.github.caaarlowsz.basicpvp.apis.WorldGuardAPI;
import com.github.caaarlowsz.basicpvp.kit.Kit;
import com.github.caaarlowsz.basicpvp.kit.KitAPI;
import com.github.caaarlowsz.basicpvp.utils.Strings;

public final class FishermanKit extends Kit implements Listener {

	public FishermanKit() {
		super("Fisherman");
	}

	@EventHandler
	private void onPlayerFish(PlayerFishEvent event) {
		Player player = event.getPlayer();
		if (KitAPI.getKit(player) instanceof FishermanKit && WorldGuardAPI.hasPvP(player)
				&& event.getCaught() instanceof Player) {
			Player caught = (Player) event.getCaught();
			if (WorldGuardAPI.hasPvP(caught)) {
				caught.teleport(player.getLocation());
				caught.sendMessage(Strings.getPrefixo() + " §eVocê foi puxado por " + player.getName() + ".");
				player.sendMessage(Strings.getPrefixo() + " §aVocê puxou " + caught.getName() + ".");
			}
		}
	}
}