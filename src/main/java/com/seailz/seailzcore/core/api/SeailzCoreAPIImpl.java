package com.seailz.seailzcore.core.api;

import com.seailz.seailzcore.profile.Profile;

import java.util.UUID;

/**
 * Interface for the API for this project
 * @implNote To use the api, please access the methods from {@link SeailzCoreAPI}
 * @author Seailz - <b><u>www.seailz.com</u></b>
 */
public interface SeailzCoreAPIImpl {

    /**
     * Get a profile of a player
     * @param player The player's UUID
     * @return The player's profile
     * @implNote Use this method from {@link SeailzCoreAPI}
     */
    Profile getProfile(UUID player);

    /**
     * Get a players first join date
     * @param player The player's UUID
     * @return The time at which they joined
     */
    long getFirstJoin(UUID player);

    /**
     * <p> Generates a string which shows the player's first join date. </p>
     * Looks similar to this: <b> 15/12/2022 13:28:42 </b>
     *
     * @param player The player's UUID
     * @param monthBeforeDay Whether the string should show the month before the day, or not.
     * @param timezone The timezone you wish to choose. You may pick any from this website: <b><u>https://en.wikipedia.org/wiki/List_of_tz_database_time_zones</u></b>, under the TZ database name within the table.
     *
     * @return The nicely formatted date
     */
    String getFirstJoinNice(UUID player, boolean monthBeforeDay, String timezone);

}
