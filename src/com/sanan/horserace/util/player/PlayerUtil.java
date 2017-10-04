package com.sanan.horserace.util.player;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Horse.Variant;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import com.sanan.horserace.Main;
import com.sanan.horserace.util.RaceTrack;
import com.sanan.horserace.util.chat.Message;
import com.sanan.horserace.util.game.Game;

import net.minecraft.server.v1_8_R3.EntityLiving;
import net.minecraft.server.v1_8_R3.GenericAttributes;

public class PlayerUtil {

	private static List<RacePlayer> allRacePlayers = new ArrayList<RacePlayer>();
	private static Game game = Game.getInstance();
	private static RaceTrack raceTrack = RaceTrack.getInstance();
	private static Scoreboard board;

	public static List<RacePlayer> getAllRacePlayers() {
		return allRacePlayers;
	}

	public static void updateScoreboard(Player player) {
		for (RacePlayer rp : PlayerUtil.getAllRacePlayers()) {
			board.getObjective(DisplaySlot.SIDEBAR).getScore(ChatColor.AQUA + "" + ChatColor.BOLD + "").setScore(rp.getDistanceTraveled());
		}
		player.setScoreboard(board);
	}
	
	public static void resetScoreboard() {
		board = null;
	}

	public static void registerPlayerToScoreboard(Player player) {
        Team playerName = board.registerNewTeam("testing");
        playerName.addEntry(ChatColor.AQUA + "" + ChatColor.BOLD + "");
        playerName.setPrefix(ChatColor.AQUA + player.getName());
        board.getObjective(DisplaySlot.SIDEBAR).getScore(ChatColor.AQUA + "" + ChatColor.BOLD + "").setScore(0);
	}
	
	public static void setupScoreboard() {
		board = Bukkit.getScoreboardManager().getNewScoreboard();
		Objective obj = board.registerNewObjective("HorseRace", "dummy");
		obj.setDisplaySlot(DisplaySlot.SIDEBAR);
		obj.setDisplayName("Distance");
	}

	public static void finishPlayerLap(final Player player) {
		for (final RacePlayer rp : PlayerUtil.getAllRacePlayers()) {
			if (rp.getPlayer().getUniqueId().equals(player.getUniqueId())) {
				if (rp.getCurrentLap() >= 3) {
					returnAndUnregisterPlayerFromRace(player);
					return;
				}
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
				Horse horse = (Horse) entityHorse;
				horse.setTamed(true);
				horse.setAdult();
				horse.setVariant(Variant.HORSE);
				horse.getInventory().addItem(new ItemStack(Material.SADDLE));
				horse.setJumpStrength(1);
				((EntityLiving) ((CraftEntity) horse).getHandle())
						.getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(0.4);
				horse.setPassenger(player);
				rp.setHorse(horse);
			}
		}
	}

	public static void returnAndUnregisterPlayerFromRace(final Player player) {
		game.setFinishedPlayers(game.getFinishedPlayers() + 1);

		for (final RacePlayer rp : PlayerUtil.getAllRacePlayers()) {
			if (rp.getPlayer().getUniqueId().equals(player.getUniqueId())) {
				rp.getHorse().remove();
				rp.setHasFinished(true);
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
					public void run() {
						player.teleport(rp.getOriginalLocation());
						for (int i = 0; i < rp.getInventoryContents().length; i++) {
							player.getInventory().setItem(i, rp.getInventoryContents()[i]);
						}
						player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
					}
				}, 5);
			}
		}

		if (game.getFinishedPlayers() == PlayerUtil.getAllRacePlayers().size()) {
			game.stopRace();
		} else if (game.getFinishedPlayers() == 1) {
			game.setCurrentTimer(60);
			for (RacePlayer rp : PlayerUtil.getAllRacePlayers()) {
				rp.getPlayer().sendMessage(Message.FIRST_FINISH.getConfigMessage().replaceAll("%player%", player.getName()));
			}
		}

	}

	public static void unregisterPlayer(RacePlayer rp) {
		allRacePlayers.remove(rp);
	}

	public static void teleportAndRegisterAllPlayersToRace() {
		for (Player player : Bukkit.getServer().getOnlinePlayers()) {
			setupRacePlayer(player);
			player.teleport(raceTrack.getSpawnLocation());
			registerPlayerToScoreboard(player);
			updateScoreboard(player);
		}
	}

	private static void setupRacePlayer(Player player) {
		Inventory inv = player.getInventory();
		ItemStack[] contents = new ItemStack[inv.getSize()];
		for (int i = 0; i < contents.length; i++) {
			contents[i] = inv.getItem(i);
		}
		RacePlayer rp = new RacePlayer(player, contents, player.getLocation(), null);
		registerRacePlayer(rp);
		inv.clear();
	}

	private static void registerRacePlayer(RacePlayer rp) {
		allRacePlayers.add(rp);
	}

}
