package com.yiorno.displayshopsfixer;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import xzot1k.plugins.ds.api.events.ShopCreationEvent;
import xzot1k.plugins.ds.api.objects.MarketRegion;
import xzot1k.plugins.ds.api.objects.Shop;

public final class DisplayShopsFixer extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void onCreateShop(ShopCreationEvent e){
        Location loc = e.getLocation();
        Location newLoc = new Location(e.getLocation().getWorld(),e.getLocation().getX(),e.getLocation().getY()-1, e.getLocation().getZ());
        MarketRegion mr = null;
        Shop shop = null;
        shop.getShopId();
    }
}
