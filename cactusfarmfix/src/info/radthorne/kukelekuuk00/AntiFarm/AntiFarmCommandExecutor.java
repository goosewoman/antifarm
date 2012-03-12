package info.radthorne.kukelekuuk00.AntiFarm;

import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permissible;

public class AntiFarmCommandExecutor implements CommandExecutor
{
	Logger log = Logger.getLogger("Minecraft"); 
	private AntiFarm plugin;
	private Permissible p;
 
	public AntiFarmCommandExecutor(AntiFarm plugin) {
		this.plugin = plugin;
	}
 
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
	{
		Permissible player = p;
		if ((sender instanceof Player) || !(sender instanceof Player))
		{
			if(cmd.getName().equalsIgnoreCase("afcactus"))
			{ 
					if (AntiFarm.cactusenabled == true)
				{
					AntiFarm.cactusenabled = false;
					sender.sendMessage(ChatColor.RED + "AntiFarm Cactus has been disabled.");
				}
					
				else if (AntiFarm.cactusenabled == false)
				{
						AntiFarm.cactusenabled = true;
					sender.sendMessage(ChatColor.RED + "AntiFarm Cactus has been enabled.");
				}
				return true;
			}
			
		}
		if ((sender instanceof Player) || !(sender instanceof Player))
		{
			if(cmd.getName().equalsIgnoreCase("afreed"))
			{ 
					if (AntiFarm.reedenabled == true)
				{
					AntiFarm.reedenabled = false;
					sender.sendMessage(ChatColor.RED + "AntiFarm Reed has been disabled.");
				}
					
				else if (AntiFarm.reedenabled == false)
				{
						AntiFarm.reedenabled = true;
					sender.sendMessage(ChatColor.RED + "AntiFarm Reed has been enabled.");
				}
				return true;
			}
		
		}
		return false;
	
	}
	
}	