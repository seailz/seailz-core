package com.seailz.seailzcore.core.utils;

import com.seailz.seailzcore.core.Locale;
import lombok.Setter;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

/**
 * Class to manage players
 * @author Seailz
 */
@Setter
public class PlayerManager {

    private Player player;

    /**
     * Create a player manager
     * @param player The player you would like to manage
     */
    public PlayerManager(Player player) {
        setPlayer(player);
    }

    // FLY

    /**
     * Toggles a player's fly
     */
    public void toggleFly() {
       setFly(!player.getAllowFlight());
    }

    /**
     * Sets a player's fly mode
     * @param on on/off
     */
    private void setFly(boolean on) {
        if (on) {
            player.setFlying(true);
            player.setAllowFlight(true);

            Locale.FLIGHT_TOGGLED.replace("%status%", "on").send(player);
        } else {
            player.setFlying(false);
            player.setAllowFlight(false);

            Locale.FLIGHT_TOGGLED.replace("%status%", "off").send(player);
        }
    }

    // GAMEMODE

    /**
     * Sets a player's gamemode
     * @param gamemode The gamemode which you wat to swap to
     */
    public void setGamemode(GameMode gamemode) {
        player.setGameMode(gamemode);
        Locale.GAMEMODE_SWITCHED.replace("%gamemode%", gamemode.toString().toLowerCase()).send(player);
    }


}
