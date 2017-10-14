package ca.ualberta.cortland.ignoreString;

import org.bukkit.Server;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class ignoreString extends JavaPlugin
{
    ConcurrentHashMap<UUID, PlayerIgnoredStringList>  ignoredStringsByUUID = new ConcurrentHashMap<UUID, PlayerIgnoredStringList>();

    @Override
    public void onDisable()
    {
        try
        {
            FileOutputStream f_out = new FileOutputStream("plugins/ignoredStrings.data");
            ObjectOutputStream obj_out = new ObjectOutputStream(f_out);
            obj_out.writeObject(ignoredStringsByUUID);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    @Override
    public void onEnable()
    {
        chatListener chatInterceptor = new chatListener(this);
        CommandParser commandParser = new CommandParser(this);
        ClearCommandParser clearCommandParser = new ClearCommandParser(this);

        getServer().getPluginManager().registerEvents(chatInterceptor, this );

        this.getCommand("ignoreString").setExecutor(commandParser);
        this.getCommand("clearIgnoredStrings").setExecutor(clearCommandParser);

        //De-serialize from data file
       try
       {
           FileInputStream f_in = new FileInputStream("plugins/ignoredStrings.data");
           ObjectInputStream obj_in = new ObjectInputStream(f_in);
           Object obj = obj_in.readObject();
           ignoredStringsByUUID = (ConcurrentHashMap)obj;
           commandParser.OnlineUUIDs = this.getServer();
       }
       catch (Exception e)
       {
           e.printStackTrace();
       }
    }

    public  void AddNewIgnoredString(UUID player, String toIgnore)
    {
        ignoredStringsByUUID.putIfAbsent(player, new PlayerIgnoredStringList());
        ignoredStringsByUUID.get(player).AddIgnoredString(toIgnore);
    }


    public  void ClearIgnoredString(UUID player)
    {
        ignoredStringsByUUID.putIfAbsent(player, new PlayerIgnoredStringList());
        ignoredStringsByUUID.get(player).ClearAllIgnoredString();
    }

    public List<UUID> GetPlayersWhoIgnoredString(String chatMessage)
    {
        List<UUID> playersWhoIgnoredString = new ArrayList<>();

        for (UUID u : ignoredStringsByUUID.keySet())
        {
            for (String ignored : ignoredStringsByUUID.get(u).getIgnoredStrings())
            {
                if (chatMessage.contains(ignored))
                {
                    playersWhoIgnoredString.add(u);
                }
            }
        }
        return playersWhoIgnoredString;
    }

    public void CheckIgnoredStrings(Server chatMessage, String toCheck)
    {
        chatMessage.dispatchCommand(PlayerIgnoredStringList.getStringFromUUID(chatMessage), toCheck);
    }
}
