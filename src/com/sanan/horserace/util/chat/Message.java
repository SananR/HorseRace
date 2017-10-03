package com.sanan.horserace.util.chat;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

import com.sanan.horserace.util.ConfigUtil;

public enum Message {

	NO_PERMISSION ("invalid-permissions", ConfigUtil.getInvalidPermissionsMessage()),
	ALREADY_STARTED ("game-started", ConfigUtil.getAlreadyStartedMessage()),
	GAME_START ("game-start", ConfigUtil.getGameStartMessage());

    private static FileConfiguration cfg;
	private String key, value;
	
	Message(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public static void setFile(FileConfiguration config) {
    	cfg = config;
    }
	
	public String getConfigMessageWithArgs(String[] replaceKey, String[] replaceValue) {
		String value = getConfigMessage();
		
		if (replaceKey == null || replaceValue == null) return value;
		if (replaceKey.length == 0 || replaceValue.length == 0) return value;
		
		for (int i=0; i < replaceKey.length; i++) {
			value = value.replaceAll("%" + replaceKey + "%", replaceValue[i]);
		}
		return value;
	}

	public String getConfigMessage() {
		String value = ChatColor.translateAlternateColorCodes('&', cfg.getString(this.key, this.value));
		return value;
	}
}
