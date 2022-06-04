package com.seailz.seailzcore.core.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seailz.seailzcore.SeailzCore;
import com.seailz.seailzcore.profile.Profile;
import com.seailz.seailzcore.profile.expections.NoProfileExistsException;
import org.bukkit.Bukkit;

import java.io.File;
import java.io.IOException;
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
     */
    @Override
    public Profile getProfile(UUID player) throws NoProfileExistsException {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(
                    new File(SeailzCore.getInstance().getDataFolder(), "/users/" + player), Profile.class
            );
        } catch (IOException e) {
            throw new NoProfileExistsException("No profile exists for the given UUID!");
        }
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
     * @return Something that looks similar to this: <b> 15/12/2022 13:28:42 </b>
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
