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
import com.github.caaarlowsz.basicpvp.kit.kits.AntiStomperKit;
import com.github.caaarlowsz.basicpvp.kit.kits.ArcherKit;
import com.github.caaarlowsz.basicpvp.kit.kits.CamelKit;
import com.github.caaarlowsz.basicpvp.kit.kits.FishermanKit;
import com.github.caaarlowsz.basicpvp.kit.kits.HulkKit;
import com.github.caaarlowsz.basicpvp.kit.kits.KangarooKit;
import com.github.caaarlowsz.basicpvp.kit.kits.MagmaKit;
import com.github.caaarlowsz.basicpvp.kit.kits.MonkKit;
import com.github.caaarlowsz.basicpvp.kit.kits.NeoKit;
import com.github.caaarlowsz.basicpvp.kit.kits.NinjaKit;
import com.github.caaarlowsz.basicpvp.kit.kits.PhantomKit;
import com.github.caaarlowsz.basicpvp.kit.kits.PoseidonKit;
import com.github.caaarlowsz.basicpvp.kit.kits.PvPKit;
import com.github.caaarlowsz.basicpvp.kit.kits.QuickdropperKit;
import com.github.caaarlowsz.basicpvp.kit.kits.ReaperKit;
import com.github.caaarlowsz.basicpvp.kit.kits.ScoutKit;
import com.github.caaarlowsz.basicpvp.kit.kits.SnailKit;
import com.github.caaarlowsz.basicpvp.kit.kits.StomperKit;
import com.github.caaarlowsz.basicpvp.kit.kits.SwitcherKit;
import com.github.caaarlowsz.basicpvp.kit.kits.ThorKit;
import com.github.caaarlowsz.basicpvp.kit.kits.UrgalKit;
import com.github.caaarlowsz.basicpvp.kit.kits.VikingKit;
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
		for (Kit kit : getKits()) {
			ItemStack select = Stacks.applyModel("modelos.kit.selecionar", kit.getIcon().clone());
			if (select.hasItemMeta() && icon.hasItemMeta() && select.getItemMeta().hasDisplayName()
					&& icon.getItemMeta().hasDisplayName()
					&& select.getItemMeta().getDisplayName().equals(icon.getItemMeta().getDisplayName()))
				return kit;

			ItemStack buy = Stacks.applyModel("modelos.kit.comprar", kit.getIcon().clone());
			if (buy.hasItemMeta() && icon.hasItemMeta() && buy.getItemMeta().hasDisplayName()
					&& icon.getItemMeta().hasDisplayName()
					&& buy.getItemMeta().getDisplayName().equals(icon.getItemMeta().getDisplayName()))
				return kit;
		}
		return null;
	}

	public Kits(BasicKitPvP plugin) {
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new KitListeners(), plugin);

		pm.registerEvents(new LojaDeKitsGUI(), plugin);
		pm.registerEvents(new SeusKitsGUI(), plugin);

		pm.registerEvents(new AnchorKit(), plugin);
		pm.registerEvents(new CamelKit(), plugin);
		pm.registerEvents(new FishermanKit(), plugin);
		pm.registerEvents(new HulkKit(), plugin);
		pm.registerEvents(new KangarooKit(), plugin);
		pm.registerEvents(new MagmaKit(), plugin);
		pm.registerEvents(new MonkKit(), plugin);
		pm.registerEvents(new NeoKit(), plugin);
		pm.registerEvents(new NinjaKit(), plugin);
		pm.registerEvents(new PhantomKit(), plugin);
		pm.registerEvents(new PoseidonKit(), plugin);
		pm.registerEvents(new ReaperKit(), plugin);
		pm.registerEvents(new ScoutKit(), plugin);
		pm.registerEvents(new SnailKit(), plugin);
		pm.registerEvents(new SwitcherKit(), plugin);
		pm.registerEvents(new StomperKit(), plugin);
		pm.registerEvents(new ThorKit(), plugin);
		pm.registerEvents(new UrgalKit(), plugin);
		pm.registerEvents(new VikingKit(), plugin);
		pm.registerEvents(new ViperKit(), plugin);

		plugin.getCommand("kit").setExecutor(new KitCommand());

		getKits().clear();
		getKits().add(DEFAULT_KIT);
		getKits().add(new AnchorKit());
		getKits().add(new AntiStomperKit());
		getKits().add(new ArcherKit());
		getKits().add(new CamelKit());
		getKits().add(new FishermanKit());
		getKits().add(new HulkKit());
		getKits().add(new KangarooKit());
		getKits().add(new MagmaKit());
		getKits().add(new MonkKit());
		getKits().add(new NeoKit());
		getKits().add(new NinjaKit());
		getKits().add(new PhantomKit());
		getKits().add(new PoseidonKit());
		getKits().add(new QuickdropperKit());
		getKits().add(new ReaperKit());
		getKits().add(new ScoutKit());
		getKits().add(new SnailKit());
		getKits().add(new StomperKit());
		getKits().add(new SwitcherKit());
		getKits().add(new ThorKit());
		getKits().add(new UrgalKit());
		getKits().add(new VikingKit());
		getKits().add(new ViperKit());
	}
}