package x.KenKOfficial.BanGUI.Files;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;

public class ActionFile
{
    static ActionFile instance;
    Plugin p;
    FileConfiguration data;
    public static File rfile;

    public static ActionFile getInstance()
    {
        return ActionFile.instance;
    }

    public void setup(final Plugin p)
    {
        if (!p.getDataFolder().exists()) {
            p.getDataFolder().mkdir();
        }
        final File path = new File(p.getDataFolder() + File.separator + "/Actions");
        ActionFile.rfile = new File(path, String.valueOf(String.valueOf(File.separatorChar) + "action.yml"));
        if (!ActionFile.rfile.exists()) {
            try {
                path.mkdirs();
                ActionFile.rfile.createNewFile();
            }
            catch (IOException e) {
                Bukkit.getServer().getLogger().severe("Nie udalo sie zaladowac pliku action.yml");
            }
        }
        this.data = (FileConfiguration) YamlConfiguration.loadConfiguration(ActionFile.rfile);
    }

    public FileConfiguration getFile()
    {
        return this.data;
    }

    public void saveData()
    {
        try {
            this.data.save(ActionFile.rfile);
        }
        catch (IOException e) {
            Bukkit.getServer().getLogger().severe("Nie udalo sie zapisac pliku action.yml");
        }
    }

    public void reloadData()
    {
        this.data = (FileConfiguration)YamlConfiguration.loadConfiguration(ActionFile.rfile);
    }

    static {
        ActionFile.instance = new ActionFile();
    }
}
