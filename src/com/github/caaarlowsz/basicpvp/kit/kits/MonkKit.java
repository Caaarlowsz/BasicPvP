package com.github.caaarlowsz.basicpvp.kit.kits;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import com.github.caaarlowsz.basicpvp.apis.WorldGuardAPI;
import com.github.caaarlowsz.basicpvp.kit.Kit;
import com.github.caaarlowsz.basicpvp.kit.KitAPI;
import com.github.caaarlowsz.basicpvp.utils.Stacks;
import com.github.caaarlowsz.basicpvp.utils.Strings;

public final class MonkKit extends Kit implements Listener {

	public MonkKit() {
		super("Monk");
	}

	@Override
	public void giveItems(Player player) {
		PlayerInventory inv = player.getInventory();
		super.giveItems(player);

		inv.setItem(1, Stacks.item(Material.BLAZE_ROD, "§aEmbaralhador", "§7Kit " + this.getName()));
	}

	@EventHandler
	private void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
		Player player = event.getPlayer();
		if (KitAPI.getKit(player) instanceof MonkKit && WorldGuardAPI.hasPvP(player)
				&& player.getItemInHand().getType().equals(Material.BLAZE_ROD)
				&& event.getRightClicked() instanceof Player) {
			Player righted = (Player) event.getRightClicked();
			if (!this.hasCooldown(player)) {
				if (WorldGuardAPI.hasPvP(righted)) {
					this.addCooldown(player, 20);
					player.sendMessage(
							Strings.getPrefixo() + " §aVocê embaralhou o inventário de " + righted.getName() + ".");

					PlayerInventory inv = righted.getInventory();
					int slot = new Random().nextInt(inv.getSize() - 10 + 1 + 10);
					ItemStack item = inv.getItem(slot), inHand = inv.getItemInHand();

					inv.setItem(slot, inHand);
					inv.setItemInHand(item);
					righted.sendMessage(Strings.getPrefixo() + " §eSeu inventário foi embaralhado.");
				}
			} else
				player.sendMessage(Strings.getPrefixo() + " §cAguarde " + this.getRemaingSeconds(player) + ".");
		}
	}
}