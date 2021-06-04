package com.github.caaarlowsz.basicpvp;

import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.caaarlowsz.basicpvp.cabeca.Cabecas;
import com.github.caaarlowsz.basicpvp.commands.AdminCommand;
import com.github.caaarlowsz.basicpvp.commands.ApplyCommand;
import com.github.caaarlowsz.basicpvp.commands.BroadcastCommand;
import com.github.caaarlowsz.basicpvp.commands.BuildCommand;
import com.github.caaarlowsz.basicpvp.commands.CageCommand;
import com.github.caaarlowsz.basicpvp.commands.ChatCommand;
import com.github.caaarlowsz.basicpvp.commands.GameModeCommand;
import com.github.caaarlowsz.basicpvp.commands.InfoCommand;
import com.github.caaarlowsz.basicpvp.commands.InventorySeeCommand;
import com.github.caaarlowsz.basicpvp.commands.PingCommand;
import com.github.caaarlowsz.basicpvp.commands.RankCommand;
import com.github.caaarlowsz.basicpvp.commands.ReportCommand;
import com.github.caaarlowsz.basicpvp.commands.TeleportCommand;
import com.github.caaarlowsz.basicpvp.commands.TeleportHereCommand;
import com.github.caaarlowsz.basicpvp.commands.TellCommand;
import com.github.caaarlowsz.basicpvp.guis.MenuGUI;
import com.github.caaarlowsz.basicpvp.kit.Kits;
import com.github.caaarlowsz.basicpvp.listeners.AdminListeners;
import com.github.caaarlowsz.basicpvp.listeners.ChatListeners;
import com.github.caaarlowsz.basicpvp.listeners.JumpBlockListeners;
import com.github.caaarlowsz.basicpvp.listeners.SignListeners;
import com.github.caaarlowsz.basicpvp.listeners.WorldListeners;
import com.github.caaarlowsz.basicpvp.player.PlayerListeners;
import com.github.caaarlowsz.basicpvp.player.StatusData;
import com.github.caaarlowsz.basicpvp.sidebar.SidebarAPI;
import com.github.caaarlowsz.basicpvp.tag.Tags;
import com.github.caaarlowsz.basicpvp.utils.KitType;
import com.github.caaarlowsz.basicpvp.utils.Strings;
import com.github.caaarlowsz.basicpvp.warp.Warps;

public final class BasicKitPvP extends JavaPlugin {

	public static KitType getKitType(String path) {
		return KitType.valueOf(getInstance().getConfig().getString(path));
	}

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
		StatusData.createDatabase();

		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new MenuGUI(), this);

		pm.registerEvents(new AdminListeners(), this);
		pm.registerEvents(new ChatListeners(), this);
		pm.registerEvents(new JumpBlockListeners(), this);
		pm.registerEvents(new PlayerListeners(), this);
		pm.registerEvents(new SignListeners(), this);
		pm.registerEvents(new WorldListeners(), this);

		this.getCommand("admin").setExecutor(new AdminCommand());
		this.getCommand("apply").setExecutor(new ApplyCommand());
		this.getCommand("broadcast").setExecutor(new BroadcastCommand());
		this.getCommand("build").setExecutor(new BuildCommand());
		this.getCommand("cage").setExecutor(new CageCommand());
		this.getCommand("chat").setExecutor(new ChatCommand());
		this.getCommand("gamemode").setExecutor(new GameModeCommand());
		this.getCommand("info").setExecutor(new InfoCommand());
		this.getCommand("inventorysee").setExecutor(new InventorySeeCommand());
		this.getCommand("ping").setExecutor(new PingCommand());
		this.getCommand("rank").setExecutor(new RankCommand());
		this.getCommand("report").setExecutor(new ReportCommand());
		this.getCommand("teleport").setExecutor(new TeleportCommand());
		this.getCommand("teleporthere").setExecutor(new TeleportHereCommand());
		this.getCommand("tell").setExecutor(new TellCommand());

		new Tags(this);
		new Cabecas(this);
		new Kits(this);
		new Warps(this);

		Bukkit.getScheduler().runTaskTimer(this,
				() -> Bukkit.getOnlinePlayers().forEach(players -> SidebarAPI.updateSidebar(players)), 20L, 20L);
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