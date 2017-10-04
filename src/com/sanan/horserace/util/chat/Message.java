package com.sanan.horserace.util.chat;

import java.util.ArrayList;
import java.util.List;

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
	SET_TRACK("set-track-location"),
	SET_PORTAL("set-portal-location"),
	FIRST_FINISH("first-player-finish"),
	RACE_LAP("race-lap-message"),
	TIME_LIMIT("time-limit"),
	GAME_OVER("game-over");

    private static FileConfiguration cfg;
	private String path;
	
	Message(String path) {
        this.path = path;
    }

    public static void setFile(FileConfiguration config) {
    	cfg = config;
    }

    public List<String> getConfigMessageList() {
    	List<String> messages = new ArrayList<String>();
    	for (String message : cfg.getStringList(this.path)) {
    		messages.add(ChatColor.translateAlternateColorCodes('&', message));
    	}
    	return messages;
    }
    
	public String getConfigMessage() {
		String value = ChatColor.translateAlternateColorCodes('&', cfg.getString(this.path));
		return value;
	}
}
