package x.KenKOfficial.BanGUI.Basic;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import x.KenKOfficial.BanGUI.Apis.API;
import x.KenKOfficial.BanGUI.Commands.BanGuiCommand;
import x.KenKOfficial.BanGUI.Commands.UnbanCommand;
import x.KenKOfficial.BanGUI.Commands.UnbanipCommand;
import x.KenKOfficial.BanGUI.Files.BanFile;
import x.KenKOfficial.BanGUI.Files.BanIPFile;
import x.KenKOfficial.BanGUI.Listeners.AsyncPlayerPreLogin;
import x.KenKOfficial.BanGUI.Listeners.InventoryClick;
import x.KenKOfficial.BanGUI.Listeners.PlayerJoin;
import x.KenKOfficial.BanGUI.Listeners.PlayerLogin;

public class Main extends JavaPlugin
{
    private static Main plugin;

    public void onEnable()
    {
        (plugin) = this;
        this.saveDefaultConfig();
        this.registerCommands();
        this.registerListeners();
        this.registerFiles();
        System.out.println("###########################################################");
        System.out.println(this.getName());
        System.out.println("Wersja: " + this.getDescription().getVersion());
        System.out.println("Wersja Bukkit: " + Bukkit.getBukkitVersion());
        System.out.println(this.isEnabled() ? "Aktywowany" : "Dezaktywowany");
        System.out.println("Wszelkie Edycje i Naruszanie Praw Autorskich - ZABRONIONE!");
        System.out.println("###########################################################");
    }

    public void onDisable()
    {
        this.saveDefaultConfig();
        this.saveFiles();
        System.out.println("###########################################################");
        System.out.println(this.getName());
        System.out.println("Wersja: " + this.getDescription().getVersion());
        System.out.println("Wersja Bukkit: " + Bukkit.getBukkitVersion());
        System.out.println(this.isEnabled() ? "Aktywowany" : "Dezaktywowany");
        System.out.println("Wszelkie Edycje i Naruszanie Praw Autorskich - ZABRONIONE!");
        System.out.println("###########################################################");
    }

    private void registerCommands()
    {
        this.getLogger().info("Ladowanie komend z pluginu: " + this.getName());
        this.getCommand("bangui").setExecutor(new BanGuiCommand());
        this.getCommand("unban").setExecutor(new UnbanCommand());
        this.getCommand("unbanip").setExecutor(new UnbanipCommand());
    }

    private void registerListeners()
    {
        this.getLogger().info("Ladowanie eventow z pluginu: " + this.getName());
        Bukkit.getPluginManager().registerEvents((Listener)new AsyncPlayerPreLogin(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents((Listener)new InventoryClick(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents((Listener)new PlayerJoin(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents((Listener)new PlayerLogin(), (Plugin)this);
    }

    private void registerFiles()
    {
        this.getLogger().info("Ladowanie plikow konfiguracyjnych z pluginu: " + this.getName());
        API.getDataFolder().setup(this);
        BanFile.getDataFolder().load();
        BanIPFile.getDataFolder().load();
    }

    private void saveFiles()
    {
        this.getLogger().info("Zapisywanie plikow konfiguracyjnych z pluginu: " + this.getName());
        API.getDataFolder().saveData();
    }

    public static Main getPlugin()
    {
        return plugin;
    }
}
