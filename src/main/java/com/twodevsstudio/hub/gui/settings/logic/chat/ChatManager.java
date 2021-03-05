package com.twodevsstudio.hub.gui.settings.logic.chat;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ChatManager {
    private final Map<UUID,Boolean> isPlayerChatEnabledMap = new HashMap<>();

    private void setPlayerChat(Player player, boolean isEnabled){
        isPlayerChatEnabledMap.put(player.getUniqueId(),isEnabled);
    }
    public void  addPlayer(UUID uuid,boolean shouldReceiveMessages){
        isPlayerChatEnabledMap.put(uuid,shouldReceiveMessages);
    }
    public void removePlayer(UUID uuid){
        isPlayerChatEnabledMap.remove(uuid);
    }
    public void enablePlayerChat(Player player){
        setPlayerChat(player,true);
    }
    public void disablePlayerChat(Player player){
        setPlayerChat(player,false);
    }

    public void togglePlayerChat(Player player){
        boolean playerVisibility = getPlayerChatStatus(player);
        if(playerVisibility){
            disablePlayerChat(player);
        }else {
            enablePlayerChat(player);
        }
    }
    public boolean getPlayerChatStatus(Player player){
        return isPlayerChatEnabledMap.get(player.getUniqueId());
    }
}
