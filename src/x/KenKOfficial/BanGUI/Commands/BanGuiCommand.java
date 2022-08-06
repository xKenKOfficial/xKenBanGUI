package x.KenKOfficial.BanGUI.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import x.KenKOfficial.BanGUI.Apis.API;
import x.KenKOfficial.BanGUI.Basic.Main;
import x.KenKOfficial.BanGUI.Utils.ChatUtil;

public class BanGuiCommand implements CommandExecutor
{
    private static final String PREFIX = Main.getPlugin().getConfig().getString("prefix");
    private static final String NO_PERM = Main.getPlugin().getConfig().getString("no_permission");
    private static final String WRONG_ARGS = Main.getPlugin().getConfig().getString("wrong_args");
    private static final String OFFLINE_PLAYER = Main.getPlugin().getConfig().getString("offline_player");

    private static final String COMMAND_USAGE = Main.getPlugin().getConfig().getString("bangui_command_usage");

    @Override
    public boolean onCommand(final CommandSender Sender, final Command c, final String s, final String[] args) {
        if(!Sender.hasPermission("xkenbangui.admin.bangui")) {
            Sender.sendMessage(ChatUtil.fixColor(PREFIX + " " + NO_PERM));
            return false;
        }
        if(Sender instanceof Player) {
            if(args.length < 1) {
                Sender.sendMessage(ChatUtil.fixColor(PREFIX + " " + COMMAND_USAGE));
                return false;
            } else if(args.length == 1) {
                final Player cel = Bukkit.getPlayer(args[0]);
                if(cel == null) {
                    Sender.sendMessage(ChatUtil.fixColor(PREFIX + " " + OFFLINE_PLAYER));
                    return false;
                }
                API.setGui((Player)Sender, cel);
                return false;
            } else {
                Sender.sendMessage(ChatUtil.fixColor(PREFIX + " " + WRONG_ARGS));
            }
        } else {
            Sender.sendMessage(ChatColor.DARK_RED + "Tej komendy nie mozna uzywac w konsoli!");
        }
        return false;
    }
}
