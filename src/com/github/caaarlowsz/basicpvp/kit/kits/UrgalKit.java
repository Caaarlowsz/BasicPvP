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

public final class UrgalKit extends Kit implements Listener {

	public UrgalKit() {
		super("Urgal");
	}

	@Override
	public void giveItems(Player player) {
		PlayerInventory inv = player.getInventory();
		super.giveItems(player);

		inv.setItem(1,
				Stacks.item(Material.POTION, 1, 8201,
						Arrays.asList(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_POTION_EFFECTS), "§aForça I",
						"§7Kit " + this.getName()));
	}

	@EventHandler
	private void onPlayerInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		if (KitAPI.getKit(player) instanceof UrgalKit && WorldGuardAPI.hasPvP(player) && event.hasItem()
				&& event.getItem().getType() == Material.POTION && event.getItem().getDurability() == 8201
				&& event.getAction().name().contains("RIGHT")) {
			if (!this.hasCooldown(player)) {
				this.addCooldown(player, 30);
				player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 100, 0));
				player.sendMessage(Strings.getPrefixo() + " §aVocê recebeu o efeito de Força I.");
			} else
				player.sendMessage(Strings.getPrefixo() + " §cAguarde " + this.getRemaingSeconds(player) + ".");
		}
	}
}