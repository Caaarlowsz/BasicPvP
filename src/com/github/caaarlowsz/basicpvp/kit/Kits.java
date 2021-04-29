package com.github.caaarlowsz.basicpvp.kit;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;

import com.github.caaarlowsz.basicpvp.BasicKitPvP;
import com.github.caaarlowsz.basicpvp.kit.guis.LojaDeKitsGUI;
import com.github.caaarlowsz.basicpvp.kit.guis.SeusKitsGUI;
import com.github.caaarlowsz.basicpvp.kit.kits.AnchorKit;
import com.github.caaarlowsz.basicpvp.kit.kits.ArcherKit;
import com.github.caaarlowsz.basicpvp.kit.kits.CamelKit;
import com.github.caaarlowsz.basicpvp.kit.kits.PvPKit;
import com.github.caaarlowsz.basicpvp.kit.kits.SnailKit;
import com.github.caaarlowsz.basicpvp.kit.kits.ThorKit;
import com.github.caaarlowsz.basicpvp.kit.kits.ViperKit;
import com.github.caaarlowsz.basicpvp.utils.Stacks;

public final class Kits {

	private static final ArrayList<Kit> kits = new ArrayList<>();
	private static final Kit NONE_KIT = new Kit("Nenhum", 0,
			Stacks.item(Material.STAINED_GLASS_PANE, "§aKit Nenhum", "§7Sem descrição.")), DEFAULT_KIT = new PvPKit();

	public static Kit getNoneKit() {
		return NONE_KIT;
	}

	public static Kit getDefaultKit() {
		return DEFAULT_KIT;
	}

	public static ArrayList<Kit> getKits() {
		return kits;
	}

	public static Kit getByName(String name) {
		return getKits().stream().filter(kit -> kit.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
	}

	public static Kit getByIcon(ItemStack icon) {
		return getKits().stream()
				.filter(kit -> kit.getIcon().hasItemMeta() && icon.hasItemMeta()
						&& kit.getIcon().getItemMeta().hasDisplayName() && icon.getItemMeta().hasDisplayName() && kit
								.getIcon().getItemMeta().getDisplayName().equals(icon.getItemMeta().getDisplayName()))
				.findFirst().orElse(null);
	}

	public Kits(BasicKitPvP plugin) {
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new KitListeners(), plugin);

		pm.registerEvents(new LojaDeKitsGUI(), plugin);
		pm.registerEvents(new SeusKitsGUI(), plugin);

		pm.registerEvents(new AnchorKit(), plugin);
		pm.registerEvents(new CamelKit(), plugin);
		pm.registerEvents(new SnailKit(), plugin);
		pm.registerEvents(new ThorKit(), plugin);
		pm.registerEvents(new ViperKit(), plugin);

		plugin.getCommand("kit").setExecutor(new KitCommand());

		getKits().clear();
		getKits().add(DEFAULT_KIT);
		getKits().add(new AnchorKit());
		getKits().add(new ArcherKit());
		getKits().add(new CamelKit());
		getKits().add(new SnailKit());
		getKits().add(new ThorKit());
		getKits().add(new ViperKit());
	}
}