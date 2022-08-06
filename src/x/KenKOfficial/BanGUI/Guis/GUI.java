package x.KenKOfficial.BanGUI.Guis;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
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

public class GUI
{
    public static InventoryView getMenu(Player p) {
        final String player_name = API.getDataFolder().getFile().getString(p.getName() + ".target_player");
        final Inventory inv = Bukkit.createInventory((InventoryHolder)p, InventoryType.HOPPER, NameUtil.fixColor("&8>> &b&l" + player_name));

        final ItemStack gray_glass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)7);

        final ItemStack ban = new ItemStack(Material.BARRIER, 1);
        final ItemMeta ban_meta = ban.getItemMeta();
        ban_meta.setDisplayName(NameUtil.fixColor("&c&lZbanuj Gracza."));
        final List<String> ban_lore = new ArrayList<String>();
        ban_lore.add(" ");
        ban_lore.add("&8>> &7Aby przejsc dalej, nacisnij &bLPM&7.");
        ban_lore.add(" ");
        ban_meta.setLore(LoreUtil.fixColors(ban_lore));
        ban.setItemMeta(ban_meta);

        final ItemStack tempban = new ItemStack(Material.WATCH, 1);
        final ItemMeta tempban_meta = tempban.getItemMeta();
        tempban_meta.setDisplayName(NameUtil.fixColor("&c&lZbanuj Tymczasowo Gracza."));
        final List<String> tempban_lore = new ArrayList<String>();
        tempban_lore.add(" ");
        tempban_lore.add("&8>> &7Aby przejsc dalej, nacisnij &bLPM&7.");
        tempban_lore.add(" ");
        tempban_meta.setLore(LoreUtil.fixColors(tempban_lore));
        tempban.setItemMeta(tempban_meta);

        final ItemStack banip = new ItemStack(Material.EMERALD, 1);
        final ItemMeta banip_meta = banip.getItemMeta();
        banip_meta.setDisplayName(NameUtil.fixColor("&c&lZbanuj IP Gracza."));
        final List<String> banip_lore = new ArrayList<String>();
        banip_lore.add(" ");
        banip_lore.add("&8>> &7Aby przejsc dalej, nacisnij &bLPM&7.");
        banip_lore.add(" ");
        banip_meta.setLore(LoreUtil.fixColors(banip_lore));
        banip.setItemMeta(banip_meta);

        final ItemStack kick = new ItemStack(Material.LEATHER_BOOTS, 1);
        final ItemMeta kick_meta = kick.getItemMeta();
        kick_meta.setDisplayName(NameUtil.fixColor("&c&lWyrzuc Gracza z Serwera."));
        final List<String> kick_lore = new ArrayList<String>();
        kick_lore.add(" ");
        kick_lore.add("&8>> &7Aby przejsc dalej, nacisnij &bLPM&7.");
        kick_lore.add(" ");
        kick_meta.setLore(LoreUtil.fixColors(kick_lore));
        kick.setItemMeta(kick_meta);

        final ItemStack red_glass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)14);
        final ItemMeta red_glass_meta = red_glass.getItemMeta();
        red_glass_meta.setDisplayName(NameUtil.fixColor("&c&lAnuluj"));
        final List<String> red_glass_lore = new ArrayList<String>();
        red_glass_lore.add(" ");
        red_glass_lore.add("&8>> &7Aby anulowac, nacisnij &bLPM&7.");
        red_glass_lore.add(" ");
        red_glass_meta.setLore(LoreUtil.fixColors(red_glass_lore));
        red_glass.setItemMeta(red_glass_meta);

        for(int i = 0; i < 5; i++) {
            inv.setItem(i, gray_glass);
            inv.setItem(0, ban);
            inv.setItem(1, tempban);
            inv.setItem(2, banip);
            inv.setItem(3, kick);
            inv.setItem(4, red_glass);
        }
        return p.openInventory(inv);
    }
}
