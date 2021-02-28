package me.zdziszkee.hub.util;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.UUID;


public class SkullCreator {
public static ItemStack getPlayerHead(String player){
    ItemStack itemStack = new ItemStack(Material.SKULL_ITEM);
    SkullMeta meta = (SkullMeta) itemStack.getItemMeta();
    meta.setOwner(player);
    itemStack.setItemMeta(meta);
    return itemStack;
}
    public static ItemStack getCustomTextureHead(ItemStack itemStack,String value) {
        ItemStack head =  itemStack;
        SkullMeta meta = (SkullMeta) head.getItemMeta();
        GameProfile profile = new GameProfile(UUID.randomUUID(), "");
        profile.getProperties().put("textures", new Property("textures", value));
        Field profileField = null;
        try {
            profileField = meta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(meta, profile);
        } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
            e.printStackTrace();
        }
        head.setItemMeta(meta);
        return head;
    }

}