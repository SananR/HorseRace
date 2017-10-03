package com.sanan.horserace.util.player;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.sanan.horserace.util.RaceTrack;

public class PlayerUtil {
	
	private static List<RacePlayer> allRacePlayers = new ArrayList<RacePlayer>();
	private static RaceTrack raceTrack = RaceTrack.getInstance();
	
	public static List<RacePlayer> getAllRacePlayers() {
		return allRacePlayers;
	}
	
	public static void teleportAndRegisterAllPlayersToRace() {
		for (Player player : Bukkit.getServer().getOnlinePlayers()) {
			player.teleport(raceTrack.getSpawnLocation());
			saveRacePlayerInformation(player);
		}
	}
	
	private static void saveRacePlayerInformation(Player player) {
		Entity horse = player.getWorld().spawnEntity(player.getLocation(), EntityType.HORSE);
		((EntityLiving)((CraftEntity)horse).getHandle()).getAttributeInstance(GenericAttributes.d).setValue(5);
		Inventory inv = player.getInventory();
		ItemStack[] contents = new ItemStack[inv.getSize()];
		for (int i=0; i < contents.length; i++) {
			contents[i] = inv.getItem(i);
		}
		RacePlayer rp = new RacePlayer(player, contents, horse);
		registerRacePlayer(rp);
	}

	
	private static void registerRacePlayer(RacePlayer rp) {
		allRacePlayers.add(rp);
	}

	private static void unregisterPlayer(RacePlayer rp) {
		allRacePlayers.remove(rp);
	}
	
	
}
