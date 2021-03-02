package me.zdziszkee.hub.configuration;

import com.twodevsstudio.simplejsonconfig.api.Config;
import com.twodevsstudio.simplejsonconfig.interfaces.Configuration;
import lombok.Getter;
import me.zdziszkee.hub.gui.GUIItemStack;
import org.bukkit.Material;
@Getter
@Configuration("settingsgui.json")
public class SettingsGUIConfiguration extends Config {
    private String guiName = "example";
    private GUIItemStack visibilityItem = new GUIItemStack("%status%", Material.ENDER_PEARL,"lore");
    private GUIItemStack chatItem = new GUIItemStack("%status%", Material.PAPER,"lore");
    private GUIItemStack timeItem = new GUIItemStack("name", Material.WATCH,"lore");
    private GUIItemStack flyItem = new GUIItemStack("%status%", Material.FEATHER,"lore");
    private GUIItemStack petsItem = new GUIItemStack("name", Material.EGG,"lore");
    private GUIItemStack particlesItem = new GUIItemStack("name", Material.REDSTONE,"lore");
    private GUIItemStack backItem = new GUIItemStack("name", Material.ARROW,"lore");
    private GUIItemStack exitItem = new GUIItemStack("name", Material.BED,"lore");



}
