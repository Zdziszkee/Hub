package me.zdziszkee.hub.gui;


import lombok.Getter;

@Getter
public class PositionalGUISkull extends GUISkull{
    private final int slot;
    public PositionalGUISkull(int slot,String headSkinInBase64, String displayName, String... lore) {
        super(headSkinInBase64, displayName, lore);
        this.slot = slot;
    }


}
