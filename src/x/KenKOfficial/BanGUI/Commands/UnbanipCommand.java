package x.KenKOfficial.BanGUI.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import x.KenKOfficial.BanGUI.Apis.API;
import x.KenKOfficial.BanGUI.Basic.Main;
import x.KenKOfficial.BanGUI.Listeners.InventoryClick;
import x.KenKOfficial.BanGUI.Utils.ChatUtil;

public class UnbanipCommand implements CommandExecutor
{
    private static final String PREFIX = Main.getPlugin().getConfig().getString("prefix");
    private static final String NO_PERM = Main.getPlugin().getConfig().getString("no_permission");
    private static final String WRONG_ARGS = Main.getPlugin().getConfig().getString("wrong_args");

    private static final String COMMAND_USAGE = Main.getPlugin().getConfig().getString("bangui_unbanip_command_usage");
    private static final String UNBANNED_IP = Main.getPlugin().getConfig().getString("bangui_unbanip_unbanned_ip");
    private static final String WRONG_IP = Main.getPlugin().getConfig().getString("bangui_unbanip_wrong_ip");

    @Override
    public boolean onCommand(final CommandSender Sender, final Command Cmd, final String Label, final String[] args) {
        if (!Sender.hasPermission("xkenbangui.admin.unbanip")) {
            Sender.sendMessage(ChatUtil.fixColor(PREFIX + " " + NO_PERM));
            return false;
        }
        if (args.length < 1) {
            Sender.sendMessage(ChatUtil.fixColor(PREFIX + " " + COMMAND_USAGE));
            return false;
        }
        if (args.length == 1) {
            if (InventoryClick.ipValidity.matcher(args[0]).matches()) {
                API.setUnbanIP(args[0]);
                Sender.sendMessage(ChatUtil.fixColor(PREFIX + " " + UNBANNED_IP.replace("{IP}", args[0])));
            } else {
                Sender.sendMessage(ChatUtil.fixColor(PREFIX + " " + WRONG_IP));
            }
        }
        return false;
    }
}
