package com.github.caaarlowsz.basicpvp.cabeca;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import com.github.caaarlowsz.basicpvp.utils.Stacks;
import com.github.caaarlowsz.basicpvp.utils.Strings;

public enum Cabeca {
	NENHUMA("Nenhuma", null),
	CRAFTING_TABLE("Bancada de Trabalho", Stacks.item(Material.WORKBENCH, Strings.getCorPrincipal() + "Bancada de Trabalho", "§7É usada para se fabricar itens,", "§7responsável por ser a base", "§7da evolução dos materiais.")),
	VIDRO("Vidro", Stacks.item(Material.GLASS, Strings.getCorPrincipal() + "Vidro", "§7Utilizado em decorações para", "§7se ver através de paredes e muros.")),
	ESTANTE("Estante", Stacks.item(Material.BOOKSHELF, Strings.getCorPrincipal() + "Estante", "§7Utilizada para fazer", "§7grandes livrarias.")),
	DINAMITE("Dinamite", Stacks.item(Material.TNT, Strings.getCorPrincipal() + "Dinamite", "§7Utilizada em mineração", "§7e em trollagens.")),
	MOBSPAWNER("Gerador de Monstros", Stacks.item(Material.MOB_SPAWNER, Strings.getCorPrincipal() + "Gerador de Monstros", "§7Utilizado para fazer farms", "§7e gerar monstros e animais.")),
	BLOCO_DE_DIAMANTE("Bloco de Diamante", Stacks.item(Material.DIAMOND_BLOCK, Strings.getCorPrincipal() + "Bloco de Diamante", "§7Não possui um uso,", "§7apenas para ostentação.")),
	LAMPADA_DE_REDSTONE("Lâmpada de Redstone", Stacks.item(Material.REDSTONE_LAMP_OFF, Strings.getCorPrincipal() + "Lâmpada de Redstone", "§7Utilizada em sistemas para", "§7identificar atividade."));

	private final String name;
	private final ItemStack icon;

	private Cabeca(String name, ItemStack icon) {
		this.name = name;
		this.icon = icon;
	}

	public String getName() {
		return this.name;
	}

	public ItemStack getIcon() {
		return this.icon;
	}

	public static Cabeca getByName(String name) {
		for (Cabeca cabeca : values()) {
			if (cabeca.getName().equalsIgnoreCase(name))
				return cabeca;
		}
		return null;
	}
}