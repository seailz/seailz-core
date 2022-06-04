package com.seailz.seailzcore.profile;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.seailz.seailzcore.SeailzCore;
import lombok.Getter;
import lombok.Setter;

import java.sql.*;
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

    /**
     * Saves this object to the database
     */
    public void saveToDatabase() throws JsonProcessingException, SQLException {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(this);

        String connection = "jdbc:mysql://" + SeailzCore.getInstance().getConfig().getString("mysql.ip") + ":" + SeailzCore.getInstance().getConfig().getString("mysql.port") +  "/"
                + SeailzCore.getInstance().getConfig().getString("mysql.db-name");

        Connection conn = DriverManager.getConnection(connection, SeailzCore.getInstance().getConfig().getString("mysql.username"), SeailzCore.getInstance().getConfig().getString("mysql.password"));

        DatabaseMetaData dbm = conn.getMetaData();
        // check if "employee" table is there
        ResultSet tables = dbm.getTables(null, null, "DATA", null);
        if (!tables.next()) {
            Statement createTable = conn.createStatement();
            String create = "CREATE TABLE DATA " +
                    "(player VARCHAR(255), " +
                    " data VARCHAR(255), " +
                    " PRIMARY KEY ( player ))";

            createTable.executeUpdate(create);
        }

        // Extract data from result set

        if (hasRecord(getUUID())) {
            final String REMOVE_PLAYER = "DELETE FROM DATA WHERE player=";
            PreparedStatement removePlayer = conn.prepareStatement(REMOVE_PLAYER + "'" + getUUID() + "';");
            removePlayer.execute();
        }

        final String SET_PREFERENCE = "insert into DATA (player, data) values (?, ?)";

        PreparedStatement preparedStmt = conn.prepareStatement(SET_PREFERENCE);
        preparedStmt.setString(1, getUUID());
        preparedStmt.setString(2, jsonString);

        preparedStmt.execute();
    }

    private boolean hasRecord(String id) throws SQLException {
        String connection = "jdbc:mysql://" + SeailzCore.getInstance().getConfig().getString("mysql.ip") + ":" + SeailzCore.getInstance().getConfig().getString("mysql.port") +  "/"
                + SeailzCore.getInstance().getConfig().getString("mysql.db-name");

        Connection conn = DriverManager.getConnection(connection, SeailzCore.getInstance().getConfig().getString("mysql.username"), SeailzCore.getInstance().getConfig().getString("mysql.password"));
        Statement statement = conn.createStatement();
        String stmt = "SELECT * FROM DATA WHERE player='" + id + "'";
        ResultSet rs = statement.executeQuery(stmt);

        return rs.next();
    }
}

