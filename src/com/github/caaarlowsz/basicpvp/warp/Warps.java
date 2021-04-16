package com.github.caaarlowsz.basicpvp.warp;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.plugin.PluginManager;

import com.github.caaarlowsz.basicpvp.BasicKitPvP;
import com.github.caaarlowsz.basicpvp.utils.Stacks;
import com.github.caaarlowsz.basicpvp.warp.warps.SpawnWarp;

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

	public Warps(BasicKitPvP plugin) {
		WarpsFile.createNewFile(plugin.getDataFolder().getPath());

		getWarps().clear();

		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new WarpsGUI(), plugin);
	}
}