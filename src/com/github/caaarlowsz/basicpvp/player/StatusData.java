package com.github.caaarlowsz.basicpvp.player;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;

import com.github.caaarlowsz.basicpvp.BasicKitPvP;
import com.github.caaarlowsz.basicpvp.utils.MySQL;

public class StatusData {

	private static boolean useMySQL = true;
	private static MySQL mysql;
	private static File file;
	private static YamlConfiguration config;

	public static void createDatabase() {
		if (useMySQL) {
			mysql = new MySQL("database.haskhosting.com", "3306", "s337_commons-test", "u337_ZzA1Cnnv3O",
					"6g.DH6ut6^6^fVkGdhqBHz5+");
			mysql.createTable("JOGADORES", "`NICKNAME` varchar(16)", "`MOEDAS` int(10)", "`XP` int(10)",
					"`KILLSTREAK` int(10)", "`ABATES` int(10)", "`MORTES` int(10)");
		} else {
			file = new File(BasicKitPvP.getInstance().getDataFolder(), "status.yml");
			if (!file.exists())
				file.getParentFile().mkdirs();
			config = YamlConfiguration.loadConfiguration(file);
		}
	}

	public static boolean hasAccount(String nickname) {
		if (useMySQL)
			return mysql.exists("SELECT * FROM JOGADORES WHERE NICKNAME='" + nickname + "'");
		return config.contains("status." + nickname);
	}

	public static void createAccount(String nickname) {
		if (!hasAccount(nickname)) {
			if (useMySQL)
				mysql.update(
						"INSERT INTO `JOGADORES` (`NICKNAME`, `MOEDAS`, `XP`, `KILLSTREAK`, `ABATES`, `MORTES`) VALUES ('"
								+ nickname + "', '0', '0', '0', '0', '0')");
			else
				try {
					config.set("status." + nickname + ".moedas", 0);
					config.set("status." + nickname + ".xp", 0);
					config.set("status." + nickname + ".killstreak", 0);
					config.set("status." + nickname + ".abates", 0);
					config.set("status." + nickname + ".mortes", 0);
					config.save(file);
				} catch (IOException ex) {
					ex.printStackTrace();
				}
		}
	}

	public static Status loadAccount(String nickname) {
		if (hasAccount(nickname)) {
			if (useMySQL) {
				return new Status(nickname,
						mysql.queryInt("SELECT * FROM JOGADORES WHERE NICKNAME='" + nickname + "'", "MOEDAS"),
						mysql.queryInt("SELECT * FROM JOGADORES WHERE NICKNAME='" + nickname + "'", "XP"),
						mysql.queryInt("SELECT * FROM JOGADORES WHERE NICKNAME='" + nickname + "'", "KILLSTREAK"),
						mysql.queryInt("SELECT * FROM JOGADORES WHERE NICKNAME='" + nickname + "'", "ABATES"),
						mysql.queryInt("SELECT * FROM JOGADORES WHERE NICKNAME='" + nickname + "'", "MORTES"));
			}

			return new Status(nickname, config.getInt("status." + nickname + ".moedas", 0),
					config.getInt("status." + nickname + ".xp", 0),
					config.getInt("status." + nickname + ".killstreak", 0),
					config.getInt("status." + nickname + ".abates", 0),
					config.getInt("status." + nickname + ".mortes", 0));
		}

		return new Status(nickname);
	}

	public static void setMoedas(String nickname, int moedas) {
		if (hasAccount(nickname)) {
			if (useMySQL)
				mysql.update("UPDATE JOGADORES SET MOEDAS='" + moedas + "' WHERE NICKNAME='" + nickname + "'");
			else
				try {
					config.set("status." + nickname + ".moedas", moedas);
					config.save(file);
				} catch (IOException ex) {
					ex.printStackTrace();
				}
		}
	}

	public static void setXP(String nickname, int xp) {
		if (hasAccount(nickname)) {
			if (useMySQL)
				mysql.update("UPDATE JOGADORES SET XP='" + xp + "' WHERE NICKNAME='" + nickname + "'");
			else
				try {
					config.set("status." + nickname + ".xp", xp);
					config.save(file);
				} catch (IOException ex) {
					ex.printStackTrace();
				}
		}
	}

	public static void setKillStreak(String nickname, int killstreak) {
		if (hasAccount(nickname)) {
			if (useMySQL)
				mysql.update("UPDATE JOGADORES SET KILLSTREAK='" + killstreak + "' WHERE NICKNAME='" + nickname + "'");
			else
				try {
					config.set("status." + nickname + ".killstreak", killstreak);
					config.save(file);
				} catch (IOException ex) {
					ex.printStackTrace();
				}
		}
	}

	public static void setAbates(String nickname, int abates) {
		if (hasAccount(nickname)) {
			if (useMySQL)
				mysql.update("UPDATE JOGADORES SET ABATES='" + abates + "' WHERE NICKNAME='" + nickname + "'");
			else
				try {
					config.set("status." + nickname + ".abates", abates);
					config.save(file);
				} catch (IOException ex) {
					ex.printStackTrace();
				}
		}
	}

	public static void setMortes(String nickname, int mortes) {
		if (hasAccount(nickname)) {
			if (useMySQL)
				mysql.update("UPDATE JOGADORES SET MORTES='" + mortes + "' WHERE NICKNAME='" + nickname + "'");
			else
				try {
					config.set("status." + nickname + ".mortes", mortes);
					config.save(file);
				} catch (IOException ex) {
					ex.printStackTrace();
				}
		}
	}
}