package me.zdziszkee.hub.gui.settings.logic.visiblity;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class VisibilityManager {
    private final Map<UUID,Boolean> playerVisibility = new HashMap<>();

    private void setPlayerVisibility(Player player, boolean visibility){
        playerVisibility.put(player.getUniqueId(),visibility);
    }

    public void removePlayer(UUID uuid){
        playerVisibility.remove(uuid);
    }
    public void addPlayer(UUID uuid,boolean shouldSeeOtherPlayers){
        this.playerVisibility.put(uuid,shouldSeeOtherPlayers);
    }
    public void enablePlayerVisibility(Player player){
        setPlayerVisibility(player,true);
        Bukkit.getOnlinePlayers().forEach(target -> target.showPlayer(player));
    }
    public void disablePlayerVisibility(Player player){
        setPlayerVisibility(player,false);
        Bukkit.getOnlinePlayers().forEach(target -> target.hidePlayer(player));
    }
    public void addPlayerToHide(Player target){
        for(Map.Entry<UUID,Boolean> entry :playerVisibility.entrySet()){
            if(!entry.getValue()){
                Player player = Bukkit.getPlayer(entry.getKey());
                if(player==null)continue;
                target.hidePlayer(player);
            }
        }
    }
    public void togglePlayerVisibility(Player player){
        boolean playerVisibility = getPlayerVisibility(player);
        if(playerVisibility){
            disablePlayerVisibility(player);
        }else {
            enablePlayerVisibility(player);
        }
    }
    public boolean getPlayerVisibility(Player player){
        return playerVisibility.get(player.getUniqueId());
    }
}
