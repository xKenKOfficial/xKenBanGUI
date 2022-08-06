package x.KenKOfficial.BanGUI.Files;

import x.KenKOfficial.BanGUI.Basic.Main;

import java.io.File;

public class BanIPFile
{
    private static BanIPFile banIPFile = new BanIPFile();

    public void load()
    {
        final File file = new File("plugins/" + Main.getPlugin().getName() + "/Zbanowane-IP");
        if(!file.exists()) {
            file.mkdir();
        }
    }

    public static BanIPFile getDataFolder()
    {
        return banIPFile;
    }
}
