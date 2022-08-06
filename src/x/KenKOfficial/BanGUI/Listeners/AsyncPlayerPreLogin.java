package x.KenKOfficial.BanGUI.Listeners;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

import x.KenKOfficial.BanGUI.Basic.Main;
import x.KenKOfficial.BanGUI.Utils.ChatUtil;

import java.io.File;
import java.util.List;

public class AsyncPlayerPreLogin implements Listener
{
    @EventHandler
    public void onLogin(final AsyncPlayerPreLoginEvent e) {
        final File file = new File("plugins/" + Main.getPlugin().getName() + "/Zbanowane-IP/" + e.getAddress().getHostAddress() + ".yml");
        if (file.exists()) {
            final YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file);
            final List<String> ban = (List<String>) Main.getPlugin().getConfig().getStringList("bangui_liststring.ban_ip");
            String msg = "";
            for (int i = 0; i < ban.size(); ++i) {
                msg = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(msg)))))) + ban.get(i);
                if (i <= ban.size() - 2) {
                    msg = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(msg)))))) + "{N}";
                }
            }
            msg = msg.replace("{POWOD}", yaml.getString("Reason"));
            msg = msg.replace("{ADMIN}", yaml.getString("Admin"));
            e.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, ChatUtil.fixColor(msg));
        }
    }
}
