package com.github.caaarlowsz.basicpvp.kit.kits;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.github.caaarlowsz.basicpvp.apis.WorldGuardAPI;
import com.github.caaarlowsz.basicpvp.kit.Kit;
import com.github.caaarlowsz.basicpvp.kit.KitAPI;
import com.github.caaarlowsz.basicpvp.utils.Stacks;
import com.github.caaarlowsz.basicpvp.utils.Strings;

public final class ScoutKit extends Kit implements Listener {

	public ScoutKit() {
		super("Scout");
	}

	@Override
	public void giveItems(Player player) {
		PlayerInventory inv = player.getInventory();
		super.giveItems(player);

		inv.setItem(1,
				Stacks.item(Material.POTION, 1, 8194,
						Arrays.asList(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_POTION_EFFECTS), "§aVelocidade II",
						"§7Kit " + this.getName()));
	}

	@EventHandler
	private void onPlayerInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		if (KitAPI.getKit(player) instanceof ScoutKit && event.hasItem() && event.getItem().getType() == Material.POTION
				&& event.getItem().getDurability() == 8194 && event.getAction().name().contains("RIGHT")) {
			event.setCancelled(true);
			if (WorldGuardAPI.hasPvP(player)) {
				if (!this.hasCooldown(player)) {
					this.addCooldown(player, 25);
					player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 200, 1));
					player.sendMessage(Strings.getPrefixo() + " §aVocê recebeu o efeito de Velocidade II.");
				} else
					player.sendMessage(Strings.getPrefixo() + " §cAguarde " + this.getRemaingSeconds(player) + ".");
			}
		}
	}
}