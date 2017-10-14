package ca.ualberta.cortland.ignoreString;

import org.bukkit.Server;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.*;

public class PlayerIgnoredStringList implements java.io.Serializable
{
    HashSet<String> ignoredStrings = new HashSet<String>();

    public void AddIgnoredString(String s){
        ignoredStrings.add(s);
    }

    public void ClearAllIgnoredString(){
        ignoredStrings.clear();
    }

    public HashSet<String> getIgnoredStrings() {
        return ignoredStrings;
    }

    public static CommandSender getStringFromUUID(Server UUID){return  UUID.getConsoleSender();}

}
