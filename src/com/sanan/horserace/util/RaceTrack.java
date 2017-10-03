package com.sanan.horserace.util;

import org.bukkit.Location;

public class RaceTrack {
	
	private Location spawnLocation;
	
	public RaceTrack(Location spawnLocation) {
		this.spawnLocation = spawnLocation;
	}
	
	
	
	public Location getSpawnLocation() {
		return spawnLocation;
	}
	
	
	public void setSpawnLocation(Location spawnLocation) {
		this.spawnLocation = spawnLocation;
	}

}
