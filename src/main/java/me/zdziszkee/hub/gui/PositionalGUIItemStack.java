package me.zdziszkee.hub.gui;

import lombok.Getter;
import org.bukkit.Material;
@Getter
public class PositionalGUIItemStack extends GUIItemStack{
    private final int slot;
    public PositionalGUIItemStack(int slot,String displayName, Material material, String... lore) {
        super(displayName, material, lore);
        this.slot = slot;
    }
}
