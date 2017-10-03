package com.sanan.horserace.util.player;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class RacePlayer {

	private Player player;
	private ItemStack[] inventoryContents;
	private Entity horse;
	
	public RacePlayer(Player player, ItemStack[] inventoryContents, Entity horse) {
		this.player = player;
		this.inventoryContents = inventoryContents;
		this.horse = horse;
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
	
	public void setPlayer(Player player) {
		this.player = player;
	}
	public void setInventoryContents(ItemStack[] inventoryContents) {
		this.inventoryContents = inventoryContents;
	}
	public void setHorse(Entity horse) {
		this.horse = horse;
	}
}
