package me.zdziszkee.hub.gui.settings;

import lombok.RequiredArgsConstructor;
import me.zdziszkee.hub.configuration.GeneralConfiguration;
import me.zdziszkee.hub.configuration.SettingsGUIConfiguration;
import me.zdziszkee.hub.gui.GUI;
import me.zdziszkee.hub.gui.ProfileGUI;
import me.zdziszkee.hub.gui.settings.logic.chat.ChatManager;
import me.zdziszkee.hub.gui.settings.logic.visiblity.VisibilityManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

@RequiredArgsConstructor
public class SettingsGUI implements GUI {
    private final static String enabled = "&aEnabled";
    private final static String disabled = "&cDisabled";
    private final SettingsGUIConfiguration settingsGUIConfiguration;
    private final VisibilityManager visibilityManager;
    private final ChatManager chatManager;
    private final GeneralConfiguration generalConfiguration;
    private final ProfileGUI profileGUI;
    private final Inventory inventory;
    private final Player player;

    public SettingsGUI(SettingsGUIConfiguration settingsGUIConfiguration, Player player,VisibilityManager visibilityManager,ChatManager chatManager,GeneralConfiguration generalConfiguration,ProfileGUI profileGUI) {
        this.settingsGUIConfiguration = settingsGUIConfiguration;
        this.player = player;
        this.inventory = Bukkit.createInventory(this,45, ChatColor.translateAlternateColorCodes('&',settingsGUIConfiguration.getGuiName()));
        this.visibilityManager = visibilityManager;
        this.chatManager = chatManager;
        this.generalConfiguration = generalConfiguration;
        this.profileGUI = profileGUI;
    }

    @Override
    public void onClick(InventoryClickEvent inventoryClickEvent) {
        int slot = inventoryClickEvent.getSlot();
        if(slot==10){
            visibilityManager.togglePlayerVisibility(player);
            updateInventory();
        }
        if(slot==13){
            player.setPlayerTime(0,false);
        }
        if(slot==19){
            chatManager.togglePlayerChat(player);
            updateInventory();
        }
        if(slot==22){
            player.setAllowFlight(true);
            player.setFlying(!player.isFlying());
            updateInventory();
        }
        if(slot==25){
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',generalConfiguration.getComingSoonMessage()));
        }
        if(slot==16){
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',generalConfiguration.getComingSoonMessage()));
        }
        if(slot==39){
            profileGUI.openInventory();
        }
        if(slot==41){
            player.closeInventory();
        }
    }
    private void updateInventory(){
        inventory.setContents(getInventoryContents());
    }
    public void openInventory(){
        updateInventory();
        player.openInventory(inventory);
    }
    @Override
    public Inventory getInventory() {
        return inventory;
    }
    private ItemStack[] getInventoryContents(){
        Inventory temp = Bukkit.createInventory(null,45);

        temp.setItem(10,settingsGUIConfiguration.getVisibilityItem().clone().replacePlaceHolder("%status%", visibilityManager.getPlayerVisibility(player)?enabled:disabled).getItemStack());
        temp.setItem(19,settingsGUIConfiguration.getChatItem().clone().replacePlaceHolder("%status%", chatManager.getPlayerChatStatus(player)?enabled:disabled).getItemStack());
        temp.setItem(13,settingsGUIConfiguration.getTimeItem().getItemStack());
        temp.setItem(22,settingsGUIConfiguration.getFlyItem().replacePlaceHolder("%status%",player.getAllowFlight()?enabled:disabled).getItemStack());

        temp.setItem(25,settingsGUIConfiguration.getPetsItem().getItemStack());
        temp.setItem(16,settingsGUIConfiguration.getParticlesItem().getItemStack());
        temp.setItem(39,settingsGUIConfiguration.getBackItem().getItemStack());
        temp.setItem(41,settingsGUIConfiguration.getExitItem().getItemStack());
        return temp.getContents();
    }
}
