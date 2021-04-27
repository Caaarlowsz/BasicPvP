package com.github.caaarlowsz.basicpvp.kit;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.scheduler.BukkitTask;

import com.github.caaarlowsz.basicpvp.BasicKitPvP;
import com.github.caaarlowsz.basicpvp.utils.Stacks;
import com.github.caaarlowsz.basicpvp.utils.Strings;

public class Kit {

	private final String name;
	private final int price;
	private final ItemStack icon;
	private final HashMap<UUID, Long> longMap;
	private final HashMap<UUID, BukkitTask> taskMap;

	public Kit(String name) {
		this(name, BasicKitPvP.getInstance().getConfig().getInt("icones.kits." + name.toLowerCase() + ".preco"));
	}

	public Kit(String name, int price) {
		this(name, price, Stacks.getConfigItem("icones.kits." + name.toLowerCase()));
	}

	public Kit(String name, int price, ItemStack icon) {
		this.name = name;
		this.price = price;
		this.icon = icon;
		this.longMap = new HashMap<>();
		this.taskMap = new HashMap<>();
	}

	public String getName() {
		return this.name;
	}

	public int getPrice() {
		return this.price;
	}

	public ItemStack getIcon() {
		return this.icon;
	}

	public boolean hasCooldown(Player player) {
		return this.longMap.containsKey(player.getUniqueId());
	}

	public long getCooldown(Player player) {
		if (this.hasCooldown(player))
			return (this.longMap.get(player.getUniqueId()) - System.currentTimeMillis()) / 1000L;
		return 0L;
	}

	public String getRemaingSeconds(Player player) {
		return this.getCooldown(player) + " segundo" + (this.getCooldown(player) == 1 ? "" : "s");
	}

	public void addCooldown(Player player, long seconds) {
		this.longMap.put(player.getUniqueId(), System.currentTimeMillis() + (seconds * 1000L));
		this.taskMap.put(player.getUniqueId(), Bukkit.getScheduler().runTaskLater(BasicKitPvP.getInstance(), () -> {
			if (this.hasCooldown(player)) {
				this.removeCooldown(player);
				player.playSound(player.getLocation(), Sound.LEVEL_UP, 5F, 5F);
				player.sendMessage(
						Strings.getPrefixo() + " §aVocê já pode usar o Kit " + this.getName() + " novamente.");
			}
		}, seconds * 20L));
	}

	public void removeCooldown(Player player) {
		this.longMap.remove(player.getUniqueId());
		if (this.taskMap.containsKey(player.getUniqueId()))
			this.taskMap.remove(player.getUniqueId()).cancel();
	}

	public void giveItems(Player player) {
		player.setGameMode(GameMode.SURVIVAL);
		player.setAllowFlight(false);
		player.setFlying(false);
		player.setHealthScale(20D);
		player.setMaxHealth(20D);
		player.setHealth(20D);

		PlayerInventory inv = player.getInventory();
		inv.setArmorContents(null);
		inv.clear();

		BasicKitPvP.getKitType("itens.arena").applyKit(player);
		inv.setItem(8, Stacks.item(Material.COMPASS, Strings.getBussola()));

		inv.setItem(13, Stacks.item(Material.BOWL, 64, Strings.getPote()));
		inv.setItem(14, Stacks.item(Material.RED_MUSHROOM, 64, Strings.getCogumeloVermelho()));
		inv.setItem(15, Stacks.item(Material.BROWN_MUSHROOM, 64, Strings.getCogumeloMarrom()));

		for (int i = 0; i < 32; i++)
			inv.addItem(Stacks.item(Material.MUSHROOM_SOUP));
	}
}