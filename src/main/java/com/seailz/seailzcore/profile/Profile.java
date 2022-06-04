package com.seailz.seailzcore.profile;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.UUID;

/**
 * @author Seailz
 */
@Getter
@Setter
public class Profile {

    /**
     * <P>Their name</P>
     * <p><b>Usually</b> defined by their Minecraft username</p>
     */
    private String name;
    /**
     * <P>Their UUID</P>
     * <p><b>Usually</b> defined by their Minecraft UUID</p>
     */
    private final String UUID;
    /**
     * <P>Their previous names</P>
     * <p><b>Usually</b> defined by their previous names within Minecraft</p>
     */
    private final ArrayList<String> previousNames;

    /**
     * Create a profile
     * @param name Their name
     * @param uuid Their UUID
     */
    public Profile(
            @JsonProperty(value = "IGN") String name,
            @JsonProperty(value = "UUID") UUID uuid,
            @JsonProperty(value = "Previous Names") ArrayList<String> previousNames
    ) {
        this.UUID = uuid.toString();
        setName(name);

        this.previousNames = previousNames;
    }

    /**
     * Add a previous name
     * @param s the previous name you would like to add
     */
    public void addPreviousName(String s) {
        previousNames.add(s);
    }
}

