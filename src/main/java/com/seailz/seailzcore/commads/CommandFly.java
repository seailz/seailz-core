package com.seailz.seailzcore.commads;

import com.seailz.seailzcore.core.Locale;
import com.seailz.seailzcore.core.utils.PlayerManager;
import games.negative.framework.command.Command;
import games.negative.framework.command.annotation.CommandInfo;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;

@CommandInfo(
        name = "fly",
        permission = "seailzcore.commands.fly"
)
public class CommandFly extends Command {
    @Override
    public void onCommand(CommandSender sender, String[] args) {
        if (args.length != 1) {
            PlayerManager playerManager = new PlayerManager((Player) sender);
            playerManager.toggleFly();
            return;
        }

        Player target = getPlayer(args[0]);
        if (target == null) {
            Locale.INVALID_PLAYER.send(sender);
            return;
        }

        PlayerManager playerManager = new PlayerManager(target);
        playerManager.toggleFly();
    }
}
