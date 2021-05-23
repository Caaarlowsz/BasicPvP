package com.github.caaarlowsz.basicpvp.player;

import java.text.DecimalFormat;

public class Status {

	private final String nickname;
	private int moedas, xp, killstreak, abates, mortes;

	public Status(String nickname) {
		this(nickname, 0, 0, 0, 0, 0);
	}

	public Status(String nickname, int moedas, int xp, int killstreak, int abates, int mortes) {
		this.nickname = nickname;
		this.moedas = moedas;
		this.xp = xp;
		this.killstreak = killstreak;
		this.abates = abates;
		this.mortes = mortes;
	}

	public String getNickname() {
		return this.nickname;
	}

	public Rank getRank() {
		return Rank.getRankByXP(this.getXP());
	}

	public String getFormattedMoedas() {
		return new DecimalFormat().format(this.getMoedas()).replace(",", ".");
	}

	public int getMoedas() {
		return this.moedas;
	}

	private void setMoedas(int moedas) {
		this.moedas = moedas;
		StatusData.setMoedas(this.getNickname(), moedas);
	}

	public void addMoedas(int moedas) {
		this.setMoedas(this.getMoedas() + moedas);
	}

	public void drawMoedas(int moedas) {
		int result = this.getMoedas() - moedas;
		this.setMoedas(result < 0 ? 0 : result);
	}

	public String getFormattedXP() {
		return new DecimalFormat().format(this.getXP()).replace(",", ".");
	}

	public int getXP() {
		return this.xp;
	}

	private void setXP(int xp) {
		this.xp = xp;
		StatusData.setXP(this.getNickname(), xp);
	}

	public void addXP(int xp) {
		this.setXP(this.getXP() + xp);
	}

	public void drawXP(int xp) {
		int result = this.getXP() - xp;
		this.setXP(result < 0 ? 0 : result);
	}

	public String getFormattedKillStreak() {
		return new DecimalFormat().format(this.getKillStreak()).replace(",", ".");
	}

	public int getKillStreak() {
		return this.killstreak;
	}

	private void setKillStreak(int killstreak) {
		this.killstreak = killstreak;
		StatusData.setKillStreak(this.getNickname(), killstreak);
	}

	public void addKillStreak() {
		this.setKillStreak(this.getKillStreak() + 1);
	}

	public void resetKillStreak() {
		this.setKillStreak(0);
	}

	public String getFormattedAbates() {
		return new DecimalFormat().format(this.getAbates()).replace(",", ".");
	}

	public int getAbates() {
		return this.abates;
	}

	private void setAbates(int abates) {
		this.abates = abates;
		StatusData.setAbates(this.getNickname(), abates);
	}

	public void addAbate() {
		this.setAbates(this.getAbates() + 1);
	}

	public String getFormattedMortes() {
		return new DecimalFormat().format(this.getMortes()).replace(",", ".");
	}

	public int getMortes() {
		return this.mortes;
	}

	private void setMortes(int mortes) {
		this.mortes = mortes;
		StatusData.setMortes(this.getNickname(), mortes);
	}

	public void addMorte() {
		this.setMortes(this.getMortes() + 1);
	}
}