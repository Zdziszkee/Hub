package com.twodevsstudio.hub.configuration;

import com.twodevsstudio.simplejsonconfig.api.Config;
import com.twodevsstudio.simplejsonconfig.interfaces.Configuration;
import lombok.Getter;
import com.twodevsstudio.hub.gui.PositionalGUIItemStack;
import com.twodevsstudio.hub.gui.PositionalGUISkull;
import com.twodevsstudio.hub.util.Coordinates;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Configuration( "choosegamegui.json")
public class ChooseGameGUIConfiguration extends Config {
    private String guiName = "example";
    private String gameServerName = "survival";
    private List<PositionalGUIItemStack> guiItemStacks = new ArrayList<>();
    private List<PositionalGUISkull> guiSkulls = new ArrayList<>();
    private Map<Integer, Coordinates> slotCoordinatesMap = new HashMap<>();
    private PositionalGUIItemStack serverTeleport = new PositionalGUIItemStack(22,"teleport to server", Material.DIAMOND_CHESTPLATE,"lore");
    private PositionalGUIItemStack exitItemStack = new PositionalGUIItemStack(40,"exit", Material.BED,"lore");
    public ChooseGameGUIConfiguration() {
        slotCoordinatesMap.put(1, new Coordinates("example",0, 0, 0));
    }
}
