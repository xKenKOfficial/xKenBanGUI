package x.KenKOfficial.BanGUI.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import x.KenKOfficial.BanGUI.Apis.API;
import x.KenKOfficial.BanGUI.Basic.Main;
import x.KenKOfficial.BanGUI.Utils.ChatUtil;

public class UnbanCommand implements CommandExecutor
{
    private static final String PREFIX = Main.getPlugin().getConfig().getString("prefix");
    private static final String NO_PERM = Main.getPlugin().getConfig().getString("no_permission");
    private static final String WRONG_ARGS = Main.getPlugin().getConfig().getString("wrong_args");

    private static final String COMMAND_USAGE = Main.getPlugin().getConfig().getString("bangui_unban_command_usage");
    private static final String PLAYER_DONT_BANNED = Main.getPlugin().getConfig().getString("bangui_unban_player_not_banned");

    @Override
    public boolean onCommand(final CommandSender sender, final Command cmd, final String s, final String[] args) {
        if (!sender.hasPermission("xkenbangui.admin.unban")) {
            sender.sendMessage(ChatUtil.fixColor(PREFIX + " " + NO_PERM));
            return false;
        }
        if (args.length == 1) {
            final String nick = args[0];
            final String admin = sender.getName();
            if (API.getBan(nick) != null) {
                API.setUnban(nick);
                String msg = Main.getPlugin().getConfig().getString("bangui_unban_broadcast_message");
                msg = msg.replace("&", "§");
                msg = msg.replace("{GRACZ}", nick);
                msg = msg.replace("{ADMIN}", admin);
                Bukkit.broadcastMessage(ChatUtil.fixColor(PREFIX + " " + msg));
            }
            else {
                sender.sendMessage(ChatUtil.fixColor(PREFIX + " " + PLAYER_DONT_BANNED));
            }
        }
        else {
            sender.sendMessage(ChatUtil.fixColor(PREFIX + " " + COMMAND_USAGE));
        }
        return true;
    }
}
