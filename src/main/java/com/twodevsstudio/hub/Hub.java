package com.twodevsstudio.hub;

import co.aikar.commands.PaperCommandManager;
import com.twodevsstudio.hub.commands.LocationCmd;
import com.twodevsstudio.hub.configuration.*;
import com.twodevsstudio.hub.gui.settings.logic.chat.ChatManager;
import com.twodevsstudio.hub.gui.settings.logic.chat.PlayerChatListener;
import com.twodevsstudio.hub.gui.settings.logic.visiblity.VisibilityManager;
import com.twodevsstudio.hub.listeners.*;
import com.twodevsstudio.simplejsonconfig.SimpleJSONConfig;
import com.twodevsstudio.simplejsonconfig.api.Config;
import com.twodevsstudio.wyscore.api.CoreAPI;
import com.twodevsstudio.wyscore.database.service.CurrencyService;
import com.twodevsstudio.wyscore.database.service.PlayerService;
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
    private ScoreBoardConfiguration scoreBoardConfiguration;
    private  PlayerService playerService;
    private  CurrencyService currencyService;
    private static Hub instance;
    @Override
    public void onEnable() {
        instance=this;
        currencyService  = CoreAPI.getCurrencyService();
        playerService = CoreAPI.getPlayerService();
        SimpleJSONConfig.INSTANCE.register(this);
        chooseGameGUIConfiguration = Config.getConfig(ChooseGameGUIConfiguration.class);
        generalConfiguration = Config.getConfig(GeneralConfiguration.class);
        profileGUIConfiguration = Config.getConfig(ProfileGUIConfiguration.class);
        settingsGUIConfiguration = Config.getConfig(SettingsGUIConfiguration.class);
        scoreBoardConfiguration = Config.getConfig(ScoreBoardConfiguration.class);
        chatManager = new ChatManager();
        visibilityManager = new VisibilityManager();
        registerEvents();
        PaperCommandManager paperCommandManager = new PaperCommandManager(this);
        paperCommandManager.registerCommand(new LocationCmd(chooseGameGUIConfiguration,generalConfiguration));
    }
    private void registerEvents(){
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new GUIListener(),this);
        pluginManager.registerEvents(new PlayerGUIListener(chatManager,visibilityManager,profileGUIConfiguration,settingsGUIConfiguration,generalConfiguration,chooseGameGUIConfiguration),this);
        pluginManager.registerEvents(new PlayerJoinListener(generalConfiguration,visibilityManager,chatManager,scoreBoardConfiguration,
                playerService,currencyService),this);
        pluginManager.registerEvents(new PlayerChatListener(chatManager),this);
        pluginManager.registerEvents(new BlockPlaceListener(),this);
        pluginManager.registerEvents(new DropItemListener(),this);
        pluginManager.registerEvents(new PlayerQuitListener(chatManager,visibilityManager),this);
        pluginManager.registerEvents(new PlayerPickupListener(),this);
        pluginManager.registerEvents(new PlayerFallDamageListener(),this);
        pluginManager.registerEvents(new PlayerFoodChangeListener(),this);
    }
    @Override
    public void onDisable() {

    }
    
    public static Hub getInstance() {
        
        return instance;
    }
}
