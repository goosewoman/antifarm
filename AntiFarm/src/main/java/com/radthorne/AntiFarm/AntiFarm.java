package com.radthorne.AntiFarm;

import com.radthorne.AntiFarm.metrics.Metrics;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class AntiFarm extends JavaPlugin {
  private final conf config = new conf(this);
  private final AntiFarmBlockListener blockListener = new AntiFarmBlockListener(this);

  @Override
  public void onEnable() {
    PluginManager pm = this.getServer().getPluginManager();
    pm.registerEvents(this.blockListener, this);
    this.saveDefaultConfig();
    processConfigFile();
    config.load();


    try {
      Metrics metrics = new Metrics(this);
      final Metrics.Graph confGraph = metrics.createGraph("Functions used");
      if (conf.cactusEnabled) {
        confGraph.addPlotter(new booleanPlotter("Nearby Blocks + Cactus"));
      }
      if (conf.cactusPistonEnabled) {
        confGraph.addPlotter(new booleanPlotter("Pistons + Cactus"));
      }
      if (conf.melonsEnabled) {
        confGraph.addPlotter(new booleanPlotter("Pistons + Melons"));
      }
      if (conf.pumpkinEnabled) {
        confGraph.addPlotter(new booleanPlotter("Pistons + Pumpkin"));
      }
      if (conf.reedEnabled) {
        confGraph.addPlotter(new booleanPlotter("Pistons + Sugar Cane"));
      }
      if (conf.cropsEnabled) {
        confGraph.addPlotter(new booleanPlotter("Pistons + Crops"));
      }
      if (conf.waterCropsEnabled) {
        confGraph.addPlotter(new booleanPlotter("Water + Crops"));
      }
      if (conf.waterMushroomsEnabled) {
        confGraph.addPlotter(new booleanPlotter("Water + Mushrooms"));
      }
      metrics.start();
    } catch (IOException e) {
    }

  }

  @Override
  public void onDisable() {
    config.save();
  }

  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    if (cmd.getName().equalsIgnoreCase("afreload")) {
      config.reload();
      sender.sendMessage("§2[AntiFarm]§r Configuration has been reloaded!");
      return true;
    }

    return false;
  }

  public void processConfigFile() {

    final Map<String, Object> defParams = new HashMap<String, Object>();
    FileConfiguration config = this.getConfig();
    config.options().copyDefaults(true);

    // This is the default configuration
    defParams.put("cactus", true);
    defParams.put("cactuspiston", true);
    defParams.put("reed", true);
    defParams.put("melon", true);
    defParams.put("pumpkin", true);
    defParams.put("crops", true);
    defParams.put("watercrops", true);
    defParams.put("watermushrooms", true);


    // If config does not include a default parameter, add it
    for (final Map.Entry<String, Object> e : defParams.entrySet())
      if (!config.contains(e.getKey()))
        config.set(e.getKey(), e.getValue());

    // Save default values to config.yml in datadirectory
    this.saveConfig();
  }

  private class booleanPlotter extends Metrics.Plotter {
    public booleanPlotter(final String name) {
      super(name);
    }

    @Override
    public int getValue() {
      return 1;
    }
  }
}

