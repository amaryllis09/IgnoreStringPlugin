package ca.ualberta.cortland.ignoreString;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ClearCommandParser implements CommandExecutor {

    ignoreString session;
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player sender = (Player)commandSender;
        session.ClearIgnoredString(sender.getUniqueId());
        return false;
    }

    public ClearCommandParser(ignoreString _session)
    {
        session = _session;
    }
}
