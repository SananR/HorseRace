package com.sanan.horserace;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.sanan.horserace.commands.SetPortalCommand;
import com.sanan.horserace.commands.SetTrackCommand;
import com.sanan.horserace.commands.StartRaceCommand;
import com.sanan.horserace.listeners.PortalEvents;
import com.sanan.horserace.listeners.RaceEvents;
import com.sanan.horserace.util.RaceTrack;
import com.sanan.horserace.util.chat.Message;
import com.sanan.horserace.util.file.FileManager;

public class Main extends JavaPlugin {
	
	public static Plugin plugin;
	
	private RaceTrack rt = RaceTrack.getInstance();
	private FileManager fm = FileManager.getInstance();
	
	public void onEnable() {
		plugin = this;
		
		saveDefaultConfig();
		Message.setFile(getConfig());
		fm.setup(this);
		
		setupCommands();
		setupListeners();
		
		rt.loadRaceTrackFromFile();
	}
	
	public void onDisable() {
		rt.saveRaceTrackToFile();
	}
	
	private void setupListeners() {
		getServer().getPluginManager().registerEvents(new RaceEvents(), this);
		getServer().getPluginManager().registerEvents(new PortalEvents(), this);
	}
	
	private void setupCommands() {
		getCommand("startrace").setExecutor(new StartRaceCommand());
		getCommand("settrack").setExecutor(new SetTrackCommand());
		getCommand("setportal").setExecutor(new SetPortalCommand());
	}
	

}
