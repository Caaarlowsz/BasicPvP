package com.github.caaarlowsz.basicpvp.warp.warps;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.util.Vector;

import com.github.caaarlowsz.basicpvp.guis.MenuGUI;
import com.github.caaarlowsz.basicpvp.kit.guis.SeusKitsGUI;
import com.github.caaarlowsz.basicpvp.utils.Stacks;
import com.github.caaarlowsz.basicpvp.utils.Strings;
import com.github.caaarlowsz.basicpvp.warp.Warp;
import com.github.caaarlowsz.basicpvp.warp.WarpAPI;
import com.github.caaarlowsz.basicpvp.warp.WarpsFile;
import com.github.caaarlowsz.basicpvp.warp.WarpsGUI;

public final class SpawnWarp extends Warp implements Listener {

	public SpawnWarp() {
		super("Spawn", Stacks.item(Material.BEACON, "§aSpawn", "§7Local de nascimento padrão."));
	}

	@Override
	public void giveItems(Player player) {
		PlayerInventory inv = player.getInventory();
		super.giveItems(player);

		inv.setItem(Stacks.getSlotConfigItem("itens.spawn.kits"), SeusKitsGUI.getIcon());
		inv.setItem(Stacks.getSlotConfigItem("itens.spawn.menu"), MenuGUI.getIcon());
		inv.setItem(Stacks.getSlotConfigItem("itens.spawn.warps"), WarpsGUI.getIcon());

		player.updateInventory();
	}

	@EventHandler
	private void onPlayerMove(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		if (WarpAPI.getWarp(player) instanceof SpawnWarp && WarpsFile.hasLocation(this.getName())
				&& player.getGameMode() == GameMode.SURVIVAL && player.getAllowFlight()) {
			Location loc = WarpsFile.getLocation(this.getName());
			if (player.getLocation().distance(loc) >= 50) {
				Vector vector = player.getVelocity(),
						subtract = loc.toVector().subtract(player.getLocation().toVector()).setY(0);
				vector.setX(subtract.getX() > 0 ? 1 : (-1));
				vector.setZ(subtract.getZ() > 0 ? 1 : (-1));
				player.setVelocity(vector);
				player.sendMessage(Strings.getPrefixo() + " §cVocê não pode se distanciar do Spawn.");
			}
		}
	}
}