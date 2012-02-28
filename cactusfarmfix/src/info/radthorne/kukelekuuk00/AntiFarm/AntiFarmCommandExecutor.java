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
					if (AntiFarm.enabled == true)
				{
					AntiFarm.enabled = false;
					sender.sendMessage(ChatColor.RED + "AntiFarm Cactus has been disabled.");
				}
					
				else if (AntiFarm.enabled == false)
				{
						AntiFarm.enabled = true;
					sender.sendMessage(ChatColor.RED + "AntiFarm Cactus has been enabled.");
				}
				return true;
			}
			
		}
		
		return false;
	}
	
	protected boolean permit(Player player,String permission){
		boolean permit = false;
			permit = player.hasPermission(permission);
		return permit;
		}
	
}	