package com.github.caaarlowsz.basicpvp.utils;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

import com.github.caaarlowsz.basicpvp.BasicKitPvP;

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

	public static String getCorPrincipal() {
		return color(config.getString("mensagens.cor-principal", "&a"));
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

	public static String getPeitoral() {
		return color(config.getString("itens.peitoral", "{cor_principal}Peitoral")).replace("{cor_principal}",
				getCorPrincipal());
	}

	public static String getEspadaDePedra() {
		return color(config.getString("itens.espada-de-pedra", "{cor_principal}Espada")).replace("{cor_principal}",
				getCorPrincipal());
	}

	public static String getBussola() {
		return color(config.getString("itens.bussola", "{cor_principal}Bússola")).replace("{cor_principal}",
				getCorPrincipal());
	}

	public static String getPote() {
		return color(config.getString("itens.pote", "{cor_principal}Pote")).replace("{cor_principal}",
				getCorPrincipal());
	}

	public static String getCogumeloVermelho() {
		return color(config.getString("itens.cogumelo-vermelho", "{cor_principal}Cogumelo")).replace("{cor_principal}",
				getCorPrincipal());
	}

	public static String getCogumeloMarrom() {
		return color(config.getString("itens.cogumelo-marrom", "{cor_principal}Cogumelo")).replace("{cor_principal}",
				getCorPrincipal());
	}
}