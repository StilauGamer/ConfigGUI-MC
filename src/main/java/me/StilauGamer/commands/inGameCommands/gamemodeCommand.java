package me.StilauGamer.commands.inGameCommands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Description;
import me.StilauGamer.CC;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Description("GamemodeCommand!")
public class gamemodeCommand extends BaseCommand {

    @CommandAlias("gamemode")
    public void onCommand(CommandSender sender, Player p) {

        if (sender instanceof Player) {

            if (p.hasPermission("configgui.gamemode")) {

                if (getOrigArgs().length == 0) {

                    p.sendMessage(CC.translate("Hello Testing!"));

                } else {

                    if (getOrigArgs().length == 1) {

                        String player = p.getName();
                        String gamemodeMessage = "Successfully changed your gamemode to " + getOrigArgs()[1].toLowerCase() + "!";

                        switch (getOrigArgs()[0].toLowerCase()) {

                                // Survival
                            case "survival":
                            case "s":
                            case "0":
                                p.setGameMode(GameMode.SURVIVAL);
                                p.sendMessage(gamemodeMessage);
                                break;

                                // Creative
                            case "creative":
                            case "c":
                            case "1":
                                p.setGameMode(GameMode.CREATIVE);
                                p.sendMessage(gamemodeMessage);
                                break;

                                // Adventure
                            case "adventure":
                            case "a":
                            case "2":
                                p.setGameMode(GameMode.ADVENTURE);
                                p.sendMessage(gamemodeMessage);
                                break;

                                // Spectator
                            case "spectator":
                            case "sp":
                            case "3":
                                p.setGameMode(GameMode.SPECTATOR);
                                p.sendMessage(gamemodeMessage);
                                break;
                        }

                    }

                }

            }

        }

    }

}
