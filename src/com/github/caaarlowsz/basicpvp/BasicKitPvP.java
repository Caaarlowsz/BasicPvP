package com.github.caaarlowsz.basicpvp;

import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.caaarlowsz.basicpvp.commands.BuildCommand;
import com.github.caaarlowsz.basicpvp.commands.ChatCommand;
import com.github.caaarlowsz.basicpvp.commands.SpawnCommand;
import com.github.caaarlowsz.basicpvp.commands.TeleportCommand;
import com.github.caaarlowsz.basicpvp.commands.TeleportHereCommand;
import com.github.caaarlowsz.basicpvp.commands.TellCommand;
import com.github.caaarlowsz.basicpvp.commands.WarpCommand;
import com.github.caaarlowsz.basicpvp.files.StatusFile;
import com.github.caaarlowsz.basicpvp.guis.MenuGUI;
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
		StatusFile.createNewFile(this.getDataFolder().getPath());

		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new MenuGUI(), this);

		pm.registerEvents(new ChatListeners(), this);
		pm.registerEvents(new JumpBlockListeners(), this);
		pm.registerEvents(new PlayerListeners(), this);
		pm.registerEvents(new SignListeners(), this);
		pm.registerEvents(new WorldListeners(), this);

		this.getCommand("build").setExecutor(new BuildCommand());
		this.getCommand("chat").setExecutor(new ChatCommand());
		this.getCommand("spawn").setExecutor(new SpawnCommand());
		this.getCommand("teleport").setExecutor(new TeleportCommand());
		this.getCommand("teleporthere").setExecutor(new TeleportHereCommand());
		this.getCommand("tell").setExecutor(new TellCommand());
		this.getCommand("warp").setExecutor(new WarpCommand());

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