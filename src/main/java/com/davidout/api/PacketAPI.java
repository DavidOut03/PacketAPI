package com.davidout.api;


import com.davidout.api.packet.type.entity.PacketPlayOutEntityDestroy;
import org.bukkit.Bukkit;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.InvocationTargetException;

public final class PacketAPI extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void onClick(PlayerInteractEntityEvent e) throws InvocationTargetException, IllegalAccessException, InstantiationException {
        if(!(e.getRightClicked() instanceof Zombie)) return;
        PacketPlayOutEntityDestroy destroyPacket = new PacketPlayOutEntityDestroy(e.getRightClicked().getEntityId());
        destroyPacket.sendToPlayer(e.getPlayer());

    }


}
