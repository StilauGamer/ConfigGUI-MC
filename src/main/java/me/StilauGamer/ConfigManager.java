package me.StilauGamer;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigManager {

    // private
    public static Main plugin = Main.getPlugin(Main.class);

    // Files And File Configs
    public static FileConfiguration datacfg;
    public static File datafile;

    public static FileConfiguration messagecfg;
    public static File messagefile;
    // --------------------------------------

    // Messages Setup!
    public static void setupMessages() {

        if (!plugin.getDataFolder().exists()) {

            plugin. getDataFolder().mkdir();

        }

        // Getting the Messages.yml and adding a name!
        messagefile = new File(plugin.getDataFolder(), "messages.yml");

        if (!messagefile.exists()) {

            try {
                // Message sent when the Messages.yml got created!
                System.out.println(ChatColor.GREEN + "The Messages.yml has been created!");

                // The Messages.yml getting created!
                messagefile.createNewFile();

            } catch (IOException x) {
                // Message sent when the Messages.yml couldn't be created!
                System.out.println(ChatColor.RED + "Could not create Messages.yml!");
            }
        } else {
            // Message sent when the Messages.yml is all ready created!
            System.out.println(ChatColor.GREEN + "The Messages.yml is all ready created!");

        }

        messagecfg = YamlConfiguration.loadConfiguration(messagefile);

    }

    // Data Setup!
    public static void setupData() {

        if (!plugin.getDataFolder().exists()) {

            plugin.getDataFolder().mkdir();

        }

        // Getting the Data.yml and adding a name!
        datafile = new File(plugin.getDataFolder(), "data.yml");

        if (!datafile.exists()) {

            try {
                // Message sent when the Data.yml got created!
                System.out.println(ChatColor.GREEN + "The Data.yml has been created!");

                // The Data.yml getting created!
                datafile.createNewFile();

            } catch (IOException x) {
            // Message sent when the Data.yml couldn't be created!
                System.out.println(ChatColor.RED + "Could not create Data.yml!");

            }

        } else {
            // Message sent when the Data.yml is all ready created!
            System.out.println(ChatColor.GREEN + "The Data.yml is all ready created!");

        }

        datacfg = YamlConfiguration.loadConfiguration(datafile);

    }

    // Getting the messagescfg!
    public static FileConfiguration getMessages() {

        return messagecfg;

    }

    // Getting the datacfg!
    public static FileConfiguration getData() {

        return datacfg;

    }

    // Saving the Messages.yml!
    public static void saveMessages() {

        try {
            // Saving the file!
            messagecfg.save(messagefile);

            // Message sent when the Messages.yml has been saved!
            System.out.println(ChatColor.AQUA + "The Messages.yml has been saved!");
        } catch (IOException x) {
            // Message sent when the Messages.yml couldn't be saved!
            System.out.println(ChatColor.RED + "Could not save Data.yml!");

        }

    }

    // Saving the Data.yml!
    public static void saveData() {

        try {
            // Saving the file!
            datacfg.save(datafile);

            // Message sent when the Data.yml has been saved!
            System.out.println(ChatColor.AQUA + "The Data.yml has been saved!");

        } catch (IOException x) {
            // Message sent when the Data.yml couldn't be saved!
            System.out.println(ChatColor.RED + "Could not save Data.yml!");
        }

    }

    // Reloading the Messages.yml!
    public static void reloadMessages() {

        // Reloading the Messages.yml!
        messagecfg = YamlConfiguration.loadConfiguration(messagefile);

        // Message sent when the Messages.yml has been reloaded!
        System.out.println(ChatColor.BLUE + "The Messages.yml has been reloaded!");
    }

    // Reloading the Data.yml!
    public static void reloadData() {

        // Reloading the Data.yml
        datacfg = YamlConfiguration.loadConfiguration(datafile);

        // Message sent when the Data.yml has been reloaded!
        System.out.println(ChatColor.BLUE + "The Data.yml has been reloaded!");

    }

    // Adding Default Messages to Messages.yml!
    public static void Messages() {
        getMessages().addDefault("No-Permission", "&cYou do not have permission to execute this command!");
        getMessages().addDefault("Console", "only in-game player can do this command!");
    }

}
