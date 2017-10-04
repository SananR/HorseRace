package com.sanan.horserace.util.player;

import java.util.ArrayList; 
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.entity.Horse.Variant;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.sanan.horserace.Main;
import com.sanan.horserace.util.RaceTrack;

import net.minecraft.server.v1_8_R3.EntityLiving;
import net.minecraft.server.v1_8_R3.GenericAttributes;

public class PlayerUtil {
	
	private static List<RacePlayer> allRacePlayers = new ArrayList<RacePlayer>();
	private static RaceTrack raceTrack = RaceTrack.getInstance();
	
	public static List<RacePlayer> getAllRacePlayers() {
		return allRacePlayers;
	}
	
	public static void finishPlayerLap(final Player player) {
		for (final RacePlayer rp : PlayerUtil.getAllRacePlayers()) {
			if (rp.getPlayer().getUniqueId().equals(player.getUniqueId())) {
				rp.getHorse().remove();
				player.teleport(raceTrack.getSpawnLocation());
				rp.setCurrentLap(rp.getCurrentLap() + 1);
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
					public void run() {
						spawnPlayerHorse(player);
						rp.getHorse().setPassenger(player);
					}
					
				}, 5);
			}
		}
	}
	
	public static void spawnPlayerHorse(Player player) { 
		for (RacePlayer rp : PlayerUtil.getAllRacePlayers()) {
			if (rp.getPlayer().getUniqueId().equals(player.getUniqueId())) {
				Entity entityHorse = player.getWorld().spawnEntity(player.getLocation(), EntityType.HORSE);
				Horse horse = (Horse)entityHorse;
				horse.setTamed(true);
				horse.setAdult();
				horse.setVariant(Variant.HORSE);
				horse.getInventory().addItem(new ItemStack(Material.SADDLE));
				horse.setJumpStrength(1);
				((EntityLiving)((CraftEntity)horse).getHandle()).getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(0.4);
				horse.setPassenger(player);
				rp.setHorse(horse);
			}
		}
	}
	
	public static void teleportAndRegisterAllPlayersToRace() {
		for (Player player : Bukkit.getServer().getOnlinePlayers()) {
			player.teleport(raceTrack.getSpawnLocation());
			setupRacePlayer(player);
		}
	}
	
	private static void setupRacePlayer(Player player) {
		Inventory inv = player.getInventory();
		ItemStack[] contents = new ItemStack[inv.getSize()];
		for (int i=0; i < contents.length; i++) {
			contents[i] = inv.getItem(i);
		}
		RacePlayer rp = new RacePlayer(player, contents, null);
		registerRacePlayer(rp);
	}
	
	
	private static void registerRacePlayer(RacePlayer rp) {
		allRacePlayers.add(rp);
	}

	private static void unregisterPlayer(RacePlayer rp) {
		allRacePlayers.remove(rp);
	}
	
	
}
