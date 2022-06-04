package com.seailz.seailzcore.core.api;

import com.seailz.seailzcore.profile.Profile;
import org.bukkit.Bukkit;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.UUID;

/**
 * API for this project
 * @author Seailz
 */
public class SeailzCoreAPI implements SeailzCoreAPIImpl {

    /**
     * Get a profile of a player
     * @param player The player's UUID
     * @return The player's profile
     * @implNote Use this method from {@link SeailzCoreAPI}
     */
    @Override
    public Profile getProfile(UUID player) {
        // TODO: make this method return something
        return null;
    }

    /**
     * Get a players first join date
     * @param player The player's UUID
     * @return The time at which they joined
     */
    @Override
    public long getFirstJoin(UUID player) {
        return Bukkit.getOfflinePlayer(player).getFirstPlayed();
    }

    /**
     * <p> Generates a string which shows the player's first join date. </p>
     * Looks similar to this: <b> 15/12/2022 13:28:42 </b>
     *
     * @param player The player's UUID
     * @param monthBeforeDay Whether the string should show the month before the day, or not.
     * @param timezone The timezone you wish to choose. You may pick any from this website: <b><u>https://en.wikipedia.org/wiki/List_of_tz_database_time_zones</u></b>, under the TZ database name within the table.
     *
     * @return
     */
    @Override
    public String getFirstJoinNice(UUID player, boolean monthBeforeDay, String timezone) {
        Date date = new Date(getFirstJoin(player));

        DateFormat format;
        if (monthBeforeDay)
             format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        else
            format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        format.setTimeZone(TimeZone.getTimeZone(timezone));

        return format.format(date);
    }
}
