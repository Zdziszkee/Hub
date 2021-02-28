package me.zdziszkee.hub.configuration;

import com.twodevsstudio.simplejsonconfig.api.Config;
import com.twodevsstudio.simplejsonconfig.interfaces.Configuration;
import lombok.Getter;
import me.zdziszkee.hub.gui.GUIHead;
import me.zdziszkee.hub.gui.GUIItemStack;
import org.bukkit.Material;

@Getter
@Configuration(name = "usergui.json")
public class ProfileGUIConfiguration extends Config {
    private String guiName = "name";
    private GUIItemStack nickNameItem = new GUIItemStack("name", Material.NAME_TAG,"lore");
    private GUIHead playerHead = new GUIHead("%player%","lore");
    private GUIItemStack settingsItem = new GUIItemStack("settings",Material.REDSTONE_COMPARATOR,"lore");
    private GUIItemStack exitItem = new GUIItemStack("exit",Material.BED,"lore");
    private String linkToWebSiteMessage = "www.example/%player%";
}
