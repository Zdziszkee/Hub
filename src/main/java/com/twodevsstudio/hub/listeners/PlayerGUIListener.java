package com.twodevsstudio.hub.listeners;

import com.twodevsstudio.hub.configuration.ChooseGameGUIConfiguration;
import com.twodevsstudio.hub.configuration.GeneralConfiguration;
import com.twodevsstudio.hub.configuration.ProfileGUIConfiguration;
import com.twodevsstudio.hub.configuration.SettingsGUIConfiguration;
import com.twodevsstudio.wyscore.api.CoreAPI;
import lombok.RequiredArgsConstructor;
import com.twodevsstudio.hub.gui.ChooseGameGUI;
import com.twodevsstudio.hub.gui.ProfileGUI;
import com.twodevsstudio.hub.gui.settings.logic.chat.ChatManager;
import com.twodevsstudio.hub.gui.settings.logic.visiblity.VisibilityManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.PlayerInventory;

@RequiredArgsConstructor
public class PlayerGUIListener implements Listener {
    private final ChatManager chatManager;
    private final VisibilityManager visibilityManager;
    private final ProfileGUIConfiguration profileGUIConfiguration;
    private final SettingsGUIConfiguration settingsGUIConfiguration;
    private final GeneralConfiguration generalConfiguration;
    private final ChooseGameGUIConfiguration chooseGameGUIConfiguration;

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Inventory clickedInventory = event.getClickedInventory();
        if (!(clickedInventory instanceof PlayerInventory)) return;
        Player whoClicked = (Player) event.getWhoClicked();
        PlayerInventory playerInventory = (PlayerInventory) clickedInventory;
        if (!(whoClicked.getInventory().equals(playerInventory))) return;
        event.setCancelled(true);


    }

    @EventHandler
    public void onInventoryDrag(InventoryDragEvent event) {
        Inventory clickedInventory = event.getInventory();
        if (!(clickedInventory instanceof PlayerInventory)) return;
        Player whoClicked = (Player) event.getWhoClicked();
        PlayerInventory playerInventory = (PlayerInventory) clickedInventory;
        if (!(whoClicked.getInventory().equals(playerInventory))) return;
        event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        int heldItemSlot = player.getInventory().getHeldItemSlot();
        switch (heldItemSlot) {
            case 0:
                new ProfileGUI(profileGUIConfiguration, player, settingsGUIConfiguration, chatManager, visibilityManager, generalConfiguration, CoreAPI.getPlayerService().findByUUID(player.getUniqueId()), CoreAPI.getCurrencyService().findByUUID(player.getUniqueId())).openInventory();
                break;
            case 4:
                new ChooseGameGUI(chooseGameGUIConfiguration, player).openInventory();
                break;
            case 8:
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', generalConfiguration.getComingSoonMessage()));
                break;
        }
        event.setCancelled(true);
    }
}
