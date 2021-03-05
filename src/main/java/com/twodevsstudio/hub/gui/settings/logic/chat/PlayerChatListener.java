package com.twodevsstudio.hub.gui.settings.logic.chat;

import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class PlayerChatListener implements Listener {
    private final ChatManager chatManager;
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event){
        event.setCancelled(true);
        Set<Player> recipients = event.getRecipients();
        List<Player> receivers = recipients.stream().filter(chatManager::getPlayerChatStatus).collect(Collectors.toList());
        receivers.forEach(player -> player.sendMessage(event.getMessage()));
    }
}
