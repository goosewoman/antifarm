package info.radthorne.kukelekuuk00.AntiFarm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;


import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;


public class AntiFarm extends JavaPlugin {
	public final HashMap<Player, ArrayList<Block>> cfUsers = new HashMap<Player, ArrayList<Block>>();
	private final AntiFarmBlockListener blockListener = new AntiFarmBlockListener(this);
	Logger log = Logger.getLogger("Minecraft");
	
	public void onEnable()
	{
	log.info("AntiFarm fix has been enabled.");
	PluginManager pm = this.getServer().getPluginManager();
	pm.registerEvents(this.blockListener, this); 
	}
		 
	
	 
	public void onDisable()
	{
		log.info("AntiFarm fix has been disabled.");
	}
}
