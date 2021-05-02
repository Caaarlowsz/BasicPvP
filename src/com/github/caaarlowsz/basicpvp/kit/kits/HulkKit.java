package com.github.caaarlowsz.basicpvp.kit.kits;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

import com.github.caaarlowsz.basicpvp.BasicKitPvP;
import com.github.caaarlowsz.basicpvp.apis.WorldGuardAPI;
import com.github.caaarlowsz.basicpvp.kit.Kit;
import com.github.caaarlowsz.basicpvp.kit.KitAPI;
import com.github.caaarlowsz.basicpvp.utils.Strings;

public final class HulkKit extends Kit implements Listener {

	public HulkKit() {
		super("Hulk");
	}

	@EventHandler
	private void onPlayerInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		if (KitAPI.getKit(player) instanceof HulkKit && WorldGuardAPI.hasPvP(player) && !event.hasItem()
				&& !event.hasBlock() && player.getPassenger() != null && player.getPassenger() instanceof Player) {
			Player passenger = (Player) player.getPassenger();
			Vector vector = player.getEyeLocation().getDirection();
			float pitch = player.getLocation().getPitch();

			if (pitch > 0)
				vector.setY(vector.getY() * 2);
			else
				vector.multiply(2);

			player.eject();
			Bukkit.getScheduler().runTaskLater(BasicKitPvP.getInstance(), () -> passenger.setVelocity(vector), 1L);
		}
	}

	@EventHandler
	private void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
		Player player = event.getPlayer();
		if (KitAPI.getKit(player) instanceof HulkKit && WorldGuardAPI.hasPvP(player)
				&& (player.getItemInHand() == null || player.getItemInHand().getType().equals(Material.AIR))
				&& event.getRightClicked() instanceof Player && player.getPassenger() == null) {
			Player righted = (Player) event.getRightClicked();
			if (!this.hasCooldown(player)) {
				if (righted.getPassenger() == null && WorldGuardAPI.hasPvP(righted)) {
					this.addCooldown(player, 6);
					player.setPassenger(righted);
					player.sendMessage(Strings.getPrefixo() + " §aVocê pegou " + righted.getName() + ".");
					righted.sendMessage(Strings.getPrefixo() + " §eVocê foi pego por " + player.getName() + ".");
				}
			} else
				player.sendMessage(Strings.getPrefixo() + " §cAguarde " + this.getRemaingSeconds(player) + ".");
		}
	}

	@EventHandler
	private void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
		if (event.getEntity() instanceof Player && event.getDamager() instanceof Player) {
			Player player = (Player) event.getEntity(), damager = (Player) event.getDamager();
			if (KitAPI.getKit(damager) instanceof HulkKit && damager.getPassenger().getName().equals(player.getName()))
				event.setCancelled(true);
		}
	}
}