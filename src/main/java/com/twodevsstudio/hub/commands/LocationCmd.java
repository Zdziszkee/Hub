package com.twodevsstudio.hub.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Subcommand;
import com.twodevsstudio.hub.configuration.ChooseGameGUIConfiguration;
import com.twodevsstudio.hub.configuration.GeneralConfiguration;
import com.twodevsstudio.hub.gui.PositionalGUIItemStack;
import com.twodevsstudio.hub.util.Coordinates;
import lombok.RequiredArgsConstructor;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.Optional;

@CommandAlias( "location" )
@RequiredArgsConstructor
public class LocationCmd extends BaseCommand {
    private final ChooseGameGUIConfiguration chooseGameGUIConfiguration;
    private final GeneralConfiguration generalConfiguration;
    
    @Subcommand( "set" )
    public void setLocation(Player player, String[] args) {
        
        if (args.length != 1) {
            return;
        }
        
        Map<Integer, Coordinates> slotCoordinatesMap = chooseGameGUIConfiguration.getSlotCoordinatesMap();
        Optional<Coordinates> optionalCoordinates = slotCoordinatesMap.values()
                .stream()
                .filter(coordinates -> coordinates.getName().equalsIgnoreCase(args[0]))
                .findAny();
        if (optionalCoordinates.isPresent()) {
            generalConfiguration.getSettingLocationFailMessage()
                    .forEach(s -> player.sendMessage(
                            ChatColor.translateAlternateColorCodes('&', s.replace("%name%", args[0]))));
            
        }
        Location loc = player.getLocation();
        for (int i = 0 ; i < 44 ; i++) {
            if (!slotCoordinatesMap.containsKey(i)) {
                slotCoordinatesMap.put(i, new Coordinates(args[0], loc.getX(), loc.getY(), loc.getZ()));
                chooseGameGUIConfiguration.getGuiItemStacks()
                        .add(new PositionalGUIItemStack(i, args[0], Material.PAPER, "lore"));
                chooseGameGUIConfiguration.save();
                generalConfiguration.getSettingLocationSuccesMessage()
                        .forEach(s -> player.sendMessage(
                                ChatColor.translateAlternateColorCodes('&', s.replace("%name%", args[0]))));
                return;
            }
        }
        generalConfiguration.getSettingLocationFailMessage()
                .forEach(s -> player.sendMessage(
                        ChatColor.translateAlternateColorCodes('&', s.replace("%name%", args[0]))));
        chooseGameGUIConfiguration.save();
        chooseGameGUIConfiguration.reload();
        
    }
    
    @Subcommand( "delete" )
    public void deleteLoc(CommandSender commandSender, String[] args) {
        
        if (args.length != 1) {
            return;
        }
        Map<Integer, Coordinates> slotCoordinatesMap = chooseGameGUIConfiguration.getSlotCoordinatesMap();
        Optional<Map.Entry<Integer, Coordinates>> coordinatesEntry = slotCoordinatesMap.entrySet()
                .stream()
                .filter(integerCoordinatesEntry -> integerCoordinatesEntry.getValue()
                        .getName()
                        .equalsIgnoreCase(args[0]))
                .findAny();
        if (!coordinatesEntry.isPresent()) {
            generalConfiguration.getLocationNotFoundMessage()
                    .forEach(s -> commandSender.sendMessage(
                            ChatColor.translateAlternateColorCodes('&', s.replace("%name%", args[0]))));
            return;
        }
        int key = coordinatesEntry.get().getKey();
        slotCoordinatesMap.remove(key);
        Optional<PositionalGUIItemStack> first = chooseGameGUIConfiguration.getGuiItemStacks()
                .stream()
                .filter(positionalGUIItemStack -> positionalGUIItemStack.getSlot() == key)
                .findFirst();
        first.ifPresent(
                positionalGUIItemStack -> chooseGameGUIConfiguration.getGuiItemStacks().remove(positionalGUIItemStack));
        chooseGameGUIConfiguration.save();
        chooseGameGUIConfiguration.reload();
        generalConfiguration.getDeleteLocationSuccess()
                .forEach(s -> commandSender.sendMessage(
                        ChatColor.translateAlternateColorCodes('&', s.replace("%name%", args[0]))));
    }
    
    @Subcommand( "tp" )
    public void tpToLoc(Player player, String[] args) {
        
        if (args.length != 1) {
            return;
        }
        Map<Integer, Coordinates> slotCoordinatesMap = chooseGameGUIConfiguration.getSlotCoordinatesMap();
        Optional<Map.Entry<Integer, Coordinates>> coordinatesEntry = slotCoordinatesMap.entrySet()
                .stream()
                .filter(integerCoordinatesEntry -> integerCoordinatesEntry.getValue()
                        .getName()
                        .equalsIgnoreCase(args[0]))
                .findAny();
        if (!coordinatesEntry.isPresent()) {
            generalConfiguration.getLocationNotFoundMessage()
                    .forEach(s -> player.sendMessage(
                            ChatColor.translateAlternateColorCodes('&', s.replace("%name%", args[0]))));
            return;
        }
        Coordinates value = coordinatesEntry.get().getValue();
        player.teleport(new Location(player.getWorld(), value.getX(), value.getY(), value.getZ()));
        
    }
}
