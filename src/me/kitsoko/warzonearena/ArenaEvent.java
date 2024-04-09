package me.kitsoko.warzonearena;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class ArenaEvent implements Listener {
	
	Main main;
	public ArenaEvent(Main main) {
		this.main = main;
	}
	
	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		for(ArrayList<Location> locs : main.arenasLocs) {
			ArrayList<Player> arena = main.arenasMap.get(locs);
			Location loc1 = locs.get(0);
			Location loc2 = locs.get(1);
			int minX = (loc1.getX() < loc2.getX()) ? (int) loc1.getX() + 1 : (int) loc2.getX() + 1;
			int maxX = (loc1.getX() > loc2.getX()) ? (int) loc1.getX() : (int) loc2.getX();
			
			int minY = (loc1.getY() < loc2.getY()) ? (int) loc1.getY() : (int) loc2.getY();
			int maxY = (loc1.getY() > loc2.getY()) ? (int) loc1.getY() : (int) loc2.getY();
			
			int minZ = (loc1.getZ() < loc2.getZ()) ? (int) loc1.getZ() + 1 : (int) loc2.getZ() + 1;
			int maxZ = (loc1.getZ() > loc2.getZ()) ? (int) loc1.getZ() : (int) loc2.getZ();
			
			Player p = e.getPlayer();
			if(!(p.getLocation().getX() >= minX && p.getLocation().getX() <= maxX)) {
				if(arena.contains(p)) {
					p.sendMessage("§4Vous êtes sortie de l'arene !");
					if(arena.size() == 2) {
						final int minXCopy = minX;
						final int minZCopy = minZ;
						new BukkitRunnable() {
							
							@Override
							public void run() {
								for(int integer = minXCopy; integer < maxX; integer++) {
									for(int i = minZCopy; i < maxZ; i++) {
										Location loc = new Location(p.getWorld(), integer, maxY, i);
										loc.getBlock().setType(Material.AIR);
									}
								}
							}
							
						}.runTaskLater(main, 20*60);
					}
					arena.remove(p);
				}
				continue;
			}
			if(!(p.getLocation().getZ() >= minZ && p.getLocation().getZ() <= maxZ)) {
				if(arena.contains(p)) {
					p.sendMessage("§4Vous êtes sortie de l'arene !");
					if(arena.size() == 2) {
						final int minXCopy = minX;
						final int minZCopy = minZ;
						new BukkitRunnable() {
							
							@Override
							public void run() {
								for(int integer = minXCopy; integer < maxX; integer++) {
									for(int i = minZCopy; i < maxZ; i++) {
										Location loc = new Location(p.getWorld(), integer, maxY, i);
										loc.getBlock().setType(Material.AIR);
									}
								}
							}
							
						}.runTaskLater(main, 20*60);
					}
					arena.remove(p);
				}
				continue;
			}
			if(!(p.getLocation().getY() >= minY && p.getLocation().getY() <= maxY)) {
				if(arena.contains(p)) {
					p.sendMessage("§4Vous êtes sortie de l'arene !");
					if(arena.size() == 2) {
						final int minXCopy = minX;
						final int minZCopy = minZ;
						new BukkitRunnable() {
							
							@Override
							public void run() {
								for(int integer = minXCopy; integer < maxX; integer++) {
									for(int i = minZCopy; i < maxZ; i++) {
										Location loc = new Location(p.getWorld(), integer, maxY, i);
										loc.getBlock().setType(Material.AIR);
									}
								}
							}
							
						}.runTaskLater(main, 20*60);
					}
					arena.remove(p);
				}
				continue;
			}
			if(arena.size() == 2 && !arena.contains(p)) {
				p.sendMessage("§2L'arène est pleine !");
				p.teleport(new Location(p.getWorld(), p.getLocation().getX(), p.getLocation().getY() + 5, p.getLocation().getZ()));
				return;
			}
			if(arena.size() == 0 && !arena.contains(p)) {
				arena.add(p);
				p.sendMessage("§2Vous êtes entrée dans l'arène !");
				return;
			}
			if(arena.size() == 1 && !arena.contains(p)) {
				arena.add(p);
				p.sendMessage("§2Vous êtes entrée dans l'arène !");
				final int minXCopy = minX;
				final int minZCopy = minZ;
				new BukkitRunnable() {
					
					@Override
					public void run() {
						for(int integer = minXCopy; integer < maxX; integer++) {
							for(int i = minZCopy; i < maxZ; i++) {
								Location loc = new Location(p.getWorld(), integer, maxY, i);
								loc.getBlock().setType(Material.GLASS);
							}
						}
					}
					
				}.runTaskLater(main, 20*1);
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onDead(PlayerDeathEvent e) {
		for(ArrayList<Location> locs : main.arenasLocs) {
			ArrayList<Player> arena = main.arenasMap.get(locs);
			if(!arena.contains(e.getEntity())) return;
			Location loc1 = locs.get(0);
			Location loc2 = locs.get(1);
			int minX = (loc1.getX() < loc2.getX()) ? (int) loc1.getX() + 1 : (int) loc2.getX() + 1;
			int maxX = (loc1.getX() > loc2.getX()) ? (int) loc1.getX() : (int) loc2.getX();
			
			int maxY = (loc1.getY() > loc2.getY()) ? (int) loc1.getY() : (int) loc2.getY();
			
			int minZ = (loc1.getZ() < loc2.getZ()) ? (int) loc1.getZ() + 1 : (int) loc2.getZ() + 1;
			int maxZ = (loc1.getZ() > loc2.getZ()) ? (int) loc1.getZ() : (int) loc2.getZ();
			Player p = e.getEntity();
			p.sendMessage("§4Vous avez perdu votre duel §o§6#noobs");
			arena.remove(p);
			Player winner = arena.get(0);
			winner.sendTitle("§2Tu as gagné ton duel §6GG", "§6Tu as 1 minute pour recupérer le stuff et partir !");
			winner.sendMessage("§2Tu as gagné ton duel ! §6GG");
			winner.sendMessage("§2Tu as maintenant §61 minute §2pour récuperer le stuff et partir !");
			final int minXCopy = minX;
			final int minZCopy = minZ;
			new BukkitRunnable() {
				
				@Override
				public void run() {
					for(int integer = minXCopy; integer < maxX; integer++) {
						for(int i = minZCopy; i < maxZ; i++) {
							Location loc = new Location(p.getWorld(), integer, maxY, i);
							loc.getBlock().setType(Material.AIR);
						}
					}
				}
				
			}.runTaskLater(main, 20*60);
		}
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onDisconnect(PlayerQuitEvent e) {
		for(ArrayList<Location> locs : main.arenasLocs) {
			ArrayList<Player> arena = main.arenasMap.get(locs);
			if(!arena.contains(e.getPlayer())) return;
			Location loc1 = locs.get(0);
			Location loc2 = locs.get(1);
			int minX = (loc1.getX() < loc2.getX()) ? (int) loc1.getX() + 1 : (int) loc2.getX() + 1;
			int maxX = (loc1.getX() > loc2.getX()) ? (int) loc1.getX() : (int) loc2.getX();
			
			int maxY = (loc1.getY() > loc2.getY()) ? (int) loc1.getY() : (int) loc2.getY();
			
			int minZ = (loc1.getZ() < loc2.getZ()) ? (int) loc1.getZ() + 1 : (int) loc2.getZ() + 1;
			int maxZ = (loc1.getZ() > loc2.getZ()) ? (int) loc1.getZ() : (int) loc2.getZ();
			Player p = e.getPlayer();
			arena.remove(p);
			Player winner = arena.get(0);
			winner.sendTitle("§2Tu as gagné ton duel §6GG", "§6Tu as 1 minute pour recupérer le stuff et partir !");
			winner.sendMessage("§2Tu as gagné ton duel ! §6GG");
			winner.sendMessage("§2Tu as maintenant §61 minute §2pour récuperer le stuff et partir !");
			final int minXCopy = minX;
			final int minZCopy = minZ;
			new BukkitRunnable() {
				
				@Override
				public void run() {
					for(int integer = minXCopy; integer < maxX; integer++) {
						for(int i = minZCopy; i < maxZ; i++) {
							Location loc = new Location(p.getWorld(), integer, maxY, i);
							loc.getBlock().setType(Material.AIR);
						}
					}
				}
				
			}.runTaskLater(main, 20*60);
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void teleport(PlayerTeleportEvent e) {
		for(ArrayList<Location> locs : main.arenasLocs) {
			ArrayList<Player> arena = main.arenasMap.get(locs);
			if(!arena.contains(e.getPlayer())) return;
			Location loc1 = locs.get(0);
			Location loc2 = locs.get(1);
			int minX = (loc1.getX() < loc2.getX()) ? (int) loc1.getX() + 1 : (int) loc2.getX() + 1;
			int maxX = (loc1.getX() > loc2.getX()) ? (int) loc1.getX() : (int) loc2.getX();
			
			int maxY = (loc1.getY() > loc2.getY()) ? (int) loc1.getY() : (int) loc2.getY();
			
			int minZ = (loc1.getZ() < loc2.getZ()) ? (int) loc1.getZ() + 1 : (int) loc2.getZ() + 1;
			int maxZ = (loc1.getZ() > loc2.getZ()) ? (int) loc1.getZ() : (int) loc2.getZ();
			if(arena.size() == 2) {
				Player p = e.getPlayer();
				arena.remove(p);
				Player winner = arena.get(0);
				winner.sendTitle("§2Tu as gagné ton duel §6GG", "§6Tu as 1 minute pour recupérer le stuff et partir !");
				winner.sendMessage("§2Tu as gagné ton duel ! §6GG");
				winner.sendMessage("§2Tu as maintenant §61 minute §2pour récuperer le stuff et partir !");
				final int minXCopy = minX;
				final int minZCopy = minZ;
				new BukkitRunnable() {
					
					@Override
					public void run() {
						for(int integer = minXCopy; integer < maxX; integer++) {
							for(int i = minZCopy; i < maxZ; i++) {
								Location loc = new Location(p.getWorld(), integer, maxY, i);
								loc.getBlock().setType(Material.AIR);
							}
						}
					}
					
				}.runTaskLater(main, 20*60);
			}else {
				arena.clear();
			}
		}
	}
}
