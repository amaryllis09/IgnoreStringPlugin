package ca.ualberta.cortland.ignoreString;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

public class chatListener implements Listener
{
    ignoreString session;

    chatListener(ignoreString _session){
        session = _session;
    }
    @EventHandler
    public void onPlayerSentChatMessage(AsyncPlayerChatEvent event)
    {
        for(UUID uuid : session.GetPlayersWhoIgnoredString(event.getMessage()))
        {
            event.getRecipients().remove(session.getServer().getPlayer(uuid));
        }
    }

}
