package x.KenKOfficial.BanGUI.Utils;

public class NameUtil
{
    public static String fixColor(final String msg)
    {
        return msg.replaceAll("&", "§").replace(">>", "»").replace("<<", "«").replace("{N}", "\n");
    }
}
