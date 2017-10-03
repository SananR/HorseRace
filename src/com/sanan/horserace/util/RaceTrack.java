package com.sanan.horserace.util;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import com.sanan.horserace.util.file.FileManager;

public class RaceTrack {
	
	public static RaceTrack raceTrack;
	
	private FileManager fm = FileManager.getInstance();
	
	private Location spawnLocation = null;
	private Location portalLocation = null;
	
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

		fm.getGameData().set("track.portal.world", portalLocation.getWorld().getName());
		fm.getGameData().set("track.portal.x", portalLocation.getX());
		fm.getGameData().set("track.portal.y", portalLocation.getY());
		fm.getGameData().set("track.portal.z", portalLocation.getZ());
		
		fm.saveGameData();
	}
	
	public void loadRaceTrackFromFile() {
		if (fm.getGameData().getConfigurationSection("track")==null) return;
		
		World spawnWorld = Bukkit.getWorld(fm.getGameData().getString("track.location.world"));
		double spawnX = fm.getGameData().getDouble("track.location.x");
		double spawnY = fm.getGameData().getDouble("track.location.y");
		double spawnZ = fm.getGameData().getDouble("track.location.z");
		float pitch = (float) fm.getGameData().getDouble("track.location.pitch");
		float yaw = (float) fm.getGameData().getDouble("track.location.yaw");
		
		World portalWorld = Bukkit.getWorld(fm.getGameData().getString("track.portal.world"));
		double portalX = fm.getGameData().getDouble("track.portal.x");
		double portalY = fm.getGameData().getDouble("track.portal.y");
		double portalZ = fm.getGameData().getDouble("track.portal.z");
		
		Location spawnLoc = new Location(spawnWorld, spawnX, spawnY, spawnZ, yaw, pitch);
		Location portalLoc = new Location(portalWorld, portalX, portalY, portalZ);
		
		spawnLocation = spawnLoc;
		portalLocation = portalLoc;
	}
	
	public Location getSpawnLocation() {
		return spawnLocation;
	}
	public Location getPortalLocation() {
		return portalLocation;
	}
	
	
	public void setPortalLocation(Location portalLocation) {
		this.portalLocation = portalLocation;
	}
	
	public void setSpawnLocation(Location spawnLocation) {
		this.spawnLocation = spawnLocation;
	}


}

