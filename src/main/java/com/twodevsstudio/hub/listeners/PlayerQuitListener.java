package com.twodevsstudio.hub.listeners;

import com.twodevsstudio.hub.gui.settings.logic.chat.ChatManager;
import com.twodevsstudio.hub.gui.settings.logic.visiblity.VisibilityManager;
import lombok.RequiredArgsConstructor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

@RequiredArgsConstructor
public class PlayerQuitListener implements Listener {
    private final ChatManager chatManager;
    private final VisibilityManager visibilityManager;
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event){
        UUID player = event.getPlayer().getUniqueId();
        chatManager.removePlayer(player);
        visibilityManager.removePlayer(player);
    }
}
