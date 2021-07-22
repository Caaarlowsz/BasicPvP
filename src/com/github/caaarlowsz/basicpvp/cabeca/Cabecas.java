package com.github.caaarlowsz.basicpvp.cabeca;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;

import com.github.caaarlowsz.basicpvp.BasicKitPvP;
import com.github.caaarlowsz.basicpvp.utils.Stacks;

public final class Cabecas {

	private static final ArrayList<Cabeca> cabecas = new ArrayList<>();
	private static final Cabeca NONE_CABECA = new Cabeca("Nenhuma", null);

	public static ArrayList<Cabeca> getCabecas() {
		return cabecas;
	}

	public static Cabeca getNoneCabeca() {
		return NONE_CABECA;
	}

	public static Cabeca getByName(String name) {
		return getCabecas().stream().filter(cabeca -> cabeca.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
	}

	public static Cabeca getByIcon(ItemStack icon) {
		for (Cabeca cabeca : getCabecas()) {
			ItemStack select = Stacks.applyModel("modelos.cabeca.selecionar", cabeca.getIcon().clone());

			if (select.hasItemMeta() && icon.hasItemMeta() && select.getItemMeta().hasDisplayName()
					&& icon.getItemMeta().hasDisplayName()
					&& select.getItemMeta().getDisplayName().equals(icon.getItemMeta().getDisplayName()))
				return cabeca;

			ItemStack withoutPermission = Stacks.applyModel("modelos.cabeca.sem-permissao", cabeca.getIcon().clone());
			if (withoutPermission.hasItemMeta() && icon.hasItemMeta()
					&& withoutPermission.getItemMeta().hasDisplayName() && icon.getItemMeta().hasDisplayName()
					&& withoutPermission.getItemMeta().getDisplayName().equals(icon.getItemMeta().getDisplayName()))
				return cabeca;
		}
		return null;
	}

	public Cabecas(BasicKitPvP plugin) {
		getCabecas().clear();
		plugin.getConfig().getConfigurationSection("icones.cabecas").getKeys(false)
				.forEach(name -> getCabecas().add(new Cabeca(name)));

		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new CabecasGUI(), plugin);
	}
}