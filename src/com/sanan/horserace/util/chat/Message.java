package com.sanan.horserace.util.chat;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

public enum Message {

	RACE_START("race-start"),
	TELEPORT_TIMER("teleport-timer-message"),
	WAITING_TIMER("waiting-timer-message"),
	NO_PERMISSION ("invalid-permissions"),
	ALREADY_STARTED ("already-started"),
	GAME_START ("game-start"),
	INVALID_TRACK("invalid-track-location"),
	SET_TRACK("set-track-location");

    private static FileConfiguration cfg;
	private String path;
	
	Message(String path) {
        this.path = path;
    }

    public static void setFile(FileConfiguration config) {
    	cfg = config;
    }

	public String getConfigMessage() {
		String value = ChatColor.translateAlternateColorCodes('&', cfg.getString(this.path));
		return value;
	}
}
