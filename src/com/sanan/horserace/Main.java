package com.sanan.horserace;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.sanan.horserace.commands.StartRaceCommand;
import com.sanan.horserace.util.chat.Message;

public class Main extends JavaPlugin {
	
	public static Plugin plugin;
	
	public void onEnable() {
		plugin = this;
		saveDefaultConfig();
		Message.setFile(getConfig());
		setupCommands();
	}
	
	private void setupCommands() {
		getCommand("startrace").setExecutor(new StartRaceCommand());
	}
	

}
