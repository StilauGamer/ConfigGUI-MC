package me.StilauGamer.events;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;

public class eventPlayerSneak implements Listener {

    @EventHandler
    public void onSneak(PlayerToggleSneakEvent e) {

        Player p = e.getPlayer();

        if (e.isSneaking()) {

            if (!e.isCancelled()) {
                if (e.isSneaking()) {

                    p.sendMessage("debug!");
                    p.setGameMode(GameMode.CREATIVE);

                }

            }
        }

    }

}
