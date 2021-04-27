package com.github.caaarlowsz.basicpvp.kit.kits;

import java.util.Arrays;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.PlayerInventory;

import com.github.caaarlowsz.basicpvp.apis.WorldGuardAPI;
import com.github.caaarlowsz.basicpvp.kit.Kit;
import com.github.caaarlowsz.basicpvp.kit.KitAPI;
import com.github.caaarlowsz.basicpvp.utils.Stacks;
import com.github.caaarlowsz.basicpvp.utils.Strings;

public final class ThorKit extends Kit implements Listener {

	public ThorKit() {
		super("Thor");
	}

	@Override
	public void giveItems(Player player) {
		PlayerInventory inv = player.getInventory();
		super.giveItems(player);

		inv.setItem(1,
				Stacks.item(Material.WOOD_AXE, true, Arrays.asList(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES),
						"§aMjolnir", "§7Kit " + this.getName()));
		player.updateInventory();
	}

	@EventHandler
	private void onPlayerInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		if (KitAPI.getKit(player) instanceof ThorKit && event.hasItem() && event.hasBlock()
				&& event.getMaterial().equals(Material.WOOD_AXE) && event.getAction().name().contains("RIGHT")
				&& WorldGuardAPI.hasPvP(player)) {
			event.setCancelled(true);

			if (!this.hasCooldown(player)) {
				this.addCooldown(player, 5);
				Location location = event.getClickedBlock().getLocation();
				if (WorldGuardAPI.hasPvP(location))
					player.getWorld().strikeLightning(location);
			} else
				player.sendMessage(Strings.getPrefixo() + " §cAguarde " + this.getRemaingSeconds(player) + ".");
		}
	}
}