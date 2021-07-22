package com.github.caaarlowsz.basicpvp.kit.kits;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import com.github.caaarlowsz.basicpvp.kit.Kit;
import com.github.caaarlowsz.basicpvp.kit.KitAPI;
import com.github.caaarlowsz.basicpvp.utils.Stacks;
import com.github.caaarlowsz.basicpvp.utils.Strings;

public final class StomperKit extends Kit implements Listener {

	public StomperKit() {
		super("Stomper", 1500, Stacks.item(Material.IRON_BOOTS, "§aKit Stomper", "§7Esmague seus amigos e inimigos,",
				"§7mas lembre-se: ser silencioso", "§7é o segredo da vitória."));
	}

	@EventHandler
	private void onEntityDamage(EntityDamageEvent event) {
		if (event.getEntity() instanceof Player && event.getCause() == DamageCause.FALL) {
			Player player = (Player) event.getEntity();
			if (KitAPI.getKit(player) instanceof StomperKit) {
				for (Entity entities : player.getNearbyEntities(4D, 6D, 4D)) {
					if (entities instanceof Player) {
						Player players = (Player) entities;
						if (!(KitAPI.getKit(players) instanceof AntiStomperKit)) {
							players.damage(players.isSneaking() ? event.getDamage() : 4D);
							players.playSound(players.getLocation(), Sound.ANVIL_LAND, 1F, 1F);
							players.sendMessage(
									Strings.getPrefixo() + " §eVocê foi pisoteado por " + player.getName() + ".");
						}
						player.playSound(player.getLocation(), Sound.ANVIL_LAND, 1F, 1F);
						player.sendMessage(Strings.getPrefixo() + " §aVocê pisoteou " + players.getName() + ".");
					}
				}
				event.setDamage(4D);
			}
		}
	}
}