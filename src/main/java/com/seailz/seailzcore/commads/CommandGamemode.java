package com.seailz.seailzcore.commads;

import com.seailz.seailzcore.core.Locale;
import com.seailz.seailzcore.core.utils.PlayerManager;
import games.negative.framework.command.Command;
import games.negative.framework.command.SubCommand;
import games.negative.framework.command.annotation.CommandInfo;
import games.negative.framework.message.FrameworkMessage;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandInfo(
        name = "gamemode",
        permission = "seailzcore.command.gamemode",
        aliases = {"gm"}
)
public class CommandGamemode extends Command {

    public CommandGamemode() {
        this.addSubCommands(
                new SubGamemodeAdventure(),
                new SubGamemodeCreative(),
                new SubGamemodeSurvival(),
                new SubGamemodeSpectator()
        );
    }

    @Override
    public void onCommand(CommandSender sender, String[] args) {}
}

@CommandInfo(
        name = "survival",
        aliases = {"s"},
        shortCommands = {"gms"},
        permission = "seailzcore.gamemode.survival"
)
class SubGamemodeSurvival extends SubCommand {
    @Override
    public void onCommand(CommandSender sender, String[] args) {
        if (getPlayer(args[1]) == null && args.length == 3)
            Locale.INVALID_PLAYER.send(sender);
        else if (getPlayer(args[1]) != null && args.length == 3)
            new PlayerManager(getPlayer(args[1])).setGamemode(GameMode.SURVIVAL);

        if (args.length == 2) {
            if (!(sender instanceof Player)) {
                FrameworkMessage.COMMAND_CANNOT_USE_THIS_AS_CONSOLE.send(sender);
                return;
            }

            new PlayerManager((Player) sender).setGamemode(GameMode.SURVIVAL);
        }
    }
}

@CommandInfo(
        name = "creative",
        aliases = {"c"},
        shortCommands = {"gmc"},
        permission = "seailzcore.gamemode.creative"
)
class SubGamemodeCreative extends SubCommand {
    @Override
    public void onCommand(CommandSender sender, String[] args) {
        if (getPlayer(args[1]) == null && args.length == 3)
            Locale.INVALID_PLAYER.send(sender);
        else if (getPlayer(args[1]) != null && args.length == 3)
            new PlayerManager(getPlayer(args[1])).setGamemode(GameMode.CREATIVE);

        if (args.length == 2) {
            if (!(sender instanceof Player)) {
                FrameworkMessage.COMMAND_CANNOT_USE_THIS_AS_CONSOLE.send(sender);
                return;
            }

            new PlayerManager((Player) sender).setGamemode(GameMode.CREATIVE);
        }
    }
}

@CommandInfo(
        name = "adventure",
        aliases = {"a"},
        shortCommands = {"gma"}
)
class SubGamemodeAdventure extends SubCommand {
    @Override
    public void onCommand(CommandSender sender, String[] args) {
        if (getPlayer(args[1]) == null && args.length == 3)
            Locale.INVALID_PLAYER.send(sender);
        else if (getPlayer(args[1]) != null && args.length == 3)
            new PlayerManager(getPlayer(args[1])).setGamemode(GameMode.ADVENTURE);

        if (args.length == 2) {
            if (!(sender instanceof Player)) {
                FrameworkMessage.COMMAND_CANNOT_USE_THIS_AS_CONSOLE.send(sender);
                return;
            }

            new PlayerManager((Player) sender).setGamemode(GameMode.ADVENTURE);
        }
    }
}

@CommandInfo(
        name = "spectator",
        aliases = {"sp"},
        shortCommands = {"gmsp"}
)
class SubGamemodeSpectator extends SubCommand {
    @Override
    public void onCommand(CommandSender sender, String[] args) {
        if (getPlayer(args[1]) == null && args.length == 3)
            Locale.INVALID_PLAYER.send(sender);
        else if (getPlayer(args[1]) != null && args.length == 3)
            new PlayerManager(getPlayer(args[1])).setGamemode(GameMode.SPECTATOR);

        if (args.length == 2) {
            if (!(sender instanceof Player)) {
                FrameworkMessage.COMMAND_CANNOT_USE_THIS_AS_CONSOLE.send(sender);
                return;
            }

            new PlayerManager((Player) sender).setGamemode(GameMode.SPECTATOR);
        }
    }
}
