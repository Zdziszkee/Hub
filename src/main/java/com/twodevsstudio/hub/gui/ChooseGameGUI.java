package com.twodevsstudio.hub.gui;

import com.twodevsstudio.hub.configuration.ChooseGameGUIConfiguration;
import com.twodevsstudio.hub.util.Coordinates;
import com.twodevsstudio.wyscore.utils.ConnectionUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class ChooseGameGUI implements GUI {
    private final Player player;
    private final Inventory inventory;
    private final ChooseGameGUIConfiguration chooseGameGUIConfiguration;
    List<PositionalGUISkull> guiSkulls;
    List<PositionalGUIItemStack> guiItemStacks;

    public ChooseGameGUI(ChooseGameGUIConfiguration chooseGameGUIConfiguration, Player player) {
        this.chooseGameGUIConfiguration = chooseGameGUIConfiguration;
        this.inventory = Bukkit.createInventory(this, 45, ChatColor.translateAlternateColorCodes('&', chooseGameGUIConfiguration.getGuiName()));
        this.player = player;
        guiSkulls = chooseGameGUIConfiguration.getGuiSkulls();
        guiItemStacks = chooseGameGUIConfiguration.getGuiItemStacks();
    }

    @Override
    public void onClick(InventoryClickEvent inventoryClickEvent) {
        int slot = inventoryClickEvent.getSlot();

        if (slot == chooseGameGUIConfiguration.getServerTeleport().getSlot()) {
            ConnectionUtil.sendPlayerToServer(player,chooseGameGUIConfiguration.getGameServerName());
        }
        if (slot == chooseGameGUIConfiguration.getExitItemStack().getSlot()) {
            player.closeInventory();
        }
        Coordinates coordinates = getCoordinates(slot);
        if(coordinates!=null){
            player.teleport(new Location(player.getWorld(),coordinates.getX(),coordinates.getY(),coordinates.getZ()));
        }
    }
    private Coordinates getCoordinates(int slot){
        return chooseGameGUIConfiguration.getSlotCoordinatesMap().get(slot);
    }

    @Override
    public Inventory getInventory() {
        return this.inventory;
    }

    public void openInventory() {
        updateInventory();
        this.player.openInventory(inventory);
    }

    private void updateInventory() {
        inventory.setContents(getInventoryContents());
    }

    private ItemStack[] getInventoryContents() {
        Inventory temp = Bukkit.createInventory(null, 45);

        guiSkulls.forEach(positionalGUISkull -> temp.setItem(positionalGUISkull.getSlot(), positionalGUISkull.getItemStack()));
        guiItemStacks.forEach(positionalGUIItemStack -> temp.setItem(positionalGUIItemStack.getSlot(), positionalGUIItemStack.getItemStack()));
        PositionalGUIItemStack serverTeleport = chooseGameGUIConfiguration.getServerTeleport();
        PositionalGUIItemStack exitItemStack = chooseGameGUIConfiguration.getExitItemStack();
        temp.setItem(serverTeleport.getSlot(),serverTeleport.getItemStack());
        temp.setItem(exitItemStack.getSlot(),exitItemStack.getItemStack());
        return temp.getContents();
    }
}
