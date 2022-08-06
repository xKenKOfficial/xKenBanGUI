package x.KenKOfficial.BanGUI.Files;

import x.KenKOfficial.BanGUI.Basic.Main;

import java.io.File;

public class BanFile
{
    private static BanFile banFile = new BanFile();

    public void load()
    {
        final File file = new File("plugins/" + Main.getPlugin().getName() + "/Zbanowani");
        if(!file.exists()) {
            file.mkdir();
        }
    }

    public static BanFile getDataFolder()
    {
        return banFile;
    }
}
