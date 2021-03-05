package com.twodevsstudio.hub;

import com.twodevsstudio.hub.configuration.ChooseGameGUIConfiguration;
import com.twodevsstudio.hub.configuration.GeneralConfiguration;
import com.twodevsstudio.hub.configuration.ProfileGUIConfiguration;
import com.twodevsstudio.hub.configuration.SettingsGUIConfiguration;
import com.twodevsstudio.hub.gui.settings.logic.chat.ChatManager;
import com.twodevsstudio.hub.gui.settings.logic.chat.PlayerChatListener;
import com.twodevsstudio.hub.gui.settings.logic.visiblity.VisibilityManager;
import com.twodevsstudio.hub.listeners.*;
import com.twodevsstudio.simplejsonconfig.SimpleJSONConfig;
import com.twodevsstudio.simplejsonconfig.api.Config;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Hub extends JavaPlugin {
    private VisibilityManager visibilityManager;
    private ChooseGameGUIConfiguration chooseGameGUIConfiguration;
    private GeneralConfiguration generalConfiguration;
    private ProfileGUIConfiguration profileGUIConfiguration;
    private SettingsGUIConfiguration settingsGUIConfiguration;
    private ChatManager chatManager;

    @Override
    public void onEnable() {
        SimpleJSONConfig.INSTANCE.register(this);
        chooseGameGUIConfiguration = Config.getConfig(ChooseGameGUIConfiguration.class);
        generalConfiguration = Config.getConfig(GeneralConfiguration.class);
        profileGUIConfiguration = Config.getConfig(ProfileGUIConfiguration.class);
        settingsGUIConfiguration = Config.getConfig(SettingsGUIConfiguration.class);
        chatManager = new ChatManager();
        visibilityManager = new VisibilityManager();
        registerEvents();
    }
    private void registerEvents(){
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new GUIListener(),this);
        pluginManager.registerEvents(new PlayerGUIListener(chatManager,visibilityManager,profileGUIConfiguration,settingsGUIConfiguration,generalConfiguration,chooseGameGUIConfiguration),this);
        pluginManager.registerEvents(new PlayerJoinListener(generalConfiguration,visibilityManager,chatManager),this);
        pluginManager.registerEvents(new PlayerChatListener(chatManager),this);
        pluginManager.registerEvents(new BlockPlaceListener(),this);
        pluginManager.registerEvents(new DropItemListener(),this);
        pluginManager.registerEvents(new PlayerQuitListener(chatManager,visibilityManager),this);
        pluginManager.registerEvents(new PlayerPickupListener(),this);
    }
    @Override
    public void onDisable() {

    }
}
