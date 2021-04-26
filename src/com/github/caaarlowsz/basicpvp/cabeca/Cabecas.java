package com.github.caaarlowsz.basicpvp.cabeca;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;

import com.github.caaarlowsz.basicpvp.BasicKitPvP;

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
		return getCabecas().stream()
				.filter(cabeca -> cabeca.getIcon().hasItemMeta() && icon.hasItemMeta()
						&& cabeca.getIcon().getItemMeta().hasDisplayName() && icon.getItemMeta().hasDisplayName()
						&& cabeca.getIcon().getItemMeta().getDisplayName().equals(icon.getItemMeta().getDisplayName()))
				.findFirst().orElse(null);
	}

	public Cabecas(BasicKitPvP plugin) {
		getCabecas().clear();
		getCabecas().add(new Cabeca("Bancada de Trabalho"));
		getCabecas().add(new Cabeca("Vidro"));
		getCabecas().add(new Cabeca("Estante"));
		getCabecas().add(new Cabeca("Dinamite"));
		getCabecas().add(new Cabeca("Gerador de Monstros"));
		getCabecas().add(new Cabeca("Bloco de Diamante"));
		getCabecas().add(new Cabeca("Lampada de Redstone"));

		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new CabecasGUI(), plugin);
	}
}