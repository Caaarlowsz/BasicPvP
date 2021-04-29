package com.github.caaarlowsz.basicpvp.kit.kits;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.PlayerInventory;

import com.github.caaarlowsz.basicpvp.apis.WorldGuardAPI;
import com.github.caaarlowsz.basicpvp.kit.Kit;
import com.github.caaarlowsz.basicpvp.kit.KitAPI;
import com.github.caaarlowsz.basicpvp.utils.Stacks;
import com.github.caaarlowsz.basicpvp.utils.Strings;

public final class FishermanKit extends Kit implements Listener {

	public FishermanKit() {
		super("Fisherman");
	}

	@Override
	public void giveItems(Player player) {
		PlayerInventory inv = player.getInventory();
		super.giveItems(player);

		inv.setItem(1, Stacks.item(Material.FISHING_ROD, true, Arrays.asList(ItemFlag.HIDE_UNBREAKABLE),
				"§aVara de Pesca", "§7Kit " + this.getName()));
		player.updateInventory();
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