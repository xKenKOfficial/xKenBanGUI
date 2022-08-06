package x.KenKOfficial.BanGUI.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import x.KenKOfficial.BanGUI.Apis.API;
import x.KenKOfficial.BanGUI.Basic.Main;
import x.KenKOfficial.BanGUI.Guis.Ban.BanGUI;
import x.KenKOfficial.BanGUI.Guis.BanIP.BanipGUI;
import x.KenKOfficial.BanGUI.Guis.GUI;
import x.KenKOfficial.BanGUI.Guis.Kick.KickGUI;
import x.KenKOfficial.BanGUI.Guis.Tempban.TempbanGUI;
import x.KenKOfficial.BanGUI.Utils.ChatUtil;
import x.KenKOfficial.BanGUI.Utils.NameUtil;
import x.KenKOfficial.BanGUI.Utils.TimeUtil;

import java.util.List;
import java.util.regex.Pattern;

public class InventoryClick implements Listener
{
    private static final String PREFIX = Main.getPlugin().getConfig().getString("prefix");
    private static final String NO_PERM = Main.getPlugin().getConfig().getString("bangui_no_permission_usage");
    private static final String BAN_OFFLINE_PLAYER = Main.getPlugin().getConfig().getString("bangui_ban_offline_player");

    private static final String BANIP_KICK_PLAYER = Main.getPlugin().getConfig().getString("bangui_banip_kick_player");
    private static final String BANIP_ADMIN_MESSAGE = Main.getPlugin().getConfig().getString("bangui_banip_admin_message");
    private static final String BANIP_ADMINS_MESSAGE = Main.getPlugin().getConfig().getString("bangui_banip_admins_message");

    private static final String KICK_PLAYER = Main.getPlugin().getConfig().getString("bangui_kick_player_reason");

    public static final Pattern ipValidity;

