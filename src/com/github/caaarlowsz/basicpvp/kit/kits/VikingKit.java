package com.github.caaarlowsz.basicpvp.kit.kits;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.PlayerInventory;

import com.github.caaarlowsz.basicpvp.BasicKitPvP;
import com.github.caaarlowsz.basicpvp.kit.Kit;
import com.github.caaarlowsz.basicpvp.kit.KitAPI;
import com.github.caaarlowsz.basicpvp.utils.KitType;
import com.github.caaarlowsz.basicpvp.utils.Stacks;
import com.github.caaarlowsz.basicpvp.utils.Strings;

public final class VikingKit extends Kit implements Listener {

	public VikingKit() {
		super("Viking");
	}

	@Override
	public void giveItems(Player player) {
		PlayerInventory inv = player.getInventory();
		super.giveItems(player);

		KitType kitType = BasicKitPvP.getKitType("itens.arena");
		inv.setItem(0,
				Stacks.item(kitType == KitType.FULLIRON ? Material.DIAMOND_AXE : Material.STONE_AXE, true,
						Arrays.asList(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE), Strings.getMachado(),
						"§7Kit " + this.getName()));
	}

	@EventHandler
	private void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
		if (event.getDamager() instanceof Player) {
			Player damager = (Player) event.getDamager();
			if (KitAPI.getKit(damager) instanceof VikingKit
					&& damager.getItemInHand().getType().name().contains("_AXE"))
				event.setDamage(event.getDamage() * 1.66D);
		}
	}
}