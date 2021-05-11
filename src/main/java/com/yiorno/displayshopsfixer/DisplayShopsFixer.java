package com.yiorno.displayshopsfixer;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import xzot1k.plugins.ds.api.events.ShopCreationEvent;

public final class DisplayShopsFixer extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void onCreateShop(ShopCreationEvent e){
        Location loc = e.getLocation();
        Location newLoc = new Location(e.getLocation().getWorld(),e.getLocation().getX(),e.getLocation().getY()-1, e.getLocation().getZ());
        Block block = newLoc.getBlock();
        if(block.getType() == Material.CHEST){
            e.getPlayer().sendMessage(ChatColor.RED + "ショップを重ねて置くことはできません！");
            e.setCancelled(true);
            e.setCancelBlockPlaceEvent(true);
        }

    }
}
