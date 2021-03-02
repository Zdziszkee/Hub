package me.zdziszkee.hub.gui;

import me.zdziszkee.hub.configuration.GeneralConfiguration;
import me.zdziszkee.hub.configuration.ProfileGUIConfiguration;
import me.zdziszkee.hub.configuration.SettingsGUIConfiguration;
import me.zdziszkee.hub.gui.settings.SettingsGUI;
import me.zdziszkee.hub.gui.settings.logic.chat.ChatManager;
import me.zdziszkee.hub.gui.settings.logic.visiblity.VisibilityManager;
import me.zdziszkee.hub.util.TimeUtil;
import me.zdziszkee.wyscore.currency.CurrencyPack;
import me.zdziszkee.wyscore.database.service.PlayerService;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.time.format.DateTimeFormatter;

public class ProfileGUI implements GUI {
    private final ProfileGUIConfiguration userGUIConfiguration;
    private final Inventory inventory;
    private final Player player;
    private final SettingsGUIConfiguration settingsGUIConfiguration;
    private final ChatManager chatManager;
    private final VisibilityManager visibilityManager;
    private final GeneralConfiguration generalConfiguration;
    private final PlayerService.PlayerData playerData;
    private final CurrencyPack currencyPack;
    public ProfileGUI(ProfileGUIConfiguration userGUIConfiguration, Player player, SettingsGUIConfiguration settingsGUIConfiguration, ChatManager chatManager, VisibilityManager visibilityManager, GeneralConfiguration generalConfiguration, PlayerService.PlayerData playerData,CurrencyPack currencyPack) {
        this.userGUIConfiguration = userGUIConfiguration;
        this.player = player;
        inventory = Bukkit.createInventory(this, 45, ChatColor.translateAlternateColorCodes('&', userGUIConfiguration.getGuiName()));
        this.settingsGUIConfiguration = settingsGUIConfiguration;
        this.chatManager = chatManager;
        this.visibilityManager = visibilityManager;
        this.generalConfiguration = generalConfiguration;
        this.playerData = playerData;
        this.currencyPack = currencyPack;
    }

    @Override
    public void onClick(InventoryClickEvent inventoryClickEvent) {
        int slot = inventoryClickEvent.getSlot();
        if (slot == 24) {
            SettingsGUI settingsGUI = new SettingsGUI(settingsGUIConfiguration, player, visibilityManager, chatManager,generalConfiguration,this);
            settingsGUI.openInventory();
        }
        if (slot == 20) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', generalConfiguration.getComingSoonMessage()));
        }
        if (slot == 22) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', userGUIConfiguration.getLinkToWebSiteMessage().replace("%player%", player.getName())));
        }
        if (slot == 40) {
            player.closeInventory();
        }
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }

    public ItemStack[] getInventoryContents() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
        Inventory temp = Bukkit.createInventory(null, 45);
        temp.setItem(20, userGUIConfiguration.getNickNameItem().getItemStack());
        temp.setItem(22, userGUIConfiguration.getPlayerHead().setHeadOwner(player.getName()).replacePlaceHolder("%player%", player.getName())
                .replacePlaceHolder("%firstjoin%",playerData.getFirstJoinTime().format(dateTimeFormatter))
                .replacePlaceHolder("%onelinetime%", String.valueOf(TimeUtil.convertMillisToDays(playerData.getOnlineTimeInMillis())))
                .replacePlaceHolder("%money%", String.valueOf(currencyPack.getMoney()))
                .replacePlaceHolder("%bezants%",String.valueOf(currencyPack.getBezants()))
                .replacePlaceHolder("%keys%",String.valueOf(currencyPack.getKeys()))
                .getItemStack());
        temp.setItem(24, userGUIConfiguration.getSettingsItem().getItemStack());
        temp.setItem(40, userGUIConfiguration.getExitItem().getItemStack());
        return temp.getContents();
    }

    private void updateInventory() {
        inventory.setContents(getInventoryContents());
    }

    public void openInventory() {
        updateInventory();
        player.openInventory(inventory);
    }
}
