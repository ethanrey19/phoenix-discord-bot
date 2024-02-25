package me.ethan.config;

import java.util.HashMap;
import java.util.Map;

public class Commands {

    public static Map<String, Integer> getCommands() {
        Map<String, Integer> commands = new HashMap<>();
        commands.put("wockky",10);
        commands.put("esex", 11);
        return commands;
    }
}