    @EventHandler
    public void onClick(final InventoryClickEvent e) {
        final Player p = (Player)e.getWhoClicked();
        final String player_name = API.getDataFolder().getFile().getString(p.getName() + ".target_player");
        if(e.getView().getTitle().equalsIgnoreCase(NameUtil.fixColor("&8>> &b&l" + player_name))) {
            e.setCancelled(true);
            if(e.getSlot() == 0) {
                if(p.hasPermission("xkenbangui.admin.ban")) {
                    p.closeInventory();
                    BanGUI.getBanMenu(p);
                } else {
                    p.sendMessage(ChatUtil.fixColor(PREFIX + " " + NO_PERM));
                }
            } else if(e.getSlot() == 1) {
                if(p.hasPermission("xkenbangui.admin.tempban")) {
                    p.closeInventory();
                    TempbanGUI.getTempbanMenuTime(p);
                } else {
                    p.sendMessage(ChatUtil.fixColor(PREFIX + " " + NO_PERM));
                }
            } else if(e.getSlot() == 2) {
                if(p.hasPermission("xkenbangui.admin.banip")) {
                    p.closeInventory();
                    BanipGUI.getBanIPMenu(p);
                } else {
                    p.sendMessage(ChatUtil.fixColor(PREFIX + " " + NO_PERM));
                }
            } else if(e.getSlot() == 3) {
                if(p.hasPermission("xkenbangui.admin.kick")) {
                    p.closeInventory();
                    KickGUI.getKickMenu(p);
                } else {
                    p.sendMessage(ChatUtil.fixColor(PREFIX + " " + NO_PERM));
                }
            } else if(e.getSlot() == 4) {
                p.closeInventory();
                API.clearGui(p);
            }
        } else if(e.getView().getTitle().equalsIgnoreCase(NameUtil.fixColor("&4&lBan Perm&8: &c" + player_name))) {
            e.setCancelled(true);
            if(e.getSlot() == 10) {
                API.setBan(p, player_name, BanGUI.REASON_1);
                final Player cel = Bukkit.getPlayer(player_name);
                if(cel != null) {
                    final List<String> kick = Main.getPlugin().getConfig().getStringList("bangui_liststring.ban");
                    String msg = "";
                    for(int i = 0; i < kick.size(); i++) {
                        msg = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(msg)))))) + kick.get(i);
                        if(i <= kick.size() - 2) {
                            msg = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(msg)))))) + "{N}";
                        }
                    }
                    msg = msg.replace("&", "§");
                    msg = msg.replace("{N}", "\n");
                    msg = msg.replace("{POWOD}", BanGUI.REASON_1);
                    msg = msg.replace("{ADMIN}", p.getName());
                    cel.kickPlayer(ChatUtil.fixColor(msg));
                } else {
                    p.sendMessage(ChatUtil.fixColor(PREFIX + " " + BAN_OFFLINE_PLAYER));
                }
                String msg2 = Main.getPlugin().getConfig().getString("bangui_ban_broadcast_message");
                msg2 = msg2.replace("&", "§");
                msg2 = msg2.replace("&", "§");
                msg2 = msg2.replace("{N}", "\n");
                msg2 = msg2.replace("{POWOD}", BanGUI.REASON_1);
                msg2 = msg2.replace("{GRACZ}", cel.getName());
                msg2 = msg2.replace("{ADMIN}", p.getName());
                Bukkit.broadcastMessage(ChatUtil.fixColor(PREFIX + " " + msg2));
                API.clearGui(p);
            } else if(e.getSlot() == 11) {
                API.setBan(p, player_name, BanGUI.REASON_2);
                final Player cel = Bukkit.getPlayer(player_name);
                if(cel != null) {
                    final List<String> kick = Main.getPlugin().getConfig().getStringList("bangui_liststring.ban");
                    String msg = "";
                    for(int i = 0; i < kick.size(); i++) {
                        msg = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(msg)))))) + kick.get(i);
                        if(i <= kick.size() - 2) {
                            msg = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(msg)))))) + "{N}";
                        }
                    }
                    msg = msg.replace("&", "§");
                    msg = msg.replace("{N}", "\n");
                    msg = msg.replace("{POWOD}", BanGUI.REASON_2);
                    msg = msg.replace("{ADMIN}", p.getName());
                    cel.kickPlayer(ChatUtil.fixColor(msg));
                } else {
                    p.sendMessage(ChatUtil.fixColor(PREFIX + " " + BAN_OFFLINE_PLAYER));
                }
                String msg2 = Main.getPlugin().getConfig().getString("bangui_ban_broadcast_message");
                msg2 = msg2.replace("&", "§");
                msg2 = msg2.replace("&", "§");
                msg2 = msg2.replace("{N}", "\n");
                msg2 = msg2.replace("{POWOD}", BanGUI.REASON_2);
                msg2 = msg2.replace("{GRACZ}", cel.getName());
                msg2 = msg2.replace("{ADMIN}", p.getName());
                Bukkit.broadcastMessage(ChatUtil.fixColor(PREFIX + " " + msg2));
                API.clearGui(p);
            } else if(e.getSlot() == 12) {
                API.setBan(p, player_name, BanGUI.REASON_3);
                final Player cel = Bukkit.getPlayer(player_name);
                if(cel != null) {
                    final List<String> kick = Main.getPlugin().getConfig().getStringList("bangui_liststring.ban");
                    String msg = "";
                    for(int i = 0; i < kick.size(); i++) {
                        msg = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(msg)))))) + kick.get(i);
                        if(i <= kick.size() - 2) {
                            msg = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(msg)))))) + "{N}";
                        }
                    }
                    msg = msg.replace("&", "§");
                    msg = msg.replace("{N}", "\n");
                    msg = msg.replace("{POWOD}", BanGUI.REASON_3);
                    msg = msg.replace("{ADMIN}", p.getName());
                    cel.kickPlayer(ChatUtil.fixColor(msg));
                } else {
                    p.sendMessage(ChatUtil.fixColor(PREFIX + " " + BAN_OFFLINE_PLAYER));
                }
                String msg2 = Main.getPlugin().getConfig().getString("bangui_ban_broadcast_message");
                msg2 = msg2.replace("&", "§");
                msg2 = msg2.replace("&", "§");
                msg2 = msg2.replace("{N}", "\n");
                msg2 = msg2.replace("{POWOD}", BanGUI.REASON_3);
                msg2 = msg2.replace("{GRACZ}", cel.getName());
                msg2 = msg2.replace("{ADMIN}", p.getName());
                Bukkit.broadcastMessage(ChatUtil.fixColor(PREFIX + " " + msg2));
                API.clearGui(p);
            } else if(e.getSlot() == 13) {
                API.setBan(p, player_name, BanGUI.REASON_4);
                final Player cel = Bukkit.getPlayer(player_name);
                if(cel != null) {
                    final List<String> kick = Main.getPlugin().getConfig().getStringList("bangui_liststring.ban");
                    String msg = "";
                    for(int i = 0; i < kick.size(); i++) {
                        msg = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(msg)))))) + kick.get(i);
                        if(i <= kick.size() - 2) {
                            msg = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(msg)))))) + "{N}";
                        }
                    }
                    msg = msg.replace("&", "§");
                    msg = msg.replace("{N}", "\n");
                    msg = msg.replace("{POWOD}", BanGUI.REASON_4);
                    msg = msg.replace("{ADMIN}", p.getName());
                    cel.kickPlayer(ChatUtil.fixColor(msg));
                } else {
                    p.sendMessage(ChatUtil.fixColor(PREFIX + " " + BAN_OFFLINE_PLAYER));
                }
                String msg2 = Main.getPlugin().getConfig().getString("bangui_ban_broadcast_message");
                msg2 = msg2.replace("&", "§");
                msg2 = msg2.replace("&", "§");
                msg2 = msg2.replace("{N}", "\n");
                msg2 = msg2.replace("{POWOD}", BanGUI.REASON_4);
                msg2 = msg2.replace("{GRACZ}", cel.getName());
                msg2 = msg2.replace("{ADMIN}", p.getName());
                Bukkit.broadcastMessage(ChatUtil.fixColor(PREFIX + " " + msg2));
                API.clearGui(p);
            } else if(e.getSlot() == 14) {
                API.setBan(p, player_name, BanGUI.REASON_5);
                final Player cel = Bukkit.getPlayer(player_name);
                if(cel != null) {
                    final List<String> kick = Main.getPlugin().getConfig().getStringList("bangui_liststring.ban");
                    String msg = "";
                    for(int i = 0; i < kick.size(); i++) {
                        msg = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(msg)))))) + kick.get(i);
                        if(i <= kick.size() - 2) {
                            msg = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(msg)))))) + "{N}";
                        }
                    }
                    msg = msg.replace("&", "§");
                    msg = msg.replace("{N}", "\n");
                    msg = msg.replace("{POWOD}", BanGUI.REASON_5);
                    msg = msg.replace("{ADMIN}", p.getName());
                    cel.kickPlayer(ChatUtil.fixColor(msg));
                } else {
                    p.sendMessage(ChatUtil.fixColor(PREFIX + " " + BAN_OFFLINE_PLAYER));
                }
                String msg2 = Main.getPlugin().getConfig().getString("bangui_ban_broadcast_message");
                msg2 = msg2.replace("&", "§");
                msg2 = msg2.replace("&", "§");
                msg2 = msg2.replace("{N}", "\n");
                msg2 = msg2.replace("{POWOD}", BanGUI.REASON_5);
                msg2 = msg2.replace("{GRACZ}", cel.getName());
                msg2 = msg2.replace("{ADMIN}", p.getName());
                Bukkit.broadcastMessage(ChatUtil.fixColor(PREFIX + " " + msg2));
                API.clearGui(p);
            } else if(e.getSlot() == 15) {
                API.setBan(p, player_name, BanGUI.REASON_6);
                final Player cel = Bukkit.getPlayer(player_name);
                if(cel != null) {
                    final List<String> kick = Main.getPlugin().getConfig().getStringList("bangui_liststring.ban");
                    String msg = "";
                    for(int i = 0; i < kick.size(); i++) {
                        msg = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(msg)))))) + kick.get(i);
                        if(i <= kick.size() - 2) {
                            msg = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(msg)))))) + "{N}";
                        }
                    }
                    msg = msg.replace("&", "§");
                    msg = msg.replace("{N}", "\n");
                    msg = msg.replace("{POWOD}", BanGUI.REASON_6);
                    msg = msg.replace("{ADMIN}", p.getName());
                    cel.kickPlayer(ChatUtil.fixColor(msg));
                } else {
                    p.sendMessage(ChatUtil.fixColor(PREFIX + " " + BAN_OFFLINE_PLAYER));
                }
                String msg2 = Main.getPlugin().getConfig().getString("bangui_ban_broadcast_message");
                msg2 = msg2.replace("&", "§");
                msg2 = msg2.replace("&", "§");
                msg2 = msg2.replace("{N}", "\n");
                msg2 = msg2.replace("{POWOD}", BanGUI.REASON_6);
                msg2 = msg2.replace("{GRACZ}", cel.getName());
                msg2 = msg2.replace("{ADMIN}", p.getName());
                Bukkit.broadcastMessage(ChatUtil.fixColor(PREFIX + " " + msg2));
                API.clearGui(p);
            } else if(e.getSlot() == 16) {
                API.setBan(p, player_name, BanGUI.REASON_7);
                final Player cel = Bukkit.getPlayer(player_name);
                if(cel != null) {
                    final List<String> kick = Main.getPlugin().getConfig().getStringList("bangui_liststring.ban");
                    String msg = "";
                    for(int i = 0; i < kick.size(); i++) {
                        msg = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(msg)))))) + kick.get(i);
                        if(i <= kick.size() - 2) {
                            msg = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(msg)))))) + "{N}";
                        }
                    }
                    msg = msg.replace("&", "§");
                    msg = msg.replace("{N}", "\n");
                    msg = msg.replace("{POWOD}", BanGUI.REASON_7);
                    msg = msg.replace("{ADMIN}", p.getName());
                    cel.kickPlayer(ChatUtil.fixColor(msg));
                } else {
                    p.sendMessage(ChatUtil.fixColor(PREFIX + " " + BAN_OFFLINE_PLAYER));
                }
                String msg2 = Main.getPlugin().getConfig().getString("bangui_ban_broadcast_message");
                msg2 = msg2.replace("&", "§");
                msg2 = msg2.replace("&", "§");
                msg2 = msg2.replace("{N}", "\n");
                msg2 = msg2.replace("{POWOD}", BanGUI.REASON_7);
                msg2 = msg2.replace("{GRACZ}", cel.getName());
                msg2 = msg2.replace("{ADMIN}", p.getName());
                Bukkit.broadcastMessage(ChatUtil.fixColor(PREFIX + " " + msg2));
                API.clearGui(p);
            } else if(e.getSlot() == 26) {
                p.closeInventory();
                GUI.getMenu(p);
            }
        } else if(e.getView().getTitle().equalsIgnoreCase(NameUtil.fixColor("&4&lBan Czas&8: &c" + player_name))) {
            e.setCancelled(true);
            if(e.getSlot() == 11) {
                final long czas = System.currentTimeMillis() + TimeUtil.getTimeWithString(TempbanGUI.TIME_1);
                API.getDataFolder().getFile().set(p.getName() + ".time", (long)czas);
                API.getDataFolder().saveData();
                p.closeInventory();
                TempbanGUI.getTempbanMenuReason(p);
            } else if(e.getSlot() == 12) {
                final long czas = System.currentTimeMillis() + TimeUtil.getTimeWithString(TempbanGUI.TIME_1);
                API.getDataFolder().getFile().set(p.getName() + ".time", (long)czas);
                API.getDataFolder().saveData();
                p.closeInventory();
                TempbanGUI.getTempbanMenuReason(p);
            } else if(e.getSlot() == 14) {
                final long czas = System.currentTimeMillis() + TimeUtil.getTimeWithString(TempbanGUI.TIME_1);
                API.getDataFolder().getFile().set(p.getName() + ".time", (long)czas);
                API.getDataFolder().saveData();
                p.closeInventory();
                TempbanGUI.getTempbanMenuReason(p);
            } else if(e.getSlot() == 15) {
                final long czas = System.currentTimeMillis() + TimeUtil.getTimeWithString(TempbanGUI.TIME_1);
                API.getDataFolder().getFile().set(p.getName() + ".time", (long)czas);
                API.getDataFolder().saveData();
                p.closeInventory();
                TempbanGUI.getTempbanMenuReason(p);
            } else if(e.getSlot() == 26) {
                p.closeInventory();
                GUI.getMenu(p);
            }
        } else if(e.getView().getTitle().equalsIgnoreCase(NameUtil.fixColor("&4&lBan Powod&8: &c" + player_name))) {
            e.setCancelled(true);
            if(e.getSlot() == 10) {
                final long czas = API.getDataFolder().getFile().getLong(p.getName() + ".time");
                API.setTempBan(p, player_name, czas, TempbanGUI.REASON_1);
                final Player cel = Bukkit.getPlayer(player_name);
                if(cel != null) {
                    final List<String> kick = Main.getPlugin().getConfig().getStringList("bangui_liststring.tempban");
                    String msg = "";
                    for(int i = 0; i < kick.size(); i++) {
                        msg = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(msg)))))) + kick.get(i);
                        if(i <= kick.size() - 2) {
                            msg = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(msg)))))) + "{N}";
                        }
                    }
                    msg = msg.replace("&", "§");
                    msg = msg.replace("{N}", "\n");
                    msg = msg.replace("{POWOD}", TempbanGUI.REASON_1);
                    msg = msg.replace("{ADMIN}", p.getName());
                    msg = msg.replace("{DATA}", TimeUtil.getDate(czas));
                    cel.kickPlayer(ChatUtil.fixColor(msg));
                } else {
                    p.sendMessage(ChatUtil.fixColor(PREFIX + " " + BAN_OFFLINE_PLAYER));
                }
                String msg2 = Main.getPlugin().getConfig().getString("bangui_tempban_broadcast_message");
                msg2 = msg2.replace("&", "§");
                msg2 = msg2.replace("&", "§");
                msg2 = msg2.replace("{N}", "\n");
                msg2 = msg2.replace("{POWOD}", TempbanGUI.REASON_1);
                msg2 = msg2.replace("{GRACZ}", cel.getName());
                msg2 = msg2.replace("{ADMIN}", p.getName());
                msg2 = msg2.replace("{DATA}", TimeUtil.getDate(czas));
                Bukkit.broadcastMessage(ChatUtil.fixColor(PREFIX + " " + msg2));
                API.clearGui(p);
            } else if(e.getSlot() == 11) {
                final long czas = API.getDataFolder().getFile().getLong(p.getName() + ".time");
                API.setTempBan(p, player_name, czas, TempbanGUI.REASON_2);
                final Player cel = Bukkit.getPlayer(player_name);
                if(cel != null) {
                    final List<String> kick = Main.getPlugin().getConfig().getStringList("bangui_liststring.tempban");
                    String msg = "";
                    for(int i = 0; i < kick.size(); i++) {
                        msg = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(msg)))))) + kick.get(i);
                        if(i <= kick.size() - 2) {
                            msg = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(msg)))))) + "{N}";
                        }
                    }
                    msg = msg.replace("&", "§");
                    msg = msg.replace("{N}", "\n");
                    msg = msg.replace("{POWOD}", TempbanGUI.REASON_2);
                    msg = msg.replace("{ADMIN}", p.getName());
                    msg = msg.replace("{DATA}", TimeUtil.getDate(czas));
                    cel.kickPlayer(ChatUtil.fixColor(msg));
                } else {
                    p.sendMessage(ChatUtil.fixColor(PREFIX + " " + BAN_OFFLINE_PLAYER));
                }
                String msg2 = Main.getPlugin().getConfig().getString("bangui_tempban_broadcast_message");
                msg2 = msg2.replace("&", "§");
                msg2 = msg2.replace("&", "§");
                msg2 = msg2.replace("{N}", "\n");
                msg2 = msg2.replace("{POWOD}", TempbanGUI.REASON_2);
                msg2 = msg2.replace("{GRACZ}", cel.getName());
                msg2 = msg2.replace("{ADMIN}", p.getName());
                msg2 = msg2.replace("{DATA}", TimeUtil.getDate(czas));
                Bukkit.broadcastMessage(ChatUtil.fixColor(PREFIX + " " + msg2));
                API.clearGui(p);
            } else if(e.getSlot() == 12) {
                final long czas = API.getDataFolder().getFile().getLong(p.getName() + ".time");
                API.setTempBan(p, player_name, czas, TempbanGUI.REASON_3);
                final Player cel = Bukkit.getPlayer(player_name);
                if(cel != null) {
                    final List<String> kick = Main.getPlugin().getConfig().getStringList("bangui_liststring.tempban");
                    String msg = "";
                    for(int i = 0; i < kick.size(); i++) {
                        msg = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(msg)))))) + kick.get(i);
                        if(i <= kick.size() - 2) {
                            msg = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(msg)))))) + "{N}";
                        }
                    }
                    msg = msg.replace("&", "§");
                    msg = msg.replace("{N}", "\n");
                    msg = msg.replace("{POWOD}", TempbanGUI.REASON_3);
                    msg = msg.replace("{ADMIN}", p.getName());
                    msg = msg.replace("{DATA}", TimeUtil.getDate(czas));
                    cel.kickPlayer(ChatUtil.fixColor(msg));
                } else {
                    p.sendMessage(ChatUtil.fixColor(PREFIX + " " + BAN_OFFLINE_PLAYER));
                }
                String msg2 = Main.getPlugin().getConfig().getString("bangui_tempban_broadcast_message");
                msg2 = msg2.replace("&", "§");
                msg2 = msg2.replace("&", "§");
                msg2 = msg2.replace("{N}", "\n");
                msg2 = msg2.replace("{POWOD}", TempbanGUI.REASON_3);
                msg2 = msg2.replace("{GRACZ}", cel.getName());
                msg2 = msg2.replace("{ADMIN}", p.getName());
                msg2 = msg2.replace("{DATA}", TimeUtil.getDate(czas));
                Bukkit.broadcastMessage(ChatUtil.fixColor(PREFIX + " " + msg2));
                API.clearGui(p);
            } else if(e.getSlot() == 13) {
                final long czas = API.getDataFolder().getFile().getLong(p.getName() + ".time");
                API.setTempBan(p, player_name, czas, TempbanGUI.REASON_4);
                final Player cel = Bukkit.getPlayer(player_name);
                if(cel != null) {
                    final List<String> kick = Main.getPlugin().getConfig().getStringList("bangui_liststring.tempban");
                    String msg = "";
                    for(int i = 0; i < kick.size(); i++) {
                        msg = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(msg)))))) + kick.get(i);
                        if(i <= kick.size() - 2) {
                            msg = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(msg)))))) + "{N}";
                        }
                    }
                    msg = msg.replace("&", "§");
                    msg = msg.replace("{N}", "\n");
                    msg = msg.replace("{POWOD}", TempbanGUI.REASON_4);
                    msg = msg.replace("{ADMIN}", p.getName());
                    msg = msg.replace("{DATA}", TimeUtil.getDate(czas));
                    cel.kickPlayer(ChatUtil.fixColor(msg));
                } else {
                    p.sendMessage(ChatUtil.fixColor(PREFIX + " " + BAN_OFFLINE_PLAYER));
                }
                String msg2 = Main.getPlugin().getConfig().getString("bangui_tempban_broadcast_message");
                msg2 = msg2.replace("&", "§");
                msg2 = msg2.replace("&", "§");
                msg2 = msg2.replace("{N}", "\n");
                msg2 = msg2.replace("{POWOD}", TempbanGUI.REASON_4);
                msg2 = msg2.replace("{GRACZ}", cel.getName());
                msg2 = msg2.replace("{ADMIN}", p.getName());
                msg2 = msg2.replace("{DATA}", TimeUtil.getDate(czas));
                Bukkit.broadcastMessage(ChatUtil.fixColor(PREFIX + " " + msg2));
                API.clearGui(p);
            } else if(e.getSlot() == 14) {
                final long czas = API.getDataFolder().getFile().getLong(p.getName() + ".time");
                API.setTempBan(p, player_name, czas, TempbanGUI.REASON_5);
                final Player cel = Bukkit.getPlayer(player_name);
                if(cel != null) {
                    final List<String> kick = Main.getPlugin().getConfig().getStringList("bangui_liststring.tempban");
                    String msg = "";
                    for(int i = 0; i < kick.size(); i++) {
                        msg = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(msg)))))) + kick.get(i);
                        if(i <= kick.size() - 2) {
                            msg = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(msg)))))) + "{N}";
                        }
                    }
                    msg = msg.replace("&", "§");
                    msg = msg.replace("{N}", "\n");
                    msg = msg.replace("{POWOD}", TempbanGUI.REASON_5);
                    msg = msg.replace("{ADMIN}", p.getName());
                    msg = msg.replace("{DATA}", TimeUtil.getDate(czas));
                    cel.kickPlayer(ChatUtil.fixColor(msg));
                } else {
                    p.sendMessage(ChatUtil.fixColor(PREFIX + " " + BAN_OFFLINE_PLAYER));
                }
                String msg2 = Main.getPlugin().getConfig().getString("bangui_tempban_broadcast_message");
                msg2 = msg2.replace("&", "§");
                msg2 = msg2.replace("&", "§");
                msg2 = msg2.replace("{N}", "\n");
                msg2 = msg2.replace("{POWOD}", TempbanGUI.REASON_5);
                msg2 = msg2.replace("{GRACZ}", cel.getName());
                msg2 = msg2.replace("{ADMIN}", p.getName());
                msg2 = msg2.replace("{DATA}", TimeUtil.getDate(czas));
                Bukkit.broadcastMessage(ChatUtil.fixColor(PREFIX + " " + msg2));
                API.clearGui(p);
            } else if(e.getSlot() == 15) {
                final long czas = API.getDataFolder().getFile().getLong(p.getName() + ".time");
                API.setTempBan(p, player_name, czas, TempbanGUI.REASON_6);
                final Player cel = Bukkit.getPlayer(player_name);
                if(cel != null) {
                    final List<String> kick = Main.getPlugin().getConfig().getStringList("bangui_liststring.tempban");
                    String msg = "";
                    for(int i = 0; i < kick.size(); i++) {
                        msg = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(msg)))))) + kick.get(i);
                        if(i <= kick.size() - 2) {
                            msg = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(msg)))))) + "{N}";
                        }
                    }
                    msg = msg.replace("&", "§");
                    msg = msg.replace("{N}", "\n");
                    msg = msg.replace("{POWOD}", TempbanGUI.REASON_6);
                    msg = msg.replace("{ADMIN}", p.getName());
                    msg = msg.replace("{DATA}", TimeUtil.getDate(czas));
                    cel.kickPlayer(ChatUtil.fixColor(msg));
                } else {
                    p.sendMessage(ChatUtil.fixColor(PREFIX + " " + BAN_OFFLINE_PLAYER));
                }
                String msg2 = Main.getPlugin().getConfig().getString("bangui_tempban_broadcast_message");
                msg2 = msg2.replace("&", "§");
                msg2 = msg2.replace("&", "§");
                msg2 = msg2.replace("{N}", "\n");
                msg2 = msg2.replace("{POWOD}", TempbanGUI.REASON_6);
                msg2 = msg2.replace("{GRACZ}", cel.getName());
                msg2 = msg2.replace("{ADMIN}", p.getName());
                msg2 = msg2.replace("{DATA}", TimeUtil.getDate(czas));
                Bukkit.broadcastMessage(ChatUtil.fixColor(PREFIX + " " + msg2));
                API.clearGui(p);
            } else if(e.getSlot() == 16) {
                final long czas = API.getDataFolder().getFile().getLong(p.getName() + ".time");
                API.setTempBan(p, player_name, czas, TempbanGUI.REASON_7);
                final Player cel = Bukkit.getPlayer(player_name);
                if(cel != null) {
                    final List<String> kick = Main.getPlugin().getConfig().getStringList("bangui_liststring.tempban");
                    String msg = "";
                    for(int i = 0; i < kick.size(); i++) {
                        msg = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(msg)))))) + kick.get(i);
                        if(i <= kick.size() - 2) {
                            msg = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(msg)))))) + "{N}";
                        }
                    }
                    msg = msg.replace("&", "§");
                    msg = msg.replace("{N}", "\n");
                    msg = msg.replace("{POWOD}", TempbanGUI.REASON_7);
                    msg = msg.replace("{ADMIN}", p.getName());
                    msg = msg.replace("{DATA}", TimeUtil.getDate(czas));
                    cel.kickPlayer(ChatUtil.fixColor(msg));
                } else {
                    p.sendMessage(ChatUtil.fixColor(PREFIX + " " + BAN_OFFLINE_PLAYER));
                }
                String msg2 = Main.getPlugin().getConfig().getString("bangui_tempban_broadcast_message");
                msg2 = msg2.replace("&", "§");
                msg2 = msg2.replace("&", "§");
                msg2 = msg2.replace("{N}", "\n");
                msg2 = msg2.replace("{POWOD}", TempbanGUI.REASON_7);
                msg2 = msg2.replace("{GRACZ}", cel.getName());
                msg2 = msg2.replace("{ADMIN}", p.getName());
                msg2 = msg2.replace("{DATA}", TimeUtil.getDate(czas));
                Bukkit.broadcastMessage(ChatUtil.fixColor(PREFIX + " " + msg2));
                API.clearGui(p);
            } else if(e.getSlot() == 26) {
                p.closeInventory();
                TempbanGUI.getTempbanMenuTime(p);
            }
        } else if(e.getView().getTitle().equalsIgnoreCase(NameUtil.fixColor("&4&lBan IP&8: &c" + player_name))) {
            e.setCancelled(true);
            if(e.getSlot() == 10) {
                final Player cel = Bukkit.getPlayer(player_name);
                this.processIPBan(cel.getAddress().getAddress().getHostAddress(), p, BanipGUI.REASON_1);
                API.clearGui(p);
            } else if(e.getSlot() == 11) {
                final Player cel = Bukkit.getPlayer(player_name);
                this.processIPBan(cel.getAddress().getAddress().getHostAddress(), p, BanipGUI.REASON_2);
                API.clearGui(p);
            } else if(e.getSlot() == 12) {
                final Player cel = Bukkit.getPlayer(player_name);
                this.processIPBan(cel.getAddress().getAddress().getHostAddress(), p, BanipGUI.REASON_3);
                API.clearGui(p);
            } else if(e.getSlot() == 13) {
                final Player cel = Bukkit.getPlayer(player_name);
                this.processIPBan(cel.getAddress().getAddress().getHostAddress(), p, BanipGUI.REASON_4);
                API.clearGui(p);
            } else if(e.getSlot() == 14) {
                final Player cel = Bukkit.getPlayer(player_name);
                this.processIPBan(cel.getAddress().getAddress().getHostAddress(), p, BanipGUI.REASON_5);
                API.clearGui(p);
            } else if(e.getSlot() == 15) {
                final Player cel = Bukkit.getPlayer(player_name);
                this.processIPBan(cel.getAddress().getAddress().getHostAddress(), p, BanipGUI.REASON_6);
                API.clearGui(p);
            } else if(e.getSlot() == 16) {
                final Player cel = Bukkit.getPlayer(player_name);
                this.processIPBan(cel.getAddress().getAddress().getHostAddress(), p, BanipGUI.REASON_7);
                API.clearGui(p);
            } else if(e.getSlot() == 26) {
                p.closeInventory();
                GUI.getMenu(p);
            }
        } else if(e.getView().getTitle().equalsIgnoreCase(NameUtil.fixColor("&4&lWyrzuc&8: &c" + player_name))) {
            e.setCancelled(true);
            if(e.getSlot() == 10) {
                final Player cel = Bukkit.getPlayer(player_name);
                cel.kickPlayer(ChatUtil.fixColor(KICK_PLAYER.replace("{POWOD}", KickGUI.REASON_1)));
                API.clearGui(p);
            } else if(e.getSlot() == 11) {
                final Player cel = Bukkit.getPlayer(player_name);
                cel.kickPlayer(ChatUtil.fixColor(KICK_PLAYER.replace("{POWOD}", KickGUI.REASON_2)));
                API.clearGui(p);
            } else if(e.getSlot() == 12) {
                final Player cel = Bukkit.getPlayer(player_name);
                cel.kickPlayer(ChatUtil.fixColor(KICK_PLAYER.replace("{POWOD}", KickGUI.REASON_3)));
                API.clearGui(p);
            } else if(e.getSlot() == 13) {
                final Player cel = Bukkit.getPlayer(player_name);
                cel.kickPlayer(ChatUtil.fixColor(KICK_PLAYER.replace("{POWOD}", KickGUI.REASON_4)));
                API.clearGui(p);
            } else if(e.getSlot() == 14) {
                final Player cel = Bukkit.getPlayer(player_name);
                cel.kickPlayer(ChatUtil.fixColor(KICK_PLAYER.replace("{POWOD}", KickGUI.REASON_5)));
                API.clearGui(p);
            } else if(e.getSlot() == 15) {
                final Player cel = Bukkit.getPlayer(player_name);
                cel.kickPlayer(ChatUtil.fixColor(KICK_PLAYER.replace("{POWOD}", KickGUI.REASON_6)));
                API.clearGui(p);
            } else if(e.getSlot() == 16) {
                final Player cel = Bukkit.getPlayer(player_name);
                cel.kickPlayer(ChatUtil.fixColor(KICK_PLAYER.replace("{POWOD}", KickGUI.REASON_7)));
                API.clearGui(p);
            } else if(e.getSlot() == 26) {
                p.closeInventory();
                GUI.getMenu(p);
            }
        }
    }

    private void processIPBan(final String ip, final Player admin, final String reason) {
        API.setBanIP(admin, ip, reason);
        for (final Player player : Bukkit.getOnlinePlayers()) {
            if (player.getAddress().getAddress().getHostAddress().equals(ip)) {
                player.kickPlayer(ChatUtil.fixColor(BANIP_KICK_PLAYER.replace("{POWOD}", reason)));
            }
        }
        admin.sendMessage(ChatUtil.fixColor(PREFIX + " " + BANIP_ADMIN_MESSAGE.replace("{IP}", ip)));
        for (final Player admins : Bukkit.getOnlinePlayers()) {
            if (admins.hasPermission("xkenbangui.admin.banip")) {
                admins.sendMessage(ChatUtil.fixColor(PREFIX + " " + BANIP_ADMINS_MESSAGE.replace("{ADMIN}", admin.getName()).replace("{IP}", ip)));
            }
        }
        Bukkit.getConsoleSender().sendMessage(ChatUtil.fixColor(PREFIX + " " + BANIP_ADMINS_MESSAGE.replace("{ADMIN}", admin.getName()).replace("{IP}", ip)));
    }

    static {
        ipValidity = Pattern.compile("^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])$");
    }
}
