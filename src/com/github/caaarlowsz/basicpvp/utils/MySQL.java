package com.github.caaarlowsz.basicpvp.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.bukkit.Bukkit;

public class MySQL {

	private String host, port, database, user, password;
	public Connection connection;

	public MySQL(String host, String database, String user, String password) {
		this(host, "3306", database, user, password);
	}

	public MySQL(String host, String port, String database, String user, String password) {
		this.host = host;
		this.port = port;
		this.database = database;
		this.user = user;
		this.password = password;
	}

	public void connect() {
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

	public void disconnect() {
		try {
			if (connection != null && !connection.isClosed())
				connection.close();
		} catch (SQLException ex) {
			Bukkit.getConsoleSender()
					.sendMessage("§c[MYSQL] Erro ao desconectar do Banco de Dados: " + ex.getMessage());
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
		try {
			Statement statement = this.connection.createStatement();
			statement.executeUpdate(update);
			statement.close();
		} catch (SQLException ex) {
			Bukkit.getConsoleSender()
					.sendMessage("§c[MYSQL] Erro ao executar update no Banco de Dados: " + ex.getMessage());
		}
	}

	public ResultSet query(String query) {
		try {
			Statement statement = this.connection.createStatement();
			return statement.executeQuery(query);
		} catch (SQLException ex) {
			Bukkit.getConsoleSender()
					.sendMessage("§c[MYSQL] Erro ao executar query no Banco de Dados: " + ex.getMessage());
		}

		return null;
	}
}