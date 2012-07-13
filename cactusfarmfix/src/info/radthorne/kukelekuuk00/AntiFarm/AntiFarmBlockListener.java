package info.radthorne.kukelekuuk00.AntiFarm;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.Listener;
import org.bukkit.event.EventPriority;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.event.block.BlockPistonRetractEvent;
import org.bukkit.event.Cancellable;
import org.bukkit.Location;

public class AntiFarmBlockListener implements Listener 
{
	Logger log = Logger.getLogger("Minecraft");
	private boolean cancelled;
	private BlockFace direction;
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void CactusPhysics(BlockPhysicsEvent evt)
		{
		Block block = evt.getBlock();
		if (AntiFarm.cactusenabled)
			if(block.getType() == Material.CACTUS)
			{
				if(isPistonExtension(block.getRelative(BlockFace.NORTH, 1)) || isPistonExtension(block.getRelative(BlockFace.SOUTH, 1)) ||isPistonExtension(block.getRelative(BlockFace.EAST, 1)) ||isPistonExtension(block.getRelative(BlockFace.WEST, 1))) 
				{
					evt.setCancelled(true);
					block.setType(Material.AIR);
				}
				else if(!isSafeCactusBlock(block.getRelative(BlockFace.NORTH, 1)) || !isSafeCactusBlock(block.getRelative(BlockFace.SOUTH, 1)) ||!isSafeCactusBlock(block.getRelative(BlockFace.EAST, 1)) ||!isSafeCactusBlock(block.getRelative(BlockFace.WEST, 1))) 
				{ 
						evt.setCancelled(true);
						block.setType(Material.AIR);
				}	
			}
		}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void ReedPistonRetract(BlockPistonRetractEvent evt) 
	{
		if (AntiFarm.reedenabled)
		    if (!evt.isCancelled() && evt.isSticky()) 
		    {
		            Block movedBlock = evt.getBlock();
		            if (movedBlock.getType() == Material.SUGAR_CANE_BLOCK) 
		            {
						evt.setCancelled(true);
		            }
		    }
	}
	@EventHandler(priority = EventPriority.NORMAL)
	public void ReedPistonExtend(BlockPistonExtendEvent evt) 
	{
		if (AntiFarm.reedenabled)
		    if (!evt.isCancelled()) 
		    {
		    	Block movedBlock = evt.getBlock();
		            if (movedBlock.getType() == Material.SUGAR_CANE_BLOCK)
		            {
		            	evt.setCancelled(true);
		            }
		    }
	}	

	private static final Set<Integer> AIR_MATERIALS = new HashSet<Integer>();
	  
	  static 
	  {
	    AIR_MATERIALS.add(Material.AIR.getId());
	    AIR_MATERIALS.add(Material.SAPLING.getId());
	    AIR_MATERIALS.add(Material.POWERED_RAIL.getId());
	    AIR_MATERIALS.add(Material.DETECTOR_RAIL.getId());
	    AIR_MATERIALS.add(Material.LONG_GRASS.getId());
	    AIR_MATERIALS.add(Material.DEAD_BUSH.getId());
	    AIR_MATERIALS.add(Material.YELLOW_FLOWER.getId());
	    AIR_MATERIALS.add(Material.RED_ROSE.getId());
	    AIR_MATERIALS.add(Material.BROWN_MUSHROOM.getId());
	    AIR_MATERIALS.add(Material.RED_MUSHROOM.getId());  
	    AIR_MATERIALS.add(Material.TORCH.getId());
	    AIR_MATERIALS.add(Material.REDSTONE_WIRE.getId());
	    AIR_MATERIALS.add(Material.SEEDS.getId());
	    AIR_MATERIALS.add(Material.WOODEN_DOOR.getId());
	    AIR_MATERIALS.add(Material.LADDER.getId());
	    AIR_MATERIALS.add(Material.RAILS.getId());
	    AIR_MATERIALS.add(Material.LEVER.getId());
	    AIR_MATERIALS.add(Material.STONE_PLATE.getId());
	    AIR_MATERIALS.add(Material.WOOD_PLATE.getId());  
	    AIR_MATERIALS.add(Material.REDSTONE_TORCH_OFF.getId());
	    AIR_MATERIALS.add(Material.REDSTONE_TORCH_ON.getId());
	    AIR_MATERIALS.add(Material.STONE_BUTTON.getId());
	    AIR_MATERIALS.add(Material.SUGAR_CANE_BLOCK.getId());    
	    AIR_MATERIALS.add(Material.DIODE_BLOCK_OFF.getId());
	    AIR_MATERIALS.add(Material.DIODE_BLOCK_ON.getId());
	    AIR_MATERIALS.add(Material.PUMPKIN_STEM.getId());
	    AIR_MATERIALS.add(Material.MELON_STEM.getId());
	    AIR_MATERIALS.add(Material.VINE.getId());
	    //Cactus edits
	    AIR_MATERIALS.add(Material.WATER.getId());
	    AIR_MATERIALS.add(Material.LAVA.getId());
	    AIR_MATERIALS.add(Material.STATIONARY_WATER.getId());
	    AIR_MATERIALS.add(Material.STATIONARY_LAVA.getId());
	    AIR_MATERIALS.add(Material.FIRE.getId());	    


	        
	  }
	
	  
private static final Set<Integer> PISTON_EXTENSION = new HashSet<Integer>();
	  
	static 
	{
		  PISTON_EXTENSION.add(Material.PISTON_EXTENSION.getId());
		  PISTON_EXTENSION.add(Material.PISTON_MOVING_PIECE.getId());
	}		
	  
	public boolean isSafeCactusBlock(Block block) 
	{
		int type = block.getType().getId();
		if(AIR_MATERIALS.contains(type)) return true;
		return false;
	}
	
	public boolean isPistonExtension(Block block)
	{
		int type = block.getType().getId();
		if(PISTON_EXTENSION.contains(type)) return true;
		return false;
	}

	public static AntiFarm plugin;
	 
	public AntiFarmBlockListener(AntiFarm instance)
	{
	    plugin = instance; 
	}
}
