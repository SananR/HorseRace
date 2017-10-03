package com.sanan.horserace.util;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import com.sanan.horserace.util.file.FileManager;

public class RaceTrack {
	
	public static RaceTrack raceTrack;
	
	private FileManager fm = FileManager.getInstance();
	
	private Location spawnLocation = null;
	
	public static RaceTrack getInstance() {
		if (raceTrack == null) { raceTrack = new RaceTrack(); }
		return raceTrack;
	}
	
	public void saveRaceTrackToFile() {
		if (spawnLocation==null) return;
		fm.getGameData().set("track.location.world", spawnLocation.getWorld().getName());
		fm.getGameData().set("track.location.x", spawnLocation.getX());
		fm.getGameData().set("track.location.y", spawnLocation.getY());
		fm.getGameData().set("track.location.z", spawnLocation.getZ());
		fm.getGameData().set("track.location.pitch", spawnLocation.getPitch());
		fm.getGameData().set("track.location.yaw", spawnLocation.getYaw());
		
		fm.saveGameData();
	}
	
	public void loadRaceTrackFromFile() {
		if (fm.getGameData().getConfigurationSection("track")==null) return;
		
		World world = Bukkit.getWorld(fm.getGameData().getString("track.location.world"));
		double x = fm.getGameData().getDouble("track.location.x");
		double y = fm.getGameData().getDouble("track.location.y");
		double z = fm.getGameData().getDouble("track.location.z");
		float pitch = (float) fm.getGameData().getDouble("track.location.pitch");
		float yaw = (float) fm.getGameData().getDouble("track.location.yaw");
		
		Location loc = new Location(world, x, y, z, yaw, pitch);
		
		spawnLocation = loc;
	}
	
	public Location getSpawnLocation() {
		return spawnLocation;
	}
	
	public void setSpawnLocation(Location spawnLocation) {
		this.spawnLocation = spawnLocation;
	}

}
