package me.zdziszkee.hub.configuration;

import com.twodevsstudio.simplejsonconfig.api.Config;
import com.twodevsstudio.simplejsonconfig.interfaces.Configuration;
import lombok.Getter;
import me.zdziszkee.hub.gui.PositionalGUIItemStack;
import me.zdziszkee.hub.gui.PositionalGUISkull;

import java.util.ArrayList;
import java.util.List;

@Getter
@Configuration(name = "choosegamegui.json")
public class ChooseGameGUIConfiguration extends Config {
private String guiName = "example";
private List<PositionalGUIItemStack> guiItemStacks = new ArrayList<>();
private List<PositionalGUISkull> guiSkulls = new ArrayList<>();
}
