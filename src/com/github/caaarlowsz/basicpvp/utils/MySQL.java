package com.github.caaarlowsz.basicpvp.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.bukkit.Bukkit;

public class MySQL {

	private String host, database, user, password;
	private int port;
	private Connection connection;

	public MySQL(String host, String database, String user, String password) {
		this(host, 3306, database, user, password);
	}

	public MySQL(String host, int port, String database, String user, String password) {
		this.host = host;
		this.port = port;
		this.database = database;
		this.user = user;
		this.password = password;
	}

	public boolean hasConnection() {
		try {
			return this.connection != null && !this.connection.isClosed();
		} catch (SQLException ex) {
		}
		return false;
	}

	public void connect() {
		if (!this.hasConnection()) {
			try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException ex) {
				Bukkit.getConsoleSender().sendMessage("§c[MYSQL] Driver do MySQL não encontrado: " + ex.getMessage());
			}

			try {
				this.connection = DriverManager.getConnection(
						"jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database, this.user, this.password);
			} catch (SQLException ex) {
				Bukkit.getConsoleSender()
						.sendMessage("§c[MYSQL] Erro ao conectar com o Banco de Dados: " + ex.getMessage());
			}
		}
	}

	public void disconnect() {
		if (this.hasConnection()) {
			try {
				connection.close();
			} catch (SQLException ex) {
				Bukkit.getConsoleSender()
						.sendMessage("§c[MYSQL] Erro ao desconectar do Banco de Dados: " + ex.getMessage());
			}
		}
	}

	public void createTable(String table, String... fields) {
		String query = "CREATE TABLE IF NOT EXISTS `" + table + "` (";
		for (String field : fields)
			query += query.endsWith(")") ? ", " + field : field;
		query += ");";
		this.update(query);
	}

	public void update(String update) {
		this.connect();
		if (this.hasConnection()) {
			try {
				Statement statement = this.connection.createStatement();
				statement.executeUpdate(update);
				statement.close();
			} catch (SQLException ex) {
				Bukkit.getConsoleSender()
						.sendMessage("§c[MYSQL] Erro ao executar update no Banco de Dados: " + ex.getMessage());
			}
			this.disconnect();
		}
	}

	public ResultSet query(String query) {
		try {
			this.connect();
			ResultSet set = this.connection.createStatement().executeQuery(query);
			this.disconnect();
			if (set != null)
				return set;
		} catch (SQLException ex) {
		}
		return null;
	}

	public boolean exists(String query) {
		try {
			this.connect();
			ResultSet set = this.connection.createStatement().executeQuery(query);
			boolean next = false;
			if (set != null)
				next = set.next();
			this.disconnect();
			return next;
		} catch (SQLException ex) {
		}
		return false;
	}

	public int queryInt(String query, String field) {
		try {
			this.connect();
			ResultSet set = this.connection.createStatement().executeQuery(query);
			if (set != null && set.next()) {
				int i = set.getInt(field);
				this.disconnect();
				return i;
			}
		} catch (SQLException ex) {
		}
		return 0;
	}
}