package com.github.caaarlowsz.basicpvp.warp.warps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

import com.github.caaarlowsz.basicpvp.BasicKitPvP;
import com.github.caaarlowsz.basicpvp.apis.StaffAPI;
import com.github.caaarlowsz.basicpvp.cabeca.CabecaAPI;
import com.github.caaarlowsz.basicpvp.player.StatusFile;
import com.github.caaarlowsz.basicpvp.utils.Stacks;
import com.github.caaarlowsz.basicpvp.utils.Strings;
import com.github.caaarlowsz.basicpvp.warp.Warp;
import com.github.caaarlowsz.basicpvp.warp.WarpAPI;
import com.github.caaarlowsz.basicpvp.warp.WarpsFile;

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

		this.clearInvites(player);
		this.removeFastDuel(player);
		this.removeEnemy(player);

		inv.setItem(Stacks.getSlotConfigItem("itens.1v1.convidar"), this.getInviteItem());
		inv.setItem(Stacks.getSlotConfigItem("itens.1v1.1v1-rapido"), this.getFast1v1Item());
		player.updateInventory();
	}

	public ItemStack getInviteItem() {
		return Stacks.getConfigItem("itens.1v1.convidar");
	}

	public ItemStack getFast1v1Item() {
		return Stacks.getConfigItem("itens.1v1.1v1-rapido");
	}

	public ItemStack getFinding1v1Item() {
		return Stacks.getConfigItem("itens.1v1.procurando-1v1-rapido");
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
		if (this.fastDuelList.size() > 0) {
			UUID uuid = this.fastDuelList.get(new Random().nextInt(this.fastDuelList.size()));
			Player enemy = Bukkit.getPlayer(uuid);
			if (enemy != null)
				this.set1v1Combat(enemy, player);
			this.fastDuelList.remove(uuid);
		} else if (!this.hasFastDuel(player))
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

	public void showPlayers(Player player) {
		Bukkit.getOnlinePlayers().stream().filter(
				players -> (!StaffAPI.hasAdmin(players) && !player.hasPermission("kitpvp.permission.viewadmins")))
				.forEach(players -> player.showPlayer(players));
	}

	private void giveKit(Player player) {
		this.clearInvites(player);
		this.removeFastDuel(player);

		player.setGameMode(GameMode.SURVIVAL);
		player.setAllowFlight(false);
		player.setFlying(false);
		player.setHealthScale(20D);
		player.setMaxHealth(20D);
		player.setHealth(20D);

		PlayerInventory inv = player.getInventory();
		inv.setArmorContents(null);
		inv.clear();

		BasicKitPvP.getKitType("itens.1v1.modo").applyKit(player);
		for (int i = 0; i < 8; i++)
			inv.addItem(Stacks.item(Material.MUSHROOM_SOUP));

		CabecaAPI.updateCabeca(player);
		player.updateInventory();

		Bukkit.getOnlinePlayers().forEach(players -> player.hidePlayer(players));
	}

	public void set1v1Combat(Player player, Player player2) {
		if (WarpsFile.hasLocation("1v1.Pos1") && WarpsFile.hasLocation("1v1.Pos2")) {
			this.setEnemy(player, player2);
			this.giveKit(player);
			player.teleport(WarpsFile.getLocation("1v1.Pos1"));
			player.showPlayer(player2);

			this.setEnemy(player2, player);
			this.giveKit(player2);
			player2.teleport(WarpsFile.getLocation("1v1.Pos2"));
			player2.showPlayer(player);
		}
	}

	@EventHandler
	private void onPlayerInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		if (WarpAPI.getWarp(player) instanceof UMvUMWarp && event.hasItem()) {
			ItemStack fast1v1 = this.getFast1v1Item(), finding1v1 = this.getFinding1v1Item();
			if (event.getItem().isSimilar(fast1v1)) {
				this.addFastDuel(player);
				event.getItem().setType(finding1v1.getType());
				event.getItem().setAmount(finding1v1.getAmount());
				event.getItem().setDurability(finding1v1.getDurability());
				if (finding1v1.hasItemMeta()) {
					ItemMeta meta = event.getItem().getItemMeta(), mFinding1v1 = finding1v1.getItemMeta();
					if (mFinding1v1.hasDisplayName())
						meta.setDisplayName(mFinding1v1.getDisplayName());
					if (mFinding1v1.hasLore())
						meta.setLore(mFinding1v1.getLore());
					event.getItem().setItemMeta(meta);
				}
				player.updateInventory();
			} else if (event.getItem().isSimilar(finding1v1)) {
				this.removeFastDuel(player);
				event.getItem().setType(fast1v1.getType());
				event.getItem().setAmount(fast1v1.getAmount());
				event.getItem().setDurability(fast1v1.getDurability());
				if (fast1v1.hasItemMeta()) {
					ItemMeta meta = event.getItem().getItemMeta(), mFast1v1 = fast1v1.getItemMeta();
					if (mFast1v1.hasDisplayName())
						meta.setDisplayName(mFast1v1.getDisplayName());
					if (mFast1v1.hasLore())
						meta.setLore(mFast1v1.getLore());
					event.getItem().setItemMeta(meta);
				}
				player.updateInventory();
			}
		}
	}

	@EventHandler
	private void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
		Player player = event.getPlayer();
		if (WarpAPI.getWarp(player) instanceof UMvUMWarp && player.getItemInHand().isSimilar(this.getInviteItem())
				&& event.getRightClicked() instanceof Player) {
			Player righted = (Player) event.getRightClicked();
			if (WarpAPI.getWarp(righted) instanceof UMvUMWarp) {
				if (this.hasInvite(righted, player))
					player.sendMessage(
							Strings.getPrefixo() + " §cVocê já enviou um convite para " + righted.getName() + ".");
				else if (this.hasInvite(player, righted)) {
					this.set1v1Combat(player, righted);
					player.sendMessage(
							Strings.getPrefixo() + " §aVocê aceitou o convite de " + righted.getName() + ".");
					righted.sendMessage(Strings.getPrefixo() + " §a" + player.getName() + " aceitou seu convite.");
				} else {
					this.addInvite(righted, player);
					player.sendMessage(
							Strings.getPrefixo() + " §aVocê enviou um convite para " + righted.getName() + ".");
					righted.sendMessage(
							Strings.getPrefixo() + " §aVocê recebeu um convite de " + player.getName() + ".");
				}
			}
		}
	}

	@EventHandler
	private void onPlayerDeath(PlayerDeathEvent event) {
		Player player = event.getEntity();

		if (WarpAPI.getWarp(player) instanceof UMvUMWarp && this.hasEnemy(player) && this.getEnemy(player) != null) {
			Player enemy = this.getEnemy(player);

			this.showPlayers(enemy);
			StatusFile.addMoedas(enemy, 10);
			StatusFile.addXP(enemy, 3);
			StatusFile.addKillStreak(enemy);
			StatusFile.addAbate(enemy);
			WarpAPI.setWarp(enemy, this);
			enemy.playSound(enemy.getLocation(), Sound.ARROW_HIT, 10F, 1F);
			enemy.sendMessage("§6+10 Moedas");
			enemy.sendMessage("§b+3 XP");
			enemy.sendMessage(Strings.getPrefixo() + " §aVocê venceu o 1v1 contra " + player.getName() + ".");

			this.showPlayers(player);
			StatusFile.drawMoedas(player, 5);
			StatusFile.drawXP(player, 1);
			StatusFile.resetKillStreak(player);
			StatusFile.addMorte(player);
			player.sendMessage("§6-5 Moedas");
			player.sendMessage("§b-1 XP");
			player.playSound(player.getLocation(), Sound.ANVIL_USE, 10F, 1F);
			player.sendMessage(Strings.getPrefixo() + " §cVocê perdeu o 1v1 contra " + enemy.getName() + ".");
		}
	}

	@EventHandler
	private void onPlayerQuit(PlayerQuitEvent event) {
		Player player = event.getPlayer();

		if (WarpAPI.getWarp(player) instanceof UMvUMWarp && this.hasEnemy(player) && this.getEnemy(player) != null) {
			Player enemy = this.getEnemy(player);

			this.showPlayers(enemy);
			StatusFile.addMoedas(enemy, 10);
			StatusFile.addXP(enemy, 3);
			StatusFile.addKillStreak(enemy);
			StatusFile.addAbate(enemy);
			WarpAPI.setWarp(enemy, this);
			enemy.playSound(enemy.getLocation(), Sound.ARROW_HIT, 10F, 1F);
			enemy.sendMessage("§6+10 Moedas");
			enemy.sendMessage("§b+3 XP");
			enemy.sendMessage(Strings.getPrefixo() + " §aVocê venceu o 1v1 contra " + player.getName() + ".");

			this.showPlayers(player);
			StatusFile.drawMoedas(player, 5);
			StatusFile.drawXP(player, 1);
			StatusFile.resetKillStreak(player);
			StatusFile.addMorte(player);
			player.sendMessage("§6-5 Moedas");
			player.sendMessage("§b-1 XP");
			player.playSound(player.getLocation(), Sound.ANVIL_USE, 10F, 1F);
			player.sendMessage(Strings.getPrefixo() + " §cVocê perdeu o 1v1 contra " + enemy.getName() + ".");
		}
	}
}