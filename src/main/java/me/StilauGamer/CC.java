package me.StilauGamer;

import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

public class CC {

    // Chat Colors with ColorCodes
    public static String translate (String input) {

        // return
        return ChatColor.translateAlternateColorCodes('&', input);
    }

    // Strips the ColorCodes
    public static String strip (String input) {

        // return
        return ChatColor.stripColor(input);
    }

    // Gets the String and Text
    public static List<String> translate (List<String> text) {

        // Creation of an Array List
        List<String> messages = new ArrayList<>();

        // For statement
        for (String string : text) {
            messages.add(translate(string));
        }

        // return
        return messages;

    }

}
