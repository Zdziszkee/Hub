package me.zdziszkee.hub.listeners;

import lombok.RequiredArgsConstructor;
import me.zdziszkee.hub.configuration.ChooseGameGUIConfiguration;
import me.zdziszkee.hub.configuration.GeneralConfiguration;
import me.zdziszkee.hub.configuration.ProfileGUIConfiguration;
import me.zdziszkee.hub.configuration.SettingsGUIConfiguration;
import me.zdziszkee.hub.gui.ChooseGameGUI;
import me.zdziszkee.hub.gui.ProfileGUI;
import me.zdziszkee.hub.gui.settings.logic.chat.ChatManager;
import me.zdziszkee.hub.gui.settings.logic.visiblity.VisibilityManager;
import me.zdziszkee.wyscore.api.CoreAPI;
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
