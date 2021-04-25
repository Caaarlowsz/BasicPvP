package com.github.caaarlowsz.basicpvp.utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

import com.github.caaarlowsz.basicpvp.BasicKitPvP;
import com.github.caaarlowsz.basicpvp.sidebar.Sidebar;

public final class Strings {

	private static FileConfiguration config = BasicKitPvP.getInstance().getConfig();

	private static String color(String input) {
		return ChatColor.translateAlternateColorCodes('&', input);
	}

	public static String getNome() {
		return color(config.getString("servidor.nome", "&aBasicPvP"));
	}

	public static String getPrefixo() {
		return color(config.getString("servidor.prefixo", "&f[{nome}&f]")).replace("{nome}", getNome());
	}

	public static String getWebsite() {
		return config.getString("informacoes.website", "www.basicpvp.com.br");
	}

	public static String getLoja() {
		return config.getString("informacoes.loja", "{website}/loja").replace("{website}", getWebsite());
	}

	public static String getDiscord() {
		return config.getString("informacoes.discord", "{website}/discord").replace("{website}", getWebsite());
	}

	public static String getTrialModApplication() {
		return config.getString("aplicacoes.trialmod", "{website}/trialmod").replace("{website}", getWebsite());
	}

	public static String getMOTD() {
		return color(config.getString("mensagens.motd", "{prefixo} &aEstamos esperando por você!"))
				.replace("{nl}", "\n").replace("{nome}", getNome()).replace("{prefixo}", getPrefixo())
				.replace("{website}", getWebsite()).replace("{loja}", getLoja()).replace("{discord}", getDiscord());
	}

	public static String getWhitelistMOTD() {
		return color(config.getString("mensagens.whitelist-motd", "{prefixo} &cServidor em manutenção."))
				.replace("{nl}", "\n").replace("{nome}", getNome()).replace("{prefixo}", getPrefixo())
				.replace("{website}", getWebsite()).replace("{loja}", getLoja()).replace("{discord}", getDiscord());
	}

	public static String getCapacete() {
		return color(config.getString("itens.geral.capacete", "&aCapacete")).replace("{nome}", getNome());
	}

	public static String getPeitoral() {
		return color(config.getString("itens.geral.peitoral", "&aPeitoral")).replace("{nome}", getNome());
	}

	public static String getCalca() {
		return color(config.getString("itens.geral.calca", "&aCalça")).replace("{nome}", getNome());
	}

	public static String getBotas() {
		return color(config.getString("itens.geral.botas", "&aBotas")).replace("{nome}", getNome());
	}

	public static String getEspada() {
		return color(config.getString("itens.geral.espada", "&aEspada")).replace("{nome}", getNome());
	}

	public static String getBussola() {
		return color(config.getString("itens.geral.bussola", "&aBússola")).replace("{nome}", getNome());
	}

	public static String getPote() {
		return color(config.getString("itens.geral.pote", "&aPote")).replace("{nome}", getNome());
	}

	public static String getCogumeloVermelho() {
		return color(config.getString("itens.geral.cogumelo-vermelho", "&aCogumelo")).replace("{nome}", getNome());
	}

	public static String getCogumeloMarrom() {
		return color(config.getString("itens.geral.cogumelo-marrom", "&aCogumelo")).replace("{nome}", getNome());
	}

	private static Sidebar getSidebar(String name) {
		Sidebar sidebar = new Sidebar(color(config.getString("sidebar." + name + ".titulo", "{nome}"))
				.replace("{nome}", getNome()).replace("{prefixo}", getPrefixo().replace("{website}",
						getWebsite().replace("{loja}", getLoja()).replace("{discord}", getDiscord()))));
		List<String> lines = Arrays.asList(" ", " Cargo: {player_group}", " Moedas: &6{player_coins}", " ",
				" KillStreak: &7{player_killstreak}", " Abates: &7{player_kills}", " Mortes: &7{player_deaths}", " ",
				" Rank: {player_rank}", " Jogadores: &7{server_players}/{server_slots}", "&e{website}");
		if (config.contains("sidebar." + name + ".linhas"))
			lines = config.getStringList("sidebar." + name + ".linhas");
		else
			config.set("sidebar." + name + ".linhas", lines);
		Collections.reverse(lines);
		for (String line : lines)
			sidebar.addLine(color(line.replace("{nome}", getNome()).replace("{prefixo}", getPrefixo())
					.replace("{website}", getWebsite()).replace("{loja}", getLoja()).replace("{loja}", getLoja())));
		return sidebar;
	}

	public static Sidebar getSidebarKits() {
		return getSidebar("kits");
	}

	public static Sidebar getSidebarWarps() {
		return getSidebar("warps");
	}
}