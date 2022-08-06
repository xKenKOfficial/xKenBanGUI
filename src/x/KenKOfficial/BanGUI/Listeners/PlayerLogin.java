package x.KenKOfficial.BanGUI.Listeners;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import x.KenKOfficial.BanGUI.Apis.API;
import x.KenKOfficial.BanGUI.Basic.Main;
import x.KenKOfficial.BanGUI.Utils.ChatUtil;
import x.KenKOfficial.BanGUI.Utils.TimeUtil;

import java.io.File;
import java.util.List;

public class PlayerLogin implements Listener
{
    @EventHandler
    public void onLogin(final PlayerLoginEvent e) {
        final Player p = e.getPlayer();
        final File file = new File("plugins/" + Main.getPlugin().getName() + "/Zbanowani/" + p.getName() + ".yml");
        if (file.exists()) {
            final YamlConfiguration yml = YamlConfiguration.loadConfiguration(file);
            if (yml.getString("Ban_Type").equals("Ban")) {
                final List<String> list = (List<String>)Main.getPlugin().getConfig().getStringList("bangui_liststring.ban");
                String msg = "";
                for (int i = 0; i < list.size(); ++i) {
                    msg = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(msg)))))) + list.get(i);
                    if (i <= list.size() - 2) {
                        msg = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(msg)))))) + "{N}";
                    }
                }
                msg = msg.replace("&", "§");
                msg = msg.replace("{N}", "\n");
                msg = msg.replace("{POWOD}", API.getBan(p.getName()).split(";")[1]);
                msg = msg.replace("{ADMIN}", API.getBan(p.getName()).split(";")[2]);
                e.disallow(PlayerLoginEvent.Result.KICK_OTHER, ChatUtil.fixColor(msg));
            }
            else if (yml.getString("Ban_Type").equals("Tempban")) {
                if (System.currentTimeMillis() > Long.valueOf(API.getBan(p.getName()).split(";")[2])) {
                    API.setUnban(p.getName());
                }
                else {
                    final List<String> list = (List<String>)Main.getPlugin().getConfig().getStringList("bangui_liststring.tempban");
                    String msg = "";
                    for (int i = 0; i < list.size(); ++i) {
                        msg = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(msg)))))) + list.get(i);
                        if (i <= list.size() - 2) {
                            msg = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(msg)))))) + "{N}";
                        }
                    }
                    msg = msg.replace("&", "§");
                    msg = msg.replace("{N}", "\n");
                    msg = msg.replace("{ADMIN}", API.getBan(p.getName()).split(";")[1]);
                    msg = msg.replace("{DATA}", TimeUtil.getDate(Long.valueOf(API.getBan(p.getName()).split(";")[2])));
                    msg = msg.replace("{POWOD}", API.getBan(p.getName()).split(";")[3]);
                    e.disallow(PlayerLoginEvent.Result.KICK_OTHER, ChatUtil.fixColor(msg));
                }
            }
        }
    }
}
