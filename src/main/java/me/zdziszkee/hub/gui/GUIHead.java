package me.zdziszkee.hub.gui;

import me.zdziszkee.hub.util.SkullCreator;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GUIHead {
    private String displayName;
    private List<String> lore;
    private String owner;

    public GUIHead(String owner, String displayName, String... lore) {
        this.displayName = displayName;
        this.lore = Arrays.asList(lore);
        this.owner = owner;
    }

    public GUIHead(String displayName, List<String> lore) {
        this.displayName = displayName;
        this.lore = lore;
    }

    public GUIHead setHeadOwner(String name){
        owner = name;
        return this;
    }

    @Override
    public GUIHead clone() {
        return new GUIHead(owner, displayName, lore.toArray(new String[0]));
    }

    public ItemStack getItemStack() {
        ItemStack itemStack = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.addItemFlags(ItemFlag.values());
        itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', displayName));
        itemMeta.setLore(lore.stream().map(s -> ChatColor.translateAlternateColorCodes('&', s)).collect(Collectors.toList()));
        itemStack.setItemMeta(itemMeta);
        return SkullCreator.getPlayerHead(owner);
    }

    public GUIHead replacePlaceHolder(String placeHolder, String value) {
        this.displayName = displayName.replace(placeHolder, value);
        this.lore = lore.stream().map(s -> s.replace(placeHolder, value)).collect(Collectors.toList());
        return this;
    }
}
