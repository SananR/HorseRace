package com.sanan.horserace.util;

import org.bukkit.configuration.file.FileConfiguration;

import com.sanan.horserace.Main;

public class ConfigUtil {

	private static FileConfiguration cfg = Main.plugin.getConfig();
	
	/*
	 * Messages
	 */
	public static String getGameStartMessage() {
		return cfg.getString("game-start");
	}
	public static String getAlreadyStartedMessage() {
		return cfg.getString("already-started");
	}
	public static String getInvalidPermissionsMessage() {
		return cfg.getString("invalid-permissions");
	}
	
}
