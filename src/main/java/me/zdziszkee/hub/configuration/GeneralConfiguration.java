package me.zdziszkee.hub.configuration;

import com.twodevsstudio.simplejsonconfig.api.Config;
import com.twodevsstudio.simplejsonconfig.interfaces.Configuration;
import lombok.Getter;
import me.zdziszkee.hub.gui.GUIItemStack;
import me.zdziszkee.hub.gui.GUIHead;
import org.bukkit.Material;
@Getter
@Configuration( "generalconfiguration.json")
public class GeneralConfiguration extends Config {
    private GUIHead profileItem = new GUIHead( "example", "example");
    private GUIItemStack chooseGames = new GUIItemStack("example", Material.EYE_OF_ENDER, "lore");
    private GUIItemStack thirdItem = new GUIItemStack("example", Material.YELLOW_FLOWER, "lore");
    private String comingSoonMessage = "Coming soon";
}
