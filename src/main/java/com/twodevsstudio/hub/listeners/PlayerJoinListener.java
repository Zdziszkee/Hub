package com.twodevsstudio.hub.listeners;

import com.twodevsstudio.hub.configuration.GeneralConfiguration;
import com.twodevsstudio.hub.configuration.ScoreBoardConfiguration;
import com.twodevsstudio.hub.gui.settings.logic.chat.ChatManager;
import com.twodevsstudio.hub.gui.settings.logic.visiblity.VisibilityManager;
import com.twodevsstudio.hub.scoreboard.lib.ScoreboardLib;
import com.twodevsstudio.hub.scoreboard.lib.common.EntryBuilder;
import com.twodevsstudio.hub.scoreboard.lib.type.Entry;
import com.twodevsstudio.hub.scoreboard.lib.type.ScoreboardHandler;
import com.twodevsstudio.wyscore.currency.CurrencyPack;
import com.twodevsstudio.wyscore.database.service.CurrencyService;
import com.twodevsstudio.wyscore.database.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.PlayerInventory;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class PlayerJoinListener implements Listener {
    private final GeneralConfiguration generalConfiguration;
    private final VisibilityManager visibilityManager;
    private final ChatManager chatManager;
    private final ScoreBoardConfiguration scoreBoardConfiguration;
    private final PlayerService playerService;
    private final CurrencyService currencyService;
    
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.setJoinMessage(null);
        Player player = event.getPlayer();
        PlayerInventory playerInventory = player.getInventory();
        player.getInventory().clear();
        playerInventory.setItem(0, generalConfiguration.getProfileItem().setHeadOwner(player.getName()).getItemStack());
        playerInventory.setItem(4, generalConfiguration.getChooseGames().getItemStack());
        playerInventory.setItem(8, generalConfiguration.getThirdItem().getItemStack());
        visibilityManager.addPlayerToHide(player);
        UUID uniqueId = player.getUniqueId();
        visibilityManager.addPlayer(uniqueId, true);
        chatManager.addPlayer(uniqueId, true);
        player.setGameMode(GameMode.ADVENTURE);
        CurrencyPack currencyPack = currencyService.findByUUID(uniqueId);
        PlayerService.PlayerData playerData = playerService.findByUUID(uniqueId);
    
        ScoreboardLib.createScoreboard(player).setHandler(new ScoreboardHandler() {
            @Override
            public String getTitle(Player player) {
                
                return ChatColor.translateAlternateColorCodes('&', "&e&lWystern");
            }
            
            @Override
            public List<Entry> getEntries(Player player) {
                
                EntryBuilder entryBuilder = new EntryBuilder();
                for (String line : scoreBoardConfiguration.getLines()) {
                    String formatted = line.replace("%gender%",playerData.isBoy() ? "Boy" : "Girl")
                            .replace("%money%", String.valueOf(currencyPack.getMoney()))
                            .replace("%bezants%", String.valueOf(currencyPack.getBezants()))
                            .replace("%keys%", String.valueOf(currencyPack.getKeys()))
                            .replace("%online%", String.valueOf(Bukkit.getOnlinePlayers().size()));
                    entryBuilder.next(formatted);
                }
                return entryBuilder.build();
            }
        }).setUpdateInterval(10L).activate();
        
    }
    
}
