package com.radthorne.AntiFarm;/*
 * Created by Luuk Jacobs at 17-1-13 14:30
 */

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.block.BlockPistonExtendEvent;

import java.util.HashSet;
import java.util.Set;

class AntiFarmBlockListener implements Listener {

  @EventHandler(priority = EventPriority.HIGHEST)
  public void CactusPhysics(BlockPhysicsEvent evt) {
    Block block = evt.getBlock();
    if (conf.cactusEnabled)
      if (block.getType() == Material.CACTUS) {
        if (isPistonExtension(block.getRelative(BlockFace.NORTH, 1)) || isPistonExtension(block.getRelative(BlockFace.SOUTH, 1)) || isPistonExtension(block.getRelative(BlockFace.EAST, 1)) || isPistonExtension(block.getRelative(BlockFace.WEST, 1))) {
          evt.setCancelled(true);
          block.setType(Material.AIR);
        } else if (isNotSafeCactusBlock(block.getRelative(BlockFace.NORTH, 1)) || isNotSafeCactusBlock(block.getRelative(BlockFace.SOUTH, 1)) || isNotSafeCactusBlock(block.getRelative(BlockFace.EAST, 1)) || isNotSafeCactusBlock(block.getRelative(BlockFace.WEST, 1))) {
          evt.setCancelled(true);
          block.setType(Material.AIR);

        }
      }
  }

  @EventHandler(priority = EventPriority.HIGHEST)
  public void PistonExtendEvent(BlockPistonExtendEvent event) {
    if (event.isCancelled()) {
      return;
    }
    Block movedBlock = event.getBlock();
    Block pistonRelative = movedBlock.getRelative(event.getDirection());
    if (conf.reedEnabled) {
      if (pistonRelative.getType() == Material.SUGAR_CANE_BLOCK) {
        event.setCancelled(true);
        return;
      }
      for (Block block : event.getBlocks()) {
        if (block.getRelative(event.getDirection()).getType() == Material.SUGAR_CANE_BLOCK) {
          event.setCancelled(true);
          return;
        }
      }
    }
    if (conf.cropsEnabled) {
      if (pistonRelative.getType() == Material.CROPS || pistonRelative.getType() == Material.POTATO || pistonRelative.getType() == Material.CARROT) {
        event.setCancelled(true);
        return;
      }
      for (Block block : event.getBlocks()) {
        if (block.getRelative(event.getDirection()).getType() == Material.CROPS || block.getRelative(event.getDirection()).getType() == Material.POTATO || block.getRelative(event.getDirection()).getType() == Material.CARROT) {
          event.setCancelled(true);
          return;
        }
      }
    }
    if (conf.melonsEnabled) {
      if (pistonRelative.getType() == Material.MELON_BLOCK || pistonRelative.getType() == Material.MELON_STEM) {
        event.setCancelled(true);
        return;
      }
      for (Block block : event.getBlocks()) {
        if (block.getRelative(event.getDirection()).getType() == Material.MELON_BLOCK || block.getRelative(event.getDirection()).getType() == Material.MELON_STEM) {
          event.setCancelled(true);
          return;
        }
      }
    }
    if (conf.pumpkinEnabled) {
      if (pistonRelative.getType() == Material.PUMPKIN || pistonRelative.getType() == Material.PUMPKIN_STEM) {
        event.setCancelled(true);
        return;
      }
      for (Block block : event.getBlocks()) {
        if (block.getRelative(event.getDirection()).getType() == Material.PUMPKIN || block.getRelative(event.getDirection()).getType() == Material.PUMPKIN_STEM) {
          event.setCancelled(true);
          return;
        }
      }
    }
    if (conf.cactusPistonEnabled) {
      if (pistonRelative.getRelative(event.getDirection()).getType() == Material.CACTUS) {
        event.setCancelled(true);
        return;
      }
      for (Block block : event.getBlocks()) {
        if (block.getRelative(event.getDirection()).getRelative(event.getDirection()).getType() == Material.CACTUS) {
          event.setCancelled(true);
          return;
        }
      }
    }
  }

  private static final Set<Integer> SAFE_MATERIALS = new HashSet<Integer>();

  static {
    SAFE_MATERIALS.add(Material.AIR.getId());
    SAFE_MATERIALS.add(Material.SAPLING.getId());
    SAFE_MATERIALS.add(Material.POWERED_RAIL.getId());
    SAFE_MATERIALS.add(Material.DETECTOR_RAIL.getId());
    SAFE_MATERIALS.add(Material.LONG_GRASS.getId());
    SAFE_MATERIALS.add(Material.DEAD_BUSH.getId());
    SAFE_MATERIALS.add(Material.YELLOW_FLOWER.getId());
    SAFE_MATERIALS.add(Material.RED_ROSE.getId());
    SAFE_MATERIALS.add(Material.BROWN_MUSHROOM.getId());
    SAFE_MATERIALS.add(Material.RED_MUSHROOM.getId());
    SAFE_MATERIALS.add(Material.TORCH.getId());
    SAFE_MATERIALS.add(Material.REDSTONE_WIRE.getId());
    SAFE_MATERIALS.add(Material.SEEDS.getId());
    SAFE_MATERIALS.add(Material.WOODEN_DOOR.getId());
    SAFE_MATERIALS.add(Material.LADDER.getId());
    SAFE_MATERIALS.add(Material.RAILS.getId());
    SAFE_MATERIALS.add(Material.LEVER.getId());
    SAFE_MATERIALS.add(Material.STONE_PLATE.getId());
    SAFE_MATERIALS.add(Material.WOOD_PLATE.getId());
    SAFE_MATERIALS.add(Material.REDSTONE_TORCH_OFF.getId());
    SAFE_MATERIALS.add(Material.REDSTONE_TORCH_ON.getId());
    SAFE_MATERIALS.add(Material.STONE_BUTTON.getId());
    SAFE_MATERIALS.add(Material.SUGAR_CANE_BLOCK.getId());
    SAFE_MATERIALS.add(Material.DIODE_BLOCK_OFF.getId());
    SAFE_MATERIALS.add(Material.DIODE_BLOCK_ON.getId());
    SAFE_MATERIALS.add(Material.PUMPKIN_STEM.getId());
    SAFE_MATERIALS.add(Material.MELON_STEM.getId());
    SAFE_MATERIALS.add(Material.VINE.getId());
    SAFE_MATERIALS.add(Material.WATER.getId());
    SAFE_MATERIALS.add(Material.LAVA.getId());
    SAFE_MATERIALS.add(Material.STATIONARY_WATER.getId());
    SAFE_MATERIALS.add(Material.STATIONARY_LAVA.getId());
    SAFE_MATERIALS.add(Material.FIRE.getId());


  }


  private static final Set<Integer> PISTON_EXTENSION = new HashSet<Integer>();

  static {
    PISTON_EXTENSION.add(Material.PISTON_EXTENSION.getId());
    PISTON_EXTENSION.add(Material.PISTON_MOVING_PIECE.getId());
  }

  boolean isNotSafeCactusBlock(Block block) {
    int type = block.getType().getId();
    return !SAFE_MATERIALS.contains(type);
  }

  boolean isPistonExtension(Block block) {
    int type = block.getType().getId();
    return PISTON_EXTENSION.contains(type);
  }

  private static AntiFarm plugin;

  public AntiFarmBlockListener(AntiFarm instance) {
    plugin = instance;
  }
}
