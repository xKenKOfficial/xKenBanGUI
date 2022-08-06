package x.KenKOfficial.BanGUI.Apis;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import x.KenKOfficial.BanGUI.Basic.Main;
import x.KenKOfficial.BanGUI.Files.ActionFile;
import x.KenKOfficial.BanGUI.Guis.GUI;

import java.io.File;
import java.io.IOException;

public class API
{
    private static ActionFile af;

    public static void setGui(Player admin, Player cel)
    {
        getDataFolder().getFile().set(admin.getName() + ".target_player", cel.getName());
        getDataFolder().saveData();
        GUI.getMenu(admin);
    }

    public static void clearGui(Player admin)
    {
        getDataFolder().getFile().set(admin.getName(), null);
        getDataFolder().saveData();
    }

    public static void setBan(Player admin, String nick, String reason)
    {
        final File file = new File("plugins/" + Main.getPlugin().getName() + "/Zbanowani/" + nick + ".yml");
        final YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(file);
        yamlConfiguration.set("Ban_Type", "Ban");
        yamlConfiguration.set("Admin", admin.getName());
        yamlConfiguration.set("Reason", reason);
        try {
            yamlConfiguration.save(file);
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static void setTempBan(Player admin, String nick, long time, String reason)
    {
        final File file = new File("plugins/" + Main.getPlugin().getName() + "/Zbanowani/" + nick + ".yml");
        final YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(file);
        yamlConfiguration.set("Ban_Type", "Tempban");
        yamlConfiguration.set("Admin", admin.getName());
        yamlConfiguration.set("Time", time);
        yamlConfiguration.set("Reason", reason);
        try {
            yamlConfiguration.save(file);
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static void setUnban(String nick)
    {
        final File file = new File("plugins/" + Main.getPlugin().getName() + "/Zbanowani/" + nick + ".yml");
        file.delete();
    }

    public static String getBan(final String nick) {
        final File file = new File("plugins/" + Main.getPlugin().getName() + "/Zbanowani/" + nick + ".yml");
        if (file.exists()) {
            final YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(file);
            final String msg = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(yamlConfiguration.getString("Ban_Type"))))))) + ";" + yamlConfiguration.getString("Reason") + ";" + yamlConfiguration.getString("Admin") + ";" + yamlConfiguration.getLong("Time");
            return msg;
        }
        return null;
    }

    public static void setBanIP(Player admin, String IP, String reason)
    {
        final File file = new File("plugins/" + Main.getPlugin().getName() + "/Zbanowane-IP/" + IP + ".yml");
        final YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(file);
        yamlConfiguration.set("Admin", admin.getName());
        yamlConfiguration.set("Reason", reason);
        try {
            yamlConfiguration.save(file);
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static void setUnbanIP(String IP)
    {
        final File file = new File("plugins/" + Main.getPlugin().getName() + "/Zbanowane-IP/" + IP + ".yml");
        file.delete();
    }

    public static String getBanIP(final String IP) {
        final File file = new File("plugins/" + Main.getPlugin().getName() + "/Zbanowane-IP/" + IP + ".yml");
        if (file.exists()) {
            final YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file);
            final String msg = yaml.getString("Admin") + ";" + yaml.getString("Reason");
            return msg;
        }
        return null;
    }

    public static ActionFile getDataFolder()
    {
        return af;
    }

    static {
        af = ActionFile.getInstance();
    }
}
