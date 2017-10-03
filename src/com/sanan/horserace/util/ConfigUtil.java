package com.sanan.horserace.util;

import org.bukkit.configuration.file.FileConfiguration;

import com.sanan.horserace.Main;

public class ConfigUtil {

	private static FileConfiguration cfg = Main.plugin.getConfig();
	
	/*
	 * Messages
	 */
	public static String getInvalidPermissionsMessage() {
		return cfg.getString("messages.invalid-permissions");
	}
	
}