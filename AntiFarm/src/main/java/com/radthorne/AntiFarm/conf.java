package com.radthorne.AntiFarm;/*
 * Created by Luuk Jacobs at 17-1-13 16:04
 */

import org.bukkit.plugin.Plugin;

class conf {
  public static boolean cactusEnabled = true;
  public static boolean cactusPistonEnabled = true;
  public static boolean reedEnabled = true;
  public static boolean cropsEnabled = true;
  public static boolean pumpkinEnabled = true;
  public static boolean melonsEnabled = true;
  public static boolean waterCropsEnabled = true;
  public static boolean waterMushroomsEnabled = true;

  private final Plugin plugin;


  public conf(Plugin plugin) {
    this.plugin = plugin;
  }

  public void load() {
    this.cactusEnabled = plugin.getConfig().getBoolean("cactus");
    this.cactusPistonEnabled = plugin.getConfig().getBoolean("cactuspiston");
    this.reedEnabled = plugin.getConfig().getBoolean("reed");
    this.pumpkinEnabled = plugin.getConfig().getBoolean("pumpkin");
    this.melonsEnabled = plugin.getConfig().getBoolean("melon");
    this.cropsEnabled = plugin.getConfig().getBoolean("crops");
    this.waterCropsEnabled = plugin.getConfig().getBoolean("watercrops");
    this.waterMushroomsEnabled = plugin.getConfig().getBoolean("watermushrooms");
  }

  public void reload() {
    plugin.reloadConfig();
    load();
  }

  public void save() {
  }

}
