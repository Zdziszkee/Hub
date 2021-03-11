package com.twodevsstudio.hub.configuration;

import com.twodevsstudio.simplejsonconfig.api.Config;
import com.twodevsstudio.simplejsonconfig.interfaces.Configuration;
import lombok.Getter;

import java.util.LinkedList;
import java.util.List;
@Getter
@Configuration("scoreboardconfig.json")
public class ScoreBoardConfiguration extends Config {
    private String title;
    private List<String> lines = new LinkedList<>(getDefault());
    private List<String> getDefault(){
        List<String> list = new LinkedList<>();
        list.add("");
        list.add("&c&lProfile");
        list.add("&7Gender: &9&l%gender%");
        list.add("&7Rank: coming soon");
        list.add("");
        list.add("&a&lWallet");
        list.add("&7Balance: &a%money%$");
        list.add("&7Bezants: &a%bezants%$");
        list.add("&7Treasure Keys: &a%keys%$");
        list.add("");
        list.add("&e&lServer");
        list.add("Lobby: &a%online%");
        list.add("");
        list.add("");
        list.add("&e&lplay.wystern.com");
        return list;
    }
}
