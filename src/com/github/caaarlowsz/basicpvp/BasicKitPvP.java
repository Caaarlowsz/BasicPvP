package com.github.caaarlowsz.basicpvp;

import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.caaarlowsz.basicpvp.kit.Kits;
import com.github.caaarlowsz.basicpvp.listeners.ChatListeners;
import com.github.caaarlowsz.basicpvp.listeners.JumpBlockListeners;
import com.github.caaarlowsz.basicpvp.listeners.PlayerListeners;
import com.github.caaarlowsz.basicpvp.listeners.SignListeners;
import com.github.caaarlowsz.basicpvp.listeners.WorldListeners;
import com.github.caaarlowsz.basicpvp.utils.Strings;
import com.github.caaarlowsz.basicpvp.warp.Warps;

public final class BasicKitPvP extends JavaPlugin {

	public static BasicKitPvP getInstance() {
		return getPlugin(BasicKitPvP.class);
	}

	@Override
	public void onLoad() {
		super.onLoad();
	}

	@Override
	public void onEnable() {
		super.onEnable();
		this.saveDefaultConfig();

		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new ChatListeners(), this);
		pm.registerEvents(new JumpBlockListeners(), this);
		pm.registerEvents(new PlayerListeners(), this);
		pm.registerEvents(new SignListeners(), this);
		pm.registerEvents(new WorldListeners(), this);

		new Kits(this);
		new Warps(this);

		Bukkit.getConsoleSender().sendMessage(Strings.getPrefixo() + " §aPlugin habilitado com sucesso.");
	}

	@Override
	public void onDisable() {
		super.onDisable();
		HandlerList.unregisterAll(this);
		Bukkit.getScheduler().cancelTasks(this);
		Bukkit.getOnlinePlayers()
				.forEach(players -> players.kickPlayer(Strings.getPrefixo() + " §cServidor reiniciando..."));
		Bukkit.getConsoleSender().sendMessage(Strings.getPrefixo() + " §cPlugin desabilitado com sucesso.");
	}
}