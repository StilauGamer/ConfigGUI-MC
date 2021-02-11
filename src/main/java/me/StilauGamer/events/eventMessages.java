package me.StilauGamer.events;

import me.StilauGamer.CC;
import me.StilauGamer.ConfigManager;
import me.StilauGamer.Main;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.ArrayList;

public class eventMessages implements Listener {

    // private
    private Main main;

    // public
    public eventMessages (Main Instance) {
        main = Instance;
    }

    // ArrayLists
    public static ArrayList<Player> ConsoleMessage = new ArrayList<>();

    @EventHandler
    public void ConsoleMessage(AsyncPlayerChatEvent e) {

        // Get the player!
        Player p = e.getPlayer();

        if (eventMessages.ConsoleMessage.contains(p)) {

            // Get the message!
            String message = e.getMessage();

            // Remove the Player from the event!
            eventMessages.ConsoleMessage.remove(p);

            // Setting the new message!
            ConfigManager.getMessages().set("Console", message);
            ConfigManager.saveMessages();

            // Cancelling the event!
            e.setCancelled(true);

            // Message sent to player!
            p.sendMessage(CC.translate("&aSuccessfully changed the Console Message to: " + message));

            // Discord Intergration (Under Development)
            if (main.getConfig().getBoolean("Discord.Notifications.MessageChanged")) {



            }

        }

    }

}
