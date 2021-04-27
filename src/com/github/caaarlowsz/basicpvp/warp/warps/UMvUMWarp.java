package com.github.caaarlowsz.basicpvp.warp.warps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.PlayerInventory;

import com.github.caaarlowsz.basicpvp.utils.Stacks;
import com.github.caaarlowsz.basicpvp.warp.Warp;

public final class UMvUMWarp extends Warp implements Listener {

	private final HashMap<UUID, UUID> enemyMap;
	private final HashMap<UUID, ArrayList<UUID>> invitesMap;
	private final ArrayList<UUID> fastDuelList;

	public UMvUMWarp() {
		super("1v1");
		this.enemyMap = new HashMap<>();
		this.invitesMap = new HashMap<>();
		this.fastDuelList = new ArrayList<>();
	}

	@Override
	public void giveItems(Player player) {
		PlayerInventory inv = player.getInventory();
		super.giveItems(player);

		inv.setItem(3, Stacks.item(Material.BLAZE_ROD, "§aConvidar para 1v1"));
		inv.setItem(5, Stacks.item(Material.INK_SACK, 1, 8, "§a1v1 Rápido"));
		player.updateInventory();
	}

	public ArrayList<UUID> getInvites(Player player) {
		return this.invitesMap.getOrDefault(player.getUniqueId(), new ArrayList<>());
	}

	public void clearInvites(Player player) {
		this.invitesMap.remove(player.getUniqueId());
	}

	public boolean hasInvite(Player player, Player inviter) {
		return this.getInvites(player).contains(inviter.getUniqueId());
	}

	public void addInvite(Player player, Player inviter) {
		if (!this.hasInvite(player, inviter)) {
			ArrayList<UUID> invites = this.getInvites(player);
			invites.add(inviter.getUniqueId());
			this.invitesMap.put(player.getUniqueId(), invites);
		}
	}

	public void removeInvite(Player player, Player inviter) {
		ArrayList<UUID> invites = this.getInvites(player);
		invites.remove(inviter.getUniqueId());
		this.invitesMap.put(player.getUniqueId(), invites);
	}

	public boolean hasFastDuel(Player player) {
		return this.fastDuelList.contains(player.getUniqueId());
	}

	public void addFastDuel(Player player) {
		if (!this.hasFastDuel(player))
			this.fastDuelList.add(player.getUniqueId());
	}

	public void removeFastDuel(Player player) {
		this.fastDuelList.remove(player.getUniqueId());
	}

	public boolean hasEnemy(Player player) {
		return this.enemyMap.containsKey(player.getUniqueId());
	}

	public Player getEnemy(Player player) {
		if (this.hasEnemy(player))
			return Bukkit.getPlayer(this.enemyMap.get(player.getUniqueId()));
		return null;
	}

	public void setEnemy(Player player, Player enemy) {
		this.enemyMap.put(player.getUniqueId(), enemy.getUniqueId());
	}

	public void removeEnemy(Player player) {
		this.enemyMap.remove(player.getUniqueId());
	}
}