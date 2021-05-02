package com.github.caaarlowsz.basicpvp.kit.kits;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.PlayerInventory;

import com.github.caaarlowsz.basicpvp.apis.WorldGuardAPI;
import com.github.caaarlowsz.basicpvp.kit.Kit;
import com.github.caaarlowsz.basicpvp.kit.KitAPI;
import com.github.caaarlowsz.basicpvp.utils.Stacks;

public final class SwitcherKit extends Kit implements Listener {

	public SwitcherKit() {
		super("Switcher");
	}

	@Override
	public void giveItems(Player player) {
		PlayerInventory inv = player.getInventory();
		super.giveItems(player);

		inv.setItem(1, Stacks.item(Material.SNOW_BALL, 16, "§aTrocador", "§7Kit " + this.getName()));
	}

	@EventHandler
	private void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
		if (event.getEntity() instanceof Player && event.getDamager() instanceof Snowball) {
			Player player = (Player) event.getEntity();
			Snowball snowball = (Snowball) event.getDamager();
			if (snowball.getShooter() instanceof Player) {
				Player shooter = (Player) snowball.getShooter();
				if (KitAPI.getKit(shooter) instanceof SwitcherKit && WorldGuardAPI.hasPvP(player)
						&& WorldGuardAPI.hasPvP(shooter)) {
					Location loc = shooter.getLocation();
					shooter.teleport(player.getLocation());
					player.teleport(loc);

					shooter.getWorld().playEffect(loc, Effect.ENDER_SIGNAL, 10);
					shooter.getWorld().playEffect(loc, Effect.EXTINGUISH, 10);
					shooter.playSound(shooter.getLocation(), Sound.ENDERMAN_TELEPORT, 1F, 1.2F);
				}
			}
		}
	}

	@EventHandler
	private void onPlayerDeath(PlayerDeathEvent event) {
		Player player = event.getEntity(), killer = player.getKiller();
		if (killer != null && killer != player && KitAPI.getKit(killer) instanceof SwitcherKit
				&& WorldGuardAPI.hasPvP(killer))
			killer.getInventory().addItem(Stacks.item(Material.SNOW_BALL, "§aTrocador", "§7Kit " + this.getName()));
	}
}