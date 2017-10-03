package com.sanan.horserace.util.file;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public class FileManager {

	static FileManager instance = new FileManager();
	Plugin p;

	FileConfiguration GameData;
	File GameDataFile;

	public static FileManager getInstance() {
		return instance;
	}

	public void setup(Plugin p) {
		if (!p.getDataFolder().exists()) {
			p.getDataFolder().mkdir();
		}

		GameDataFile = new File(p.getDataFolder(), "GameData.yml");

		if (!GameDataFile.exists()) {
			try {
				this.GameDataFile.createNewFile();
			} catch (IOException e) {
				Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not create GameData.yml!");
			}
		}

		GameData = YamlConfiguration.loadConfiguration(this.GameDataFile);

	}

	public FileConfiguration getGameData() {
		return GameData;
	}

	public void saveGameData() {
		try {
			GameData.save(GameDataFile);
		} catch (IOException e) {
			Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not save GameData.yml!");
		}
	}

}
