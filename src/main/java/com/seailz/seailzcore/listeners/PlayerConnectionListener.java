package com.seailz.seailzcore.listeners;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seailz.seailzcore.SeailzCore;
import com.seailz.seailzcore.profile.Profile;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class PlayerConnectionListener implements Listener {

    ObjectMapper objectMapper = new ObjectMapper();

    @EventHandler
    public void playerJoin(PlayerJoinEvent e) throws IOException {
        Player p = e.getPlayer();

        // Profile Management
        File profileFile = new File(SeailzCore.getInstance().getDataFolder(), "/users/" + p.getUniqueId());

        // Check if the player's profile exists
        if (!profileFile.exists()) {
            // File doesn't exist, lets make one!
            profileFile.mkdir();
            Profile newProfile = new Profile(
                    e.getPlayer().getName(), p.getUniqueId(), new ArrayList<>(Arrays.asList(p.getName()))
            );

            // Save the profile into JSON
            objectMapper.writeValue(profileFile, newProfile);
        } else {
            // Update the profile
            Profile profile = objectMapper.readValue(profileFile, Profile.class);
            // If the old profile's previous names doesn't contain the player's current name, we need to add it.
            if (!profile.getPreviousNames().contains(p.getName())) {
                // Update the values
                profile.addPreviousName(p.getName());
                profile.setName(p.getName());

                // Write it
                objectMapper.writeValue(profileFile, profile);
            }

            // If nothing changed, no need to update the JSON file
        }
    }
}
