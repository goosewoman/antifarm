package info.radthorne.kukelekuuk00.AntiFarm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;


import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;


public class AntiFarm extends JavaPlugin {
	public final HashMap<Player, ArrayList<Block>> cfUsers = new HashMap<Player, ArrayList<Block>>();
	private final AntiFarmBlockListener blockListener = new AntiFarmBlockListener(this);
	Logger log = Logger.getLogger("Minecraft");
	public static boolean enabled = false;

	public void onEnable()
	{
	log.info("AntiFarm has been enabled.");
	PluginManager pm = this.getServer().getPluginManager();
	pm.registerEvents(this.blockListener, this); 
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		if(cmd.getName().equalsIgnoreCase("afcactus"))
		{ 
			if (this.enabled == true)
				{
				this.enabled = false;
				sender.sendMessage(ChatColor.DARK_GREEN + "AntiFarm Cactus has been disabled.");
				}
			else if (this.enabled == false)
				{	
				this.enabled = true;
				sender.sendMessage(ChatColor.DARK_GREEN + "AntiFarm Cactus has been enabled");
				}
			return true;
		}
		return false;
			
	}	

	
	 
	public void onDisable()
	{
		log.info("AntiFarm has been disabled.");
	}
}
