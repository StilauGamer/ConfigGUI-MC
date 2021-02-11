package me.StilauGamer.Listener;

import me.StilauGamer.CC;
import me.StilauGamer.ConfigManager;
import me.StilauGamer.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.Objects;

public class chatFormatListener implements Listener {

    // private
    private Main main;

    // public
    public chatFormatListener (Main Instance) {
        main = Instance;
    }

    // ChatFormat
    @EventHandler
    public void chatFormat (AsyncPlayerChatEvent e) {

        // Getting the player!
        Player p = e.getPlayer();

        // Getting the message!
        String eventMessage = e.getMessage();

        String chatformat = CC.translate(ConfigManager.getData().getString(Main.getChat().getPrimaryGroup(p)));
        chatformat = chatformat.replace("<player>", CC.translate("%1$s"))
                .replace("<prefix>", CC.translate(Main.getChat().getPlayerPrefix(p)))
                .replace("<suffix>", CC.translate(Main.getChat().getPlayerSuffix(p)))
                .replace("<message>", CC.translate("%2$s"));

        e.setMessage(eventMessage);
        e.setFormat(chatformat);
    }

}
