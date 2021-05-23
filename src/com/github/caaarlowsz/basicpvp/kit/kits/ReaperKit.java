package com.github.caaarlowsz.basicpvp.kit.kits;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.github.caaarlowsz.basicpvp.apis.WorldGuardAPI;
import com.github.caaarlowsz.basicpvp.kit.Kit;
import com.github.caaarlowsz.basicpvp.kit.KitAPI;
import com.github.caaarlowsz.basicpvp.utils.Stacks;

public final class ReaperKit extends Kit implements Listener {

	public ReaperKit() {
		super("Reaper");
	}

	@Override
	public void giveItems(Player player) {
		PlayerInventory inv = player.getInventory();
		super.giveItems(player);

		inv.setItem(1, Stacks.item(Material.WOOD_HOE, true, Arrays.asList(ItemFlag.HIDE_UNBREAKABLE), "§aFoice",
				"§7Kit " + this.getName()));
	}

	@EventHandler
	private void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
		if (event.getEntity() instanceof Player && event.getDamager() instanceof Player) {
			Player player = (Player) event.getEntity(), damager = (Player) event.getDamager();
			if (WorldGuardAPI.hasPvP(player) && WorldGuardAPI.hasPvP(damager)
					&& KitAPI.getKit(damager) instanceof ReaperKit
					&& damager.getItemInHand().getType().equals(Material.WOOD_HOE))
				player.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 100, 0), true);
		}
	}
}