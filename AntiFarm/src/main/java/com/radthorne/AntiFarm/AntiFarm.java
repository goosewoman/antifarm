package com.radthorne.AntiFarm;

import com.radthorne.AntiFarm.metrics.Metrics;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;


public class AntiFarm extends JavaPlugin {
  private final conf conf = new conf(this);
  private final AntiFarmBlockListener blockListener = new AntiFarmBlockListener(this);

  @Override
  public void onEnable() {
    PluginManager pm = this.getServer().getPluginManager();
    pm.registerEvents(this.blockListener, this);
    this.saveDefaultConfig();
    conf.load();

    try {
      Metrics metrics = new Metrics(this);
      metrics.start();
    } catch (IOException e) {
    }

  }

  @Override
  public void onDisable() {
    conf.save();
  }

  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    if (cmd.getName().equalsIgnoreCase("afreload")) {
      conf.reload();
      sender.sendMessage("§2[AntiFarm]§r Configuration has been reloaded!");
      return true;
    }

    return false;
  }

}
