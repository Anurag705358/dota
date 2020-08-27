package com.example.demo.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public class PlayerDetails {
	@JsonProperty("hero_id")
	private String heroId;
	private int lastPlayed;
	private int games;
	private int win;
	private int withGames;
	private int withWin;
	private int againstGames;
	private int againstWin;
    
	public String getHeroId() {
		return heroId;
	}
	public void setHeroId(String heroId) {
		this.heroId = heroId;
	}
	public int getLastPlayed() {
		return lastPlayed;
	}
	public void setLastPlayed(int lastPlayed) {
		this.lastPlayed = lastPlayed;
	}
	public int getGames() {
		return games;
	}
	public void setGames(int games) {
		this.games = games;
	}
	public int getWin() {
		return win;
	}
	public void setWin(int win) {
		this.win = win;
	}
	public int getWithGames() {
		return withGames;
	}
	public void setWithGames(int withGames) {
		this.withGames = withGames;
	}
	public int getWithWin() {
		return withWin;
	}
	public void setWithWin(int withWin) {
		this.withWin = withWin;
	}
	public int getAgainstGames() {
		return againstGames;
	}
	public void setAgainstGames(int againstGames) {
		this.againstGames = againstGames;
	}
	public int getAgainstWin() {
		return againstWin;
	}
	public void setAgainstWin(int againstWin) {
		this.againstWin = againstWin;
	}
   
}
