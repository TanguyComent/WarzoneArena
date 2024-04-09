package me.kitsoko.warzonearena;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{
	
	File arenas = new File(this.getDataFolder() + "/config.yml");
	public HashMap<ArrayList<Location>, ArrayList<Player>> arenasMap = new HashMap<ArrayList<Location>, ArrayList<Player>>();
	public ArrayList<ArrayList<Location>> arenasLocs = new ArrayList<ArrayList<Location>>();
	
	@Override
	public void onEnable() {
		arenasMap.clear();
		arenasLocs.clear();
		getServer().getPluginManager().registerEvents(new ArenaEvent(this), this);
		getCommand("arena").setExecutor(new ArenaCmd(this));
		File folder = new File(this.getDataFolder() + "");
		if(!folder.exists()) folder.mkdir();
		
		if(!this.arenas.exists()) {
			try {
				this.arenas.createNewFile();
				defaultArenaFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		createArenas();
	}
	
	public void defaultArenaFile() {
		YamlConfiguration conf = YamlConfiguration.loadConfiguration(arenas);
		conf.set(1 + ".loc1", new Location(Bukkit.getWorld("world"), 0, 0, 0));
		conf.set(1 + ".loc2", new Location(Bukkit.getWorld("world"), 0, 0, 0));
		try {
			conf.save(arenas);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void createArenas() {
		YamlConfiguration conf = YamlConfiguration.loadConfiguration(arenas);
		int i = 1;
		while(conf.get(i + ".loc1") != null) {
			Location loc1 = (Location) conf.get(i + ".loc1");
			Location loc2 = (Location) conf.get(i + ".loc2");
			ArrayList<Location> locs = new ArrayList<Location>();
			locs.add(loc1);
			locs.add(loc2);
			ArrayList<Player> players = new ArrayList<Player>();
			arenasMap.put(locs, players);
			arenasLocs.add(locs);
			i++;
		}
	}
}
