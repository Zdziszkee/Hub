package me.zdziszkee.hub.listeners;

import lombok.RequiredArgsConstructor;
import me.zdziszkee.hub.configuration.GeneralConfiguration;
import me.zdziszkee.hub.gui.settings.logic.chat.ChatManager;
import me.zdziszkee.hub.gui.settings.logic.visiblity.VisibilityManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.PlayerInventory;
@RequiredArgsConstructor
public class PlayerJoinListener implements Listener {
    private final GeneralConfiguration generalConfiguration;
    private final VisibilityManager visibilityManager;
    private final ChatManager chatManager;
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        PlayerInventory playerInventory = player.getInventory();
        player.getInventory().clear();
        playerInventory.setItem(0,generalConfiguration.getProfileItem().setHeadOwner(player.getName()).getItemStack());
        playerInventory.setItem(4,generalConfiguration.getChooseGames().getItemStack());
        playerInventory.setItem(8,generalConfiguration.getThirdItem().getItemStack());

        visibilityManager.addPlayerToHide(player);
        visibilityManager.addPlayer(player.getUniqueId(),true);
        chatManager.addPlayer(player.getUniqueId(),true);
    }

}
