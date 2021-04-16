package com.github.caaarlowsz.basicpvp.kit;

import org.bukkit.Material;

import com.github.caaarlowsz.basicpvp.utils.Stacks;

public final class Kits {

	private static final Kit NONE_KIT = new Kit("Nenhum",
			Stacks.item(Material.STAINED_GLASS_PANE, "§aKit Nenhum", "§7Sem descrição."));

	public static Kit getNoneKit() {
		return NONE_KIT;
	}

	public Kits() {
	}
}