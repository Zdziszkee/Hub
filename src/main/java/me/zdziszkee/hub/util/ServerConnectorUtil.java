package me.zdziszkee.hub.util;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import org.bukkit.entity.Player;

public class ServerConnectorUtil {

    public static void connect(Player player, String serverName) {
        ProxyServer instance = ProxyServer.getInstance();
        if(instance==null)return;
        ProxiedPlayer proxiedPlayer = instance.getPlayer(player.getUniqueId());
        if (proxiedPlayer == null) return;
        ServerInfo target = instance.getServerInfo(serverName);
        if (target == null) return;

        proxiedPlayer.connect(target);
    }


}
