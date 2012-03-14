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
import org.bukkit.permissions.Permissible;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;


public class AntiFarm extends JavaPlugin 
	{
	public final HashMap<Player, ArrayList<Block>> cfUsers = new HashMap<Player, ArrayList<Block>>();
	private final AntiFarmBlockListener blockListener = new AntiFarmBlockListener(this);
	Logger log = Logger.getLogger("Minecraft");
	private String permission;
	public static boolean cactusenabled = true;
	public static boolean reedenabled = true;
	private AntiFarmCommandExecutor afcactusExecutor;
	private AntiFarmCommandExecutor afreedExecutor;
	@Override
	public void onEnable()
	{
	log.info("AntiFarm has been enabled.");
	PluginManager pm = this.getServer().getPluginManager();
	pm.registerEvents(this.blockListener, this); 
	afcactusExecutor = new AntiFarmCommandExecutor(this);
	afreedExecutor = new AntiFarmCommandExecutor(this);
	getCommand("afcactus").setExecutor(afcactusExecutor);
	getCommand("afreed").setExecutor(afreedExecutor);
	}
	
	public void onDisable()
	{
		log.info("AntiFarm has been disabled.");
	}
}
