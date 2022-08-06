package x.KenKOfficial.BanGUI.Guis.Kick;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import x.KenKOfficial.BanGUI.Apis.API;
import x.KenKOfficial.BanGUI.Utils.LoreUtil;
import x.KenKOfficial.BanGUI.Utils.NameUtil;

import java.util.ArrayList;
import java.util.List;

public class KickGUI
{
    public static String REASON_1 = "&c&lWyzywanie Graczy.";
    public static String REASON_2 = "&c&lCheatowanie.";
    public static String REASON_3 = "&c&lObraza Administratora.";
    public static String REASON_4 = "&c&lSpamienie na chat'cie.";
    public static String REASON_5 = "&c&lReklamowanie Serwerow.";
    public static String REASON_6 = "&c&lBotowanie Serwera.";
    public static String REASON_7 = "&c&lProba crashowania serwera.";

    public static InventoryView getKickMenu(Player p) {
        final String player_name = API.getDataFolder().getFile().getString(p.getName() + ".target_player");
        final Inventory inv = Bukkit.createInventory((InventoryHolder)p, 27, NameUtil.fixColor("&4&lWyrzuc&8: &c" + player_name));

        final ItemStack gray_glass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)7);

        final ItemStack reason_1 = new ItemStack(Material.PAPER, 1);
        final ItemMeta reason_1_meta = reason_1.getItemMeta();
        reason_1_meta.setDisplayName(NameUtil.fixColor(REASON_1));
        final List<String> reason_1_lore = new ArrayList<String>();
        reason_1_lore.add(" ");
        reason_1_lore.add("&8>> &7Kliknij &bLPM&7, aby wybrac powod bana.");
        reason_1_lore.add(" ");
        reason_1_meta.setLore(LoreUtil.fixColors(reason_1_lore));
        reason_1.setItemMeta(reason_1_meta);

        final ItemStack reason_2 = new ItemStack(Material.PAPER, 1);
        final ItemMeta reason_2_meta = reason_2.getItemMeta();
        reason_2_meta.setDisplayName(NameUtil.fixColor(REASON_2));
        final List<String> reason_2_lore = new ArrayList<String>();
        reason_2_lore.add(" ");
        reason_2_lore.add("&8>> &7Kliknij &bLPM&7, aby wybrac powod bana.");
        reason_2_lore.add(" ");
        reason_2_meta.setLore(LoreUtil.fixColors(reason_2_lore));
        reason_2.setItemMeta(reason_2_meta);

        final ItemStack reason_3 = new ItemStack(Material.PAPER, 1);
        final ItemMeta reason_3_meta = reason_3.getItemMeta();
        reason_3_meta.setDisplayName(NameUtil.fixColor(REASON_3));
        final List<String> reason_3_lore = new ArrayList<String>();
        reason_3_lore.add(" ");
        reason_3_lore.add("&8>> &7Kliknij &bLPM&7, aby wybrac powod bana.");
        reason_3_lore.add(" ");
        reason_3_meta.setLore(LoreUtil.fixColors(reason_3_lore));
        reason_3.setItemMeta(reason_3_meta);

        final ItemStack reason_4 = new ItemStack(Material.PAPER, 1);
        final ItemMeta reason_4_meta = reason_4.getItemMeta();
        reason_4_meta.setDisplayName(NameUtil.fixColor(REASON_4));
        final List<String> reason_4_lore = new ArrayList<String>();
        reason_4_lore.add(" ");
        reason_4_lore.add("&8>> &7Kliknij &bLPM&7, aby wybrac powod bana.");
        reason_4_lore.add(" ");
        reason_4_meta.setLore(LoreUtil.fixColors(reason_4_lore));
        reason_4.setItemMeta(reason_4_meta);

        final ItemStack reason_5 = new ItemStack(Material.PAPER, 1);
        final ItemMeta reason_5_meta = reason_5.getItemMeta();
        reason_5_meta.setDisplayName(NameUtil.fixColor(REASON_5));
        final List<String> reason_5_lore = new ArrayList<String>();
        reason_5_lore.add(" ");
        reason_5_lore.add("&8>> &7Kliknij &bLPM&7, aby wybrac powod bana.");
        reason_5_lore.add(" ");
        reason_5_meta.setLore(LoreUtil.fixColors(reason_5_lore));
        reason_5.setItemMeta(reason_5_meta);

        final ItemStack reason_6 = new ItemStack(Material.PAPER, 1);
        final ItemMeta reason_6_meta = reason_6.getItemMeta();
        reason_6_meta.setDisplayName(NameUtil.fixColor(REASON_6));
        final List<String> reason_6_lore = new ArrayList<String>();
        reason_6_lore.add(" ");
        reason_6_lore.add("&8>> &7Kliknij &bLPM&7, aby wybrac powod bana.");
        reason_6_lore.add(" ");
        reason_6_meta.setLore(LoreUtil.fixColors(reason_6_lore));
        reason_6.setItemMeta(reason_6_meta);

        final ItemStack reason_7 = new ItemStack(Material.PAPER, 1);
        final ItemMeta reason_7_meta = reason_7.getItemMeta();
        reason_7_meta.setDisplayName(NameUtil.fixColor(REASON_7));
        final List<String> reason_7_lore = new ArrayList<String>();
        reason_7_lore.add(" ");
        reason_7_lore.add("&8>> &7Kliknij &bLPM&7, aby wybrac powod bana.");
        reason_7_lore.add(" ");
        reason_7_meta.setLore(LoreUtil.fixColors(reason_7_lore));
        reason_7.setItemMeta(reason_7_meta);

        final ItemStack red_glass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)14);
        final ItemMeta red_glass_meta = red_glass.getItemMeta();
        red_glass_meta.setDisplayName(NameUtil.fixColor("&c&lPowrot"));
        final List<String> red_glass_lore = new ArrayList<String>();
        red_glass_lore.add(" ");
        red_glass_lore.add("&8>> &7Aby wrocic do menu, nacisnij &bLPM&7.");
        red_glass_lore.add(" ");
        red_glass_meta.setLore(LoreUtil.fixColors(red_glass_lore));
        red_glass.setItemMeta(red_glass_meta);

        for(int i = 0; i < 27; i++) {
            inv.setItem(i, gray_glass);
            inv.setItem(10, reason_1);
            inv.setItem(11, reason_2);
            inv.setItem(12, reason_3);
            inv.setItem(13, reason_4);
            inv.setItem(14, reason_5);
            inv.setItem(15, reason_6);
            inv.setItem(16, reason_7);
            inv.setItem(26, red_glass);
        }
        return p.openInventory(inv);
    }
}
