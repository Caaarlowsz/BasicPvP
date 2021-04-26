package com.github.caaarlowsz.basicpvp.warp;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;

import com.github.caaarlowsz.basicpvp.BasicKitPvP;
import com.github.caaarlowsz.basicpvp.utils.Stacks;
import com.github.caaarlowsz.basicpvp.warp.commands.SetCommand;
import com.github.caaarlowsz.basicpvp.warp.commands.SpawnCommand;
import com.github.caaarlowsz.basicpvp.warp.commands.WarpCommand;
import com.github.caaarlowsz.basicpvp.warp.warps.FPSWarp;
import com.github.caaarlowsz.basicpvp.warp.warps.LavaChallengeWarp;
import com.github.caaarlowsz.basicpvp.warp.warps.SpawnWarp;
import com.github.caaarlowsz.basicpvp.warp.warps.UMvUMWarp;

public final class Warps {

	private static final ArrayList<Warp> warps = new ArrayList<>();
	private static final Warp NONE_WARP = new Warp("Nenhuma",
			Stacks.item(Material.STAINED_GLASS_PANE, "§aWarp Nenhuma", "§7Sem descrição.")),
			DEFAULT_WARP = new SpawnWarp();

	public static Warp getNoneWarp() {
		return NONE_WARP;
	}

	public static Warp getDefaultWarp() {
		return DEFAULT_WARP;
	}

	public static ArrayList<Warp> getWarps() {
		return warps;
	}

	public static Warp getByName(String name) {
		return getWarps().stream().filter(warp -> warp.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
	}

	public static Warp getByIcon(ItemStack icon) {
		return getWarps().stream()
				.filter(warp -> warp.getIcon().hasItemMeta() && icon.hasItemMeta()
						&& warp.getIcon().getItemMeta().hasDisplayName() && icon.getItemMeta().hasDisplayName() && warp
								.getIcon().getItemMeta().getDisplayName().equals(icon.getItemMeta().getDisplayName()))
				.findFirst().orElse(null);
	}

	public Warps(BasicKitPvP plugin) {
		WarpsFile.createNewFile(plugin.getDataFolder().getPath());

		getWarps().clear();
		getWarps().add(new UMvUMWarp());
		getWarps().add(new FPSWarp());
		getWarps().add(new LavaChallengeWarp());

		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new WarpsGUI(), plugin);

		pm.registerEvents(new LavaChallengeWarp(), plugin);

		plugin.getCommand("set").setExecutor(new SetCommand());
		plugin.getCommand("spawn").setExecutor(new SpawnCommand());
		plugin.getCommand("warp").setExecutor(new WarpCommand());
	}
}