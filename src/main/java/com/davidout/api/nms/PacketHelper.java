package com.davidout.api.nms;

import com.davidout.api.exception.PacketSendException;
import com.davidout.api.nms.reflection.ReflectionHelper;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;

public class PacketHelper {

    public static Object getCraftPlayer(Player player){
        try {
            return  ReflectionHelper.getMethod(player.getClass(), "getHandle").invoke(player);
        } catch (Exception ignored) {
            return null;
        }
    }

    public static Object getPlayerConnection(Player player) {
        Object craftPlayer = getCraftPlayer(player);
        if(craftPlayer == null) return null;
        try {
            return ReflectionHelper.getField(craftPlayer.getClass(), "playerConnection").get(craftPlayer);
        } catch (Exception ex) {
            return null;
        }
    }

    public static void sendPacket(Player player, Object packet) throws PacketSendException {
        try {
            Object connection = getPlayerConnection(player);

            if(connection == null) {
                throw new PacketSendException();
            }

            ReflectionHelper.getMethod(connection.getClass(), "sendPacket", NMSHelper.getMinecraftClass("Packet")).invoke( connection , packet);
        } catch (Exception ex) {
            throw new PacketSendException();
        }
    }

}
