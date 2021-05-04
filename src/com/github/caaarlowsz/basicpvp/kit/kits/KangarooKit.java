package com.github.caaarlowsz.basicpvp.kit.kits;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.util.Vector;

import com.github.caaarlowsz.basicpvp.apis.WorldGuardAPI;
import com.github.caaarlowsz.basicpvp.kit.Kit;
import com.github.caaarlowsz.basicpvp.kit.KitAPI;
import com.github.caaarlowsz.basicpvp.utils.Stacks;

public final class KangarooKit extends Kit implements Listener {

	private static final ArrayList<UUID> jump = new ArrayList<>();

	public KangarooKit() {
		super("Kangaroo");
	}

	@Override
	public void giveItems(Player player) {
		PlayerInventory inv = player.getInventory();
		super.giveItems(player);

		inv.setItem(1, Stacks.item(Material.FIREWORK, "§aPular", "§7Kit " + this.getName()));
	}

	@EventHandler
	private void onPlayerInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		if (KitAPI.getKit(player) instanceof KangarooKit && event.hasItem()
				&& event.getMaterial().equals(Material.FIREWORK)) {
			event.setCancelled(true);
			if (!jump.contains(player.getUniqueId()) && WorldGuardAPI.hasPvP(player)) {
				Vector vector = player.getEyeLocation().getDirection();
				if (player.isSneaking())
					vector.multiply(1.35F).setY(0.66F);
				else
					vector.multiply(0.6F).setY(1F);
				player.setVelocity(vector);
				player.setFallDistance(-3F);
				jump.add(player.getUniqueId());
			}
		}
	}

	@EventHandler
	private void onPlayerMove(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		Block block = event.getFrom().getBlock();
		if (jump.contains(player.getUniqueId()) && (!block.getType().equals(Material.AIR)
				|| !block.getRelative(BlockFace.DOWN).getType().equals(Material.AIR)))
			jump.remove(player.getUniqueId());
	}

	@EventHandler
	private void onEntityDamage(EntityDamageEvent event) {
		if (event.getEntity() instanceof Player && event.getCause().equals(DamageCause.FALL)
				&& event.getDamage() > 7D) {
			Player player = (Player) event.getEntity();
			if (KitAPI.getKit(player) instanceof KangarooKit && WorldGuardAPI.hasPvP(player))
				event.setDamage(7D);
		}
	}
}