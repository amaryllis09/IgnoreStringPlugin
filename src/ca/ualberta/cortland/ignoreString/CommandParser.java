package ca.ualberta.cortland.ignoreString;

import com.mysql.fabric.xmlrpc.base.Array;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;


public class CommandParser implements CommandExecutor {

    ignoreString session;
    Server OnlineUUIDs;

    public CommandParser(ignoreString _session)
    {
        session = _session;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] strings) {

        if(!(commandSender instanceof Player))
        {
            return  false;
        }

        Player sender = (Player)commandSender;

        session.AddNewIgnoredString(sender.getUniqueId(), strings[0]);

        //Parse input for serialization later
        String input = "";
        for (String i: Arrays.copyOfRange(strings, 1, strings.length))
        {
            input += (i + " ");
        }
        session.CheckIgnoredStrings(OnlineUUIDs, input);


        return true;
    }


}
