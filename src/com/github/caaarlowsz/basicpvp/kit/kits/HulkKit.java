package com.github.caaarlowsz.basicpvp.kit.kits;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import com.github.caaarlowsz.basicpvp.apis.WorldGuardAPI;
import com.github.caaarlowsz.basicpvp.kit.Kit;
import com.github.caaarlowsz.basicpvp.kit.KitAPI;
import com.github.caaarlowsz.basicpvp.utils.Strings;

public final class HulkKit extends Kit implements Listener {

	public HulkKit() {
		super("Hulk");
	}

	@EventHandler
	private void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
		Player player = event.getPlayer();
		if (KitAPI.getKit(player) instanceof HulkKit && WorldGuardAPI.hasPvP(player)
				&& (player.getItemInHand() == null || player.getItemInHand().getType().equals(Material.AIR))
				&& event.getRightClicked() instanceof Player && player.getPassenger() == null) {
			Player righted = (Player) event.getRightClicked();
			if (!this.hasCooldown(player)) {
				if (righted.getPassenger() == null && WorldGuardAPI.hasPvP(righted)) {
					this.addCooldown(player, 6);
					player.setPassenger(righted);
					player.sendMessage(Strings.getPrefixo() + " §aVocê pegou " + righted.getName() + ".");
					righted.sendMessage(Strings.getPrefixo() + " §eVocê foi pego por " + player.getName() + ".");
				}
			} else
				player.sendMessage(Strings.getPrefixo() + " §cAguarde " + this.getRemaingSeconds(player) + ".");
		}
	}
}