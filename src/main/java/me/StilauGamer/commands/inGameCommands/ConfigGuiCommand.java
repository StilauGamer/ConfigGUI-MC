package me.StilauGamer.commands.inGameCommands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import me.StilauGamer.CC;
import me.StilauGamer.ConfigManager;
import me.StilauGamer.Main;
import me.mattstudios.mfgui.gui.components.ItemBuilder;
import me.mattstudios.mfgui.gui.guis.Gui;
import me.mattstudios.mfgui.gui.guis.GuiItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;
import java.util.Optional;

@Description("ACF command!")
public class ConfigGuiCommand extends BaseCommand {

    // private
    private Main main;

    // public
    public ConfigGuiCommand (Main Instance) {
        this.main = Instance;
    }

    // Creation Of GUI's
    Gui MainGui = new Gui(5, CC.translate("&aConfigGUI"));
    Gui DiscordGui = new Gui(5, CC.translate("&aDiscord"));

    @CommandAlias("configgui")
    public void onCommand(CommandSender sender, Player p) {

        // Checking if the player is a player or console!
        if (sender instanceof Player) {

            // Has Permission!
            if (p.hasPermission("configgui.open")) {

                // Main GUI

                GuiItem DiscordIntergration = ItemBuilder.from(Material.PAPER).setName(CC.translate("&bDiscord")).asGuiItem(event -> DiscordGui.open(p));

                // ---------------------------------

                // Default Click Action

                MainGui.setDefaultClickAction(event -> {
                    event.setCancelled(true);
                });

                // ---------------------------------

                // Background

                final Optional<Material> material = Optional.ofNullable(Material.matchMaterial(main.getConfig().getString("background")));

                if (!material.isPresent()) {

                    // Message sent to the console!
                    System.out.println(ChatColor.RED + "Background material is wrong! Switched automatically to Black Stained Glass Pane!");
                    // Message sent to the player!
                    p.sendMessage(CC.translate("&cBackground material is wrong! Switched automatically to Black Stained Glass Pane!"));
                    // Setting the new material!
                    main.getConfig().set("background", "BLACK_STAINED_GLASS_PANE");
                    // Saving the config!
                    main.saveConfig();
                }

                MainGui.getFiller().fill(ItemBuilder.from(material.get()).setName(CC.translate("&l")).asGuiItem());
                GuiItem backgroundMaterial = ItemBuilder.from(material.get()).setName(CC.translate("&l")).asGuiItem();

                // ---------------------------------

                // Main
                if (Objects.equals(main.getConfig().get("Discord.Token"), "token")) {
                    // Setting the background material if the discord token is "token"
                    MainGui.setItem(2, 2, backgroundMaterial);
                } else {
                    // Setting the discord intergration button if the token is something else than "token"
                    MainGui.setItem(2, 2, DiscordIntergration);
                }

                // ---------------------------------

                // Opening the Main GUI!
                MainGui.open(p);

            } else {
                // Sending the No-Permission message!
                p.sendMessage(CC.translate(ConfigManager.getMessages().getString("No-Permission")));

            }

        } else {
            // Sending the Console message!
            p.sendMessage(ConfigManager.getMessages().getString("Console"));
        }

    }

}
