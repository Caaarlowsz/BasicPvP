package com.github.caaarlowsz.basicpvp.kit.kits;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import com.github.caaarlowsz.basicpvp.BasicKitPvP;
import com.github.caaarlowsz.basicpvp.apis.WorldGuardAPI;
import com.github.caaarlowsz.basicpvp.kit.Kit;
import com.github.caaarlowsz.basicpvp.kit.KitAPI;
import com.github.caaarlowsz.basicpvp.utils.Stacks;
import com.github.caaarlowsz.basicpvp.utils.Strings;

public final class PhantomKit extends Kit implements Listener {

	private static final HashMap<UUID, ItemStack[]> armorMap = new HashMap<>();

	public PhantomKit() {
		super("Phantom");
	}

	@Override
	public void giveItems(Player player) {
		PlayerInventory inv = player.getInventory();
		super.giveItems(player);

		inv.setItem(1, Stacks.item(Material.FEATHER, "§aAtivar vôo", "§7Kit " + this.getName()));
	}

	@EventHandler
	private void onPlayerInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		if (KitAPI.getKit(player) instanceof PhantomKit && event.hasItem()
				&& event.getMaterial().equals(Material.FEATHER) && event.getAction().name().contains("RIGHT")) {
			event.setCancelled(true);
			if (WorldGuardAPI.hasPvP(player)) {
				if (!this.hasCooldown(player)) {
					this.addCooldown(player, 30);

					PlayerInventory inv = player.getInventory();
					if (!armorMap.containsKey(player.getUniqueId()))
						armorMap.put(player.getUniqueId(), inv.getArmorContents());
					inv.setHelmet(Stacks.armor(Material.LEATHER_HELMET, Color.WHITE, "§aArmadura"));
					inv.setChestplate(Stacks.armor(Material.LEATHER_CHESTPLATE, Color.WHITE, "§aArmadura"));
					inv.setLeggings(Stacks.armor(Material.LEATHER_LEGGINGS, Color.WHITE, "§aArmadura"));
					inv.setBoots(Stacks.armor(Material.LEATHER_BOOTS, Color.WHITE, "§aArmadura"));

					player.setAllowFlight(true);
					player.setFlying(true);
					player.sendMessage(Strings.getPrefixo() + " §aVocê ativou o vôo por 5 segundos.");

					Bukkit.getScheduler().runTaskLater(BasicKitPvP.getInstance(),
							() -> player.sendMessage("§e4 segundos..."), 20L);
					Bukkit.getScheduler().runTaskLater(BasicKitPvP.getInstance(),
							() -> player.sendMessage("§e3 segundos..."), 40L);
					Bukkit.getScheduler().runTaskLater(BasicKitPvP.getInstance(),
							() -> player.sendMessage("§e2 segundos..."), 60L);
					Bukkit.getScheduler().runTaskLater(BasicKitPvP.getInstance(),
							() -> player.sendMessage("§e1 segundo..."), 80L);
					Bukkit.getScheduler().runTaskLater(BasicKitPvP.getInstance(), () -> {
						if (armorMap.containsKey(player.getUniqueId()))
							inv.setArmorContents(armorMap.remove(player.getUniqueId()));
						player.setFlying(false);
						player.setAllowFlight(false);
						player.setFallDistance(0F);
						player.sendMessage(Strings.getPrefixo() + " §eSeu vôo acabou.");
					}, 100L);
				} else
					player.sendMessage(Strings.getPrefixo() + " §cAguarde " + this.getRemaingSeconds(player) + ".");
			}
		}
	}

	@EventHandler
	private void onInventoryClick(InventoryClickEvent event) {
		if (event.getWhoClicked() instanceof Player && event.getSlotType() == SlotType.ARMOR) {
			Player player = (Player) event.getWhoClicked();
			if (KitAPI.getKit(player) instanceof PhantomKit && armorMap.containsKey(player.getUniqueId()))
				event.setCancelled(true);
		}
	}

	@EventHandler
	private void onPlayerDeath(PlayerDeathEvent event) {
		armorMap.remove(event.getEntity().getUniqueId());
	}

	@EventHandler
	private void onPlayerQuit(PlayerQuitEvent event) {
		armorMap.remove(event.getPlayer().getUniqueId());
	}
}