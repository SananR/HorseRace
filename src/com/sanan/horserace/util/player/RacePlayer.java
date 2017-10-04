package com.sanan.horserace.util.player;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class RacePlayer {

	private Player player;
	private ItemStack[] inventoryContents;
	private Location originalLocation;
	private Entity horse;
	private int currentLap;
	
	public RacePlayer(Player player, ItemStack[] inventoryContents, Location originalLocation, Entity horse) {
		this.player = player;
		this.inventoryContents = inventoryContents;
		this.originalLocation = originalLocation;
		this.horse = horse;
		this.currentLap = 1;
	}
	
	public Player getPlayer() {
		return player;
	}
	public ItemStack[] getInventoryContents() {
		return inventoryContents;
	}
	public Entity getHorse() {
		return horse;
	}
	public int getCurrentLap() {
		return currentLap;
	}
	public Location getOriginalLocation() {
		return originalLocation;
	}
	
	
	public void setPlayer(Player player) {
		this.player = player;
	}
	public void setInventoryContents(ItemStack[] inventoryContents) {
		this.inventoryContents = inventoryContents;
	}
	public void setHorse(Entity horse) {
		this.horse = horse;
	}
	public void setCurrentLap(int currentLap) {
		this.currentLap = currentLap;
	}
	public void setOriginalLocation(Location originalLocation) {
		this.originalLocation = originalLocation;
	}
	
}
