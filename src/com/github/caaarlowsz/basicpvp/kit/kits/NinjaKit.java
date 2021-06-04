package com.github.caaarlowsz.basicpvp.kit.kits;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;

import com.github.caaarlowsz.basicpvp.apis.WorldGuardAPI;
import com.github.caaarlowsz.basicpvp.kit.Kit;
import com.github.caaarlowsz.basicpvp.kit.KitAPI;
import com.github.caaarlowsz.basicpvp.utils.Strings;

public final class NinjaKit extends Kit implements Listener {

	private static final HashMap<UUID, UUID> ninjaMap = new HashMap<>();

	public NinjaKit() {
		super("Ninja");
	}

	@EventHandler
	private void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
		if (event.getEntity() instanceof Player && event.getDamager() instanceof Player) {
			Player player = (Player) event.getEntity(), damager = (Player) event.getDamager();
			if (KitAPI.getKit(damager) instanceof NinjaKit)
				ninjaMap.put(damager.getUniqueId(), player.getUniqueId());
		}
	}

	@EventHandler
	private void onPlayerToggleSneak(PlayerToggleSneakEvent event) {
		if (event.isSneaking())
			return;
		Player player = event.getPlayer();

		if (KitAPI.getKit(player) instanceof NinjaKit && WorldGuardAPI.hasPvP(player)) {
			if (!this.hasCooldown(player)) {
				if (ninjaMap.containsKey(player.getUniqueId())) {
					Player target = Bukkit.getPlayer(ninjaMap.get(player.getUniqueId()));
					if (target != null) {
						if (KitAPI.hasKit(target) && WorldGuardAPI.hasPvP(target)) {
							if (player.getLocation().distance(target.getLocation()) <= 50) {
								this.addCooldown(player, 10);
								player.teleport(target.getLocation());
								player.playSound(player.getLocation(), Sound.WITHER_SHOOT, 1F, 1F);
								player.sendMessage(Strings.getPrefixo() + " §aTeleportado!");
							} else
								player.sendMessage(
										Strings.getDesafioExtremoMoedas() + " §cO seu alvo está muito distance.");
						} else
							player.sendMessage(Strings.getPrefixo() + " §cNão foi possível teleportar até seu alvo.");
					} else {
						ninjaMap.remove(player.getUniqueId());
						player.sendMessage(Strings.getPrefixo() + " §cSeu alvo não foi encontrado.");
					}
				} else
					player.sendMessage(Strings.getPrefixo() + " §eNenhum alvo foi encontrado.");
			} else
				player.sendMessage(Strings.getPrefixo() + " §cAguarde " + this.getRemaingSeconds(player) + ".");
		}
	}

	@EventHandler
	private void onPlayerDeath(PlayerDeathEvent event) {
		Player player = event.getEntity(), killer = player.getKiller();
		ninjaMap.remove(player.getUniqueId());
		if (killer != null && killer != player)
			ninjaMap.remove(killer.getUniqueId());
	}

	@EventHandler
	private void onPlayerQuit(PlayerQuitEvent event) {
		ninjaMap.remove(event.getPlayer().getUniqueId());
	}
}