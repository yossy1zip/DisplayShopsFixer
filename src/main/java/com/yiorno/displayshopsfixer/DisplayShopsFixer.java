package com.yiorno.displayshopsfixer;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import xzot1k.plugins.ds.api.events.ShopCreationEvent;
import xzot1k.plugins.ds.api.events.ShopItemSetEvent;

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

    @EventHandler(priority= EventPriority.LOWEST)
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

    @EventHandler(priority= EventPriority.LOWEST)
    public void onPlaceShop(BlockPlaceEvent e){
        Material mat = e.getBlockPlaced().getType();
        Location loc = e.getBlockPlaced().getLocation();
        String itemName = e.getItemInHand().getItemMeta().getDisplayName();

        if(mat==Material.CHEST) {

            if(itemName.contains("ショップ")) {

                for (int y = 1; y <= 2; y++) {
                    if (loc.getBlock().getRelative(0, y, 0).getType()!=Material.AIR) {
                        e.getPlayer().sendMessage(ChatColor.RED + "上方に十分な空きスペースがありません！");
                        e.setCancelled(true);
                        break;
                    }
                }

            }

        }
    }

    @EventHandler
    public void onItemSet(ShopItemSetEvent e){
        if(!(e.getItemStack().hasItemMeta())){
            return;
        }

        assert e.getItemStack().getItemMeta() != null;
        String name = e.getItemStack().getItemMeta().getDisplayName();

        if(name.contains("\\")){
            e.setCancelled(true);
            e.getPlayer().sendMessage(ChatColor.RED + "アイテム名に使用できない文字が含まれています！");
        }
    }
}
